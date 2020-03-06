package br.usp.calculosalario;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter
@ToString
public class DescontoSalarial {
	
	   private BigDecimal baseCalculo = BigDecimal.ZERO;
	   
	   private BigDecimal aliquota =BigDecimal.ZERO;
	   
	   private BigDecimal deducaoTotalDevidoADependentes =BigDecimal.ZERO;
	   
	   private BigDecimal deducaoFaixa =BigDecimal.ZERO;
	   
	   private BigDecimal valor =BigDecimal.ZERO;
	   
	

}
