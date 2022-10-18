package dataAccess;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import configuration.ConfigXML;
import configuration.UtilDate;
import domain.Admin;
import domain.AnadirUsuarioParameter;
import domain.Apuesta;
import domain.Cupon;
import domain.Estadistica;
import domain.Event;
import domain.Mensaje;
import domain.Pronostico;
import domain.Question;
import domain.Seleccion;
import domain.Subdito;
import domain.Usuario;
import exceptions.EventAlreadyExists;
import exceptions.PronosticoAlreadyExists;
import exceptions.QuestionAlreadyExist;
import exceptions.ResultAlreadyExists;
import exceptions.UserAlreadyExists;
import exceptions.WrongUserOrPassword;

/**
 * It implements the data access to the objectDb database
 */
public class DataAccess {
	Admin admin = Admin.getInstance();
	protected static EntityManager db;
	protected static EntityManagerFactory emf;

	ConfigXML c = ConfigXML.getInstance();

	public DataAccess(boolean initializeMode) {

		System.out.println("Creating DataAccess instance => isDatabaseLocal: " + c.isDatabaseLocal()
				+ " getDatabBaseOpenMode: " + c.getDataBaseOpenMode());

		open(initializeMode);

	}

	public DataAccess() {
		new DataAccess(false);
	}

	/**
	 * This is the data access method that initializes the database with some events
	 * and questions. This method is invoked by the business logic (constructor of
	 * BLFacadeImplementation) when the option "initialize" is declared in the tag
	 * dataBaseOpenMode of resources/config.xml file
	 */
	public void initializeDB() {

		db.getTransaction().begin();

		try {

			db.persist(admin);
			Calendar today = Calendar.getInstance();

			int month = today.get(Calendar.MONTH);
			month += 1;
			int year = today.get(Calendar.YEAR);
			if (month == 12) {
				month = 0;
				year += 1;
			}

			Seleccion s1 = new Seleccion("Futbol", "M", "Champions League");

			// Estadistica est1=new Estadistica(YearMonth.of(year,month))
			// db.persist(est1)
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			Date fechaNacimiento = formato.parse("05/05/1998");
			AnadirUsuarioParameter info= new AnadirUsuarioParameter("Kim", "Posible", "Posible", 54654564,fechaNacimiento,"63546738H");
			Usuario u1 = new Usuario( "hola@correo", "kimposible",  "llama", 51566565, info);
			u1.addCupon();
			AnadirUsuarioParameter info2 = new AnadirUsuarioParameter("Manolo", "Gil", "Fernandez", 54654564, fechaNacimiento,
					"63546738H");
			Usuario u2 = new Usuario("correo@correo","manolito64", "deOro", 51566565, info2);
			u2.addCupon();

			Event ev1 = s1.addEvent("Atlético-Athletic", UtilDate.newDate(year, month, 17));
			Event ev2 = s1.addEvent("Eibar-Barcelona", UtilDate.newDate(year, month, 17));
			Event ev3 = s1.addEvent("Getafe-Celta", UtilDate.newDate(year, month, 17));
			Event ev4 = s1.addEvent("Alavés-Deportivo", UtilDate.newDate(year, month, 17));
			Event ev5 = s1.addEvent("Español-Villareal", UtilDate.newDate(year, month, 17));
			Event ev6 = s1.addEvent("Las Palmas-Sevilla", UtilDate.newDate(year, month, 17));
			Event ev7 = s1.addEvent("Malaga-Valencia", UtilDate.newDate(year, month, 17));
			Event ev8 = s1.addEvent("Girona-Leganés", UtilDate.newDate(year, month, 17));
			Event ev9 = s1.addEvent("Real Sociedad-Levante", UtilDate.newDate(year, month, 17));
			Event ev10 = s1.addEvent("Btis-Real Madrid", UtilDate.newDate(year, month, 17));

			Event ev11 = s1.addEvent("Atletico-Athletic", UtilDate.newDate(year, month, 1));
			Event ev12 = s1.addEvent("Eibar-Barcelona", UtilDate.newDate(year, month, 1));
			Event ev13 = s1.addEvent("Getafe-Celta", UtilDate.newDate(year, month, 1));
			Event ev14 = s1.addEvent("Alavés-Deportivo", UtilDate.newDate(year, month, 1));
			Event ev15 = s1.addEvent("Español-Villareal", UtilDate.newDate(year, month, 1));
			Event ev16 = s1.addEvent("Las Palmas-Sevilla", UtilDate.newDate(year, month, 1));

			Event ev17 = s1.addEvent("Málaga-Valencia", UtilDate.newDate(year, month + 1, 28));
			Event ev18 = s1.addEvent("Girona-Leganés", UtilDate.newDate(year, month + 1, 28));
			Event ev19 = s1.addEvent("Real Sociedad-Levante", UtilDate.newDate(year, month + 1, 28));
			Event ev20 = s1.addEvent("Betis-Real Madrid", UtilDate.newDate(year, month + 1, 28));

			Question q1;
			Question q2;
			Question q3;
			Question q4;
			Question q5;
			Question q6;

			if (Locale.getDefault().equals(new Locale("es"))) {
				q1 = ev1.addQuestion("¿Quién ganará el partido?", 1);
				q2 = ev1.addQuestion("¿Quién meterá el primer gol?", 2);
				q3 = ev11.addQuestion("¿Quién ganará el partido?", 1);
				q4 = ev11.addQuestion("¿Cuántos goles se marcarán?", 2);
				q5 = ev17.addQuestion("¿Quién ganará el partido?", 1);
				q6 = ev17.addQuestion("¿Habrá goles en la primera parte?", 2);
			} else if (Locale.getDefault().equals(new Locale("en"))) {
				q1 = ev1.addQuestion("Who will win the match?", 1);
				q2 = ev1.addQuestion("Who will score first?", 2);
				q3 = ev11.addQuestion("Who will win the match?", 1);
				q4 = ev11.addQuestion("How many goals will be scored in the match?", 2);
				q5 = ev17.addQuestion("Who will win the match?", 1);
				q6 = ev17.addQuestion("Will there be goals in the first half?", 2);
			} else {
				q1 = ev1.addQuestion("Zeinek irabaziko du partidua?", 1);
				q2 = ev1.addQuestion("Zeinek sartuko du lehenengo gola?", 2);
				q3 = ev11.addQuestion("Zeinek irabaziko du partidua?", 1);
				q4 = ev11.addQuestion("Zenbat gol sartuko dira?", 2);
				q5 = ev17.addQuestion("Zeinek irabaziko du partidua?", 1);
				q6 = ev17.addQuestion("Golak sartuko dira lehenengo zatian?", 2);

			}

			db.persist(u1);
			db.persist(u2);

			db.persist(s1);
			db.persist(q1);
			db.persist(q2);
			db.persist(q3);
			db.persist(q4);
			db.persist(q5);
			db.persist(q6);

			db.persist(ev1);
			db.persist(ev2);
			db.persist(ev3);
			db.persist(ev4);
			db.persist(ev5);
			db.persist(ev6);
			db.persist(ev7);
			db.persist(ev8);
			db.persist(ev9);
			db.persist(ev10);
			db.persist(ev11);
			db.persist(ev12);
			db.persist(ev13);
			db.persist(ev14);
			db.persist(ev15);
			db.persist(ev16);
			db.persist(ev17);
			db.persist(ev18);
			db.persist(ev19);
			db.persist(ev20);

			db.getTransaction().commit();
			System.out.println("Db initialized");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method creates a question for an event, with a question text and the
	 * minimum bet
	 * 
	 * @param event      to which question is added
	 * @param question   text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws QuestionAlreadyExist if the same question already exists for the
	 *                              event
	 */
	public Question createQuestion(Event event, String question, float betMinimum) throws QuestionAlreadyExist {
		System.out.println(">> DataAccess: createQuestion=> event= " + event + " question= " + question + " betMinimum="
				+ betMinimum);

		Event ev = db.find(Event.class, event.getEventNumber());

		if (ev.DoesQuestionExists(question))
			throw new QuestionAlreadyExist(ResourceBundle.getBundle("Etiquetas").getString("ErrorQueryAlreadyExist"));

		db.getTransaction().begin();
		Question q = ev.addQuestion(question, betMinimum);
		db.persist(ev); // db.persist(q) not required when CascadeType.PERSIST is added in questions
						// property of Event class
						// @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
		db.getTransaction().commit();
		return q;

	}

	/**
	 * This method retrieves from the database the events of a given date
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	public Vector<Event> getEvents(Date date) {
		System.out.println(">> DataAccess: getEvents");
		Vector<Event> res = new Vector<Event>();
		TypedQuery<Event> query = db.createQuery("SELECT ev FROM Event ev WHERE ev.eventDate=?1 AND ev.finalizar=?2",
				Event.class);
		query.setParameter(1, date);
		query.setParameter(2, false);
		List<Event> events = query.getResultList();
		for (Event ev : events) {
			System.out.println(ev.toString());
			res.add(ev);
		}
		return res;
	}

	public Vector<Event> getEventsEspecified(Date date, Seleccion sel) {
		System.out.println(">> DataAccess: getEvents");
		Vector<Event> res = new Vector<Event>();
		TypedQuery<Event> query = db.createQuery(
				"SELECT ev FROM Event ev WHERE ev.eventDate=?1 AND ev.finalizar=?2 AND ev.seleccion=?3", Event.class);
		query.setParameter(1, date);
		query.setParameter(2, false);
		query.setParameter(3, sel);
		List<Event> events = query.getResultList();
		for (Event ev : events) {
			System.out.println(ev.toString());
			res.add(ev);
		}
		return res;
	}

	/**
	 * This method retrieves from the database the dates a month for which there are
	 * events
	 * 
	 * @param date of the month for which days with events want to be retrieved
	 * @return collection of dates
	 */
	public Vector<Date> getEventsMonth(Date date) {
		System.out.println(">> DataAccess: getEventsMonth");
		Vector<Date> res = new Vector<Date>();

		Date firstDayMonthDate = UtilDate.firstDayMonth(date);
		Date lastDayMonthDate = UtilDate.lastDayMonth(date);

		TypedQuery<Date> query = db.createQuery(
				"SELECT DISTINCT ev.eventDate FROM Event ev WHERE ev.eventDate BETWEEN ?1 and ?2 AND ev.finalizar=?3",
				Date.class);
		query.setParameter(1, firstDayMonthDate);
		query.setParameter(2, lastDayMonthDate);
		query.setParameter(3, false);
		List<Date> dates = query.getResultList();
		for (Date d : dates) {
			System.out.println(d.toString());
			res.add(d);
		}
		return res;
	}

	public Vector<Date> getEventsMonthEspecified(Date date, Seleccion sel) {
		System.out.println(">> DataAccess: getEventsMonth");
		Vector<Date> res = new Vector<Date>();

		Date firstDayMonthDate = UtilDate.firstDayMonth(date);
		Date lastDayMonthDate = UtilDate.lastDayMonth(date);

		TypedQuery<Date> query = db.createQuery(
				"SELECT DISTINCT ev.eventDate FROM Event ev WHERE ev.eventDate BETWEEN ?1 and ?2 AND ev.finalizar=?3 AND ev.seleccion=?4",
				Date.class);
		query.setParameter(1, firstDayMonthDate);
		query.setParameter(2, lastDayMonthDate);
		query.setParameter(3, false);
		query.setParameter(4, sel);
		List<Date> dates = query.getResultList();
		for (Date d : dates) {
			System.out.println(d.toString());
			res.add(d);
		}
		return res;
	}

	public void open(boolean initializeMode) {

		System.out.println("Opening DataAccess instance => isDatabaseLocal: " + c.isDatabaseLocal()
				+ " getDatabBaseOpenMode: " + c.getDataBaseOpenMode());

		String fileName = c.getDbFilename();
		if (initializeMode) {
			fileName = fileName + ";drop";
			System.out.println("Deleting the DataBase");
		}

		if (c.isDatabaseLocal()) {
			emf = Persistence.createEntityManagerFactory("objectdb:" + fileName);
			db = emf.createEntityManager();
		} else {
			Map<String, String> properties = new HashMap<String, String>();
			properties.put("javax.persistence.jdbc.user", c.getUser());
			properties.put("javax.persistence.jdbc.password", c.getPassword());

			emf = Persistence.createEntityManagerFactory(
					"objectdb://" + c.getDatabaseNode() + ":" + c.getDatabasePort() + "/" + fileName, properties);

			db = emf.createEntityManager();
		}

	}

	public boolean existQuestion(Event event, String question) {
		System.out.println(">> DataAccess: existQuestion=> event= " + event + " question= " + question);
		Event ev = db.find(Event.class, event.getEventNumber());
		return ev.DoesQuestionExists(question);

	}
	
	public AnadirUsuarioParameter crearInfoUsuario(String nombre, String apellido1, String apellido2, int telefono, Date fechaNac, String dNi) {
		return new AnadirUsuarioParameter(nombre, apellido1, apellido2, telefono, fechaNac, dNi);
		
		
	}

	public Usuario anadirUsuario(AnadirUsuarioParameter parameterObject, String nombreUsuario, String password, String correo, int tarjeta)
			throws UserAlreadyExists {
		System.out.println(">> DataAccess: Registro=> nombreUsuario= " + nombreUsuario);

		Usuario us = db.find(Usuario.class, nombreUsuario);
		Subdito subd = db.find(Subdito.class, nombreUsuario);

		if (us != null || subd != null)
			throw new UserAlreadyExists(ResourceBundle.getBundle("Etiquetas").getString("UserAlreadyExists"));

		db.getTransaction().begin();
		
		Usuario u = new Usuario(correo, nombreUsuario, password, tarjeta, parameterObject );
		u.addCupon();
		db.persist(u);
		db.getTransaction().commit();
		return u;
	}

	public Usuario comprobar(String nombreUsuario, String password) throws WrongUserOrPassword {

		System.out.println(">> DataAccess: Login=> nombreUsuario= " + nombreUsuario + " contrase�a= " + password);

		Usuario user = db.find(Usuario.class, nombreUsuario);

		if (user == null)
			throw new WrongUserOrPassword(ResourceBundle.getBundle("Etiquetas").getString("UserPassWordIsWrong"));
		else {
			String passW = user.getPassword();
			if (!passW.equals(password))
				throw new WrongUserOrPassword(ResourceBundle.getBundle("Etiquetas").getString("UserPassWordIsWrong"));
		}
		return user;

	}

	public boolean esSubdito(String nombreUsuario, String password) throws WrongUserOrPassword {
		boolean b = true;
		System.out.println(">> DataAccess: Login=> nombreUsuario= " + nombreUsuario + " contrase�a= " + password);

		Subdito sub = db.find(Subdito.class, nombreUsuario);

		if (sub == null) {
			b = false;
		} else {
			String passW = sub.getPassword();
			if (!passW.equals(password)) {
				b = false;
				throw new WrongUserOrPassword(ResourceBundle.getBundle("Etiquetas").getString("UserPassWordIsWrong"));
			}
		}
		if (b == true) {
			return true;
		} else {
			return false;
		}
	}

	public boolean esAdmin(String nombreUsuario, String password) throws WrongUserOrPassword {
		boolean b = true;
		System.out.println(">> DataAccess: Login=> nombreUsuario= " + nombreUsuario + " contrase�a= " + password);

		Admin a = db.find(Admin.class, nombreUsuario);

		if (a == null) {
			b = false;
		} else {

			String passW = a.getPassword();
			if (!passW.equals(password)) {
				b = false;
				throw new WrongUserOrPassword(ResourceBundle.getBundle("Etiquetas").getString("UserPassWordIsWrong"));
			}
		}
		return b;
	}

	public Subdito contratarSubdito(String nombreUsuario, String password) throws UserAlreadyExists {

		System.out.println(">> DataAccess: Registro=> nombreUsuario= " + nombreUsuario);

		Usuario us = db.find(Usuario.class, nombreUsuario);
		Subdito subd = db.find(Subdito.class, nombreUsuario);

		if (us != null || subd != null)
			throw new UserAlreadyExists(ResourceBundle.getBundle("Etiquetas").getString("UserAlreadyExists"));

		db.getTransaction().begin();

		Subdito s = new Subdito(nombreUsuario, password);

		db.persist(s);
		db.getTransaction().commit();
		return s;

	}

	public Event anadirEvent(String description, Date eDate, Seleccion s) throws EventAlreadyExists {
		System.out.println(">> DataAccess: CreateEvent=> Description= " + description + " Date= " + eDate);

		TypedQuery<Event> query = db.createQuery(
				"SELECT ev FROM Event ev WHERE ev.description=?1 AND ev.eventDate=?2 AND ev.seleccion=?3", Event.class);
		query.setParameter(1, description);
		query.setParameter(2, eDate);
		query.setParameter(3, s);
		List<Event> events = query.getResultList();
		if (!events.isEmpty())
			throw new EventAlreadyExists(ResourceBundle.getBundle("Etiquetas").getString("EventAlreadyExists"));
		Seleccion selec = db.find(Seleccion.class, s.getId());

		db.getTransaction().begin();
		Event e = selec.addEvent(description, eDate);

		db.persist(selec);
		db.getTransaction().commit();
		return e;
	}

	public Pronostico anadirPronostico(Question question, String sol, float porGanancia)
			throws PronosticoAlreadyExists {
		System.out.println(">> DataAccess: CreatePronostico=> Question= " + question + " pronostico= " + sol
				+ " porcentageGanancia= " + porGanancia);

		Question q = db.find(Question.class, question.getQuestionNumber());

		if (q.DoesPronosticoExists(sol))
			throw new PronosticoAlreadyExists(
					ResourceBundle.getBundle("Etiquetas").getString("PronosticoAlreadyExists"));

		db.getTransaction().begin();
		Pronostico p = q.addPronostico(sol, porGanancia);
		db.persist(q);
		db.getTransaction().commit();
		return p;
	}

	public boolean existPronostico(Question quest, String pronostico) {
		System.out.println(">> DataAccess: existPronostico=> Question= " + quest + " pronostico= " + pronostico);
		Question q = db.find(Question.class, quest.getQuestionNumber());
		return q.DoesPronosticoExists(pronostico);
	}

	public int getNumberOfEvents() {
		TypedQuery<Event> query = db.createQuery("SELECT e FROM Event e", Event.class);
		List<Event> events = query.getResultList();
		return events.size();
	}

	public Apuesta anadirApuesta(String modoPago, int year, Usuario user, Pronostico respuesta, float apuesta,
			Event ev) {
		System.out.println(">> DataAccess: CreateApuesta=> Usuario= " + user + " pronostico= " + respuesta
				+ " apuesta= " + apuesta);

		Usuario u = db.find(Usuario.class, user.getNombreUsuario());
		Cupon cupon = u.getCupon();
		Cupon cup = db.find(Cupon.class, cupon.getUser());
		Event e = db.find(Event.class, ev.getEventNumber());
		Seleccion sel = db.find(Seleccion.class, e.getSeleccion().getId());

		TypedQuery<Estadistica> query = db.createQuery("SELECT e FROM Estadistica e WHERE e.year=?1",
				Estadistica.class);
		query.setParameter(1, year);
		List<Estadistica> estadistica = query.getResultList();
		Estadistica esta = estadistica.get(0);
		if (modoPago.equals("tarjeta")) {
			float dineroempresa = esta.getGanancias() + apuesta;
			esta.setGanancias(dineroempresa);

			float dinerouser = u.getMiMonedero() - apuesta;
			u.setMiMonedero(dinerouser);
		} else {
			float dineroCupon = cup.getDineroAcumulado() - apuesta;
			cup.setDineroAcumulado(dineroCupon);
		}

		db.getTransaction().begin();
		Apuesta a = u.addApuesta(respuesta, ev, apuesta);
		e.addApuesta(user, respuesta, apuesta);
		sel.setCuentaDeApuestas(sel.getCuentaDeApuestas() + 1);
		db.persist(esta);
		db.persist(e);
		db.persist(u);
		db.getTransaction().commit();
		return a;
	}

	public String anadirResultado(Question question, Pronostico pronostico) throws ResultAlreadyExists {
		System.out.println(">> DataAccess: A�adirResultado=> Question= " + question + " pronostico= " + pronostico);

		Question q = db.find(Question.class, question.getQuestionNumber());
		Pronostico p = db.find(Pronostico.class, pronostico.getNumPronostico());

		if (q.getResult() != null)
			throw new ResultAlreadyExists(ResourceBundle.getBundle("Etiquetas").getString("ResultAlreadyExists"));

		db.getTransaction().begin();

		q.setResult(p.getSolucion());

		db.persist(q);

		db.getTransaction().commit();
		return pronostico.getSolucion();
	}

	public boolean cerrarEvento(Event event) {
		System.out.println(">> DataAccess: cerrarEvento=> Event= " + event);

		Event e = db.find(Event.class, event.getEventNumber());

		db.getTransaction().begin();

		boolean cerrar = e.verificarPreguntas();
		if (cerrar) {
			e.setFinalizar(true);
			db.persist(e);
		}

		db.getTransaction().commit();
		return cerrar;
	}

	public Vector<Usuario> getUsuariosGanadores(Pronostico pronos, int year) {
		System.out.println(">> DataAccess: getUsuariosGanadores");

		db.getTransaction().begin();

		Vector<Usuario> ganadores = new Vector<Usuario>();
		TypedQuery<Apuesta> query = db.createQuery("SELECT a FROM Apuesta a WHERE a.respuesta=?1", Apuesta.class);
		query.setParameter(1, pronos);
		List<Apuesta> apuestas = query.getResultList();

		TypedQuery<Estadistica> queryest = db.createQuery("SELECT e FROM Estadistica e WHERE e.year=?1",
				Estadistica.class);
		queryest.setParameter(1, year);
		List<Estadistica> estadistica = queryest.getResultList();
		Estadistica est = estadistica.get(0);

		float totalpagado = 0;

		for (Apuesta a : apuestas) {
			Usuario ganador = a.getUser();
			Usuario u = db.find(Usuario.class, ganador.getNombreUsuario());

			float gana = a.getApuesta() + a.getApuesta() * pronos.getPorcentageGanancia();
			totalpagado = totalpagado + gana;
			System.out.println(u.getNombreUsuario() + " ha ganado: " + gana);
			float tenia = u.getMiMonedero();
			u.setMiMonedero(tenia + gana);

			u.setApuestasganadas(u.getApuestasganadas() + 1);
			u.setPorcentajeGanancias((float) ((u.getApuestasganadas() * 100.0) / u.getCuentaDeApuestas()));

			if (u.getCuentaDeApuestas() >= 100 && u.getPorcentajeGanancias() >= 95) {
				est.anadirUsuarioAListaNegra(u);
			}

			db.persist(u);
			ganadores.add(ganador);
		}
		est.setGanancias(est.getGanancias() - totalpagado);
		db.persist(est);

		db.getTransaction().commit();

		return ganadores;
	}

	/**
	 * public void restarNuestrasGanancias(int dateYear, float totalpagado) {
	 * TypedQuery<Estadistica> query = db.createQuery("SELECT e FROM Estadistica e
	 * WHERE e.year=?1",Estadistica.class); query.setParameter(1, dateYear);
	 * Estadistica est= query.getResultList().get(0);
	 * 
	 * db.getTransaction().begin();
	 * 
	 * est.setGanancias(est.getGanancias()-totalpagado); db.persist(est);
	 * 
	 * db.getTransaction().commit(); }
	 **/

	public int getNumCuponesConseguido(Usuario u) {
		System.out.println(">> DataAccess: getNumCuponesConseguido");

		Usuario user = db.find(Usuario.class, u.getNombreUsuario());
		Cupon c = db.find(Cupon.class, user);

		db.getTransaction().begin();

		int num = c.getNumCuponconseguido();
		System.out.println(num);

		db.getTransaction().commit();

		return num;
	}

	public float getDineroAcumulado(Usuario u) {
		System.out.println(">> DataAccess: getNumCuponesConseguido");

		Usuario user = db.find(Usuario.class, u.getNombreUsuario());
		Cupon c = db.find(Cupon.class, user);

		db.getTransaction().begin();

		float num = c.getDineroAcumulado();
		System.out.println(num);

		db.getTransaction().commit();

		return num;
	}

	public float getMiMonedero(Usuario u) {
		System.out.println(">> DataAccess: getNumCuponesConseguido");

		Usuario user = db.find(Usuario.class, u.getNombreUsuario());

		db.getTransaction().begin();

		float num = user.getMiMonedero();
		System.out.println(num);

		db.getTransaction().commit();

		return num;
	}

	public Seleccion anadirSeleccion(String dep, String gen, String sel) {
		System.out.println(">> DataAccess: CreateSelection=> Sport= " + dep + " Gender= " + gen + " Selection= " + sel);

		TypedQuery<Seleccion> query = db.createQuery(
				"SELECT s FROM Seleccion s WHERE s.deporte=?1 AND s.genero=?2 AND s.seleccion=?3", Seleccion.class);
		query.setParameter(1, dep);
		query.setParameter(2, gen);
		query.setParameter(3, sel);
		List<Seleccion> selecciones = query.getResultList();
		if (!selecciones.isEmpty()) {
			return selecciones.get(0);
		} else {
			db.getTransaction().begin();
			Seleccion s = new Seleccion(dep, gen, sel);

			db.persist(s);
			db.getTransaction().commit();
			return s;
		}
	}

	public Usuario usuarioMasApostado() {
		System.out.println(">> DataAccess: usuarioMasApostado");
		TypedQuery<Usuario> query = db.createQuery(
				"SELECT u FROM Usuario u WHERE u.bloqueado=?1 ORDER BY u.cuentaDeApuestas DESC", Usuario.class);
		query.setParameter(1, false);
		Usuario top = query.getResultList().get(0);
		return top;
	}

	public Usuario usuarioMayorApuesta() {
		System.out.println(">> DataAccess: usuarioMayorApuesta");
		TypedQuery<Apuesta> query = db.createQuery("SELECT a FROM Apuesta a ORDER BY a.apuesta DESC", Apuesta.class);
		List<Apuesta> aTop = query.getResultList();
		int i = 0;
		boolean encontrado = false;
		Usuario top = null;
		if (!aTop.isEmpty()) {

			while (i < aTop.size() && !encontrado) {
				if (!aTop.get(i).getUser().isBloqueado()) {
					encontrado = true;
					top = aTop.get(i).getUser();
				}
				i++;

			}
			return top;
		} else {
			return null;
		}
	}

	public Vector<Event> eventosMasApostados() {
		System.out.println(">> DataAccess: eventosMasApostados");

		Vector<Event> tops = new Vector<Event>();
		TypedQuery<Event> query = db.createQuery("SELECT e FROM Event e ORDER BY e.cuentaDeApuestas DESC", Event.class);
		List<Event> masApostados = query.getResultList();

		int i = 0;
		while (i < masApostados.size() && i < 5) {
			tops.add(masApostados.get(i));
			i++;
		}
		return tops;
	}

	public Vector<Seleccion> seleccionesMasApostadas() {
		System.out.println(">> DataAccess: seleccionesMasApostadas");

		Vector<Seleccion> tops = new Vector<Seleccion>();
		TypedQuery<Seleccion> query = db.createQuery("SELECT s FROM Seleccion s ORDER BY s.cuentaDeApuestas DESC",
				Seleccion.class);
		List<Seleccion> masApostados = query.getResultList();

		int i = 0;
		while (i < masApostados.size() && i < 5) {
			tops.add(masApostados.get(i));
			i++;
		}
		return tops;
	}

	public float conseguirGanancias(int year) {
		System.out.println(">> DataAccess: conseguirGanancias");

		db.getTransaction().begin();

		TypedQuery<Estadistica> query = db.createQuery("SELECT e FROM Estadistica e WHERE e.year=?1",
				Estadistica.class);
		query.setParameter(1, year);
		List<Estadistica> estadistica = query.getResultList();
		Estadistica esta = estadistica.get(0);

		float ganancias = esta.getGanancias();
		System.out.println(ganancias);
		db.getTransaction().commit();
		return ganancias;
	}

	public Estadistica crearEstadistica(int year) {
		System.out.println(">> DataAccess: crearEstadistica");

		TypedQuery<Estadistica> query = db.createQuery("SELECT e FROM Estadistica e WHERE e.year=?1",
				Estadistica.class);
		query.setParameter(1, year);
		List<Estadistica> estadistica = query.getResultList();

		if (estadistica.isEmpty()) {
			db.getTransaction().begin();
			Estadistica esta = new Estadistica(year);
			db.persist(esta);
			db.getTransaction().commit();
			return esta;
		} else {
			Estadistica esta = estadistica.get(0);
			return esta;
		}
	}

	public Estadistica actualizarEstadistica(int year) {
		System.out.println(">> DataAccess: crearOActualizarEstadistica");

		TypedQuery<Estadistica> query = db.createQuery("SELECT e FROM Estadistica e WHERE e.year=?1",
				Estadistica.class);
		query.setParameter(1, year);
		List<Estadistica> estadistica = query.getResultList();

		Estadistica esta = estadistica.get(0);

		Usuario uMayorApuesta = usuarioMayorApuesta();
		Usuario uMasApostado = usuarioMasApostado();
		Vector<Event> eMasApostado = eventosMasApostados();
		Vector<Seleccion> sMasApostado = seleccionesMasApostadas();
		db.getTransaction().begin();

		extractEstadistica(esta, uMayorApuesta, uMasApostado, eMasApostado, sMasApostado);

		db.persist(esta);
		db.getTransaction().commit();

		return esta;

	}

	private void extractEstadistica(Estadistica esta, Usuario uMayorApuesta, Usuario uMasApostado,
			Vector<Event> eMasApostado, Vector<Seleccion> sMasApostado) {
		esta.setEventosMasapostado(eMasApostado);
		esta.setUsuarioMayorApuesta(uMayorApuesta);
		esta.setUsuarioMasApostado(uMasApostado);
		esta.setEventosMasapostado(eMasApostado);
		esta.setSeleccionesMasApostadas(sMasApostado);
	}

	public Usuario anadirAListaNegra(Usuario user, int year) {
		System.out.println(">> DataAccess: anadirAListaNegra=> Usuario= " + user.getNombreUsuario());

		Usuario u = db.find(Usuario.class, user.getNombreUsuario());

		TypedQuery<Estadistica> query = db.createQuery("SELECT e FROM Estadistica e WHERE e.year=?1",
				Estadistica.class);
		query.setParameter(1, year);
		List<Estadistica> estadistica = query.getResultList();
		Estadistica esta = estadistica.get(0);

		db.getTransaction().begin();
		esta.anadirUsuarioAListaNegra(u);
		db.persist(esta);
		db.getTransaction().commit();

		return u;
	}

	public Usuario eliminarDeListaNegra(String username, int year) {
		System.out.println(">> DataAccess: getListaNegra=> Usuario= " + username);

		Usuario u = db.find(Usuario.class, username);
		TypedQuery<Estadistica> query = db.createQuery("SELECT e FROM Estadistica e WHERE e.year=?1",
				Estadistica.class);
		query.setParameter(1, year);
		List<Estadistica> estadistica = query.getResultList();
		Estadistica esta = estadistica.get(0);

		db.getTransaction().begin();
		esta.eliminarDeListaNegra(u);
		db.persist(esta);
		db.getTransaction().commit();

		return u;
	}

	public List<Usuario> getListaNegra(int year) {
		System.out.println(">> DataAccess: getListaNegra");
		TypedQuery<Estadistica> query = db.createQuery("SELECT e FROM Estadistica e WHERE e.year=?1",
				Estadistica.class);
		query.setParameter(1, year);
		List<Estadistica> estadistica = query.getResultList();
		Estadistica esta = estadistica.get(0);

		db.getTransaction().begin();
		List<Usuario> listaN = esta.getListaNegra();
		for (Usuario u : listaN)
			System.out.println(u.getNombreUsuario());
		db.getTransaction().commit();
		return listaN;
	}

	public void bloquearUsuario(String username, int year) {
		System.out.println(">> DataAccess: bloquearUsuario=> Usuario= " + username);
		Usuario u = db.find(Usuario.class, username);

		TypedQuery<Estadistica> query = db.createQuery("SELECT e FROM Estadistica e WHERE e.year=?1",
				Estadistica.class);
		query.setParameter(1, year);
		List<Estadistica> estadistica = query.getResultList();
		Estadistica esta = estadistica.get(0);

		db.getTransaction().begin();
		u.setBloqueado(true);
		esta.eliminarDeListaNegra(u);
		db.persist(u);
		db.persist(esta);
		db.getTransaction().commit();
	}

//FORO::

	public Vector<Mensaje> getForoMensajes() {
		System.out.println(">> DataAccess: Obtener TODOS los mensajes");

		TypedQuery<Mensaje> mensajesAll = db.createQuery("Select m FROM Mensaje m ORDER BY m.date ASC", Mensaje.class);
		List<Mensaje> resultado = mensajesAll.getResultList();
		Vector<Mensaje> mensajes = new Vector<Mensaje>();

		for (Mensaje e : resultado) {
			mensajes.add(e);
		}
		return mensajes;
	}

	public Mensaje guardarMensaje(Usuario user, String texto, Date date) {

		Usuario u = db.find(Usuario.class, user.getNombreUsuario());

		System.out.println(">> DataAccess: createMensaje=> emisor: " + u.getNombreUsuario() + " Texto=> " + texto);

		db.getTransaction().begin();
		Mensaje m = new Mensaje(user, texto, date);
		db.persist(m); // db.persist(q) not required when CascadeType.PERSIST is added in questions
						// property of Event class
						// @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
		db.getTransaction().commit();
		return m;
	}

	public int dadoMegusta(Mensaje m) {
		System.out.println(">>Guardamos el me gusta");
		db.getTransaction().begin();
		Mensaje men = db.find(Mensaje.class, m.getMensajeNumber());
		men.setMegustas(men.getMegustas() + 1);
		men.setDado(true);
		db.persist(men);
		db.getTransaction().commit();

		return men.getMegustas();
	}

	public Vector<Mensaje> filtrarPorUsuario(String nombreUsuario) {
		Usuario u = db.find(Usuario.class, nombreUsuario);
		System.out.println(">> DataAccess: obtener mensajes segun el usuario");
		TypedQuery<Mensaje> query = db.createQuery("SELECT m FROM Mensaje m WHERE m.due�o=?", Mensaje.class);
		query.setParameter(1, u);
		List<Mensaje> que = query.getResultList();
		Vector<Mensaje> men = new Vector<Mensaje>();
		for (Mensaje m : que) {
			men.add(m);
		}
		return men;

	}

	public void close() {
		db.close();
		System.out.println("DataBase closed");
	}

}
