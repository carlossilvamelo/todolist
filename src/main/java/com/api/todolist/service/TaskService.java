package com.api.todolist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.todolist.models.Task;
import com.api.todolist.repository.TaskRepository;

@Service
public class TaskService {

	@Autowired
	private TaskRepository taskRepository;

	public Task getById(Long taskId) {
		return taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException());
	}

	public Page<Task> getAll(Integer pageNumber, Integer pageSize) {
		Pageable pageRequest = PageRequest.of(pageNumber, pageSize);
		return taskRepository.findAll(pageRequest);
	}

	public Task deleteById(Long taskId) {
		Task task = taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException());
		taskRepository.delete(task);
		return task;
	}

	public Task update(Long taskId) {
		return null;
	}

	public Task create(Task task) {
		return null;
	}

}
