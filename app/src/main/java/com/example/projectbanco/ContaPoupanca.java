package com.example.projectbanco;

public class ContaPoupanca extends ContaBancaria {
    int diaDeRendimento;

    public ContaPoupanca(String cliente, int num_conta, float saldo, int diaDeRendimento) {
        super(cliente, num_conta, saldo);
        this.diaDeRendimento = diaDeRendimento;
    }

    public void calcularNovoSaldo(float taxaDeRendimento) {
        saldo += saldo * Math.pow(taxaDeRendimento, diaDeRendimento);
    }
}
