package atelierSpringBoot_Ch;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
public 	 boolean existsByUsername(String username);
public 	    boolean existsByEmail(String email);
public User findByEmailAndPassword(String email,String password);
public User findByEmail(String email);
}
	    

