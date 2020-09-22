package ToDoApp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class ToDoEntry extends AbstractPersistable<Long> {

    private String toDo;
    private LocalDate deadline;
    private boolean done;

}
