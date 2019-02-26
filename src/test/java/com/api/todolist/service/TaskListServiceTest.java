package com.api.todolist.service;

import static org.assertj.core.api.Assertions.fail;
import java.time.LocalDate;
import java.util.Arrays;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;
import com.api.todolist.dto.TaskDto;
import com.api.todolist.enums.TaskPriority;
import com.api.todolist.enums.TaskStatus;
import com.api.todolist.exception.ResourceNotFoundException;
import com.api.todolist.models.Task;
import com.api.todolist.models.TaskList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskListServiceTest {

	@Autowired
	private ITaskListService iTaskListService;

	public void getAllWithNameFilterTest() {
		String titleTest = "my";
		Page<TaskList> taskLists = iTaskListService.getAllWithNameFilter(titleTest, 0, 5);
		Assertions.assertThat(taskLists).isNotNull();
		taskLists.forEach(tl -> Assertions.assertThat(tl.getName()).contains(titleTest));
	}

	@Test
	public void getByIdTest() {
		Long id = 1L;
		TaskList taskList = iTaskListService.getById(id);
		Assertions.assertThat(taskList).isNotNull();
		Assertions.assertThat(taskList.getId()).isEqualTo(1L);
	}

	@Test
	public void deleteByIdTest() {
		Long id = 2L;

		iTaskListService.deleteById(id);

		try {
			TaskList taskList = iTaskListService.getById(id);
			fail("expected exception was not occured.");
		} catch (ResourceNotFoundException e) {

		}

	}

	@Test
	public void updateTest() {
		Long id = 1L;
		String newName = "UPDATED";
		TaskList taskList = iTaskListService.getById(id);
		taskList.setName(newName);
		iTaskListService.update(id, taskList);
		taskList = iTaskListService.getById(id);
		Assertions.assertThat(taskList.getName()).isEqualTo(newName);
	}

	@Test
	public void createTest() {
		Task task = new Task(null, "new task", TaskStatus.IN_PROGRESS, TaskPriority.HIGH, LocalDate.now(),
				LocalDate.now(), null);
		TaskList taskList = new TaskList(null, "new taskList", Arrays.asList(task));
		taskList = iTaskListService.create(taskList);
		taskList = iTaskListService.getById(taskList.getId());
		Assertions.assertThat(taskList).isNotNull();
		Assertions.assertThat(taskList.getTasks()).isNotNull();
		Assertions.assertThat(taskList.getTasks().size()).isEqualTo(1);
		Assertions.assertThat(taskList.getTasks().get(0).getTitle()).isEqualTo("new task");

	}

	@Test
	public void createTaskToTaskListTest() {
		Long taskListId = 1L;
		TaskList taskList = null;
		boolean TaskWasCreated = false;

		taskList = iTaskListService.getById(taskListId);
		TaskDto taskDto = new TaskDto(null, "new task", TaskStatus.IN_PROGRESS, TaskPriority.HIGH, LocalDate.now(),
				LocalDate.now(), taskList);
		taskList = iTaskListService.createTaskToTaskList(taskListId, taskDto);

		Assertions.assertThat(taskList).isNotNull();
		Assertions.assertThat(taskList.getTasks()).isNotNull();

		for (Task task : taskList.getTasks()) {
			if (task.getTitle().equals("new task") && task.getPriority() == TaskPriority.HIGH
					&& task.getStatus() == TaskStatus.IN_PROGRESS) {
				TaskWasCreated = true;
			}

		}
		Assertions.assertThat(TaskWasCreated).isEqualTo(true);

	}

	@Test
	public void getAlltasksFromTasklistByIdWithTitleFilterTest() {
		final String title = "test";
		Page<Task> tasks = iTaskListService.getAlltasksFromTasklistById(title, "", 1L, 0, 20);
		tasks.forEach(task -> Assertions.assertThat(task.getTitle()).contains(title));
	}

	@Test
	public void getTaskInsideTaskListByIdsTest() {
		final Long taskId = 1L;
		final Long taskListId = 1L;
		Task task = iTaskListService.getTaskInsideTaskListByIds(taskId, taskListId);
		Assertions.assertThat(task).isNotNull();
		Assertions.assertThat(task.getTaskList()).isNotNull();
		Assertions.assertThat(task.getTaskList().getId()).isEqualTo(taskListId);
		Assertions.assertThat(task.getId()).isEqualTo(taskId);
	}

	@Test
	public void updateTaskFromTaskListTest() {
		final Long taskId = 1L;
		final Long taskListId = 1L;
		final String title = "UPDATED";
		iTaskListService.getById(taskListId);
		TaskDto taskDto = new TaskDto(null, title, TaskStatus.IN_PROGRESS, TaskPriority.HIGH, LocalDate.now(),
				LocalDate.now(), null);
		iTaskListService.updateTaskFromTaskList(taskId, taskListId, taskDto);
		Task task = iTaskListService.getTaskInsideTaskListByIds(taskId, taskListId);
		Assertions.assertThat(task).isNotNull();
		Assertions.assertThat(task.getTitle()).isEqualTo(title);
	}

}
