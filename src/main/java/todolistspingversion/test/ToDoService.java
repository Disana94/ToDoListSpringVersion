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
                map(this::toDomainReservation)
                .toList();
    }

    private Tasks toDomainReservation(TaskEntity tasks){
        return new Tasks(
                tasks.getId(),
                tasks.getTask(),
                tasks.getDate(),
                tasks.getStatus()
        );
    }
}
//тут вся логика