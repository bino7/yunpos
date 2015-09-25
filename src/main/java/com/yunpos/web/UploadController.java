package com.yunpos.web;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yunpos.utils.Upload;

public class UploadController  extends BaseController {
	
	/**
	 * 上传
	 * @return
	 */
	@RequestMapping(value="/ajax/upload",method = RequestMethod.POST)
	public void upload(HttpServletRequest request, HttpServletResponse response){
		Upload.upload(request, response);
	}
}
