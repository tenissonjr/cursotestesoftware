package br.usp.icmc.labes.cursotestesoftware.calculosalario.teste;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses(
			{TesteClassesEquivalênciaValoresEntrada.class
			,TesteEstrutural.class
			,TesteValoresLimiteCalculoInss.class
			,TesteValoresLimiteCalculoIrrf.class 
			})
/**
 *  Suite de testes do cálcuro de slário líquido
 * 
 * @author tenissonjr
 *
 * Para execução do Teste de Análise de Mutantes executar maven com a propriedade GOAL org.pitest:pitest-maven:mutationCoverage
 *
 */
public class AllTests {

}
