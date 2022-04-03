package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Carlos Valadao
 * Classe que contem as funcoes que gerenciam as
 * funcionalidades do sistema
 */
public class Gerenciador {

    // Guarda os medicos do sistema
    private static final HashMap<Long, Medico> medicos = new HashMap<>();
    // Guarda os pacientes do sistema
    private static final HashMap<Long, Paciente> pacientes = new HashMap<>();
    // Guarda todas as especialidades do sistema
    private static final HashMap<String, Especialidade> especialidades = new HashMap<>();
    // Guarda todas as subespecialidades do sistema
    private static final HashMap<String, Especialidade> subespecialidades = new HashMap<>();
    // Guarda a recepcionista do sistema
    private static final Recepcionista recepcionista = new Recepcionista("Recepcionista", 123, "321");
    

    /**
     * Autentica uma recepcionista no sistema
     *
     * @param login a ser verificado
     * @param senha a ser verificadq
     * @return Um inteiro entre 0 e 2, inclusos
     * 0 - caso as informacoes estejam corretas
     * 1 - caso haja uma informacao incorreta 
     * 2 - caso nao haja nenhuma informacao correta
     */
    public static int autenticarRecepcionista(long login, String senha) {
        if (recepcionista.getCpf() == login && recepcionista.getSenha().equals(senha)) {
            return 0;
        } else if (!recepcionista.getSenha().equals(senha) ^ login != recepcionista.getCpf()) {
            return 1;
        }
        return 2;
    }

    /**
     * Autentica um paciente no sistema
     *
     * @param login a ser verificado
     * @param senha a ser verificada
     * @return Um inteiro entre 0 e 2, inclusos. 0 - caso as informacoes
     * estejam corretas 1 - caso haja uma informacao incorreta 2 - caso nao haja
     * nenhuma informacao correta
     */
    public static int autenticarPaciente(long login, String senha) {
        Paciente paciente = buscarPaciente(login);
        if (paciente != null) {
            if (paciente.getNome().equals(senha) && paciente.getCpf() == login) {
                return 0;
            }
            return 1;
        }
        return 2;
    }
    
     /**
     * Autentica um Medico no sistema
     *
     * @param login a ser verificado
     * @param senha a ser verificada
     * @return Um inteiro entre 0 e 2, inclusos
     * 0 - caso as informacoes estejam corretas
     * 1 - caso haja uma informacao incorreta 
     * 2 - caso nao haja nenhuma informacao correta
     */
    public static int autenticarMedico(long login, String senha) {
        Medico medico = buscarMedico(login);
        if (medico != null) {
            if (medico.getCpf() == login && medico.getSenha().equals(senha)) {
                return 0;
            }
            return 1;
        }
        return 2;
    }
    
    
    /**
     * Identifica o tipo de um usuario
     *
     * @param cpf do usuario a ser identificado
     * @return um inteiro
     * @see TipoUsuario
     * 2 - Identifica um medico
     * 3 - Identifica uma recepcionista
     */
    public static int identificarTipoUsuario(long cpf) {
        Medico medico = buscarMedico(cpf);
        if (medico != null) {
            return TipoUsuario.MEDICO;
        }
        return TipoUsuario.RECEPCIONISTA;
    }

    
    /**
     * Busca por um paciente de acordo com o seu cpf
     *
     * @param cpf do paciente a ser buscado
     * @return Paciente caso ele exista e null caso contrario
     */
    public static Paciente buscarPaciente(long cpf) {
        return getPacientes().get(cpf);
    }

    /**
     * Busca por um medico de acordo com o seu cpf
     *
     * @param cpf do medico a ser buscado
     * @return Medico caso ele exista e null caso contrario
     */
    public static Medico buscarMedico(long cpf) {
        return getMedicos().get(cpf);
    }

    /**
     * Busca por uma especialidade de acordo com o seu nome
     *
     * @param nome da especialidade a ser buscada
     * @return Especialidade caso ele exista e null caso contrario
     */
    public static Especialidade buscarEspecialidade(String nome) {
        return getEspecialidades().get(nome);
    }

    /**
     * Busca por uma subespecialidade de acordo com o seu nome
     *
     * @param nome da subespecialidade a ser buscada
     * @param dif inteiro qualquer para diferenciar a assinatura desse método
     * com o outro
     * @return Especialidade caso ele exista e null caso contrario
     */
    public static Especialidade buscarSubespecialidade(String nome, int dif) {
        return getSubespecialidades().get(nome);
    }
    
    /**
     * Verifica a existencia de um medico em uma especialidade
     *
     * @param nomeEspecialidade onde sera realizada a busca pelo medico
     * @param cpfMedico a ser buscado
     * @return true caso o medico esteja associado a especialidade false caso
     * contrario
     */
    private static boolean hasMedicoInEspecialidade(long cpfMedico, String nomeEspecialidade) {
        Especialidade especialidade = buscarEspecialidade(nomeEspecialidade);
        return especialidade.getAssociados().containsKey(cpfMedico);
    }

        /**
     * Verifica a existencia de um medico em uma subespecialidade
     *
     * @param nomeSubespecialidade onde sera realizada a busca pelo medico
     * @param cpfMedico a ser buscado
     * @return true caso o medico esteja associado a subespecialidade false caso
     * contrario
     */
    private static boolean hasMedicoInSubespecialidade(long cpfMedico, String nomeSubespecialidade) {
        Especialidade especialidade = buscarSubespecialidade(nomeSubespecialidade, 0);
        return especialidade.getAssociados().containsKey(cpfMedico);
    }
    
    
    
    /**
     * Adiciona um medico a lista de medicos
     *
     * @param medico a ser adicionado
     * @return false caso o cpf do novo medico ja exista
     * no sistema e true caso contrario
     */
    public static boolean adicionarMedico(Medico medico) {
        Medico medico1 = buscarMedico(medico.getCpf());
        if (medico1 == null) {
            getMedicos().put(medico.getCpf(), medico);
            return true;
        }
        return false;
    }

    /**
     * Adiciona um paciente a lista de pacientes
     *
     * @param paciente a ser adicionado
     * @return false caso o cpf do novo paciente ja exista
     * no sistema e true caso contrario
     */
    public static boolean adicionarPaciente(Paciente paciente) {
        Paciente paciente1;
        paciente1 = buscarPaciente(paciente.getCpf());
        if (paciente1 == null) {
            getPacientes().put(paciente.getCpf(), paciente);
            return true;
        }
        // Caso o cpf do novo paciente ja exista no sistema
        return false;
    }

    /**
     * Adiciona uma especialidade a lista de especialidades
     *
     * @param nome da especialidade a ser adicionada
     * @return true caso o nome da especialidade a ser adicionada nao exista na
     * lista de especialidades e false caso contrario
     */
    public static boolean adicionarEspecialidade(String nome) {
        Especialidade especialidade = buscarEspecialidade(nome);
        if (especialidade != null) {
            return false;
        } else {
            getEspecialidades().put(nome, new Especialidade(nome));
            return true;
        }
    }
    /**
     * Adiciona uma subespecialidade a lista de especialidades
     *
     * @param nome da subespecialidade a ser adicionada
     * @return true caso o nome da subespecialidade a ser adicionada nao exista na
     * lista de subespecialidades e false caso contrario
     */
    public static boolean adicionarSubespecialidade(String nome) {
        Especialidade especialidade = buscarSubespecialidade(nome, 0);
        if (especialidade != null) {
            return false;
        } else {
            getSubespecialidades().put(nome, new Especialidade(nome));
            return true;
        }
    }
    /**
     * Remove um medico da lista de medicos do sistema
     *
     * @param medico a ser removido
     * @return true caso o medico nao possua nenhuma consulta efetuada
     * false caso contrario
     */
    
    // incluir a parte da subespecialidade
    public static boolean removerMedico(Medico medico) {
        if(medico.getConsultasRealizadas().isEmpty()) {
            getMedicos().remove(medico.getCpf());
            if(medico.getEspecialidade() != null)
                buscarEspecialidade(medico.getEspecialidade()).getAssociados().remove(medico.getCpf());
            if(medico.getSubespecialidade() != null)
                buscarSubespecialidade(medico.getEspecialidade(), 0).getAssociados().remove(medico.getCpf());
            return true;
        }
        return false;
    }

    /**
     * Remove um paciente do sistema
     *
     * @param paciente a ser removido
     * @return true caso o paciente nao possua consultas realizadas
     * e false caso contrario
     */
    public static boolean removerPaciente(Paciente paciente) {
        if(paciente.getConsultasRealizadas().isEmpty()){
            getPacientes().remove(paciente.getCpf());
            return true;
        }
        return false;
    }

    /**
     * Remove uma especialidade do sistema
     *
     * @param nome da especialidade a ser removida
     * @return true se nao ha nenhum medico associado a especialidade
     * fonercida, e false caso contrario
     */
    public static boolean removerEspecialidade(String nome) {
        Especialidade especialidade = buscarEspecialidade(nome);
        if (especialidade != null && especialidade.getAssociados().isEmpty()) {
            getEspecialidades().remove(nome);
            return true;
        }
        return false;
    }

    /**
     * Remove uma subespecialidade do sistema
     *
     * @param nome da especialidade a ser removida
     * @return true se nao ha nenhum medico associado a especialidade
     * fonercida, e false caso contrario
     */
    public static boolean removerSubespecialidade(String nome) {
        Especialidade especialidade = buscarSubespecialidade(nome, 0);
        if (especialidade != null && especialidade.getAssociados().isEmpty()) {
            getSubespecialidades().remove(nome);
            return true;
        }
        return false;
    }
    
    /**
     * Atualiza um medico do sistema
     *
     * @param antigo medico a ser substituido
     * @param novo medico (substituto)
     * @return true caso o cpf do novo medico nao exista no sistema e false caso
     * contrario
     */
    public static boolean atualizarMedico(Medico antigo, Medico novo) {
        if (antigo.getCpf() == novo.getCpf()) {
            getMedicos().replace(novo.getCpf(), antigo, novo);
            return true;
        }
        Medico medico = buscarMedico(novo.getCpf());
        if (medico == null) {
            getMedicos().remove(antigo.getCpf());
            getMedicos().put(novo.getCpf(), novo);
            return true;
        }
        // Caso o cpf do novo paciente ja exista no sistema
        return false;
    }

    /**
     * Atualiza um paciente do sistema
     *
     * @param antigo paciente a ser substituido
     * @param novo paciente (substituto)
     * @return true caso o cpf do novo paciente nao exista no sistema e false
     * caso o novo cpf do paciente ja exista no sistema
     */
    public static boolean atualizarPaciente(Paciente antigo, Paciente novo) {
        if (antigo.getCpf() == novo.getCpf()) {
            getPacientes().replace(novo.getCpf(), antigo, novo);
            return true;
        }
        Paciente paciente = buscarPaciente(novo.getCpf());
        if (paciente == null) {
            getPacientes().remove(antigo.getCpf());
            getPacientes().put(novo.getCpf(), novo);
            return true;
        }
        // Caso o cpf do novo paciente ja exista no sistema
        return false;
    }

    /**
     * Atualiza uma especialidade do sistema
     *
     * @param antiga especialidade a ser substituida
     * @param nova especialidade (substituta)
     * @return true caso o nome da nova especialidade nao exista no sistema e
     * false caso contrario
     */
    public static boolean atualizarEspecialidade(Especialidade antiga, Especialidade nova) {
        Especialidade especialidade = buscarEspecialidade(nova.getNome());
        if (especialidade == null) {
            getEspecialidades().remove(antiga.getNome());
            getEspecialidades().put(nova.getNome(), nova);
            return true;
        }
        // Caso o nome da nova especialidade ja exista no sistema
        return false;
    }

        /**
     * Atualiza uma subespecialidade do sistema
     *
     * @param antiga subespecialidade a ser substituida
     * @param nova subespecialidade (substituta)
     * @return true caso o nome da nova subespecialidade nao exista no sistema e
     * false caso contrario
     */
    public static boolean atualizarSubspecialidade(Especialidade antiga, Especialidade nova) {
        Especialidade especialidade = buscarSubespecialidade(nova.getNome(), 0);
        if (especialidade == null) {
            getSubespecialidades().remove(antiga.getNome());
            getSubespecialidades().put(nova.getNome(), nova);
            return true;
        }
        // Caso o nome da nova subespecialidade ja exista no sistema
        return false;
    }
    
    
    /**
     * Atualiza o prontuario de um paciente
     *
     * @param paciente dono do prontuario
     * @param novoP novo prontuario
     */
    public static void atualizarProntuarioPaciente(Paciente paciente, Prontuario novoP) {
        paciente.setProntuario(novoP);
    }

    
    /**
     * Busca o prontuario de um determinado paciente
     *
     * @param cpfPaciente dono do prontuario
     * @return Prontuario do paciente (Sempre)
     */
    public static Prontuario buscarProntuario(long cpfPaciente) {
        Paciente paciente;
        paciente = buscarPaciente(cpfPaciente);
        return paciente.getProntuario();
    }
    
    /**
     * Coleta as horas indisponiveis de um determinado dia
     *
     * @param dia onde as horas indisponiveis serao coletadas
     * @return list contendo as horas indisponiveis para marcacao de
     * consultas
     */
    private static List<Integer> getHorasIndisponiveis(int dia) {
        List<Paciente> pacientesLista = getPacientes().values().stream().collect(Collectors.toList());
        Iterator<Paciente> it = pacientesLista.iterator();
        ArrayList<Integer> horasIndisp = new ArrayList<>();
        int contador;
        while (it.hasNext()) {
            contador = 0;
            Paciente paciente = it.next();
            ArrayList<Consulta> consultasP = paciente.getConsultasMarcadas();
            Iterator<Consulta> itC = consultasP.iterator();
            while (itC.hasNext()) {
                Consulta c = itC.next();
                if (c.getData().getDayOfMonth() == dia) {
                    contador++;
                    if(contador == 3)
                        horasIndisp.add(c.getHora().getHour());
                }
            }
        }
        horasIndisp.add(9);
        return horasIndisp;
    }

    /**
     * Coleta os minutos indisponiveis de uma determinada hora
     *
     * @param dia ao qual sera coletado, os minutos
     * @param hora ao qual os minutos serao coletados
     * @return list que contem os minutos indisponiveis para marcacao de
     * consultas
     */
    private static List<Integer> getMinIndisponiveis(int dia, int hora) {
        List<Paciente> pacientes1 = getPacientes().values().stream().collect(Collectors.toList());
        Iterator<Paciente> it = pacientes1.iterator();
        ArrayList<Integer> minIndisp = new ArrayList<>();
        while (it.hasNext()) {
            Paciente paciente = it.next();
            ArrayList<Consulta> consultasP = paciente.getConsultasMarcadas();
            Iterator<Consulta> itC = consultasP.iterator();
            while (itC.hasNext()) {
                Consulta consulta = itC.next();
                int diaConsulta = consulta.getData().getDayOfMonth();
                int horaConsulta = consulta.getHora().getHour();
                if (diaConsulta == dia && horaConsulta == hora) {
                    minIndisp.add(consulta.getHora().getMinute());
                }
            }
        }
        return minIndisp;
    }

    
    /**
     * Devolve as horas, da agenda, disponiveis de um determinado dia.
     *
     * @param dia onde as horas serao verificadas
     * @return list contendo as horas disponiveis
     */
    public static List<Integer> getHorasDisponiveis(int dia) {
        List<Integer> horasDisponiveis = new ArrayList<>();
        preencherVetorComHoras(horasDisponiveis);
        List<Integer> horasIndisponiveis = getHorasIndisponiveis(dia);
        int sizeHorasIndisponiveis = horasIndisponiveis.size();
        for (int i = 0; i < sizeHorasIndisponiveis; i++) {
            int horaIndisponivel = horasIndisponiveis.get(i);
            if (horasDisponiveis.contains(horaIndisponivel)) {
                horasDisponiveis.remove(Integer.valueOf(horaIndisponivel));
            }
        }
        return horasDisponiveis;
    }

    /**
     * Devolve os minutos, da agenda, disponiveis de um determinado dia e hora.
     *
     * @param dia onde as horas serao verificadas
     * @param hora onde as horas serao verificadas
     * @return list contendo as horas disponiveis
     */
    public static List<Integer> getMinDisponiveis(int dia, int hora) {
        List<Integer> minDisponiveis = new ArrayList<>();
        preencherVetorComMinutos(minDisponiveis);
        List<Integer> minIndisponiveis = getMinIndisponiveis(dia, hora);
        int sizeMinIndisponiveis = minIndisponiveis.size();
        for (int i = 0; i < sizeMinIndisponiveis; i++) {
            int minIndisponivel = minIndisponiveis.get(i);
            if (minDisponiveis.contains(minIndisponivel)) {
                minDisponiveis.remove(Integer.valueOf(minIndisponivel));
            }
        }
        return minDisponiveis;
    }
        
    
    /**
     * [USO INTERNO]
     * Preenche um List<Integer> com valores inteiros que
     * correspondem aos horarios de funcionamento da clinica.
     * @param list a ser preenchida
     */
    private static void preencherVetorComHoras(List<Integer> list) {
        list.add(8);
        list.add(9);
        list.add(10);
        list.add(11);
        list.add(14);
        list.add(15);
        list.add(16);
        list.add(17);
    }
    
        /**
     * [USO INTERNO]
     * Preenche um List<Integer> com valores inteiros que
     * correspondem os minutos (o tempo de duracao de uma consulta)
     * [20min]
     * @param list a ser preenchida
     */
    private static void preencherVetorComMinutos(List<Integer> list) {
        list.add(0);
        list.add(20);
        list.add(40);
    }
    
    
    /**
     * Imprime a receita de um determinado paciente em um arquivo de texto
     * O arquivo tem o seguinte padrao de nome:
     * receita_NOMEPACIENTE_NOMEMEDICO_ddMMyyyyhhmmss
     * @param nomePaciente "dono" da receita
     * @param nomeMedico prescritor da receita
     * @param prescricao do medico
     * @throws java.io.IOException
     */
    public static void imprimirReceita(String nomeMedico, String nomePaciente, String prescricao) throws IOException {
        LocalDateTime ldt = LocalDateTime.now();
        String Fldt = ldt.format(DateTimeFormatter.ofPattern("ddMMyyyyhhmmss"));
        String nomeArquivo = "receita_" + nomePaciente + "_" + nomeMedico + "_" + Fldt + ".txt";
        File fp = new File(".", nomeArquivo);
        fp.createNewFile();
        FileWriter fileWriter = new FileWriter(fp, false);
        PrintWriter printWriter;
        printWriter = new PrintWriter(fileWriter);
        printWriter.println("Nome Paciente:");
        printWriter.println(nomePaciente);
        printWriter.println("\nPrescrição:");
        printWriter.println(prescricao);
        printWriter.flush();
        printWriter.close();
    }
    
    
    /**
     * Marca uma consulta para um determinado paciente
     *
     * @param medico responsavel pela consulta
     * @param paciente ao qual a consulta sera marcada
     * @param consulta a ser marcada
     */
    public static void marcarConsulta(Medico medico, Paciente paciente, Consulta consulta) {
        paciente.getConsultasMarcadas().add(consulta);
        medico.getConsultasMarcadas().add(consulta);
    }

    /**
     * Conclui uma consulta de um determinado medico
     *
     * @param medico responsavel pela consulta
     * @param consulta que foi realizada
     */
    public static void concluirConsulta(Medico medico, Consulta consulta) {
        medico.getConsultasMarcadas().remove(consulta);
        medico.getConsultasRealizadas().add(consulta);
        // Paciente que contem a consulte
        Paciente paciente;
        paciente = buscarPaciente(consulta.getCpfPaciente());
        
        paciente.getConsultasMarcadas().remove(consulta);
        paciente.getConsultasRealizadas().add(consulta);
    }

    /**
     * Associa um determinado medico a uma determinada especialidade
     *
     * @param medico a ser associado
     * @param nomeEspecialidade ao qual o medico sera associado
     * @return true caso o medico nao esteja associado a uma especialidade false
     * caso contrario
     */
    public static boolean associarMedicoEspecialidade(Medico medico, String nomeEspecialidade) {
        boolean hasMedicoEspecialidade = hasMedicoInEspecialidade(medico.getCpf(), nomeEspecialidade);
        if (!hasMedicoEspecialidade) {
            Especialidade espec = buscarEspecialidade(nomeEspecialidade);
            espec.getAssociados().put(medico.getCpf(), medico);
            medico.setEspecialidade(nomeEspecialidade);
            return true;
        }
        // Caso o medico ja esteja associado a uma especialidade
        return false;
    }

        /**
     * Associa um determinado medico a uma determinada especialidade
     *
     * @param medico a ser associado
     * @param nomeSubespecialidade a oqua o medico sera associado
     * @return true caso o medico nao esteja associado a uma especialidade false
     * caso contrario
     */
    public static boolean associarMedicoSubespecialidade(Medico medico, String nomeSubespecialidade) {
        boolean hasMedicoEspecialidade = hasMedicoInSubespecialidade(medico.getCpf(), nomeSubespecialidade);
        if (!hasMedicoEspecialidade) {
            Especialidade subEspec = buscarSubespecialidade(nomeSubespecialidade, 0);
            subEspec.getAssociados().put(medico.getCpf(), medico);
            medico.setSubespecialidade(nomeSubespecialidade);
            return true;
        }
        // Caso o medico ja esteja associado a uma especialidade
        return false;
        
    }
    
    /**
     * Desassocia um medico de uma especialidade
     *
     * @param medico ao qual a especialidade sera desassociada
     */
    public static void desassociarMedicoEspecialidade(Medico medico) {
        Especialidade especialidade;
        especialidade = buscarEspecialidade(medico.getEspecialidade());
        especialidade.getAssociados().remove(medico.getCpf());
        medico.setEspecialidade(null);
    }

        /**
     * Desassocia um medico de uma especialidade
     *
     * @param medico ao qual a especialidade sera desassociada
     */
    public static void desassociarMedicoSubespecialidade(Medico medico) {
        Especialidade especialidade;
        especialidade = buscarSubespecialidade(medico.getSubespecialidade(), 0);
        especialidade.getAssociados().remove(medico.getCpf());
        medico.setEspecialidade(null);
    }
    
    
    /**
     * Devolve uma lista contendo todas as consultas do paciente da clinica
     *
     * @param paciente de onde a lista sera gerada
     * @return lista contendo as consultas realizadas do paciente
     */
    public static ArrayList<Consulta> getHistoricoConsultaPaciente(Paciente paciente) {
        return paciente.getConsultasRealizadas();
    }

    public static HashMap<Long, Medico> getMedicos() {
        return medicos;
    }

    public static HashMap<Long, Paciente> getPacientes() {
        return pacientes;
    }

    public static Recepcionista getRecepcionista() {
        return recepcionista;
    }

    public static HashMap<String, Especialidade> getEspecialidades() {
        return especialidades;
    }

    public static HashMap<String, Especialidade> getSubespecialidades() {
        return subespecialidades;
    }
   
}
