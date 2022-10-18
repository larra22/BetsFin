package test.test;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dataAccess.DataAccess;
import domain.AnadirUsuarioParameter;
import domain.Usuario;
import exceptions.UserAlreadyExists;
import exceptions.WrongUserOrPassword;
import test.dataAccess.TestDataAccess;

public class ComprobarDABTest {

	// sut:system under test
	static DataAccess sut = new DataAccess();

	static TestDataAccess testDA = new TestDataAccess();

	private Usuario u;

	@Before
	public void creamosUsuarioTest() {
		String nombreUsuario = "maider";
		String password = "zermoduz";
		String nombre = "nerea";
		String dni = "787676D";
		int telefono = 8889890;
		int tarjeta = 7889799;
		String apel1 = "larra";
		String apel2 = "sanchez";
		String correo = "iiuiui@fdf";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date oneDate = null;
		try {
			oneDate = sdf.parse("05/10/2022");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		AnadirUsuarioParameter info= new AnadirUsuarioParameter(nombre,apel1,apel2,telefono,oneDate,dni);
		testDA.open();
	 try {
		u = testDA.addUsuario(info, nombreUsuario, password,correo,  tarjeta);
	} catch (UserAlreadyExists e) {
		e.printStackTrace();
	}
	 testDA.close();
	}

	@After
	public void eliminamosUseranadido() {
		// Dejamos la Base de datos como estaba
		testDA.open();
		boolean b = testDA.removeUsuario(u);
		testDA.close();
		System.out.println("Finaly " + b);

	}

	@Test
	// Ni el usuario ni la contrase�a estan en la base de datos auqnue los 
	//parametros pasandos cumplen las condiciones, el usuario no esta registrado
	public void test1() {
		String nombreUsser="juanita";
		String pass="nosenada";
		String expected= ResourceBundle.getBundle("Etiquetas").getString("UserPassWordIsWrong");
		
		Usuario real= null;
		
		try {
			//Probamos el metodo
			real = sut.comprobar(nombreUsser, pass);
			
			//Si el programa continua es fail
			fail();
		}catch (WrongUserOrPassword e) {
			assertEquals(expected, e.getMessage());
		}
	}

	@Test
	// El usuario se encuentra en la base de datos, es pasado como parametro su
	// nombredeUsuario correcto
	// y la contrse�a tambien es correcta
	public void test2() {
		String nombreUsuario="maider";
		String password="zermoduz";
		
		Usuario real=null;
		
		try {
			//Probamos el metodo que necesitamos
			real= sut.comprobar(nombreUsuario, password);
			
			assertTrue(real!=null);
			assertEquals(real.getNombreUsuario(),u.getNombreUsuario());
			assertEquals(real.getPassword(),u.getPassword());
			
			
			
		}catch(WrongUserOrPassword e) {
			fail("Everything should be fine");
		}
	}

	@Test
	// Es usuario esta en la base de datos pero la contrase�a pasada es incorrecta
	public void test3() {
		String nombreUsuario="maider";
		String password="EIBAR";
		String expected= ResourceBundle.getBundle("Etiquetas").getString("UserPassWordIsWrong");
		
		Usuario real= null;
		
		try {
			//Probamos el metodo
			real = sut.comprobar(nombreUsuario, password);
			
			//Si el programa continua es fail
			fail();
		}catch (WrongUserOrPassword e) {
			assertEquals(expected, e.getMessage());
		}

	}

	@Test
	// Uno de los parametros es null
	public void test4() {
		String nombreUsuario="";
		String password=null;
		String expected= ResourceBundle.getBundle("Etiquetas").getString("UserPassWordIsWrong");
		
		Usuario real= null;
		
		try {
			//Probamos el metodo
			real = sut.comprobar(nombreUsuario, password);
			
			//Si el programa continua es fail
			fail();
		}catch (WrongUserOrPassword e) {
			assertEquals(expected, e.getMessage());
		}
		
	}

	@Test
	// String vacios
	public void test5() {
		String nombreUsuario="";
		String password="";
		String expected= ResourceBundle.getBundle("Etiquetas").getString("UserPassWordIsWrong");
		
		Usuario real= null;
		
		try {
			//Probamos el metodo
			real = sut.comprobar(nombreUsuario, password);
			
			//Si el programa continua es fail
			fail();
		}catch (WrongUserOrPassword e) {
			assertEquals(expected, e.getMessage());
		}
		
	}

}