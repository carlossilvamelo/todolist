package com.api.todolist.models;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "tasklist")
public class TaskList {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tasklist_id")
	private Long id;
	@Column(name = "name")
	private String name;
	@OneToMany(mappedBy = "taskList", cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
	List<Task> tasks;

	public TaskList() {

	}

	public TaskList(Long id, String name, List<Task> tasks) {
		this.id = id;
		this.name = name;
		this.tasks = tasks;
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

}
