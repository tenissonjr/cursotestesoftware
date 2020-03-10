package br.usp.calculosalario.teste;

import static br.usp.calculosalario.util.CalculadoraSalarioUtil.arred;
import static br.usp.calculosalario.util.CalculadoraSalarioUtil.toBigDecimal;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import br.usp.calculosalario.desconto.DescontoSalarial;
import br.usp.calculosalario.exception.SalarioException;

class TesteValoresLimiteCalculoInss extends AbstractTest {

	
	private static final double VALOR_MAXIMO_BASE_INSS = 6101.06;

	private void assertDescontoCalculadoInss(double baseCalculoInssParam, double valorEsperadoAliquotaParam )  {

		BigDecimal baseCalculoInss 			= toBigDecimal( baseCalculoInssParam);
		BigDecimal valorEsperadoAliquota 	= toBigDecimal(valorEsperadoAliquotaParam);
		
		
		DescontoSalarial descontoSalarialInss = calculadoraSalario.calcularDescontoInss(baseCalculoInss) ;
		
		
		BigDecimal baseCalculoInssMax 		= toBigDecimal( Math.min(baseCalculoInssParam,VALOR_MAXIMO_BASE_INSS));
		BigDecimal valorEsperadoInss		= arred(baseCalculoInssMax.multiply(valorEsperadoAliquota).divide(toBigDecimal(100))) ;
		
		
		assertEquals(baseCalculoInssMax		, descontoSalarialInss.getBaseCalculo());
		assertEquals(valorEsperadoAliquota	, descontoSalarialInss.getAliquota());
		assertEquals(valorEsperadoInss		, descontoSalarialInss.getValor());

	}
		
	
	@Test
	void testaAliquotasInssQuandoBaseCalculoIgualAnteriorFaixaInicial()  {

		assertDescontoCalculadoInss(-0.01 	,   0.0);
		assertDescontoCalculadoInss(1045.00	,   7.5);
		assertDescontoCalculadoInss(2089.60	,	9.0);
		assertDescontoCalculadoInss(3134.40	,  12.00);

	}

	@Test
	void testaAliquotasInssQuandoBaseCalculoIgualFaixaInicial()  {
		
		assertDescontoCalculadoInss( 0.00 	,    7.5);
		assertDescontoCalculadoInss(1045.01	,    9.0);
		assertDescontoCalculadoInss(2089.61	,   12.0);
		assertDescontoCalculadoInss(3134.41	,   14.0);

	}

	@Test
	void testaAliquotasInssQuandoBaseCalculoIgualFaixaFinal() throws SalarioException {

		assertDescontoCalculadoInss(1045.00	,    7.5);
		assertDescontoCalculadoInss(2089.60	,    9.0);
		assertDescontoCalculadoInss(3134.40	,   12.0);
		assertDescontoCalculadoInss(6101.06	,   14.0);
		

	}

	@Test
	void testaAliquotasInssQuandoBaseCalculoIgualFaixaFinalMaisUm() throws SalarioException {

		assertDescontoCalculadoInss(1045.01	,    9.0);
		assertDescontoCalculadoInss(2089.61	,   12.0);
		assertDescontoCalculadoInss(3134.41	,   14.0);
		assertDescontoCalculadoInss(6101.07	,   14.0);
		assertDescontoCalculadoInss(8000.00	,   14.0);
		assertDescontoCalculadoInss(99999999,   14.0);

	}

}
