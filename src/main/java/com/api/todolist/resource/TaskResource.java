package com.api.todolist.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.todolist.models.Task;
import com.api.todolist.service.TaskService;

@RestController
@RequestMapping("/tasks")
public class TaskResource {

	@Autowired
	private TaskService taskService;

	@GetMapping
	public List<Task> getAll(@RequestParam(defaultValue = "0") Integer pageNumber,
			@RequestParam(defaultValue = "5") Integer pageSize) {
		return taskService.getAll(pageNumber, pageSize).getContent();
		
	}

	@GetMapping("/{taskId}")
	public Task getById(@PathVariable(name = "taskId") Long taskId) {
		return taskService.getById(taskId);
	}

	@DeleteMapping("/{taskId}")
	public Task deleteById(@PathVariable(name = "taskId") Long taskId) {
		return taskService.deleteById(taskId);
	}

	@PutMapping("/{taskId}")
	public Task updateById(Long taskId) {
		return taskService.update(taskId);
	}

	@PostMapping
	public Task create(@RequestBody(required = true) Task task) {
		return taskService.create(task);
	}
}
