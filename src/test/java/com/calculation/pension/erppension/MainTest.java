package com.calculation.pension.erppension;

import com.calculation.pension.erppension.domain.service.CalculadoraPatrimonio;
import com.calculation.pension.erppension.domain.service.Empresa;
import com.calculation.pension.erppension.domain.service.PessoaFisica;
import com.calculation.pension.erppension.domain.service.PessoaJuridica;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {

    private CalculadoraPatrimonio calculadora;
    private PessoaFisica pf1, pf2, pf3, pf4;
    private PessoaJuridica pj1, pj2;
    private Empresa empresaA, empresaB, empresaC, empresaD;

    @BeforeEach
    void setUp() {
        // Criação de pessoas físicas
        pf1 = new PessoaFisica("123.456.789-00", 1000000);
        pf2 = new PessoaFisica("987.654.321-00", 2000000);
        pf3 = new PessoaFisica("111.222.333-44", 500000);
        pf4 = new PessoaFisica("555.666.777-88", 1500000);

        // Criação de pessoas jurídicas
        pj1 = new PessoaJuridica("12.345.678/0001-00", 5000000);
        pj2 = new PessoaJuridica("98.765.432/0001-99", 3000000);

        // Criação de empresas
        empresaA = new Empresa("Empresa A");
        empresaB = new Empresa("Empresa B");
        empresaC = new Empresa("Empresa C");
        empresaD = new Empresa("Empresa D");

        // Definindo a estrutura societária
        empresaA.adicionarSocio(pf1);
        empresaA.adicionarSocio(pf2);
        empresaA.adicionarSocio(pj1);

        empresaB.adicionarSocio(pf3);
        empresaB.adicionarSocio(empresaA);

        empresaC.adicionarSocio(pf4);
        empresaC.adicionarSocio(pj2);

        empresaD.adicionarSocio(empresaC);
        empresaD.adicionarSocio(empresaB);

        // Instanciando a calculadora de patrimônio
        calculadora = new CalculadoraPatrimonio();
    }

    @Test
    void testCalculoPatrimonioEmpresaA() {
        double patrimonioEsperado = 1000000 + 2000000 + 5000000; // Soma dos patrimônios dos sócios
        double patrimonioCalculado = calculadora.calcularPatrimonio(empresaA);
        assertEquals(patrimonioEsperado, patrimonioCalculado, 0.01);
    }

    @Test
    void testCalculoPatrimonioEmpresaB() {
        double patrimonioEsperado = 500000 + (1000000 + 2000000 + 5000000); // Pessoa física + Empresa A
        double patrimonioCalculado = calculadora.calcularPatrimonio(empresaB);
        assertEquals(patrimonioEsperado, patrimonioCalculado, 0.01);
    }

    @Test
    void testCalculoPatrimonioEmpresaC() {
        double patrimonioEsperado = 1500000 + 3000000; // Pessoa física + Pessoa jurídica
        double patrimonioCalculado = calculadora.calcularPatrimonio(empresaC);
        assertEquals(patrimonioEsperado, patrimonioCalculado, 0.01);
    }

    @Test
    void testCalculoPatrimonioEmpresaD() {
        double patrimonioEsperado = (1500000 + 3000000) + (500000 + (1000000 + 2000000 + 5000000)); // Empresa C + Empresa B
        double patrimonioCalculado = calculadora.calcularPatrimonio(empresaD);
        assertEquals(patrimonioEsperado, patrimonioCalculado, 0.01);
    }


}