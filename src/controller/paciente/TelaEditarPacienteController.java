package controller.paciente;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Gerenciador;
import model.Paciente;

/**
 * FXML Controller class
 *
 * @author Carlos Valadão
 */
public class TelaEditarPacienteController {

    @FXML
    private Button btCancelar;

    @FXML
    private Button btEditar;

    @FXML
    private TextField txtCPF;

    @FXML
    private TextField txtNome;

    private Paciente antigoPaciente;
    private Paciente cloneAntigoPaciente;
    private Stage primaryStage;
    private boolean botaoConfirmarClicked;

    public void setPaciente(Paciente paciente) {
        this.cloneAntigoPaciente = paciente;
    }

    public void setAntigoPaciente(Paciente antigoPaciente) {
        this.antigoPaciente = antigoPaciente;
    }

    public void carregarDados() {
        txtCPF.setText(String.valueOf(cloneAntigoPaciente.getCpf()));
        txtNome.setText(cloneAntigoPaciente.getNome());
    }

    public void setText(String txt) {
        this.txtCPF.setText(txt);
    }

    @FXML
    public void confirmar() {
        if (cloneAntigoPaciente != null) {
            cloneAntigoPaciente.setNome(txtNome.getText());
            cloneAntigoPaciente.setCpf(Long.valueOf(txtCPF.getText()));
            if (cloneAntigoPaciente.getCpf() != antigoPaciente.getCpf()) {
                if (Gerenciador.buscarPaciente(cloneAntigoPaciente.getCpf()) != null) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Esse CPF já existe no sistema");
                    alert.show();
                    return;
                }
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Paciente Alterado com sucesso!");
            alert.show();
            fecharTela();
            botaoConfirmarClicked = true;
        }
    }

    @FXML
    public void cancelar() {
        fecharTela();
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @SuppressWarnings("empty-statement")
    private void fecharTela() {
        primaryStage.close();;
    }

    public boolean isBotaoConfirmarClicked() {
        return botaoConfirmarClicked;
    }

}
