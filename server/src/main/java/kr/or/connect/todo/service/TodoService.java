package kr.or.connect.todo.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import kr.or.connect.todo.domain.Todo;
import kr.or.connect.todo.persistence.TodoDao;

@Service
public class TodoService {
	private TodoDao dao;
	
	public TodoService(TodoDao dao) {
		this.dao = dao;
	}
	
	public void create (String todo) {
		dao.insert(todo);
	}
	
	public Collection<Todo> findAll() {
		return dao.selectAll();
	}
	
	public boolean update(Todo todo) {
		int affected = dao.update(todo);
		return affected == 1;
	}
	
	public boolean delete(Integer id) {
		int affected = dao.deleteById(id);
		return affected == 1;
	}
	
	public boolean deleteCompleted() {
		int affected = dao.deleteCompleted();
		return affected == 1;
	}
}
