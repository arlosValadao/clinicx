package controller.medico;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Gerenciador;

public class TelaReceitaController {

    @FXML
    private Button btCancelar;

    @FXML
    private Button btImprimirReceita;

    @FXML
    private TextField txtNomePaciente;

    @FXML
    private TextArea txtaPrescricao;

    private String nomePaciente;
    private String nomeMedico;

    @FXML
    public void imprimirReceita() throws IOException {
        String nomePacienteLocal = txtNomePaciente.getText();
        String prescricao = txtaPrescricao.getText();
        Gerenciador.imprimirReceita(nomeMedico, nomePacienteLocal, prescricao);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Receita impressa com sucesso");
        alert.show();
        fecharJanela();
    }

    @FXML
    public void cancelar() {
        fecharJanela();
    }

    private void fecharJanela() {
        Stage stage;
        stage = (Stage) btCancelar.getScene().getWindow();
        stage.close();
    }

    public void carregarDados() {
        txtNomePaciente.setText(nomePaciente);
    }

    public void setNomePaciente(String nomePaciente) {
        this.nomePaciente = nomePaciente;
    }

    public void setNomeMedico(String nomeMedico) {
        this.nomeMedico = nomeMedico;
    }

}
