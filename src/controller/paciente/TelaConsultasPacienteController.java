package controller.paciente;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Consulta;
import model.Medico;
import model.Paciente;

/**
 * FXML Controller class
 *
 * @author Carlos Valadão
 */
public class TelaConsultasPacienteController {

    @FXML
    private TableColumn<Consulta, String> colunaData;

    @FXML
    private TableColumn<Consulta, String> colunaEspecialidade;

    @FXML
    private TableColumn<Consulta, String> colunaHora;

    @FXML
    private TableView<Consulta> tableViewConsultas;

    private Paciente pacienteSelecionado;

    public void mostrarTableViewConsultas() {
        carregarTableViewConsultas();
    }

    private void carregarTableViewConsultas() {
        colunaEspecialidade.setCellValueFactory(new PropertyValueFactory<>("especialidade"));
        colunaData.setCellValueFactory(new PropertyValueFactory<>("dataString"));
        colunaHora.setCellValueFactory(new PropertyValueFactory<>("horaString"));
        List<Consulta> listConsultas;
        listConsultas = pacienteSelecionado.getConsultasMarcadas();
        ObservableList<Consulta> observableConsultas;
        observableConsultas = FXCollections.observableArrayList(listConsultas);
        tableViewConsultas.setItems(observableConsultas);
    }

    public void setPacienteSelecionado(Paciente pacienteSelecionado) {
        this.pacienteSelecionado = pacienteSelecionado;
    }

}
