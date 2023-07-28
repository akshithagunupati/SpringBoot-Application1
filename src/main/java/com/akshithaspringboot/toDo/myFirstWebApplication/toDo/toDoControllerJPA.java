package com.akshithaspringboot.toDo.myFirstWebApplication.toDo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;


@Controller
@SessionAttributes("name")
public class toDoControllerJPA {
//list of Todos
	

	
	private todoRepository todoRepository;
	
	public toDoControllerJPA( todoRepository todoRepository) {
	super();

	this.todoRepository = todoRepository;
}

	@RequestMapping("list-Todos")
	public String listAllTodos(ModelMap model) {
		String username = getLoggedInUserName(model);
//		todoRepository.ge
		
		
		
		List<toDo> todos = todoRepository.findByUsername(username);
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
		
		todo.setUserName(username);
		todoRepository.save(todo);
		
//		todoService.addTodo(username, todo.getDescription(), 
//				todo.getTargetDate(), todo.isDone());
		return "redirect:list-Todos";
		
	}
	
	@RequestMapping("delete-todo")
	public String deleteToDo(@RequestParam int id) {
//		todoService.deleteByid(id);
		todoRepository.deleteById(id);
		
		return "redirect:list-Todos";

	}
	
	@RequestMapping(value = "update-todo", method= RequestMethod.GET)
	public String showUpdatedTodoPage(@RequestParam int id,ModelMap model) {
			toDo todo = todoRepository.findById(id).get();
			
			
			model.addAttribute("todo",todo);
		return "todo";
	}
	
	@RequestMapping(value="update-todo", method = RequestMethod.POST)
	public String updateToDo(ModelMap model,toDo todo) {
		
		String username = getLoggedInUserName(model);

		todo.setUserName(username);
		todoRepository.save(todo);
		
		return "redirect:list-Todos";
		
	}
	
}
