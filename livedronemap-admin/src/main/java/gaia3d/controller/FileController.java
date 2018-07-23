package gaia3d.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import gaia3d.config.PropertiesConfig;
import gaia3d.domain.FileInfo;
import gaia3d.service.FileService;
import gaia3d.util.FileUtil;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
@RequestMapping("/file/")
public class FileController {
	
	@Autowired
	PropertiesConfig propertiesConfig;

	
	@Autowired
	FileService fileService;
	
	
	@GetMapping("list")
	public List<FileInfo> listFil(FileInfo fileInfo){
		List<FileInfo> fileList = new ArrayList<>();
		fileList = fileService.getFileList(fileInfo);
		
		return fileList;
	}
/*	
	@GetMapping("uploadForm")
	public String uploadFileForm() {
		
		return "/file/input-upload";
	}
*/
	
	@GetMapping("uploadForm")
	public String uploadFileForm() {
		
		return "/file/ajax-upload";
	}
	
	@PostMapping("upload")
	public void uploadFile(@RequestParam("file") MultipartFile file, Model model) throws IOException {
		
		log.info("@@ OriginalName : " + file.getOriginalFilename());
		log.info("@@ size : " + file.getSize());
		log.info("@@ contentType : " + file.getContentType());
		
		String saveName = uploadFile(file.getOriginalFilename(), file.getBytes());
		
		model.addAttribute("saveName", saveName);
	}
	
/*	
	@PostMapping("uploadIframe")
	public String uploadFile(@RequestParam("file") MultipartFile file, Model model) throws IOException {
		
		log.info("@@ OriginalName : " + file.getOriginalFilename());
		log.info("@@ size : " + file.getSize());
		log.info("@@ contentType : " + file.getContentType());
		
		String saveName = uploadFile(file.getOriginalFilename(), file.getBytes());
		
		model.addAttribute("saveName", saveName);
		
		return "/file/iframe-upload";
	}
*/

	private String uploadFile(String originalFilename, byte[] fileData) throws IOException{
		UUID uid = UUID.randomUUID();
		log.info("@@ uid : " + uid);
		String saveName = uid.toString() + "_" + originalFilename;
		log.info("@@ saveName : " + saveName);
		File target = new File(propertiesConfig.getSampleUploadDir(), saveName);
		log.info("@@ target : " + target);
		FileCopyUtils.copy(fileData, target);
		
		return saveName;
	}
/*	
	@PostMapping("uploadAjax")
	@ResponseBody
	public ResponseEntity<String> uploadAjax(MultipartFile file) throws IOException {
		log.info("@@ OriginalName : " + file.getOriginalFilename());
		log.info("@@ size : " + file.getSize());
		log.info("@@ contentType : " + file.getContentType());
		
		return new ResponseEntity<>(file.getOriginalFilename(), HttpStatus.CREATED);
		
	}
*/
	
	@PostMapping("uploadAjax")
	@ResponseBody
	public ResponseEntity<String> uploadAjax(MultipartFile file) throws IOException {
		log.info("@@ OriginalName : " + file.getOriginalFilename());
		log.info("@@ size : " + file.getSize());
		log.info("@@ contentType : " + file.getContentType());
		
		return new ResponseEntity<>(FileUtil.uploadFile(propertiesConfig.getSampleUploadDir(), 
														file.getOriginalFilename(), 
														file.getBytes()), HttpStatus.CREATED);
		
	}
	
	@ResponseBody
	@RequestMapping(value="displayFile")
	public ResponseEntity<byte[]> displayFile(String fileName) throws IOException {
		InputStream in = null;
		ResponseEntity<byte[]> entity = null;
		
		log.info("@@ FILE NAME : " + fileName);
		
		try {
			String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
			HttpHeaders headers = new HttpHeaders();
			
			in = new FileInputStream(propertiesConfig.getSampleUploadDir() + fileName);
			
			fileName = fileName.substring(fileName.lastIndexOf(".") + 1);
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headers.add("Content-Disposition", "attachment; filename=\"" 
							+ new String(fileName.getBytes("UTF-8"), "ISO-8859-1") + "\""); 
			
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
					
					
	 	} catch (Exception e) {
	 		e.printStackTrace();
	 		entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
	 	} finally {
	 		in.close();
	 	}
		
		return entity;
	}
	
	
	
	
}



















