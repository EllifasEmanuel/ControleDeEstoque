/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;


import DAO.DaoUsuarios;
import java.util.ArrayList;
import model.ModelUsuarios;

/**
 *
 * @author ellif
 */
public class ControllerUsuarios {
    private DaoUsuarios daoUsuarios = new DaoUsuarios();
    
    public int salvarUsuariosController(ModelUsuarios pModalUsuarios){
        return this.daoUsuarios.salvarUsuariosDAO(pModalUsuarios);
        
    }
    
    public boolean excluirUsuariosController(int pCodigo){
        return this.excluirUsuariosController(pCodigo);
    }
    
    public boolean alterarUsuariosController(ModelUsuarios pModelUsuarios){
        return this.daoUsuarios.alterarUsuariosDAO(pModelUsuarios);
    }
    
    public ModelUsuarios retornaUsuariosController(int pCodigo){
        return this.daoUsuarios.retornarUsuariosDAO(pCodigo);
    }
    
    public ArrayList<ModelUsuarios> retornaListaUsuariosController(){ 
        return this.daoUsuarios.retornarListaUsuariosDAO();
    }

    public boolean getValidarUsuarioController(ModelUsuarios pModelUsuario) {
        return this.daoUsuarios.getValidarUsuarioDao(pModelUsuario);
    }
}