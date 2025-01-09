package com.calculation.pension.erppension;

import com.calculation.pension.erppension.domain.service.CalculadoraPatrimonio;
import com.calculation.pension.erppension.domain.service.Empresa;
import com.calculation.pension.erppension.domain.service.PessoaFisica;
import com.calculation.pension.erppension.domain.service.PessoaJuridica;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		// Criação de pessoas físicas
		PessoaFisica pf1 = new PessoaFisica("123.456.789-00", 1000000);
		PessoaFisica pf2 = new PessoaFisica("987.654.321-00", 2000000);
		PessoaFisica pf3 = new PessoaFisica("111.222.333-44", 500000);
		PessoaFisica pf4 = new PessoaFisica("555.666.777-88", 1500000);

		// Criação de pessoas jurídicas
		PessoaJuridica pj1 = new PessoaJuridica("12.345.678/0001-00", 5000000);
		PessoaJuridica pj2 = new PessoaJuridica("98.765.432/0001-99", 3000000);

		// Criação de empresas
		Empresa empresaA = new Empresa("Empresa A");
		Empresa empresaB = new Empresa("Empresa B");
		Empresa empresaC = new Empresa("Empresa C");
		Empresa empresaD = new Empresa("Empresa D");

		// Definindo a estrutura societária
		empresaA.adicionarSocio(pf1);
		empresaA.adicionarSocio(pf2);
		empresaA.adicionarSocio(pj1);

		empresaB.adicionarSocio(pf3);
		empresaB.adicionarSocio(empresaA);

		empresaC.adicionarSocio(pf4);
		empresaC.adicionarSocio(pj2);

		empresaD.adicionarSocio(empresaC);
		empresaD.adicionarSocio(empresaB);

		// Calculando o patrimônio total de uma empresa
		CalculadoraPatrimonio calculadora = new CalculadoraPatrimonio();
		double patrimonioEmpresaA = calculadora.calcularPatrimonio(empresaA);
		System.out.println("Patrimônio total da Empresa A: " + patrimonioEmpresaA);
	}
}

