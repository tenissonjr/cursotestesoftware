package br.usp.calculosalario;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import br.usp.calculosalario.exception.SalarioBrutoInvalidoException;

public final class SalarioBuilder {

	   /*
	   R$  -   	 			R$  1.247,70 	8,00%
	   R$  1.247,71 	 	R$  2.079,50 	9,00%
	   R$  2.079,51 	 	R$  4.159,00 	11,00%
	   */	
	private final List<FaixaInss> TABELA_INSS = Arrays.asList( 
														new FaixaInss(BigDecimal.valueOf(-0.0) 		,BigDecimal.valueOf(1247.70) 		 	,BigDecimal.valueOf(8.0/100))
														,new FaixaInss(BigDecimal.valueOf(1247.71) 	,BigDecimal.valueOf(2079.50) 			,BigDecimal.valueOf(9.0/100))
														,new FaixaInss(BigDecimal.valueOf(2079.51) 	,BigDecimal.valueOf(Double.MAX_VALUE) 	,BigDecimal.valueOf(11.0/100))
														);
	
	public static SalarioBuilder getInstance() {
		return new SalarioBuilder();
	}
	
	
	public Salario getSalario(BigDecimal salarioBruto) throws SalarioBrutoInvalidoException{
		
		Salario salario = new Salario(salarioBruto);
		
		BigDecimal aliquotaInss = BigDecimal.ZERO;
		for (FaixaInss faixaInss : TABELA_INSS) {
			if( faixaInss.contemValor(salario.getSalarioBruto())) {
				aliquotaInss = faixaInss.getAliquota() ;
			}
		}
		salario.setInss(salario.getSalarioBruto().multiply(aliquotaInss));
		
		
		return salario;
		
	}
	
}
