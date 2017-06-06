package kr.or.connect.todo.persistence;

public class TodoSqls {
	static final String SELECT_ALL = 
			"SELECT * FROM TODO ORDER BY completed DESC, id DESC";
	static final String UPDATE_STATE =
			"UPDATE todo SET completed = :completed WHERE id = :id";
	static final String DELETE_BY_ID =
			"DELETE FROM todo WHERE id = :id";
	static final String DELETE_COMPLETED =
			"DELETE FROM todo WHERE completed = 1";
	static final String SELECT_BY_ID = 
			"SELECT * FROM todo WHERE id = :id";
	static final String COUNT_COMPLETED =
			"SELECT COUNT(*) FROM todo WHERE completed = 1";
}
