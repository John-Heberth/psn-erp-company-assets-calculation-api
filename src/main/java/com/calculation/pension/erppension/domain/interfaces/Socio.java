package com.calculation.pension.erppension.domain.interfaces;


import java.util.Set;

/**
 * Classe base para Socio
 * Classe abstrata que define a base para sócios (pessoas físicas e jurídicas).
 * Ajuda a garantir a extensibilidade e a padronização do cálculo de bens.
 *
 */
public abstract class Socio {
    public abstract double calcularTotalBens(Set<String> visitados);
}
