package model;

/**
 * @author Carlos Valadão
 */

/**
 Contem as constantes que representam
 os niveis de permissao de cada usuario no sistema.
 As permissoes sao usadas evitar o carregamento
 de uma tela indevida, na view
 * 1 - Paciente
 * 2 - Medico
 * 3 - Recepcionista
 */
public class TipoUsuario {
    public static final int PACIENTE = 1;
    public static final int MEDICO = 2;
    public static final int RECEPCIONISTA = 3;
}
