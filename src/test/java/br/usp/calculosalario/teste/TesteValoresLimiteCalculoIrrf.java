package br.usp.calculosalario.teste;

import static br.usp.calculosalario.util.CalculadoraSalarioUtil.arred;
import static br.usp.calculosalario.util.CalculadoraSalarioUtil.toBigDecimal;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import br.usp.calculosalario.desconto.DescontoSalarial;
import br.usp.calculosalario.desconto.FaixaIrrf;
import br.usp.calculosalario.exception.SalarioException;

class TesteValoresLimiteCalculoIrrf extends AbstractTest {

	@Test
	void testaAliquotasIrrfQuandoBaseCalculoIgualFaixaInicialMenosUm()  {

		for (FaixaIrrf faixaIrrf : FaixaIrrf.TABELA_IRRF) {


			BigDecimal baseCalculoIrrf = faixaIrrf.getLimiteInferior().subtract(BigDecimal.valueOf(0.01));
			
			DescontoSalarial descontoIrrf = calculadoraSalario.calcularDescontoIrrf(baseCalculoIrrf) ;

			assertNotEquals(descontoIrrf.getAliquota(), faixaIrrf.getAliquota());

		}

	}

	@Test
	void testaAliquotasIrrfQuandoBaseCalculoIgualFaixaInicial()  {

		for (FaixaIrrf faixaIrrf : FaixaIrrf.TABELA_IRRF) {

			BigDecimal baseCalculoIrrf = faixaIrrf.getLimiteInferior();
			
			DescontoSalarial descontoIrrf = calculadoraSalario.calcularDescontoIrrf(baseCalculoIrrf) ;

			assertEquals(descontoIrrf.getAliquota(), faixaIrrf.getAliquota());

			
			BigDecimal impostoRenda = arred( ( baseCalculoIrrf.multiply(faixaIrrf.getAliquota())
					 .divide(toBigDecimal(100))
					).subtract(faixaIrrf.getDeducaoFaixa())
				)
			 ;
			
			
			assertEquals(impostoRenda,descontoIrrf.getValor());

		}

	}

	@Test
	void testaAliquotasIrrfQuandoBaseCalculoIgualFaixaFinal() throws SalarioException {

		for (FaixaIrrf faixaIrrf : FaixaIrrf.TABELA_IRRF) {
			
			BigDecimal baseCalculoIrrf = faixaIrrf.getLimiteSuperior();

			DescontoSalarial descontoIrrf = calculadoraSalario.calcularDescontoIrrf(baseCalculoIrrf) ;

			assertEquals(descontoIrrf.getAliquota(), faixaIrrf.getAliquota());

			BigDecimal impostoRenda = arred( ( baseCalculoIrrf.multiply(faixaIrrf.getAliquota())
												 .divide(toBigDecimal(100))
												).subtract(faixaIrrf.getDeducaoFaixa())
											)
										 ;


			
			
			
			assertEquals((descontoIrrf.getValor().subtract(impostoRenda)).signum(),0);

		}

	}

	@Test
	void testaAliquotasIrrfQuandoBaseCalculoIgualFaixaFinalMaisUm() throws SalarioException {

		for (FaixaIrrf faixaIrrf : FaixaIrrf.TABELA_IRRF) {

			BigDecimal baseCalculoIrrf = faixaIrrf.getLimiteSuperior().add(BigDecimal.valueOf(0.01));
			
			DescontoSalarial descontoIrrf = calculadoraSalario.calcularDescontoIrrf(baseCalculoIrrf) ;

			assertNotEquals(descontoIrrf.getAliquota(), faixaIrrf.getAliquota());

		}

	}

}
