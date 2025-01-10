package com.calculation.pension.erppension.domain.service;

import com.calculation.pension.erppension.domain.exception.BusinessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmpresaTest {

    private Empresa empresa;

    @BeforeEach
    void setUp() {
        empresa = new Empresa("Empresa Teste");
    }

    @Test
    void getNome_retornaNomeCorreto() {
        assertEquals("Empresa Teste", empresa.getNome(), "O nome da empresa deve ser retornado corretamente.");
    }

    @Test
    void getSocios_inicialmenteVazio() {
        assertNotNull(empresa.getSocios(), "A lista de sócios deve ser inicializada.");
        assertTrue(empresa.getSocios().isEmpty(), "A lista de sócios deve estar vazia inicialmente.");
    }

    @Test
    void adicionarSocio_comPessoaFisica_socioAdicionado() {
        var cpfValido = "12345678909";
        var socio = new PessoaFisica(cpfValido, 100_000.0);

        empresa.adicionarSocio(socio);

        Set<Object> socios = empresa.getSocios();
        assertTrue(socios.contains(socio), "O sócio pessoa física deve ser adicionado à lista.");
        assertEquals(1, socios.size(), "A lista de sócios deve conter exatamente um elemento.");
    }


    @Test
    void adicionarSocio_comPessoaJuridica_socioAdicionado() {
        var cnpjValido = "12345678000195";

        var socio = new PessoaJuridica(cnpjValido, 500_000.0);

        empresa.adicionarSocio(socio);

        Set<Object> socios = empresa.getSocios();
        assertTrue(socios.contains(socio), "O sócio pessoa jurídica deve ser adicionado à lista.");
        assertEquals(1, socios.size(), "A lista de sócios deve conter exatamente um elemento.");
    }

    @Test
    void adicionarSocio_comEmpresa_socioAdicionado() {
        var empresaSocio = new Empresa("Empresa Filha");

        empresa.adicionarSocio(empresaSocio);

        Set<Object> socios = empresa.getSocios();
        assertTrue(socios.contains(empresaSocio), "A empresa sócia deve ser adicionada à lista.");
        assertEquals(1, socios.size(), "A lista de sócios deve conter exatamente um elemento.");
    }

    @Test
    void adicionarSocio_comNulo_disparaExcecao() {
        var exception = assertThrows(BusinessException.class, () -> empresa.adicionarSocio(null),
                "Adicionar um sócio nulo deve lançar uma exceção.");

        assertEquals("Sócio não pode ser nulo", exception.getMessage(), "A mensagem da exceção deve ser correta.");
    }


}