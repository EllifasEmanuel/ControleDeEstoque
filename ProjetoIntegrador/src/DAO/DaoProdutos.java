/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import conexoes.ConexaoMySql;
import java.util.ArrayList;
import model.ModelProdutos;

/**
 *
 * @author ellif
 */
public class DaoProdutos extends ConexaoMySql {

    //Cadastrar um produto no banco de dados
    public int salvarProdutosDAO(ModelProdutos pModelProdutos) {
        try {
            this.conectar();
            return this.insertSQL("INSERT INTO tabela_produto("
                    + "id_produto,"
                    + "produtoNome,"
                    + "produtoValor,"
                    + "produtoEstoque"
                    + ") VALUES ("
                    + "'" + pModelProdutos.getIdProduto() + "',"
                    + "'" + pModelProdutos.getNomeProduto() + "',"
                    + "'" + pModelProdutos.getPrecoProduto() + "',"
                    + "'" + pModelProdutos.getEstoqueProduto() + "'"
                    +");"
            );
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            this.fecharConexao();
        }
    }

    //Excluir um produto do banco de dados
    public boolean excluirProdutoDAO(int pIdProduto) {
        try {
            this.conectar();
            return this.executarUpdateDeleteSQL(
                    "DELETE FROM tabela_produto WHERE id_produto = '" + pIdProduto + "'"
            );
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            this.fecharConexao();
        }
    }

    //Alterar dados do produto
    public boolean alterarProdutoDAO(ModelProdutos pModelProdutos) {
        try {
            this.conectar();
            return this.executarUpdateDeleteSQL(
                    "UPDATE tabela_produto"
                    + "produtoNome = '" + pModelProdutos.getNomeProduto() + "',"
                    + "produtoValor = '" + pModelProdutos.getPrecoProduto() + "',"
                    + "produtoEstoque = '" + pModelProdutos.getEstoqueProduto() + "'"
                    + "WHERE id_produto = '" + pModelProdutos.getIdProduto() + "'"
            );
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            this.fecharConexao();
        }
    }

    //Retorna um produto pelo codigo
    public ModelProdutos retornarProdutoDAO(int pIdProduto) {
        ModelProdutos modelProdutos = new ModelProdutos();
        try {
            this.conectar();
            this.executarSQL("SELECT "
                    + "id_produto, "
                    + "produtoNome, "
                    + "produtoValor, "
                    + "produtoEstoque"
                    + "FROM tabela_produto WHRE id_produto = '" + pIdProduto + "';");
            while (this.getResultSet().next()) {
                modelProdutos.setIdProduto(this.getResultSet().getInt(1));
                modelProdutos.setNomeProduto(this.getResultSet().getString(2));
                modelProdutos.setPrecoProduto(this.getResultSet().getDouble(3));
                modelProdutos.setEstoqueProduto(this.getResultSet().getInt(4));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.fecharConexao();
        }

        return modelProdutos;
    }
    
    //Retorna uma lista completa de produtos
    public ArrayList<ModelProdutos> retornarListaProdutosDAO() {
        ArrayList<ModelProdutos> listaModelProdutos = new ArrayList<>();
        ModelProdutos modelProdutos = new ModelProdutos();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "id_produto, "
                    + "produtoNome, "
                    + "produtoValor, "
                    + "produtoEstoque"
                    + "FROM tabela_produto;"
            );
            while(this.getResultSet().next()){
                modelProdutos = new ModelProdutos();
                modelProdutos.setIdProduto(this.getResultSet().getInt(1));
                modelProdutos.setNomeProduto(this.getResultSet().getString(2));
                modelProdutos.setPrecoProduto(this.getResultSet().getDouble(3));
                modelProdutos.setEstoqueProduto(this.getResultSet().getInt(4));
                
                listaModelProdutos.add(modelProdutos);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{
            this.fecharConexao();
        }
        return listaModelProdutos;
    }
}
