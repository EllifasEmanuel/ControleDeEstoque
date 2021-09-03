package DAO;

import conexoes.ConexaoMySql;
import java.util.ArrayList;
import model.ModelClientes;

/**
 *
 * @author ellif
 */
public class DaoCliente extends ConexaoMySql {

    //Cadastrar um cliente no banco de dados
    public int salvarClienteDAO(ModelClientes pModelClientes) {
        try {
            this.conectar();
            return this.insertSQL("INSERT INTO tabela_cliente("
                    + "id_cliente,"
                    + "nomeCliente,"
                    + "enderecoCliente,"
                    + "bairroCliente"
                    + "numeroCliente"
                    + "cidadeCliente"
                    + "telefoneCliente"
                    + ") VALUES ("
                    + "'" + pModelClientes.getIdCliente()+ "',"
                    + "'" + pModelClientes.getNomeCliente() + "',"
                    + "'" + pModelClientes.getEnderecoCliente() + "',"
                    + "'" + pModelClientes.getBairroCliente() + "',"
                    + "'" + pModelClientes.getNumeroCliente() + "',"
                    + "'" + pModelClientes.getCidadeCliente() + "',"
                    + "'" + pModelClientes.getTelefoneCliente() + "'"
                    +");"
            );
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            this.fecharConexao();
        }
    }

    //Excluir um cliente do banco de dados
    public boolean excluirClienteDAO(int pIdClientes) {
        try {
            this.conectar();
            return this.executarUpdateDeleteSQL(
                    "DELETE FROM tabela_cliente WHERE id_cliente = '" + pIdClientes + "'"
            );
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            this.fecharConexao();
        }
    }

    //Alterar dados do cliente
    public boolean alterarClienteDAO(ModelClientes pModelClientes) {
        try {
            this.conectar();
            return this.executarUpdateDeleteSQL(
                    "UPDATE tabela_cliente"
                    + "nomeCliente = '" + pModelClientes.getNomeCliente() + "',"
                    + "enderecoCliente = '" + pModelClientes.getEnderecoCliente() + "',"
                    + "bairroCliente = '" + pModelClientes.getBairroCliente() + "',"
                    + "numeroCliente = '" + pModelClientes.getNumeroCliente() + "',"
                    + "cidadeCliente = '" + pModelClientes.getCidadeCliente() + "',"
                    + "telefoneCliente = '" + pModelClientes.getTelefoneCliente() + "'"
                    + "WHERE id_cliente = '" + pModelClientes.getIdCliente() + "'"
            );
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            this.fecharConexao();
        }
    }

    //Retorna um cliente pelo codigo
    public ModelClientes retornarClienteDAO(int pIdCliente) {
        ModelClientes modelClientes = new ModelClientes();
        try {
            this.conectar();
            this.executarSQL("SELECT "
                    + "id_cliente,"
                    + "nomeCliente,"
                    + "enderecoCliente,"
                    + "bairroCliente"
                    + "numeroCliente"
                    + "cidadeCliente"
                    + "telefoneCliente"
                    + "FROM tabela_cliente WHRE id_cliente = '" + pIdCliente + "';");
            while (this.getResultSet().next()) {
                modelClientes.setIdCliente(this.getResultSet().getInt(1));
                modelClientes.setNomeCliente(this.getResultSet().getString(2));
                modelClientes.setEnderecoCliente(this.getResultSet().getString(3));
                modelClientes.setBairroCliente(this.getResultSet().getString(4));
                modelClientes.setNumeroCliente(this.getResultSet().getString(5));
                modelClientes.setCidadeCliente(this.getResultSet().getString(6));
                modelClientes.setTelefoneCliente(this.getResultSet().getString(7));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.fecharConexao();
        }

        return modelClientes;
    }
    
    //Retorna uma lista completa de clientes
    public ArrayList<ModelClientes> retornarListaClientesDAO() {
        ArrayList<ModelClientes> listaModelClientes = new ArrayList<>();
        ModelClientes modelClientes = new ModelClientes();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "id_cliente,"
                    + "nomeCliente,"
                    + "enderecoCliente,"
                    + "bairroCliente"
                    + "numeroCliente"
                    + "cidadeCliente"
                    + "telefoneCliente"
                    + "FROM tabela_cliente;"
            );
            while(this.getResultSet().next()){
                modelClientes = new ModelClientes();
                modelClientes.setIdCliente(this.getResultSet().getInt(1));
                modelClientes.setNomeCliente(this.getResultSet().getString(2));
                modelClientes.setEnderecoCliente(this.getResultSet().getString(3));
                modelClientes.setBairroCliente(this.getResultSet().getString(4));
                modelClientes.setNumeroCliente(this.getResultSet().getString(5));
                modelClientes.setCidadeCliente(this.getResultSet().getString(6));
                modelClientes.setTelefoneCliente(this.getResultSet().getString(7));
                
                listaModelClientes.add(modelClientes);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{
            this.fecharConexao();
        }
        return listaModelClientes;
    }
}

