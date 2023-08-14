package com.ict.board.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ict.board.model.service.BoardService;
import com.ict.board.model.vo.BoardVO;
import com.ict.common.paging;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;

	@Autowired
	private paging paging;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@RequestMapping("/board_list.do")
	public ModelAndView getBoardList(HttpServletRequest request) {

		// 1. 전체 게시물의 수
		int count = boardService.getCount();
		paging.setTotalRecord(count);

		// 2. 전체 페이지 수
		// 전체 게시글의 수가 한 페이지 안에 존재하는 원글의 수보다 작거나 같으면 전체 페이지 수는 1페이지
		if (paging.getTotalRecord() <= paging.getNumPerPage()) {
			paging.setTotalPage(1);
		} else {
			paging.setTotalPage(paging.getTotalRecord() / paging.getNumPerPage());
			// 나머지가 있으면 1페이지 증가
			if (paging.getTotalRecord() % paging.getNumPerPage() != 0) {
				paging.setTotalPage(paging.getTotalPage() + 1);
			}
		}

		// 현재 페이지
		String page = request.getParameter("page");
		if (page == null) {
			paging.setNowPage(1);
		} else {
			paging.setNowPage(Integer.parseInt(page));
		}

		// begin, end 대신 limit, offset
		// limit = paging.getNumPerPage()

		// offset = limit * (현재 페이지 - 1)
		paging.setOffset(paging.getNumPerPage() * (paging.getNowPage() - 1));

		// 현재 페이지의 시작과 끝 블록 구하기
		paging.setBeginBlock(
				(int) ((paging.getNowPage() - 1) / paging.getPagePerBlock()) * paging.getPagePerBlock() + 1);
		paging.setEndBlock(paging.getBeginBlock() + paging.getPagePerBlock() - 1);

		if (paging.getEndBlock() > paging.getTotalPage()) {
			paging.setEndBlock(paging.getTotalPage());
		}

		ModelAndView mv = new ModelAndView("board/list");
		List<BoardVO> board_list = boardService.getBoardList(paging.getOffset(), paging.getNumPerPage());

		mv.addObject("page", paging.getNowPage());
		mv.addObject("paging", paging);
		mv.addObject("board_list", board_list);
		return mv;
	}

	@GetMapping("board_insertForm.do")
	public ModelAndView boardInsertForm() {
		return new ModelAndView("board/write");
	}

	@PostMapping("board_insert.do")
	public ModelAndView BoardInsertOk(BoardVO bovo, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("redirect:/board_list.do");
		try {
			String path = request.getSession().getServletContext().getRealPath("/resources/images");
			MultipartFile file = bovo.getFile();
			if (file.isEmpty()) {
				bovo.setF_name("");
			} else {
				// 같은 이름 없도록 uuid 사용
				UUID uuid = UUID.randomUUID();
				String f_name = uuid.toString() + "_" + bovo.getFile().getOriginalFilename();
				bovo.setF_name(f_name);

				// 이미지 저장
				byte[] in = bovo.getFile().getBytes();
				File out = new File(path, f_name);
				FileCopyUtils.copy(in, out);
			}
			// 패스워드 암호화
			bovo.setPwd(passwordEncoder.encode(bovo.getPwd()));

			boardService.BoardInsertOk(bovo);
			return mv;
		} catch (Exception e) {
		}
		return null;
	}

	@GetMapping("/board_onelist.do")
	public ModelAndView BoardOneList(@ModelAttribute("idx") String idx, @ModelAttribute("page") String page) {
		ModelAndView mv = new ModelAndView("board/onelist");
		BoardVO bovo = boardService.BoardOneList(idx);

		// 조회수 업데이트
		int hit = boardService.getBoardHit(idx);

		mv.addObject("bovo", bovo);
		mv.addObject("page", page);
		return mv;
	}

	@PostMapping("/board_updateForm.do")
	public ModelAndView BoardUpdateForm(@ModelAttribute("idx") String idx, @ModelAttribute("page") String page) {
		ModelAndView mv = new ModelAndView("board/update");
		BoardVO bovo = boardService.BoardOneList(idx);
		mv.addObject("bovo", bovo);
		mv.addObject("page", page);
		return mv;
	}

	@PostMapping("/board_updateOk.do")
	public ModelAndView BoardUpdateOk(BoardVO bovo, @RequestParam("page")String page, 
			@RequestParam("file")MultipartFile file, @RequestParam("f_name")String f_name, HttpServletRequest request) {
		String file_name = file.getOriginalFilename();
		if (!passwordEncoder.matches(bovo.getPwd(), boardService.BoardOneList(bovo.getIdx()).getPwd())) {
			ModelAndView mv = new ModelAndView("board/update");
			String alertScript = "<script>alert('비밀번호가 일치하지 않습니다.');</script>";
			mv.addObject("bovo", bovo);
			String file2 = file.getOriginalFilename();
			mv.addObject("file2", file2);
			mv.addObject("alertScript", alertScript);
			return mv;
		} else {
			String path = request.getSession().getServletContext().getRealPath("/resources/images");
			try {
				if (!f_name.isEmpty()) {
					if (file.isEmpty()) {
						bovo.setF_name(f_name);
					} else {
						bovo.setF_name(file_name);
						byte[] in = file.getBytes();
						File out = new File(path, file_name);
						FileCopyUtils.copy(in, out);
					}
				} else {
					if (file.isEmpty()) {
						bovo.setF_name("");
					} else {
						bovo.setF_name(file_name);
						byte[] in = file.getBytes();
						File out = new File(path, file_name);
						FileCopyUtils.copy(in, out);
					}
				}
				ModelAndView mv = new ModelAndView("redirect:/board_list.do");
				boardService.getBoardUpdateOk(bovo);
				mv.addObject("page", page);
				return mv;
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}
	}
	
	@PostMapping("/board_deleteForm.do")
	public ModelAndView BoardDeleteForm(@ModelAttribute("page") String page, @ModelAttribute("idx") String idx) {
		ModelAndView mv = new ModelAndView("board/delete");
		BoardVO bovo = boardService.BoardOneList(idx);
		mv.addObject("bovo", bovo);
		return mv;
	}
	
	@PostMapping("/board_deleteOk.do")
	public ModelAndView getBoardDeleteOk(String idx, @ModelAttribute("page") String page) {
		ModelAndView mv = new ModelAndView("redirect:/board_list.do");
		boardService.getBoardDeleteOk(idx);
		mv.addObject("page", page);
		return mv;
	}

	@GetMapping("/board_down.do")
	public void BoardDown(@RequestParam("f_name") String f_name, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String path = request.getSession().getServletContext().getRealPath("/resources/images/" + f_name);
			String realPath = URLEncoder.encode(path, "utf-8");
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-Disposition", "attachment; filename=" + realPath);

			File file = new File(new String(path.getBytes()));
			FileInputStream in = new FileInputStream(file);
			OutputStream out = response.getOutputStream();
			FileCopyUtils.copy(in, out);
		} catch (Exception e) {
		}
	}

	@PostMapping("/board_ans_insertForm.do")
	public ModelAndView BoardAnsInsertForm(@ModelAttribute("idx")String idx, @ModelAttribute("page")String page) {
		return new ModelAndView("board/ans_write");
	}

	@PostMapping("/board_ans_insert.do")
	public ModelAndView BoardAnsInsert(@ModelAttribute("page") String page, @ModelAttribute("idx") String idx,
			BoardVO bovo, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("redirect:/board_list.do");
		try {
			// 상세보기에서 groups, step, lev 가져오기
			BoardVO bvo = boardService.BoardOneList(idx);

			int groups = Integer.parseInt(bvo.getGroups());
			int step = Integer.parseInt(bvo.getStep());
			int lev = Integer.parseInt(bvo.getLev());

			// step, lev 1씩 올려주기
			step++;
			lev++;

			// db에 groups, lev 업데이트
			// groups와 같은 원글을 찾아서 lev이 같거나 크면 lev 증가시킴
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("groups", groups);
			map.put("lev", lev);

			boardService.getLevUpdate(map);

			bovo.setGroups(String.valueOf(groups));
			bovo.setStep(String.valueOf(step));
			bovo.setLev(String.valueOf(lev));

			// 암호화, 첨부파일 처리
			String path = request.getSession().getServletContext().getRealPath("/resources/images");
			MultipartFile file = bovo.getFile();
			if (file.isEmpty()) {
				bovo.setF_name("");
			} else {
				// 같은 이름 없도록 uuid 사용
				UUID uuid = UUID.randomUUID();
				String f_name = uuid.toString() + "_" + bovo.getFile().getOriginalFilename();
				bovo.setF_name(f_name);

				// 이미지 저장
				byte[] in = bovo.getFile().getBytes();
				File out = new File(path, f_name);
				FileCopyUtils.copy(in, out);
			}
			boardService.BoardAnsInsert(bovo);
			mv.addObject("page", page);
			return mv;
		} catch (Exception e) {
			return mv;
		}
	}
}
