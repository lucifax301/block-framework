package com.block.framework.admin.controller;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.block.framework.file.FileItem;

public abstract class AbstractFileController extends AdminController {

	FileItem parse(MultipartFile mfile,String random) throws IOException{
		String originalFileName = mfile.getOriginalFilename();
		String suffix = originalFileName.substring(originalFileName.lastIndexOf("."));
		FileItem fileItem = new FileItem();
		fileItem.setInput(mfile.getInputStream());
		fileItem.setSuffix(suffix);
		if(!"true".equals(random)){
			fileItem.setFileName(originalFileName.substring(0,originalFileName.lastIndexOf(".")));
		}
		return fileItem;
	}
}
