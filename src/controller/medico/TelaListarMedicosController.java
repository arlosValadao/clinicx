package controller.medico;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Gerenciador;
import model.Medico;

/**
 *
 * @author Carlos Valadão
 */
public class TelaListarMedicosController implements Initializable {

    @FXML
    private Button btEditar;

    @FXML
    private Button btRemover;

    @FXML
    private Button btConsultas;

    @FXML
    private TableColumn<Medico, Long> colunaCPF;

    @FXML
    private TableColumn<Medico, String> colunaEspecialidade;

    @FXML
    private TableColumn<Medico, String> colunaNome;

    @FXML
    private TableColumn<Medico, String> colunaSubespecialidade;

    @FXML
    private TableView<Medico> tableViewMedicos;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarTableViewMedicos();
    }

    @FXML
    public void listarConsultasMedico() throws IOException {
        Medico medico;
        medico = tableViewMedicos.getSelectionModel().getSelectedItem();
        if (medico != null) {
            FXMLLoader TLCMLoader = new FXMLLoader(getClass().getResource("/view/medico/TelaListarConsultasMedico.fxml"));

            Stage stage;
            stage = new Stage();
            stage.setScene(new Scene(TLCMLoader.load()));

            TelaListarConsultasMedicoController controller = TLCMLoader.getController();
            controller.setMedicoSelecionado(medico);
            controller.mostrarTableViewConsultas();
            stage.show();
            return;
        }
        mostrarMsg("Você precisa selecionar um Medico", Alert.AlertType.ERROR);
    }

    @FXML
    public void editarMedico() throws IOException, CloneNotSupportedException {
        Medico medico;
        medico = tableViewMedicos.getSelectionModel().getSelectedItem();
        if (medico != null) {
            Medico cloneMedico;
            cloneMedico = medico.clone();

            FXMLLoader TEMLoader = new FXMLLoader(getClass().getResource("/view/medico/TelaEditarMedico.fxml"));
            Stage stage;
            stage = new Stage();
            stage.setScene(new Scene(TEMLoader.load()));

            TelaEditarMedicoController controller = TEMLoader.getController();
            controller.setCloneAntigoMedico(cloneMedico);
            controller.setAntigoMedico(medico);
            controller.carregarDados();
            controller.setController(this);
            stage.show();
            return;
        }
        mostrarMsg("Você precisa selecionar um Medico", Alert.AlertType.ERROR);
    }

    @FXML
    public void removerMedico() {
        Medico medico;
        medico = tableViewMedicos.getSelectionModel().getSelectedItem();
        if (medico != null) {
            boolean isMedicoRemovido;
            isMedicoRemovido = Gerenciador.removerMedico(medico);
            if (isMedicoRemovido) {
                mostrarMsg("Medico removido com sucesso", Alert.AlertType.INFORMATION);
                carregarTableViewMedicos();
                return;
            }
            mostrarMsg("Impossível remover este paciente, pois ele\n"
                    + "já realizou alguma consulta", Alert.AlertType.ERROR);
            return;
        }
        mostrarMsg("Você precisa selecionar um médico", Alert.AlertType.ERROR);
    }

    public void carregarTableViewMedicos() {
        colunaCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaEspecialidade.setCellValueFactory(new PropertyValueFactory<>("especialidade"));
        colunaSubespecialidade.setCellValueFactory(new PropertyValueFactory<>("subespecialidade"));
        List<Medico> listMedicos;
        listMedicos = Gerenciador.getMedicos().values().stream().collect(Collectors.toList());
        ObservableList<Medico> observableMedicos;
        observableMedicos = FXCollections.observableArrayList(listMedicos);
        tableViewMedicos.setItems(observableMedicos);
    }

    private void fecharJanela() {
        Stage stage;
        stage = (Stage) btEditar.getScene().getWindow();
        stage.close();
    }

    private void mostrarMsg(String texto, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setContentText(texto);
        alerta.show();
    }
}
