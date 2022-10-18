package domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Event implements Serializable {

	private static final long serialVersionUID = 1L;
	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@Id
	@GeneratedValue
	private Integer eventNumber;
	private String description;
	private Date eventDate;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private Vector<Question> questions = new Vector<>();
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private Vector<Apuesta> apuestas = new Vector<>();
	@XmlIDREF
	private Seleccion seleccion;
	private boolean finalizar;
	private int cuentaDeApuestas;

	public Event() {
		super();
	}

	public Event(String description, Date eventDate, Seleccion seleccion) {
		// jfsdjfthis.eventNumber = Id
		this.description = description;
		this.eventDate = eventDate;
		this.seleccion = seleccion;
		this.finalizar = false;
		this.cuentaDeApuestas = 0;
	}

	public Seleccion getSeleccion() {
		return seleccion;
	}

	public void setSeleccion(Seleccion seleccion) {
		this.seleccion = seleccion;
	}

	public Vector<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(Vector<Question> questions) {
		this.questions = questions;
	}

	public Integer getEventNumber() {
		return eventNumber;
	}

	public void setEventNumber(Integer eventNumber) {
		this.eventNumber = eventNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public boolean isFinalizar() {
		return finalizar;
	}

	public void setFinalizar(boolean finalizar) {
		this.finalizar = finalizar;
	}

	public String toString() {
		return eventNumber + ";" + description;
	}

	public List<Apuesta> getApuestas() {
		return apuestas;
	}

	public void setApuestas(Vector<Apuesta> apuestas) {
		this.apuestas = apuestas;
	}

	public int getCuentaDeApuestas() {
		return cuentaDeApuestas;
	}

	public void setCuentaDeApuestas(int cuentaDeApuestas) {
		this.cuentaDeApuestas = cuentaDeApuestas;
	}

	/**
	 * This method creates a bet with a question, minimum bet ammount and percentual
	 * profit
	 * 
	 * @param question   to be added to the event
	 * @param betMinimum of that question
	 * @return Bet
	 */
	public Question addQuestion(String question, float betMinimum) {
		Question q = new Question(question, betMinimum, this);
		questions.add(q);
		return q;
	}

	public Apuesta addApuesta(Usuario user, Pronostico respuesta, float apuesta) {
		Apuesta a = new Apuesta(user, respuesta, this, apuesta);
		apuestas.add(a);
		cuentaDeApuestas++;
		return a;
	}

	/**
	 * This method checks if the question already exists for that event
	 * 
	 * @param question that needs to be checked if there exists
	 * @return true if the question exists and false in other case
	 */
	public boolean DoesQuestionExists(String question) {
		for (Question q : this.getQuestions()) {
			if (q.getQuestion().compareTo(question) == 0)
				return true;
		}
		return false;
	}

	public boolean verificarPreguntas() {
		for (Question q : this.getQuestions()) {
			if (q.getResult() == null)
				return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + eventNumber;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		if (eventNumber != other.eventNumber)
			return false;
		return true;
		
	}

}
