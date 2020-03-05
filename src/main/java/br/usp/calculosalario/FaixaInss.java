package br.usp.calculosalario;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import lombok.Getter;

@Getter 

public class FaixaInss {

	
	   /*
	   R$  -   	 			R$  1.247,70 	8,00%
	   R$  1.247,71 	 	R$  2.079,50 	9,00%
	   R$  2.079,51 	 	R$  4.159,00 	11,00%
	   */	
	public static final List<FaixaInss> TABELA_INSS = Arrays.asList( 
														new FaixaInss(BigDecimal.valueOf(-0.0) 		,BigDecimal.valueOf(1247.70) 		 	,BigDecimal.valueOf(8.0/100))
														,new FaixaInss(BigDecimal.valueOf(1247.71) 	,BigDecimal.valueOf(2079.50) 			,BigDecimal.valueOf(9.0/100))
														,new FaixaInss(BigDecimal.valueOf(2079.51) 	,BigDecimal.valueOf(Double.MAX_VALUE) 	,BigDecimal.valueOf(11.0/100))
														);
		

	private BigDecimal limiteInferior;
	
	private BigDecimal limiteSuperior;
	
	private BigDecimal aliquota;

	public FaixaInss(BigDecimal limiteInferior, BigDecimal limiteSuperior, BigDecimal aliquota) {
		super();
		this.limiteInferior = limiteInferior;
		this.limiteSuperior = limiteSuperior;
		this.aliquota = aliquota;
	}
		
	
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
