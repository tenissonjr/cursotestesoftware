package br.usp.icmc.labes.cursotestesoftware.calculosalario.teste;

import java.math.BigDecimal;

import org.junit.Test;

import static br.usp.icmc.labes.cursotestesoftware.calculosalario.util.CalculadoraSalarioUtil.toBigDecimal;
import static org.junit.Assert.assertEquals;

import br.usp.icmc.labes.cursotestesoftware.calculosalario.CalculadoraSalario;
import br.usp.icmc.labes.cursotestesoftware.calculosalario.dominio.Salario;
import br.usp.icmc.labes.cursotestesoftware.calculosalario.exception.DependenteInvalidoException;
import br.usp.icmc.labes.cursotestesoftware.calculosalario.exception.SalarioBrutoInvalidoException;
import br.usp.icmc.labes.cursotestesoftware.calculosalario.exception.SalarioException;

public class TesteClassesEquivalênciaValoresEntrada  {

	
	private CalculadoraSalario calculadoraSalario = new CalculadoraSalario();
	
/*
 * 
+------------------------+---------------------------------+-----------------------------------+
|  Variáveis de Entrada  | Classes de Equivalência válidas | Classes de Equivalência inválidas |
+------------------------+---------------------------------+-----------------------------------+
| Salário bruto          |                         1,2,3.. | Null, 0 , <0                      |
| Qtd. dependentes de IR |                       0,1,2,3.. | Null, <0                          |
+------------------------+---------------------------------+-----------------------------------+
 */
	
	@Test(expected = DependenteInvalidoException.class)
	public void testaSalarioBrutoValidoQuantidaddeDependentesInvalido() throws SalarioException {

		calculadoraSalario.calcular(BigDecimal.valueOf(1.0),-1);

	}	
	
	@Test(expected = SalarioBrutoInvalidoException.class)
	public void testaSalarioBrutoInvalidoNuloQuantidaddeDependentesValido() throws SalarioException {

		calculadoraSalario.calcular(null,0);


	}

	
	@Test(expected = SalarioBrutoInvalidoException.class)
	public void testaSalarioBrutoInvalidoNegativoQuantidaddeDependentesValido() throws SalarioException {

		calculadoraSalario.calcular(null,0);
	
	}
	

	@Test(expected = SalarioBrutoInvalidoException.class)
	public void testaSalarioBrutoInvalidoNuloQuantidaddeDependentesInvalido() throws SalarioException {

		calculadoraSalario.calcular(null,0-1);
	
	}
	
	@Test(expected = SalarioBrutoInvalidoException.class)
	public void testaSalarioBrutoInvalidoNegativoQuantidaddeDependentesInvalido() throws SalarioException {

		calculadoraSalario.calcular(BigDecimal.valueOf(-1),-3);

	}	
	
	@Test
	public void testaSalarioBrutoValidoQuantidaddeDependentesValido() throws SalarioException {

		Salario salario = calculadoraSalario.calcular(toBigDecimal(0),0);

		assertEquals(toBigDecimal(0), salario.getSalarioLiquido())	;

	}	
		
}
