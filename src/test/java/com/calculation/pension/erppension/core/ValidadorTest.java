package com.calculation.pension.erppension.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidadorTest {

    @Test
    public void testValidarCPF() {
        assertTrue(Validador.validarCPF("12345678900"), "O CPF deve ser válido");

        assertFalse(Validador.validarCPF("12345"), "O CPF deve ser inválido (menos de 11 dígitos)");
        assertFalse(Validador.validarCPF("abc12345678"), "O CPF deve ser inválido (contém letras)");
        assertFalse(Validador.validarCPF("123456789012"), "O CPF deve ser inválido (mais de 11 dígitos)");
        assertFalse(Validador.validarCPF(null), "O CPF não pode ser nulo");
    }

    @Test
    public void testValidarCNPJ() {
        assertTrue(Validador.validarCNPJ("12345678000195"), "O CNPJ deve ser válido");

        assertFalse(Validador.validarCNPJ("12345678"), "O CNPJ deve ser inválido (menos de 14 dígitos)");
        assertFalse(Validador.validarCNPJ("1234567890123456"), "O CNPJ deve ser inválido (mais de 14 dígitos)");
        assertFalse(Validador.validarCNPJ("abc12345678901"), "O CNPJ deve ser inválido (contém letras)");
        assertFalse(Validador.validarCNPJ(null), "O CNPJ não pode ser nulo");
    }

    @Test
    public void testFormatarCPF() {
        var cpf = "12345678900";
        var cpfFormatado = Validador.formatarCPF(cpf);

        assertEquals("123.456.789-00", cpfFormatado, "O CPF formatado está incorreto");
    }

    @Test
    public void testFormatarCNPJ() {
        var cnpj = "12345678000195";
        var cnpjFormatado = Validador.formatarCNPJ(cnpj);

        assertEquals("12.345.678/0001-95", cnpjFormatado, "O CNPJ formatado está incorreto");
    }
}