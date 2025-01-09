package com.calculation.pension.erppension.core;

/**
 * Classe utilitária para validação de CPF e CNPJ
 * Centraliza as validações para evitar duplicação de código
 *
 */
public class Validador {

    /**
     * Método responsável por validar o formato do CPF.
     * Pode ser expandido para incluir validações mais rigorosas, como cálculo de dígitos verificadores.
     *
     */
    public static boolean validarCPF(String cpf) {
        return cpf != null && cpf.matches("\\d{11}");
    }

    /**
     * Método responsável por validar o formato do CNPJ.
     * Pode ser expandido para incluir validações mais rigorosas, como cálculo de dígitos verificadores.
     *
     */
    public static boolean validarCNPJ(String cnpj) {
        return cnpj != null && cnpj.matches("\\d{14}");
    }

}
