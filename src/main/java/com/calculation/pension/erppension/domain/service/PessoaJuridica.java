package com.calculation.pension.erppension.domain.service;

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
 * Classe para Pessoa Jurídica
 * Representa uma pessoa jurídica que é sócia de outra empresa.
 *
 */
public class PessoaJuridica extends Socio {
    private final Empresa empresa;

    public PessoaJuridica(Empresa empresa) {
        if (empresa == null) {
            throw new IllegalArgumentException("Empresa não pode ser nula");
        }
        this.empresa = empresa;
    }

    @Override
    public double calcularTotalBens(Set<String> visitados) {
        return empresa.calcularTotalBens(visitados);
    }
}
