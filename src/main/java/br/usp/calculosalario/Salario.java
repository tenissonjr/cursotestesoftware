package br.usp.calculosalario;

import java.math.BigDecimal;

import br.usp.calculosalario.exception.SalarioBrutoInvalidoException;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter
@ToString
public class Salario
{
   private BigDecimal salarioBruto = BigDecimal.ZERO;
   
   private int dependentesImpostoRenda= 0;
   
   private DescontoSalarial inns = new  DescontoSalarial();
   
   private DescontoSalarial irrf = new  DescontoSalarial();
   
   
   public Salario(BigDecimal salarioBruto) throws SalarioBrutoInvalidoException {
	   if(salarioBruto==null || salarioBruto.signum()<0) {
		   throw new SalarioBrutoInvalidoException();
	   }
	   this.salarioBruto=salarioBruto;
	 
   }

public Salario(BigDecimal salarioBruto,int dependentesImpostoRenda) throws SalarioBrutoInvalidoException {
	   this(salarioBruto);
	   this.dependentesImpostoRenda=dependentesImpostoRenda;
   }
   
   public BigDecimal getSalarioLiquido() {
	  return  salarioBruto.subtract(inns.getValor()).subtract(irrf.getValor());
   }
	
}
