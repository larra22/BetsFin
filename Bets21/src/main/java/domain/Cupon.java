package domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlIDREF;

@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Cupon implements Serializable {
	@Id
	@XmlIDREF
	private Usuario user;
	private int numCuponconseguido;
	private float dineroAcumulado;

	public Cupon() {

	}

	public Cupon(Usuario user) {
		this.user = user;
		this.dineroAcumulado = 0;
		this.numCuponconseguido = 0;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public float getDineroAcumulado() {
		return dineroAcumulado;
	}

	public void setDineroAcumulado(float dineroAcumulado) {
		this.dineroAcumulado = dineroAcumulado;
	}

	public int getNumCuponconseguido() {
		return numCuponconseguido;
	}

	public void setNumCuponconseguido(int numCuponconseguido) {
		this.numCuponconseguido = numCuponconseguido;
	}

}
