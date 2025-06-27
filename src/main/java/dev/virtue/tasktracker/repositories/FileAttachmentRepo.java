package dev.virtue.tasktracker.repositories;

import dev.virtue.tasktracker.domain.entities.FileAttachment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FileAttachmentRepo extends JpaRepository<FileAttachment, UUID> {

}
