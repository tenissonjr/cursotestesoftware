package br.usp.teste.calculosalario;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static br.usp.calculosalario.SalarioUtil.toBigDecimal;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import br.usp.calculosalario.Salario;
import br.usp.calculosalario.exception.DependenteInvalidoException;
import br.usp.calculosalario.exception.SalarioBrutoInvalidoException;
import br.usp.calculosalario.exception.SalarioException;

public class TesteClassesEquivalênciaValoresEntrada extends AbstractTest {

/*
 * 

+------------------------+---------------------------------+-----------------------------------+
|  Variáveis de Entrada  | Classes de Equivalência válidas | Classes de Equivalência inválidas |
+------------------------+---------------------------------+-----------------------------------+
| Salário bruto          |                         1,2,3.. | Null, 0 , <0                      |
| Qtd. dependentes de IR |                       0,1,2,3.. | Null, <0                          |
+------------------------+---------------------------------+-----------------------------------+


	
	
 */
	
	
	
	@Test
	void testaSalarioBrutoValidoQuantidaddeDependentesInvalido() throws SalarioException {

		Assertions.assertThrows(DependenteInvalidoException.class, () -> {calculadoraSalario.calcular(BigDecimal.valueOf(1.0),-1);});

	}	
	
	@Test
	void testaSalarioBrutoInvalidoQuantidaddeDependentesValido() throws SalarioException {

		Assertions.assertThrows(SalarioBrutoInvalidoException.class, () -> {calculadoraSalario.calcular(null,0);});
		Assertions.assertThrows(SalarioBrutoInvalidoException.class, () -> {calculadoraSalario.calcular(BigDecimal.valueOf(-1),0);});

	}


	@Test
	void testaSalarioBrutoInvalidoQuantidaddeDependentesInvalido() throws SalarioException {

		Assertions.assertThrows(SalarioBrutoInvalidoException.class, () -> {calculadoraSalario.calcular(null,0-1);});
		Assertions.assertThrows(SalarioBrutoInvalidoException.class, () -> {calculadoraSalario.calcular(BigDecimal.valueOf(-1),-3);});

	}	
	@Test
	void testaSalarioBrutoValidoQuantidaddeDependentesValido() throws SalarioException {

		Salario salario = calculadoraSalario.calcular(toBigDecimal(0),0);
		
		assertEquals(toBigDecimal(0), salario.getSalarioLiquido())	;

	}	
		
}
