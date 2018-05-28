package com.block.framework.file;

import java.io.File;

public interface FileService {

	/**
	 * 
	 * @param in 上传文件输入流
	 * @return 文件路径
	 */
	UploadResult updateFile(FileItem fileItem) throws Exception;
	
	UploadResult updateImg(FileItem fileItem) throws Exception;
	
	UploadResult updateFile(File file) throws Exception;
	
	UploadResult updateImg(File file) throws Exception;
}
