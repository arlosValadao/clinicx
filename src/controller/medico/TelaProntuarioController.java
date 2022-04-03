package controller.medico;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Gerenciador;
import model.Paciente;
import model.Prontuario;

public class TelaProntuarioController {

    @FXML
    private Button btConfirmarEdicao;

    @FXML
    private Button btSair;

    @FXML
    private Button btImprimirFormulario;

    @FXML
    private TextField txtNomePaciente;

    @FXML
    private TextArea txtaAnamnese;

    @FXML
    private TextArea txtaDiagnosticosDefinitivos;

    @FXML
    private TextArea txtaExameFisico;

    @FXML
    private TextArea txtaHipotesesDiagnosticas;

    @FXML
    private TextArea txtaTratamentosEfetuados;

    private Paciente paciente;

    @FXML
    public void sair() {
        fecharJanela();
    }

    @FXML
    public void confirmarEdicao() {
        String anamnese = txtaAnamnese.getText();
        String diagnosticosDefinitivos = txtaDiagnosticosDefinitivos.getText();
        String exameFisico = txtaExameFisico.getText();
        String hipotesesDiagnosticas = txtaHipotesesDiagnosticas.getText();
        String tratamentosEfetuados = txtaTratamentosEfetuados.getText();
        Prontuario novoP = new Prontuario(anamnese, exameFisico, hipotesesDiagnosticas,
                diagnosticosDefinitivos, tratamentosEfetuados);
        Gerenciador.atualizarProntuarioPaciente(paciente, novoP);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Prontuario atualizado com sucesso");
        alert.show();
    }

    public void carregarDados() {
        Prontuario prontuarioPaciente = paciente.getProntuario();
        txtNomePaciente.setText(paciente.getNome());
        txtaAnamnese.setText(prontuarioPaciente.getAnamnese());
        txtaDiagnosticosDefinitivos.setText((prontuarioPaciente.getDiagnosticosDefinitivos()));
        txtaExameFisico.setText(prontuarioPaciente.getExameFisico());
        txtaHipotesesDiagnosticas.setText(prontuarioPaciente.getHipotesesDiagnosticas());
        txtaTratamentosEfetuados.setText(prontuarioPaciente.getTratamentosEfetuados());
    }

    private void fecharJanela() {
        Stage stage;
        stage = (Stage) btConfirmarEdicao.getScene().getWindow();
        stage.close();
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

}
