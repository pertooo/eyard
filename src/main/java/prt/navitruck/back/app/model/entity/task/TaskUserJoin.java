package prt.navitruck.back.app.model.entity.task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import prt.navitruck.back.app.model.entity.User;
import prt.navitruck.back.app.model.entity.abstr.AbstractEntity;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"task_id", "user_id"}))
public class TaskUserJoin extends AbstractEntity {

    public TaskUserJoin(Task task, User user){
        this.task = task;
        this.user = user;
    }

    @ManyToOne
    @JoinColumn
    private Task task;

    @ManyToOne
    @JoinColumn
    private User user;

    @Column(name = "task_given", nullable = false)
    private boolean given;
}
