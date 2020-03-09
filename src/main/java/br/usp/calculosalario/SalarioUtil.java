package br.usp.calculosalario;

import java.math.BigDecimal;

public class SalarioUtil {

	private static final int NUMERO_CASAS_DECIMAS = 3;

	public static BigDecimal arred(BigDecimal valor) {
		if (valor!=null) {
			return valor.setScale(NUMERO_CASAS_DECIMAS, BigDecimal.ROUND_HALF_UP);
		}
		
		return valor;
	}
	
	public static BigDecimal toBigDecimal(double d) {
		return arred( BigDecimal.valueOf(d));
	}	
	
}
