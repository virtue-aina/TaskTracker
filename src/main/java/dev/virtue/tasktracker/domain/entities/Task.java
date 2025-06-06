package dev.virtue.tasktracker.domain.entities;

import jakarta.persistence.*;
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
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
   private UUID id;
    @Column(name = "title", updatable = true, nullable = false )
   private String title;

   @Column(name = "description")
   private String description;

   @Column(name = "dead_line")
   private LocalDateTime deadline;

   @Column(name = "priority")
   private TaskPriority priority;

   @Column(name = "status")
   private TaskStatus status;

   @Column(name = "created")
   private LocalDateTime created;

   @Column(name = "updated")
   private LocalDateTime updated;

   private TaskList taskList;

}
