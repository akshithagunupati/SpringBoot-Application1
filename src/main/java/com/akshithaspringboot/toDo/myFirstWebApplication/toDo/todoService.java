package com.akshithaspringboot.toDo.myFirstWebApplication.toDo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;import java.util.function.Predicate;

import org.springframework.stereotype.Service;
//@Service is where business logic resides.
@Service
public class todoService {
	private static List<toDo> todos = new ArrayList<>();;
	private static int todoCount =1;
	static {
		todos.add(new toDo(todoCount++,"Akshitha","Learn AWS 1",LocalDate.now().plusYears(1),false));
		todos.add(new toDo(todoCount++,"Akshitha","Learn Spring 1",LocalDate.now().plusYears(2),false));
		todos.add(new toDo(todoCount++,"Akshitha","Learn React 1",LocalDate.now().plusYears(3),false));
	}
	
	public List<toDo> findByUsername(String Username){
		Predicate<? super toDo> predicate= todo -> todo.getUserName().equalsIgnoreCase(Username);
		return todos.stream().filter(predicate).toList();
	}
	
	public void addTodo(String name, String description, LocalDate targetDate, boolean done) {
		toDo ToDo = new toDo(todoCount++, name, description, targetDate, done );
		todos.add(ToDo);
	}
	
	public void deleteByid(int id) {
		//define predicate--> if the given todo is matching id, then do that
		Predicate<? super toDo> predicate= todo -> todo.getId()==id;
		todos.removeIf(predicate);
	}

	public toDo findByid(int id) {
		// TODO Auto-generated method stub
		Predicate<? super toDo> predicate= todo -> todo.getId()==id;
		toDo todo = todos.stream().filter(predicate).findFirst().get();
		return todo;
	}

	public void updateToDo(toDo todo) {
		// TODO Auto-generated method stub
		deleteByid(todo.getId());
		todos.add(todo);
		
	}
	
}
