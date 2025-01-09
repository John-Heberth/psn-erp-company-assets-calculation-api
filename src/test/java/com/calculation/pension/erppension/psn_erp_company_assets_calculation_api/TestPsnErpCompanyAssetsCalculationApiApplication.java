package com.calculation.pension.erppension.psn_erp_company_assets_calculation_api;

import org.springframework.boot.SpringApplication;

public class TestPsnErpCompanyAssetsCalculationApiApplication {

	public static void main(String[] args) {
		SpringApplication.from(Application::main).with(TestcontainersConfiguration.class).run(args);
	}

}
