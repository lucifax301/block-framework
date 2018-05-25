package com.block.framework.file.local;

import org.springframework.beans.factory.annotation.Autowired;

import com.block.framework.file.FileItem;
import com.block.framework.file.FileService;
import com.block.framework.file.UploadResult;

public class LocalFileServiceImpl implements FileService {

	@Autowired
	LocalConfig config;
	
	@Override
	public UploadResult updateFile(FileItem fileItem) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UploadResult updateImg(FileItem fileItem) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
