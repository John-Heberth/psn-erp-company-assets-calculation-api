# Banco XPTO - Cálculo de Comprometimento Financeiro

Este projeto tem como objetivo calcular o comprometimento financeiro de uma empresa, levando em consideração os bens imóveis de sua estrutura societária.

## Requisitos de Negócio

- A estrutura societária de uma empresa pode ser composta por uma ou mais pessoas físicas ou jurídicas.
- Empresas podem ser sócias de outras empresas, criando um ciclo societário.
- A contabilização dos bens imóveis deve incluir os bens de todos os sócios e suas respectivas empresas, sem duplicação.
- O valor total dos bens imóveis é somado, e não a quantidade de bens.

## Funcionalidade

O sistema deve implementar um método para calcular o total de bens imóveis de uma empresa, levando em consideração sua estrutura societária. A estrutura de sociedade pode ser complexa, com ciclos e múltiplos níveis de associatividade entre pessoas físicas e jurídicas.

### Exemplo:

No caso de a Empresa A ter como sócios a Pessoa Física 1, Pessoa Física 2 e Empresa D, o patrimônio de cada um desses sócios deve ser somado ao valor total de bens imóveis da Empresa A. Além disso, a Empresa D também tem seus próprios sócios, e os bens de seus sócios devem ser contabilizados na Empresa A de maneira indireta.

## Requisitos Técnicos

- **Linguagem:** Java
- **Gerenciador de Dependências:** Maven
- **Validação:** Valide entradas como CPF/CNPJ e garanta a consistência dos objetos instanciados.
- **Evite Loops Infinitos:** Cuide para que o código não entre em loops infinitos em estruturas societárias cíclicas.
- **Bibliotecas Permitidas:** Apenas bibliotecas nativas do Java e bibliotecas de testes como Junit e AssertJ. Não use frameworks web nem persistência em bancos de dados.
- **Estrutura:** O sistema deve ser uma aplicação de console Java.

## Como Rodar o Projeto

1. **Clone o repositório:**
    ```bash
    git clone <https://github.com/John-Heberth/psn-erp-company-assets-calculation-api.git>
    ```

2. **Instale as dependências com Maven:**
   Se o Maven estiver instalado, use o seguinte comando na raiz do projeto:
    ```bash
    mvn clean install
    ```

3. **Compile e execute a aplicação:**
   Após a instalação das dependências, execute a aplicação:
    ```bash
    mvn exec:java
    ```

## Testes

Para rodar os testes unitários, utilize o seguinte comando Maven:

```bash
mvn test
