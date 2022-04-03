package controller.medico;

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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Especialidade;
import model.Gerenciador;
import model.Medico;

/**
 *
 * @author Carlos Valadão
 */
public class TelaCadastrarMedicoController implements Initializable {

    @FXML
    private Button btCadastrar;

    @FXML
    private Button btCancelar;

    @FXML
    private ComboBox<Especialidade> comboBoxEspecialidade1;

    @FXML
    private ComboBox<Especialidade> comboBoxEspecialidade2;

    @FXML
    private TextField txtCpf;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtSenha;

    private ObservableList<Especialidade> observableEspecialidade;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarComboBoxEspecialidades();
    }

    @FXML
    public void cadastrarMedico() {
        Medico novoMedico;
        String nomeMedico = txtNome.getText();
        long cpfMedico = Long.valueOf(txtCpf.getText());
        String senhaMedico = txtSenha.getText();
        Especialidade especialidadeMedico = comboBoxEspecialidade1.getSelectionModel().getSelectedItem();
        novoMedico = new Medico(nomeMedico, cpfMedico, senhaMedico);
        // Caso nao foi selecionada nenhuma especialidade
        if (especialidadeMedico != null) {
            novoMedico.setEspecialidade(especialidadeMedico.getNome());
            Gerenciador.associarMedicoEspecialidade(novoMedico, especialidadeMedico.getNome());
        }
        boolean isMedicoCadastrado;
        isMedicoCadastrado = Gerenciador.adicionarMedico(novoMedico);
        // Caso o medico seja cadastrado com sucesso
        if (isMedicoCadastrado) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Medico cadastrado com sucesso");
            alert.show();
            fecharTela();
            return;
        }
        Alert alert1 = new Alert(Alert.AlertType.ERROR);
        alert1.setContentText("Esse CPF ja existe no sistema");
        alert1.show();
    }

    @FXML
    public void cancelar() {
        fecharTela();
    }

    private void carregarComboBoxEspecialidades() {
        List<Especialidade> listEspecialidades;
        listEspecialidades = Gerenciador.getEspecialidades().values().stream().collect(Collectors.toList());
        observableEspecialidade = FXCollections.observableArrayList(listEspecialidades);
        comboBoxEspecialidade1.setItems(observableEspecialidade);
    }

    private void carregarComboBoxSubespecialidade() {
    }

    private void fecharTela() {
        Stage stage;
        stage = (Stage) btCadastrar.getScene().getWindow();
        stage.close();
    }
}
