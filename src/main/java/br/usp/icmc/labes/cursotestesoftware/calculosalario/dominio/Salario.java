package br.usp.icmc.labes.cursotestesoftware.calculosalario.dominio;

import java.math.BigDecimal;

import br.usp.icmc.labes.cursotestesoftware.calculosalario.desconto.DescontoSalarial;
import br.usp.icmc.labes.cursotestesoftware.calculosalario.exception.DependenteInvalidoException;
import br.usp.icmc.labes.cursotestesoftware.calculosalario.exception.SalarioBrutoInvalidoException;
import br.usp.icmc.labes.cursotestesoftware.calculosalario.exception.SalarioException;
import br.usp.icmc.labes.cursotestesoftware.calculosalario.util.CalculadoraSalarioUtil;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
public class Salario
{
   private BigDecimal salarioBruto = CalculadoraSalarioUtil.ZERO;
   
   private int dependentesImpostoRenda= 0;
   
   private DescontoSalarial inns = new  DescontoSalarial();
   
   private DescontoSalarial irrf = new  DescontoSalarial();
   
   private BigDecimal salarioLiquido = CalculadoraSalarioUtil.ZERO;
   


public Salario(BigDecimal salarioBruto,int dependentesImpostoRenda) throws SalarioException {
	   if(salarioBruto==null ) {
		   throw new SalarioBrutoInvalidoException();
	   }
	   if( salarioBruto.signum()<0) {
		   throw new SalarioBrutoInvalidoException();
	   }
	   setSalarioBruto(salarioBruto);
	   
	   if(dependentesImpostoRenda<0) {
		   throw new DependenteInvalidoException();
	   }   
	   setDependentesImpostoRenda(dependentesImpostoRenda);
	   
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


   public Salario valorIrrf(BigDecimal valorIrrf) {
	   this.irrf.setValor(valorIrrf);
	   return this;
   }   
   
   public Salario salarioLiquido(BigDecimal salarioLiquido) {
	   this.setSalarioLiquido(salarioLiquido);
	   return this;
   }   
   
}
