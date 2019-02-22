package com.api.todolist.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.api.todolist.enums.TaskPriority;
import com.api.todolist.enums.TaskStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "task")
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "task_id")
	private Long id;
	@Column(name = "title")
	private String title;
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "status")
	private TaskStatus status;
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "priority")
	private TaskPriority priority;
	@ManyToOne
	@JoinColumn(name = "tasklist_fk")
	@JsonIgnore
	private TaskList taskList;

	public Task() {
	}

	public Task(String title, TaskStatus status, TaskPriority priority) {
		this.title = title;
		this.status = status;
		this.priority = priority;
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

}
