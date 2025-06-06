package dev.virtue.tasktracker.domain.entities;

/**
 * Enum representing the priority levels of a task in the task tracker system.
 * This enum is used to indicate the importance or urgency of a task.
 */
public enum TaskPriority {
    /**
     * Indicates a task with the highest priority level.
     * Tasks with this priority should be addressed first and require immediate attention.
     */
    HIGH,

    /**
     * Indicates a task with a standard priority level.
     * Tasks with this priority should be addressed after high priority tasks.
     */
    MEDIUM,

    /**
     * Indicates a task with the lowest priority level.
     * Tasks with this priority can be addressed after high and medium priority tasks.
     */
    LOW
}
