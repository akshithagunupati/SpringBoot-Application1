package com.akshithaspringboot.toDo.myFirstWebApplication.toDo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;



//@Controller
@SessionAttributes("name")
public class toDoController {
//list of Todos
	
	private todoService todoService;
	
	public toDoController(com.akshithaspringboot.toDo.myFirstWebApplication.toDo.todoService todoService) {
	super();
	this.todoService = todoService;
}

	@RequestMapping("list-Todos")
	public String listAllTodos(ModelMap model) {
		String username = getLoggedInUserName(model);
		List<toDo> todos = todoService.findByUsername(username);
		model.addAttribute("todos",todos);
		return "listTodos";
	}

	private String getLoggedInUserName(ModelMap model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}
	

	@RequestMapping(value = "add-todo", method= RequestMethod.GET)
	public String showNewToDoPage(ModelMap model) {
		String userName = getLoggedInUserName(model);
		toDo todo = new toDo(0,userName,"",LocalDate.now().plusYears(1),false);
		model.put("todo", todo);
		return "todo";
	}
	
//	@RequestMapping(value = "add-todo", method= RequestMethod.POST)
//	public String addNewToDoPage( ModelMap model, @Valid toDo todo, BindingResult result) {
//		
//		if(result.hasErrors()) {
//			return "todo"; 
//		}
//		todoService.addTodo((String)model.get("name"), todo.getDescription(), LocalDate.now().plusYears(1),false );
//		return "redirect:list-Todos";
//	}
	
	@RequestMapping(value="add-todo", method = RequestMethod.POST)
	public String addNewTodo(ModelMap model,toDo todo) {
		
		
		String username = getLoggedInUserName(model);
		todoService.addTodo(username, todo.getDescription(), 
				todo.getTargetDate(), false);
		return "redirect:list-Todos";
		
	}
	
	@RequestMapping("delete-todo")
	public String deleteToDo(@RequestParam int id) {
		todoService.deleteByid(id);
		return "redirect:list-Todos";

	}
	
	@RequestMapping(value = "update-todo", method= RequestMethod.GET)
	public String showUpdatedTodoPage(@RequestParam int id,ModelMap model) {
			toDo todo = todoService.findByid(id);
			model.addAttribute("todo",todo);
		return "todo";
	}
	
	@RequestMapping(value="update-todo", method = RequestMethod.POST)
	public String updateToDo(ModelMap model,toDo todo) {
		
		String username = getLoggedInUserName(model);
//		LocalDate date = (LocalDate)model.get("targetDate");
		todo.setUserName(username);
//		todo.setTargetDate(date);
		todoService.updateToDo(todo);
		
		return "redirect:list-Todos";
		
	}
	
}
