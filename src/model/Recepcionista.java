package model;

/**
 *
 * @author Carlos Valadao
 */
/**
 * Classe que representa uma recepcionista
 */
public class Recepcionista {

    private String nome;
    private long cpf;
    private String senha;
    private byte permissao;

    public Recepcionista(String nome, long cpf, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        this.permissao = TipoUsuario.RECEPCIONISTA;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getCpf() {
        return cpf;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public byte getPermissao() {
        return permissao;
    }

}
