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

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
    public ResponseEntity accept(@RequestParam long taskId,
                                 @RequestParam long userId,
                                 @RequestParam double fee) {

        Task task = taskService.getTask(taskId);
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

        try{
            TimeUnit.SECONDS.sleep(30);

        }catch (Exception e){
            e.printStackTrace();
        }


        return ResponseEntity.ok(ResponseDTO.builder().success(true).build());
    }



    @PostMapping("/assign")
    public ResponseEntity assign(@RequestParam long userId,
                                 @RequestParam long taskId) {

        try{
            int ret = taskUserJoinService.assignUserToTask(userId, taskId);
            System.out.println("assign ret "+ret);
        }catch (Exception e){
            e.printStackTrace();
        }

        return ResponseEntity.ok(ResponseDTO.builder().success(true).build());
    }

    @PostMapping("/assignById")
    public ResponseEntity accept(@RequestParam long id) {

        try{
            TaskUserJoin taskUserJoin = taskUserJoinService.getOne(id);
            taskUserJoin.getTimestamp().setUpdated(LocalDateTime.now());

            if(taskUserJoin!=null){
                taskUserJoin = taskUserJoinService.update(taskUserJoin);
                System.out.println("assign taskUserJoin.getTask().getAddressFrom() "+taskUserJoin.getTask().getAddressFrom());



            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return ResponseEntity.ok(ResponseDTO.builder().success(true).build());
    }

}
