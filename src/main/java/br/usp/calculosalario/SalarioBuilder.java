package br.usp.calculosalario;

import java.math.BigDecimal;

import br.usp.calculosalario.exception.SalarioBrutoInvalidoException;

public final class SalarioBuilder {

	
	public static SalarioBuilder getInstance() {
		return new SalarioBuilder();
	}
	
	
	public Salario getSalario(BigDecimal salarioBruto) throws SalarioBrutoInvalidoException{
		
		Salario salario = new Salario(salarioBruto);
		
		
		return salario;
		
	}
	
}
