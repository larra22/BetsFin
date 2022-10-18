package domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Mensaje implements Serializable {
	private static final long serialVersionUID = 1L;
	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@Id
	@GeneratedValue
	private Integer mensajeNumber;

	private Usuario dueno;
	private Usuario receptor; // Sera opcional ya que el mensaje puede ser tb para todos los usuarios
	// Solo habra receptor cuando un usuario responda a otro concretamente
	private String texto;

	private int megustas;
	private boolean dado;
	private Date date;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private List<Mensaje> respuestas = new ArrayList<>();
	
	

	public List<Mensaje> getRespuestas() {
		return respuestas;
	}

	public void setRespuestas(List<Mensaje> respuestas) {
		this.respuestas = respuestas;
	}

	public boolean isDado() {
		return dado;
	}

	public void setDado(boolean dado) {
		this.dado = dado;
	}

	public int getMegustas() {
		return megustas;
	}

	public void setMegustas(int megustas) {
		this.megustas = megustas;
	}

	/// OTRO ATRIBUTO PODRIA SER CORREALACIONARLO CON LAS RESPUENTAS AL MENSAJE
	/// PRINCIPAL

	public Integer getMensajeNumber() {
		return mensajeNumber;
	}

	public void setMensajeNumber(Integer mensajeNumber) {
		this.mensajeNumber = mensajeNumber;
	}

	public Mensaje(Usuario due, String texto, Date date) {
		this.dueno = due;
		this.texto = texto;
		this.date = date;
		this.megustas = 0;
		this.dado = false;
	}

	public Usuario getdueno() {
		return dueno;
	}

	public void setdueno(Usuario dueno) {
		this.dueno = dueno;
	}

	public Usuario getReceptor() {
		return receptor;
	}

	public void setReceptor(Usuario receptor) {
		this.receptor = receptor;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return dueno.getNombreUsuario() + "                   " + date + "\n" + texto;
	}

	public String fechaBonita(Date fecha) {
		// En esta linea de codigo estamos indicando el nuevo formato que queremos para
		// nuestra fecha.
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
		// Aqui usamos la instancia formatter para darle el formato a la fecha. Es
		// importante ver que el resultado es un string.
		return formatter.format(fecha);
	}

	public List<Mensaje> addMensaje(Mensaje men) {

		respuestas.add(men);
		return respuestas;
	}
}