package com.calculation.pension.erppension.domain.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CalculadoraPatrimonioTest {

    private CalculadoraPatrimonio calculadoraPatrimonio;

    @BeforeEach
    void setUp() {
        calculadoraPatrimonio = new CalculadoraPatrimonio();
    }

    @Test
    void calcularPatrimonio_comEmpresaSemSocios_retornaZero() {
        var empresa = Mockito.mock(Empresa.class);
        when(empresa.getSocios()).thenReturn(Set.of());

        double patrimonio = calculadoraPatrimonio.calcularPatrimonio(empresa);

        assertEquals(0, patrimonio, "O patrimônio de uma empresa sem sócios deve ser zero.");
    }

    @Test
    void calcularPatrimonio_comPessoaFisica_retornaValorBensImoveis() {
        var socio = Mockito.mock(PessoaFisica.class);
        when(socio.getValorBensImoveis()).thenReturn(100_000.0);

        var empresa = Mockito.mock(Empresa.class);
        when(empresa.getSocios()).thenReturn(Set.of(socio));

        double patrimonio = calculadoraPatrimonio.calcularPatrimonio(empresa);

        assertEquals(100_000.0, patrimonio, "O patrimônio deve ser igual ao valor dos bens imóveis do sócio pessoa física.");
    }

    @Test
    void calcularPatrimonio_comPessoaJuridica_retornaValorBensImoveis() {
        var socio = Mockito.mock(PessoaJuridica.class);
        when(socio.getValorBensImoveis()).thenReturn(500_000.0);

        var empresa = Mockito.mock(Empresa.class);
        when(empresa.getSocios()).thenReturn(Set.of(socio));

        double patrimonio = calculadoraPatrimonio.calcularPatrimonio(empresa);

        assertEquals(500_000.0, patrimonio, "O patrimônio deve ser igual ao valor dos bens imóveis do sócio pessoa jurídica.");
    }

    @Test
    void calcularPatrimonio_comSocioJaContabilizado_retornaValorSemDuplicar() {
        var socio = Mockito.mock(PessoaFisica.class);
        when(socio.getValorBensImoveis()).thenReturn(200_000.0);

        var empresa = Mockito.mock(Empresa.class);
        when(empresa.getSocios()).thenReturn(Set.of(socio));

        double patrimonio1 = calculadoraPatrimonio.calcularPatrimonio(empresa);

        double patrimonio2 = calculadoraPatrimonio.calcularPatrimonio(empresa);

        assertEquals(200_000.0, patrimonio1, "O patrimônio inicial deve ser igual ao valor do sócio.");
        assertEquals(0, patrimonio2, "O patrimônio deve ser zero quando o sócio já foi contabilizado.");
    }

    @Test
    void calcularPatrimonio_comEmpresaComoSocio_retornaValorTotalRecursivo() {
        var socio1 = Mockito.mock(PessoaFisica.class);
        when(socio1.getValorBensImoveis()).thenReturn(150_000.0);

        var socio2 = Mockito.mock(PessoaFisica.class);
        when(socio2.getValorBensImoveis()).thenReturn(50_000.0);

        var empresaFilha = Mockito.mock(Empresa.class);
        when(empresaFilha.getSocios()).thenReturn(Set.of(socio2));

        var empresaMae = Mockito.mock(Empresa.class);
        when(empresaMae.getSocios()).thenReturn(Set.of(socio1, empresaFilha));

        double patrimonio = calculadoraPatrimonio.calcularPatrimonio(empresaMae);

        assertEquals(200_000.0, patrimonio, "O patrimônio deve somar o valor dos bens imóveis do sócio e da empresa filha.");
    }

    @Test
    void calcularPatrimonio_comCicloEntreEmpresas_retornaSemLoopInfinito() {
        var empresaA = Mockito.mock(Empresa.class);
        var empresaB = Mockito.mock(Empresa.class);

        when(empresaA.getSocios()).thenReturn(Set.of(empresaB));
        when(empresaB.getSocios()).thenReturn(Set.of(empresaA));

        double patrimonio = calculadoraPatrimonio.calcularPatrimonio(empresaA);

        assertEquals(0, patrimonio, "O patrimônio deve ser calculado corretamente mesmo com ciclo entre empresas.");
    }
}
