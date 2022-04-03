package controller.medico;

import java.io.IOException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Consulta;
import model.Gerenciador;
import model.Medico;
import model.Paciente;

public class TelaMedicoController {

    @FXML
    private Button btEncerrarAtendimento;

    @FXML
    private Button btEncerrarConsulta;

    @FXML
    private Button btIniciarConsulta;

    @FXML
    private TableColumn<Consulta, String> colunaHorario;

    @FXML
    private TableColumn<Consulta, Consulta> colunaPaciente;

    @FXML
    private TableView<Consulta> tableViewConsultas;

    private Medico medicoLogado;

    @FXML
    public void iniciarConsulta() throws IOException {
        Consulta consulta;
        consulta = tableViewConsultas.getSelectionModel().getSelectedItem();
        if (consulta != null) {
            Paciente pacienteConsulta;
            pacienteConsulta = Gerenciador.buscarPaciente(consulta.getCpfPaciente());

            FXMLLoader TCALoader = new FXMLLoader(getClass().getResource("/view/medico/TelaConsultaAndamento.fxml"));

            Stage stage;
            stage = new Stage();
            stage.setScene(new Scene(TCALoader.load()));

            TelaConsultaAndamentoController controller = TCALoader.getController();
            controller.setConsultaSelecionada(consulta);
            controller.setMedicoLogado(medicoLogado);
            controller.setPacienteConsulta(pacienteConsulta);
            controller.setController(this);

            stage.show();
            return;
        }
        mostrarMsg("Nenhuma consulta foi selecionada", Alert.AlertType.ERROR);
    }

    @FXML
    public void encerrarAtendimento() {
        fecharJanela();
    }

    public void carregarDados() {
        carregarTableViewConsultas();
    }

    private void carregarTableViewConsultas() {
        colunaPaciente.setCellValueFactory(new PropertyValueFactory<>("nomePaciente"));
        colunaHorario.setCellValueFactory(new PropertyValueFactory<>("horaString"));
        List<Consulta> consultasMedico;
        consultasMedico = medicoLogado.getConsultasMarcadas();
        ObservableList<Consulta> observableConsultas;
        observableConsultas = FXCollections.observableArrayList(consultasMedico);
        tableViewConsultas.setItems(observableConsultas);
    }

    private void fecharJanela() {
        Stage stage;
        stage = (Stage) btEncerrarAtendimento.getScene().getWindow();
        stage.close();
    }

    public void setMedicoLogado(Medico medicoLogado) {
        this.medicoLogado = medicoLogado;
    }

    private void mostrarMsg(String texto, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setContentText(texto);
        alerta.show();
    }
}
