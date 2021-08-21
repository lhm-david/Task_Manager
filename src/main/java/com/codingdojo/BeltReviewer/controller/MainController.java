package com.codingdojo.BeltReviewer.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codingdojo.BeltReviewer.models.Event;
import com.codingdojo.BeltReviewer.models.Message;
import com.codingdojo.BeltReviewer.models.User;
import com.codingdojo.BeltReviewer.services.EventService;
import com.codingdojo.BeltReviewer.services.MessageService;
import com.codingdojo.BeltReviewer.services.UserService;
import com.codingdojo.BeltReviewer.validators.UserValidator;

@Controller
public class MainController {
	@Autowired
	private final UserService uService;
	private final UserValidator uValidator;
	@Autowired
	private final EventService eService;
	
	@Autowired
	private final MessageService mService;
	
	public MainController (UserService uService, UserValidator uValidator, EventService eService, MessageService mService) {
		this.uService = uService;
		this.uValidator = uValidator;
		this.eService = eService;
		this.mService = mService;
	}
	
	@RequestMapping("/")
	public String index(@ModelAttribute ("user") User user, HttpSession session) {
		return "index.jsp";
	}
	
	@PostMapping("/newuser")
	public String newuser(@Valid @ModelAttribute ("user") User user, BindingResult result, HttpSession session) {
		uValidator.validate(user, result);
		if(result.hasErrors()) {
			return "index.jsp";
		}
		User newuser = uService.register(user);
		session.setAttribute("userId", newuser.getId());
		return "redirect:/home";
	}
	
	@PostMapping("/login")
	public String login(@RequestParam("email") String email, @RequestParam("password") String password, HttpSession session, RedirectAttributes redirectAttr) {
		if(!this.uService.authenicateUser(email, password)) {
			redirectAttr.addFlashAttribute("error", "Invalid Credentials");
			return "redirect:/";
		}
		User loginuser = this.uService.getByEmail(email);
		session.setAttribute("userId", loginuser.getId());
		return "redirect:/home";
	}
	
	@RequestMapping("/home")
	public String home(HttpSession session, Model viewModel, @ModelAttribute("event")Event event, @ModelAttribute("message")Message message) {
		Long userId = (Long) session.getAttribute("userId");
		User user = uService.getOneUser(userId);
		viewModel.addAttribute("user", user);
		viewModel.addAttribute("events", this.eService.stateEvents(user.getState()));
		viewModel.addAttribute("otherevents", this.eService.otherEvents(user.getState()));
		return "home.jsp";
	}
	
	@RequestMapping("/joinevent/{id}")
	public String joinevent(HttpSession session,@PathVariable("id") Long eventId) {
		Long userId = (Long)session.getAttribute("userId");
		User usertojoin = this.uService.getOneUser(userId);
		Event eventtojoin = this.eService.getOneEvent(eventId);
		this.eService.joinEvent(eventtojoin, usertojoin);
		return "redirect:/home";
	}
	
	@RequestMapping("/canceljoining/{id}")
	public String canceljoinevent(HttpSession session,@PathVariable("id") Long eventId) {
		Long userId = (Long)session.getAttribute("userId");
		User usertojoin = this.uService.getOneUser(userId);
		Event eventtojoin = this.eService.getOneEvent(eventId);
		this.eService.canceljoinEvent(eventtojoin, usertojoin);
		return "redirect:/home";
	}
	
	@PostMapping("/newevent")
	public String addevent(@Valid @ModelAttribute("event")Event event, BindingResult result, HttpSession session, Model viewModel, @ModelAttribute("message")Message message) {
		if (result.hasErrors()) {
			Long userId = (Long) session.getAttribute("userId");
			User user = uService.getOneUser(userId);
			viewModel.addAttribute("user", user);
			viewModel.addAttribute("events", this.eService.stateEvents(user.getState()));
			viewModel.addAttribute("otherevents", this.eService.otherEvents(user.getState()));
			return "home.jsp";
		}
		this.eService.createEvent(event);
		return "redirect:/home";
	}
	
	@RequestMapping("/event/{id}")
	public String showevent(Model viewModel, @PathVariable("id")Long id, HttpSession session, @ModelAttribute("message")Message message) {
		Long userId = (Long) session.getAttribute("userId");
		User thisuser = uService.getOneUser(userId);
		Event thisevent = this.eService.getOneEvent(id);
		
		viewModel.addAttribute("user", thisuser);
		viewModel.addAttribute("event", thisevent);
		
		return "event.jsp";
	}
	
	@RequestMapping("/edit/{id}")
	public String editEvent(@PathVariable("id")Long id, Model viewModel, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		User thisUser = this.uService.getOneUser(userId);
		viewModel.addAttribute("event", this.eService.getOneEvent(id));
		viewModel.addAttribute("user", thisUser);
		return "edit.jsp";
	}
	
	@PostMapping("/update/{id}")
	public String updateEvent(@Valid @ModelAttribute("event")Event event, BindingResult result, @PathVariable("id") Long id, Model viewModel, HttpSession session) {
		if (result.hasErrors()) {
			Long userId = (Long) session.getAttribute("userId");
			User thisUser = this.uService.getOneUser(userId);
//			viewModel.addAttribute("event", this.eService.getOneEvent(id));
			viewModel.addAttribute("user", thisUser);
			return "edit.jsp";
		}
		this.eService.updateEvent(event);
		return "redirect:/event/"+id;
	}
	
	@PostMapping("/event/{id}/newcomment")
	public String newcomment(@Valid @ModelAttribute("event")Event event,@PathVariable("id")Long id, Model viewModel, BindingResult result, @ModelAttribute("message")Message message) {
		if (result.hasErrors()) {
			
			return "edit.jsp";
		}
		
		this.mService.createM(message);
		return "redirect:/event/"+id;
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteEvent(@PathVariable("id")Long id) {
		this.eService.deleteEvent(id);
		return "redirect:/home";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}

