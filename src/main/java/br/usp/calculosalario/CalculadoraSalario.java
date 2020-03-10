package br.usp.calculosalario;

import static br.usp.calculosalario.util.CalculadoraSalarioUtil.arred;
import static br.usp.calculosalario.util.CalculadoraSalarioUtil.toBigDecimal;

import java.math.BigDecimal;

import br.usp.calculosalario.desconto.DescontoSalarial;
import br.usp.calculosalario.desconto.FaixaInss;
import br.usp.calculosalario.desconto.FaixaIrrf;
import br.usp.calculosalario.dominio.Salario;
import br.usp.calculosalario.exception.SalarioException;
import br.usp.calculosalario.util.CalculadoraSalarioUtil;


public final class CalculadoraSalario {


	public CalculadoraSalario() {
		super();
	}

	public DescontoSalarial calcularDescontoInss(BigDecimal baseCalculoInss) {
		
		DescontoSalarial descontoSalarial = new DescontoSalarial();
		
		if (baseCalculoInss.compareTo(FaixaInss.BASE_CALCULO_TETO)>0) {
			baseCalculoInss = FaixaInss.BASE_CALCULO_TETO ;
		}		
		
		descontoSalarial.setBaseCalculo(baseCalculoInss);
		
		for (FaixaInss faixaInss : FaixaInss.TABELA_INSS) {

			if( faixaInss.contemValor(baseCalculoInss)) {

								
				descontoSalarial.setAliquota( faixaInss.getAliquota() );
				descontoSalarial.setValor(arred(baseCalculoInss.multiply(faixaInss.getAliquota().divide(toBigDecimal(100)))));
				
				break;
			}
		}
		return descontoSalarial;
		
	}
	public DescontoSalarial calcularDescontoIrrf(BigDecimal baseCalculoIrrf) {
		return calcularDescontoIrrf(baseCalculoIrrf,0);
	}

	public DescontoSalarial calcularDescontoIrrf(BigDecimal baseCalculoIrrfParam,int dependentesImpostoRenda) {
		
		DescontoSalarial descontoSalarial = new DescontoSalarial();
		

		BigDecimal deducaoTotalDevidoADependentes = arred( FaixaIrrf.DEDUCAO_POR_DEPENDENTE.multiply(BigDecimal.valueOf(dependentesImpostoRenda)) ) ;		
		
		BigDecimal baseCalculoIrrf = arred(baseCalculoIrrfParam.subtract(deducaoTotalDevidoADependentes ));
		
		descontoSalarial.setDeducaoTotalDevidoADependentes(deducaoTotalDevidoADependentes);
		
		descontoSalarial.setBaseCalculo(baseCalculoIrrf);
		descontoSalarial.setAliquota(CalculadoraSalarioUtil.ZERO);
		descontoSalarial.setValor(CalculadoraSalarioUtil.ZERO);
		
		
			for (FaixaIrrf faixaIrrf : FaixaIrrf.TABELA_IRRF) {
				if( faixaIrrf.contemValor(baseCalculoIrrf)) {
					
					descontoSalarial.setAliquota(faixaIrrf.getAliquota());
					descontoSalarial.setDeducaoFaixa(faixaIrrf.getDeducaoFaixa());
					
					BigDecimal impostoRenda = arred( (baseCalculoIrrf
														.multiply(descontoSalarial.getAliquota())
														.divide(toBigDecimal(100))
													)		
														.subtract(descontoSalarial.getDeducaoFaixa()) 
													);
					

					descontoSalarial.setValor(impostoRenda);
					
					break ;
					
				}
		

		}

		return descontoSalarial;
		
	}
	
	
	public Salario calcular(BigDecimal salarioBruto,int dependentesImpostoRenda) throws SalarioException{
		
		Salario salario = new Salario(salarioBruto,dependentesImpostoRenda);
		
		salario.setInns(calcularDescontoInss(salarioBruto));

		BigDecimal baseCalculoIrrf = arred( salario.getSalarioBruto()
				.subtract(salario.getInns().getValor()) )
				;
		
		salario.setIrrf(calcularDescontoIrrf(baseCalculoIrrf, salario.getDependentesImpostoRenda()));
		
		
		BigDecimal salarioLiquido=  arred(salario.getSalarioBruto()
										.subtract(salario.getInns().getValor())
										.subtract(salario.getIrrf().getValor())
										);
		
		salario.setSalarioLiquido(salarioLiquido);
			  
		
		
		return salario;
		
	}

	
}
