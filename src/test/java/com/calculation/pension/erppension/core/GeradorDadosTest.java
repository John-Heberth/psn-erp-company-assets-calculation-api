package com.calculation.pension.erppension.core;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GeradorDadosTest {

    @Test
    public void testGerarCPF() {
        var gerador = new GeradorDados();
        var cpf = gerador.gerarCPF();

        assertEquals(11, cpf.length(), "O CPF deve ter 11 dígitos");
        assertTrue(cpf.matches("\\d{11}"), "O CPF deve conter apenas números");
    }

    @Test
    public void testGerarCNPJ() {
        var gerador = new GeradorDados();
        var cnpj = gerador.gerarCNPJ();

        assertEquals(14, cnpj.length(), "O CNPJ deve ter 14 dígitos");
        assertTrue(cnpj.matches("\\d{14}"), "O CNPJ deve conter apenas números");
        assertTrue(cnpj.substring(8, 12).equals("0001"), "O CNPJ deve ter '0001' como filial");
    }

    @Test
    public void testGerarValorBens() {
        var gerador = new GeradorDados();
        double valorBens = gerador.gerarValorBens();

        assertTrue(valorBens >= 100_000 && valorBens <= 10_000_000, "O valor dos bens deve estar entre 100.000 e 10.000.000");
    }

    @Test
    public void testGerarNomeEmpresa() {
        var gerador = new GeradorDados();
        var nomeEmpresa = gerador.gerarNomeEmpresa();

        assertTrue(nomeEmpresa.length() >= 5, "O nome da empresa deve ter pelo menos 5 caracteres");
        assertTrue(nomeEmpresa.contains(" "), "O nome da empresa deve conter um espaço entre nome e tipo");
    }
}