package br.usp.calculosalario;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter 
@NoArgsConstructor 
@ToString
public class Salario
{
   private BigDecimal salarioBruto;
   
   private BigDecimal inss;
   
   private BigDecimal impostoRenda;
   
   private int dependentesImpostoRenda;
   
   private BigDecimal salarioLiquido;
	
}
