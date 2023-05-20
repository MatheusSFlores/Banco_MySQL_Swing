package model.bean;

import java.text.DecimalFormat;
import model.dao.UsuarioDAO;

/**
 *
 * @author mathe
 */
public class Usuario {

    private int id;
    private String nome;
    private String cpf;
    private String celular;
    private double saldo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void subSaldo(double valor) {
        this.saldo -= valor;
    }

    public String pontCpf() {
        String cpfSemPontos = this.cpf;
        return cpfSemPontos.substring(0, 3) + "."
                + cpfSemPontos.substring(3, 6) + "."
                + cpfSemPontos.substring(6, 9) + "-"
                + cpfSemPontos.substring(9);
    }
    public String pontSaldo() {
        double saldoSemPontos = this.saldo;
        DecimalFormat df = new DecimalFormat("#,##0.00");
        return df.format(saldoSemPontos);
    }

}
