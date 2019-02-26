package com.api.todolist.service;

import org.springframework.data.domain.Page;
import com.api.todolist.dto.TaskDto;
import com.api.todolist.models.Task;
import com.api.todolist.models.TaskList;

public interface ITaskListService {

	/**
	 * Get list of task lists with pagination and filter by name of task list.
	 * 
	 * @param name
	 * @param pageNumber
	 * @param pageSize
	 * @return List of task list
	 */
	public Page<TaskList> getAllWithNameFilter(String name, Integer pageNumber, Integer pageSize);

	/**
	 * Find task list by ID.
	 * 
	 * @param id
	 * @return taskList
	 */
	public TaskList getById(Long id);

	/**
	 * Remove taskList by ID.
	 * 
	 * @param taskListId
	 * @return
	 */
	public TaskList deleteById(Long taskListId);

	/**
	 * Update task list by ID.
	 * 
	 * @param taskListId
	 * @param taskList
	 * @return TaskList
	 */
	public TaskList update(Long taskListId, TaskList taskList);

	/**
	 * Create a new Task list.
	 * 
	 * @param taskList
	 * @return taskList
	 */
	public TaskList create(TaskList taskList);

	/**
	 * Create a new task to a task list.
	 * 
	 * @param taskListId
	 * @param taskDto
	 * @return TaskList
	 */
	public TaskList createTaskToTaskList(Long taskListId, TaskDto taskDto);

	/**
	 * Get all tasks from a task list, filter by title of task and order by
	 * priority.
	 * 
	 * @param title
	 * @param orderBy
	 * @param taskListId
	 * @param pageNumber
	 * @param pageSize
	 * @return list of tasks
	 */
	public Page<Task> getAlltasksFromTasklistById(String title, String orderBy, Long taskListId, Integer pageNumber,
			Integer pageSize);

	/**
	 * Find a task from a task list.
	 * 
	 * @param taskId
	 * @param taskListId
	 * @return Task
	 */
	public Task getTaskInsideTaskListByIds(Long taskId, Long taskListId);

	/**
	 * Remove a task from a task list.
	 * 
	 * @param taskId
	 * @param taskListId
	 * @return Task
	 */
	public Task deleteTaskInsideTaskListByIds(Long taskId, Long taskListId);

	/**
	 * Update task from a task list.
	 * 
	 * @param taskListId
	 * @param taskId
	 * @param taskDto
	 * @return Task
	 */
	public Task updateTaskFromTaskList(Long taskListId, Long taskId, TaskDto taskDto);

}
