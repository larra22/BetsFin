package domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Seleccion implements Serializable {

	@Id
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@GeneratedValue
	private int id;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private Vector<Event> events = new Vector<Event>();
	private String deporte;
	private String genero;
	private String seleccion;
	private int cuentaDeApuestas;

	public Seleccion() {
		super();
	}

	public Seleccion(String deporte, String genero, String seleccion) {
		this.deporte = deporte;
		this.genero = genero;
		this.seleccion = seleccion;
		this.cuentaDeApuestas = 0;
	}

	public Vector<Event> getEvents() {
		return events;
	}

	public void setEvents(Vector<Event> events) {
		this.events = events;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDeporte() {
		return deporte;
	}

	public void setDeporte(String deporte) {
		this.deporte = deporte;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getSeleccion() {
		return seleccion;
	}

	public void setSeleccion(String seleccion) {
		this.seleccion = seleccion;
	}

	public int getCuentaDeApuestas() {
		return cuentaDeApuestas;
	}

	public void setCuentaDeApuestas(int cuentaDeApuestas) {
		this.cuentaDeApuestas = cuentaDeApuestas;
	}

	public Event addEvent(String description, Date eventDate) {
		Event ev = new Event(description, eventDate, this);
		events.add(ev);
		return ev;
	}

}
