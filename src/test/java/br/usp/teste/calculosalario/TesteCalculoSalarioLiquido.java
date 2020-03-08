package br.usp.teste.calculosalario;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import br.usp.calculosalario.Salario;
import br.usp.calculosalario.exception.DependenteInvalidoException;
import br.usp.calculosalario.exception.SalarioBrutoInvalidoException;
import br.usp.calculosalario.exception.SalarioException;

public class TesteCalculoSalarioLiquido extends AbstractTest {



	@Test
	void testaSalarioZerado() throws SalarioException {

		Salario salario = calculadoraSalario.calcular(BigDecimal.ZERO,0);

		validarSalario(salario);

		assertEquals(true, salario.getSalarioBruto().signum() == 0);
		assertEquals(true, salario.getInns().getValor().signum() == 0);
		assertEquals(true, salario.getIrrf().getValor().signum() == 0);
		assertEquals(true, salario.getSalarioLiquido().signum() == 0);

	}
	
	
}
