package prt.navitruck.back.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import prt.navitruck.back.app.config.QueryCfg;
import prt.navitruck.back.app.controller.abstr.AbstractController;
import prt.navitruck.back.app.model.dto.ResponseDTO;
import prt.navitruck.back.app.model.entity.Task;
import prt.navitruck.back.app.network.HttpClient;
import prt.navitruck.back.app.repository.TaskRepository;
import prt.navitruck.back.app.repository.abstr.AbstractRepository;
import prt.navitruck.back.app.utils.Constants;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/task")
public class TaskController extends AbstractController<Task, Long> {

    @Autowired
    public TaskController(TaskRepository repository) {
        super(repository);
        this.getFilterFields().add(QueryCfg.builder()
                .field("name")
                .operation(Constants.QueryOperation.LIKE)
                .nextOperation(Constants.QueryOperation.OR)
                .build());
    }

    @PostMapping("/accept")
    public ResponseEntity accept(@RequestParam long taskid,
                                 @RequestParam double fee) {

        System.out.println("Task taskId - "+taskid);
        System.out.println("Task fee - "+fee);

        try{
            TimeUnit.SECONDS.sleep(30);

        }catch (Exception e){
            e.printStackTrace();
        }


        return ResponseEntity.ok(ResponseDTO.builder().success(true).build());
    }


}
