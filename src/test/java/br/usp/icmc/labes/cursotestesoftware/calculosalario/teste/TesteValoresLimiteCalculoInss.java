package br.usp.icmc.labes.cursotestesoftware.calculosalario.teste;
import static br.usp.icmc.labes.cursotestesoftware.calculosalario.desconto.FaixaInss.FAIXA_1;
import static br.usp.icmc.labes.cursotestesoftware.calculosalario.desconto.FaixaInss.FAIXA_2;
import static br.usp.icmc.labes.cursotestesoftware.calculosalario.desconto.FaixaInss.FAIXA_3;
import static br.usp.icmc.labes.cursotestesoftware.calculosalario.desconto.FaixaInss.FAIXA_4;
import static br.usp.icmc.labes.cursotestesoftware.calculosalario.util.CalculadoraSalarioUtil.adicionaDecimo;
import static br.usp.icmc.labes.cursotestesoftware.calculosalario.util.CalculadoraSalarioUtil.arred;
import static br.usp.icmc.labes.cursotestesoftware.calculosalario.util.CalculadoraSalarioUtil.subtraiDecimo;
import static br.usp.icmc.labes.cursotestesoftware.calculosalario.util.CalculadoraSalarioUtil.toBigDecimal;
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

import br.usp.icmc.labes.cursotestesoftware.calculosalario.CalculadoraSalario;
import br.usp.icmc.labes.cursotestesoftware.calculosalario.desconto.DescontoSalarial;
import br.usp.icmc.labes.cursotestesoftware.calculosalario.desconto.FaixaInss;
public class TesteValoresLimiteCalculoInss  {

	private CalculadoraSalario calculadoraSalario = new CalculadoraSalario();	
	
	private void assertDescontoCalculadoInss(BigDecimal baseCalculoInssParam, double valorEsperadoAliquotaParam )  {
		BigDecimal baseCalculoEsperado 		= toBigDecimal( Math.min(baseCalculoInssParam.doubleValue(),FaixaInss.TETO_BASE_CALCULO.doubleValue()));
		BigDecimal valorAliquotaEsperado 	= toBigDecimal(valorEsperadoAliquotaParam);
		BigDecimal valorEsperado			= arred(baseCalculoEsperado.multiply(valorAliquotaEsperado).divide(toBigDecimal(100))) ;
		DescontoSalarial descontoSalarialInss = calculadoraSalario.calcularDescontoInss(baseCalculoInssParam) ;
		assertEquals(baseCalculoEsperado	, descontoSalarialInss.getBaseCalculo());
		assertEquals(valorAliquotaEsperado	, descontoSalarialInss.getAliquota());
		assertEquals(valorEsperado			, descontoSalarialInss.getValor());
	}
	
	@Test
	public void testaAliquotasInssQuandoBaseCalculoMenorLimitesInferioresFaixas()  {
		assertDescontoCalculadoInss(subtraiDecimo(FAIXA_1.getLimiteInferior()) ,   0.0);
		assertDescontoCalculadoInss(subtraiDecimo(FAIXA_2.getLimiteInferior())	,   7.5);
		assertDescontoCalculadoInss(subtraiDecimo(FAIXA_3.getLimiteInferior())	,	9.0);
		assertDescontoCalculadoInss(subtraiDecimo(FAIXA_4.getLimiteInferior())	,  12.00);
	}

	@Test
	public void testaAliquotasInssQuandoBaseCalculoIgualLimitesInferioresFaixas()  {
		assertDescontoCalculadoInss(FAIXA_1.getLimiteInferior() ,    7.5);
		assertDescontoCalculadoInss(FAIXA_2.getLimiteInferior()	,    9.0);
		assertDescontoCalculadoInss(FAIXA_3.getLimiteInferior()	,   12.0);
		assertDescontoCalculadoInss(FAIXA_4.getLimiteInferior()	,   14.0);
	}

	@Test
	public void testaAliquotasInssQuandoBaseCalculoIgualLimitesSuperioresFaixas()  {
		assertDescontoCalculadoInss(FAIXA_1.getLimiteSuperior() ,    7.5);
		assertDescontoCalculadoInss(FAIXA_2.getLimiteSuperior()	,    9.0);
		assertDescontoCalculadoInss(FAIXA_3.getLimiteSuperior()	,   12.0);
		assertDescontoCalculadoInss(FAIXA_4.getLimiteSuperior()	,   14.0);
	}
	@Test
	public void testaAliquotasInssQuandoBaseCalculoMaiorLimitesSuperioresFaixas() {
		assertDescontoCalculadoInss(adicionaDecimo(FAIXA_1.getLimiteSuperior())	,    9.0);
		assertDescontoCalculadoInss(adicionaDecimo(FAIXA_2.getLimiteSuperior())	,   12.0);
		assertDescontoCalculadoInss(adicionaDecimo(FAIXA_3.getLimiteSuperior())	,   14.0);
		assertDescontoCalculadoInss(adicionaDecimo(FAIXA_4.getLimiteSuperior())	,   14.0);
	}
}
