package br.com.techsantanna.meioambienteemjogo.model;

public class Usuario {
    private String nome, email, senha, sexo;
    private int idade;
    private Arvore arvore = new Arvore();

    public Usuario(String nome, String sexo, String email, String senha, int idade, String ar) {
        this.nome = nome;
        this.sexo = sexo;
        this.email = email;
        this.senha = senha;
        this.idade = idade;
        this.arvore.setNome(""+ar);
        this.arvore.setNivel(1);
        this.arvore.setXp(0);
        this.arvore.setQtdAdubar(10);
        this.arvore.setQtdAntiPragas(10);
        this.arvore.setQtdPodar(10);
        this.arvore.setQtdReagar(10);
    }
    public  Usuario(){

    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public Arvore getArvore() {
        return arvore;
    }

    public void setArvore(Arvore arvore) {
        this.arvore = arvore;
    }
}
