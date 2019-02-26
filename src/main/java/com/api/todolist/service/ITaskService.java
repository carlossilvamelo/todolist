package com.api.todolist.service;

import org.springframework.data.domain.Page;
import com.api.todolist.models.Task;

public interface ITaskService {
	public Task getById(Long taskId);

	/**
	 * Get all tasks.
	 * 
	 * @param orderBy
	 * @param pageNumber
	 * @param pageSize
	 * @return tasks
	 */
	public Page<Task> getAll(Integer pageNumber, Integer pageSize);

	/**
	 * delete task by id.
	 * 
	 * @param taskId
	 * @return
	 */
	public Task deleteById(Long taskId);

	/**
	 * Get every task about to expire.
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @return list of tasks
	 */
	Page<Task> getAllAboutToExpire(Integer pageNumber, Integer pageSize);

	/**
	 * Get every task expired.
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @return list of tasks
	 */
	Page<Task> getAllExpired(Integer pageNumber, Integer pageSize);
}
