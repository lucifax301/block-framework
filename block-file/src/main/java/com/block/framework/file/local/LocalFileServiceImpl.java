package com.block.framework.file.local;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;

import com.block.framework.file.FileService;
import com.block.framework.file.UploadResult;

public class LocalFileServiceImpl implements FileService {

	@Autowired
	LocalConfig config;
	
	@Override
	public UploadResult updateFile(InputStream in, String suffix) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UploadResult updateImg(InputStream in,String suffix) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
