package br.usp.teste.calculosalario;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.usp.calculosalario.FaixaInss;
import br.usp.calculosalario.Salario;
import br.usp.calculosalario.CalculadoraSalario;
import br.usp.calculosalario.exception.SalarioBrutoInvalidoException;
import br.usp.calculosalario.exception.SalarioExcetion;

public class TesteCalculoSalario {

	private static CalculadoraSalario calculadoraSalario;

	private void validarSalario(Salario salario) {
		assertNotNull(salario);
		assertNotNull(salario.getSalarioBruto());
		assertNotNull(salario.getInns());
		assertNotNull(salario.getIrrf());
		assertNotNull(salario.getSalarioLiquido());
	}

	@BeforeAll
	static void initAll() {
		calculadoraSalario = new CalculadoraSalario();
	}

	@Test
	void testaSalarioBrutoNulo() throws SalarioExcetion {

		Assertions.assertThrows(SalarioBrutoInvalidoException.class, () -> {
			calculadoraSalario.calcular(null);
		});

	}

	@Test
	void testaSalarioBrutoNegativo() throws SalarioExcetion {

		Assertions.assertThrows(SalarioBrutoInvalidoException.class, () -> {
			calculadoraSalario.calcular(BigDecimal.valueOf(-1.0));
		});

	}

	@Test
	void testaSalarioZerado() throws SalarioExcetion {

		Salario salario = calculadoraSalario.calcular(BigDecimal.ZERO);

		validarSalario(salario);

		assertEquals(true, salario.getSalarioBruto().signum() == 0);
		assertEquals(true, salario.getInns().getValor().signum() == 0);
		assertEquals(true, salario.getIrrf().getValor().signum() == 0);
		assertEquals(true, salario.getSalarioLiquido().signum() == 0);

	}

	/*
	 * ------------------------------------------------------------
	 * Testes de valor de INSS
	 * ------------------------------------------------------------
	 */

	/*
	 * R$ - R$ 1.247,70 8,00% 
	 * R$ 1.247,71 R$ 2.079,50 9,00% R$ 2.079,51 R$ 4.159,00
	 * 11,00%
	 */

	@Test
	void testaAliquotasInssQuandoSalarioIgualFaixaInicialMenosUm() throws SalarioExcetion {

		for (FaixaInss faixaInss : FaixaInss.TABELA_INSS) {

			if (faixaInss.getLimiteInferior().signum() == 0) {
				continue;
			}

			Salario salario = calculadoraSalario
					.calcular(faixaInss.getLimiteInferior().subtract(BigDecimal.valueOf(0.01)));

			validarSalario(salario);

			assertNotEquals(salario.getInns().getAliquota(), faixaInss.getAliquota());

			assertNotEquals(salario.getInns(), salario.getSalarioBruto().multiply(faixaInss.getAliquota()));

		}

	}

	@Test
	void testaAliquotasInssQuandoSalarioIgualFaixaInicial() throws SalarioExcetion {

		for (FaixaInss faixaInss : FaixaInss.TABELA_INSS) {

			Salario salario = calculadoraSalario.calcular(faixaInss.getLimiteInferior());

			validarSalario(salario);

			assertEquals(0, salario.getInns().getAliquota().compareTo(faixaInss.getAliquota()));

			assertEquals(salario.getInns().getValor(), salario.getSalarioBruto().multiply(faixaInss.getAliquota()));

		}

	}

	@Test
	void testaAliquotasInssQuandoSalarioIgualFaixaFinal() throws SalarioExcetion {

		for (FaixaInss faixaInss : FaixaInss.TABELA_INSS) {

			Salario salario = calculadoraSalario.calcular(faixaInss.getLimiteSuperior());

			validarSalario(salario);

			assertEquals(0, salario.getInns().getAliquota().compareTo(faixaInss.getAliquota()));

			assertEquals(salario.getInns().getValor(), salario.getSalarioBruto().multiply(faixaInss.getAliquota()));

		}

	}

	@Test
	void testaAliquotasInssQuandoSalarioIgualFaixaFinalMaisUm() throws SalarioExcetion {

		for (FaixaInss faixaInss : FaixaInss.TABELA_INSS) {

			Salario salario = calculadoraSalario.calcular(faixaInss.getLimiteSuperior().add(BigDecimal.valueOf(0.01)));

			validarSalario(salario);

			assertNotEquals(salario.getInns().getAliquota(), faixaInss.getAliquota());

			assertNotEquals(salario.getInns(), salario.getSalarioBruto().multiply(faixaInss.getAliquota()));

		}

	}

}
