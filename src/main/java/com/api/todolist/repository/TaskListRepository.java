package com.api.todolist.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.api.todolist.models.TaskList;

@Repository
public interface TaskListRepository extends JpaRepository<TaskList, Long> {

	@Query("SELECT t FROM TaskList t WHERE UPPER(t.name) LIKE UPPER(CONCAT('%',:name,'%'))")
	public Page<TaskList> findAllWithNameFilter(@Param("name") String name, Pageable page);

	@Query("SELECT tl FROM TaskList tl INNER JOIN tl.tasks t WHERE tl.id = :taskListId AND t.id = :taskId")
	public TaskList getTaskFromTaskListByIds(@Param(value = "taskListId") Long taskListId,
			@Param(value = "taskId") Long taskId);

}
