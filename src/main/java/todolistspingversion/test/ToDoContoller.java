package todolistspingversion.test;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;


@Controller
@RequestMapping
public class ToDoContoller {

    private final ToDoService toDoService;

    public ToDoContoller(ToDoService toDoService){
        this.toDoService = toDoService;
    }

    @GetMapping("/{allTasks}")
    public ResponseEntity<List<Tasks>> getReservations(){
        return ResponseEntity.ok(toDoService.getAllTasks());

    }
}

//принимает запросы
