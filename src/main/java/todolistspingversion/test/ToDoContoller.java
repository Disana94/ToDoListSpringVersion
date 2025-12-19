package todolistspingversion.test;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;


@Controller
@RequestMapping("/api")
public class ToDoContoller {

    private final ToDoService toDoService;

    public ToDoContoller(ToDoService toDoService){
        this.toDoService = toDoService;
    }

    @GetMapping("/allTasks")
    public ResponseEntity<List<Tasks>> getAllTask(){
        return ResponseEntity.ok(toDoService.getAllTasks());

    }

    @GetMapping("/{id}")
    public ResponseEntity<Tasks> getTaskById(
            @PathVariable("id") Long id
    ){
        return ResponseEntity.status(HttpStatus.OK).body(toDoService.getTaskById(id));
    }

    @PostMapping("/tasks")
    public ResponseEntity<Tasks> postTask(
            @Valid @RequestBody TaskDto task
    ){
        Tasks taskToService = new Tasks(
                null,
                task.task(),
                LocalDateTime.now(),
                Status.InProgress
        );
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(toDoService.createTask(taskToService));

    }

    @PutMapping("/{id}")
    public ResponseEntity<Tasks> putTask(
            @PathVariable("id") Long id,
            @Valid @RequestBody TaskDto task
    ){
        Tasks taskToService = new Tasks(
                id,
                task.task(),
                null,
                Status.InProgress
        );
        try{
            Tasks update = toDoService.putTask(taskToService);
            return ResponseEntity.ok(update);
        }catch (NoSuchElementException e){
            return ResponseEntity.status(404).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Tasks> deleteTask(
            @PathVariable("id") Long id
    ){
        try{
            toDoService.deleteTask(id);
            return ResponseEntity.status(204)
                    .build();
        }catch (NoSuchElementException e){
            return ResponseEntity.status(404)
                    .build();
        }

    }
}

//принимает запросы












