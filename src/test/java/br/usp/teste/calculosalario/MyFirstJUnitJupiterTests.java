package br.usp.teste.calculosalario;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;



import org.junit.jupiter.api.Test;


import br.usp.calculosalario.Salario;
import br.usp.calculosalario.SalarioBuilder;

class TestesSalario {

    

    @Test
    void addition() {
    	
    	BigDecimal salarioBruto = BigDecimal.ZERO;
    	
    	Salario salario =SalarioBuilder.getSalario(salarioBruto);
    	
        assertEquals(BigDecimal.ZERO, salario.getSalarioBruto());
    }

}