# 🏦 Resilient Ledger Core

Um motor simples e seguro de transações bancárias desenvolvido em **Java 21+**, criado com o objetivo de evoluir o clássico exercício acadêmico de Orientação a Objetos (`class Conta`) para práticas utilizadas em sistemas financeiros reais.

## 🎯 Decisões de Arquitetura

Ao sair do código introdutório para o ambiente de produção, alguns problemas silenciosos precisam ser resolvidos:

* **Precisão Monetária (`BigDecimal` vs `double`):** Tipos de ponto flutuante (`double`/`float`) sofrem com problemas de arredondamento binário. O uso de `BigDecimal` garante exatidão em cálculos financeiros.
* **Imutabilidade:** Em vez de alterar diretamente a variável `saldo` (`this.saldo -= valor`), cada transação aprovada retorna uma **nova instância** da conta, facilitando rastreabilidade e evitando efeitos colaterais em ambientes concorrentes.
* **Retornos Tipados (`Sealed Interfaces` & `Records`):** Em vez de métodos `void` que apenas imprimem mensagens no console ou lançam exceções custosas para regras de negócio esperadas (como saldo insuficiente), o sistema retorna um tipo selado (`ResultadoOperacao`), obrigando quem consome o código a tratar explicitamente os cenários de `Sucesso` e `Falha`.

## 🚀 Como executar

Certifique-se de ter o **JDK 21** (ou superior) instalado em sua máquina:

```bash
# Clone o repositório
git clone [https://github.com/SEU-USUARIO/resilient-ledger-core.git](https://github.com/SEU-USUARIO/resilient-ledger-core.git)

# Entre na pasta e compile
cd resilient-ledger-core
javac src/*.java

# Execute a demonstração
java src.Main

