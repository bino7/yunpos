package com.yunpos.web;



import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yunpos.utils.Upload;

@RestController
public class UploadController  extends BaseController {
	
	/**
	 * 上传
	 * @return
	 */
	@RequestMapping(value="/ajax/upload",method = RequestMethod.POST)
	public Map upload(HttpServletRequest request, HttpServletResponse response){
		Map map = Upload.upload(request, response);
		return map;
	}
}
