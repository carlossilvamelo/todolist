package com.api.todolist.dto;

import java.io.Serializable;
import java.time.LocalDate;
import com.api.todolist.enums.TaskPriority;
import com.api.todolist.enums.TaskStatus;
import com.api.todolist.models.Task;
import com.api.todolist.models.TaskList;

public class TaskDto implements Serializable {

	private static final long serialVersionUID = -4245649530008147362L;

	private Long id;
	private String title;
	private TaskStatus status;
	private TaskPriority priority;
	private LocalDate created;
	private LocalDate due;
	private TaskList taskList;

	public TaskDto() {
	}

	public TaskDto(Long id, String title, TaskStatus status, TaskPriority priority, LocalDate created, LocalDate due,
			TaskList taskList) {
		super();
		this.id = id;
		this.title = title;
		this.status = status;
		this.priority = priority;
		this.created = created;
		this.due = due;
		this.taskList = taskList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	public TaskPriority getPriority() {
		return priority;
	}

	public void setPriority(TaskPriority priority) {
		this.priority = priority;
	}

	public TaskList getTaskList() {
		return taskList;
	}

	public void setTaskList(TaskList taskList) {
		this.taskList = taskList;
	}

	public LocalDate getCreated() {
		return created;
	}

	public void setCreated(LocalDate created) {
		this.created = created;
	}

	public LocalDate getDue() {
		return due;
	}

	public void setDue(LocalDate due) {
		this.due = due;
	}

	public Task fromDto(TaskDto taskDto) {
		return new Task(id, taskDto.getTitle(), taskDto.getStatus(), taskDto.getPriority(), created, created, taskList);
	}

}
