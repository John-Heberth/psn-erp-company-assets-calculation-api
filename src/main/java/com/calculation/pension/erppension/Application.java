package com.calculation.pension.erppension;

import com.calculation.pension.erppension.domain.service.Empresa;
import com.calculation.pension.erppension.domain.service.PessoaFisica;
import com.calculation.pension.erppension.domain.service.PessoaJuridica;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class Application {


	public static void main(String[] args) {
		// Cenário 1: Empresa A com pessoas físicas e uma empresa como sócios
		Empresa empresaA = new Empresa("12345678000195", 1000000);
		Empresa empresaB = new Empresa("98765432000199", 500000);

		// Criando pessoas físicas
		PessoaFisica pessoa1 = new PessoaFisica("12345678901", 200000);
		PessoaFisica pessoa2 = new PessoaFisica("98765432100", 300000);
		PessoaFisica pessoa3 = new PessoaFisica("11122233344", 150000);

		// Adicionando sócios às empresas
		empresaA.adicionarSocio(pessoa1);
		empresaA.adicionarSocio(pessoa2);
		empresaA.adicionarSocio(new PessoaJuridica(empresaB));

		empresaB.adicionarSocio(pessoa3);
		empresaB.adicionarSocio(new PessoaJuridica(empresaA)); // Teste de ciclo

		// Calculando total de bens da Empresa A
		Set<String> visitados = new HashSet<>();
		double totalBensA = empresaA.calcularTotalBens(visitados);

		// Exibindo o resultado
		System.out.println("Cenário 1: Total de bens da Empresa A: R$ " + totalBensA);

		// Cenário 2: Empresa B com um único sócio
		Empresa empresaC = new Empresa("12345678000110", 500000);
		PessoaFisica pessoa4 = new PessoaFisica("44455566677", 100000);

		empresaC.adicionarSocio(pessoa4);

		Set<String> visitadosC = new HashSet<>();
		double totalBensC = empresaC.calcularTotalBens(visitadosC);

		System.out.println("Cenário 2: Total de bens da Empresa C: R$ " + totalBensC);

		// Cenário 3: Empresa A com múltiplos sócios (Pessoa Física + Pessoa Jurídica)
		Set<String> visitadosA = new HashSet<>();
		double totalBensA2 = empresaA.calcularTotalBens(visitadosA);

		System.out.println("Cenário 3: Total de bens da Empresa A com múltiplos sócios: R$ " + totalBensA2);
	}
}

