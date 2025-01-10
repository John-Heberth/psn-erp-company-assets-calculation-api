package com.calculation.pension.erppension.domain.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


/**
 * Classe que representa uma calculadora de Patrimnonios
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CalculadoraPatrimonio {

    private Set<Object> sociosContabilizados = new HashSet<>();

    public double calcularPatrimonio(Empresa empresa) {
        return calcularPatrimonioRecursivo(empresa, new HashSet<>());
    }

    private double calcularPatrimonioRecursivo(Empresa empresa, Set<Empresa> empresasVisitadas) {
        if (empresasVisitadas.contains(empresa)) {
            return 0;
        }
        empresasVisitadas.add(empresa);

        double totalPatrimonio = 0;

        for (Object socio : empresa.getSocios()) {
            if (socio instanceof PessoaFisica) {
                if (sociosContabilizados.add(socio)) { // Verifica se a pessoa já foi contabilizada
                    totalPatrimonio += ((PessoaFisica) socio).getValorBensImoveis();
                }
            } else if (socio instanceof PessoaJuridica) {
                if (sociosContabilizados.add(socio)) { // Verifica se a pessoa jurídica já foi contabilizada
                    totalPatrimonio += ((PessoaJuridica) socio).getValorBensImoveis();
                }
            } else if (socio instanceof Empresa) {
                totalPatrimonio += calcularPatrimonioRecursivo((Empresa) socio, empresasVisitadas);
            }
        }

        return totalPatrimonio;
    }
}


