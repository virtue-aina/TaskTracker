package dev.virtue.tasktracker.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;


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
    private String fileName;
    @Column(name = "original_f_name", nullable = false)
    private String OriginalFileName;
    @Column(name = "f_type", nullable = false)
    private String fileType;
    @Column(name = "f_size", nullable = false)
    private Long size;

    @Lob
    @Column(name = "file_content", columnDefinition = "BLOB")
    private byte[] fileContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        FileAttachment that = (FileAttachment) o;
        return Objects.equals(fileName, that.fileName) && Objects.equals(OriginalFileName, that.OriginalFileName) && Objects.equals(fileType, that.fileType) && Objects.equals(size, that.size) && Objects.deepEquals(fileContent, that.fileContent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileName, OriginalFileName, fileType, size, Arrays.hashCode(fileContent));
    }
}
