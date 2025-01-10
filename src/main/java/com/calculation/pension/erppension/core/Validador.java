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

    /**
     * Método para formatar o CPF
     *
     */
    public static String formatarCPF(String cpf) {
        return String.format("%s.%s.%s-%s", cpf.substring(0, 3), cpf.substring(3, 6), cpf.substring(6, 9), cpf.substring(9, 11));
    }

    /**
     * Método para formatar o CNPJ
     *
     */
    public static String formatarCNPJ(String cnpj) {
        return String.format("%s.%s.%s/%s-%s", cnpj.substring(0, 2), cnpj.substring(2, 5), cnpj.substring(5, 8), cnpj.substring(8, 12), cnpj.substring(12, 14));
    }

}
