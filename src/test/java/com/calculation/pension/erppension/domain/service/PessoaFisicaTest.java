package com.calculation.pension.erppension.domain.service;

import com.calculation.pension.erppension.domain.exception.BusinessException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PessoaFisicaTest {

    @Test
    void constructor_valido_inicializaCorretamente() {
        var cpf = "12345678900"; // CPF no formato aceito pelo Validador
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
    void constructor_comCpfInvalido_disparaExcecao() {
        var cpfInvalido = "12345678"; // CPF com formato inválido
        var exception = assertThrows(BusinessException.class,
                () -> new PessoaFisica(cpfInvalido, 100_000.0),
                "CPF inválido deve lançar exceção.");
        assertEquals("CPF inválido", exception.getMessage(), "Mensagem da exceção deve estar correta.");
    }

    @Test
    void constructor_comValorBensImoveisNegativo_disparaExcecao() {
        var exception = assertThrows(BusinessException.class,
                () -> new PessoaFisica("12345678900", -1),
                "Valor de bens imóveis negativo deve lançar exceção.");
        assertEquals("Valor de bens imóveis deve ser maior que zero", exception.getMessage(), "Mensagem da exceção deve estar correta.");
    }

    @Test
    void constructor_comValorBensImoveisZero_disparaExcecao() {
        var exception = assertThrows(BusinessException.class,
                () -> new PessoaFisica("12345678900", 0),
                "Valor de bens imóveis igual a zero deve lançar exceção.");
        assertEquals("Valor de bens imóveis deve ser maior que zero", exception.getMessage(), "Mensagem da exceção deve estar correta.");
    }

    @Test
    void equals_mesmoCpf_retornaTrue() {
        var pessoa1 = new PessoaFisica("12345678900", 100_000.0);
        var pessoa2 = new PessoaFisica("12345678900", 200_000.0);

        assertEquals(pessoa1, pessoa2, "Pessoas físicas com o mesmo CPF devem ser consideradas iguais.");
    }

    @Test
    void equals_diferenteCpf_retornaFalse() {
        var pessoa1 = new PessoaFisica("12345678900", 100_000.0);
        var pessoa2 = new PessoaFisica("98765432100", 100_000.0);

        assertNotEquals(pessoa1, pessoa2, "Pessoas físicas com CPFs diferentes devem ser consideradas diferentes.");
    }

    @Test
    void hashCode_mesmoCpf_mesmoHashCode() {
        var pessoa1 = new PessoaFisica("12345678900", 100_000.0);
        var pessoa2 = new PessoaFisica("12345678900", 200_000.0);

        assertEquals(pessoa1.hashCode(), pessoa2.hashCode(), "Pessoas físicas com o mesmo CPF devem ter o mesmo hashCode.");
    }

    @Test
    void hashCode_diferenteCpf_diferenteHashCode() {
        var pessoa1 = new PessoaFisica("12345678900", 100_000.0);
        var pessoa2 = new PessoaFisica("98765432100", 100_000.0);

        assertNotEquals(pessoa1.hashCode(), pessoa2.hashCode(), "Pessoas físicas com CPFs diferentes devem ter hashCodes diferentes.");
    }
}
