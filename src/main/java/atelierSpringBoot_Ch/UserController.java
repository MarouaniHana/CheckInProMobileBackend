package atelierSpringBoot_Ch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.mail.MessagingException;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private EmailService emailService;
    @Autowired
    private UserService userService;
    @Autowired
    private WorkSessionService service;
    
    
    
    @PostMapping(value = "/si", consumes = {"application/json"})
    public ResponseEntity<?> signup(@RequestBody User user) {
        try {
            // Check if the email or username already exists
            if (userService.existsByEmail(user.getEmail())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Email déjà utilisé");
            }

            if (userService.existsByUsername(user.getUsername())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Nom d'utilisateur déjà utilisé");
            }

            // Add the new user
            User newUser = userService.addUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'inscription");
        }
    }

    @PostMapping("/work")
    public WorkSession createSession(@RequestBody WorkSession session) {
        return service.save(session);
    }

    @GetMapping("/session")
    public List<WorkSession> getAllSessions() {
        return service.getAllSessions();
    }
    @GetMapping("/profile")
    public ResponseEntity<?> findUserProfile(@RequestHeader("User-Email") String email) {
        // Vérifier que l'email est présent
        if (email == null || email.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email utilisateur manquant dans les en-têtes");
        }

        // Rechercher l'utilisateur à partir de l'email fourni
        User user = userService.findByEmail(email);

        // Vérifier si l'utilisateur existe
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Utilisateur non trouvé");
        }

        // Retourner le profil de l'utilisateur
        return ResponseEntity.ok(user);
    }

  
       
    @PostMapping("/login")
	public ResponseEntity<User> getUserByEmailAndPassword(@RequestBody LoginRequest loginReq) {
	    User user = userService.findUserByEmailAndPassword(loginReq.getEmail(), loginReq.getPassword());
	    if (user != null) {
	        return ResponseEntity.ok(user);
	    } else {
	        return ResponseEntity.status(401).build(); // Unauthorized
	    }
	}
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        try {
            User user = userService.updateUser(id, updatedUser);
            return ResponseEntity.ok(user);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    
    
    @DeleteMapping(path="/{id}")
    public void deleletUser(@PathVariable Long id) {
        userService.deleletUser(id);
    }
    @GetMapping("/login")
	public User findByEmailAndPassword(@RequestBody LoginRequest loginRequest)
	{
    	return userService.findByEmailAndPassword(loginRequest.getEmail(),loginRequest.getPassword());
	}
    @GetMapping
    public List<User> getAllUtilisateurs2() {
        return userService.getAllUtilisateur();
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.findUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
       }

    @PostMapping("/{id}/send-email")
    public ResponseEntity<?> sendEmailToUser(@PathVariable Long id) {
        try {
            User user = userService.findUserById(id);
            if (user != null) {
                emailService.sendEmailAttachment(
                    user.getEmail(),
                    " Confirmation de votre inscription",
                    "Bonjour " + user.getPrenom()+ " Nous avons le plaisir de vous informer que votre demande d'inscription a été acceptée avec succès. Vous faites désormais partie de notre communauté.\r\n"
                    		+ "\r\n"
                    		+ "Voici quelques informations importantes pour commencer :\r\n"
                    		+ "- Accédez à votre compte en ligne : https://www.winshot.net/\r\n"
                    		+ "- En cas de besoin, notre équipe est disponible pour vous assister.\r\n"
                    		+ "\r\n"
                    		+ "Si vous avez des questions ou besoin d'assistance, n'hésitez pas à nous contacter à 20550162.\r\n"
                    		+ "\r\n"
                    		+ "Nous vous remercions pour votre confiance et vous souhaitons une excellente expérience avec nous."
                );
                return ResponseEntity.ok("Email envoyé avec succès.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Utilisateur introuvable.");
            }
        } catch (MessagingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'envoi de l'email.");
        }
    }
    @PutMapping("active/{id}")
    public ResponseEntity<?> updateActiveStatus(@PathVariable Long id, @RequestBody Map<String, Boolean> updates) {
        Boolean isActive = updates.get("active");
        if (isActive == null) {
            return ResponseEntity.badRequest().body("Le champ 'active' est manquant ou incorrect.");
        }

        try {
            User updatedUser = userService.updateActiveStatus(id, isActive);
            return ResponseEntity.ok("Statut actif de l'utilisateur mis à jour avec succès.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    
    @GetMapping("/active")
    public List<User> getActiveUsers() {
        return userService.getActiveUsers(); 
    }
    
    @PostMapping 
	public User createUser(@RequestBody User user){ 
    	return userService.addUser(user); 
	}
    
    
    
}
