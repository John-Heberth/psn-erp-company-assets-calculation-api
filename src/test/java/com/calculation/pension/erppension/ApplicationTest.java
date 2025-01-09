package com.calculation.pension.erppension;

import com.calculation.pension.erppension.domain.service.Empresa;
import com.calculation.pension.erppension.domain.service.PessoaFisica;
import com.calculation.pension.erppension.domain.service.PessoaJuridica;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class ApplicationTest {

    @Test
    public void testEmpresaAComSociosPessoasFisicas() {
        // Criando a Empresa A e os sócios
        Empresa empresaA = new Empresa("12345678000195", 1000000);
        PessoaFisica pessoa1 = new PessoaFisica("12345678901", 200000);
        PessoaFisica pessoa2 = new PessoaFisica("98765432100", 300000);

        // Adicionando sócios à empresa A
        empresaA.adicionarSocio(pessoa1);
        empresaA.adicionarSocio(pessoa2);

        Set<String> visitados = new HashSet<>();
        double totalBensA = empresaA.calcularTotalBens(visitados);

        // Esperado: 1000000 + 200000 + 300000 = 1500000
        assertEquals(1500000, totalBensA);
    }

    @Test
    public void testEmpresaAComSocioEmpresa() {
        // Criando as empresas e a relação entre elas
        Empresa empresaA = new Empresa("12345678000195", 1000000);
        Empresa empresaB = new Empresa("98765432000199", 500000);

        PessoaFisica pessoa1 = new PessoaFisica("12345678901", 200000);
        PessoaFisica pessoa2 = new PessoaFisica("98765432100", 300000);

        // Adicionando sócios à empresa A
        empresaA.adicionarSocio(pessoa1);
        empresaA.adicionarSocio(pessoa2);
        empresaA.adicionarSocio(new PessoaJuridica(empresaB));

        Set<String> visitados = new HashSet<>();
        double totalBensA = empresaA.calcularTotalBens(visitados);

        // Esperado: 1000000 + 200000 + 300000 + 500000 (bens da empresaB)
        assertEquals(2000000, totalBensA);
    }

    @Test
    public void testEmpresaComUnicoSocio() {
        // Criando uma empresa com um único sócio
        Empresa empresaC = new Empresa("12345678000110", 500000);
        PessoaFisica pessoa4 = new PessoaFisica("44455566677", 100000);

        empresaC.adicionarSocio(pessoa4);

        Set<String> visitados = new HashSet<>();
        double totalBensC = empresaC.calcularTotalBens(visitados);

        // Esperado: 500000 + 100000 = 600000
        assertEquals(600000, totalBensC);
    }

    @Test
    public void testEmpresaComSociosECalculandoTotalDeBens() {
        // Criando uma empresa com múltiplos sócios e calculando o total de bens
        Empresa empresaA = new Empresa("12345678000195", 1000000);
        Empresa empresaB = new Empresa("98765432000199", 500000);

        PessoaFisica pessoa1 = new PessoaFisica("12345678901", 200000);
        PessoaFisica pessoa2 = new PessoaFisica("98765432100", 300000);

        empresaA.adicionarSocio(pessoa1);
        empresaA.adicionarSocio(pessoa2);
        empresaA.adicionarSocio(new PessoaJuridica(empresaB));

        Set<String> visitados = new HashSet<>();
        double totalBensA = empresaA.calcularTotalBens(visitados);

        // Esperado: 1000000 + 200000 + 300000 + 500000 = 2000000
        assertEquals(2000000, totalBensA);
    }
}
