package com.ict.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ict.model.service.Guestbook2Service;
import com.ict.model.vo.Guestbook2VO;

@Controller
public class Guestbook2Controller {
	// 일처리(DB)가 있으면 서비스로 가기
	@Autowired
	private Guestbook2Service guestbook2Service;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@GetMapping("/guestbook2_list.do")
	public ModelAndView getGuestList() {
		ModelAndView mv = new ModelAndView("guestbook2/list");
		List<Guestbook2VO> list = guestbook2Service.guestbookList();
		
		mv.addObject("list", list);
		return mv;
	}

	@GetMapping("/guestbook2_write.do")
	public ModelAndView Write() {
		return new ModelAndView("guestbook2/write");
	}

	@PostMapping("/guestbook2_writeOk.do")
	public ModelAndView WriteOk(@RequestParam("file") MultipartFile file, Guestbook2VO gvo,
			HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("redirect:/guestbook2_list.do");
		String path = request.getSession().getServletContext().getRealPath("/resources/images");
		try {
			if (!file.isEmpty()) {
				String f_name = file.getOriginalFilename();
				gvo.setF_name(f_name);
				byte[] in = file.getBytes();
				File out = new File(path, f_name);
				FileCopyUtils.copy(in, out);
			} 
			
			// 패스워드 암호화
			System.out.println("변경전: " + gvo.getPwd());
			gvo.setPwd(passwordEncoder.encode(gvo.getPwd()));
			System.out.println("변경후: " + gvo.getPwd());
			guestbook2Service.WriteAdd(gvo);
			return mv;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// idx는 onelist.jsp에도 사용하기 때문에 넘겨야함
	@GetMapping("/guestbook2_onelist.do")
	public ModelAndView oneList(@ModelAttribute("idx") String idx) {
		ModelAndView mv = new ModelAndView("guestbook2/onelist");
		Guestbook2VO gvo = guestbook2Service.oneList(idx);
		mv.addObject("gvo", gvo);
		return mv;
	}

	@PostMapping("/guestbook2_update.do")
	public ModelAndView UpdateAdd(String idx) {
		ModelAndView mv = new ModelAndView("guestbook2/update");
		Guestbook2VO gvo = guestbook2Service.oneList(idx);
		mv.addObject("gvo", gvo);
		return mv;
	}

	@PostMapping("/guestbook2_updateOk.do")
	public ModelAndView UpdateOk(Guestbook2VO gvo, @RequestParam("file") MultipartFile file,
			@RequestParam("f_name") String f_name, HttpServletRequest request) {
		String file_name = file.getOriginalFilename();

		if (!passwordEncoder.matches(gvo.getPwd(), guestbook2Service.oneList(gvo.getIdx()).getPwd())) {
			ModelAndView mv = new ModelAndView("guestbook2/update");
			String alertScript = "<script>alert('비밀번호가 일치하지 않습니다.');</script>";
			mv.addObject("gvo", gvo);
			String file2 = file.getOriginalFilename();
			mv.addObject("file2", file2);
		    mv.addObject("alertScript", alertScript);
			return mv;
		} else {
			String path = request.getSession().getServletContext().getRealPath("/resources/images");
			try {
				if (!f_name.isEmpty()) {
					if (file.isEmpty()) {
						gvo.setF_name(f_name);
					} else {
						gvo.setF_name(file_name);
						byte[] in = file.getBytes();
						File out = new File(path, file_name);
						FileCopyUtils.copy(in, out);
					}
				} else {
					if (file.isEmpty()) {
						gvo.setF_name("");
					} else {
						gvo.setF_name(file_name);
						byte[] in = file.getBytes();
						File out = new File(path,file_name);
						FileCopyUtils.copy(in, out);
					}
				}
				guestbook2Service.UpdateOk(gvo);

				return new ModelAndView("redirect:/guestbook2_list.do");
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}
	}

	@PostMapping("/guestbook2_delete.do")
	public ModelAndView delete(@ModelAttribute("idx") String idx) {
		ModelAndView mv = new ModelAndView("guestbook2/delete");
		// jsp에서 실제 삭제할 때 비밀번호를 검사하기 위해서 oneList() 실행
		Guestbook2VO gvo = guestbook2Service.oneList(idx);
		mv.addObject("gvo", gvo);
		return mv;
	}

	@GetMapping("/guestbook2_deleteOk.do")
	public ModelAndView DeleteOk(Guestbook2VO gvo) {
		if (!passwordEncoder.matches(gvo.getPwd(), guestbook2Service.oneList(gvo.getIdx()).getPwd())) {
			ModelAndView mv = new ModelAndView("guestbook2/delete");
			String alertScript = "<script>alert('비밀번호가 일치하지 않습니다.');</script>";
			 mv.addObject("alertScript", alertScript);
			 mv.addObject("gvo", gvo);
			 return mv;
	}else {
		ModelAndView mv = new ModelAndView("redirect:/guestbook2_list.do");
		guestbook2Service.DeleteOk(gvo.getIdx());
		return mv;
	}
	}

	@GetMapping("/guestbook2_down.do")
	public void DownOk(HttpServletRequest request, HttpServletResponse response) {
		String f_name = request.getParameter("f_name");
		String path = request.getSession().getServletContext().getRealPath("/resources/images/" + f_name);

		try {
			String realPath = URLEncoder.encode(path, "utf-8");

			response.setContentType("application/x-msdownload");
			response.setHeader("Content-Disposition", "attachment; filename=" + realPath);

			// 실제 저장
			File file = new File(new String(path.getBytes()));
			FileInputStream in = new FileInputStream(file);
			OutputStream out = response.getOutputStream();

			// 파일복사(upload)
			FileCopyUtils.copy(in, out);
		} catch (Exception e) {
		}
	}
}
