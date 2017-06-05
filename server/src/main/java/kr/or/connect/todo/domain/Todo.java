package kr.or.connect.todo.domain;

import java.sql.Timestamp;

public class Todo {
	int id;
	String todo;
	int completed;
	Timestamp date;
	
	public int getId() {
		return id;
	}
	public Todo setId(int id) {
		this.id = id;
		return this;
	}
	
	public String getTodo() {
		return todo;
	}
	public Todo setTodo(String todo) {
		this.todo = todo;
		return this;
	}
	
	public int getCompleted() {
		return completed;
	}
	public Todo setCompleted(int completed) {
		this.completed = completed;
		return this;
	}
	public Timestamp getDate() {
		return date;
	}
	public Todo setDate(Timestamp date) {
		this.date = date;
		return this;
	}

	@Override
	public String toString() {
		return "Todo [id=" + id + ", todo=" + todo + ", completed=" + completed + ", date=" + date + "]";
	}
}
