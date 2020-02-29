package br.usp.calculosalario;

import java.math.BigDecimal;

public class SalarioBuilder {

	
	public static Salario getSalario(BigDecimal salarioBruto) {
		
		Salario salario = new Salario();
		salario.setSalarioBruto(salarioBruto);
		
		return salario;
		
	}
	
}
