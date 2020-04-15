package prt.navitruck.back.app.serviceImpl.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import prt.navitruck.back.app.errors.EntityNotFoundException;
import prt.navitruck.back.app.model.entity.task.Task;
import prt.navitruck.back.app.model.entity.task.TaskUserJoin;
import prt.navitruck.back.app.repository.task.TaskUserJoinRepository;
import prt.navitruck.back.app.service.task.TaskUserJoinService;

@Service
public class TaskUserJoinServiceImpl implements TaskUserJoinService {

    @Autowired
    private TaskUserJoinRepository taskUserJoinRepository;

    @Override
    public TaskUserJoin getOne(long id) {
        return taskUserJoinRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(TaskUserJoin.class));
    }

    @Override
    public TaskUserJoin save(TaskUserJoin taskUserJoin) {
        return taskUserJoinRepository.save(taskUserJoin);
    }


    @Override
    @Transactional
    public int assignUserToTask(long userId, long taskId) {
        return taskUserJoinRepository.setAssigned(userId, taskId);
    }

    @Override
    @Transactional
    public TaskUserJoin update(TaskUserJoin taskUserJoin) {
        return taskUserJoinRepository.save(taskUserJoin);
    }
}
