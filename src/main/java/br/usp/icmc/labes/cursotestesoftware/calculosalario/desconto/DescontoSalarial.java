package br.usp.icmc.labes.cursotestesoftware.calculosalario.desconto;

import java.math.BigDecimal;

import br.usp.icmc.labes.cursotestesoftware.calculosalario.util.CalculadoraSalarioUtil;
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
