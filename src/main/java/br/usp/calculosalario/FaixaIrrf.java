package br.usp.calculosalario;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import static br.usp.calculosalario.SalarioUtil.arred;

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
	
	public static BigDecimal DEDUCAO_POR_DEPENDENTE=BigDecimal.valueOf(189.59);
	
	public static final List<FaixaIrrf> TABELA_IRRF = Arrays.asList( 
														 new FaixaIrrf(BigDecimal.valueOf(1903,99) 	,BigDecimal.valueOf(2826.65) 		 	,BigDecimal.valueOf(7.5) 	,BigDecimal.valueOf(142.80))
														,new FaixaIrrf(BigDecimal.valueOf(2826.66) 	,BigDecimal.valueOf(3751.05) 		 	,BigDecimal.valueOf(15.0) 	,BigDecimal.valueOf(354.80))
														,new FaixaIrrf(BigDecimal.valueOf(3751.06) 	,BigDecimal.valueOf(4664.68) 		 	,BigDecimal.valueOf(22.5) 	,BigDecimal.valueOf(636.13))
														,new FaixaIrrf(BigDecimal.valueOf(4664.69) 	,BigDecimal.valueOf(Double.MAX_VALUE) 	,BigDecimal.valueOf(27.5) 	,BigDecimal.valueOf(869.36))
														);
		

	private BigDecimal limiteInferior;
	
	private BigDecimal limiteSuperior;
	
	private BigDecimal aliquota;
	
	private BigDecimal deducaoFaixa;
	
	

	public FaixaIrrf(BigDecimal limiteInferior, BigDecimal limiteSuperior, BigDecimal aliquota,BigDecimal deducaoFaixa) {
		super();
		this.limiteInferior = arred(limiteInferior);
		this.limiteSuperior = arred(limiteSuperior);
		this.aliquota = arred(aliquota);
		this.deducaoFaixa=arred(deducaoFaixa);
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
