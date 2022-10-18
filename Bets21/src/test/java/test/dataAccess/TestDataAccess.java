package test.dataAccess;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import configuration.ConfigXML;
import domain.AnadirUsuarioParameter;
import domain.Apuesta;
import domain.Cupon;
import domain.Estadistica;
import domain.Event;
import domain.Pronostico;
import domain.Question;
import domain.Seleccion;
import domain.Subdito;
import domain.Usuario;
import exceptions.EventAlreadyExists;
import exceptions.PronosticoAlreadyExists;
import exceptions.QuestionAlreadyExist;
import exceptions.UserAlreadyExists;
//Coemntatrio
public class TestDataAccess {
	protected  EntityManager  db;
	protected  EntityManagerFactory emf;

	ConfigXML  c=ConfigXML.getInstance();

//bhjjhbh
	public TestDataAccess()  {
		
		System.out.println("Creating TestDataAccess instance");

		open();
		
	}

	
	public void open(){
		
		System.out.println("Opening TestDataAccess instance ");

		String fileName=c.getDbFilename();
		
		if (c.isDatabaseLocal()) {
			  emf = Persistence.createEntityManagerFactory("objectdb:"+fileName);
			  db = emf.createEntityManager();
		} else {
			Map<String, String> properties = new HashMap<String, String>();
			  properties.put("javax.persistence.jdbc.user", c.getUser());
			  properties.put("javax.persistence.jdbc.password", c.getPassword());

			  emf = Persistence.createEntityManagerFactory("objectdb://"+c.getDatabaseNode()+":"+c.getDatabasePort()+"/"+fileName, properties);

			  db = emf.createEntityManager();
    	   }
		
	}
	public void close(){
		db.close();
		System.out.println("DataBase closed");
	}

	public boolean removeInfo(AnadirUsuarioParameter info) {
		System.out.println("Quitamos info");
		AnadirUsuarioParameter inf = db.find(AnadirUsuarioParameter.class, info.getId());
		if(inf !=null) {
			db.getTransaction().begin();
			db.remove(inf);
			db.getTransaction().commit();
		}else {
			return false;
		}
		return true;
	}



public boolean removeUsuario(Usuario a) {
	System.out.println("Quitamos la apuesta uno");
	Usuario ap = db.find(Usuario.class, a.getNombreUsuario());
	if(ap!=null) {
	db.getTransaction().begin();
	db.remove(ap);
	db.getTransaction().commit();
	}else {
		return false;
	}
	return true;
}


public AnadirUsuarioParameter crearInfoUsuario(String nombre, String apellido1, String apellido2, int telefono, Date fechaNac, String dNi) {
	return new AnadirUsuarioParameter(nombre, apellido1, apellido2, telefono, fechaNac, dNi);
	
	
}

public Usuario addUsuario(AnadirUsuarioParameter parameterObject, String nombreUsuario, String password, String correo, int tarjeta)
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

}
	


