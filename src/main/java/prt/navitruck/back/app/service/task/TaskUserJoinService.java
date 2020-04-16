package prt.navitruck.back.app.service.task;

import prt.navitruck.back.app.model.entity.task.Task;
import prt.navitruck.back.app.model.entity.task.TaskUserJoin;

public interface TaskUserJoinService {

    TaskUserJoin getOne(long id);
    TaskUserJoin getAssignedByTask(Task task);
    TaskUserJoin save(TaskUserJoin taskUserJoin);
    TaskUserJoin update(TaskUserJoin taskUserJoin);
    int assignUserToTask(long userId, long taskId);

}
