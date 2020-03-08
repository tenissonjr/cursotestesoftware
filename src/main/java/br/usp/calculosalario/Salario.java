package br.usp.calculosalario;

import java.math.BigDecimal;

import br.usp.calculosalario.exception.DependenteInvalidoException;
import br.usp.calculosalario.exception.SalarioBrutoInvalidoException;
import br.usp.calculosalario.exception.SalarioException;
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
   
   
   public Salario(BigDecimal salarioBruto) throws SalarioException {
	  this(salarioBruto,0);

   }

public Salario(BigDecimal salarioBruto,int dependentesImpostoRenda) throws SalarioException {
	   if(salarioBruto==null || salarioBruto.signum()<0) {
		   throw new SalarioBrutoInvalidoException();
	   }
	   this.salarioBruto=salarioBruto;
	   
	   if(dependentesImpostoRenda<0) {
		   throw new DependenteInvalidoException();
	   }   
	   this.dependentesImpostoRenda=dependentesImpostoRenda;
   }
   
   public BigDecimal getSalarioLiquido() {
	  return  (salarioBruto.subtract(inns.getValor()).subtract(irrf.getValor())).setScale(2);
   }
	
}
