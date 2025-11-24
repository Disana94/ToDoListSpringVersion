package todolistspingversion.test;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ToDoService {

    private final TaskRepository repository;

    public ToDoService(TaskRepository repository) {
        this.repository = repository;
    }

    public List<Tasks> getAllTasks(){
        List<TaskEntity> allTasks = repository.findAll();
        return allTasks.stream().
                map(this::toTasks)
                .toList();
    }

    public Tasks createTask(Tasks tasks){
       TaskEntity task = toEntity(tasks);
       TaskEntity savedEntity = repository.save(task);
       return toTasks(savedEntity);

    }

    //вид принять данные и показать
    private Tasks toTasks(TaskEntity tasks){
        return new Tasks(
                tasks.getId(),
                tasks.getTask(),
                tasks.getDate(),
                tasks.getStatus()
        );
    }
    //вид для записи в бд
    private TaskEntity toEntity(Tasks task){
        return new TaskEntity(
                null,
                task.task(),
                task.date(),
                Status.InProgress
        );
    }

}
//тут вся логика