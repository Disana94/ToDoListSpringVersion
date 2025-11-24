package todolistspingversion.test;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping
public class ToDoContoller {

    private final ToDoService toDoService;

    public ToDoContoller(ToDoService toDoService){
        this.toDoService = toDoService;
    }

    @GetMapping("/allTasks")
    public ResponseEntity<List<Tasks>> getTask(){
        return ResponseEntity.ok(toDoService.getAllTasks());

    }

    @PostMapping
    public ResponseEntity<Tasks> postTask(
            @RequestBody Tasks task
    ){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(toDoService.createTask(task));

    }
}

//принимает запросы












