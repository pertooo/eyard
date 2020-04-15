package prt.navitruck.back.app.repository.task;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import prt.navitruck.back.app.model.entity.task.Task;
import prt.navitruck.back.app.model.entity.task.TaskUserJoin;
import prt.navitruck.back.app.repository.abstr.AbstractRepository;

public interface TaskUserJoinRepository extends AbstractRepository<TaskUserJoin, Long> {


    @Modifying
    @Query("update TaskUserJoin t set t.assigned = true where t.user.id = :userId and t.task.id = :taskId")
    int setAssigned(@Param("userId") Long userId, @Param("taskId") Long taskId);
}
