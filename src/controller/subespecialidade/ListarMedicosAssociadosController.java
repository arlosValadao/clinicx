package controller.subespecialidade;

import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Especialidade;
import model.Gerenciador;
import model.Medico;

/**
 *
 * @author Carlos Valadao
 */
public class ListarMedicosAssociadosController {

    @FXML
    private Button btDesassociar;

    @FXML
    private TableColumn<Medico, Long> colunaCPF;

    @FXML
    private TableColumn<Medico, String> colunaNome;

    @FXML
    private TableColumn<Medico, String> colunaSubespecialidade;

    @FXML
    private TableView<Medico> tableViewMedicosAssociados;

    private ObservableList<Medico> observableMedicos;
    private Especialidade especialidadeSelecionada;

    @FXML
    public void desassociarMedico() {
        Medico medico;
        medico = tableViewMedicosAssociados.getSelectionModel().getSelectedItem();
        if (medico != null) {
            Gerenciador.desassociarMedicoSubespecialidade(medico);
            carregarTableViewMedicosAssociados();
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setContentText("Medico desassociado com sucesso");
            alert1.show();
            return;
        }
        Alert alert2 = new Alert(Alert.AlertType.ERROR);
        alert2.setContentText("Você precisa selecionar um medico");
        alert2.show();
    }

    public void carregarDados() {
        carregarTableViewMedicosAssociados();
    }

    private void carregarTableViewMedicosAssociados() {
        List<Medico> medicosAssociados;
        medicosAssociados = especialidadeSelecionada.getAssociados().values().stream().collect(Collectors.toList());
        colunaCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaSubespecialidade.setCellValueFactory(new PropertyValueFactory<>("subespecialidade"));
        observableMedicos = FXCollections.observableArrayList(medicosAssociados);
        tableViewMedicosAssociados.setItems(observableMedicos);
    }

    public void setEspecialidadeSelecionada(Especialidade especialidadeSelecionada) {
        this.especialidadeSelecionada = especialidadeSelecionada;
    }

}
