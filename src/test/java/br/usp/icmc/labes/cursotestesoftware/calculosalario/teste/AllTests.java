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
public class AllTests {

}
