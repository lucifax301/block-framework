package com.block.framework.file.qiniu;

import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.block.framework.file.FileItem;
import com.block.framework.file.FileService;
import com.block.framework.file.UploadResult;
import com.qiniu.api.io.PutRet;
import com.qiniu.storage.model.DefaultPutRet;

//@Service
public class QiniuFileServiceImpl implements FileService {

	@Autowired
	QiniuConfig config;
	
	@Override
	public UploadResult updateFile(FileItem fileItem) throws Exception{
		Long timestamp = new Date().getTime();
		Integer random = new Random().nextInt(1000);
		String fileName = fileItem.getFileName();
		if(fileName==null)
			fileName = timestamp.toString() + random.toString();
		File file = new File(fileName + fileItem.getSuffix());
		FileUtils.copyInputStreamToFile(fileItem.getInput(), file);
		PutRet ret = QiniuPicUtil.uploadFile(file);
		System.out.println(file.getAbsolutePath());
		
		file.delete();
		UploadResult result = new UploadResult();
		result.setUrl(config.getDomain()+ret.getKey());
		//result.setPath(config.getDomain()+ret.getKey());
		return result;
	}

	@Override
	public UploadResult updateImg(FileItem fileItem) throws Exception {
		Long timestamp = new Date().getTime();
		Integer random = new Random().nextInt(1000);
		String fileName = fileItem.getFileName();
		if(fileName==null)
			fileName = timestamp.toString() + random.toString();
		File file = new File(fileName+fileItem.getSuffix());
		FileUtils.copyInputStreamToFile(fileItem.getInput(), file);
		PutRet ret = null;
		if(fileItem.getFileName()==null){
			ret = QiniuPicUtil.uploadFileWithRandomName(file);
		}else{
			ret = QiniuPicUtil.uploadFile(file);
		}
		
		System.out.println(file.getAbsolutePath());
		file.delete();
		UploadResult result = new UploadResult();
		//result.setPath(config.getDomain()+ret.getKey());
		result.setUrl(config.getDomain()+ret.getKey());
		return result;
	}

	@Override
	public UploadResult updateFile(File file) throws Exception {
		PutRet ret = QiniuPicUtil.uploadFile(file);
		System.out.println("ret:"+ret);
		System.out.println(file.getAbsolutePath());
		
		UploadResult result = new UploadResult();
		result.setUrl(config.getDomain()+ret.getKey());
		//result.setPath(config.getDomain()+ret.getKey());
		return result;
	}

	@Override
	public UploadResult updateImg(File file) throws Exception {
		
		DefaultPutRet ret = QiniuPicUtil.uploadFileOverride(file);;
		System.out.println("ret key:"+ret.key+" hash:"+ret.hash);
		System.out.println(file.getAbsolutePath());
		
		UploadResult result = new UploadResult();
		//result.setPath(config.getDomain()+ret.getKey());
		result.setUrl(config.getDomain()+ret.key);
		return result;
	}

}
