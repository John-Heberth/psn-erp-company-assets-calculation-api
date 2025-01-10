package com.calculation.pension.erppension.domain.service;


import com.calculation.pension.erppension.core.Validador;
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
public class PessoaJuridica {

    private String cnpj;
    private double valorBensImoveis;

    public PessoaJuridica(String cnpj, double valorBensImoveis) {
        if (cnpj == null || cnpj.isEmpty()) {
            throw new BusinessException("CNPJ não pode ser vazio");
        }
        if (!Validador.validarCNPJ(cnpj)) {
            throw new BusinessException("CNPJ inválido");
        }
        if (valorBensImoveis <= 0) {
            throw new BusinessException("Valor de bens imóveis deve ser maior que zero");
        }
        this.cnpj = cnpj;
        this.valorBensImoveis = valorBensImoveis;
    }

    public String getCnpj() {
        return cnpj;
    }

    public double getValorBensImoveis() {
        return valorBensImoveis;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        PessoaJuridica that = (PessoaJuridica) obj;
        return Objects.equals(cnpj, that.cnpj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cnpj);
    }
}
