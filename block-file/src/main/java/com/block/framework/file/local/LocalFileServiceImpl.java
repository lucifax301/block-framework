package com.block.framework.file.local;

import java.io.File;
import java.util.Date;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.block.framework.file.FileItem;
import com.block.framework.file.FileService;
import com.block.framework.file.UploadResult;
import com.block.framework.file.qiniu.QiniuPicUtil;
import com.qiniu.api.io.PutRet;

public class LocalFileServiceImpl implements FileService {

	@Autowired
	LocalConfig config;
	
	@Override
	public UploadResult updateFile(FileItem fileItem) throws Exception {
		Long timestamp = new Date().getTime();
		Integer random = new Random().nextInt(1000);
		String fileName = fileItem.getFileName();
		if(fileName==null)
			fileName = timestamp.toString() + random.toString();
		String fullName = fileName + fileItem.getSuffix();
		File file = new File(config.getPath()+fullName );
		FileUtils.copyInputStreamToFile(fileItem.getInput(), file);
		
		System.out.println(file.getAbsolutePath());
		
		UploadResult result = new UploadResult();
		result.setUrl(config.getDomain()+fullName);
		result.setPath(config.getPath()+fullName);
		return result;
	}

	@Override
	public UploadResult updateImg(FileItem fileItem) throws Exception {
		Long timestamp = new Date().getTime();
		Integer random = new Random().nextInt(1000);
		String fileName = fileItem.getFileName();
		if(fileName==null)
			fileName = timestamp.toString() + random.toString();
		String fullName = fileName + fileItem.getSuffix();
		File file = new File(config.getPath()+ fullName);
		FileUtils.copyInputStreamToFile(fileItem.getInput(), file);
		
		
		System.out.println(file.getAbsolutePath());
		UploadResult result = new UploadResult();
		result.setPath(config.getPath()+fullName);
		result.setUrl(config.getDomain()+fullName);
		return result;
	}

	@Override
	public UploadResult updateFile(File file) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UploadResult updateImg(File file) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
