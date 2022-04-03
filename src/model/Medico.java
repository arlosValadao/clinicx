package model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Carlos Valadao
 * Classe que representa um medico
 */
public class Medico implements Cloneable {

    private String nome;
    private long cpf;
    private String senha;
    private String especialidade;
    private String subespecialidade;
    private final List<Consulta> consultasMarcadas;
    private final List<Consulta> consultasRealizadas;
    private final byte permissao;

    public Medico(String nome, long cpf, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        this.especialidade = null;
        this.subespecialidade = null;
        this.consultasRealizadas = new ArrayList<>();
        this.consultasMarcadas = new ArrayList<>();
        this.permissao = TipoUsuario.MEDICO;
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

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getSubespecialidade() {
        return subespecialidade;
    }

    public void setSubespecialidade(String subespecialidade) {
        this.subespecialidade = subespecialidade;
    }

    public byte getPermissao() {
        return permissao;
    }

    public List<Consulta> getConsultasMarcadas() {
        return consultasMarcadas;
    }

    public List<Consulta> getConsultasRealizadas() {
        return consultasRealizadas;
    }

    @Override
    public String toString() {
        return getNome();
    }

    @Override
    public Medico clone() throws CloneNotSupportedException {
        return (Medico) super.clone();
    }
}
