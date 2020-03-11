package br.usp.icmc.labes.cursotestesoftware.calculosalario.desconto;

import static br.usp.icmc.labes.cursotestesoftware.calculosalario.util.CalculadoraSalarioUtil.arred;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import lombok.Getter;

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
	
	public static final FaixaInss FAIXA_1 = new FaixaInss(arred(   0.0) 	,arred(1045.00) 		 	,arred(7.5));
	public static final FaixaInss FAIXA_2 = new FaixaInss(arred(1045.01) 	,arred(2089.60) 			,arred(9.0));
	public static final FaixaInss FAIXA_3 = new FaixaInss(arred(2089.61) 	,arred(3134.40) 			,arred(12.0));	
	public static final FaixaInss FAIXA_4 = new FaixaInss(arred(3134.41) 	,arred(Double.MAX_VALUE) 	,arred(14.0));	
	
	public static BigDecimal TETO_BASE_CALCULO=arred(6101.06);
	
	public static final List<FaixaInss> TABELA_INSS = Arrays.asList( 
														FAIXA_1
														,FAIXA_2
														,FAIXA_3
														,FAIXA_4
														);
		

	private BigDecimal limiteInferior;
	
	private BigDecimal limiteSuperior;
	
	private BigDecimal aliquota;

	public FaixaInss(BigDecimal limiteInferior, BigDecimal limiteSuperior, BigDecimal aliquota) {
		super();
		this.limiteInferior = limiteInferior;
		this.limiteSuperior = limiteSuperior;
		this.aliquota 		= aliquota;
	}
		
	
	public boolean contemValor(BigDecimal salarioBruto) {
		
		if(salarioBruto.compareTo(limiteInferior)<0) {
			return false;
		}
		if(salarioBruto.compareTo(limiteSuperior)>0) {
			return false;
		}
		
		return true;
	
		}

     



	
}
