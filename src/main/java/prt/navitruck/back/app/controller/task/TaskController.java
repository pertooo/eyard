package prt.navitruck.back.app.controller.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import prt.navitruck.back.app.config.QueryCfg;
import prt.navitruck.back.app.controller.abstr.AbstractController;
import prt.navitruck.back.app.model.dto.ResponseDTO;
import prt.navitruck.back.app.model.entity.User;
import prt.navitruck.back.app.model.entity.task.Task;
import prt.navitruck.back.app.model.entity.task.TaskUserJoin;
import prt.navitruck.back.app.repository.task.TaskRepository;
import prt.navitruck.back.app.service.task.TaskUserJoinService;
import prt.navitruck.back.app.serviceImpl.task.TaskServiceImpl;
import prt.navitruck.back.app.serviceImpl.user.UserServiceImpl;
import prt.navitruck.back.app.utils.Constants;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController extends AbstractController<Task, Long> {

    @Autowired
    private TaskServiceImpl taskService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private TaskUserJoinService taskUserJoinService;

    @Autowired
    public TaskController(TaskRepository repository) {
        super(repository);
    }

    @PostMapping("/accept")
    public ResponseEntity accept(@RequestParam long taskid,
                                 @RequestParam double fee) {

        Task task = taskService.getTask(taskid);
        User user = userService.getUser(1l);
        System.out.println("Task task - "+task.getAddressFrom());

        List<String> errors = null;


        if(task!=null && user!=null){
            try{
                taskUserJoinService.save(new TaskUserJoin(task, user));
            }catch (Exception e){
                errors.add(e.getMessage());
                ResponseEntity.ok(ResponseDTO.builder()
                        .success(false)
                        .errors(errors).build());
                e.printStackTrace();
            }

        }

//        try{
//            TimeUnit.SECONDS.sleep(30);
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }


        return ResponseEntity.ok(ResponseDTO.builder().success(true).build());
    }


}
