package br.usp.icmc.labes.cursotestesoftware.calculosalario;

import static br.usp.icmc.labes.cursotestesoftware.calculosalario.util.CalculadoraSalarioUtil.arred;
import static br.usp.icmc.labes.cursotestesoftware.calculosalario.util.CalculadoraSalarioUtil.toBigDecimal;

import java.math.BigDecimal;

import br.usp.icmc.labes.cursotestesoftware.calculosalario.desconto.DescontoSalarial;
import br.usp.icmc.labes.cursotestesoftware.calculosalario.desconto.FaixaInss;
import br.usp.icmc.labes.cursotestesoftware.calculosalario.desconto.FaixaIrrf;
import br.usp.icmc.labes.cursotestesoftware.calculosalario.dominio.Salario;
import br.usp.icmc.labes.cursotestesoftware.calculosalario.exception.SalarioException;
import br.usp.icmc.labes.cursotestesoftware.calculosalario.util.CalculadoraSalarioUtil;

/**
 * Classe utilitária para cálculo de salario
 * @author p_6677
 *
 */
public final class CalculadoraSalario {

	
	public CalculadoraSalario() {
		super();
	}

	public DescontoSalarial calcularDescontoInss(BigDecimal baseCalculoInss) {
		
		DescontoSalarial descontoSalarial = new DescontoSalarial();
		
		baseCalculoInss = CalculadoraSalarioUtil.limitarValorTeto(baseCalculoInss,FaixaInss.TETO_BASE_CALCULO);
		
		descontoSalarial.setBaseCalculo(baseCalculoInss);
		
		for (FaixaInss faixaInss : FaixaInss.TABELA_INSS) {

			if( faixaInss.contemValor(baseCalculoInss)) {
				descontoSalarial.setAliquota( faixaInss.getAliquota() );
				descontoSalarial.setValor(arred(baseCalculoInss.multiply(faixaInss.getAliquota().divide(toBigDecimal(100)))));
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
