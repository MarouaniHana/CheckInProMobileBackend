package atelierSpringBoot_Ch;

import java.time.LocalDate;
import java.util.Arrays;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String cin;
    private String email;
    private String username;
    private String password;
    private String dateNaissance;
    private String poste;
    private String adresseComplet;
    private String telephone;
    private String roles;
    @Lob
    private byte[] image;
    private String dateDebutTravail;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getCin() {
		return cin;
	}
	public void setCin(String cin) {
		this.cin = cin;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(String dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	public String getPoste() {
		return poste;
	}
	public void setPoste(String poste) {
		this.poste = poste;
	}
	public String getAdresseComplet() {
		return adresseComplet;
	}
	public void setAdresseComplet(String adresseComplet) {
		this.adresseComplet = adresseComplet;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public String getDateDebutTravail() {
		return dateDebutTravail;
	}
	public void setDateDebutTravail(String dateDebutTravail) {
		this.dateDebutTravail = dateDebutTravail;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", cin=" + cin + ", email=" + email
				+ ", username=" + username + ", password=" + password + ", dateNaissance=" + dateNaissance + ", poste="
				+ poste + ", adresseComplet=" + adresseComplet + ", telephone=" + telephone + ", image="
				+ Arrays.toString(image) + ", dateDebutTravail=" + dateDebutTravail + "]";
	}

    
}


