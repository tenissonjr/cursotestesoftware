package br.usp.teste.calculosalario;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import br.usp.calculosalario.DescontoSalarial;
import br.usp.calculosalario.FaixaInss;
import br.usp.calculosalario.exception.SalarioException;

public class TesteValoresLimiteCalculoInss extends AbstractTest {

	@Test
	void testaAliquotasInssQuandoBaseCalculoIgualFaixaInicialMenosUm()  {

		for (FaixaInss faixaInss : FaixaInss.TABELA_INSS) {

			if (faixaInss.getLimiteInferior().signum() == 0) {
				continue;
			}
			BigDecimal baseCalculoInss = faixaInss.getLimiteInferior().subtract(BigDecimal.valueOf(0.01));
			
			DescontoSalarial descontoInss = calculadoraSalario.calcularDescontoInss(baseCalculoInss) ;

			assertNotEquals(descontoInss.getAliquota(), faixaInss.getAliquota());

			assertNotEquals(descontoInss.getValor(), baseCalculoInss.multiply(faixaInss.getAliquota()));

		}

	}

	@Test
	void testaAliquotasInssQuandoBaseCalculoIgualFaixaInicial()  {

		for (FaixaInss faixaInss : FaixaInss.TABELA_INSS) {

			BigDecimal baseCalculoInss = faixaInss.getLimiteInferior();
			
			DescontoSalarial descontoInss = calculadoraSalario.calcularDescontoInss(baseCalculoInss) ;

			assertEquals(descontoInss.getAliquota(), faixaInss.getAliquota());

			assertEquals(descontoInss.getValor(), baseCalculoInss.multiply(faixaInss.getAliquota()));

		}

	}

	@Test
	void testaAliquotasInssQuandoBaseCalculoIgualFaixaFinal() throws SalarioException {

		for (FaixaInss faixaInss : FaixaInss.TABELA_INSS) {
			
			BigDecimal baseCalculoInss = faixaInss.getLimiteSuperior();

			DescontoSalarial descontoInss = calculadoraSalario.calcularDescontoInss(baseCalculoInss) ;

			assertEquals(descontoInss.getAliquota(), faixaInss.getAliquota());

			if(baseCalculoInss.compareTo(FaixaInss.BASE_CALCULO_TETO)>0) {
				baseCalculoInss = FaixaInss.BASE_CALCULO_TETO ;
			}
						
			BigDecimal valorInss = baseCalculoInss.multiply(faixaInss.getAliquota());
		
			assertEquals(descontoInss.getValor(), valorInss);

		}

	}

	@Test
	void testaAliquotasInssQuandoBaseCalculoIgualFaixaFinalMaisUm() throws SalarioException {

		for (FaixaInss faixaInss : FaixaInss.TABELA_INSS) {

			BigDecimal baseCalculoInss = faixaInss.getLimiteSuperior().add(BigDecimal.valueOf(0.01));
			
			DescontoSalarial descontoInss = calculadoraSalario.calcularDescontoInss(baseCalculoInss) ;

			if(baseCalculoInss.compareTo(FaixaInss.BASE_CALCULO_TETO)>0) {
				assertEquals(descontoInss.getAliquota(), faixaInss.getAliquota());
				
				baseCalculoInss = FaixaInss.BASE_CALCULO_TETO ;
				
				assertEquals(descontoInss.getValor(), baseCalculoInss.multiply(faixaInss.getAliquota()));
				
			}else {
				assertNotEquals(descontoInss.getAliquota(), faixaInss.getAliquota());

				assertNotEquals(descontoInss.getValor(), baseCalculoInss.multiply(faixaInss.getAliquota()));
			}
							
			
		

		}

	}

}
