package model;

import java.util.ArrayList;

/**
 *
 * @author Carlos Valadao
 */
// Classe que representa o paciente
public class Paciente implements Cloneable {

    private String nome;
    private long cpf;
    private Prontuario prontuario;
    private ArrayList<Consulta> consultasRealizadas;
    private ArrayList<Consulta> consultasMarcadas;
    private final byte permissao;

    public Paciente(String nome, long cpf) {
        this.nome = nome;
        this.cpf = cpf;
        this.prontuario = new Prontuario();
        this.consultasMarcadas = new ArrayList<>();
        this.consultasRealizadas = new ArrayList<>();
        this.permissao = TipoUsuario.PACIENTE;
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

    public Prontuario getProntuario() {
        return prontuario;
    }

    public void setProntuario(Prontuario prontuario) {
        this.prontuario = prontuario;
    }

    public ArrayList<Consulta> getConsultasRealizadas() {
        return consultasRealizadas;
    }

    public void setConsultasRealizadas(ArrayList<Consulta> consultasRealizadas) {
        this.consultasRealizadas = consultasRealizadas;
    }

    public ArrayList<Consulta> getConsultasMarcadas() {
        return consultasMarcadas;
    }

    public void setConsultasMarcadas(ArrayList<Consulta> consultasMarcadas) {
        this.consultasMarcadas = consultasMarcadas;
    }

    public byte getPermissao() {
        return permissao;
    }

    @Override
    public Paciente clone() throws CloneNotSupportedException {
        return (Paciente) super.clone();
    }

    @Override
    public String toString() {
        return getNome();
    }
}
