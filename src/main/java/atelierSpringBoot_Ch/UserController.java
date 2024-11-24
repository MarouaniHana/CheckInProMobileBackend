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
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private WorkSessionService service;
    
    
    
    @PostMapping(value = "/si", consumes = {"multipart/form-data"})
    public ResponseEntity<?> signup(
            @RequestParam("nom") String nom,
            @RequestParam("prenom") String prenom,
            @RequestParam("email") String email,
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("telephone") String telephone,
            @RequestParam("cin") String cin,
            @RequestParam("poste")String poste,
            @RequestParam("adresseComplet")String adresseComplet,
            @RequestParam("dateNaissance")String dateNaissance,
            @RequestParam("dateDebutTravail")String dateDebutTravail,
            @RequestParam(value = "image", required = false) MultipartFile image) {
        
        try {
            User user = new User();
            user.setNom(nom);
            user.setPrenom(prenom);
            user.setEmail(email);
            user.setUsername(username);
            user.setPassword(password);
            user.setTelephone(telephone);
            user.setCin(cin);
            user.setPoste(poste);
            user.setDateNaissance(dateNaissance);
            user.setDateDebutTravail(dateDebutTravail);
            
            user.setAdresseComplet(adresseComplet);
            if (image != null && !image.isEmpty()) {
                user.setImage(image.getBytes());
            }

            // Vérifier les doublons
            if (userService.existsByEmail(email)) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Email déjà utilisé");
            }

            if (userService.existsByUsername(username)) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Nom d'utilisateur déjà utilisé");
            }

            // Ajouter l'utilisateur
            User newUser = userService.addUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(newUser);

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'enregistrement de l'image");
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
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        User user = userService.findByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword());

        if (user != null) {
            return ResponseEntity.ok(user);  // Connexion réussie
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Identifiants incorrects");  // Échec de la connexion
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
    
    
}
