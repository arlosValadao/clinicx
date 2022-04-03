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
public class TelaEditarEspecialidadeController {

    @FXML
    private Button btCancelar;

    @FXML
    private Button btConfirmar;

    @FXML
    private TextField txtNomeEspecialidade;

    private boolean botaoConfirmarClicked;
    private Especialidade antigaEspecialidade;
    private Especialidade cloneAntigaEspecialidade;

    @FXML
    void confirmar() {
        String nomeEsp = txtNomeEspecialidade.getText();
        cloneAntigaEspecialidade.setNome(nomeEsp);
        if (!cloneAntigaEspecialidade.getNome().equals(antigaEspecialidade.getNome())) {
            if (Gerenciador.buscarEspecialidade(nomeEsp) != null) {
                Alert alert1 = new Alert(Alert.AlertType.ERROR);
                alert1.setContentText("Essa especialidade ja existe no sistema");
                alert1.show();
                return;
            }

        }
        Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
        alert2.setContentText("Especialidade alterada com sucesso");
        alert2.show();
        botaoConfirmarClicked = true;
        fecharJanela();
    }

    @FXML
    void cancelar() {
        fecharJanela();
    }

    public void carregarDados() {
        txtNomeEspecialidade.setText(antigaEspecialidade.getNome());
    }

    public void setAntigaEspecialidade(Especialidade antigaEspecialidade) {
        this.antigaEspecialidade = antigaEspecialidade;
    }

    public void setCloneAntigaEspecialidade(Especialidade cloneAntigaEspecialidade) {
        this.cloneAntigaEspecialidade = cloneAntigaEspecialidade;
    }

    public boolean isBotaoConfirmarClicked() {
        return botaoConfirmarClicked;
    }

    private void fecharJanela() {
        Stage stage;
        stage = (Stage) btCancelar.getScene().getWindow();
        stage.close();
    }
}
