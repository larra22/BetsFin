package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	private String correo;
	@Id
	private String nombreUsuario;
	private String password;
	private int numTarjeta;
	private float miMonedero;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private List<Apuesta> apuestas = new ArrayList<>();
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private Cupon cupon;
	private int cuentaDeApuestas;
	private int apuestasganadas;
	private float porcentajeGanancias;
	private boolean bloqueado;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private AnadirUsuarioParameter info;

	public Usuario(String correo, String nombreUsuario,
			String password, int numTarjeta, AnadirUsuarioParameter info) {
		super();
		this.correo = correo;
		this.nombreUsuario = nombreUsuario;
		this.password = password;
		this.numTarjeta = numTarjeta;
		this.miMonedero = (float) ThreadLocalRandom.current().nextDouble(10, 2000000);
		this.cuentaDeApuestas = 0;
		this.porcentajeGanancias = 0;
		this.apuestasganadas = 0;
		this.bloqueado = false;
		this.info=info;
	}

	public AnadirUsuarioParameter getInfo() {
		return info;
	}

	public void setInfo(AnadirUsuarioParameter info) {
		this.info = info;
	}

	public Usuario(String nombre, String password) {
		this.nombreUsuario = nombre;
		this.password = password;
	}


	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getNumTarjeta() {
		return numTarjeta;
	}

	public void setNumTarjeta(int numTarjeta) {
		this.numTarjeta = numTarjeta;
	}

	public float getMiMonedero() {
		return miMonedero;
	}

	public void setMiMonedero(float miMonedero) {
		this.miMonedero = miMonedero;
	}

	public List<Apuesta> getApuestas() {
		return apuestas;
	}

	public void setApuestas(List<Apuesta> apuestas) {
		this.apuestas = apuestas;
	}

	public Cupon getCupon() {
		return cupon;
	}

	public void setCupon(Cupon cupon) {
		this.cupon = cupon;
	}

	public int getCuentaDeApuestas() {
		return cuentaDeApuestas;
	}

	public void setCuentaDeApuestas(int cuentaDeApuestas) {
		this.cuentaDeApuestas = cuentaDeApuestas;
	}

	public float getPorcentajeGanancias() {
		return porcentajeGanancias;
	}

	public void setPorcentajeGanancias(float ganancias) {
		this.porcentajeGanancias = ganancias;
	}

	public boolean isBloqueado() {
		return bloqueado;
	}

	public void setBloqueado(boolean bloqueado) {
		this.bloqueado = bloqueado;
	}

	public int getApuestasganadas() {
		return apuestasganadas;
	}

	public void setApuestasganadas(int apuestasganadas) {
		this.apuestasganadas = apuestasganadas;
	}

	public Apuesta addApuesta(Pronostico respuesta, Event ev, float apuesta) {
		Apuesta a = new Apuesta(this, respuesta, ev, apuesta);
		apuestas.add(a);
		cuentaDeApuestas++;
		if (cuentaDeApuestas % 10 == 0) {
			float dinero = (float) (this.getCupon().getDineroAcumulado() + 5.0);
			this.getCupon().setDineroAcumulado(dinero);
			int cuponesobtenidos = this.getCupon().getNumCuponconseguido() + 1;
			this.getCupon().setNumCuponconseguido(cuponesobtenidos);
		}
		return a;
	}

	public Cupon addCupon() {
		Cupon c = new Cupon(this);
		this.setCupon(c);
		return c;
	}
	
	

}
