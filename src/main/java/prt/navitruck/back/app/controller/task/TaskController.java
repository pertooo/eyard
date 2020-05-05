package prt.navitruck.back.app.controller.task;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import prt.navitruck.back.app.controller.abstr.AbstractController;
import prt.navitruck.back.app.controller.notification.NotificationController;
import prt.navitruck.back.app.model.response.ResponseDTO;
import prt.navitruck.back.app.model.entity.User;
import prt.navitruck.back.app.model.entity.task.Task;
import prt.navitruck.back.app.model.entity.task.TaskUserJoin;
import prt.navitruck.back.app.repository.task.TaskRepository;
import prt.navitruck.back.app.service.notification.AndroidPushNotificationsService;
import prt.navitruck.back.app.service.task.TaskUserJoinService;
import prt.navitruck.back.app.serviceImpl.task.TaskServiceImpl;
import prt.navitruck.back.app.serviceImpl.user.UserServiceImpl;
import prt.navitruck.back.app.utils.Constants;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;
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
    AndroidPushNotificationsService androidPushNotificationsService;

    private static int attempt = 0;
    private final int maxAttempts = 3;
    private final int threadSleepDelay = 15;

    @Autowired
    public TaskController(TaskRepository repository) {
        super(repository);
    }

    @PutMapping("/save")
    public ResponseEntity save(@RequestBody Task task) {
        Task savedObject = taskService.saveTask(task);

        if(task.isSendImmediately()) { // send notification
            try {
                JSONObject body = taskService.buildJsonFromTask(savedObject);
                HttpEntity<String> request = new HttpEntity<>(body.toString());

                CompletableFuture<String> pushNotification = androidPushNotificationsService.send(request);
                CompletableFuture.allOf(pushNotification).join();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        if (savedObject != null) {
            return ResponseEntity.ok(ResponseDTO.builder().success(true).build());
        }
        return ResponseEntity.notFound().build();
    }

    //    @RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT },
//            consumes = { "multipart/form-data" } , path = "update_status")
//    public ResponseEntity updateStatus(@RequestParam("files") MultipartFile[] files,
//                                       @RequestParam long taskId,
//                                       @RequestParam long userId) {
//
//        System.out.println("updateStatus");
//
//        return ResponseEntity.ok(ResponseDTO.builder().success(true).build());
//    }

    @PostMapping("/update_status")
    public ResponseEntity updateStatus(@RequestParam long taskId,
                                 @RequestParam long userId) {

        System.out.println("updateStatus");

        return ResponseEntity.ok(ResponseDTO.builder().success(true).build());
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
                System.out.println(e.getMessage());
            }

        }

        TaskUserJoin taskUserJoin = null;

        try{
            taskUserJoin = getAssignedAfterDelay(task);
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }


        if(taskUserJoin!=null){ //exists and return success

            if(true) {//TODO check if taskUserJoin is for current user
                return ResponseEntity.ok(ResponseDTO.builder().success(true).content(Constants.TaskStatusObj.ASSIGNED).build());
            }else{
                return ResponseEntity.ok(ResponseDTO.builder().success(true).content(Constants.TaskStatusObj.ASSIGNED_TO_OTHER).build());
            }


        }else{ //return response needs more time
            return ResponseEntity.ok(ResponseDTO.builder().success(true).content(Constants.TaskStatusObj.NOT_ASSIGNED).build());
        }

    }

    private TaskUserJoin getAssignedAfterDelay(Task task){
        threadSleep();

        TaskUserJoin taskUserJoin = taskUserJoinService.getAssignedByTask(task);

        if(taskUserJoin!=null){
            return  taskUserJoin;
        }else{ // wait for more  until 5 attempts
            if(attempt<maxAttempts){
                attempt++;
                getAssignedAfterDelay(task);
            }
        }
        return null;
    }

    private void threadSleep(){
        try{
            TimeUnit.SECONDS.sleep(threadSleepDelay);

        }catch (Exception e){
            e.printStackTrace();
        }
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
