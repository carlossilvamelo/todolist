package com.api.todolist.models;

import java.time.LocalDate;
import javax.persistence.CascadeType;
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
	private LocalDate created;
	private LocalDate due;
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "tasklist_fk")
	@JsonIgnore
	private TaskList taskList;

	public Task() {
	}

	public Task(Long id, String title, TaskStatus status, TaskPriority priority, LocalDate created, LocalDate due,
			TaskList taskList) {

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

}
