package com.block.framework.file;

import java.io.IOException;
import java.io.InputStream;

public interface FileService {

	/**
	 * 
	 * @param in 上传文件输入流
	 * @return 文件路径
	 */
	String updateFile(InputStream in,String suffix) throws Exception;
	
	String updateImg(InputStream in) throws Exception;
}
