package com.calculation.pension.erppension.domain.service;

import com.calculation.pension.erppension.domain.exception.BusinessException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Objects;


/**
 * Classe para Pessoa Física
 * Representa uma pessoa física com CPF e valor de bens imóveis.
 *
 */
@Getter
@RequiredArgsConstructor
public class PessoaFisica {

    private String cpf;
    private double valorBensImoveis;

    public PessoaFisica(String cpf, double valorBensImoveis) {
        if (cpf == null || cpf.isEmpty()) {
            throw new BusinessException("CPF não pode ser vazio");
        }
        if (valorBensImoveis <= 0) {
            throw new BusinessException("Valor de bens imóveis deve ser maior que zero");
        }
        this.cpf = cpf;
        this.valorBensImoveis = valorBensImoveis;
    }

    public String getCpf() {
        return cpf;
    }

    public double getValorBensImoveis() {
        return valorBensImoveis;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        PessoaFisica that = (PessoaFisica) obj;
        return Objects.equals(cpf, that.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }

}
