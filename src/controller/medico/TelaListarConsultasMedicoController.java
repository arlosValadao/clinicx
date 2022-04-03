package controller.medico;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Consulta;
import model.Medico;

/**
 * FXML Controller class
 *
 * @author Carlos Valadão
 */
public class TelaListarConsultasMedicoController {

    @FXML
    private TableColumn<Consulta, String> colunaData;

    @FXML
    private TableColumn<Consulta, String> colunaEspecialidade;

    @FXML
    private TableColumn<Consulta, String> colunaHora;

    @FXML
    private TableColumn<Consulta, String> colunaNomePaciente;

    @FXML
    private TableView<Consulta> tableViewConsultas;

    private Medico medicoSelecionado;

    public void mostrarTableViewConsultas() {
        carregarTableViewConsultas();
    }

    private void carregarTableViewConsultas() {
        colunaNomePaciente.setCellValueFactory(new PropertyValueFactory<>("nomePaciente"));
        colunaEspecialidade.setCellValueFactory(new PropertyValueFactory<>("especialidade"));
        colunaData.setCellValueFactory(new PropertyValueFactory<>("dataString"));
        colunaHora.setCellValueFactory(new PropertyValueFactory<>("horaString"));
        List<Consulta> listConsultas;
        listConsultas = medicoSelecionado.getConsultasMarcadas();
        ObservableList<Consulta> observableConsultas;
        observableConsultas = FXCollections.observableArrayList(listConsultas);
        tableViewConsultas.setItems(observableConsultas);
    }

    public void setMedicoSelecionado(Medico medicoSelecionado) {
        this.medicoSelecionado = medicoSelecionado;
    }

}
