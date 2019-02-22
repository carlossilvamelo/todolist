package com.api.todolist.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.todolist.models.Task;
import com.api.todolist.models.TaskList;
import com.api.todolist.repository.TaskListRepository;
import com.api.todolist.repository.TaskRepository;

@Service
public class TaskListService {

	@Autowired
	private TaskListRepository taskListRepository;

	@Autowired
	private TaskRepository taskRepository;

	public Page<TaskList> getAll(Integer pageNumber, Integer pageSize) {
		Pageable pageRequest = PageRequest.of(pageNumber, pageSize);
		return taskListRepository.findAll(pageRequest);
	}

	public TaskList getById(Long id) {
		return taskListRepository.findById(id).orElseThrow(() -> new RuntimeException(""));
	}
	
	public TaskList deleteById(Long taskListId) {
		TaskList taskList = taskListRepository.findById(taskListId).orElseThrow(()->new RuntimeException());
		taskListRepository.deleteById(taskListId);
		return taskList;
	}
	
	public TaskList update(Long taskListId, TaskList taskList) {
		taskList.setId(taskListId);
		taskList.getTasks().forEach(task->task.setTaskList(taskList));
		return taskListRepository.save(taskList);
	}
	
	public TaskList create(TaskList taskList) {
		TaskList newTaskList = taskListRepository.save(taskList);
		//newTaskList.getTasks().forEach(task->task.setTaskList(newTaskList));
		return taskListRepository.save(newTaskList);
	}


	public List<Task> getAlltasksFromTasklistById(Long taskListId) {
		TaskList taskList = taskListRepository.findById(taskListId)
				.orElseThrow(() -> new RuntimeException("nao existe a tasklist"));
		return Optional.ofNullable(taskList.getTasks()).orElseThrow(() -> new RuntimeException("tasklist n tem tasks"));
	}
	
	public Task getTaskByIdFromTasklist(Long taskId, Long taskListId) {
		return taskRepository.getTaskListByIdThenTaskById(taskId, taskListId);
	}

}
