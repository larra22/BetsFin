package domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Apuesta implements Serializable {

	@Id
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@GeneratedValue
	private int id;
	@XmlIDREF
	private Usuario user;
	@XmlIDREF
	private Pronostico respuesta;
	@XmlIDREF
	private Event evento;
	private float apuesta;

	public Apuesta() {

	}

	public Apuesta(Usuario user, Pronostico respuesta, Event ev, float apuesta) {
		this.user = user;
		this.respuesta = respuesta;
		this.evento = ev;
		this.apuesta = apuesta;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public Pronostico getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(Pronostico respuesta) {
		this.respuesta = respuesta;
	}

	public float getApuesta() {
		return apuesta;
	}

	public void setApuesta(float apuesta) {
		this.apuesta = apuesta;
	}

}
