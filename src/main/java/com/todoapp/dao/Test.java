package com.todoapp.dao;

import java.util.Date;

import com.todoapp.model.Todo;

public class Test {

	public static void main(String[] args) {
		
		TodoDao mydao = new TodoDao();
		
		Todo todo1 = new Todo("Project 123 - My Task 3","Pending",new Date(),new Date(),1);
		mydao.addTodo(todo1);
		
		
		Todo todo2 = new Todo("Project 123 - My Task 3","Pending",new Date(),new Date(),2);
		mydao.addTodo(todo2);
		Todo todo3 = new Todo("Project 123 - My Task 3","Pending",new Date(),new Date(),2);
		mydao.addTodo(todo3);
		
		Todo todo4 = new Todo("Project 123 - My Task 3","Pending",new Date(),new Date(),3);
		mydao.addTodo(todo4);
		Todo todo5 = new Todo("Project 123 - My Task 3","Pending",new Date(),new Date(),3);
		mydao.addTodo(todo5);
		Todo todo6 = new Todo("Project 123 - My Task 3","Pending",new Date(),new Date(),3);
		mydao.addTodo(todo6);
		
	    Todo todo7 = new Todo("MyBookStore - My Task 4","Pending",new Date(),new Date(),4);
	    mydao.addTodo(todo7);
	    Todo todo8 = new Todo("Project 123 - My Task 3","Pending",new Date(),new Date(),4);
		mydao.addTodo(todo8);
		Todo todo9 = new Todo("Project 123 - My Task 3","Pending",new Date(),new Date(),4);
		mydao.addTodo(todo9);
		Todo todo10 = new Todo("Project 123 - My Task 3","Pending",new Date(),new Date(),4);
		mydao.addTodo(todo10);

	}

}
