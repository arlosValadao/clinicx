package model;

/**
 *
 * @author Carlos Valadao
 */
/**
 * Classe que representa o prontuario de um paciente
 *
 * @see Paciente
 */
public class Prontuario {

    private String anamnese;
    private String exameFisico;
    private String hipotesesDiagnosticas;
    private String diagnosticosDefinitivos;
    private String tratamentosEfetuados;

    public Prontuario() {
        this.anamnese = null;
        this.diagnosticosDefinitivos = null;
        this.exameFisico = null;
        this.hipotesesDiagnosticas = null;
        this.tratamentosEfetuados = null;
    }

    public Prontuario(String anamnese, String exameFisico, String hipotesesDiagnosticas, String diagnosticosDefinitivos, String tratamentosEfetuados) {
        this.anamnese = anamnese;
        this.exameFisico = exameFisico;
        this.hipotesesDiagnosticas = hipotesesDiagnosticas;
        this.diagnosticosDefinitivos = diagnosticosDefinitivos;
        this.tratamentosEfetuados = tratamentosEfetuados;
    }

    public String getAnamnese() {
        return anamnese;
    }

    public void setAnamnese(String anamnese) {
        this.anamnese = anamnese;
    }

    public String getExameFisico() {
        return exameFisico;
    }

    public void setExameFisico(String exameFisico) {
        this.exameFisico = exameFisico;
    }

    public String getHipotesesDiagnosticas() {
        return hipotesesDiagnosticas;
    }

    public void setHipotesesDiagnosticas(String hipotesesDiagnosticas) {
        this.hipotesesDiagnosticas = hipotesesDiagnosticas;
    }

    public String getDiagnosticosDefinitivos() {
        return diagnosticosDefinitivos;
    }

    public void setDiagnosticosDefinitivos(String diagnosticosDefinitivos) {
        this.diagnosticosDefinitivos = diagnosticosDefinitivos;
    }

    public String getTratamentosEfetuados() {
        return tratamentosEfetuados;
    }

    public void setTratamentosEfetuados(String tratamentosEfetuados) {
        this.tratamentosEfetuados = tratamentosEfetuados;
    }

    public boolean isEmpty() {
        boolean empty;
        empty = anamnese != null && diagnosticosDefinitivos != null
                && exameFisico != null && hipotesesDiagnosticas != null
                && tratamentosEfetuados != null;
        return empty;
    }

}
