package atelierSpringBoot_Ch;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class WorkSessionService {
	 @Autowired
	    private WorkSessionRepository repository;

	    public WorkSession save(WorkSession session) {
	        return repository.save(session);
	    }

	    public List<WorkSession> getAllSessions() {
	        return repository.findAll();
	    }
}
