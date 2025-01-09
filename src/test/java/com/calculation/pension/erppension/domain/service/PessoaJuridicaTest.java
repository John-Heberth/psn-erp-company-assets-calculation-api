package com.calculation.pension.erppension.domain.service;

import com.calculation.pension.erppension.domain.exception.BusinessException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PessoaJuridicaTest {

    @Test
    void constructor_valido_inicializaCorretamente() {
        var cnpj = "12.345.678/0001-90";
        double valorBensImoveis = 500_000.0;

        var pessoaJuridica = new PessoaJuridica(cnpj, valorBensImoveis);

        assertEquals(cnpj, pessoaJuridica.getCnpj(), "O CNPJ deve ser inicializado corretamente.");
        assertEquals(valorBensImoveis, pessoaJuridica.getValorBensImoveis(), "O valor de bens imóveis deve ser inicializado corretamente.");
    }

    @Test
    void constructor_comCnpjNulo_disparaExcecao() {
        var exception = assertThrows(BusinessException.class,
                () -> new PessoaJuridica(null, 500_000.0),
                "CNPJ nulo deve lançar exceção.");
        assertEquals("CNPJ não pode ser vazio", exception.getMessage(), "Mensagem da exceção deve estar correta.");
    }

    @Test
    void constructor_comCnpjVazio_disparaExcecao() {
        var exception = assertThrows(BusinessException.class,
                () -> new PessoaJuridica("", 500_000.0),
                "CNPJ vazio deve lançar exceção.");
        assertEquals("CNPJ não pode ser vazio", exception.getMessage(), "Mensagem da exceção deve estar correta.");
    }

    @Test
    void constructor_comValorBensImoveisNegativo_disparaExcecao() {
        var exception = assertThrows(BusinessException.class,
                () -> new PessoaJuridica("12.345.678/0001-90", -1),
                "Valor de bens imóveis negativo deve lançar exceção.");
        assertEquals("Valor de bens imóveis deve ser maior que zero", exception.getMessage(), "Mensagem da exceção deve estar correta.");
    }

    @Test
    void constructor_comValorBensImoveisZero_disparaExcecao() {
        var exception = assertThrows(BusinessException.class,
                () -> new PessoaJuridica("12.345.678/0001-90", 0),
                "Valor de bens imóveis igual a zero deve lançar exceção.");
        assertEquals("Valor de bens imóveis deve ser maior que zero", exception.getMessage(), "Mensagem da exceção deve estar correta.");
    }

    @Test
    void equals_mesmoCnpj_retornaTrue() {
        var pessoa1 = new PessoaJuridica("12.345.678/0001-90", 500_000.0);
        PessoaJuridica pessoa2 = new PessoaJuridica("12.345.678/0001-90", 1_000_000.0);

        assertEquals(pessoa1, pessoa2, "Pessoas jurídicas com o mesmo CNPJ devem ser consideradas iguais.");
    }

    @Test
    void equals_diferenteCnpj_retornaFalse() {
        var pessoa1 = new PessoaJuridica("12.345.678/0001-90", 500_000.0);
        PessoaJuridica pessoa2 = new PessoaJuridica("98.765.432/0001-10", 500_000.0);

        assertNotEquals(pessoa1, pessoa2, "Pessoas jurídicas com CNPJs diferentes devem ser consideradas diferentes.");
    }

    @Test
    void hashCode_mesmoCnpj_mesmoHashCode() {
        var pessoa1 = new PessoaJuridica("12.345.678/0001-90", 500_000.0);
        PessoaJuridica pessoa2 = new PessoaJuridica("12.345.678/0001-90", 1_000_000.0);

        assertEquals(pessoa1.hashCode(), pessoa2.hashCode(), "Pessoas jurídicas com o mesmo CNPJ devem ter o mesmo hashCode.");
    }

    @Test
    void hashCode_diferenteCnpj_diferenteHashCode() {
        var pessoa1 = new PessoaJuridica("12.345.678/0001-90", 500_000.0);
        PessoaJuridica pessoa2 = new PessoaJuridica("98.765.432/0001-10", 500_000.0);

        assertNotEquals(pessoa1.hashCode(), pessoa2.hashCode(), "Pessoas jurídicas com CNPJs diferentes devem ter hashCodes diferentes.");
    }

}