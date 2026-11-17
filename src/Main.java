package src;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        var conta = new ContaBancaria("Dev Júnior", new BigDecimal("100.00"));

        System.out.println("Saldo inicial: R$ " + conta.getSaldo());

        // Tentativa 1: Saque acima do limite
        processarOperacao(conta.sacar(new BigDecimal("150.00")));

        // Tentativa 2: Saque válido
        var resultado = conta.sacar(new BigDecimal("30.00"));
        processarOperacao(resultado);
    }

    private static void processarOperacao(ContaBancaria.ResultadoOperacao resultado) {
        // Pattern Matching para switch (Java 21)
        switch (resultado) {
            case ContaBancaria.Sucesso s -> 
                System.out.println("✅ Operação realizada às " + s.timestamp() + " | Novo saldo: R$ " + s.contaAtualizada().getSaldo());
            case ContaBancaria.Falha f -> 
                System.out.println("❌ Operação negada: " + f.motivo());
        }
    }
}
