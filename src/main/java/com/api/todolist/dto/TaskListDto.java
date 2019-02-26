package com.api.todolist.dto;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import com.api.todolist.models.Task;
import com.api.todolist.models.TaskList;

public class TaskListDto implements Serializable {

	private static final long serialVersionUID = 5134424445141940379L;

	private Long id;
	private String name;
	private List<Task> tasks;

	public TaskListDto() {
		super();
	}

	public TaskListDto(Long id, String name, List<Task> tasks) {
		super();
		this.id = id;
		this.name = name;
		this.tasks = tasks;
	}

	public TaskListDto(TaskList taskList) {
		this.id = taskList.getId();
		this.name = taskList.getName();
		this.tasks = taskList.getTasks();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public TaskList fromDto(TaskListDto taskListDto) {

		return new TaskList(taskListDto.getId(), taskListDto.getName(), taskListDto.getTasks());
	}

}
