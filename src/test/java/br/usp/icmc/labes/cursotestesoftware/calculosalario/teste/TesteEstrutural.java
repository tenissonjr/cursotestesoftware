package br.usp.icmc.labes.cursotestesoftware.calculosalario.teste;

import org.junit.Test;

import static br.usp.icmc.labes.cursotestesoftware.calculosalario.util.CalculadoraSalarioUtil.toBigDecimal;
import static org.junit.Assert.assertEquals;

import br.usp.icmc.labes.cursotestesoftware.calculosalario.CalculadoraSalario;
import br.usp.icmc.labes.cursotestesoftware.calculosalario.dominio.Salario;
import br.usp.icmc.labes.cursotestesoftware.calculosalario.exception.SalarioException;
import br.usp.icmc.labes.cursotestesoftware.calculosalario.util.CalculadoraSalarioUtil;

public class TesteEstrutural {

	private CalculadoraSalario calculadoraSalario = new CalculadoraSalario();	
	
		
	private void assertSalarioLiquidoCalculado(Salario salarioEsperado) throws SalarioException {
		
		Salario salarioCalculado = calculadoraSalario.calcular(salarioEsperado.getSalarioBruto()
																,salarioEsperado.getDependentesImpostoRenda());
		
		
		assertEquals(salarioEsperado.getInns().getBaseCalculo()	, salarioCalculado.getInns().getBaseCalculo())	;
		assertEquals(salarioEsperado.getInns().getAliquota()	, salarioCalculado.getInns().getAliquota())	;
		assertEquals(salarioEsperado.getInns().getValor()		, salarioCalculado.getInns().getValor())	;
		
		assertEquals(salarioEsperado.getIrrf().getDeducaoTotalDevidoADependentes()	, salarioCalculado.getIrrf().getDeducaoTotalDevidoADependentes())	;
		assertEquals(salarioEsperado.getIrrf().getBaseCalculo()	, salarioCalculado.getIrrf().getBaseCalculo())	;
		assertEquals(salarioEsperado.getIrrf().getAliquota()	, salarioCalculado.getIrrf().getAliquota())	;
		
		assertEquals(salarioEsperado.getIrrf().getValor()		, salarioCalculado.getIrrf().getValor())	;

		
		assertEquals(salarioEsperado.getSalarioLiquido(), salarioCalculado.getSalarioLiquido())	;
		
		
		
	}
	
	
/*
 * 

+--------------+--------+-----------------+-------------------+---------------+--------------+-----------------+---------------+----------------+-----------------+
|  EMPREGADO   | Nº DEP |     SALÁRIO     | Base Cálculo INSS | Alíquota INSS |     INSS     | Base Cálculo IR | Alíquota IRRF |       IR       | SALÁRIO LÍQUIDO |
+--------------+--------+-----------------+-------------------+---------------+--------------+-----------------+---------------+----------------+-----------------+
| Empregado 0  |      0 |  R$  	   0,00   |  R$      0,00     | 7,50%         |  R$   0,00   |  R$    0,00     | 7,50%         |  R$  -         |  R$    0,00     |
| Empregado 1  |      0 |  R$  1.000,00   |  R$  1.000,00     | 7,50%         |  R$  75,00   |  R$  925,00     | 7,50%         |  R$  -         |  R$  925,00     |
| Empregado 2  |      1 |  R$  1.000,00   |  R$  1.000,00     | 7,50%         |  R$  75,00   |  R$  735,41     | 7,50%         |  R$  -         |  R$  925,00     |
| Empregado 3  |      0 |  R$  2.000,00   |  R$  2.000,00     | 9,00%         |  R$  180,00  |  R$  1.820,00   | 7,50%         |  R$  -         |  R$  1.820,00   |
| Empregado 4  |      1 |  R$  2.000,00   |  R$  2.000,00     | 9,00%         |  R$  180,00  |  R$  1.630,41   | 7,50%         |  R$  -         |  R$  1.820,00   |
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

	
	@Test
	public void testaSalarioBrutoValidoQuantidaddeDependentesValido_00() throws SalarioException {
		
		
		Salario salarioEsperado = new Salario(toBigDecimal(0),0)
				.baseCalculoInns(toBigDecimal(0.00))
				.aliquotaInss(toBigDecimal(7.5))
				.valorInss(toBigDecimal(0))
				
				.deducaoTotalDevidoADependentesIrrf(toBigDecimal(0))
				.baseCalculoIrrf(toBigDecimal(0))
				.aliquotaIrrf(toBigDecimal(0))
				.valorIrrf(toBigDecimal(0))
				
				.salarioLiquido(toBigDecimal(0.00)) ;		
		
		
		
		assertSalarioLiquidoCalculado(salarioEsperado);
		
	}
	


	@Test
	public void testaSalarioBrutoValidoQuantidaddeDependentesValido_01() throws SalarioException {
		
		Salario salarioEsperado = new Salario(toBigDecimal(1000),0)
			.baseCalculoInns(toBigDecimal(1000.00))
			.aliquotaInss(toBigDecimal(7.5))
			.valorInss(toBigDecimal(75))
			
			.deducaoTotalDevidoADependentesIrrf(toBigDecimal(0))
			.baseCalculoIrrf(toBigDecimal(925.00))
			.aliquotaIrrf(toBigDecimal(0))
			.valorIrrf(toBigDecimal(0))
			
			.salarioLiquido(toBigDecimal(925.00)) ;		
		
		assertSalarioLiquidoCalculado(salarioEsperado);	
		
	
		
	}

	@Test
	public void testaSalarioBrutoValidoQuantidaddeDependentesValido_02() throws SalarioException {
		
		Salario salarioEsperado = new Salario(toBigDecimal(1000),1)
				.baseCalculoInns(toBigDecimal(1000.00))
				.aliquotaInss(toBigDecimal(7.5))
				.valorInss(toBigDecimal(75))
				
				.deducaoTotalDevidoADependentesIrrf(toBigDecimal(189.59))
				.baseCalculoIrrf(toBigDecimal(735.41))
				.aliquotaIrrf(toBigDecimal(0))
				.valorIrrf(toBigDecimal(0))
				
				.salarioLiquido(toBigDecimal(925.00)) ;		
			
			assertSalarioLiquidoCalculado(salarioEsperado);	
		

	}
	
		
	@Test
	public void testaSalarioBrutoValidoQuantidaddeDependentesValido_03() throws SalarioException {
		
		Salario salarioEsperado = new Salario(toBigDecimal(2000),0)
				.baseCalculoInns(toBigDecimal(2000.00))
				.aliquotaInss(toBigDecimal(9.0))
				.valorInss(toBigDecimal(180))
				
				.deducaoTotalDevidoADependentesIrrf(toBigDecimal(0))
				.baseCalculoIrrf(toBigDecimal(1820))
				.aliquotaIrrf(toBigDecimal(0))
				.valorIrrf(toBigDecimal(0))
				
				.salarioLiquido(toBigDecimal(1820.00)) ;		
			
			assertSalarioLiquidoCalculado(salarioEsperado);	


	}	

	
	@Test
	public void testaSalarioBrutoValidoQuantidaddeDependentesValido_04() throws SalarioException {
		
		Salario salarioEsperado = new Salario(toBigDecimal(2000),1)
				.baseCalculoInns(toBigDecimal(2000.00))
				.aliquotaInss(toBigDecimal(9.0))
				.valorInss(toBigDecimal(180))
				
				.deducaoTotalDevidoADependentesIrrf(toBigDecimal(189.59))
				.baseCalculoIrrf(toBigDecimal(1630.41))
				.aliquotaIrrf(toBigDecimal(0))
				.valorIrrf(toBigDecimal(0))
				
				.salarioLiquido(toBigDecimal(1820.00)) ;		
			
			assertSalarioLiquidoCalculado(salarioEsperado);	

	}	
	
	
	@Test
	public void testaSalarioBrutoValidoQuantidaddeDependentesValido_05() throws SalarioException {
		

		Salario salarioEsperado = new Salario(toBigDecimal(3000),0)
				.baseCalculoInns(toBigDecimal(3000.00))
				.aliquotaInss(toBigDecimal(12.0))
				.valorInss(toBigDecimal(360))
				
				.deducaoTotalDevidoADependentesIrrf(toBigDecimal(0))
				.baseCalculoIrrf(toBigDecimal(2640))
				.aliquotaIrrf(toBigDecimal(7.5))
				.valorIrrf(toBigDecimal(55.20))
				
				.salarioLiquido(toBigDecimal(2584.80)) ;		
			
			assertSalarioLiquidoCalculado(salarioEsperado);	
		
		
	}		
	
	
	@Test
	public void testaSalarioBrutoValidoQuantidaddeDependentesValido_06() throws SalarioException {
		

		Salario salarioEsperado = new Salario(toBigDecimal(3000),1)
				
				.baseCalculoInns(toBigDecimal(3000.00))
				.aliquotaInss(toBigDecimal(12))
				.valorInss(toBigDecimal(360))
				
				.deducaoTotalDevidoADependentesIrrf(toBigDecimal(189.59))
				.baseCalculoIrrf(toBigDecimal(2450.41))
				.aliquotaIrrf(toBigDecimal(7.5))
				.valorIrrf(toBigDecimal(40.98))
				
				.salarioLiquido(toBigDecimal(2599.02)) ;

		assertSalarioLiquidoCalculado(salarioEsperado);			
		
	}	
	
	@Test
	public void testaSalarioBrutoValidoQuantidaddeDependentesValido_07() throws SalarioException {
		

		Salario salarioEsperado = new Salario(toBigDecimal(3000),5)
				
				.baseCalculoInns(toBigDecimal(3000.00))
				.aliquotaInss(toBigDecimal(12))
				.valorInss(toBigDecimal(360))
				
				.deducaoTotalDevidoADependentesIrrf(toBigDecimal(189.59*5))
				.baseCalculoIrrf(toBigDecimal(1692.05))
				.aliquotaIrrf(toBigDecimal(0))
				.valorIrrf(toBigDecimal(0))
				
				.salarioLiquido(toBigDecimal(2640.00)) ;

		assertSalarioLiquidoCalculado(salarioEsperado);			
		
	}	

	@Test
	public void testaSalarioBrutoValidoQuantidaddeDependentesValido_08() throws SalarioException {
		

		Salario salarioEsperado = new Salario(toBigDecimal(4000),0)
				
				.baseCalculoInns(toBigDecimal(4000.00))
				.aliquotaInss(toBigDecimal(14))
				.valorInss(toBigDecimal(560))
				
				.deducaoTotalDevidoADependentesIrrf(toBigDecimal(189.59*0))
				.baseCalculoIrrf(toBigDecimal(3440))
				.aliquotaIrrf(toBigDecimal(15))
				.valorIrrf(toBigDecimal(161.2))
				
				.salarioLiquido(toBigDecimal(3278.8)) ;

		assertSalarioLiquidoCalculado(salarioEsperado);			
		
	}	
	
	@Test
	public void testaSalarioBrutoValidoQuantidaddeDependentesValido_09() throws SalarioException {
		

		Salario salarioEsperado = new Salario(toBigDecimal(4000),2)
				
				.baseCalculoInns(toBigDecimal(4000.00))
				.aliquotaInss(toBigDecimal(14))
				.valorInss(toBigDecimal(560))
				
				.deducaoTotalDevidoADependentesIrrf(toBigDecimal(189.59*2))
				.baseCalculoIrrf(toBigDecimal(3060.82))
				.aliquotaIrrf(toBigDecimal(15))
				.valorIrrf(toBigDecimal(104.32))
				
				.salarioLiquido(toBigDecimal(3335.68)) ;

		assertSalarioLiquidoCalculado(salarioEsperado);			
		
	}		
	
	@Test
	public void testaSalarioBrutoValidoQuantidaddeDependentesValido_10() throws SalarioException {
		

		Salario salarioEsperado = new Salario(toBigDecimal(4000),4)
				
				.baseCalculoInns(toBigDecimal(4000.00))
				.aliquotaInss(toBigDecimal(14))
				.valorInss(toBigDecimal(560))
				
				.deducaoTotalDevidoADependentesIrrf(toBigDecimal(189.59*4))
				.baseCalculoIrrf(toBigDecimal(2681.64))
				.aliquotaIrrf(toBigDecimal(7.5))
				.valorIrrf(toBigDecimal(58.32))
				
				.salarioLiquido(toBigDecimal(3381.68)) ;

		assertSalarioLiquidoCalculado(salarioEsperado);			
		
	}	

	
	@Test
	public void testaSalarioBrutoValidoQuantidaddeDependentesValido_11() throws SalarioException {
		

		Salario salarioEsperado = new Salario(toBigDecimal(4000),9)
				
				.baseCalculoInns(toBigDecimal(4000.00))
				.aliquotaInss(toBigDecimal(14))
				.valorInss(toBigDecimal(560))
				
				.deducaoTotalDevidoADependentesIrrf(toBigDecimal(189.59*9))
				.baseCalculoIrrf(toBigDecimal(1733.69))
				.aliquotaIrrf(toBigDecimal(0))
				.valorIrrf(toBigDecimal(0))
				
				.salarioLiquido(toBigDecimal(3440.00)) ;

		assertSalarioLiquidoCalculado(salarioEsperado);			
		
	}	

	@Test
	public void testaSalarioBrutoValidoQuantidaddeDependentesValido_12() throws SalarioException {
		

		Salario salarioEsperado = new Salario(toBigDecimal(5000),0)
				
				.baseCalculoInns(toBigDecimal(5000.00))
				.aliquotaInss(toBigDecimal(14))
				.valorInss(toBigDecimal(700))
				
				.deducaoTotalDevidoADependentesIrrf(toBigDecimal(189.59*0))
				.baseCalculoIrrf(toBigDecimal(4300))
				.aliquotaIrrf(toBigDecimal(22.5))
				.valorIrrf(toBigDecimal(331.37))
				
				.salarioLiquido(toBigDecimal(3968.63)) ;

		assertSalarioLiquidoCalculado(salarioEsperado);			
		
	}	
		
	@Test
	public void testaSalarioBrutoValidoQuantidaddeDependentesValido_13() throws SalarioException {
		

		Salario salarioEsperado = new Salario(toBigDecimal(5000),3)
				
				.baseCalculoInns(toBigDecimal(5000.00))
				.aliquotaInss(toBigDecimal(14))
				.valorInss(toBigDecimal(700))
				
				.deducaoTotalDevidoADependentesIrrf(toBigDecimal(189.59*3))
				.baseCalculoIrrf(toBigDecimal(3731.23))
				.aliquotaIrrf(toBigDecimal(15))
				.valorIrrf(toBigDecimal(204.88))
				
				.salarioLiquido(toBigDecimal(4095.12)) ;

		assertSalarioLiquidoCalculado(salarioEsperado);			
		
	}	
	
	@Test
	public void testaSalarioBrutoValidoQuantidaddeDependentesValido_14() throws SalarioException {
		

		Salario salarioEsperado = new Salario(toBigDecimal(5000),8)
				
				.baseCalculoInns(toBigDecimal(5000.00))
				.aliquotaInss(toBigDecimal(14))
				.valorInss(toBigDecimal(700))
				
				.deducaoTotalDevidoADependentesIrrf(toBigDecimal(189.59*8))
				.baseCalculoIrrf(toBigDecimal(2783.28))
				.aliquotaIrrf(toBigDecimal(7.5))
				.valorIrrf(toBigDecimal(65.95))
				
				.salarioLiquido(toBigDecimal(4234.05)) ;

		assertSalarioLiquidoCalculado(salarioEsperado);			
		
	}		
	

	@Test
	public void testaSalarioBrutoValidoQuantidaddeDependentesValido_15() throws SalarioException {
		

		Salario salarioEsperado = new Salario(toBigDecimal(5000),13)
				
				.baseCalculoInns(toBigDecimal(5000.00))
				.aliquotaInss(toBigDecimal(14))
				.valorInss(toBigDecimal(700))
				
				.deducaoTotalDevidoADependentesIrrf(toBigDecimal(189.59*13))
				.baseCalculoIrrf(toBigDecimal(1835.33))
				.aliquotaIrrf(toBigDecimal(0))
				.valorIrrf(toBigDecimal(0))
				
				.salarioLiquido(toBigDecimal(4300)) ;

		assertSalarioLiquidoCalculado(salarioEsperado);			
		
	}		
	
	@Test
	public void testaSalarioBrutoValidoQuantidaddeDependentesValido_16() throws SalarioException {
		

		Salario salarioEsperado = new Salario(toBigDecimal(8000),0)
				
				.baseCalculoInns(toBigDecimal(6101.06))
				.aliquotaInss(toBigDecimal(14))
				.valorInss(toBigDecimal(854.15))
				
				.deducaoTotalDevidoADependentesIrrf(toBigDecimal(189.59*0))
				.baseCalculoIrrf(toBigDecimal(7145.85))
				.aliquotaIrrf(toBigDecimal(27.5))
				.valorIrrf(toBigDecimal(1095.75))
				
				.salarioLiquido(toBigDecimal(6050.1)) ;

		assertSalarioLiquidoCalculado(salarioEsperado);			
		
	}		

	@Test
	public void testaSalarioBrutoValidoQuantidaddeDependentesValido_17() throws SalarioException {
		

		Salario salarioEsperado = new Salario(toBigDecimal(8000),14)
				
				.baseCalculoInns(toBigDecimal(6101.06))
				.aliquotaInss(toBigDecimal(14))
				.valorInss(toBigDecimal(854.15))
				
				.deducaoTotalDevidoADependentesIrrf(toBigDecimal(189.59*14))
				.baseCalculoIrrf(toBigDecimal(4491.59))
				.aliquotaIrrf(toBigDecimal(22.5))
				.valorIrrf(toBigDecimal(374.48))
				
				.salarioLiquido(toBigDecimal(6771.37)) ;

		assertSalarioLiquidoCalculado(salarioEsperado);			
		
	}		
	
	@Test
	public void testaSalarioBrutoValidoQuantidaddeDependentesValido_18() throws SalarioException {
		

		Salario salarioEsperado = new Salario(toBigDecimal(8000),20)
				
				.baseCalculoInns(toBigDecimal(6101.06))
				.aliquotaInss(toBigDecimal(14))
				.valorInss(toBigDecimal(854.15))
				
				.deducaoTotalDevidoADependentesIrrf(toBigDecimal(189.59*20))
				.baseCalculoIrrf(toBigDecimal(3354.05))
				.aliquotaIrrf(toBigDecimal(15.0))
				.valorIrrf(toBigDecimal(148.31))
				
				.salarioLiquido(toBigDecimal(6997.54)) ;

		assertSalarioLiquidoCalculado(salarioEsperado);			
		
	}		
	
	@Test
	public void testaSalarioBrutoValidoQuantidaddeDependentesValido_19() throws SalarioException {
		

		Salario salarioEsperado = new Salario(toBigDecimal(8000),25)
				
				.baseCalculoInns(toBigDecimal(6101.06))
				.aliquotaInss(toBigDecimal(14))
				.valorInss(toBigDecimal(854.15))
				
				.deducaoTotalDevidoADependentesIrrf(toBigDecimal(189.59*25))
				.baseCalculoIrrf(toBigDecimal(2406.10))
				.aliquotaIrrf(toBigDecimal(7.5))
				.valorIrrf(toBigDecimal(37.66))
				
				.salarioLiquido(toBigDecimal(7108.19)) ;

		assertSalarioLiquidoCalculado(salarioEsperado);			
		
	}	
	
	@Test
	public void testaSalarioBrutoValidoQuantidaddeDependentesValido_20() throws SalarioException {
		

		Salario salarioEsperado = new Salario(toBigDecimal(8000),30)
				
				.baseCalculoInns(toBigDecimal(6101.06))
				.aliquotaInss(toBigDecimal(14))
				.valorInss(toBigDecimal(854.15))
				
				.deducaoTotalDevidoADependentesIrrf(toBigDecimal(189.59*30))
				.baseCalculoIrrf(toBigDecimal(1458.15))
				.aliquotaIrrf(toBigDecimal(0))
				.valorIrrf(toBigDecimal(0))
				
				.salarioLiquido(toBigDecimal(7145.85)) ;

		assertSalarioLiquidoCalculado(salarioEsperado);			
		
	}		
	
	@Test
	public void testaSalarioBrutoValidoQuantidaddeDependentesValido_21() throws SalarioException {
		

		Salario salarioEsperado = new Salario(toBigDecimal(10000),0)
				
				.baseCalculoInns(toBigDecimal(6101.06))
				.aliquotaInss(toBigDecimal(14))
				.valorInss(toBigDecimal(854.15))
				
				.deducaoTotalDevidoADependentesIrrf(toBigDecimal(189.59*0))
				.baseCalculoIrrf(toBigDecimal(9145.85))
				.aliquotaIrrf(toBigDecimal(27.5))
				.valorIrrf(toBigDecimal(1645.75))
				
				.salarioLiquido(toBigDecimal(7500.10)) ;

		assertSalarioLiquidoCalculado(salarioEsperado);			
		
	}		
			
	@Test
	public void testaSalarioBrutoValidoQuantidaddeDependentesValido_22() throws SalarioException {
		

		Salario salarioEsperado = new Salario(toBigDecimal(10000),25)
				
				.baseCalculoInns(toBigDecimal(6101.06))
				.aliquotaInss(toBigDecimal(14))
				.valorInss(toBigDecimal(854.15))
				
				.deducaoTotalDevidoADependentesIrrf(toBigDecimal(189.59*25))
				.baseCalculoIrrf(toBigDecimal(4406.10))
				.aliquotaIrrf(toBigDecimal(22.5))
				.valorIrrf(toBigDecimal(355.24))
				
				.salarioLiquido(toBigDecimal(8790.61)) ;

		assertSalarioLiquidoCalculado(salarioEsperado);			
		
	}
	
	@Test
	public void testaSalarioBrutoValidoQuantidaddeDependentesValido_23() throws SalarioException {
		

		Salario salarioEsperado = new Salario(toBigDecimal(10000),30)
				
				.baseCalculoInns(toBigDecimal(6101.06))
				.aliquotaInss(toBigDecimal(14))
				.valorInss(toBigDecimal(854.15))
				
				.deducaoTotalDevidoADependentesIrrf(toBigDecimal(189.59*30))
				.baseCalculoIrrf(toBigDecimal(3458.15))
				.aliquotaIrrf(toBigDecimal(15))
				.valorIrrf(toBigDecimal(163.92))
				
				.salarioLiquido(toBigDecimal(8981.93)) ;

		assertSalarioLiquidoCalculado(salarioEsperado);			
		
	}


	@Test
	public void testaSalarioBrutoValidoQuantidaddeDependentesValido_24() throws SalarioException {
		

		Salario salarioEsperado = new Salario(toBigDecimal(10000),35)
				
				.baseCalculoInns(toBigDecimal(6101.06))
				.aliquotaInss(toBigDecimal(14))
				.valorInss(toBigDecimal(854.15))
				
				.deducaoTotalDevidoADependentesIrrf(toBigDecimal(189.59*35))
				.baseCalculoIrrf(toBigDecimal(2510.20))
				.aliquotaIrrf(toBigDecimal(7.5))
				.valorIrrf(toBigDecimal(45.47))
				
				.salarioLiquido(toBigDecimal(9100.38)) ;

		assertSalarioLiquidoCalculado(salarioEsperado);			
		
	}	
	
	@Test
	public void testaSalarioBrutoValidoQuantidaddeDependentesValido_25() throws SalarioException {
		

		Salario salarioEsperado = new Salario(toBigDecimal(10000),40)
				
				.baseCalculoInns(toBigDecimal(6101.06))
				.aliquotaInss(toBigDecimal(14))
				.valorInss(toBigDecimal(854.15))
				
				.deducaoTotalDevidoADependentesIrrf(toBigDecimal(189.59*40))
				.baseCalculoIrrf(toBigDecimal(1562.25))
				.aliquotaIrrf(toBigDecimal(0))
				.valorIrrf(toBigDecimal(0))
				
				.salarioLiquido(toBigDecimal(9145.85)) ;

		assertSalarioLiquidoCalculado(salarioEsperado);			
		
	}	
	
	@Test
	public void testaSalarioBrutoValidoQuantidaddeDependentesValido_26() throws SalarioException {
		

		Salario salarioEsperado = new Salario(toBigDecimal(15000),0)
				
				.baseCalculoInns(toBigDecimal(6101.06))
				.aliquotaInss(toBigDecimal(14))
				.valorInss(toBigDecimal(854.15))
				
				.deducaoTotalDevidoADependentesIrrf(toBigDecimal(189.59*0))
				.baseCalculoIrrf(toBigDecimal(14145.85))
				.aliquotaIrrf(toBigDecimal(27.5))
				.valorIrrf(toBigDecimal(3020.75))
				
				.salarioLiquido(toBigDecimal(11125.10)) ;

		assertSalarioLiquidoCalculado(salarioEsperado);			
		
	}		

	@Test
	public void testaSalarioBrutoValidoQuantidaddeDependentesValido_27() throws SalarioException {
		

		Salario salarioEsperado = new Salario(toBigDecimal(18000),1)
				
				.baseCalculoInns(toBigDecimal(6101.06))
				.aliquotaInss(toBigDecimal(14))
				.valorInss(toBigDecimal(854.15))
				
				.deducaoTotalDevidoADependentesIrrf(toBigDecimal(189.59*1))
				.baseCalculoIrrf(toBigDecimal(16956.26))
				.aliquotaIrrf(toBigDecimal(27.5))
				.valorIrrf(toBigDecimal(3793.61))
				
				.salarioLiquido(toBigDecimal(13352.24)) ;
		
		CalculadoraSalarioUtil util = new CalculadoraSalarioUtil();

		assertSalarioLiquidoCalculado(salarioEsperado);			
		
	}		
	
	@Test
	public void testaSalarioBrutoValidoQuantidaddeDependentesValido_28() throws SalarioException {
		

		Salario salarioEsperado = new Salario(toBigDecimal(20000),5)
				
				.baseCalculoInns(toBigDecimal(6101.06))
				.aliquotaInss(toBigDecimal(14))
				.valorInss(toBigDecimal(854.15))
				
				.deducaoTotalDevidoADependentesIrrf(toBigDecimal(189.59*5))
				.baseCalculoIrrf(toBigDecimal(18197.90))
				.aliquotaIrrf(toBigDecimal(27.5))
				.valorIrrf(toBigDecimal(4135.06))
				
				.salarioLiquido(toBigDecimal(15010.79)) ;

		assertSalarioLiquidoCalculado(salarioEsperado);			
		
	}		
		
	
	

}
