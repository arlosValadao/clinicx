package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Carlos Valadao
 */

/**
 * Classe que representa uma consulta de um usuário
 *
 * @see Paciente
 */
public class Consulta {

    private String nomePaciente;
    private final long cpfPaciente;
    private String especialidade;
    private LocalDate data;
    private LocalTime hora;
    private String dataString;
    private String horaString;
    private String medicoResponsavel;

    public Consulta(String nomePaciente, long cpfPaciente, String especialidade,
            String medico, LocalDate data, LocalTime hora) {
        this.nomePaciente = nomePaciente;
        this.especialidade = especialidade;
        this.cpfPaciente = cpfPaciente;
        this.data = data;
        this.hora = hora;
        this.medicoResponsavel = medico;
        this.horaString = hora.toString();
        this.dataString = data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getMedicoResponsavel() {
        return medicoResponsavel;
    }

    public void setMedicoResponsavel(String medicoResponsavel) {
        this.medicoResponsavel = medicoResponsavel;
    }

    public String getNomePaciente() {
        return nomePaciente;
    }

    public void setNomePaciente(String nomePaciente) {
        this.nomePaciente = nomePaciente;
    }

    public long getCpfPaciente() {
        return cpfPaciente;
    }

    public String getDataString() {
        return dataString;
    }

    public void setDataString(String dataString) {
        this.dataString = dataString;
    }

    public String getHoraString() {
        return horaString;
    }

    public void setHoraString(String horaString) {
        this.horaString = horaString;
    }

}
