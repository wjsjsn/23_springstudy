package com.ict.bbs.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

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

import com.ict.bbs.model.service.BBSService;
import com.ict.bbs.model.vo.BBSVO;
import com.ict.common.paging;

@Controller
public class BBSController {

	@Autowired
	private BBSService bbsService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@GetMapping("/bbs_list.do")
	public ModelAndView bbsList(String page) {
		
		// 페이지 처리
		paging paging = new paging();
		
		// 1. 전체 게시물의 수
		int count = bbsService.getCount();
		paging.setTotalRecord(count);
		
		// 2. 전체 페이지 수
		// 전체 게시글의 수가 한 페이지 안에 존재하는 원글의 수보다 작거나 같으면 전체 페이지 수는 1페이지
		if(paging.getTotalRecord() <= paging.getNumPerPage()) {
			paging.setTotalPage(1);
		}else {
			paging.setTotalPage(paging.getTotalRecord() / paging.getNumPerPage());
			// 나머지가 있으면 1페이지 증가
			if(paging.getTotalRecord() % paging.getNumPerPage() != 0) {
				paging.setTotalPage(paging.getTotalPage() + 1);
			}
		}
		
		// 맨 처음 오면 page는 null임
		if(page == null) {
			paging.setNowPage(1);
		}else {
			paging.setNowPage(Integer.parseInt(page));
		}
		
		// 4. 현재 페이지의 시작 번호와 끝 번호 구하기
		paging.setBegin((paging.getNowPage() - 1) * paging.getNumPerPage() + 1);
		paging.setEnd((paging.getBegin() - 1) + paging.getNumPerPage());
	
		// 5. 현재 페이지의 시작과 끝 블록 구하기
		paging.setBeginBlock((int)(paging.getNowPage() - 1) / paging.getPagePerBlock() + 1);
		paging.setEndBlock(paging.getBeginBlock() + paging.getPagePerBlock() - 1);
		
		if(paging.getEndBlock() > paging.getTotalPage()) {
			paging.setEndBlock(paging.getTotalPage());
		}
		
		ModelAndView mv = new ModelAndView("bbs/list");
		List<BBSVO> bbs_list = bbsService.getBBSList(paging.getNowPage() + "");
		mv.addObject("paging", paging);
		mv.addObject("bbs_list", bbs_list);
		return mv;
	}

	@GetMapping("/bbs_insertForm.do")
	public ModelAndView getInsertForm(HttpServletRequest request) {
		return new ModelAndView("bbs/write");
	}

	@PostMapping("/bbs_insert.do")
	public ModelAndView bbsInsert(BBSVO bvo, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("redirect:/bbs_list.do");
		try {
			String path = request.getSession().getServletContext().getRealPath("/resources/images");
			MultipartFile file = bvo.getFile();
			if (file.isEmpty()) {
				bvo.setF_name("");
			} else {
				// 같은 이름의 파일이 없도록 uuid 사용
				UUID uuid = UUID.randomUUID();
				String f_name = uuid.toString() + "_" + bvo.getFile().getOriginalFilename();
				bvo.setF_name(f_name);

				// 이미지 저장
				byte[] in = bvo.getFile().getBytes();
				File out = new File(path, f_name);
				FileCopyUtils.copy(in, out);
			}
			// 패스워드 암호화
			bvo.setPwd(passwordEncoder.encode(bvo.getPwd()));
			int res = bbsService.getInsert(bvo);
			if (res > 0) {
				return mv;
			} else {
				return null;
			}
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	@GetMapping("/bbs_onelist.do")
	public ModelAndView getBBSOnelist(@ModelAttribute("b_idx") String b_idx) {
		ModelAndView mv = new ModelAndView("bbs/onelist");
		BBSVO bvo = bbsService.getBBSOnelist(b_idx);
		mv.addObject("bvo", bvo);
		return mv;
	}
	
	@PostMapping("/bbs_updateForm.do")
	public ModelAndView getUpdateForm(@ModelAttribute("b_idx") String b_idx) {
		ModelAndView mv = new ModelAndView("bbs/update");
		BBSVO bvo = bbsService.getBBSOnelist(b_idx);
		mv.addObject("bvo", bvo);
		return mv;
	}
	
	@PostMapping("/bbs_updateOk.do")
	public ModelAndView getBBSUpdateOk(BBSVO bvo, @RequestParam("file") MultipartFile file, HttpServletRequest request, @RequestParam("f_name") String f_name) {
		String file_name = file.getOriginalFilename();

		if (!passwordEncoder.matches(bvo.getPwd(), bbsService.getBBSOnelist(bvo.getB_idx()).getPwd())) {
			ModelAndView mv = new ModelAndView("bbs/update");
			String alertScript = "<script>alert('비밀번호가 일치하지 않습니다.');</script>";
			mv.addObject("bvo", bvo);
			String file2 = file.getOriginalFilename();
			mv.addObject("file2", file2);
		    mv.addObject("alertScript", alertScript);
			return mv;
		} else {
			String path = request.getSession().getServletContext().getRealPath("/resources/images");
			try {
				if (!f_name.isEmpty()) {
					if (file.isEmpty()) {
						bvo.setF_name(f_name);
					} else {
						bvo.setF_name(file_name);
						byte[] in = file.getBytes();
						File out = new File(path, file_name);
						FileCopyUtils.copy(in, out);
					}
				} else {
					if (file.isEmpty()) {
						bvo.setF_name("");
					} else {
						bvo.setF_name(file_name);
						byte[] in = file.getBytes();
						File out = new File(path,file_name);
						FileCopyUtils.copy(in, out);
					}
				}
				bbsService.getBBSUpdateOk(bvo);

				return new ModelAndView("redirect:/bbs_list.do");
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}
	}

	@PostMapping("/bbs_deleteForm.do")
	public ModelAndView getDeleteForm(@ModelAttribute("b_idx") String b_idx) {
		
	}
}
