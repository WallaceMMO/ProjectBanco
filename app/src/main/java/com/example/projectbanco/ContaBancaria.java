package com.example.projectbanco;

public class ContaBancaria {
    String cliente;
    int num_conta;
    float saldo;

    public ContaBancaria(String cliente, int num_conta, float saldo) {
        this.cliente = cliente;
        this.num_conta = num_conta;
        this.saldo = saldo;
    }

    public void sacar(float valor) {
        if (saldo - valor >= 0) {
            saldo -= valor;
        }
    }

    public void depositar(float valor) {
        saldo += valor;
    }

    public String getDados() {
        return "Cliente: " + cliente + "\nNúmero da Conta: " + num_conta + "\nSaldo: " + saldo;
    }
}
