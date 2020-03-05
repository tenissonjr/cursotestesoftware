package br.usp.calculosalario;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter 
@AllArgsConstructor()
public class FaixaInss {


	private BigDecimal limiteInferior;
	
	private BigDecimal limiteSuperior;
	
	private BigDecimal aliquota;

	public boolean contemValor(BigDecimal salarioBruto) {
		
		if (limiteInferior==null || limiteSuperior==null || salarioBruto==null) {
			return false;
		}
		if(salarioBruto.compareTo(limiteInferior)<0) {
			return false;
		}
		if(salarioBruto.compareTo(limiteSuperior)>0) {
			return false;
		}
		
		return true;
	
		}
	

	
}
