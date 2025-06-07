package dev.virtue.tasktracker.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Entity class representing a task list in the task tracker system.
 * This class is mapped to the "task_lists" table in the database.
 * It contains information about a task list such as its title, description,
 * and the tasks that belong to it.
 */
@Getter
@Setter
@Entity
@Table(name = "task_lists")
public class TaskList {
    /**
     * Unique identifier for the task list.
     * This field is automatically generated and cannot be updated.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    /**
     * Title of the task list.
     * This field is required and cannot be null.
     */
    @Column(name = "title", nullable = false)
    private String title;
    /**
     * Detailed description of the task list.
     * This field is required and cannot be null.
     */
    @Column(name = "description", nullable = false)
    private String description;

    /**
     * List of tasks that belong to this task list.
     * This is a one-to-many relationship, where one task list can have many tasks.
     * The tasks are mapped by the "taskList" field in the Task entity.
     * When a task list is removed or persisted, the corresponding operations are cascaded to its tasks.
     */
    @OneToMany(mappedBy = "taskList", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<Task> tasks;
    /**
     * Timestamp when the task list was created.
     * This field is required and cannot be null.
     */
    @Column(name = "created_at",nullable = false)
    private LocalDateTime createdAt;

    /**
     * Timestamp when the task list was last updated.
     * This field is required and cannot be null.
     */
    @Column(name = "updated_at",nullable = false)
    private LocalDateTime updatedAt;

    /**
     * Default constructor for JPA
     */
    public TaskList() {
    }

    /**
     * Constructor for creating a TaskList with all fields
     * 
     * @param id the unique identifier
     * @param title the title of the task list
     * @param description the description of the task list
     * @param tasks the list of tasks
     * @param createdAt the creation timestamp
     * @param updatedAt the last update timestamp
     */
    public TaskList(UUID id, String title, String description, List<Task> tasks, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.tasks = tasks;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

}
