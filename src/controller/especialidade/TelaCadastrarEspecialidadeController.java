package controller.especialidade;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Especialidade;
import model.Gerenciador;

/**
 *
 * @author Carlos Valadão
 */
public class TelaCadastrarEspecialidadeController {

    @FXML
    private Button btAdicionar;

    @FXML
    private Button btCancelar;

    @FXML
    private TextField txtNomeEspecialidade;

    @FXML
    public void cadastrarEspecialidade() {
        String nomeEsp = txtNomeEspecialidade.getText();
        Especialidade esp = Gerenciador.buscarEspecialidade(nomeEsp);
        if (esp == null) {
            Gerenciador.adicionarEspecialidade(nomeEsp);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Especialidade cadastrada no sistema");
            alert.show();
            fecharJanela();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Essa especialidade já está cadastrada no sistema");
        alert.show();
    }

    @FXML
    public void cancelar() {
        fecharJanela();
    }

    public void fecharJanela() {
        Stage stage;
        stage = (Stage) btCancelar.getScene().getWindow();
        stage.close();
    }

}
