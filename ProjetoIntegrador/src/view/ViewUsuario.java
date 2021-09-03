/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

/**
 *
 * @author ellif
 */
import java.sql.*;
import DAO.ConexaoUtil;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

public class ViewUsuario extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form ViewUsuario
     */
    public ViewUsuario() {
        initComponents();
        conexao = ConexaoUtil.conector();
        habilitarDesabilitarCampos(false);
    }

    private void consultar() {
        String sql = "SELECT * FROM tabela_usuarios WHERE idUsuario=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, codigoUsuario.getText());
            rs = pst.executeQuery();

            if (rs.next()) {
                nomeUsuario.setText(rs.getString(2));
                textoLogin.setText(rs.getString(3));
                textoSenha.setText(rs.getString(4));
                ComboBoxPerfil.setSelectedItem(rs.getString(5));
            } else {
                JOptionPane.showMessageDialog(this, "Usuario não cadastrado!", "ERRO", JOptionPane.ERROR_MESSAGE);
                //limpar campos
                codigoUsuario.setText("");
                nomeUsuario.setText("");
                textoLogin.setText("");
                textoSenha.setText("");
                
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void adicionar() {
        String sql = "INSERT INTO tabela_usuarios(idUsuario,nomeUsuario,loginUsuario,senhaUsuario,perfil) values(?, ?, ?, ?, ?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, codigoUsuario.getText());
            pst.setString(2, nomeUsuario.getText());
            pst.setString(3, textoLogin.getText());
            pst.setString(4, textoSenha.getText());
            pst.setString(5, ComboBoxPerfil.getSelectedItem().toString());

            if ((codigoUsuario.getText().isEmpty()) || (nomeUsuario.getText().isEmpty()) || (textoLogin.getText().isEmpty()) || (textoSenha.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todoso os campos obrigatórios");
            } else {

                int adicionado = pst.executeUpdate();

                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Usuário adicionado com sucessso!");
                    codigoUsuario.setText("");
                    nomeUsuario.setText("");
                    textoLogin.setText("");
                    textoSenha.setText("");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void alterar(){
        String sql = "UPDATE tabela_usuarios set nomeUsuario=?,loginUsuario=?,senhaUsuario=?,perfil=? where idUsuario=?";
        try{
            pst = conexao.prepareStatement(sql);
            pst.setString(1, nomeUsuario.getText());
            pst.setString(2, textoLogin.getText());
            pst.setString(3, textoSenha.getText());
            pst.setString(4, ComboBoxPerfil.getSelectedItem().toString());
            pst.setString(5, codigoUsuario.getText());
            if ((codigoUsuario.getText().isEmpty()) || (nomeUsuario.getText().isEmpty()) || (textoLogin.getText().isEmpty()) || (textoSenha.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todoso os campos obrigatórios");
            } else {

                int adicionado = pst.executeUpdate();

                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Usuário alterado com sucessso!");
                    codigoUsuario.setText("");
                    nomeUsuario.setText("");
                    textoLogin.setText("");
                    textoSenha.setText("");
                }
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
    
    private void remover(){
        int confirmar = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover este usuário","Atenção",JOptionPane.WARNING_MESSAGE);
        if(confirmar == JOptionPane.YES_OPTION){
            String sql = "DELETE FROM tabela_usuarios WHERE idUsuario=?";
            try{
                pst=conexao.prepareStatement(sql);
                pst.setString(1, codigoUsuario.getText());
                int apagado = pst.executeUpdate();
                
                if(apagado>0){
                    JOptionPane.showMessageDialog(null, "Usuário removido com sucesso!");
                    codigoUsuario.setText("");
                    nomeUsuario.setText("");
                    textoLogin.setText("");
                    textoSenha.setText("");
                }
                
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }
    
    private void habilitarDesabilitarCampos(boolean condicao) {
        codigoUsuario.setEnabled(condicao);
        nomeUsuario.setEnabled(condicao);
        textoLogin.setEnabled(condicao);
        textoSenha.setEnabled(condicao);
        botaoSalvar.setEnabled(condicao);
        botaoAlterar.setEnabled(condicao);
        botaoExcluir.setEnabled(condicao);
    }
    
    private void limparCampos(){
        codigoUsuario.setText("");
        nomeUsuario.setText("");
        textoLogin.setText("");
        textoSenha.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        painelUsuario = new javax.swing.JPanel();
        labelCodigo = new javax.swing.JLabel();
        labelNome = new javax.swing.JLabel();
        nomeUsuario = new javax.swing.JTextField();
        labelLogin = new javax.swing.JLabel();
        textoLogin = new javax.swing.JTextField();
        labelSenha = new javax.swing.JLabel();
        textoSenha = new javax.swing.JTextField();
        botaoCancelar = new javax.swing.JButton();
        botaoSalvar = new javax.swing.JButton();
        botaoAlterar = new javax.swing.JButton();
        botaoExcluir = new javax.swing.JButton();
        botaoNovo = new javax.swing.JButton();
        codigoUsuario = new javax.swing.JFormattedTextField();
        ComboBoxPerfil = new javax.swing.JComboBox<>();
        perfilUsuario = new javax.swing.JLabel();
        botaoPesquisar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Cadastro de Usuários");

        painelUsuario.setBackground(new java.awt.Color(176, 196, 222));
        painelUsuario.setPreferredSize(new java.awt.Dimension(976, 506));

        labelCodigo.setText("* Código:");

        labelNome.setText("* Nome:");

        nomeUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nomeUsuarioActionPerformed(evt);
            }
        });

        labelLogin.setText("* Login:");

        textoLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textoLoginActionPerformed(evt);
            }
        });

        labelSenha.setText("* Senha:");

        botaoCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/cancelar.png"))); // NOI18N
        botaoCancelar.setText("Cancelar");
        botaoCancelar.setToolTipText("Cancelar cadastro");

        botaoSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/salvar.png"))); // NOI18N
        botaoSalvar.setText("Salvar");
        botaoSalvar.setToolTipText("Salvar Usuário");
        botaoSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoSalvarActionPerformed(evt);
            }
        });

        botaoAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/alterar.png"))); // NOI18N
        botaoAlterar.setText("Alterar");
        botaoAlterar.setToolTipText("Alterar Usuário");
        botaoAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAlterarActionPerformed(evt);
            }
        });

        botaoExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/excluir.png"))); // NOI18N
        botaoExcluir.setText("Excluir");
        botaoExcluir.setToolTipText("Excluir Usuário");
        botaoExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoExcluirActionPerformed(evt);
            }
        });

        botaoNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/novo.png"))); // NOI18N
        botaoNovo.setText("Novo");
        botaoNovo.setToolTipText("Novo Usuário");
        botaoNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoNovoActionPerformed(evt);
            }
        });

        codigoUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                codigoUsuarioActionPerformed(evt);
            }
        });

        ComboBoxPerfil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "admin", "user" }));

        perfilUsuario.setText("* Perfil");

        botaoPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/procura-de-emprego.png"))); // NOI18N
        botaoPesquisar.setText("Pesquisar");
        botaoPesquisar.setToolTipText("Alterar Usuário");
        botaoPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoPesquisarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel1.setText("* CAMPOS OBRIGATÓRIOS");

        javax.swing.GroupLayout painelUsuarioLayout = new javax.swing.GroupLayout(painelUsuario);
        painelUsuario.setLayout(painelUsuarioLayout);
        painelUsuarioLayout.setHorizontalGroup(
            painelUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelUsuarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelUsuarioLayout.createSequentialGroup()
                        .addGroup(painelUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelCodigo)
                            .addComponent(labelLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(157, 157, 157)
                        .addGroup(painelUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelNome, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(painelUsuarioLayout.createSequentialGroup()
                                .addComponent(labelSenha)
                                .addGap(274, 274, 274)
                                .addComponent(perfilUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(painelUsuarioLayout.createSequentialGroup()
                                .addGap(317, 317, 317)
                                .addComponent(ComboBoxPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(painelUsuarioLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(botaoCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoExcluir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoNovo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(botaoAlterar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(botaoPesquisar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 95, Short.MAX_VALUE)
                        .addComponent(botaoSalvar))
                    .addGroup(painelUsuarioLayout.createSequentialGroup()
                        .addGroup(painelUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(textoLogin, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
                            .addComponent(codigoUsuario))
                        .addGap(26, 26, 26)
                        .addGroup(painelUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nomeUsuario)
                            .addGroup(painelUsuarioLayout.createSequentialGroup()
                                .addGroup(painelUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(textoSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        painelUsuarioLayout.setVerticalGroup(
            painelUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelUsuarioLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(painelUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCodigo)
                    .addComponent(labelNome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(36, 36, 36)
                .addGroup(painelUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(codigoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nomeUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55)
                .addGroup(painelUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelSenha)
                    .addComponent(perfilUsuario)
                    .addComponent(labelLogin))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(painelUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboBoxPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(84, 84, 84)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(104, 104, 104)
                .addGroup(painelUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoCancelar)
                    .addComponent(botaoExcluir)
                    .addComponent(botaoNovo)
                    .addComponent(botaoAlterar)
                    .addComponent(botaoSalvar)
                    .addComponent(botaoPesquisar))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 726, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoPesquisarActionPerformed
        // TODO add your handling code here:
        consultar();
    }//GEN-LAST:event_botaoPesquisarActionPerformed

    private void codigoUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_codigoUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_codigoUsuarioActionPerformed

    private void botaoNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoNovoActionPerformed
        // TODO add your handling code here:
        habilitarDesabilitarCampos(true);

    }//GEN-LAST:event_botaoNovoActionPerformed

    private void botaoExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoExcluirActionPerformed
        // TODO add your handling code here:
        habilitarDesabilitarCampos(true);
        remover();
        limparCampos();
        habilitarDesabilitarCampos(false);
    }//GEN-LAST:event_botaoExcluirActionPerformed

    private void botaoAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAlterarActionPerformed
        // TODO add your handling code here:
        alterar();
        limparCampos();
        habilitarDesabilitarCampos(false);
    }//GEN-LAST:event_botaoAlterarActionPerformed

    private void botaoSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSalvarActionPerformed
        // TODO add your handling code here:
        habilitarDesabilitarCampos(true);
        adicionar();
        limparCampos();
        habilitarDesabilitarCampos(false);
    }//GEN-LAST:event_botaoSalvarActionPerformed

    private void textoLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textoLoginActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textoLoginActionPerformed

    private void nomeUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nomeUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nomeUsuarioActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboBoxPerfil;
    private javax.swing.JButton botaoAlterar;
    private javax.swing.JButton botaoCancelar;
    private javax.swing.JButton botaoExcluir;
    private javax.swing.JButton botaoNovo;
    private javax.swing.JButton botaoPesquisar;
    private javax.swing.JButton botaoSalvar;
    private javax.swing.JFormattedTextField codigoUsuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel labelCodigo;
    private javax.swing.JLabel labelLogin;
    private javax.swing.JLabel labelNome;
    private javax.swing.JLabel labelSenha;
    private javax.swing.JTextField nomeUsuario;
    private javax.swing.JPanel painelUsuario;
    private javax.swing.JLabel perfilUsuario;
    private javax.swing.JTextField textoLogin;
    private javax.swing.JTextField textoSenha;
    // End of variables declaration//GEN-END:variables
}
