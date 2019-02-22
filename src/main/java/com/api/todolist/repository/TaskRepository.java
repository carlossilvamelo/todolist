package com.api.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.todolist.models.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{

	@Query("SELECT task FROM Task task WHERE task.id = :taskId AND task.taskList.id = :taskListId")
	public Task getTaskListByIdThenTaskById(@Param(value="taskId") Long taskId,@Param(value="taskListId") Long taskListId);

}
