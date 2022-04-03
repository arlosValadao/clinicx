package controller.paciente;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Consulta;
import model.Gerenciador;
import model.Paciente;

public class TelaHistoricoConsultaController {

    @FXML
    private TableColumn<Consulta, String> colunaData;

    @FXML
    private TableColumn<Consulta, String> colunaEspecialidade;

    @FXML
    private TableColumn<Consulta, String> colunaHora;

    @FXML
    private TableColumn<Consulta, String> colunaNomeMedico;

    @FXML
    private TableView<Consulta> tableViewConsultas;

    private Paciente paciente;

    public void mostrarTableViewConsultas() {
        carregarTableViewConsultas();
    }

    private void carregarTableViewConsultas() {
        colunaNomeMedico.setCellValueFactory(new PropertyValueFactory<>("medicoResponsavel"));
        colunaEspecialidade.setCellValueFactory(new PropertyValueFactory<>("especialidade"));
        colunaData.setCellValueFactory(new PropertyValueFactory<>("dataString"));
        colunaHora.setCellValueFactory(new PropertyValueFactory<>("horaString"));
        List<Consulta> listConsultas;
        listConsultas = Gerenciador.getHistoricoConsultaPaciente(paciente);
        ObservableList<Consulta> observableConsultas;
        observableConsultas = FXCollections.observableArrayList(listConsultas);
        tableViewConsultas.setItems(observableConsultas);
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

}
