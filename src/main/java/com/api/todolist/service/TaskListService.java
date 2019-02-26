package com.api.todolist.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.api.todolist.dto.TaskDto;
import com.api.todolist.exception.ResourceNotFoundException;
import com.api.todolist.models.Task;
import com.api.todolist.models.TaskList;
import com.api.todolist.repository.TaskListRepository;
import com.api.todolist.repository.TaskRepository;

@Service
public class TaskListService implements ITaskListService {

	final Logger LOG = Logger.getLogger(TaskListService.class);

	@Autowired
	private TaskListRepository taskListRepository;

	@Autowired
	private TaskRepository taskRepository;

	@Override
	public Page<TaskList> getAllWithNameFilter(String name, Integer pageNumber, Integer pageSize) {
		Pageable pageRequest = PageRequest.of(pageNumber, pageSize);
		return taskListRepository.findAllWithNameFilter(name, pageRequest);
	}

	@Override
	public TaskList getById(Long id) {
		LOG.info(String.format("Finding Task List with id=%d", id));
		return taskListRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(String.format("There is no tasklist with id=%d", id)));
	}

	@Override
	public TaskList deleteById(Long taskListId) {
		LOG.info(String.format("Deleting Task List with id=%d", taskListId));
		TaskList taskList = taskListRepository.findById(taskListId).orElseThrow(
				() -> new ResourceNotFoundException(String.format("There is no tasklist with id=%d", taskListId)));
		taskListRepository.deleteById(taskListId);
		return taskList;
	}

	@Override
	public TaskList update(Long taskListId, TaskList taskList) {
		LOG.info(String.format("Updating Task List with id=%d", taskListId));
		taskList.setId(taskListId);
		taskList.getTasks().forEach(task -> task.setTaskList(taskList));
		return taskListRepository.save(taskList);
	}

	@Override
	public Task updateTaskFromTaskList(Long taskListId, Long taskId, TaskDto taskDto) {
		LOG.info(String.format("Deleting tasks with id=%d from Task List with id=%d", taskId, taskListId));

		TaskList taskList = Optional.of(taskListRepository.getTaskFromTaskListByIds(taskListId, taskId)).orElseThrow(
				() -> new ResourceNotFoundException(String.format("There is no task-list with id=%d", taskListId)));
		taskDto.setId(taskId);
		taskDto.setTaskList(taskList);
		return taskRepository.save(taskDto.fromDto(taskDto));
	}

	@Override
	public TaskList create(TaskList taskList) {
		LOG.info(String.format("Creating Task List with id=%d", taskList.getId()));
		TaskList newTaskList = taskListRepository.save(taskList);
		newTaskList.getTasks().forEach(task -> {
			LocalDate currentDate = LocalDate.now();
			LocalDate dueDate = currentDate.plusMonths(1);
			task.setCreated(currentDate);
			task.setDue(dueDate);
			task.setTaskList(newTaskList);
		});
		return taskListRepository.save(newTaskList);
	}

	@Override
	public TaskList createTaskToTaskList(Long taskListId, TaskDto taskDto) {
		TaskList taskList = taskListRepository.findById(taskListId).orElseThrow(
				() -> new ResourceNotFoundException(String.format("There is no tasklist with id=%d", taskListId)));
		Task task = taskDto.fromDto(taskDto);
		LOG.info(String.format("Creating Task into a task List with id=%d", taskList.getId()));
		if (taskList.getTasks() != null)
			Collections.addAll(taskList.getTasks(), task);
		else
			taskList.setTasks(Arrays.asList(task));

		return taskListRepository.save(taskList);
	}

	@Override
	public Page<Task> getAlltasksFromTasklistById(String title, String orderBy, Long taskListId, Integer pageNumber,
			Integer pageSize) {
		List<String> orderParamList = Arrays.asList("PRIORITY");
		Pageable pageRequest = PageRequest.of(pageNumber, pageSize);
		LOG.info(String.format("Finding tasks from Task List with id=%d", taskListId));
		List<Task> tasks = new ArrayList<Task>(
				taskRepository.findTasksByTaskListWithNameFilter(title, taskListId, pageRequest).getContent());

		if (orderParamList.contains(orderBy.toUpperCase())) {
			Collections.sort(tasks,
					(t1, t2) -> Integer.compare(t2.getPriority().ordinal(), t1.getPriority().ordinal()));
		}

		return new PageImpl<>(tasks);
	}

	@Override
	public Task getTaskInsideTaskListByIds(Long taskId, Long taskListId) {
		LOG.info(String.format("Finding tasks with id=%d from Task List with id=%d", taskId, taskListId));
		TaskList taskList = taskListRepository.findById(taskListId).orElseThrow(
				() -> new ResourceNotFoundException(String.format("There is no task-list with id=%d", taskListId)));

		return taskList.getTasks().stream().filter(t -> t.getId().equals(taskId)).findFirst()
				.orElseThrow(() -> new ResourceNotFoundException(String.format("There is no task with id=%d", taskId)));
	}

	@Override
	public Task deleteTaskInsideTaskListByIds(Long taskId, Long taskListId) {
		LOG.info(String.format("Deleting tasks with id=%d from Task List with id=%d", taskId, taskListId));
		TaskList taskList = taskListRepository.findById(taskListId).orElseThrow(
				() -> new ResourceNotFoundException(String.format("There is no task-list with id=%d", taskListId)));
		Task task = taskList.getTasks().stream().filter(t -> t.getId().equals(taskId)).findFirst()
				.orElseThrow(() -> new ResourceNotFoundException(String.format("There is no task with id=%d", taskId)));

		taskList.getTasks().remove(task);
		taskRepository.delete(task);
		taskListRepository.save(taskList);
		return task;
	}

}
