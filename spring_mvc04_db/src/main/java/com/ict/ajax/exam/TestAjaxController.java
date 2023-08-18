package com.ict.ajax.exam;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

//@Controller
//public class TestAjaxController {
	
	// 아래 두 메서드는 return 이 servlet-context.xml 의 viewResolver에 가서
	// prefix, suffix를 받아서 뷰가 만들어지고 만들어진 뷰 로 이동한다.
	// prefix(접두어) =>  /WEB-INF/views/" + result + ".jsp" <= suffix(접미어)
//	@RequestMapping("/test.do")
//	public ModelAndView Test01() {
//		ModelAndView mv = new ModelAndView("result");
//		// 일처라
//		
//		return mv ;
//	}
//	@RequestMapping("/test.do")
//	public String Test02() {
//		ModelAndView mv = new ModelAndView("result");
//		// 일처라
//		
//		return mv ;
//	}
		
	@RestController
	public class TestAjaxController {
		// servlet-context.xml로 리턴되지 않고 브라우저에 출력
		// 반환형이 String 인 경우 문서타입이 contentType = "text/html" 타입으로 알아서 처리 된다.
		
		@RequestMapping(value = "/test01.do" , produces = "text/html; charset=utf-8")
		@ResponseBody
		public String Text_Exam01() {
			String msg = "<h2>안녕하멘</h2>";
			return msg ;
		}
	
		@RequestMapping(value = "/test02.do" , produces = "text/xml; charset=utf-8")
		@ResponseBody
		public String XML_Exam01() {
			StringBuffer sb = new StringBuffer();
	        
			sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
	        sb.append("<products>");
	        sb.append("<product>");
	        sb.append("<name>흰우유</name>");
	        sb.append("<price>950</price>");
	        sb.append("</product>");
	        sb.append("<product>");
	        sb.append("<name>딸기우유</name>");
	        sb.append("<price>1050</price>");
	        sb.append("</product>");
	        sb.append("<product>");
	        sb.append("<name>초코우유</name>");
	        sb.append("<price>1100</price>");
	        sb.append("</product>");
	        sb.append("<product>");
	        sb.append("<name>바나나우유</name>");
	        sb.append("<price>1550</price>");
	        sb.append("</product>");
	        sb.append("</products>");
	        
	        return sb.toString();
		}
		
		@RequestMapping(value = "/test03.do" , produces = "text/xml; charset=utf-8")
		@ResponseBody
		public String XML_Exam02() {
			StringBuffer sb = new StringBuffer();
	       
			sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
	        sb.append("<products>");
	        sb.append("<product count=\"5\" name=\"제네시스\" />");
	        sb.append("<product count=\"7\" name=\"카렌스\" />");
	        sb.append("<product count=\"9\" name=\"카니발\" />");
	        sb.append("<product count=\"5\" name=\"카스타\" />");
	        sb.append("<product count=\"2\" name=\"트위치\" />");
	        sb.append("</products>");
	       
	        return sb.toString();
		}
		
		@RequestMapping(value = "/test04.do" , produces = "text/xml; charset=utf-8")
		@ResponseBody
		public String XML_Exam03() {
			 StringBuffer sb = new StringBuffer();
		        
			 sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		        sb.append("<products>");
		        sb.append("<product count=\"5\" name=\"제네시스\"> 현대자동자 </product>");
		        sb.append("<product count=\"7\" name=\"카렌스\"> 기아자동자 </product>");
		        sb.append("<product count=\"9\" name=\"카니발\"> 기아자동자 </product>");
		        sb.append("<product count=\"5\" name=\"카스타\"> 기아자동자 </product>");
		        sb.append("<product count=\"2\" name=\"트위치\"> 르노자동자 </product>");
		        sb.append("</products>");
		      
		        return sb.toString();
		}
		
		@RequestMapping(value = "/test05.do" , produces = "text/xml; charset=utf-8")
		@ResponseBody
		public String XML_Exam04() {
			StringBuffer sb = new StringBuffer();
			BufferedReader br = null;
			try {
				URL url = new URL("http://www.kma.go.kr/XML/weather/sfc_web_map.xml");
				URLConnection conn = url.openConnection();
				
				br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
				
				String msg = "";
				while((msg = br.readLine()) != null) {
					sb.append(msg);
				}
				return sb.toString();
			} catch (Exception e) {
				System.out.println(e);
			}
			return null;
		}
		
		@RequestMapping(value = "/test06.do" , produces = "text/json; charset=utf-8")
		@ResponseBody
		public String Json_Exam() {
			StringBuffer sb = new StringBuffer();
			BufferedReader br = null;
			try {
				URL url = new URL("https://raw.githubusercontent.com/paullabkorea/coronaVaccinationStatus/main/data/data.json");
				URLConnection conn = url.openConnection();
				
				br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
				
				String msg = "";
				while((msg = br.readLine()) != null) {
					sb.append(msg);
				}
				return sb.toString();
			} catch (Exception e) {
				System.out.println(e);
			}
			return null;
		}
}
