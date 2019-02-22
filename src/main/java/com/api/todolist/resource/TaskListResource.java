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

import com.api.todolist.dto.TaskListDto;
import com.api.todolist.models.Task;
import com.api.todolist.models.TaskList;
import com.api.todolist.service.TaskListService;

@RestController
@RequestMapping("/task-lists")
public class TaskListResource {

	@Autowired
	private TaskListService taskListService;

	@GetMapping
	public List<TaskList> getAll(@RequestParam(defaultValue = "0") Integer pageNumber,
			@RequestParam(defaultValue = "5") Integer pageSize) {
		return taskListService.getAll(pageNumber, pageSize).getContent();
	}

	@GetMapping("/{taskListId}")
	public TaskList getById(@PathVariable(name = "taskListId") Long taskListId) {
		
		return taskListService.getById(taskListId);
	}

	@GetMapping("/{taskListId}/tasks")
	public List<Task> getAltasksFromTasklistById(@PathVariable(name = "taskListId") Long taskListId) {

		return taskListService.getAlltasksFromTasklistById(taskListId);
	}

	@GetMapping("/{taskListId}/tasks/{taskId}")
	public Task getTaskListByIdThenTaskById(@PathVariable(name = "taskListId") Long taskListId,
			@PathVariable(name = "taskId") Long taskId) {

		return taskListService.getTaskByIdFromTasklist(taskId, taskListId);
	}

	@DeleteMapping("/{taskListId}")
	public TaskList deleteById(@PathVariable(name = "taskListId") Long taskListId) {
		return taskListService.deleteById(taskListId);
	}

	@PutMapping("/{taskListId}")
	public TaskList updateById(@PathVariable(name = "taskListId") Long taskListId,
			@RequestBody TaskListDto taskListDto) {
		return taskListService.update(taskListId, taskListDto.fromDto(taskListDto));
	}

	@PostMapping
	public TaskList create(@RequestBody(required = true) TaskListDto taskListDto) {
		return taskListService.create(taskListDto.fromDto(taskListDto));
	}

}
