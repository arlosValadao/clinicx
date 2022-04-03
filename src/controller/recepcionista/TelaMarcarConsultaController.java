package controller.recepcionista;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import model.Consulta;
import model.Especialidade;
import model.Gerenciador;
import model.Medico;
import model.Paciente;

public class TelaMarcarConsultaController implements Initializable {

    @FXML
    private Button btCancelar;

    @FXML
    private Button btMarcarConsulta;

    @FXML
    private ComboBox<Especialidade> comboBoxEspecialidade;

    @FXML
    private ComboBox<Medico> comboBoxMedico;

    @FXML
    private ComboBox<Integer> comboBoxHora;

    @FXML
    private ComboBox<Integer> comboBoxMin;

    @FXML
    private ComboBox<Paciente> comboBoxPaciente;
    @FXML
    private DatePicker datePicker;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarComboBoxPacientes();
        carregarComboBoxMedicos();
        carregarComboBoxEspecialidades();
    }

    @FXML
    void carregarHorarioDisponivel(ActionEvent event) {
        LocalDate data = datePicker.getValue();
        int dia = data.getDayOfMonth();

        List<Integer> horasDisponiveis = Gerenciador.getHorasDisponiveis(dia);
        ObservableList<Integer> observableHoras;
        observableHoras = FXCollections.observableArrayList(horasDisponiveis);
        comboBoxHora.setItems(observableHoras);
    }

    @FXML
    public void carregarMinutos() {
        LocalDate data = datePicker.getValue();
        int dia = data.getDayOfMonth();
        int hora = comboBoxHora.getSelectionModel().getSelectedItem();

        List<Integer> minDisponiveis = Gerenciador.getMinDisponiveis(dia, hora);
        ObservableList<Integer> observableMin;
        observableMin = FXCollections.observableArrayList(minDisponiveis);
        comboBoxMin.setItems(observableMin);
    }

    @FXML
    public void carregarComboBoxPacientes() {
        List<Paciente> pacientes;
        pacientes = Gerenciador.getPacientes().values().stream().collect(Collectors.toList());
        ObservableList<Paciente> observablePacientes;
        observablePacientes = FXCollections.observableArrayList(pacientes);
        comboBoxPaciente.setItems(observablePacientes);
    }

    @FXML
    public void carregarComboBoxMedicos() {
        List<Medico> medicos;
        medicos = Gerenciador.getMedicos().values().stream().collect(Collectors.toList());

        ObservableList<Medico> observableMedicos;
        observableMedicos = FXCollections.observableArrayList(medicos);
        comboBoxMedico.setItems(observableMedicos);
    }

    @FXML
    public void carregarComboBoxEspecialidades() {
        List<Especialidade> subespecialidades;
        subespecialidades = Gerenciador.getSubespecialidades().values().stream().collect(Collectors.toList());

        List<Especialidade> especialidades;
        especialidades = Gerenciador.getEspecialidades().values().stream().collect(Collectors.toList());

        especialidades.addAll(subespecialidades);

        ObservableList<Especialidade> observableEspecialidades;
        observableEspecialidades = FXCollections.observableArrayList(especialidades);
        comboBoxEspecialidade.setItems(observableEspecialidades);
    }

    @FXML
    public void marcarConsulta() {
        Medico medico = comboBoxMedico.getSelectionModel().getSelectedItem();
        Especialidade especialidade = comboBoxEspecialidade.getSelectionModel().getSelectedItem();
        if (verificarEspecialidade(medico, especialidade.getNome())) {
            Paciente paciente = comboBoxPaciente.getSelectionModel().getSelectedItem();
            int horaConsulta = comboBoxHora.getSelectionModel().getSelectedItem();
            int minutoConsulta = comboBoxMin.getSelectionModel().getSelectedItem();
            LocalTime horarioConsulta = LocalTime.of(horaConsulta, minutoConsulta);
            LocalDate dataConsulta = datePicker.getValue();
            Consulta consulta;
            consulta = new Consulta(paciente.getNome(), paciente.getCpf(),
                    especialidade.getNome(), medico.getNome(), dataConsulta, horarioConsulta);

            Gerenciador.marcarConsulta(medico, paciente, consulta);
            mostrarMsg("Consulta marcada com sucesso", Alert.AlertType.INFORMATION);
            fecharJanela();
            return;
        }
        mostrarMsg("O medico" + medico.getNome() + "não tem essa especialidade:", Alert.AlertType.ERROR);
    }

    @FXML
    public void cancelar() {
        fecharJanela();
    }

    private boolean verificarEspecialidade(Medico medico, String nomeEspecialidade) {
        if (medico.getEspecialidade().equals(nomeEspecialidade)
                || medico.getSubespecialidade().equals(nomeEspecialidade)) {
            return true;
        }
        return false;
    }

    private void fecharJanela() {
        Stage stage;
        stage = (Stage) btCancelar.getScene().getWindow();
        stage.close();
    }

    private void mostrarMsg(String texto, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setContentText(texto);
        alerta.show();
    }
}
