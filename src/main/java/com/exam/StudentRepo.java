package com.exam;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepo extends CrudRepository<Student, Long> {
	public List<Student> findAll();
	public List<Student> findByName(String name);

	@Query("From Student where name=?1 order by id")
	public List<Student> findByNameSorted(String name);

	@Query("From Student where name=?1 or id>=?2 order by id")
	public List<Student> findByNameSorted(String name, long id);

	public List<Student> findByIdGreaterThan(long id);

}
