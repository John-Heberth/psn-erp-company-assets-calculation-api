package com.calculation.pension.erppension;

import com.calculation.pension.erppension.core.GeradorDados;
import com.calculation.pension.erppension.core.Validador;
import com.calculation.pension.erppension.domain.service.CalculadoraPatrimonio;
import com.calculation.pension.erppension.domain.service.Empresa;
import com.calculation.pension.erppension.domain.service.PessoaFisica;
import com.calculation.pension.erppension.domain.service.PessoaJuridica;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.DecimalFormat;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		var gerador = new GeradorDados();
		var formatter = new DecimalFormat("#,###,##0.00");

		var cpf1 = gerador.gerarCPF();
		var cpf2 = gerador.gerarCPF();
		var cpf3 = gerador.gerarCPF();
		var cpf4 = gerador.gerarCPF();

		var pf1 = new PessoaFisica(cpf1, gerador.gerarValorBens());
		var pf2 = new PessoaFisica(cpf2, gerador.gerarValorBens());
		var pf3 = new PessoaFisica(cpf3, gerador.gerarValorBens());
		var pf4 = new PessoaFisica(cpf4, gerador.gerarValorBens());

		var cnpj1 = gerador.gerarCNPJ();
		var cnpj2 = gerador.gerarCNPJ();

		var pj1 = new PessoaJuridica(cnpj1, gerador.gerarValorBens());
		var pj2 = new PessoaJuridica(cnpj2, gerador.gerarValorBens());

		var empresaA = new Empresa(gerador.gerarNomeEmpresa());
		var empresaB = new Empresa(gerador.gerarNomeEmpresa());
		var empresaC = new Empresa(gerador.gerarNomeEmpresa());
		var empresaD = new Empresa(gerador.gerarNomeEmpresa());

		empresaA.adicionarSocio(pf1);
		empresaA.adicionarSocio(pf2);
		empresaA.adicionarSocio(pj1);

		empresaB.adicionarSocio(pf3);
		empresaB.adicionarSocio(empresaA);

		empresaC.adicionarSocio(pf4);
		empresaC.adicionarSocio(pj2);

		empresaD.adicionarSocio(empresaC);
		empresaD.adicionarSocio(empresaA);

		// Calculando e exibindo o patrimônio total de cada empresa
		var calculadora = new CalculadoraPatrimonio();

		System.out.println("Empresas:");
		System.out.println("Patrimônio total da " + empresaA.getNome() + ": R$ " + formatter.format(calculadora.calcularPatrimonio(empresaA)));
		System.out.println("Patrimônio total da " + empresaB.getNome() + ": R$ " + formatter.format(calculadora.calcularPatrimonio(empresaB)));
		System.out.println("Patrimônio total da " + empresaC.getNome() + ": R$ " + formatter.format(calculadora.calcularPatrimonio(empresaC)));
		System.out.println("Patrimônio total da " + empresaD.getNome() + ": R$ " + formatter.format(calculadora.calcularPatrimonio(empresaD)));

		System.out.println("\nPessoas Físicas:");

		// Exibindo patrimônio das pessoas físicas
		System.out.println("Patrimônio de Pessoa Física 1 (CPF: " + Validador.formatarCPF(cpf1) + "): R$ " + formatter.format(pf1.getValorBensImoveis()));
		System.out.println("Patrimônio de Pessoa Física 2 (CPF: " + Validador.formatarCPF(cpf2) + "): R$ " + formatter.format(pf2.getValorBensImoveis()));
		System.out.println("Patrimônio de Pessoa Física 3 (CPF: " + Validador.formatarCPF(cpf3) + "): R$ " + formatter.format(pf3.getValorBensImoveis()));
		System.out.println("Patrimônio de Pessoa Física 4 (CPF: " + Validador.formatarCPF(cpf4) + "): R$ " + formatter.format(pf4.getValorBensImoveis()));

		System.out.println("\nPessoas Jurídicas:");

		// Exibindo patrimônio das pessoas jurídicas
		System.out.println("Patrimônio de Pessoa Jurídica 1 (CNPJ: " +  Validador.formatarCNPJ(cnpj1) + "): R$ " + formatter.format(pj1.getValorBensImoveis()));
		System.out.println("Patrimônio de Pessoa Jurídica 2 (CNPJ: " +  Validador.formatarCNPJ(cnpj2) + "): R$ " + formatter.format(pj2.getValorBensImoveis()));
	}

}

