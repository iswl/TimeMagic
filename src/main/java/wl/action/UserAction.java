package wl.action;

import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import wl.entity.User;
import wl.service.UserService;

@Controller
@Scope("prototype")
public class UserAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource
	private UserService userService;
	private User user;
	private String id;

	public String add() {
		return "add";
	}

	public String save() {
		Calendar calendar = Calendar.getInstance();
		user.setCreateTime(calendar.getTime());
		//TODO以后通过时间控件赋值
		user.setBirthday(calendar.getTime());
		userService.save(user);
		return "toList";
	}

	public String edit() {
		user = userService.getById(id);
		return "edit";
	}

	public String update() {
		return "toList";
	}

	public String delete() {
		userService.delete(id);
		return "toList";
	}

	public String list() {
		List<User> list = userService.findAll();
//		ActionContext.getContext().getValueStack().push(list);
		ServletActionContext.getRequest().setAttribute("list", list);
		return "list";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
