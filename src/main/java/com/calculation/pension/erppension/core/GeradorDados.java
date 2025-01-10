package com.calculation.pension.erppension.core;

import java.util.Random;

/**
 * Classe utilitária para gerar CPF,CNPJ & Valores de Bens
 *
 */
public class GeradorDados {

    private static final String[] NOMES_EMPRESARIAIS = {
            "Alpha", "Beta", "Gamma", "Delta", "Omega", "Sigma", "Epsilon", "Zeta",
            "Lunar", "Solar", "Pioneer", "Nova", "Future", "Prime", "Summit"
    };
    private static final String[] TIPOS_EMPRESARIAIS = {
            "Solutions", "Corp", "Inc.", "Partners", "Group", "Enterprises", "Technologies", "Systems"
    };
    private static final Random RANDOM = new Random();

    /**
     * Gerador de CPF válido no formato "12345678900"
     *
     */
    public String gerarCPF() {
        var cpf = new StringBuilder();
        for (int i = 0; i < 11; i++) {
            cpf.append(RANDOM.nextInt(10));
        }
        return cpf.toString();
    }

    /**
     * Gerador de CNPJ válido no formato "12345678000195"
     *
     */
    public String gerarCNPJ() {
        var cnpj = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            cnpj.append(RANDOM.nextInt(10));
        }
        cnpj.append("0001");
        cnpj.append(RANDOM.nextInt(90) + 10);
        return cnpj.toString();
    }


    /**
     * Gerador de valores de bens entre 100 mil e 10 milhões
     *
     */
    public double gerarValorBens() {
        return 100_000 + (RANDOM.nextDouble() * 9_900_000);
    }


    /**
     * Gerador de Nomes para Empresas
     *
     */
    public String gerarNomeEmpresa() {
        var nome = NOMES_EMPRESARIAIS[RANDOM.nextInt(NOMES_EMPRESARIAIS.length)];
        var tipo = TIPOS_EMPRESARIAIS[RANDOM.nextInt(TIPOS_EMPRESARIAIS.length)];
        return nome + " " + tipo;
    }

}


