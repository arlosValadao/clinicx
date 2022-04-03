package controller.especialidade;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import model.Especialidade;
import model.Gerenciador;

/**
 *
 * @author Carlos Valadão
 */
public class ListarEspecialidadesController implements Initializable {

    @FXML
    private Button btEditar;

    @FXML
    private Button btMedicosAssociados;

    @FXML
    private Button btAssociarMedico;

    @FXML
    private Button btRemover;

    @FXML
    private TableColumn<Especialidade, String> colunaNome;

    @FXML
    private TableView<Especialidade> tableViewEspecialidades;

    private ObservableList<Especialidade> observableEspecialidades;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarTableViewEspecialidades();
    }

    @FXML
    void editarEspecialidade() throws IOException, CloneNotSupportedException {
        Especialidade antigaEspecialidade;
        antigaEspecialidade = tableViewEspecialidades.getSelectionModel().getSelectedItem();
        if (antigaEspecialidade != null) {
            Especialidade cloneAntigaEspecialidade;
            cloneAntigaEspecialidade = antigaEspecialidade.clone();
            boolean isBotaoConfirmarClicked;
            isBotaoConfirmarClicked = carregarTelaEditarEspecialidade(antigaEspecialidade, cloneAntigaEspecialidade);
            if (isBotaoConfirmarClicked) {
                Gerenciador.atualizarEspecialidade(antigaEspecialidade, cloneAntigaEspecialidade);
                carregarTableViewEspecialidades();
                return;
            }
        }
        mostrarMsg("Você precisa selecionar uma especialidade", Alert.AlertType.ERROR);
    }

    @FXML
    void removerEspecialidade(ActionEvent event) {
        Especialidade especialidade;
        especialidade = tableViewEspecialidades.getSelectionModel().getSelectedItem();
        if (especialidade != null) {
            boolean isEspecialidadeRemovida;
            isEspecialidadeRemovida = Gerenciador.removerEspecialidade(especialidade.getNome());
            if (isEspecialidadeRemovida) {
                mostrarMsg("Especialidade removida com sucesso", Alert.AlertType.INFORMATION);
                carregarTableViewEspecialidades();
                return;
            }
            mostrarMsg("Ainda há médicos associados à esta especialidade", Alert.AlertType.ERROR);
            return;
        }
        mostrarMsg("Você precisa selecionar uma especialidade", Alert.AlertType.ERROR);
    }

    /**
     *
     * @throws IOException
     */
    @FXML
    public void carregarTelaMedicosAssociados() throws IOException {
        Especialidade especialidade;
        especialidade = tableViewEspecialidades.getSelectionModel().getSelectedItem();
        if (especialidade != null) {
            FXMLLoader LMALoader = new FXMLLoader(getClass().getResource("/view/especialidade/ListarMedicosAssociados.fxml"));
            Stage stage;
            stage = new Stage();
            stage.setScene(new Scene(LMALoader.load()));
            ListarMedicosAssociadosController controller = LMALoader.getController();
            controller.setEspecialidadeSelecionada(especialidade);
            controller.carregarDados();
            stage.show();
            return;
        }
        mostrarMsg("Selecione uma especialidade", Alert.AlertType.ERROR);
    }

    @FXML
    public void associarMedico() throws IOException {
        Especialidade especialidade;
        especialidade = tableViewEspecialidades.getSelectionModel().getSelectedItem();
        if (especialidade != null) {
            FXMLLoader TAMLoader = new FXMLLoader(getClass().getResource("/view/especialidade/TelaAssociarMedico.fxml"));
            Stage stage;
            stage = new Stage();
            stage.setScene(new Scene(TAMLoader.load()));
            TelaAssociarMedicoController controller = TAMLoader.getController();
            controller.setNomeEspecialidadeSelecionada(especialidade.getNome());
            stage.show();
            return;
        }
        mostrarMsg("Você precisa selecionar uma especialidade", Alert.AlertType.ERROR);
    }

    private void carregarTableViewEspecialidades() {
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        List<Especialidade> listEspecialidades;
        listEspecialidades = Gerenciador.getEspecialidades().values().stream().collect(Collectors.toList());
        observableEspecialidades = FXCollections.observableArrayList(listEspecialidades);
        tableViewEspecialidades.setItems(observableEspecialidades);

    }

    private boolean carregarTelaEditarEspecialidade(Especialidade antigaEsp, Especialidade cloneAntigaEsp) throws IOException {
        FXMLLoader TEELoader;
        TEELoader = new FXMLLoader(getClass().getResource("/view/especialidade/TelaEditarEspecialidade.fxml"));

        Stage stage;
        stage = new Stage();
        stage.setScene(new Scene(TEELoader.load()));

        TelaEditarEspecialidadeController controllerTEE = TEELoader.getController();
        controllerTEE.setAntigaEspecialidade(antigaEsp);
        controllerTEE.setCloneAntigaEspecialidade(cloneAntigaEsp);
        controllerTEE.carregarDados();

        stage.showAndWait();
        return controllerTEE.isBotaoConfirmarClicked();
    }

    private void mostrarMsg(String texto, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setContentText(texto);
        alerta.show();
    }
}
