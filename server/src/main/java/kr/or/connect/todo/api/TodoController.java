package kr.or.connect.todo.api;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.todo.domain.Todo;
import kr.or.connect.todo.service.TodoService;

@RestController
@RequestMapping("/api")
public class TodoController {
	private final TodoService service;
	private final Logger log = LoggerFactory.getLogger(TodoController.class);

	
	@Autowired
	public TodoController(TodoService service) {
		this.service = service;
	}

	@PostMapping("/newTodoAjax")
	@ResponseStatus(HttpStatus.CREATED)
	void create(@RequestParam String newTodo) {
		service.create(newTodo);
		log.info("todo created : {}" , newTodo);
	}
	
	@GetMapping("/viewAllAjax")
	Collection<Todo> readList() {
		return service.findAll();
	}
	
	@PutMapping("/changeStateAjax")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void update(@RequestBody Todo todo) {
		service.update(todo);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void delete(@PathVariable Integer id) {
		service.delete(id);
	}
	
	@DeleteMapping("/deleteCompletedAjax")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void deleteCompleted() {
		service.deleteCompleted();
	}
}
