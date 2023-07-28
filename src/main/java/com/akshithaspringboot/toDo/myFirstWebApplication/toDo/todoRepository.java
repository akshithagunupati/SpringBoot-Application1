package com.akshithaspringboot.toDo.myFirstWebApplication.toDo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface todoRepository extends JpaRepository<toDo, Integer>{
	public List<toDo> findByUsername(String username);
}
