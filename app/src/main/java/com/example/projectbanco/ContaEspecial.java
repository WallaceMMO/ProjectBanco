package com.example.projectbanco;

public class ContaEspecial extends ContaBancaria {
    float limite;

    public ContaEspecial(String cliente, int num_conta, float saldo, float limite) {
        super(cliente, num_conta, saldo);
        this.limite = limite;
    }

    @Override
    public void sacar(float valor) {
        if (saldo - valor >= -limite) {
            saldo -= valor;
        }
    }
}

