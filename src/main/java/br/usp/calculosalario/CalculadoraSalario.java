package br.usp.calculosalario;

import java.math.BigDecimal;
import java.math.BigInteger;

import br.usp.calculosalario.exception.SalarioBrutoInvalidoException;

public final class CalculadoraSalario {


	public CalculadoraSalario() {
		super();
	}
	
	
	public Salario calcular(BigDecimal salarioBruto) throws SalarioBrutoInvalidoException{
		
		Salario salario = new Salario(salarioBruto);
		
		salario.setBaseCalculoInss(salarioBruto);
		BigDecimal aliquotaInss = BigDecimal.ZERO;
		for (FaixaInss faixaInss : FaixaInss.TABELA_INSS) {
			if( faixaInss.contemValor(salario.getBaseCalculoInss())) {
				aliquotaInss = faixaInss.getAliquota() ;
			}
		}
		salario.setAliquotaInss(aliquotaInss);
		
		
		BigDecimal inss = salario.getBaseCalculoInss().multiply(aliquotaInss);
		salario.setInss(inss);
		
		BigDecimal deducaoTotalDevidoADependentes = FaixaIrrf.DEDUCAO_POR_DEPENDENTE.multiply(BigDecimal.valueOf(salario.getDependentesImpostoRenda()))  ;
		BigDecimal baseCalculoIrrf = salario.getSalarioBruto()
									.subtract(salario.getInss())
									.subtract(deducaoTotalDevidoADependentes  );
		
		salario.setBaseCalculoIrrf(baseCalculoIrrf);
		
		if (baseCalculoIrrf.signum()>0) {
			salario.setBaseCalculoInss(baseCalculoIrrf);
			BigDecimal aliquotaIrrf = BigDecimal.ZERO;
			BigDecimal deducaoIrrf = BigDecimal.ZERO;
			for (FaixaIrrf faixaIrrf : FaixaIrrf.TABELA_IRRF) {
				if( faixaIrrf.contemValor(salario.getBaseCalculoInss())) {
					aliquotaIrrf = faixaIrrf.getAliquota() ;
					deducaoIrrf	= faixaIrrf.getDeducaoFaixa();
				}
			}
			BigDecimal impostoRenda = salario.getBaseCalculoIrrf().multiply(aliquotaIrrf);
			if(impostoRenda.signum()>0) {
				impostoRenda =impostoRenda.subtract(deducaoIrrf);
			}
			if(impostoRenda.signum()>0) {
				salario.setImpostoRenda(impostoRenda);
			}
		}
		
	
		
		
		return salario;
		
	}
	
}
