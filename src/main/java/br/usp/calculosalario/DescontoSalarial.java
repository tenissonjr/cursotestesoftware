package br.usp.calculosalario;

import java.math.BigDecimal;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter
@ToString
@EqualsAndHashCode
public class DescontoSalarial {
	
	   private BigDecimal baseCalculo = SalarioUtil.ZERO;
	   
	   private BigDecimal aliquota = SalarioUtil.ZERO;
	   
	   private BigDecimal deducaoTotalDevidoADependentes =SalarioUtil.ZERO;
	   
	   private BigDecimal deducaoFaixa =SalarioUtil.ZERO;
	   
	   private BigDecimal valor =SalarioUtil.ZERO;
	   
	

}
