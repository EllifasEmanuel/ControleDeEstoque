/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import model.ModelClientes;

import DAO.DaoCliente;
import java.util.ArrayList;
import model.ModelProdutos;

/**
 *
 * @author ellif
 */
public class ControllerClientes {
    private DaoCliente daoCliente = new DaoCliente();
    
    public int salvarClienteController(ModelClientes pModalClientes){
        return this.daoCliente.salvarClienteDAO(pModalClientes);
        
    }
    
    public boolean excluirClienteController(int pCodigo){
        return this.excluirClienteController(pCodigo);
    }
    
    public boolean alterarClienteController(ModelClientes pModelClientes){
        return this.daoCliente.alterarClienteDAO(pModelClientes);
    }
    
    public ModelClientes retornaClienteController(int pCodigo){
        return this.daoCliente.retornarClienteDAO(pCodigo);
    }
    
    public ArrayList<ModelClientes> retornaListaClienteController(){ 
        return this.daoCliente.retornarListaClientesDAO();
    }
}