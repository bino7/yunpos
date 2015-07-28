package com.yunpos.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunpos.model.User;
import com.yunpos.model.ViewPage;
import com.yunpos.security.SecurityUtils;
import com.yunpos.service.UserService;

@Controller
@RequestMapping("/sys/user")
public class UserController extends BaseController {
	@Autowired
	private UserService userService;
	
	
//	@RequestMapping(method= RequestMethod.GET)
//	public void findById(){
//		User user = userService.findById(1);
//		User user2 = userService.findByUserName("yang");
//		System.out.println(user.getUserName());
//	}
	
	@RequestMapping(method= RequestMethod.GET)
	public @ResponseBody ViewPage<User> list() {
		ViewPage<User> viewPage = new ViewPage<User>();
		List<User> list = userService.findAll();
		viewPage.setPage(0);
		viewPage.setRows(list);
		viewPage.setTotal(list.size());
		viewPage.setRecords(list.size());
		return viewPage;
	}
	
	
	@RequestMapping(value = "/operate", method = { RequestMethod.POST, RequestMethod.GET })
	public void operateSysUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String oper = request.getParameter("oper");
		String id = request.getParameter("id");
		if (oper.equals("del")) {
			String[] ids = id.split(",");
			userService.batchDeleteByIds( (Integer[])ConvertUtils.convert(ids, Integer.class));
		}else {
			Map<String, Object> result = new HashMap<String, Object>();
			String userName = request.getParameter("userName");
			String email = request.getParameter("email");
			User user = null;
			if (oper.equals("edit")) {
				user = userService.findById(Integer.valueOf(id));
			}
			User emailUser = userService.findByEmail(email);
			if (StringUtils.isBlank(userName) || StringUtils.isBlank(email)) {
				response.setStatus(HttpServletResponse.SC_LENGTH_REQUIRED);
				result.put("message", "请填写姓名和邮箱");
				//writeJSON(response, result);
			} else if (null != emailUser && oper.equals("add")) {
				response.setStatus(HttpServletResponse.SC_CONFLICT);
				result.put("message", "此邮箱已存在，请重新输入");
				
				//writeJSON(response, result);
			} else if (null != emailUser && !emailUser.getEmail().equalsIgnoreCase(email) && oper.equals("edit")) {
				response.setStatus(HttpServletResponse.SC_CONFLICT);
				result.put("message", "此邮箱已存在，请重新输入");
				//writeJSON(response, result);
			} else {
				User entity = new User();
				entity.setCreatedAt(new Date());
				entity.setCreatedBy(1);
				entity.setEmail(email);
				entity.setFullname(request.getParameter("fullname"));
				entity.setNickname(request.getParameter("nickname"));
				entity.setPassword(request.getParameter("password"));
				entity.setUserName(userName);
				if (oper.equals("edit")) {
					entity.setId(Integer.valueOf(id));
					userService.update(entity);
				} else if (oper.equals("add")) {
					entity.setSalt(SecurityUtils.generateSalt());
					userService.save(entity);
				}
			}
		}
	}
	
	
//	@RequestMapping(value = "/{id}", method = GET)
//	public
//	@ResponseBody
//	User read(@PathVariable("id") int id) {
//		return userService.findById(id);
//	}
//	
//	
//	@RequestMapping(value = "/{id}", method = PUT)
//	@ResponseStatus(HttpStatus.NO_CONTENT)
//	public void updateUser(@PathVariable("id") int id, @RequestBody User user) {
//	    if (userService.exists(id)) {
//	    	user.setId(id);
//		    userService.save(user);
//	    }
//	}
//	
//	@RequestMapping(method = POST)
//	public ResponseEntity<String> createBook(HttpServletRequest request, @RequestBody User user) {
//		userService.save(user);
//		int id =user.getId();
//		URI uri = new UriTemplate("{requestUrl}/{id}").expand(request.getRequestURL().toString(), id);
//		final HttpHeaders headers = new HttpHeaders();
//		headers.put("Location", singletonList(uri.toASCIIString()));
//		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
//	}
//	
//	
//	
//	@RequestMapping(value = "/{id}", method = DELETE)
//	@ResponseStatus(HttpStatus.NO_CONTENT)
//	public void deleteUser(@PathVariable("id") int id) {
//		userService.delete(id);
//	}
//	
//	
//
//	
//	/**
//	 * 分页查询
//	 * @param page
//	 * @return
//	 */
//	@RequestMapping("/pageList")
//	public ModelAndView pageList(Page page){
//		ModelAndView view = new ModelAndView();
//		PageDate pageDate = this.getPageParam();
//		page.setPageDate(pageDate);
//		List<PageDate> userList = userService.findByPage(page);
//		view.addObject("userList", userList);
//		view.addObject("pageDate", pageDate);
//		return view;
//	}

}
