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
import com.api.todolist.dto.TaskDto;
import com.api.todolist.dto.TaskListDto;
import com.api.todolist.models.Task;
import com.api.todolist.models.TaskList;
import com.api.todolist.service.ITaskListService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/task-lists")
public class TaskListResource {

	@Autowired
	private ITaskListService iTaskListService;

	@ApiOperation(value = "get all task lists")
	@GetMapping
	public List<TaskList> getAll(@RequestParam(defaultValue = "") String name,
			@RequestParam(defaultValue = "0") Integer pageNumber, @RequestParam(defaultValue = "10") Integer pageSize) {
		return iTaskListService.getAllWithNameFilter(name, pageNumber, pageSize).getContent();
	}

	@ApiOperation(value = "get task list by id")
	@GetMapping("/{taskListId}")
	public TaskList getById(@PathVariable(name = "taskListId") Long taskListId) {
		return iTaskListService.getById(taskListId);
	}

	@ApiOperation(value = "get all tasks from a task list")
	@GetMapping("/{taskListId}/tasks")
	public List<Task> getAltasksFromTasklistById(@RequestParam(defaultValue = "") String title,
			@RequestParam(defaultValue = "") String orderBy, @PathVariable(name = "taskListId") Long taskListId,
			@RequestParam(defaultValue = "0") Integer pageNumber, @RequestParam(defaultValue = "10") Integer pageSize) {
		return iTaskListService.getAlltasksFromTasklistById(title, orderBy, taskListId, pageNumber, pageSize)
				.getContent();
	}

	@ApiOperation(value = "Get one task from a task list by id")
	@GetMapping("/{taskListId}/tasks/{taskId}")
	public Task getTaskInsideTaskListByIds(@PathVariable(name = "taskListId") Long taskListId,
			@PathVariable(name = "taskId") Long taskId) {
		return iTaskListService.getTaskInsideTaskListByIds(taskId, taskListId);
	}

	@ApiOperation(value = "Delete task list by id")
	@DeleteMapping("/{taskListId}")
	public TaskList deleteById(@PathVariable(name = "taskListId") Long taskListId) {
		return iTaskListService.deleteById(taskListId);
	}

	@ApiOperation(value = "Delete a task from a task list")
	@DeleteMapping("/{taskListId}/tasks/{taskId}")
	public Task deleteTaskInsideTaskListByIds(@PathVariable(name = "taskListId") Long taskListId,
			@PathVariable(name = "taskId") Long taskId) {

		return iTaskListService.deleteTaskInsideTaskListByIds(taskId, taskListId);
	}

	@ApiOperation(value = "Update task list")
	@PutMapping("/{taskListId}")
	public TaskList updateById(@PathVariable(name = "taskListId") Long taskListId,
			@RequestBody TaskListDto taskListDto) {
		return iTaskListService.update(taskListId, taskListDto.fromDto(taskListDto));
	}

	@ApiOperation(value = "Update task from task list")
	@PutMapping("/{taskListId}/tasks/{taskId}")
	public Task updateTaskFromTaskList(@PathVariable(name = "taskListId") Long taskListId,
			@PathVariable(name = "taskId") Long taskId, @RequestBody TaskDto taskDto) {
		return iTaskListService.updateTaskFromTaskList(taskListId, taskId, taskDto);
	}

	@ApiOperation(value = "Create a task list")
	@PostMapping
	public TaskList create(@RequestBody(required = true) TaskListDto taskListDto) {
		return iTaskListService.create(taskListDto.fromDto(taskListDto));
	}

	@ApiOperation(value = "Create new task to a task list")
	@PostMapping("{taskListId}/tasks")
	public TaskList createTaskToTaskList(@PathVariable(name = "taskListId") Long taskListId,
			@RequestBody(required = true) TaskDto taskDto) {
		return iTaskListService.createTaskToTaskList(taskListId, taskDto);
	}

}
