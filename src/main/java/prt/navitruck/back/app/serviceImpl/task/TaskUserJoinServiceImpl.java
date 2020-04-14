package prt.navitruck.back.app.serviceImpl.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prt.navitruck.back.app.model.entity.task.TaskUserJoin;
import prt.navitruck.back.app.repository.task.TaskUserJoinRepository;
import prt.navitruck.back.app.service.task.TaskUserJoinService;

@Service
public class TaskUserJoinServiceImpl implements TaskUserJoinService {

    @Autowired
    private TaskUserJoinRepository taskUserJoinRepository;

    @Override
    public TaskUserJoin save(TaskUserJoin taskUserJoin) {
        return taskUserJoinRepository.save(taskUserJoin);
    }
}
