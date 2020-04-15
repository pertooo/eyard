package prt.navitruck.back.app.service.task;

import prt.navitruck.back.app.model.entity.task.TaskUserJoin;

public interface TaskUserJoinService {

    TaskUserJoin getOne(long id);
    TaskUserJoin save(TaskUserJoin taskUserJoin);
    int assignUserToTask(long userId, long taskId);
    TaskUserJoin update(TaskUserJoin taskUserJoin);
}
