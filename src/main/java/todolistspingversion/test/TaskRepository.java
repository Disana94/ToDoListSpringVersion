package todolistspingversion.test;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity,Long> {

}
//он помогает управлять бд сам апдейтит создает удалеят