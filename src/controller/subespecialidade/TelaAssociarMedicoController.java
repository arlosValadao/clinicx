package controller.subespecialidade;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import model.Gerenciador;
import model.Medico;

/**
 * FXML Controller class
 *
 * @author Carlos Valadão
 */
public class TelaAssociarMedicoController implements Initializable {

    @FXML
    private Button btCancelar;

    @FXML
    private Button btConfirmar;

    @FXML
    private ComboBox<Medico> comboBoxMedicos;

    private String nomeEspecialidadeSelecionada;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarComboBoxMedicos();
    }

    @FXML
    void confirmar() {
        Medico medico;
        medico = comboBoxMedicos.getSelectionModel().getSelectedItem();
        if (medico != null) {
            boolean isMedicoAssociado;
            isMedicoAssociado = Gerenciador.associarMedicoSubespecialidade(medico, nomeEspecialidadeSelecionada);
            if (isMedicoAssociado) {
                Alert alert2;
                alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setContentText("Medico associado com sucesso");
                alert2.show();
                fecharTela();
                return;
            }
        }
        Alert alert1 = new Alert(Alert.AlertType.ERROR);
        alert1.setContentText("Você precisa selecionar um medico");
        alert1.show();
    }

    @FXML
    void cancelar() {
        fecharTela();
    }

    private void carregarComboBoxMedicos() {
        List<Medico> listMedicos;
        listMedicos = Gerenciador.getMedicos().values().stream().collect(Collectors.toList());
        ObservableList<Medico> observableMedicos;
        observableMedicos = FXCollections.observableArrayList(listMedicos);
        comboBoxMedicos.setItems(observableMedicos);
    }

    private void fecharTela() {
        Stage stage;
        stage = (Stage) btCancelar.getScene().getWindow();
        stage.close();
    }

    public void setNomeEspecialidadeSelecionada(String nomeEspecialidadeSelecionada) {
        this.nomeEspecialidadeSelecionada = nomeEspecialidadeSelecionada;
    }

}
