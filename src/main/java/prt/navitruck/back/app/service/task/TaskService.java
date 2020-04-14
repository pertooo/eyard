package prt.navitruck.back.app.service.task;

import prt.navitruck.back.app.model.entity.task.Task;

import java.util.List;


public interface TaskService {

    Task getTask(Long id);
    List<Task> findAll();
}
