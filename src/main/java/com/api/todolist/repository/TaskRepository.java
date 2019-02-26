package com.api.todolist.repository;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.api.todolist.enums.TaskStatus;
import com.api.todolist.models.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

	@Query("SELECT task FROM Task task WHERE task.id = :taskId AND task.taskList.id = :taskListId")
	public Task getTaskListByIdThenTaskById(@Param(value = "taskId") Long taskId,
			@Param(value = "taskListId") Long taskListId);

	@Query("SELECT t FROM Task t " + "WHERE UPPER(t.title) LIKE UPPER(CONCAT('%',:title,'%')) "
			+ "AND t.taskList.id = :taskListId ")
	public Page<Task> findTasksByTaskListWithNameFilter(@Param("title") String title,
			@Param("taskListId") Long taskListId, Pageable pageRequest);

	public Page<Task> findByDueBetweenAndStatus(LocalDate current, LocalDate due, TaskStatus taskStatus,
			Pageable pageRequest);

	public Page<Task> findByDueLessThanAndStatus(LocalDate current, TaskStatus taskStatus, Pageable pageRequest);
}
