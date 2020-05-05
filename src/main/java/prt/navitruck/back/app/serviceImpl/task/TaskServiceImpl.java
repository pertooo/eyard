package prt.navitruck.back.app.serviceImpl.task;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prt.navitruck.back.app.model.entity.task.Task;
import prt.navitruck.back.app.repository.task.TaskRepository;
import prt.navitruck.back.app.service.task.TaskService;
import prt.navitruck.back.app.utils.Constants;

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

    @Override
    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public JSONObject buildJsonFromTask(Task task) {
        JSONObject body = new JSONObject();

        try {
            //  body.put("to", "/topics/" + TOPIC);
            body.put("condition", Constants.empty_string);
            body.put("priority", "high");

            JSONObject notification = new JSONObject();
            notification.put("title", "JSA Notification");
            notification.put("body", "Happy Message!");

            JSONObject data = new JSONObject();

            data.put("addressFrom", task.getAddressFrom());
            data.put("addressTo", task.getAddressTo());

            data.put("loadTime", task.getPickupTimeStr());
            data.put("unloadTime", task.getUnloadTimeStr());

            data.put("price", task.getFee());
            data.put("weight", task.getWeight());

            //  body.put("notification", notification);
            body.put("data", data);

        }catch (Exception e){
            e.printStackTrace();
        }
        return body;
    }
}
