package com.calculation.pension.erppension.domain.service;

import com.calculation.pension.erppension.domain.exception.BusinessException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PessoaJuridicaTest {

    @Test
    void constructor_valido_inicializaCorretamente() {
        var cnpj = "12345678000195"; // CNPJ válido no formato aceito
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
    void constructor_comCnpjInvalido_disparaExcecao() {
        var cnpjInvalido = "12345678"; // CNPJ com formato inválido
        var exception = assertThrows(BusinessException.class,
                () -> new PessoaJuridica(cnpjInvalido, 500_000.0),
                "CNPJ inválido deve lançar exceção.");
        assertEquals("CNPJ inválido", exception.getMessage(), "Mensagem da exceção deve estar correta.");
    }

    @Test
    void constructor_comValorBensImoveisNegativo_disparaExcecao() {
        var exception = assertThrows(BusinessException.class,
                () -> new PessoaJuridica("12345678000195", -1),
                "Valor de bens imóveis negativo deve lançar exceção.");
        assertEquals("Valor de bens imóveis deve ser maior que zero", exception.getMessage(), "Mensagem da exceção deve estar correta.");
    }

    @Test
    void constructor_comValorBensImoveisZero_disparaExcecao() {
        var exception = assertThrows(BusinessException.class,
                () -> new PessoaJuridica("12345678000195", 0),
                "Valor de bens imóveis igual a zero deve lançar exceção.");
        assertEquals("Valor de bens imóveis deve ser maior que zero", exception.getMessage(), "Mensagem da exceção deve estar correta.");
    }

    @Test
    void equals_mesmoCnpj_retornaTrue() {
        var pessoa1 = new PessoaJuridica("12345678000195", 500_000.0);
        var pessoa2 = new PessoaJuridica("12345678000195", 1_000_000.0);

        assertEquals(pessoa1, pessoa2, "Pessoas jurídicas com o mesmo CNPJ devem ser consideradas iguais.");
    }

    @Test
    void equals_diferenteCnpj_retornaFalse() {
        var pessoa1 = new PessoaJuridica("12345678000195", 500_000.0);
        var pessoa2 = new PessoaJuridica("98765432000199", 500_000.0);

        assertNotEquals(pessoa1, pessoa2, "Pessoas jurídicas com CNPJs diferentes devem ser consideradas diferentes.");
    }

    @Test
    void hashCode_mesmoCnpj_mesmoHashCode() {
        var pessoa1 = new PessoaJuridica("12345678000195", 500_000.0);
        var pessoa2 = new PessoaJuridica("12345678000195", 1_000_000.0);

        assertEquals(pessoa1.hashCode(), pessoa2.hashCode(), "Pessoas jurídicas com o mesmo CNPJ devem ter o mesmo hashCode.");
    }

    @Test
    void hashCode_diferenteCnpj_diferenteHashCode() {
        var pessoa1 = new PessoaJuridica("12345678000195", 500_000.0);
        var pessoa2 = new PessoaJuridica("98765432000199", 500_000.0);

        assertNotEquals(pessoa1.hashCode(), pessoa2.hashCode(), "Pessoas jurídicas com CNPJs diferentes devem ter hashCodes diferentes.");
    }
}