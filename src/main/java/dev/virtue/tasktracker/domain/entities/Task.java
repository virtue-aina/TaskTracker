package dev.virtue.tasktracker.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.AllArgsConstructor;
import org.hibernate.proxy.HibernateProxy;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * Entity class representing a task in the task tracker system.
 * This class is mapped to the "tasks" table in the database.
 * It contains information about a task such as its title, description,
 * deadline, priority, status, and its relationship with a task list.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "tasks")
public class Task {
    /**
     * Unique identifier for the task.
     * This field is automatically generated and cannot be updated.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    /**
     * Title of the task.
     * This field is required and can be updated.
     */
    @Column(name = "title", updatable = true, nullable = false )
    private String title;
    /**
     * Detailed description of the task.
     * This field is optional.
     */
    @Column(name = "description")
    private String description;
    /**
     * Deadline by which the task should be completed.
     * This field is optional.
     */
    @Column(name = "dead_line")
    private LocalDateTime deadline;
    /**
     * Priority level of the task (HIGH, MEDIUM, LOW).
     * defaults to LOW if not specified.
     * This field is required and cannot be null.
     */
    @Column(name = "priority",nullable = false)
    private TaskPriority priority;
    /**
     * Current status of the task (OPEN, CLOSED).
     * defaults to OPEN if not specified.
     * This field is required and cannot be null.
     */
    @Column(name = "status",nullable = false)
    private TaskStatus status;
    /**
     * The task list to which this task belongs.
     * This is a many-to-one relationship, where many tasks can belong to one task list.
     * This field is lazily loaded to improve performance.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_list_id")
    private TaskList taskList;
    /**
     * Timestamp when the task was created.
     * This field is required and cannot be null.
     */
    @Column(name = "created", nullable = false)
    private LocalDateTime created;
    /**
     * Timestamp when the task was last updated.
     * This field is required and cannot be null.
     */
    @Column(name = "updated",nullable = false)
    private LocalDateTime updated;

    /**
     * Compares this task with another object for equality.
     * Two tasks are considered equal if they have the same non-null ID.
     * This method handles Hibernate proxies correctly.
     *
     * @param o The object to compare with this task
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Task task = (Task) o;
        return getId() != null && Objects.equals(getId(), task.getId());
    }
    /**
     * Returns a hash code value for this task.
     * This method handles Hibernate proxies correctly.
     * @return A hash code value for this task
     */
    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
