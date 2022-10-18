package businessLogic;

//hola
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.jws.WebMethod;
import javax.jws.WebService;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.Question;
import domain.Seleccion;
import domain.Subdito;
import domain.Usuario;
import domain.AnadirUsuarioParameter;
import domain.Apuesta;
import domain.Estadistica;
import domain.Event;
import domain.Mensaje;
import domain.Pronostico;
import exceptions.EventAlreadyExists;
import exceptions.EventFinished;
import exceptions.PronosticoAlreadyExists;
import exceptions.QuestionAlreadyExist;
import exceptions.ResultAlreadyExists;
import exceptions.UserAlreadyExists;
import exceptions.WrongUserOrPassword;

/**
 * It implements the business logic as a web service.
 */
@WebService(endpointInterface = "businessLogic.BLFacade")
public class BLFacadeImplementation implements BLFacade {
	DataAccess dbManager;

	public BLFacadeImplementation() {
		String initialize = "initialize";
		System.out.println("Creating BLFacadeImplementation instance");
		ConfigXML c = ConfigXML.getInstance();

		if (c.getDataBaseOpenMode().equals(initialize)) {
			dbManager = new DataAccess(c.getDataBaseOpenMode().equals(initialize));
			dbManager.initializeDB();
		} else
			dbManager = new DataAccess();
		dbManager.close();

	}

	public BLFacadeImplementation(DataAccess da) {

		System.out.println("Creating BLFacadeImplementation instance with DataAccess parameter");
		ConfigXML c = ConfigXML.getInstance();

		if (c.getDataBaseOpenMode().equals("initialize")) {
			da.open(true);
			da.initializeDB();
			da.close();

		}
		dbManager = da;
	}

	/**
	 * This method creates a question for an event, with a question text and the
	 * minimum bet
	 * 
	 * @param event      to which question is added
	 * @param question   text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws EventFinished        if current data is after data of the event
	 * @throws QuestionAlreadyExist if the same question already exists for the
	 *                              event
	 */
	@WebMethod
	public Question createQuestion(Event event, String question, float betMinimum)
			throws EventFinished, QuestionAlreadyExist {

		// The minimum bed must be greater than 0
		dbManager.open(false);
		Question qry = null;

		if (new Date().compareTo(event.getEventDate()) > 0)
			throw new EventFinished(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished"));

		qry = dbManager.createQuestion(event, question, betMinimum);

		dbManager.close();

		return qry;
	};

	/**
	 * This method invokes the data access to retrieve the events of a given date
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	@WebMethod
	public Vector<Event> getEvents(Date date) {
		dbManager.open(false);
		Vector<Event> events = dbManager.getEvents(date);
		dbManager.close();
		return events;
	}

	/**
	 * This method invokes the data access to retrieve the dates a month for which
	 * there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved
	 * @return collection of dates
	 */
	@WebMethod
	public Vector<Date> getEventsMonth(Date date) {
		dbManager.open(false);
		Vector<Date> dates = dbManager.getEventsMonth(date);
		dbManager.close();
		return dates;
	}

	public void close() {
		DataAccess dB4oManager = new DataAccess(false);
		dB4oManager.close();
	}

	/**
	 * This method invokes the data access to initialize the database with some
	 * events and questions. It is invoked only when the option initialize is
	 * declared in the tag dataBaseOpenMode of resources/config.xml file
	 */
	@WebMethod
	public void initializeBD() {
		dbManager.open(false);
		dbManager.initializeDB();
		dbManager.close();
	}
	
	@Override
	public AnadirUsuarioParameter crearInfoUsuario(String nombre, String apellido1, String apellido2, int telefono, Date fechaNac, String dNi) {
		dbManager.open(false);
		AnadirUsuarioParameter info = dbManager.crearInfoUsuario(nombre, apellido1, apellido2, telefono, fechaNac, dNi);
		dbManager.close();
		return info;
	}

	@Override
	public Usuario anadirUsuario(AnadirUsuarioParameter parameterObject, String nombreUsuario, String password, String correo, int tarjeta)
			throws UserAlreadyExists {

		dbManager.open(false);
		Usuario user = null;

		user = dbManager.anadirUsuario(parameterObject, nombreUsuario, password,correo,tarjeta );

		dbManager.close();

		return user;
	}

	@Override
	public Usuario comprobar(String nombreUsuario, String password) throws WrongUserOrPassword {
		dbManager.open(false);

		Usuario user = dbManager.comprobar(nombreUsuario, password);

		dbManager.close();
		return user;
	}

	@Override
	public boolean esSubdito(String nombreUsuario, String password) throws WrongUserOrPassword {
		dbManager.open(false);

		boolean b = dbManager.esSubdito(nombreUsuario, password);

		dbManager.close();
		return b;
	}

	@Override
	public boolean esAdmin(String nombreUsuario, String password) throws WrongUserOrPassword {
		dbManager.open(false);

		boolean b = dbManager.esAdmin(nombreUsuario, password);

		dbManager.close();
		return b;
	}

	@Override
	public Subdito contratarSubdito(String nombreUsuario, String password) throws UserAlreadyExists {
		dbManager.open(false);

		Subdito s = dbManager.contratarSubdito(nombreUsuario, password);

		dbManager.close();

		return s;
	}

	@Override
	public Apuesta anadirApuesta(Usuario user, Pronostico respuesta, float apuesta, String modoPago, Event ev,
			int year) {
		dbManager.open(false);
		Apuesta a = dbManager.anadirApuesta(modoPago, year, user, respuesta, apuesta, ev);
		dbManager.close();
		return a;
	}

	@Override
	public Event anadirEvent(String description, Date eDate, Seleccion s) throws EventAlreadyExists {
		dbManager.open(false);
		Event e = dbManager.anadirEvent(description, eDate, s);
		dbManager.close();
		return e;
	}

	@Override
	public Pronostico anadirPronostico(Question question, String sol, float porGanancia)
			throws PronosticoAlreadyExists {
		dbManager.open(false);
		Pronostico p = dbManager.anadirPronostico(question, sol, porGanancia);
		dbManager.close();
		return p;
	}

	@Override
	public int getNumberOfEvents() {
		dbManager.open(false);
		int cuantos = dbManager.getNumberOfEvents();
		return cuantos;
	}

	@Override
	public String anadirResultado(Question question, Pronostico pronostico) throws ResultAlreadyExists {
		dbManager.open(false);
		String s = dbManager.anadirResultado(question, pronostico);
		dbManager.close();
		return s;
	}

	public boolean cerrarEvento(Event event) {
		dbManager.open(false);
		boolean cerrar = dbManager.cerrarEvento(event);
		dbManager.close();
		return cerrar;
	}

	@Override
	public Vector<Usuario> getUsuariosGanadores(Pronostico pronos, int year) {
		dbManager.open(false);
		Vector<Usuario> u = dbManager.getUsuariosGanadores(pronos, year);
		dbManager.close();
		return u;
	}

	@Override
	public int getNumCuponesConseguido(Usuario u) {
		dbManager.open(false);
		int num = dbManager.getNumCuponesConseguido(u);
		dbManager.close();
		return num;
	}

	@Override
	public float getDineroAcumulado(Usuario u) {
		dbManager.open(false);
		float num = dbManager.getDineroAcumulado(u);
		dbManager.close();
		return num;
	}

	@Override
	public Seleccion anadirSeleccion(String dep, String gen, String sel) {
		dbManager.open(false);
		Seleccion seleccion = dbManager.anadirSeleccion(dep, gen, sel);
		dbManager.close();
		return seleccion;
	}

	@Override
	public float getMiMonedero(Usuario u) {
		dbManager.open(false);
		float num = dbManager.getMiMonedero(u);
		dbManager.close();
		return num;
	}

	@Override
	public Vector<Event> getEventsEspecified(Date date, Seleccion sel) {
		dbManager.open(false);
		Vector<Event> events = dbManager.getEventsEspecified(date, sel);
		dbManager.close();
		return events;
	}

	@Override
	public Vector<Date> getEventsMonthEspecified(Date date, Seleccion sel) {
		dbManager.open(false);
		Vector<Date> dates = dbManager.getEventsMonthEspecified(date, sel);
		dbManager.close();
		return dates;
	}

	@Override
	public Usuario usuarioMasApostado() {
		dbManager.open(false);
		Usuario top = dbManager.usuarioMasApostado();
		dbManager.close();
		return top;
	}

	@Override
	public Usuario usuarioMayorApuesta() {
		dbManager.open(false);
		Usuario top = dbManager.usuarioMayorApuesta();
		dbManager.close();
		return top;
	}

	@Override
	public Vector<Event> eventosMasApostados() {
		dbManager.open(false);
		Vector<Event> eventos = dbManager.eventosMasApostados();
		dbManager.close();
		return eventos;
	}

	@Override
	public Vector<Seleccion> seleccionesMasApostadas() {
		dbManager.open(false);
		Vector<Seleccion> selecciones = dbManager.seleccionesMasApostadas();
		dbManager.close();
		return selecciones;
	}

	@Override
	public Usuario anadirAListaNegra(Usuario user, int year) {
		dbManager.open(false);
		Usuario negro = dbManager.anadirAListaNegra(user, year);
		dbManager.close();
		return negro;
	}

	@Override
	public Usuario eliminarDeListaNegra(String user, int year) {
		dbManager.open(false);
		Usuario negro = dbManager.eliminarDeListaNegra(user, year);
		dbManager.close();
		return negro;
	}

	@Override
	public Estadistica crearEstadistica(int year) {
		dbManager.open(false);
		Estadistica est = dbManager.crearEstadistica(year);
		dbManager.close();
		return est;
	}

	@Override
	public Estadistica actualizarEstadistica(int year) {
		dbManager.open(false);
		Estadistica est = dbManager.actualizarEstadistica(year);
		dbManager.close();
		return est;
	}

	@Override
	public float conseguirGanancias(int year) {
		dbManager.open(false);
		float g = dbManager.conseguirGanancias(year);
		dbManager.close();
		return g;
	}

	@Override
	public List<Usuario> getListaNegra(int year) {
		dbManager.open(false);
		List<Usuario> ln = dbManager.getListaNegra(year);
		dbManager.close();
		return ln;
	}

	@Override
	public void bloquearUsuario(String user, int year) {
		dbManager.open(false);
		dbManager.bloquearUsuario(user, year);
		dbManager.close();
	}

	@Override
	public Vector<Mensaje> getMensajesAll() {
		dbManager.open(false);
		Vector<Mensaje> m = dbManager.getForoMensajes();
		dbManager.close();
		return m;
	}

	@Override
	public Mensaje guardarMensaje(Usuario user, String texto, Date date) {
		dbManager.open(false);
		Mensaje m = dbManager.guardarMensaje(user, texto, date);
		dbManager.close();

		return m;

	}

	@Override
	public int dadoMegusta(Mensaje m) {
		dbManager.open(false);
		Integer a = dbManager.dadoMegusta(m);
		dbManager.close();

		return a;
	}

	@Override
	public Vector<Mensaje> filtrarPorUsuario(String nombreUsuario) {
		dbManager.open(false);
		Vector<Mensaje> a = dbManager.filtrarPorUsuario(nombreUsuario);
		dbManager.close();
		return a;
	}

}
