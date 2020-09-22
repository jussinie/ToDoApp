package ToDoApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class DefaultController {

    @Autowired
    ToDoRepository toDoRepository;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("doneEntries", toDoRepository.findByDone(true, Sort.by("deadline").descending()));
        model.addAttribute("toDoEntries", toDoRepository.findByDone(false, Sort.by("deadline").descending()));
        return "index";
    }

    @PostMapping("/")
    public String addToDo(@RequestParam String toDo, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate deadline) {
        toDoRepository.save(new ToDoEntry(toDo, deadline, false));
        return "redirect:/";
    }

    @GetMapping("/markAsDone/{id}")
    public String markAsDone(@PathVariable Long id) {
        ToDoEntry entry = toDoRepository.getOne(id);
        entry.setDone(true);
        toDoRepository.save(entry);
        return "redirect:/";
    }

    @GetMapping("/undo/{id}")
    public String undoAsDone(@PathVariable Long id) {
        ToDoEntry entry = toDoRepository.getOne(id);
        entry.setDone(false);
        toDoRepository.save(entry);
        return "redirect:/";
    }

    @GetMapping("/deleteEntry/{id}")
    public String deleteEntry(@PathVariable Long id) {
        toDoRepository.delete(toDoRepository.getOne(id));
        return "redirect:/";
    }

}
