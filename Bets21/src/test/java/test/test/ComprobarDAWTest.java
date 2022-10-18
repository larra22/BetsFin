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

public class ComprobarDAWTest {

	static DataAccess sut = new DataAccess();
	
	static TestDataAccess testDA = new TestDataAccess();
	
	private Usuario u;
	
	@Before
	public void creamosUsuarioTest() {
		
		String nombreUsuario="aitor";
		String password="kaixo";
		String nombre= "nerea";
		String dni = "787676D";
		int telefono= 8889890;
		int tarjeta= 7889799;
		String apel1= "larra";
		String apel2="sanchez";
		String correo="iiuiui@fdf";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date oneDate=null;
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
		//Dejamos la Base de datos como estaba
		testDA.open();
	   boolean b=testDA.removeUsuario(u);
		testDA.close();
		System.out.println("Finaly " + b);
		
	}
	
	@Test
	//sut.comprobar: Deberia ir bien
	public void test1() {
		String nombreUsuario="aitor";
		String password="kaixo";
		
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
	//Salta excepcion por que los paramtros introducidos son incorrectos y no cumplen las condiciones
	//El if del codigo comprobvar esta mal, deberia ser < o igual a 0, como solo el menor de 0, los arrays
	//que estan vacion tambien los cojo por lo que no salta la correcta excepcion
	public void test2() {
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
	
//NO PASA EL TEST POR INTRODUCIR LOS PARAMTEROS EN NULL ; EN ELMETOD  NO PODRA EJECUTAR LA FUNCION .length()
	@Test
	//Salta excepcion por que los paramtros introducidos son incorrectos y no cumplen las condiciones
	public void test2_null() {
		String nombreUsuario=null;
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
	
	//Fallo que hemos encontrado, al ser null no reconoce bien ya que no puede conseguir el length 

	@Test
	//Parametros correctos pero el user no esta registrado
	public void test3() {
		String nombreUsuario="ENRIQUE";
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
	//Para metros introducidos corrrectamente, el user se encuentra registrado en la base de datos, pero la contrase�a no corresponde
	@Test public void test4() {
		String nombreUsuario="aitor";
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
	
	//Hemos realizado todos los casos de rueba que estaban en la tabal de caja balnca
	//Adem�s hemos conseguido una cobertura del 100 en el m�todo comprobar
}