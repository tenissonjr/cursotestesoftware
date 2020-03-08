package br.usp.teste.calculosalario;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import br.usp.calculosalario.Salario;
import br.usp.calculosalario.exception.DependenteInvalidoException;
import br.usp.calculosalario.exception.SalarioBrutoInvalidoException;
import br.usp.calculosalario.exception.SalarioException;

public class TesteClassesEquivalênciaValoresEntrada extends AbstractTest {

/*
 * 

+------------------------+---------------------------------+-----------------------------------+
|  Variáveis de Entrada  | Classes de Equivalência válidas | Classes de Equivalência inválidas |
+------------------------+---------------------------------+-----------------------------------+
| Salário bruto          |                         1,2,3.. | Null, 0 , <0                      |
| Qtd. dependentes de IR |                       0,1,2,3.. | Null, <0                          |
+------------------------+---------------------------------+-----------------------------------+


	
	
 */
	
	
	
	@Test
	void testaSalarioBrutoValidoQuantidaddeDependentesInvalido() throws SalarioException {

		Assertions.assertThrows(DependenteInvalidoException.class, () -> {calculadoraSalario.calcular(BigDecimal.valueOf(1.0),-1);});

	}	
	
	@Test
	void testaSalarioBrutoInvalidoQuantidaddeDependentesValido() throws SalarioException {

		Assertions.assertThrows(SalarioBrutoInvalidoException.class, () -> {calculadoraSalario.calcular(null,0);});
		Assertions.assertThrows(SalarioBrutoInvalidoException.class, () -> {calculadoraSalario.calcular(BigDecimal.valueOf(-1),0);});

	}


	@Test
	void testaSalarioBrutoInvalidoQuantidaddeDependentesInvalido() throws SalarioException {

		Assertions.assertThrows(SalarioBrutoInvalidoException.class, () -> {calculadoraSalario.calcular(null,0-1);});
		Assertions.assertThrows(SalarioBrutoInvalidoException.class, () -> {calculadoraSalario.calcular(BigDecimal.valueOf(-1),-3);});

	}	
	
/*
 * 

+--------------+--------+-----------------+-------------------+---------------+--------------+-----------------+---------------+----------------+-----------------+
|  EMPREGADO   | Nº DEP |     SALÁRIO     | Base Cálculo INSS | Alíquota INSS |     INSS     | Base Cálculo IR | Alíquota IRRF |       IR       | SALÁRIO LÍQUIDO |
+--------------+--------+-----------------+-------------------+---------------+--------------+-----------------+---------------+----------------+-----------------+
| Empregado 1  |      0 |  R$  1.000,00   |  R$  1.000,00     | 7,50%         |  R$  75,00   |  R$  925,00     | 0,00%         |  R$  -         |  R$  925,00     |
| Empregado 2  |      1 |  R$  1.000,00   |  R$  1.000,00     | 7,50%         |  R$  75,00   |  R$  735,41     | 0,00%         |  R$  -         |  R$  925,00     |
| Empregado 3  |      0 |  R$  2.000,00   |  R$  2.000,00     | 9,00%         |  R$  180,00  |  R$  1.820,00   | 0,00%         |  R$  -         |  R$  1.820,00   |
| Empregado 4  |      1 |  R$  2.000,00   |  R$  2.000,00     | 9,00%         |  R$  180,00  |  R$  1.630,41   | 0,00%         |  R$  -         |  R$  1.820,00   |
| Empregado 5  |      0 |  R$  3.000,00   |  R$  3.000,00     | 12,00%        |  R$  360,00  |  R$  2.640,00   | 7,50%         |  R$  55,20     |  R$  2.584,80   |
| Empregado 6  |      1 |  R$  3.000,00   |  R$  3.000,00     | 12,00%        |  R$  360,00  |  R$  2.450,41   | 7,50%         |  R$  40,98     |  R$  2.599,02   |
| Empregado 7  |      5 |  R$  3.000,00   |  R$  3.000,00     | 12,00%        |  R$  360,00  |  R$  1.692,05   | 0,00%         |  R$  -         |  R$  2.640,00   |
| Empregado 8  |      0 |  R$  4.000,00   |  R$  4.000,00     | 14,00%        |  R$  560,00  |  R$  3.440,00   | 15,00%        |  R$  161,20    |  R$  3.278,80   |
| Empregado 9  |      2 |  R$  4.000,00   |  R$  4.000,00     | 14,00%        |  R$  560,00  |  R$  3.060,82   | 15,00%        |  R$  104,32    |  R$  3.335,68   |
| Empregado 10 |      4 |  R$  4.000,00   |  R$  4.000,00     | 14,00%        |  R$  560,00  |  R$  2.681,64   | 7,50%         |  R$  58,32     |  R$  3.381,68   |
| Empregado 11 |      9 |  R$  4.000,00   |  R$  4.000,00     | 14,00%        |  R$  560,00  |  R$  1.733,69   | 0,00%         |  R$  -         |  R$  3.440,00   |
| Empregado 12 |      0 |  R$  5.000,00   |  R$  5.000,00     | 14,00%        |  R$  700,00  |  R$  4.300,00   | 22,50%        |  R$  331,37    |  R$  3.968,63   |
| Empregado 13 |      3 |  R$  5.000,00   |  R$  5.000,00     | 14,00%        |  R$  700,00  |  R$  3.731,23   | 15,00%        |  R$  204,88    |  R$  4.095,12   |
| Empregado 14 |      8 |  R$  5.000,00   |  R$  5.000,00     | 14,00%        |  R$  700,00  |  R$  2.783,28   | 7,50%         |  R$  65,95     |  R$  4.234,05   |
| Empregado 15 |     13 |  R$  5.000,00   |  R$  5.000,00     | 14,00%        |  R$  700,00  |  R$  1.835,33   | 0,00%         |  R$  -         |  R$  4.300,00   |
| Empregado 16 |      0 |  R$  8.000,00   |  R$  6.101,06     | 14,00%        |  R$  854,15  |  R$  7.145,85   | 27,50%        |  R$  1.095,75  |  R$  6.050,10   |
| Empregado 17 |     14 |  R$  8.000,00   |  R$  6.101,06     | 14,00%        |  R$  854,15  |  R$  4.491,59   | 22,50%        |  R$  374,48    |  R$  6.771,37   |
| Empregado 18 |     20 |  R$  8.000,00   |  R$  6.101,06     | 14,00%        |  R$  854,15  |  R$  3.354,05   | 15,00%        |  R$  148,31    |  R$  6.997,54   |
| Empregado 19 |     25 |  R$  8.000,00   |  R$  6.101,06     | 14,00%        |  R$  854,15  |  R$  2.406,10   | 7,50%         |  R$  37,66     |  R$  7.108,19   |
| Empregado 20 |     30 |  R$  8.000,00   |  R$  6.101,06     | 14,00%        |  R$  854,15  |  R$  1.458,15   | 0,00%         |  R$  -         |  R$  7.145,85   |
| Empregado 21 |      0 |  R$  10.000,00  |  R$  6.101,06     | 14,00%        |  R$  854,15  |  R$  9.145,85   | 27,50%        |  R$  1.645,75  |  R$  7.500,10   |
| Empregado 22 |     25 |  R$  10.000,00  |  R$  6.101,06     | 14,00%        |  R$  854,15  |  R$  4.406,10   | 22,50%        |  R$  355,24    |  R$  8.790,61   |
| Empregado 23 |     30 |  R$  10.000,00  |  R$  6.101,06     | 14,00%        |  R$  854,15  |  R$  3.458,15   | 15,00%        |  R$  163,92    |  R$  8.981,93   |
| Empregado 24 |     35 |  R$  10.000,00  |  R$  6.101,06     | 14,00%        |  R$  854,15  |  R$  2.510,20   | 7,50%         |  R$  45,47     |  R$  9.100,39   |
| Empregado 25 |     40 |  R$  10.000,00  |  R$  6.101,06     | 14,00%        |  R$  854,15  |  R$  1.562,25   | 0,00%         |  R$  -         |  R$  9.145,85   |
| Empregado 26 |      0 |  R$  15.000,00  |  R$  6.101,06     | 14,00%        |  R$  854,15  |  R$  14.145,85  | 27,50%        |  R$  3.020,75  |  R$  11.125,10  |
| Empregado 27 |      1 |  R$  18.000,00  |  R$  6.101,06     | 14,00%        |  R$  854,15  |  R$  16.956,26  | 27,50%        |  R$  3.793,61  |  R$  13.352,24  |
| Empregado 28 |      5 |  R$  20.000,00  |  R$  6.101,06     | 14,00%        |  R$  854,15  |  R$  18.197,90  | 27,50%        |  R$  4.135,06  |  R$  15.010,79  |
+--------------+--------+-----------------+-------------------+---------------+--------------+-----------------+---------------+----------------+-----------------+

	
 */
	
	protected void assertSalarioLiquido(double salarioBruto,  int dependentesImpostoRenda,double salarioLiquidoEsperado ) throws SalarioException {
	
		Salario salario = calculadoraSalario.calcular(toBigDecimal(salarioBruto),dependentesImpostoRenda);
		
		assertEquals(toBigDecimal(salarioLiquidoEsperado), salario.getSalarioLiquido())	;
		
		
	}
	
	@Test
	void testaSalarioBrutoValidoQuantidaddeDependentesValido_01() throws SalarioException {
		
		assertSalarioLiquido(1000,0,925.00);
		
	}

	@Test
	void testaSalarioBrutoValidoQuantidaddeDependentesValido_02() throws SalarioException {
		
		Salario empregado_02 = calculadoraSalario.calcular(toBigDecimal(1000.00),1);
		
		assertEquals(toBigDecimal(925.00), empregado_02.getSalarioLiquido());


	}
	
	@Test
	void testaSalarioBrutoValidoQuantidaddeDependentesValido_03() throws SalarioException {
		
		Salario empregado_03 = calculadoraSalario.calcular(toBigDecimal(2000.00),0);
		
		assertEquals(toBigDecimal(1820.00), empregado_03.getSalarioLiquido());


	}	

	@Test
	void testaSalarioBrutoValidoQuantidaddeDependentesValido_04() throws SalarioException {
		
		Salario empregado_04 = calculadoraSalario.calcular(toBigDecimal(2000.00),1);
		
		assertEquals(toBigDecimal(1820.00), empregado_04.getSalarioLiquido());

	}	
	
	@Test
	void testaSalarioBrutoValidoQuantidaddeDependentesValido_05() throws SalarioException {
		
		Salario empregado_05 = calculadoraSalario.calcular(toBigDecimal(3000.00),0);
		
		assertEquals(toBigDecimal(2584.80), empregado_05.getSalarioLiquido());

	}		
	
	@Test
	void testaSalarioBrutoValidoQuantidaddeDependentesValido_06() throws SalarioException {
		

		
		assertSalarioLiquido(3000,1,2599.02);

	}		
}
