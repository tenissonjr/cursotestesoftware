package br.usp.teste.calculosalario;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
	    void testaSalarioZerado() throws SalarioExcetion {
	    	
	    	BigDecimal salarioBruto = BigDecimal.ZERO;
	    	
	    	Salario salario =salarioBuilder.getSalario(salarioBruto);
	    	
	        assertEquals(BigDecimal.ZERO, salario.getSalarioBruto());
	        assertEquals(BigDecimal.ZERO, salario.getInss());
	        assertEquals(BigDecimal.ZERO, salario.getImpostoRenda());
	        assertEquals(BigDecimal.ZERO, salario.getSalarioLiquido());
	    }
}
