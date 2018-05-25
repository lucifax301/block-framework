package com.block.framework.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.block.framework.common.model.ResultBean;
import com.block.framework.file.FileItem;
import com.block.framework.file.FileService;
import com.block.framework.file.FileServiceFactory;
import com.block.framework.file.UploadResult;

@Controller
@ResponseBody
@RequestMapping(value="/cfile")
public class CDNFileController extends AbstractFileController {

	@Autowired
	private FileServiceFactory fileServiceFactory;
	
	@RequestMapping(value="/uploadImg", method=RequestMethod.POST)
    public ResultBean uploadImg(@RequestParam("file") MultipartFile mfile, @RequestParam(value = "random", required = false) String random)
    throws Exception{
		FileService service = fileServiceFactory.getService("qiniu");
		FileItem fileItem = this.parse(mfile, random);
		UploadResult result = service.updateImg(fileItem);
		return this.<UploadResult>buildResult(result);
	}
	
	@RequestMapping(value="/uploadFile", method=RequestMethod.POST)
    public ResultBean uploadfile(@RequestParam("file") MultipartFile mfile,@RequestParam(value = "random", required = false) String random) throws Exception {
		FileService service = fileServiceFactory.getService("qiniu");
		FileItem fileItem = this.parse(mfile, random);
		UploadResult result = service.updateFile(fileItem);
		return this.<UploadResult>buildResult(result);
	}
}
