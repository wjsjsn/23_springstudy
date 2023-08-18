package com.ict.ajax.kakao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class kakaoController {

	// 인증코드를 받기 위해서 redirect
	@RequestMapping("/kakaologin.do")
	public ModelAndView kakaoLogin(String code) {
		ModelAndView mv = new ModelAndView("result3");

		// 토큰 받기
		String reqURL = "https://kauth.kakao.com/oauth/token";
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			// POST 요청에 필요
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);

			// 요청 헤더 부분
			conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

			// 본문에 필요한 요구사항 4개
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			StringBuffer sb = new StringBuffer();
			sb.append("grant_type=authorization_code");
			sb.append("&client_id=1c3d098ba623cce162dc3e7c0153228d");
			sb.append("&redirect_uri=http://localhost:8090/kakaologin.do");
			sb.append("&code=" + code);

			bw.write(sb.toString());
			bw.flush();

			int responseCode = conn.getResponseCode();
			System.out.println("responseCode : " + responseCode);

			// 성공하면 200
			if (responseCode == 200) {
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				StringBuffer result = new StringBuffer();
				String line = null;
				while ((line = br.readLine()) != null) {
					result.append(line);
				}
				
				// json 파싱 처리 "access_token"과 "refresh_token"을 잡아내서 
				// kakao api 요청을 한 후
				// ModelAndView에 저장해 result3.jsp에서 결과 표현
				JSONParser pars = new JSONParser();
				Object obj = pars.parse(result.toString());
				JSONObject json = (JSONObject)obj;
				
				String access_token = (String)json.get("access_token");
				String refresh_token = (String)json.get("refresh_token");
				
				// 마지막 3번째 사용자 정보 요청
				// get/post  https://kapi.kakao.com/v2/user/me
				String apiURL = "https://kapi.kakao.com/v2/user/me";
				
				// 헤더부분
				// Authorization    Autorization:Bearer ${ACCESS_TOKEN}
				// Content-type      Content-type:application/x-www-form-urlencoded;charset=utf-8
				String header = "Bearer " + access_token;
				
				URL url2 = new URL(apiURL);
				HttpURLConnection conn2 = (HttpURLConnection)url2.openConnection();
				
				// post 타입
				conn2.setRequestMethod("POST");
				conn2.setDoOutput(true);
				
				// 헤더 요청사항
				conn2.setRequestProperty("Authorization", header);
			//	conn2.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
			
				int res_code = conn2.getResponseCode();
				
				 // 성공 시 200 => HttpURLConnection.HTTP_OK
				if(res_code == HttpURLConnection.HTTP_OK) {
					// 카카오 서버 쪽에서 사용자의 정보를 보냄
					// 이것을 읽어와서 필요한 정보들을 선별해야함
					BufferedReader brdm = new BufferedReader(new InputStreamReader(conn2.getInputStream()));
					String str = null;
					StringBuffer res = new StringBuffer();
					while ((str = brdm.readLine()) != null) {
						res.append(str);
					} 
					
					// 사용자 정보
					 System.out.println(res.toString());
					json = (JSONObject)pars.parse(res.toString());
					
					JSONObject props = (JSONObject)json.get("properties");
					String nickname = (String)props.get("nickname");
					String profile_image = (String)props.get("profile_image");
					
					JSONObject kakao_account = (JSONObject)json.get("kakao_account");
					String email = (String)kakao_account.get("email");
					
					mv.addObject("nickname", nickname);
					mv.addObject("email", email);
					mv.addObject("profile_image", profile_image);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return mv;
	}
	
	// 카카오 지도는 REST API 지원하지 않는다.(자바스크립트를 지원)
    @RequestMapping("/kakaomap01.do")
    public ModelAndView KakaoMap01() {
        return new ModelAndView("kakao_map1");
    }
   
    @RequestMapping("/kakaomap02.do")
    public ModelAndView KakaoMap02() {
        return new ModelAndView("kakao_map2");
    }
    
    @RequestMapping("/kakaomap03.do")
    public ModelAndView KakaoMap03() {
        return new ModelAndView("kakao_map3");
    }
    
    @RequestMapping("/kakaomap04.do")
    public ModelAndView KakaoMap04() {
        return new ModelAndView("kakao_map4");
    }
    
    @RequestMapping("/kakaoaddr.do")
    public ModelAndView KakaoAddr() {
    	return new ModelAndView("kakao_addr");
    }
    
    @RequestMapping("/kakao_addr_ok.do")
    public ModelAndView KakaoAddrOk(addrVO addrVO) {
    	ModelAndView mv = new ModelAndView("redirect:/");
    	System.out.println(addrVO.getPostcode());
    	System.out.println(addrVO.getAddress());
    	if(addrVO.getDetailAddress() == null || addrVO.getDetailAddress() == "") {
    		System.out.println("상세없음");
    	}else {
    		System.out.println(addrVO.getDetailAddress());
    	}
    	System.out.println(addrVO.getExtraAddress());
    	// db 처리
    	return mv;
    }
}
