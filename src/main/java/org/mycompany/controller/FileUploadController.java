package org.mycompany.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.mycompany.util.MediaUtils;
import org.mycompany.util.UploadFileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {
	
	private static final Logger logger =
			LoggerFactory.getLogger(FileUploadController.class);
	
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

	@ResponseBody
	@RequestMapping(value="/uploadAjax",
					method = RequestMethod.POST,
					produces = "text/plain;charset=UTF-8") //한국어 전송을 위함
	private ResponseEntity<String> uploadAjax(MultipartFile file ) throws Exception {
		
		logger.info("originalName: " + file.getOriginalFilename());
		logger.info("size: " + file.getSize());
		logger.info("contentType: " + file.getContentType());

		return new ResponseEntity<>(UploadFileUtils.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes()),
				HttpStatus.CREATED);// CREATE는 리소스가 정상적으로 생성되었다는 상태코드
	}

	private String uploadFile(String originalName, byte[] fileData)throws  Exception{

		UUID uid = UUID.randomUUID();

		String savedName = uid.toString() + "_"+ originalName;

		File target = new File(uploadPath,savedName);

		FileCopyUtils.copy(fileData, target);
		
		logger.info("file absolutePath: " + target.getAbsolutePath());

		return savedName;
	
	}
	
	@ResponseBody
	@RequestMapping("/displayFile")
	public ResponseEntity<byte[]> displayFile(String fileName) throws Exception {
		InputStream in = null;
		ResponseEntity<byte[]> entity = null;
		
		logger.info("/displayFile File Name: " + fileName);
		
		try {
			String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
			
			MediaType mType = MediaUtils.getMediaType(formatName);
			HttpHeaders headers = new HttpHeaders();
			in = new FileInputStream(uploadPath + fileName);
			
			if(mType != null) {
				headers.setContentType(mType);
			} else {
				fileName = fileName.substring(fileName.indexOf('_') + 1 );
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM); // mime타입 설정. 브라우저는 이것보고 다운로드 창 열어줌 
				headers.add("Content-Disposition", "attachment; filename=\"" +
						new String(fileName.getBytes("UTF-8"), "ISO-8859-1") + "\""); // 한글파일명을 위한 처리
			}
			
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in),
					headers,
					HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		} finally {
			in.close();
		}
		return entity;
	}
	
	@ResponseBody
	@RequestMapping(value="/deleteFile", method = RequestMethod.POST)
	public ResponseEntity<String> deleteFile(String fileName) {

		logger.info("delete file: " + fileName);
		
		String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
		
		MediaType mType = MediaUtils.getMediaType(formatName);
		
		if (mType != null) {
			String front = fileName.substring(0, 12);
			String end = fileName.substring(14);
			new File(uploadPath + (front + end).replace('/', File.separatorChar)).delete();
		}
		
		new File(uploadPath + fileName.replace('/', File.separatorChar)).delete();
		
		return new ResponseEntity<String>("deleted", HttpStatus.OK);

	}
	
	@ResponseBody
	@RequestMapping(value="/deleteAllFiles", method = RequestMethod.POST)
	public ResponseEntity<String> deleteFile(@RequestParam("files[]") String[] files) {
		logger.info("deleteAllFiles: " + files);
		
		if (files == null || files.length == 0) {
			return new ResponseEntity<String>("deleted", HttpStatus.OK);
		}
		
		for (String fileName : files) {
			String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
			
			MediaType mType = MediaUtils.getMediaType(formatName);
			
			if (mType != null) {
				String front = fileName.substring(0, 12);
				String end = fileName.substring(14);
				new File(uploadPath + (front + end).replace('/', File.separatorChar)).delete();
			}

			new File(uploadPath + fileName.replace('/', File.separatorChar)).delete();
			
		}

		return new ResponseEntity<String>("deleted", HttpStatus.OK);
	}
	
}
