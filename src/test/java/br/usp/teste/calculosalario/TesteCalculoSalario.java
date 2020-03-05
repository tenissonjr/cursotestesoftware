package br.usp.teste.calculosalario;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.usp.calculosalario.Salario;
import br.usp.calculosalario.SalarioBuilder;
import br.usp.calculosalario.exception.SalarioBrutoInvalidoException;
import br.usp.calculosalario.exception.SalarioExcetion;

public class TesteCalculoSalario {

	
	private static SalarioBuilder salarioBuilder;
	
	@BeforeAll
    static void initAll() {
		salarioBuilder = SalarioBuilder.getInstance();
    }
	
	
	   @Test
	    void testaSalarioBrutoNulo()  throws SalarioExcetion {
	    	
		   Assertions.assertThrows(SalarioBrutoInvalidoException.class, () -> {
			   salarioBuilder.getSalario(null);
			  });
	    	
	    }	
	
	   @Test
	    void testaSalarioBrutoNegativo()  throws SalarioExcetion {
	    	
		   Assertions.assertThrows(SalarioBrutoInvalidoException.class, () -> {
			   salarioBuilder.getSalario(BigDecimal.valueOf(-1.0));
			  });
	    	
	    }	   
	   
	  @Test
	    void testaSalarioZerado() throws SalarioExcetion {
	    	
	    	
	    	Salario salario =salarioBuilder.getSalario(BigDecimal.ZERO);
	    	
	    	validarSalario(salario);
	    	
	    	
	        assertEquals(true, salario.getSalarioBruto().signum()==0);
	        assertEquals(true, salario.getInss().signum()==0);
	        assertEquals(true, salario.getImpostoRenda().signum()==0);
	        assertEquals(true, salario.getSalarioLiquido().signum()==0);
	        
	    }


	private void validarSalario(Salario salario) {
		assertNotNull(salario);
		assertNotNull(salario.getSalarioBruto());
		assertNotNull(salario.getInss());
		assertNotNull(salario.getImpostoRenda());
		assertNotNull(salario.getSalarioLiquido());
	}
}
