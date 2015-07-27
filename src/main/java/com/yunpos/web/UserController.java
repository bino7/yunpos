package com.yunpos.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yunpos.model.Page;
import com.yunpos.model.User;
import com.yunpos.model.ViewPage;
import com.yunpos.service.UserService;
import com.yunpos.utils.PageDate;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(method= RequestMethod.GET)
	public void findById(){
		User user = userService.findById(1);
		User user2 = userService.findByUserName("yang");
		System.out.println(user.getUserName());
	}
	
	@RequestMapping(value="/list")
	public @ResponseBody ViewPage<User> list() {
		ViewPage<User> viewPage = new ViewPage<User>();
		List<User> list = userService.findAll();
		viewPage.setPage(0);
		viewPage.setRows(list);
		viewPage.setTotal(list.size());
		viewPage.setRecords(list.size());
		return viewPage;
	}

	
	/**
	 * 分页查询
	 * @param page
	 * @return
	 */
	@RequestMapping("/pageList")
	public ModelAndView pageList(Page page){
		ModelAndView view = new ModelAndView();
		PageDate pageDate = this.getPageParam();
		page.setPageDate(pageDate);
		List<PageDate> userList = userService.findByPage(page);
		view.addObject("userList", userList);
		view.addObject("pageDate", pageDate);
		return view;
	}

}
