package com.yunpos.web;

import static java.util.Collections.singletonList;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.util.UriTemplate;

import com.google.common.base.Strings;
import com.yunpos.model.SysButton;
import com.yunpos.model.SysButtonWithBLOBs;
import com.yunpos.model.ViewPage;
import com.yunpos.service.SysButtonService;
import com.yunpos.utils.PageDate;

/**
 * 
 * 功能描述：按钮资源控制器
 * <p>
 * 版权所有：小牛信息科技有限公司
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * 
 * @author Devin_Yang 新增日期：2015年7月17日
 * @author Devin_Yang 修改日期：2015年7月17日
 *
 */
@Controller
@RequestMapping("/res/button")
public class SysButtonController extends BaseController{
	@Autowired
	private SysButtonService sysButtonService;
	
	
	@RequestMapping(method = GET)
	public @ResponseBody ViewPage<SysButtonWithBLOBs> list() {
		ViewPage<SysButtonWithBLOBs> viewPage = new ViewPage<SysButtonWithBLOBs>();
		List<SysButtonWithBLOBs> list = sysButtonService.findAll();
		viewPage.setPage(0);
		viewPage.setRows(list);
		viewPage.setRecords(list.size());
		viewPage.setTotal(list.size());
		return viewPage;
	}
	
	@RequestMapping(value = "/operate", method = { RequestMethod.POST, RequestMethod.GET })
	public void operate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PageDate pageParam = this.getPageParam();
		String oper = pageParam.getString("oper");
		String id = pageParam.getString("id");
		if (oper.equals("del") && !Strings.isNullOrEmpty(id)) {
			String[] ids = id.split(",");
			sysButtonService.batchDeleteByIds( (Integer[])ConvertUtils.convert(ids, Integer.class));
		}else {
			SysButtonWithBLOBs sysButton = null;
			if (oper.equals("edit")) {
				sysButton = sysButtonService.findById(Integer.valueOf(id));
			}
			SysButtonWithBLOBs entity = new SysButtonWithBLOBs();
			
			entity.setBtnicon(pageParam.getString("btnicon"));
			entity.setBtnname(pageParam.getString("btnname"));
			if(!Strings.isNullOrEmpty(pageParam.getString("btnno"))){
				entity.setBtnno(Integer.valueOf(pageParam.getString("btnno")));
			}
			entity.setInitstatus(1);
			if(!Strings.isNullOrEmpty(pageParam.getString("menuno"))){
				entity.setMenuno(Integer.valueOf(pageParam.getString("menuno")));
			}
			if(!Strings.isNullOrEmpty(pageParam.getString("seqno"))){
				entity.setSeqno(Integer.valueOf(pageParam.getString("seqno")));
			}
			entity.setBtnclass(pageParam.getString("btnclass"));
			entity.setBtnscript(pageParam.getString("btnscript"));
			if (oper.equals("edit")) {
				entity.setBtnid(Integer.valueOf(sysButton.getBtnid()));
				sysButtonService.update(entity);
			} else if (oper.equals("add")) {
				sysButtonService.save(entity);
			}
		}
	}
	
	
	@RequestMapping(value = "/{id}", method = GET)
	public @ResponseBody  SysButton read(@PathVariable("id") int id) {
		return sysButtonService.findById(id);
	}


	@RequestMapping(value = "/{id}", method = PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateSysButton(@PathVariable("id") int id, @RequestBody SysButtonWithBLOBs sysButton) {
	    if (sysButtonService.exists(id)) {
	    	sysButton.setBtnid(id);
	    	sysButtonService.update(sysButton);
	    }
	}

	@RequestMapping(method = POST)
	public ResponseEntity<String> createSysButton(HttpServletRequest request, @RequestBody SysButtonWithBLOBs sysButton) {
		sysButtonService.insert(sysButton);
		final int id = sysButton.getBtnid();
		URI uri = new UriTemplate("{requestUrl}/{id}").expand(request.getRequestURL().toString(), id);
		final HttpHeaders headers = new HttpHeaders();
		headers.put("Location", singletonList(uri.toASCIIString()));
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}


	@RequestMapping(value = "/{id}", method = DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteSysButton(@PathVariable("id") int id) {
		sysButtonService.delete(id);
	}
	

}
