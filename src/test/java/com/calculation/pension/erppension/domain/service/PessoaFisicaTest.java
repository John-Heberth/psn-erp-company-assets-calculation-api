package com.calculation.pension.erppension.domain.service;

import com.calculation.pension.erppension.domain.exception.BusinessException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PessoaFisicaTest {

    @Test
    void constructor_valido_inicializaCorretamente() {
        var cpf = "123.456.789-00";
        double valorBensImoveis = 100_000.0;

        var pessoaFisica = new PessoaFisica(cpf, valorBensImoveis);

        assertEquals(cpf, pessoaFisica.getCpf(), "O CPF deve ser inicializado corretamente.");
        assertEquals(valorBensImoveis, pessoaFisica.getValorBensImoveis(), "O valor de bens imóveis deve ser inicializado corretamente.");
    }

    @Test
    void constructor_comCpfNulo_disparaExcecao() {
        var exception = assertThrows(BusinessException.class,
                () -> new PessoaFisica(null, 100_000.0),
                "CPF nulo deve lançar exceção.");
        assertEquals("CPF não pode ser vazio", exception.getMessage(), "Mensagem da exceção deve estar correta.");
    }

    @Test
    void constructor_comCpfVazio_disparaExcecao() {
        var exception = assertThrows(BusinessException.class,
                () -> new PessoaFisica("", 100_000.0),
                "CPF vazio deve lançar exceção.");
        assertEquals("CPF não pode ser vazio", exception.getMessage(), "Mensagem da exceção deve estar correta.");
    }

    @Test
    void constructor_comValorBensImoveisNegativo_disparaExcecao() {
        var exception = assertThrows(BusinessException.class,
                () -> new PessoaFisica("123.456.789-00", -1),
                "Valor de bens imóveis negativo deve lançar exceção.");
        assertEquals("Valor de bens imóveis deve ser maior que zero", exception.getMessage(), "Mensagem da exceção deve estar correta.");
    }

    @Test
    void constructor_comValorBensImoveisZero_disparaExcecao() {
        var exception = assertThrows(BusinessException.class,
                () -> new PessoaFisica("123.456.789-00", 0),
                "Valor de bens imóveis igual a zero deve lançar exceção.");
        assertEquals("Valor de bens imóveis deve ser maior que zero", exception.getMessage(), "Mensagem da exceção deve estar correta.");
    }

    @Test
    void equals_mesmoCpf_retornaTrue() {
        var pessoa1 = new PessoaFisica("123.456.789-00", 100_000.0);
        var pessoa2 = new PessoaFisica("123.456.789-00", 200_000.0);

        assertEquals(pessoa1, pessoa2, "Pessoas físicas com o mesmo CPF devem ser consideradas iguais.");
    }

    @Test
    void equals_diferenteCpf_retornaFalse() {
        var pessoa1 = new PessoaFisica("123.456.789-00", 100_000.0);
        var pessoa2 = new PessoaFisica("987.654.321-00", 100_000.0);

        assertNotEquals(pessoa1, pessoa2, "Pessoas físicas com CPFs diferentes devem ser consideradas diferentes.");
    }

    @Test
    void hashCode_mesmoCpf_mesmoHashCode() {
        var pessoa1 = new PessoaFisica("123.456.789-00", 100_000.0);
        var pessoa2 = new PessoaFisica("123.456.789-00", 200_000.0);

        assertEquals(pessoa1.hashCode(), pessoa2.hashCode(), "Pessoas físicas com o mesmo CPF devem ter o mesmo hashCode.");
    }

    @Test
    void hashCode_diferenteCpf_diferenteHashCode() {
        var pessoa1 = new PessoaFisica("123.456.789-00", 100_000.0);
        var pessoa2 = new PessoaFisica("987.654.321-00", 100_000.0);

        assertNotEquals(pessoa1.hashCode(), pessoa2.hashCode(), "Pessoas físicas com CPFs diferentes devem ter hashCodes diferentes.");
    }
}
