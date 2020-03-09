package br.usp.teste.calculosalario;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeAll;

import br.usp.calculosalario.CalculadoraSalario;
import br.usp.calculosalario.Salario;

public class AbstractTest {

	protected static CalculadoraSalario calculadoraSalario;

	@BeforeAll
	static void initAll() {
		calculadoraSalario = new CalculadoraSalario();
	}

	protected void validarSalario(Salario salario) {
		assertNotNull(salario);
		assertNotNull(salario.getSalarioBruto());
		assertNotNull(salario.getInns());
		assertNotNull(salario.getIrrf());
		assertNotNull(salario.getSalarioLiquido());
	}

	public AbstractTest() {
		super();
	}


}