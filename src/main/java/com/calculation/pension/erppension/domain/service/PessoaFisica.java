package com.calculation.pension.erppension.domain.service;

import com.calculation.pension.erppension.core.Validador;
import com.calculation.pension.erppension.domain.interfaces.Socio;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
/**
 * Classe para Pessoa Física
 * Representa uma pessoa física com CPF e valor de bens imóveis.
 *
 */
public class PessoaFisica extends Socio {
    private final String cpf;
    private final double valorBensImoveis;

    public PessoaFisica(String cpf, double valorBensImoveis) {
        if (!Validador.validarCPF(cpf)) {
            throw new IllegalArgumentException("CPF inválido");
        }
        if (valorBensImoveis < 0) {
            throw new IllegalArgumentException("Valor dos bens imóveis não pode ser negativo");
        }
        this.cpf = cpf;
        this.valorBensImoveis = valorBensImoveis;
    }

    @Override
    public double calcularTotalBens(Set<String> visitados) {
        if (visitados.contains(this.cpf)) {
            return 0; // Evita ciclos
        }
        visitados.add(this.cpf);
        return this.valorBensImoveis;
    }
}
