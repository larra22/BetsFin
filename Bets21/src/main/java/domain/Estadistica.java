package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Estadistica implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@GeneratedValue
	private int id;
	private int year;
	private float ganancias;
	@XmlIDREF
	private List<Event> eventosMasapostado;
	@XmlIDREF
	private List<Seleccion> seleccionesMasApostadas;
	@XmlIDREF
	private Usuario usuarioMasApostado;
	@XmlIDREF
	private Usuario usuarioMayorApuesta;
	@XmlIDREF
	private List<Usuario> listaNegra;

	/**
	 * public Estadistica(int year, Usuario u1, Usuario u2) { this.ganancias= 0;
	 * this.year=year; this.eventosMasapostado= new Vector<Event>();
	 * this.seleccionesMasApostadas= new Vector<Seleccion>();
	 * this.usuarioMasApostado=u1; this.usuarioMayorApuesta=u2;
	 * //this.apuestasMasApostadas= new Vector<Apuesta>(); this.listaNegra= new
	 * ArrayList<Usuario>(); }
	 */

	public Estadistica(int year) {
		this.ganancias = 0;
		this.year = year;
		this.eventosMasapostado = new ArrayList<>();
		this.seleccionesMasApostadas = new ArrayList<>();
		this.listaNegra = new ArrayList<>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * public int getMonth() { return month; }
	 * 
	 * 
	 * public void setMonth(int month) { this.month = month; }
	 */

	public float getGanancias() {
		return ganancias;
	}

	public void setGanancias(float ganancias) {
		this.ganancias = ganancias;
	}

	public List<Event> getEventosMasapostado() {
		return eventosMasapostado;
	}

	public void setEventosMasapostado(List<Event> eventosMasapostado) {
		this.eventosMasapostado = eventosMasapostado;
	}

	public List<Seleccion> getSeleccionesMasApostadas() {
		return seleccionesMasApostadas;
	}

	public void setSeleccionesMasApostadas(List<Seleccion> seleccionesMasApostadas) {
		this.seleccionesMasApostadas = seleccionesMasApostadas;
	}

	public Usuario getUsuarioMasApostado() {
		return usuarioMasApostado;
	}

	public void setUsuarioMasApostado(Usuario usuarioMasApostado) {
		this.usuarioMasApostado = usuarioMasApostado;
	}

	public Usuario getUsuarioMayorApuesta() {
		return usuarioMayorApuesta;
	}

	public void setUsuarioMayorApuesta(Usuario usuarioMayorApuesta) {
		this.usuarioMayorApuesta = usuarioMayorApuesta;
	}

	public List<Usuario> getListaNegra() {
		return listaNegra;
	}

	public void setListaNegra(List<Usuario> listaNegra) {
		this.listaNegra = listaNegra;
	}

	public void anadirUsuarioAListaNegra(Usuario u) {
		listaNegra.add(u);
	}

	public void eliminarDeListaNegra(Usuario u) {
		listaNegra.remove(u);

	}

}
