package com.block.framework.file.qiniu;

import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.block.framework.file.FileService;
import com.block.framework.file.UploadResult;
import com.qiniu.api.io.PutRet;

//@Service
public class QiniuFileServiceImpl implements FileService {

	@Autowired
	QiniuConfig config;
	
	@Override
	public UploadResult updateFile(InputStream in,String suffix) throws Exception{
		Long timestamp = new Date().getTime();
		Integer random = new Random().nextInt(1000);
		
		File file = new File(timestamp.toString() + random.toString() + suffix);
		FileUtils.copyInputStreamToFile(in, file);
		PutRet ret = QiniuPicUtil.uploadFile(file);
		System.out.println(file.getAbsolutePath());
		
		file.delete();
		UploadResult result = new UploadResult();
		result.setPath(config.getDomain()+ret.getKey());
		return result;
	}

	@Override
	public UploadResult updateImg(InputStream in,String suffix) throws Exception {
		Long timestamp = new Date().getTime();
		Integer random = new Random().nextInt(1000);
		File file = new File(timestamp.toString() + random.toString());
		FileUtils.copyInputStreamToFile(in, file);
		PutRet ret = QiniuPicUtil.uploadFileWithRandomName(file);
		System.out.println(file.getAbsolutePath());
		file.delete();
		UploadResult result = new UploadResult();
		result.setPath(config.getDomain()+ret.getKey());
		return result;
	}

}
