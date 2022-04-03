package controller.paciente;

/**
 *
 * @author Carlos Valadao
 */
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Paciente;
import model.Gerenciador;

public class TelaCadastrarPacienteController {

    @FXML
    private Button btCadastrar;

    @FXML
    private TextField txtCPF;

    @FXML
    private TextField txtNome;

    public void cadastrarPaciente() {
        long cpfPaciente = Long.valueOf(txtCPF.getText());
        boolean isCadastrarPaciente;
        isCadastrarPaciente = Gerenciador.adicionarPaciente(new Paciente(txtNome.getText(), cpfPaciente));
        if (isCadastrarPaciente) {
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setContentText("Paciente cadastrado com sucesso");
            alert1.show();
            fecharTela();
            return;
        }
        Alert alert2 = new Alert(Alert.AlertType.ERROR);
        alert2.setContentText("O CPF informado ja existe no sistema");
        alert2.show();
    }

    private void fecharTela() {
        Stage stage = (Stage) this.btCadastrar.getScene().getWindow();
        stage.close();
    }
}
