package dev.virtue.tasktracker;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class Task {
    @Id
   private UUID id;
   private String title;
   private String description;
   private LocalDateTime deadline;
   private TaskPriority priority;
   private LocalDateTime created;
   private LocalDateTime updated;
   private TaskList taskList;

}
