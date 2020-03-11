package br.usp.icmc.labes.cursotestesoftware.calculosalario.desconto;

import static br.usp.icmc.labes.cursotestesoftware.calculosalario.util.CalculadoraSalarioUtil.arred;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import lombok.Getter;

@Getter 

public class FaixaIrrf {

	   /*
	    
		Tabela IRRF
		

		+----------------+-----------------+----------+--------------+
		| Limte Inferior | Limite Superior | ALÍQUOTA |   Dedução    |
		+----------------+-----------------+----------+--------------+
		|  R$  1.903,99  |  R$  2.826,65   | 7,5%     |  R$  142,80  |
		|  R$  2.826,66  |  R$  3.751,05   | 15,0%    |  R$  354,80  |
		|  R$  3.751,06  |  R$  4.664,68   | 22,5%    |  R$  636,13  |
		|  R$  4.664,69  | -               | 27,5%    |  R$  869,36  |
		+----------------+-----------------+----------+--------------+
		

		Dedução por dependente :189,59

	   */	

	public static final FaixaIrrf FAIXA_1 = new FaixaIrrf(arred(1903.99) 	,arred(2826.65) 		 	,arred(7.5) 	,arred(142.80));
	public static final FaixaIrrf FAIXA_2 = new FaixaIrrf(arred(2826.66) 	,arred(3751.05) 		 	,arred(15.0) 	,arred(354.80));	
	public static final FaixaIrrf FAIXA_3 = new FaixaIrrf(arred(3751.06) 	,arred(4664.68) 		 	,arred(22.5) 	,arred(636.13));	
	public static final FaixaIrrf FAIXA_4 = new FaixaIrrf(arred(4664.69) 	,arred(Double.MAX_VALUE) 	,arred(27.5) 	,arred(869.36));	
	
	public static BigDecimal DEDUCAO_POR_DEPENDENTE=arred(189.59);
	
	public static final List<FaixaIrrf> TABELA_IRRF = Arrays.asList( 
														 FAIXA_1
														,FAIXA_2
														,FAIXA_3
														,FAIXA_4
														);

	private BigDecimal limiteInferior;
	
	private BigDecimal limiteSuperior;
	
	private BigDecimal aliquota;
	
	private BigDecimal deducaoFaixa;
	
	

	public FaixaIrrf(BigDecimal limiteInferior, BigDecimal limiteSuperior, BigDecimal aliquota,BigDecimal deducaoFaixa) {
		super();
		this.limiteInferior =limiteInferior;
		this.limiteSuperior =limiteSuperior;
		this.aliquota 		=aliquota;
		this.deducaoFaixa	=deducaoFaixa;
	}
		
	
	public boolean contemValor(BigDecimal baseCalculoIrrf) {
		
		if(baseCalculoIrrf.compareTo(limiteInferior)<0) {
			return false;
		}
		

		if (limiteSuperior.equals(arred(Double.MAX_VALUE))) {
			return true;
		}
		
		if(baseCalculoIrrf.compareTo(limiteSuperior)>0 ) {
			return false;
		}
		
		
		return true;
	
		}

     



	
}
