package com.api.todolist.service;

import java.time.LocalDate;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.api.todolist.enums.TaskStatus;
import com.api.todolist.exception.ResourceNotFoundException;
import com.api.todolist.models.Task;
import com.api.todolist.repository.TaskRepository;

@Service
public class TaskService implements ITaskService {
	final Logger LOG = Logger.getLogger(TaskService.class);
	@Autowired
	private TaskRepository taskRepository;

	@Override
	public Task getById(Long taskId) {
		LOG.info(String.format("Finding task with id=%d", taskId));
		return taskRepository.findById(taskId)
				.orElseThrow(() -> new ResourceNotFoundException(String.format("There is no task with id=%d", taskId)));
	}

	@Override
	public Page<Task> getAll(Integer pageNumber, Integer pageSize) {
		LOG.info("Finding all tasks");
		Pageable pageRequest = PageRequest.of(pageNumber, pageSize);
		return taskRepository.findAll(pageRequest);
	}

	@Override
	public Page<Task> getAllAboutToExpire(Integer pageNumber, Integer pageSize) {
		LOG.info("Finding all tasks");
		Pageable pageRequest = PageRequest.of(pageNumber, pageSize);
		LocalDate currentDate = LocalDate.now();
		LocalDate dueDate = currentDate.plusDays(5);
		return taskRepository.findByDueBetweenAndStatus(currentDate, dueDate, TaskStatus.IN_PROGRESS, pageRequest);
	}

	@Override
	public Page<Task> getAllExpired(Integer pageNumber, Integer pageSize) {
		LOG.info("Finding all tasks");
		Pageable pageRequest = PageRequest.of(pageNumber, pageSize);

		return taskRepository.findByDueLessThanAndStatus(LocalDate.now(), TaskStatus.IN_PROGRESS, pageRequest);
	}

	@Override
	public Task deleteById(Long taskId) {
		LOG.info(String.format("Deleting task with id=%d", taskId));
		Task task = taskRepository.findById(taskId)
				.orElseThrow(() -> new ResourceNotFoundException(String.format("There is no task with id=%d", taskId)));
		taskRepository.delete(task);
		return task;
	}

}
