package TestCases;

public class Conta {
    private String email;
    private String senha;
    private String userName;
    private String numero;
    private String digito;
    private double saldoinicial;

    public Conta(String email, String senha, String userName) {
        this.email = email;
        this.senha = senha;
        this.userName = userName;
    }

    public String getContaFormatada() {
        return numero + "-" + digito;
    }

    public double getSaldoinicial() {
        return this.saldoinicial;
    }

    public void setSaldoinicial(double saldoinicial) {
        this.saldoinicial = saldoinicial;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNumero() {
        return this.numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getDigito() {
        return this.digito;
    }

    public void setDigito(String digito) {
        this.digito = digito;
    }
}