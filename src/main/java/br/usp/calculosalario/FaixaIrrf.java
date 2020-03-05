package br.usp.calculosalario;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import lombok.Getter;

@Getter 

public class FaixaIrrf {

	
	public static BigDecimal DEDUCAO_POR_DEPENDENTE=BigDecimal.valueOf(171.97);
	
	   /*
			 R$  -   	 		R$  1.710,78 	0,0%	 R$  -   
			 R$  1.710,79 	 	R$  2.563,91 	7,5%	 R$  112,43 
			 R$  2.563,92 	 	R$  3.418,59 	15,0%	 R$  280,94 
			 R$  3.418,60 	 	R$  4.271,59 	22,5%	 R$  505,62 
			 R$  4.271,60 						27,5%	 R$  692,78 
	   */	
	public static final List<FaixaIrrf> TABELA_IRRF = Arrays.asList( 
														 new FaixaIrrf(BigDecimal.valueOf(1710.79) 	,BigDecimal.valueOf(2563.91) 		 	,BigDecimal.valueOf(7.5/100) 	,BigDecimal.valueOf(112.43))
														,new FaixaIrrf(BigDecimal.valueOf(2563.92) 	,BigDecimal.valueOf(3418.59) 		 	,BigDecimal.valueOf(15.0/100) 	,BigDecimal.valueOf(280.94))
														,new FaixaIrrf(BigDecimal.valueOf(3418.60) 	,BigDecimal.valueOf(Double.MAX_VALUE) 	,BigDecimal.valueOf(27.5/100) 	,BigDecimal.valueOf(692.78))
														);
		

	private BigDecimal limiteInferior;
	
	private BigDecimal limiteSuperior;
	
	private BigDecimal aliquota;
	
	private BigDecimal deducaoFaixa;
	
	

	public FaixaIrrf(BigDecimal limiteInferior, BigDecimal limiteSuperior, BigDecimal aliquota,BigDecimal deducaoFaixa) {
		super();
		this.limiteInferior = limiteInferior;
		this.limiteSuperior = limiteSuperior;
		this.aliquota = aliquota;
		this.deducaoFaixa=deducaoFaixa;
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
