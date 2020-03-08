package br.usp.calculosalario;

import java.math.BigDecimal;

import br.usp.calculosalario.exception.SalarioException;

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

				descontoSalarial.setAliquota(faixaInss.getAliquota());

				BigDecimal valorInss = baseCalculoInss.multiply(faixaInss.getAliquota());
				
				descontoSalarial.setValor(valorInss);
			}
		}
		return descontoSalarial;
		
	}
	public DescontoSalarial calcularDescontoIrrf(BigDecimal baseCalculoIrrf) {
		return calcularDescontoIrrf(baseCalculoIrrf,0);
	}

	public DescontoSalarial calcularDescontoIrrf(BigDecimal baseCalculoIrrf,int dependentesImpostoRenda) {
		
		DescontoSalarial descontoSalarial = new DescontoSalarial();
		

		BigDecimal deducaoTotalDevidoADependentes = FaixaIrrf.DEDUCAO_POR_DEPENDENTE.multiply(BigDecimal.valueOf(dependentesImpostoRenda))  ;		
		
		baseCalculoIrrf = baseCalculoIrrf
						.subtract(deducaoTotalDevidoADependentes  );
		
		descontoSalarial.setDeducaoTotalDevidoADependentes(deducaoTotalDevidoADependentes);
		descontoSalarial.setBaseCalculo(baseCalculoIrrf);
		
		if (baseCalculoIrrf.signum()>0) {
			for (FaixaIrrf faixaIrrf : FaixaIrrf.TABELA_IRRF) {
				if( faixaIrrf.contemValor(baseCalculoIrrf)) {
					descontoSalarial.setAliquota(faixaIrrf.getAliquota());
					descontoSalarial.setDeducaoFaixa(faixaIrrf.getDeducaoFaixa());
				}
			}
			BigDecimal impostoRenda = baseCalculoIrrf.multiply(descontoSalarial.getAliquota());
			if(impostoRenda.signum()>0) {
				impostoRenda =impostoRenda.subtract(descontoSalarial.getDeducaoFaixa());
			}
			if(impostoRenda.signum()>0) {
				descontoSalarial.setValor(impostoRenda);
			}
		}

		return descontoSalarial;
		
	}
	
	
	public Salario calcular(BigDecimal salarioBruto,int dependentesImpostoRenda) throws SalarioException{
		
		Salario salario = new Salario(salarioBruto,dependentesImpostoRenda);
		
		salario.setInns(calcularDescontoInss(salarioBruto));

		BigDecimal baseCalculoIrrf = salario.getSalarioBruto()
				.subtract(salario.getInns().getValor())
				;
		
		salario.setIrrf(calcularDescontoIrrf(baseCalculoIrrf, salario.getDependentesImpostoRenda()));
		
		
		return salario;
		
	}

	
}
