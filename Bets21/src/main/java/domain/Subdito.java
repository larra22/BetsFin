package domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Subdito {

	@Id
	private String nombre;
	private String password;

	public Subdito(String nom, String passw) {
		this.nombre = nom;
		this.password = passw;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
