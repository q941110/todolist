package todoDaoTest;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.todo.TodoApplication;
import kr.or.connect.todo.domain.Todo;
import kr.or.connect.todo.persistence.TodoDao;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=TodoApplication.class)
@Transactional
public class TodoDaoTest {

	@Autowired
	private TodoDao dao;
	
	@Test
	public void shouldInsertAndSelect() {
		String todo = "spring";
		Integer id = dao.insert(todo);
		Todo selected = dao.selectById(id);
		
		System.out.println(selected);
		assertThat(selected.getTodo(), is("spring"));
	}

	@Test
	public void shouldDeleteById() {
		String todo = "spring test";
		Integer id = dao.insert(todo);
		
		int affected = dao.deleteById(id);
		assertThat(affected, is(1));
	}

	@Test
	public void shouldDeleteAll() {
		Todo todo = new Todo();
		String todoThings;
		Integer id, affected;
		
		todoThings = "spring test1";
		id = dao.insert(todoThings);
		todo.setId(id);
		todo.setCompleted(1);
		
		affected = dao.update(todo);
		assertThat(affected, is(1));
		
		
		todoThings = "spring test2";
		id = dao.insert(todoThings);
		todo.setId(id);
		todo.setCompleted(1);
		
		affected = dao.update(todo);
		assertThat(affected, is(1));
		
		
		todoThings = "spring test3";
		id = dao.insert(todoThings);
		todo.setId(id);
		todo.setCompleted(1);
		
		affected = dao.update(todo);
		assertThat(affected, is(1));
		
		
		int count = dao.countCompleted();
		affected = dao.deleteCompleted();
		assertThat(affected, is(count));
	}
	
	@Test
	public void shouldUpdate() {
		String todoThing = "web";
		Integer id = dao.insert(todoThing);
		
		Todo todo = new Todo();
		todo.setId(id);
		todo.setCompleted(1);

		int affected = dao.update(todo);
		assertThat(affected, is(1));
		
		Todo updated = dao.selectById(id);
		assertThat(updated.getCompleted(), is(1));
	}
}
