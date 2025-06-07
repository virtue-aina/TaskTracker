package dev.virtue.tasktracker.repositories;

import dev.virtue.tasktracker.domain.entities.Task;
import dev.virtue.tasktracker.domain.entities.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TaskListRepo extends JpaRepository<TaskList, UUID> {
    List<Task> findTaskListById(UUID taskListId);

    Optional<Task> findByTaskListIdandId(UUID taskListId, UUID taskId);
}
