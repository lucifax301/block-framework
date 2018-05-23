package com.block.framework.file;

import java.io.InputStream;

public interface FileService {

	/**
	 * 
	 * @param in 上传文件输入流
	 * @return 文件路径
	 */
	UploadResult updateFile(InputStream in,String suffix) throws Exception;
	
	UploadResult updateImg(InputStream in,String suffix) throws Exception;
}
