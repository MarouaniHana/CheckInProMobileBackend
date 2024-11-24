package atelierSpringBoot_Ch;



import java.util.List;

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
	
	   public User updateUser(Long id, User updatedUser) {
		    if (userRepository.existsById(id)) {
		        updatedUser.setId(id);  // S'assurer que l'ID de l'utilisateur mis à jour correspond bien à l'ID passé
		        return userRepository.save(updatedUser);
		    }
		    return null;  // Si l'utilisateur n'existe pas, retourner null
		}
	 
	    public void deleletUser(Long id) {
	        if (userRepository.existsById(id)) {
	            userRepository.deleteById(id);
	        }
	    }
	    public List<User> getAllUtilisateur() {
	        return userRepository.findAll();
	    } 
	    
	    
	    
		public User findUserById(Long id) {
			// TODO Auto-generated method stub
			return userRepository.findById(id).orElse(null);
		}
    
	    
	    
	    
}
