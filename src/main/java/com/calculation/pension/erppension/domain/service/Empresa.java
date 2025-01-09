package com.calculation.pension.erppension.domain.service;

import com.calculation.pension.erppension.core.Validador;
import com.calculation.pension.erppension.domain.exception.BusinessException;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.RequiredArgsConstructor;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Classe principal para Empresa.
 * Esta classe representa uma empresa com CNPJ, valor de bens imóveis e uma lista de sócios.
 * Certifique-se de manter as validações consistentes para evitar dados inválidos.
 *
 */
@Data
@RequiredArgsConstructor
public class Empresa {

    private String nome;
    private Set<Object> socios; // Pode ser tanto PessoaFisica quanto PessoaJuridica

    public Empresa(String nome) {
        this.nome = nome;
        this.socios = new HashSet<>();
    }

    public String getNome() {
        return nome;
    }

    public void adicionarSocio(Object socio) {
        if (socio == null) {
            throw new IllegalArgumentException("Sócio não pode ser nulo");
        }
        socios.add(socio);
    }

    public Set<Object> getSocios() {
        return socios;
    }
}




