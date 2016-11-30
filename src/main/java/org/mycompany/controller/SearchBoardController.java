package org.mycompany.controller;

import javax.inject.Inject;

import org.mycompany.domain.BoardVO;
import org.mycompany.domain.PageMaker;
import org.mycompany.domain.SearchCriteria;
import org.mycompany.service.BoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/sboard/*")
public class SearchBoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(SearchBoardController.class);
	
	@Inject
	private BoardService service;
	
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void listPage(@ModelAttribute("cri") SearchCriteria cri,
			Model model) throws Exception {

		logger.info("/list: " + cri.toString());
		
		model.addAttribute("list", service.listSearchCriteria(cri));
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		
		pageMaker.setTotalCount(service.listSearchCount(cri));
		
		model.addAttribute("pageMaker", pageMaker);
	}
	
	@RequestMapping(value = "/readPage", method = RequestMethod.GET)
	public void read(@RequestParam("bno") int bno,
			@ModelAttribute("cri") SearchCriteria cri,
			Model model) throws Exception {
		logger.info("/read: " + cri.toString());

		model.addAttribute(service.read(bno));
	}

	@RequestMapping(value = "/removePage", method = RequestMethod.POST)
	public String remove(@RequestParam("bno") int bno,
			SearchCriteria cri,
			RedirectAttributes rttr) throws Exception {

		logger.info("/removePage: " + cri.toString());
		
		service.remove(bno);
		
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());
		
		rttr.addFlashAttribute("msg", "SUCCESS");
		
		return "redirect:/sboard/list";
	}
	
	@RequestMapping(value = "/modifyPage", method = RequestMethod.GET)
	public void modifyPagingGET(
			int bno,
			@ModelAttribute("cri") SearchCriteria cri,
			Model model) throws Exception {

		logger.info("/modifyPagingGet: " + cri.toString() + ", " +  bno);

		model.addAttribute(service.read(bno));
	}
	
	@RequestMapping(value = "/modifyPage", method = RequestMethod.POST)
	public String modifyPagingPOST(
			BoardVO board,
			SearchCriteria cri,
			RedirectAttributes rttr) throws Exception {

		logger.info("/modifyPagingPOST: " + cri.toString());
		
		service.modify(board);
		
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());

		rttr.addFlashAttribute("msg", "SUCCESS");
		
		logger.info(rttr.toString());
		
		return "redirect:/sboard/list";
	}
	
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void registGET() {

		logger.info("/registGET: " );

	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registPOST( BoardVO board, RedirectAttributes rttr) throws Exception {

		logger.info("/registPOST: " + board.toString());
		
		service.regist(board);
		
		rttr.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/sboard/list";

	}
}