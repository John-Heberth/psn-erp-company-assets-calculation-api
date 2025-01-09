package com.calculation.pension.erppension.domain.service;


import com.calculation.pension.erppension.domain.exception.BusinessException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Classe que representa um bem imóvel
 *
 */
@Getter
@RequiredArgsConstructor
public class BemImovel {

    private final double valor;

    public BemImovel(double valor) {
        if (valor <= 0) {
            throw new BusinessException("O valor do bem imóvel deve ser maior que zero.");
        }
        this.valor = valor;
    }
}


