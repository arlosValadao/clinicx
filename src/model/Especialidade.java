package model;

import java.util.HashMap;

/**
 * @author Carlos Valadao Classe que representa a especialidade de um medico
 * @see Medico
 */
public class Especialidade implements Cloneable {

    private String nome;
    private HashMap<Long, Medico> associados;

    public Especialidade(String nome) {
        this.nome = nome;
        this.associados = new HashMap<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public HashMap<Long, Medico> getAssociados() {
        return associados;
    }

    public void setAssociados(HashMap<Long, Medico> associados) {
        this.associados = associados;
    }

    @Override
    public Especialidade clone() throws CloneNotSupportedException {
        return (Especialidade) super.clone();
    }

    @Override
    public String toString() {
        return getNome();
    }
}
