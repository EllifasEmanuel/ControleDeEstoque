/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.ModelProdutos;

import DAO.DaoProdutos;
import java.util.ArrayList;

/**
 *
 * @author ellif
 */
public class ControllerProdutos {
    private DaoProdutos daoProdutos = new DaoProdutos();
    
    public int salvarProdutoController(ModelProdutos pModalProdutos){
        return this.daoProdutos.salvarProdutosDAO(pModalProdutos);
        
    }
    
    public boolean excluirProdutoController(int pCodigo){
        return this.excluirProdutoController(pCodigo);
    }
    
    public boolean alterarProdutoController(ModelProdutos pModelProdutos){
        return this.daoProdutos.alterarProdutoDAO(pModelProdutos);
    }
    
    public ModelProdutos retornaProdutoController(int pCodigo){
        return this.daoProdutos.retornarProdutoDAO(pCodigo);
    }
    
    public ArrayList<ModelProdutos> retornaListaProdutoController(){
    
        return this.daoProdutos.retornarListaProdutosDAO();
    }
}