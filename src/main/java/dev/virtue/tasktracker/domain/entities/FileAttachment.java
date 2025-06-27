package dev.virtue.tasktracker.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
import java.util.UUID;


/**
 * Represents a file attachment associated with a task in the task tracker application.
 * This entity is used to store metadata about the file, including its name, type, size,
 * and the actual content of the file.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "file_attachments")
public class FileAttachment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    @Column(name = "f_name", nullable = false)
    private String storedFileName;
    @Column(name = "original_f_name", nullable = false)
    private String OriginalFileName;
    @Column(name = "f_type", nullable = false)
    private String fileType;
    @Column(name = "f_size", nullable = false)
    private Long size;

    /**
     * The task to which this file attachment is associated.
     * This field establishes a many-to-one relationship with the Task entity.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_task_id", nullable = false)
    private Task task;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        FileAttachment that = (FileAttachment) o;
        return Objects.equals(id, that.id) && Objects.equals(storedFileName, that.storedFileName) && Objects.equals(OriginalFileName, that.OriginalFileName) && Objects.equals(fileType, that.fileType) && Objects.equals(size, that.size) && Objects.equals(task, that.task);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, storedFileName, OriginalFileName, fileType, size, task);
    }

}
