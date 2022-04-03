package controller.paciente;

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
import model.Paciente;

/**
 * FXML Controller class
 *
 * @author Carlos Valadão
 */
public class ListarPacientesController implements Initializable {

    @FXML
    private Button btEditar;

    @FXML
    private Button btRemover;

    @FXML
    private Button btHConsulta;

    @FXML
    private TableView<Paciente> tableViewPacientes;

    @FXML
    private TableColumn<Paciente, Long> colunaCPF;

    @FXML
    private TableColumn<Paciente, String> colunaNome;

    private FXMLLoader TEPLoader;

    private ObservableList<Paciente> observablePacientes;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarTableViewPacientes();
    }

    @FXML
    public void mostrarHistoricoConsultaPaciente() throws IOException {
        Paciente paciente;
        paciente = tableViewPacientes.getSelectionModel().getSelectedItem();
        if (paciente != null) {
            FXMLLoader THCLoader;
            THCLoader = new FXMLLoader(getClass().getResource("/view/paciente/TelaHistoricoConsulta.fxml"));

            Stage stage;
            stage = new Stage();
            stage.setScene(new Scene(THCLoader.load()));
            TelaHistoricoConsultaController controller = THCLoader.getController();
            controller.setPaciente(paciente);
            controller.mostrarTableViewConsultas();
            stage.show();
            return;
        }
        mostrarMsg("Você precisa selecionar um paciente", Alert.AlertType.ERROR);
    }

    @FXML
    public void removerPaciente() {
        Paciente paciente;
        paciente = tableViewPacientes.getSelectionModel().getSelectedItem();
        if (paciente != null) {
            boolean isPacienteRemovido;
            isPacienteRemovido = Gerenciador.removerPaciente(paciente);
            if (isPacienteRemovido) {
                mostrarMsg("Paciente removido com sucesso", Alert.AlertType.INFORMATION);
                carregarTableViewPacientes();
                return;
            }
            mostrarMsg("Impossível remover este paciente, pois ele\n"
                    + "já realizou alguma consulta", Alert.AlertType.ERROR);
            return;
        }
        mostrarMsg("Você precisa selecionar um paciente", Alert.AlertType.ERROR);
    }

    @FXML
    public void editarPaciente() throws IOException, CloneNotSupportedException {
        Paciente antigoPaciente;
        antigoPaciente = tableViewPacientes.getSelectionModel().getSelectedItem();
        if (antigoPaciente != null) {
            Paciente cloneAntigoPaciente;
            cloneAntigoPaciente = antigoPaciente.clone();
            boolean isBotaoConfirmarClicked;
            isBotaoConfirmarClicked = carregarTelaEditarPaciente(cloneAntigoPaciente, antigoPaciente);
            if (isBotaoConfirmarClicked) {
                Gerenciador.atualizarPaciente(antigoPaciente, cloneAntigoPaciente);
                carregarTableViewPacientes();
                return;
            }
        }
        mostrarMsg("Você precisa selecionar um paciente", Alert.AlertType.ERROR);
    }

    private void carregarTableViewPacientes() {
        colunaCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        List<Paciente> listPacientes;
        listPacientes = Gerenciador.getPacientes().values().stream().collect(Collectors.toList());
        observablePacientes = FXCollections.observableArrayList(listPacientes);
        tableViewPacientes.setItems(observablePacientes);
    }

    private boolean carregarTelaEditarPaciente(Paciente cloneAntigoPaciente, Paciente antigoPaciente) throws IOException {
        Stage stage;
        stage = new Stage();
        TEPLoader = new FXMLLoader(getClass().getResource("/view/paciente/TelaEditarPaciente.fxml"));
        stage.setScene(new Scene(TEPLoader.load()));
        TelaEditarPacienteController controller = TEPLoader.getController();
        System.out.println(controller);
        controller.setPaciente(cloneAntigoPaciente);
        controller.setPrimaryStage(stage);
        controller.setAntigoPaciente(antigoPaciente);
        controller.carregarDados();
        stage.showAndWait();
        return controller.isBotaoConfirmarClicked();
    }

    private void mostrarMsg(String texto, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setContentText(texto);
        alerta.show();
    }
}
