package com.api.todolist.resource;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.api.todolist.models.Task;
import com.api.todolist.service.TaskService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/tasks")
public class TaskResource {

	@Autowired
	private TaskService taskService;

	@ApiOperation(value = "Get all tasks")
	@GetMapping
	public List<Task> getAll(@RequestParam(defaultValue = "0") Integer pageNumber,
			@RequestParam(defaultValue = "10") Integer pageSize) {
		return taskService.getAll(pageNumber, pageSize).getContent();
	}

	@ApiOperation(value = "Get task by id")
	@GetMapping("/{taskId}")
	public Task getById(@PathVariable(name = "taskId") Long taskId) {
		return taskService.getById(taskId);
	}

	@ApiOperation(value = "Get all tasks about to expire")
	@GetMapping("/about-to-expire")
	public List<Task> getAllAboutToExpire(@RequestParam(defaultValue = "0") Integer pageNumber,
			@RequestParam(defaultValue = "10") Integer pageSize) {
		return taskService.getAllAboutToExpire(pageNumber, pageSize).getContent();

	}

	@ApiOperation(value = "Get all expired task")
	@GetMapping("/expired")
	public List<Task> getAllExpired(@RequestParam(defaultValue = "0") Integer pageNumber,
			@RequestParam(defaultValue = "10") Integer pageSize) {
		return taskService.getAllExpired(pageNumber, pageSize).getContent();

	}

}
