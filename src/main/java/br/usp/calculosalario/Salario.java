package br.usp.calculosalario;

import java.math.BigDecimal;

import br.usp.calculosalario.exception.SalarioBrutoInvalidoException;
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
   private BigDecimal salarioBruto = BigDecimal.ZERO;
   
   private BigDecimal inss =BigDecimal.ZERO;
   
   private BigDecimal impostoRenda =BigDecimal.ZERO;
   
   private int dependentesImpostoRenda= 0;
   
   public Salario(BigDecimal salarioBruto) throws SalarioBrutoInvalidoException {
	   if(salarioBruto==null) {
		   throw new SalarioBrutoInvalidoException();
	   }
	   this.salarioBruto=salarioBruto;
   }
   public Salario(BigDecimal salarioBruto,int dependentesImpostoRenda) throws SalarioBrutoInvalidoException {
	   this(salarioBruto);
	   this.dependentesImpostoRenda=dependentesImpostoRenda;
   }
   
   public BigDecimal getSalarioLiquido() {
	  return  salarioBruto.subtract(inss).subtract(impostoRenda);
   }
	
}
