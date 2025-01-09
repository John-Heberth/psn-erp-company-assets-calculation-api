package com.calculation.pension.erppension.domain.service;

import com.calculation.pension.erppension.core.Validador;
import com.calculation.pension.erppension.domain.interfaces.Socio;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

/**
 * Classe principal para Empresa
 * Esta classe representa uma empresa com CNPJ, valor de bens imóveis e uma lista de sócios.
 * Certifique-se de manter as validações consistentes para evitar dados inválidos.
 *
 */
public class Empresa {
    private final String cnpj;
    private final double valorBensImoveis;
    private final List<Socio> socios;

    public Empresa(String cnpj, double valorBensImoveis) {
        if (!Validador.validarCNPJ(cnpj)) {
            throw new IllegalArgumentException("CNPJ inválido");
        }
        if (valorBensImoveis < 0) {
            throw new IllegalArgumentException("Valor dos bens imóveis não pode ser negativo");
        }
        this.cnpj = cnpj;
        this.valorBensImoveis = valorBensImoveis;
        this.socios = new ArrayList<>();
    }

    public void adicionarSocio(Socio socio) {
        socios.add(socio);
    }

    public double calcularTotalBens(Set<String> visitados) {
        if (visitados.contains(this.cnpj)) {
            return 0; // Evita ciclos
        }
        visitados.add(this.cnpj);

        double total = this.valorBensImoveis;
        for (Socio socio : socios) {
            total += socio.calcularTotalBens(visitados);
        }
        return total;
    }
}


