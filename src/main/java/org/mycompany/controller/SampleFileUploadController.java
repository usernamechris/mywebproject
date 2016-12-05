package org.mycompany.controller;

import java.io.File;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class SampleFileUploadController {
	
	private static final Logger logger =
			LoggerFactory.getLogger(SampleFileUploadController.class);
	
	@Resource(name="uploadPath")  // servlet-context.xml에 정의
	private String uploadPath;
	
	@RequestMapping(value="/uploadForm", method = RequestMethod.GET)
	public void uploadForm() {
		
	}
	
	@RequestMapping(value="/uploadForm", method = RequestMethod.POST)
	public String uploadForm(MultipartFile file, Model model) throws Exception {
		
		logger.info("originalName: " + file.getOriginalFilename());
		logger.info("size: " + file.getSize());
		logger.info("contentType: " + file.getContentType());
		
		String savedName =
				uploadFile(file.getOriginalFilename(), file.getBytes());
		model.addAttribute("savedName", savedName);
		
		return "uploadResult";
		
	}
	

	@RequestMapping(value="/uploadAjax", method = RequestMethod.GET)
	public void uploadAjax() {
		
	}

	@RequestMapping(value="/uploadAjax",
					method = RequestMethod.POST,
					produces = "text/plain;charset=UTF-8") //한국어 전송을 위함
	private ResponseEntity<String> uploadAjax(MultipartFile file ) throws Exception {
		
		logger.info("originalName: " + file.getOriginalFilename());
		logger.info("size: " + file.getSize());
		logger.info("contentType: " + file.getContentType());

		return new ResponseEntity<>(file.getOriginalFilename(), HttpStatus.CREATED); // CREATE는 리소스가 정상적으로 생성되었다는 상태코드
	}

	private String uploadFile(String originalName, byte[] fileData)throws  Exception{

		UUID uid = UUID.randomUUID();

		String savedName = uid.toString() + "_"+ originalName;

		File target = new File(uploadPath,savedName);

		FileCopyUtils.copy(fileData, target);

		return savedName;
	
	}
}
