package atelierSpringBoot_Ch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sessions")
public class WorkSessionController {

    @Autowired
    private WorkSessionService service;

    @PostMapping
    public WorkSession createSession(@RequestBody WorkSession session) {
        return service.save(session);
    }

    @GetMapping
    public List<WorkSession> getAllSessions() {
        return service.getAllSessions();
    }
}