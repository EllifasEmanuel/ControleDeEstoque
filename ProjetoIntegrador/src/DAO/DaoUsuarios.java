package DAO;

import conexoes.ConexaoMySql;
import java.util.ArrayList;
import model.ModelUsuarios;

/**
 *
 * @author ellif
 */
public class DaoUsuarios extends ConexaoMySql {

    //Cadastrar um usuario no banco de dados
    public int salvarUsuariosDAO(ModelUsuarios pModelUsuarios) {
        try {
            this.conectar();
            return this.insertSQL("INSERT INTO tabela_usuario("
                    + "id_usuario,"
                    + "nomeUsuario,"
                    + "loginUsuario,"
                    + "senhaUsuario"
                    + ") VALUES ("
                    + "'" + pModelUsuarios.getIdUsuario() + "',"
                    + "'" + pModelUsuarios.getNomeUsuario() + "',"
                    + "'" + pModelUsuarios.getLoginUsuario() + "',"
                    + "'" + pModelUsuarios.getSenhaUsuario() + "'"
                    + ");"
            );
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            this.fecharConexao();
        }
    }

    //Excluir um usuario do banco de dados
    public boolean excluirUsuarioDAO(int pIdUsuario) {
        try {
            this.conectar();
            return this.executarUpdateDeleteSQL(
                    "DELETE FROM tabela_usuario WHERE id_usuario = '" + pIdUsuario + "'"
            );
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            this.fecharConexao();
        }
    }

    //Alterar dados do usuario
    public boolean alterarUsuariosDAO(ModelUsuarios pModelUsuarios) {
        try {
            this.conectar();
            return this.executarUpdateDeleteSQL(
                    "UPDATE tabela_usuario"
                    + "nomeUsuario = '" + pModelUsuarios.getNomeUsuario() + "',"
                    + "loginUsuario = '" + pModelUsuarios.getLoginUsuario() + "',"
                    + "senhaUsuario = '" + pModelUsuarios.getSenhaUsuario() + "',"
                    + "WHERE id_usuario = '" + pModelUsuarios.getIdUsuario() + "'"
            );
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            this.fecharConexao();
        }
    }

    //Retorna um usuario pelo codigo
    public ModelUsuarios retornarUsuariosDAO(int pIdUsuarios) {
        ModelUsuarios modelUsuarios = new ModelUsuarios();
        try {
            this.conectar();
            this.executarSQL("SELECT "
                    + "id_usuario, "
                    + "nomeUsuario, "
                    + "loginUsuario, "
                    + "senhaUsuario"
                    + "FROM tabela_usuario WHERE id_usuario = '" + pIdUsuarios + "';");
            while (this.getResultSet().next()) {
                modelUsuarios.setIdUsuario(this.getResultSet().getInt(1));
                modelUsuarios.setNomeUsuario(this.getResultSet().getString(2));
                modelUsuarios.setLoginUsuario(this.getResultSet().getString(3));
                modelUsuarios.setSenhaUsuario(this.getResultSet().getString(4));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.fecharConexao();
        }

        return modelUsuarios;
    }

    //Retorna uma lista completa de usuarios
    public ArrayList<ModelUsuarios> retornarListaUsuariosDAO() {
        ArrayList<ModelUsuarios> listaModelUsuarios = new ArrayList<>();
        ModelUsuarios modelUsuarios = new ModelUsuarios();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "id_usuario, "
                    + "nomeUsuario, "
                    + "loginUsuario, "
                    + "senhaUsuario"
                    + "FROM tabela_usuario;"
            );
            while (this.getResultSet().next()) {
                modelUsuarios = new ModelUsuarios();
                modelUsuarios.setIdUsuario(this.getResultSet().getInt(1));
                modelUsuarios.setNomeUsuario(this.getResultSet().getString(2));
                modelUsuarios.setLoginUsuario(this.getResultSet().getString(3));
                modelUsuarios.setSenhaUsuario(this.getResultSet().getString(4));

                listaModelUsuarios.add(modelUsuarios);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.fecharConexao();
        }
        return listaModelUsuarios;
    }

    public boolean getValidarUsuarioDao(ModelUsuarios pModelUsuario) {
        try {
            this.conectar();
            this.executarSQL("SELECT "
                    + "id_usuario, "
                    + "nomeUsuario, "
                    + "loginUsuario, "
                    + "senhaUsuario"
                    + "FROM tabela_usuario WHERE loginUsuario = '" + pModelUsuario.getLoginUsuario() + "'AND senhaUsuario = '" + pModelUsuario + "';");
            if (getResultSet().next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            this.fecharConexao();
        }

    }
}
