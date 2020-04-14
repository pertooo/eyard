package prt.navitruck.back.app.serviceImpl.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prt.navitruck.back.app.model.entity.task.Task;
import prt.navitruck.back.app.repository.task.TaskRepository;
import prt.navitruck.back.app.service.task.TaskService;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;


    @Override
    public Task getTask(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }
}
