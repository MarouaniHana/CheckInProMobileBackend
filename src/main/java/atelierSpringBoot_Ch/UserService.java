package atelierSpringBoot_Ch;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {
	   @Autowired
	    private UserRepository userRepository;

	 
	    public User addUser(User user) {
	        
	        if (userRepository.existsByUsername(user.getUsername())) {
	            throw new IllegalArgumentException("Username already exists");
	        }
	        if (userRepository.existsByEmail(user.getEmail())) {
	            throw new IllegalArgumentException("Email already exists");
	        }
	        return userRepository.save(user);
	    }
	    
	    public boolean existsByEmail(String email) {
	        return userRepository.existsByEmail(email);
	    }

	    public boolean existsByUsername(String username) {
	        return userRepository.existsByUsername(username);
	    }
	   public User findByEmailAndPassword(String  email,String password) {
		   return userRepository.findByEmailAndPassword(email, password);
	   }
	   public User findByEmail(String email) {
	        return userRepository.findByEmail(email);
	    } 
	

	    
}
