/*
 *@author:<Wallace Moura Machado de Oliveira;1110482413004>
 */

package com.example.projectbanco;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    RadioGroup rgTipoConta;
    RadioButton rbContaPoupanca, rbContaEspecial;
    EditText txtCliente, txtNumConta, txtSaldo, txtDiaDeRendimento, txtLimite, txtValor, txtTaxaRendimento;
    Button btnCriarConta, btnSacar, btnDepositar, btnCalcularRendimento, btnMostrarDados;
    TextView lblResultado;

    ContaBancaria conta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rgTipoConta = findViewById(R.id.rgTipoConta);
        rbContaPoupanca = findViewById(R.id.rbContaPoupanca);
        rbContaEspecial = findViewById(R.id.rbContaEspecial);
        txtCliente = findViewById(R.id.txtCliente);
        txtNumConta = findViewById(R.id.txtNumConta);
        txtSaldo = findViewById(R.id.txtSaldo);
        txtDiaDeRendimento = findViewById(R.id.txtDiaDeRendimento);
        txtLimite = findViewById(R.id.txtLimite);
        txtValor = findViewById(R.id.txtValor);
        txtTaxaRendimento = findViewById(R.id.txtTaxaRendimento);
        btnCriarConta = findViewById(R.id.btnCriarConta);
        btnSacar = findViewById(R.id.btnSacar);
        btnDepositar = findViewById(R.id.btnDepositar);
        btnCalcularRendimento = findViewById(R.id.btnCalcularRendimento);
        btnMostrarDados = findViewById(R.id.btnMostrarDados);
        lblResultado = findViewById(R.id.lblResultado);

        btnCriarConta.setOnClickListener((View v) -> {
                criarConta();
        });

        btnSacar.setOnClickListener((View v) -> {
                sacar();
        });

        btnDepositar.setOnClickListener((View v) -> {
                depositar();
        });

        btnCalcularRendimento.setOnClickListener((View v) -> {
                calcularRendimento();
        });

        btnMostrarDados.setOnClickListener((View v) -> {
                mostrarDados();
        });

        rgTipoConta.setOnCheckedChangeListener((RadioGroup group, int checkedId) -> {
                if (checkedId == R.id.rbContaPoupanca) {
                    txtDiaDeRendimento.setVisibility(View.VISIBLE);
                    txtLimite.setVisibility(View.GONE);
                    btnCalcularRendimento.setVisibility(View.VISIBLE);
                    txtTaxaRendimento.setVisibility(View.VISIBLE);
                } else if (checkedId == R.id.rbContaEspecial) {
                    txtLimite.setVisibility(View.VISIBLE);
                    txtDiaDeRendimento.setVisibility(View.GONE);
                    btnCalcularRendimento.setVisibility(View.GONE);
                    txtTaxaRendimento.setVisibility(View.GONE);
                }
        });
    }

    public void criarConta() {
        String cliente = txtCliente.getText().toString();
        int num_conta = Integer.parseInt(txtNumConta.getText().toString());
        float saldo = Float.parseFloat(txtSaldo.getText().toString());

        if (rbContaPoupanca.isChecked()) {
            int diaDeRendimento = Integer.parseInt(txtDiaDeRendimento.getText().toString());
            conta = new ContaPoupanca(cliente, num_conta, saldo, diaDeRendimento);
        } else if (rbContaEspecial.isChecked()) {
            float limite = Float.parseFloat(txtLimite.getText().toString());
            conta = new ContaEspecial(cliente, num_conta, saldo, limite);
        }

        lblResultado.setText("Conta criada com sucesso.");
    }

    public void sacar() {
        if (conta != null) {
            float valor = Float.parseFloat(txtValor.getText().toString());
            float saldoAnterior = conta.saldo;
            conta.sacar(valor);
            if (saldoAnterior != conta.saldo) {
                lblResultado.setText("Saque realizado com sucesso.");
            } else {
                lblResultado.setText("Saque não realizado. Saldo insuficiente.");
            }
        } else {
            lblResultado.setText("Crie uma conta primeiro.");
        }
    }

    public void depositar() {
        if (conta != null) {
            float valor = Float.parseFloat(txtValor.getText().toString());
            conta.depositar(valor);
            lblResultado.setText("Depósito realizado com sucesso.");
        } else {
            lblResultado.setText("Crie uma conta primeiro.");
        }
    }

    public void calcularRendimento() {
        if("".equals(txtTaxaRendimento.getText().toString()))
            lblResultado.setText("Informe a taxa de rendimento.");
        else if (conta != null && conta instanceof ContaPoupanca) {
            float taxa = Float.parseFloat(txtTaxaRendimento.getText().toString());
            ((ContaPoupanca) conta).calcularNovoSaldo(taxa);
            lblResultado.setText("Rendimento calculado com sucesso.");
        } else {
            lblResultado.setText("A conta não é poupança.");
        }
    }

    public void mostrarDados() {
        if (conta != null) {
            lblResultado.setText(conta.getDados());
        } else {
            lblResultado.setText("Crie uma conta primeiro.");
        }
    }
}