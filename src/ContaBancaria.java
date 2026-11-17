package src;

import java.math.BigDecimal;
import java.time.Instant;

public class ContaBancaria {

    // Sealed Interface: garante que o resultado de uma operação só pode ser Sucesso ou Falha
    public sealed interface ResultadoOperacao permits Sucesso, Falha {}
    public record Sucesso(ContaBancaria contaAtualizada, Instant timestamp) implements ResultadoOperacao {}
    public record Falha(String motivo) implements ResultadoOperacao {}

    private final String titular;
    private final BigDecimal saldo;

    public ContaBancaria(String titular, BigDecimal saldoInicial) {
        if (saldoInicial == null || saldoInicial.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("O saldo inicial não pode ser negativo.");
        }
        this.titular = titular;
        this.saldo = saldoInicial;
    }

    public ResultadoOperacao sacar(BigDecimal valor) {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            return new Falha("O valor do saque deve ser maior que zero.");
        }
        if (valor.compareTo(this.saldo) > 0) {
            return new Falha("Saldo insuficiente. Saldo atual: R$ " + this.saldo);
        }
        
        // Imutabilidade: cria uma nova instância com o saldo atualizado em vez de mutar o objeto atual
        ContaBancaria novaConta = new ContaBancaria(this.titular, this.saldo.subtract(valor));
        return new Sucesso(novaConta, Instant.now());
    }

    public ResultadoOperacao depositar(BigDecimal valor) {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            return new Falha("O valor do depósito deve ser maior que zero.");
        }
        ContaBancaria novaConta = new ContaBancaria(this.titular, this.saldo.add(valor));
        return new Sucesso(novaConta, Instant.now());
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public String getTitular() {
        return titular;
    }
}
