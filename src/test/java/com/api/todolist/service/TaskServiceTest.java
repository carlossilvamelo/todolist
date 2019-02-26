package com.api.todolist.service;

import java.time.Duration;
import java.time.LocalDate;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;
import com.api.todolist.models.Task;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskServiceTest {

	@Autowired
	private ITaskService iTaskService;

	@Test
	public void getByIdTest() {
		Long id = 1L;
		Task task = iTaskService.getById(id);
		Assertions.assertThat(task).isNotNull();
		Assertions.assertThat(task.getId()).isEqualTo(id);
	}

	@Test
	public void getAllPaginationTest() {
		final Integer pageSize = 4;
		Page<Task> tasks = iTaskService.getAll(0, pageSize);
		Assertions.assertThat(tasks).isNotNull();
		Assertions.assertThat(tasks.getSize()).isEqualTo(pageSize);
	}

	@Test
	public void getAllAboutToExpireTest() {
		Page<Task> tasks = iTaskService.getAllAboutToExpire(0, 30);
		tasks.forEach(task -> {
			Assertions
					.assertThat(Duration.between(LocalDate.now().atStartOfDay(), task.getDue().atStartOfDay()).toDays())
					.isBetween(0L, 5L);
		});
	}

	@Test
	public void getAllExpiredTest() {
		Page<Task> tasks = iTaskService.getAllExpired(0, 30);
		tasks.forEach(task -> {
			Assertions
					.assertThat(Duration.between(LocalDate.now().atStartOfDay(), task.getDue().atStartOfDay()).toDays())
					.isLessThan(0L);
		});
	}
}
