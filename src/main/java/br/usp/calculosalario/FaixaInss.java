package br.usp.calculosalario;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import lombok.Getter;
import static br.usp.calculosalario.SalarioUtil.arred;

@Getter 

public class FaixaInss {

	
	   /*
	    
		Tabela INSS
		

			+----------------+-----------------+----------+
			| Limte Inferior | Limite Superior | ALÍQUOTA |
			+----------------+-----------------+----------+
			| R$ 0,00        | R$ 1.045,00     | 7,50%    |
			| R$ 1.045,01    | R$ 2.089,60     | 9,00%    |
			| R$ 2.089,61    | R$ 3.134,40     | 12,00%   |
			| R$ 3.134,41    | R$ 6.101,06     | 14,00%   |
			+----------------+-----------------+----------+
			

	   */	
	public static BigDecimal BASE_CALCULO_TETO=BigDecimal.valueOf(6101.06);
	
	public static final List<FaixaInss> TABELA_INSS = Arrays.asList( 
														new FaixaInss(BigDecimal.valueOf(0.0) 		,BigDecimal.valueOf(1045.00) 		 	,BigDecimal.valueOf(7.5/100))
														,new FaixaInss(BigDecimal.valueOf(1045.01) 	,BigDecimal.valueOf(2089.60) 			,BigDecimal.valueOf(9.0/100))
														,new FaixaInss(BigDecimal.valueOf(2089.61) 	,BigDecimal.valueOf(3134.40) 			,BigDecimal.valueOf(12.0/100))
														,new FaixaInss(BigDecimal.valueOf(3134.41) 	,BigDecimal.valueOf(Double.MAX_VALUE) 	,BigDecimal.valueOf(14.0/100))
														);
		

	private BigDecimal limiteInferior;
	
	private BigDecimal limiteSuperior;
	
	private BigDecimal aliquota;

	public FaixaInss(BigDecimal limiteInferior, BigDecimal limiteSuperior, BigDecimal aliquota) {
		super();
		this.limiteInferior = arred(limiteInferior);
		this.limiteSuperior = arred(limiteSuperior);
		this.aliquota = arred(aliquota);
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
