package br.usp.calculosalario.teste;

import org.junit.jupiter.api.BeforeAll;

import br.usp.calculosalario.CalculadoraSalario;

public class AbstractTest {

	protected static CalculadoraSalario calculadoraSalario;

	@BeforeAll
	static void initAll() {
		calculadoraSalario = new CalculadoraSalario();
	}

	public AbstractTest() {
		super();
	}


}