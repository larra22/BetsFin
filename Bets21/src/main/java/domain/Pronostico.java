package domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * @author Propietario
 *
 */
@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Pronostico implements Serializable {

	@Id
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@GeneratedValue
	private Integer numPronostico;
	@XmlIDREF
	private Question question;

	private String solucion;
	private float porcentageGanancia;

	public Pronostico() {
		super();
	}

	public Pronostico(Integer num, Question q, String solucion, float porGanancia) {
		super();
		this.numPronostico = num;
		this.question = q;
		this.solucion = solucion;
		this.porcentageGanancia = porGanancia;
	}

	public Pronostico(Question q, String solucion, float porGanancia) {
		super();
		this.question = q;
		this.solucion = solucion;
		this.porcentageGanancia = porGanancia;
	}

	public Integer getNumPronostico() {
		return numPronostico;
	}

	public void setNumPronostico(Integer numPronostico) {
		this.numPronostico = numPronostico;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public String getSolucion() {
		return solucion;
	}

	public void setSolucion(String solucion) {
		this.solucion = solucion;
	}

	public float getPorcentageGanancia() {
		return porcentageGanancia;
	}

	public void setPorcentageGanancia(float porcentageGanancia) {
		this.porcentageGanancia = porcentageGanancia;
	}

	@Override
	public String toString() {
		return "Pronostico [numPronostico=" + numPronostico + ", question=" + question + ", solucion=" + solucion
				+ ", porcentageGanancia=" + Float.toString(porcentageGanancia) + "]";
	}

}
