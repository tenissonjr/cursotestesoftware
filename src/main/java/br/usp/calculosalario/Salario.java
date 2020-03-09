package br.usp.calculosalario;

import java.math.BigDecimal;

import br.usp.calculosalario.exception.DependenteInvalidoException;
import br.usp.calculosalario.exception.SalarioBrutoInvalidoException;
import br.usp.calculosalario.exception.SalarioException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter
@ToString
@EqualsAndHashCode
public class Salario
{
   private BigDecimal salarioBruto = BigDecimal.ZERO;
   
   private int dependentesImpostoRenda= 0;
   
   private DescontoSalarial inns = new  DescontoSalarial();
   
   private DescontoSalarial irrf = new  DescontoSalarial();
   
   private BigDecimal salarioLiquido = BigDecimal.ZERO;
   
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
   

   public Salario baseCalculoInns(BigDecimal baseCalculoInss) {
	   this.inns.setBaseCalculo(baseCalculoInss);
	   return this;
   }
   public Salario aliquotaInss(BigDecimal aliquotaInss) {
	   this.inns.setAliquota(aliquotaInss);
	   return this;
   }
        
   public Salario valorInss(BigDecimal valor) {
	   this.inns.setValor(valor);
	   return this;
   }   

   
   public Salario baseCalculoIrrf(BigDecimal baseCalculoIrrf) {
	   this.irrf.setBaseCalculo(baseCalculoIrrf);
	   return this;
   }
   public Salario aliquotaIrrf(BigDecimal aliquotaIrrf) {
	   this.irrf.setAliquota(aliquotaIrrf);
	   return this;
   }

   public Salario deducaoTotalDevidoADependentesIrrf(BigDecimal deducaoTotalDevidoADependentes) {
	   this.irrf.setDeducaoTotalDevidoADependentes(deducaoTotalDevidoADependentes);
	   return this;
   }
   public Salario deducaoFaixaIrrf(BigDecimal deducaoFaixa) {
	   this.irrf.setDeducaoFaixa(deducaoFaixa);
	   return this;
   } 

   public Salario valorIrrf(BigDecimal valorIrrf) {
	   this.irrf.setValor(valorIrrf);
	   return this;
   }   
   
   public Salario salarioLiquido(BigDecimal salarioLiquido) {
	   this.setSalarioLiquido(salarioLiquido);
	   return this;
   }   
   
}
