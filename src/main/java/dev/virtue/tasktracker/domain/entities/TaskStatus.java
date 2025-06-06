package dev.virtue.tasktracker.domain.entities;

/**
 * Enum representing the possible statuses of a task in the task tracker system.
 * This enum is used to track the current state of a task.
 */
public enum TaskStatus {
    /**
     * Indicates that a task is currently active and not yet completed.
     * Tasks with this status are still pending and require action.
     */
    OPEN,

    /**
     * Indicates that a task has been completed or is no longer active.
     * Tasks with this status require no further action.
     */
    CLOSED
}
