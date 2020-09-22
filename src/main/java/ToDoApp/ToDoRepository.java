package ToDoApp;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ToDoRepository extends JpaRepository<ToDoEntry, Long> {

    List<ToDoEntry> findByDone(boolean done, Sort sort);

}
