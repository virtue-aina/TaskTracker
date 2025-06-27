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
    private UUID id;
    private String fileName;
    private String OriginalFileName;
    private String fileType;
    private Long size;
    private byte[] fileContent;

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
