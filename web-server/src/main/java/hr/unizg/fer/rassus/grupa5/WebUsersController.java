package hr.unizg.fer.rassus.grupa5;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/users/*")
public class WebUsersController {

	@Autowired
	protected WebUsersServiceImpl usersService;
	
	public WebUsersController(WebUsersServiceImpl usersService) {
		this.usersService = usersService;
	}	
	
	@RequestMapping(path = {"/", "/home"}, method = RequestMethod.GET)
	public String home(Model model){
		List<User> users = usersService.getAll();
		model.addAttribute("formUser", new User());
		model.addAttribute("users", users);
		return "users-view";	
	}
	
	@RequestMapping(path = "/", method = RequestMethod.POST)
	public String saveUser(Model model, @ModelAttribute User user){
		List<User> users = 	usersService.saveUserAndGetAll(user);
		model.addAttribute("formUser", new User());
		model.addAttribute("users", users);
		return "users-view";
		
	}
	
	@RequestMapping(path = "/select/{id}", method = RequestMethod.GET)
	public String selectUser(Model model, @PathVariable long id){
		User user = usersService.findById(id);
		List<User> users = 	usersService.getAll();
		model.addAttribute("formUser", user != null ? user : new User());
		model.addAttribute("users", users);
		return "users-view";
	}
	
	@RequestMapping(path = "/delete/{id}", method = RequestMethod.GET)
	public String deleteUser(Model model, @PathVariable long id){
		List<User> users = 	usersService.deactivateUserByIdAndGetAll(id);
		model.addAttribute("formUser", new User());
		model.addAttribute("users", users);
		return "users-view";	
	}
	
	
	
}
