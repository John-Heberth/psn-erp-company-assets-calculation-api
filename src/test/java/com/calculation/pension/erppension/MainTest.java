package com.calculation.pension.erppension;

import com.calculation.pension.erppension.core.GeradorDados;
import com.calculation.pension.erppension.core.Validador;
import com.calculation.pension.erppension.domain.service.CalculadoraPatrimonio;
import com.calculation.pension.erppension.domain.service.Empresa;
import com.calculation.pension.erppension.domain.service.PessoaFisica;
import com.calculation.pension.erppension.domain.service.PessoaJuridica;
import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MainTest {

    @Test
    public void testMain() {
        var geradorMock = mock(GeradorDados.class);

        var calculadoraMock = mock(CalculadoraPatrimonio.class);

        when(geradorMock.gerarCPF()).thenReturn("12345678900", "23456789001", "34567890102", "45678901203");
        when(geradorMock.gerarCNPJ()).thenReturn("12345678000195", "98765432000189");
        when(geradorMock.gerarValorBens()).thenReturn(1000000.00, 2000000.00, 3000000.00, 4000000.00);
        when(geradorMock.gerarNomeEmpresa()).thenReturn("Empresa A", "Empresa B", "Empresa C", "Empresa D");

        var empresaAMock = mock(Empresa.class);
        var empresaBMock = mock(Empresa.class);
        var empresaCMock = mock(Empresa.class);
        var empresaDMock = mock(Empresa.class);

        when(empresaAMock.getNome()).thenReturn("Empresa A");
        when(empresaBMock.getNome()).thenReturn("Empresa B");
        when(empresaCMock.getNome()).thenReturn("Empresa C");
        when(empresaDMock.getNome()).thenReturn("Empresa D");

        when(calculadoraMock.calcularPatrimonio(empresaAMock)).thenReturn(1000000.00);
        when(calculadoraMock.calcularPatrimonio(empresaBMock)).thenReturn(2000000.00);
        when(calculadoraMock.calcularPatrimonio(empresaCMock)).thenReturn(3000000.00);
        when(calculadoraMock.calcularPatrimonio(empresaDMock)).thenReturn(4000000.00);

        var pf1Mock = mock(PessoaFisica.class);
        var pf2Mock = mock(PessoaFisica.class);
        var pf3Mock = mock(PessoaFisica.class);
        var pf4Mock = mock(PessoaFisica.class);

        when(pf1Mock.getValorBensImoveis()).thenReturn(1000000.00);
        when(pf2Mock.getValorBensImoveis()).thenReturn(2000000.00);
        when(pf3Mock.getValorBensImoveis()).thenReturn(3000000.00);
        when(pf4Mock.getValorBensImoveis()).thenReturn(4000000.00);

        var pj1Mock = mock(PessoaJuridica.class);
        var pj2Mock = mock(PessoaJuridica.class);

        when(pj1Mock.getValorBensImoveis()).thenReturn(1000000.00);
        when(pj2Mock.getValorBensImoveis()).thenReturn(2000000.00);

        String cpf1 = "12345678900";
        String cpf2 = "23456789001";
        String cpf3 = "34567890102";
        String cpf4 = "45678901203";
        String cnpj1 = "12345678000195";
        String cnpj2 = "98765432000189";

        String cpfFormatado1 = Validador.formatarCPF(cpf1);
        String cnpjFormatado1 = Validador.formatarCNPJ(cnpj1);


        NumberFormat formatter = DecimalFormat.getInstance(new Locale("pt", "BR"));
        formatter.setMaximumFractionDigits(2);
        formatter.setMinimumFractionDigits(2);

        assertEquals("123.456.789-00", cpfFormatado1, "CPF formatado incorretamente");
        assertEquals("12.345.678/0001-95", cnpjFormatado1, "CNPJ formatado incorretamente");


    }
}