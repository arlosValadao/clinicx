package controller.medico;

import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
public class TelaEditarMedicoController {

    @FXML
    private Button btCancelar;

    @FXML
    private Button btConfirmar;

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

    private Medico antigoMedico;
    private Medico cloneAntigoMedico;
    private TelaListarMedicosController controller;

    @FXML
    public void confirmarEdicao() {
        Especialidade novaEspec = comboBoxEspecialidade1.getSelectionModel().getSelectedItem();
        Especialidade novaEspec2 = comboBoxEspecialidade2.getSelectionModel().getSelectedItem();
        String novoNome = txtNome.getText();
        long novoCpf = Long.valueOf(txtCpf.getText());
        String novaSenha = txtSenha.getText();
        cloneAntigoMedico.setNome(novoNome);
        cloneAntigoMedico.setCpf(novoCpf);
        cloneAntigoMedico.setSenha(novaSenha);
        boolean isMedicoAtualizado;
        isMedicoAtualizado = Gerenciador.atualizarMedico(antigoMedico, cloneAntigoMedico);
        if (isMedicoAtualizado) {
            if (novaEspec != null) {
                Gerenciador.desassociarMedicoEspecialidade(cloneAntigoMedico);
                Gerenciador.associarMedicoEspecialidade(cloneAntigoMedico, novaEspec.getNome());
                Gerenciador.desassociarMedicoSubespecialidade(cloneAntigoMedico);
                Gerenciador.associarMedicoSubespecialidade(cloneAntigoMedico, novaEspec2.getNome());
            }
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setContentText("Medico atualizado com sucesso");
            alert1.show();
            controller.carregarTableViewMedicos();
            fecharJanela();
            return;
        }
        Alert alert2 = new Alert(Alert.AlertType.ERROR);
        alert2.setContentText("O novo CPF ja existe no sistema");
        alert2.show();
    }

    @FXML
    void cancelar(ActionEvent event) {
        fecharJanela();
    }

    private void fecharJanela() {
        Stage stage;
        stage = (Stage) btCancelar.getScene().getWindow();
        stage.close();
    }

    public void setCloneAntigoMedico(Medico cloneAntigoMedico) {
        this.cloneAntigoMedico = cloneAntigoMedico;
    }

    public void setAntigoMedico(Medico antigoMedico) {
        this.antigoMedico = antigoMedico;
    }

    public void carregarDados() {
        carregarComboBoxEspecialidade();
        carregarComboBoxSubespecialidade();
        txtCpf.setText(String.valueOf(cloneAntigoMedico.getCpf()));
        txtNome.setText(cloneAntigoMedico.getNome());
        txtSenha.setText(cloneAntigoMedico.getSenha());
        comboBoxEspecialidade1.setPromptText(cloneAntigoMedico.getEspecialidade());
        comboBoxEspecialidade2.setPromptText(cloneAntigoMedico.getSubespecialidade());
    }

    private void carregarComboBoxEspecialidade() {
        List<Especialidade> listEspec;
        listEspec = Gerenciador.getEspecialidades().values().stream().collect(Collectors.toList());
        ObservableList<Especialidade> observableEspecialidade;
        observableEspecialidade = FXCollections.observableArrayList(listEspec);
        comboBoxEspecialidade1.setItems(observableEspecialidade);
    }

    private void carregarComboBoxSubespecialidade() {
        List<Especialidade> listEspec;
        listEspec = Gerenciador.getSubespecialidades().values().stream().collect(Collectors.toList());
        ObservableList<Especialidade> observableEspecialidade;
        observableEspecialidade = FXCollections.observableArrayList(listEspec);
        comboBoxEspecialidade1.setItems(observableEspecialidade);
    }

    public void setController(TelaListarMedicosController controller) {
        this.controller = controller;
    }

}
