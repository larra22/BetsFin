package test.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mockito;

import org.mockito.runners.MockitoJUnitRunner;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;
import domain.Usuario;
import exceptions.WrongUserOrPassword;

@RunWith(MockitoJUnitRunner.class)

public class ComprobarMockIntTest {
	
	DataAccess dataAccess= Mockito.mock(DataAccess.class);
	
	Usuario mockedUser= Mockito.mock(Usuario.class);
	
	@InjectMocks
	BLFacade sut = new BLFacadeImplementation(dataAccess);
	
	@Test
	public void test1() {
		//Definimos los valores
		String nombreUsser="juanita";
		String pass="nosenada";
		mockedUser.setNombreUsuario(nombreUsser);
		mockedUser.setPassword(pass);
		
		try {
			Mockito.when(dataAccess.comprobar(nombreUsser, pass));
			Usuario real= sut.comprobar(nombreUsser, pass);
			
			assertTrue(real!=null);
			assertEquals(real.getNombreUsuario(),mockedUser.getNombreUsuario());
			assertEquals(real.getPassword(),mockedUser.getPassword());
			
		}catch(WrongUserOrPassword e) {
			fail("Si llega aqui mal");
		}
		
	}

}