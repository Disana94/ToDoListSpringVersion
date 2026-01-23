package todolistspingversion.test;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;

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

    public Tasks getTaskById(Long id){
        TaskEntity taskById = repository.getById(id);
        return(toTasks(taskById));
    }

    public Tasks createTask(Tasks tasks){
       TaskEntity task = toEntity(tasks);
       TaskEntity savedEntity = repository.save(task);
       return toTasks(savedEntity);

    }

    public Tasks editTask(Tasks Task){
        TaskEntity task = repository.findById(Task.id())
                .orElseThrow(NoSuchElementException::new);
        task.setTask(Task.task());
        TaskEntity saved = repository.save(task);
        return toTasks(saved);
    }


    public Tasks completeTask(Long id){
        TaskEntity task = repository.findById(id)
                .orElseThrow(NoSuchElementException::new);
        task.setStatus(Status.Done);
        TaskEntity saved = repository.save(task);
        return toTasks(saved);
    }

    public void deleteTask(Long id){
        if(repository.existsById(id)){
            repository.deleteById(id);
        }else{
            throw  new NoSuchElementException("No such element by id: " + id);
        }
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