package com.api.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.api.todolist.models.TaskList;

@Repository
public interface TaskListRepository extends JpaRepository<TaskList, Long> {

}
