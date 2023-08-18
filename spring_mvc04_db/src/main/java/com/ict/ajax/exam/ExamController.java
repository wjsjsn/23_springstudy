package com.ict.ajax.exam;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.jdom2.input.SAXBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Controller
public class ExamController {

	@RequestMapping("/kma_go.do")
	public ModelAndView getKmaGo() {
		ModelAndView mv = new ModelAndView("result");
		StringBuffer sb = new StringBuffer();
		BufferedReader br = null;
		try {
			URL url = new URL("http://www.kma.go.kr/XML/weather/sfc_web_map.xml");
			URLConnection conn = url.openConnection();

			br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
			String msg = "";
			while ((msg = br.readLine()) != null) {
				sb.append(msg);
			}

			InputSource in = new InputSource(new StringReader(sb.toString()));

			// 자바에서 XML 파싱하는 방법 : DOM 방식, SAX 방식
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(in);

			// 저장 VO 만들기
			ArrayList<kmaVO> list = new ArrayList<kmaVO>();

			// 원하는 태그 찾기
			NodeList locals = document.getElementsByTagName("local");
			// kmaVO[] arr = new kmaVO[locals.getLength()];
			for (int i = 0; i < locals.getLength(); i++) {

				// 태그의 text 구하기
				String local = locals.item(i).getFirstChild().getNodeValue();

				// 태그의 속성 구하기
				String ta = ((Element) (locals.item(i))).getAttribute("ta");
				String desc = ((Element) (locals.item(i))).getAttribute("desc");
				String icon = ((Element) (locals.item(i))).getAttribute("icon");

				kmaVO kmavo = new kmaVO(local, ta, desc, icon);
				list.add(kmavo);
			}

			mv.addObject("list", list);
			return mv;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	@RequestMapping("/kma_go2.do")
	public ModelAndView getKmaGo2() {
		ModelAndView mv = new ModelAndView("result");
		StringBuffer sb = new StringBuffer();
		BufferedReader br = null;
		try {
			URL url = new URL("http://www.kma.go.kr/XML/weather/sfc_web_map.xml");
			URLConnection conn = url.openConnection();

			br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));

			String msg = "";
			while ((msg = br.readLine()) != null) {
				sb.append(msg);
			}

			InputSource in = new InputSource(new StringReader(sb.toString()));

			SAXBuilder builder = new SAXBuilder();
			org.jdom2.Document doc = builder.build(in);
			org.jdom2.Element rootElement = doc.getRootElement();
			org.jdom2.Element weather = rootElement.getChild("weather", null);

			List<org.jdom2.Element> local = weather.getChildren("local", null);

			ArrayList<kmaVO> list = new ArrayList<kmaVO>();

			for (org.jdom2.Element k : local) {
				String loc = k.getText();
				String ta = k.getAttributeValue("ta");
				String desc = k.getAttributeValue("desc");
				String icon = k.getAttributeValue("icon");

				kmaVO kmavo = new kmaVO(loc, ta, desc, icon);
				list.add(kmavo);
			}
			mv.addObject("list", list);
			return mv;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	@RequestMapping("/json_go.do")
	public ModelAndView getJson_go() {
		ModelAndView mv = new ModelAndView("result2");
		StringBuffer sb = new StringBuffer();
		BufferedReader br = null;
		try {
			URL url = new URL("https://raw.githubusercontent.com/paullabkorea/coronaVaccinationStatus/main/data/data.jsons");
			URLConnection conn = url.openConnection();

			br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));

			String msg = "";
			while ((msg = br.readLine()) != null) {
				sb.append(msg);
			}

			// InputSource in = new InputSource(new StringReader(sb.toString()));
			JSONParser jsonParser = new JSONParser();
			
			// json 데이터를 넣어 json object로 만들어줌(key가 있을 때)
			// JSONObject jObject = (JSONObject) jsonParser.parse(sb.toString());
			
			// 배열로 시작하면서 key가 없는 경우
			JSONArray arr = (JSONArray) jsonParser.parse(sb.toString());
			
			List<jsonVO> list = new ArrayList<jsonVO>();
			for (int i = 0; i < arr.size(); i++) {
				JSONObject obj = (JSONObject) arr.get(i);
				String city = (String)obj.get("시·도별(1)");
				long totalCount = (long)obj.get("총인구 (명)");
				long firstCount = (long)obj.get("1차 접종 누계");
				double firstPercent = (double)obj.get("1차 접종 퍼센트");
				long secondCount = (long)obj.get("2차 접종 누계");
				double secondPercent = (double)obj.get("2차 접종 퍼센트");
				
				jsonVO jvo = new jsonVO(city, totalCount, firstCount, secondCount, firstPercent, secondPercent);
				list.add(jvo);
			}
			mv.addObject("list", list);
			return mv;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
}
