package dev.virtue.tasktracker.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
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

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "task_list_id")
   private TaskList taskList;

   @Column(name = "created")
   private LocalDateTime created;

   @Column(name = "updated")
   private LocalDateTime updated;



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

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
