package atelierSpringBoot_Ch;

import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
public class LoginRequest {
private String email;
private String password;
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	email = email;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}


}
