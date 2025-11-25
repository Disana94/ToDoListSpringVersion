package todolistspingversion.test;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;


@Controller
@RequestMapping
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

    @PostMapping
    public ResponseEntity<Tasks> postTask(
            @RequestBody Tasks task
    ){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(toDoService.createTask(task));

    }

    @PutMapping("/{id}")
    public ResponseEntity<Tasks> putTask(
            @PathVariable("id") Long id
    ){
        try{
            Tasks update = toDoService.putTask(id);
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












