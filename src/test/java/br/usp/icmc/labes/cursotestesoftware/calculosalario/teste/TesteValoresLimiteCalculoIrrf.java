package br.usp.icmc.labes.cursotestesoftware.calculosalario.teste;

import static br.usp.icmc.labes.cursotestesoftware.calculosalario.desconto.FaixaIrrf.FAIXA_1;
import static br.usp.icmc.labes.cursotestesoftware.calculosalario.desconto.FaixaIrrf.FAIXA_2;
import static br.usp.icmc.labes.cursotestesoftware.calculosalario.desconto.FaixaIrrf.FAIXA_3;
import static br.usp.icmc.labes.cursotestesoftware.calculosalario.desconto.FaixaIrrf.FAIXA_4;
import static br.usp.icmc.labes.cursotestesoftware.calculosalario.util.CalculadoraSalarioUtil.adicionaDecimo;
import static br.usp.icmc.labes.cursotestesoftware.calculosalario.util.CalculadoraSalarioUtil.arred;
import static br.usp.icmc.labes.cursotestesoftware.calculosalario.util.CalculadoraSalarioUtil.subtraiDecimo;
import static br.usp.icmc.labes.cursotestesoftware.calculosalario.util.CalculadoraSalarioUtil.toBigDecimal;
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

import br.usp.icmc.labes.cursotestesoftware.calculosalario.CalculadoraSalario;
import br.usp.icmc.labes.cursotestesoftware.calculosalario.desconto.DescontoSalarial;

public class TesteValoresLimiteCalculoIrrf {

	private CalculadoraSalario calculadoraSalario = new CalculadoraSalario();		

	private void assertDescontoCalculadoIrrf(BigDecimal baseCalculoIrrfParam, BigDecimal deducaoFaixa , double valorEsperadoAliquotaParam )  {

		
		
		BigDecimal valorAliquotaEsperado 	= toBigDecimal(valorEsperadoAliquotaParam);		

		BigDecimal valorEsperado = arred( (baseCalculoIrrfParam
				.multiply(valorAliquotaEsperado)
				.divide(toBigDecimal(100))
			)		
				.subtract(deducaoFaixa) 
			);
		
		
		
		if (valorEsperado.signum()<0) {
			valorEsperado = arred (BigDecimal.ZERO);
		}
		
		DescontoSalarial descontoIrrf = calculadoraSalario.calcularDescontoIrrf(baseCalculoIrrfParam) ;
		
		assertEquals(baseCalculoIrrfParam	, descontoIrrf.getBaseCalculo());
		assertEquals(valorAliquotaEsperado	, descontoIrrf.getAliquota());
		assertEquals(valorEsperado			, descontoIrrf.getValor());
		
	}
	
	@Test
	public void testaAliquotasIrrfQuandoBaseCalculoMenorLimitesInferioresFaixas()  {

		assertDescontoCalculadoIrrf(subtraiDecimo(FAIXA_1.getLimiteInferior()) ,BigDecimal.ZERO		  ,   0.0);
		assertDescontoCalculadoIrrf(subtraiDecimo(FAIXA_2.getLimiteInferior())	,FAIXA_1.getDeducaoFaixa(),   7.5);
		assertDescontoCalculadoIrrf(subtraiDecimo(FAIXA_3.getLimiteInferior())	,FAIXA_2.getDeducaoFaixa(),	 15.0);
		assertDescontoCalculadoIrrf(subtraiDecimo(FAIXA_4.getLimiteInferior())	,FAIXA_3.getDeducaoFaixa(),  22.5);

	}
	
	@Test
	public void testaAliquotasIrrfQuandoBaseCalculoIgualLimitesInferioresFaixas()  {

		assertDescontoCalculadoIrrf(FAIXA_1.getLimiteInferior() ,FAIXA_1.getDeducaoFaixa(),   7.5);
		assertDescontoCalculadoIrrf(FAIXA_2.getLimiteInferior()	,FAIXA_2.getDeducaoFaixa(),  15.0);
		assertDescontoCalculadoIrrf(FAIXA_3.getLimiteInferior()	,FAIXA_3.getDeducaoFaixa(),	 22.5);
		assertDescontoCalculadoIrrf(FAIXA_4.getLimiteInferior()	,FAIXA_4.getDeducaoFaixa(),  27.5);
		
	}	
	
	@Test
	public void testaAliquotasIrrfQuandoBaseCalculoIgualLimitesSuperioresFaixas()  {

		assertDescontoCalculadoIrrf(FAIXA_1.getLimiteSuperior() ,FAIXA_1.getDeducaoFaixa(),   7.5);
		assertDescontoCalculadoIrrf(FAIXA_2.getLimiteSuperior()	,FAIXA_2.getDeducaoFaixa(),  15.0);
		assertDescontoCalculadoIrrf(FAIXA_3.getLimiteSuperior()	,FAIXA_3.getDeducaoFaixa(),	 22.5);
		assertDescontoCalculadoIrrf(FAIXA_4.getLimiteSuperior()	,FAIXA_4.getDeducaoFaixa(),  27.5);
		
	}	

	@Test
	public void testaAliquotasIrrfQuandoBaseCalculoMaiorLimitesSuperioresFaixas() {

		assertDescontoCalculadoIrrf(adicionaDecimo(FAIXA_1.getLimiteSuperior()) ,FAIXA_2.getDeducaoFaixa(),  15.0);
		assertDescontoCalculadoIrrf(adicionaDecimo(FAIXA_2.getLimiteSuperior())	,FAIXA_3.getDeducaoFaixa(),  22.5);
		assertDescontoCalculadoIrrf(adicionaDecimo(FAIXA_3.getLimiteSuperior())	,FAIXA_4.getDeducaoFaixa(),	 27.5);
		assertDescontoCalculadoIrrf(adicionaDecimo(FAIXA_4.getLimiteSuperior())	,FAIXA_4.getDeducaoFaixa(),  27.5);
		
	}	
	

}
