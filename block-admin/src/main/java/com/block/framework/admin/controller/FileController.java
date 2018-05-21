package com.block.framework.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.block.framework.common.model.ResultBean;
import com.block.framework.file.FileService;
import com.block.framework.file.FileServiceFactory;

@Controller
@ResponseBody
@RequestMapping(value="/file")
public class FileController extends AdminController {

	@Autowired
	private FileServiceFactory fileServiceFactory;
	
	@RequestMapping(value="/uploadImg", method=RequestMethod.POST)
    public ResultBean uploadImg(@RequestParam("file") MultipartFile mfile, @RequestParam(value = "id", required = false) String id)
    throws Exception{
		FileService service = fileServiceFactory.getService();
		String url = service.updateImg(mfile.getInputStream());
		return this.<String>buildResult(url);
	}
	
	@RequestMapping(value="/uploadFile", method=RequestMethod.POST)
    public ResultBean uploadfile(@RequestParam("file") MultipartFile mfile) throws Exception {
		FileService service = fileServiceFactory.getService();
		String suffix = mfile.getOriginalFilename().substring(mfile.getOriginalFilename().lastIndexOf("."));
		String url = service.updateFile(mfile.getInputStream(), suffix);
		return this.<String>buildResult(url);
	}
}
