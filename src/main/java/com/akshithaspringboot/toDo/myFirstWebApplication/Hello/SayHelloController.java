package com.akshithaspringboot.toDo.myFirstWebApplication.Hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SayHelloController {
	//"create an URL -> TO say "Hello"
	//steps
	
	@RequestMapping("say-Hello")
	@ResponseBody
	public String sayHello() {
		return "Hello! What do you want to learn Today";
	}
	//basic html display
	@RequestMapping("say-Hello-HTML")
	@ResponseBody
	public StringBuffer sayHelloHtml() {
		StringBuffer sb = new StringBuffer();
		sb.append("<html>");
		sb.append("<head>");
		sb.append("<title> First </title>");
		sb.append("</head>");
		sb.append("<body>");
		sb.append("Hello this is my first");
		sb.append("</body>");
		sb.append("</html>");
		return sb;
	}
	
	
	//JSP-> View technlogy
	//Create a jsp file
	//go to url "say-hello-jsp" -> redirect to sayhello.jsp file
	
	@RequestMapping("sayHelloJSP")
	public String sayHelloJSP() {
		return "sayHello";
	}
	
	
}
