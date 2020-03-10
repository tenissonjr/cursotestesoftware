package br.usp.calculosalario.desconto;

import java.math.BigDecimal;

import br.usp.calculosalario.util.CalculadoraSalarioUtil;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter
@ToString
@EqualsAndHashCode
public class DescontoSalarial {
	
	   private BigDecimal baseCalculo = CalculadoraSalarioUtil.ZERO;
	   
	   private BigDecimal aliquota = CalculadoraSalarioUtil.ZERO;
	   
	   private BigDecimal deducaoTotalDevidoADependentes =CalculadoraSalarioUtil.ZERO;
	   
	   private BigDecimal deducaoFaixa =CalculadoraSalarioUtil.ZERO;
	   
	   private BigDecimal valor =CalculadoraSalarioUtil.ZERO;
	   
	

}
