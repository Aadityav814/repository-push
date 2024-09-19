package com.excel.webapplication.model;




import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Formdata {
@NotBlank(message = "not null")
  private String email;
  private String password;
@Override
public String toString() {
	return "Formdata [email=" + email + ", password=" + password + "]";
}
}
