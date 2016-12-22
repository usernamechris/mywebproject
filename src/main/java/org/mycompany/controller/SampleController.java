package org.mycompany.controller;

import org.mycompany.domain.ProductVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SampleController {
	
	private static final Logger logger =
			LoggerFactory.getLogger(SampleController.class);
	
	@RequestMapping("doA")
	public void doA() {
		logger.info("doA called.................");
	}

	@RequestMapping("doB")
	public void doB() {
		logger.info("doB called.................");
	}

	@RequestMapping("doC")
	public String doC(@ModelAttribute("msg") String msg) { //request시 msg 이름의 파라미터를 문자열로 처리. msg객체를 뷰까지 전달
		logger.info("doC called................." + "(" + msg + ")");
		return "result"; // /WEB-INF/views/result.jsp파일을 찾아서 실행
	}
	
	@RequestMapping("doD")
	public String doD(Model model) { //Model은 뷰에 원하는 데이터를 전달하는 일종의 상자
		
		// make sample data
		ProductVO product = new ProductVO("Sample Product", 10000);

		logger.info("doD called.................");

		model.addAttribute(product);
		
		return "productDetail"; // /WEB-INF/views/productDetail.jsp파일을 찾아서 실행
	}
	
	@RequestMapping("doE")
	public String doE(RedirectAttributes rttr) { //리다이렉트하면서 파라미터 추가해서 넘김
		
		logger.info("doE called but redirect to /doF.................");

		rttr.addFlashAttribute("msg", "This is the Message!! with redirected"); //임시데이터 전달
		
		return "redirect:/doF";
	}
	
	@RequestMapping("doF")
	public void doF(@ModelAttribute("msg") String msg) {
		
		logger.info("doF called................." +  msg );

	}
	
	
	@RequestMapping("doJSON")
	public @ResponseBody ProductVO doJSON() { //@ResponseBody는 json객체로 리턴
		
		ProductVO vo = new ProductVO("샘플상품", 30000);
		
		return vo;
	}
	
}

