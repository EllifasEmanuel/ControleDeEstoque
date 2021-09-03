/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.sql.*;
import DAO.ConexaoUtil;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
/**
 *
 * @author ellif
 */
public class ViewClientes extends javax.swing.JInternalFrame {
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    /**
     * Creates new form ViewClientes
     */
    public ViewClientes() {
        initComponents();
        habilitarDesabilitarCampos(false);
        conexao = ConexaoUtil.conector();
    }
    
    private void adicionar() {
        String sql = "INSERT INTO tabela_clientes(nomeCliente,enderecoCliente,bairroCliente,numeroCliente,cidadeCliente,telefoneCliente) values(?, ?, ?, ?, ?, ?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, textoNome.getText());
            pst.setString(2, textoEndereco.getText());
            pst.setString(3, textoBairro.getText());
            pst.setString(4, textoNumero.getText());
            pst.setString(5, textoCidade.getText());
            pst.setString(6, textoTelefone.getText());
            

            if ((textoNome.getText().isEmpty()) || (textoEndereco.getText().isEmpty()) || (textoBairro.getText().isEmpty()) || (textoNumero.getText().isEmpty()) || (textoCidade.getText().isEmpty()) || (textoTelefone.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
            } else {

                int adicionado = pst.executeUpdate();

                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Cliente adicionado com sucessso!");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void pesquisarCliente(){
        String sql = "SELECT * FROM tabela_clientes WHERE nomeCliente like ?";
        try{
            pst = conexao.prepareStatement(sql);
            this.habilitarDesabilitarCampos(true);
            pst.setString(1, textoPesquisar.getText() + "%");
            rs = pst.executeQuery();
            
            tabelaCliente.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void setarCampos(){
        int setar = tabelaCliente.getSelectedRow();
        textoCodigo.setText(tabelaCliente.getModel().getValueAt(setar, 0).toString());
        textoNome.setText(tabelaCliente.getModel().getValueAt(setar, 1).toString());
        textoEndereco.setText(tabelaCliente.getModel().getValueAt(setar, 2).toString());
        textoBairro.setText(tabelaCliente.getModel().getValueAt(setar, 3).toString());
        textoNumero.setText(tabelaCliente.getModel().getValueAt(setar, 4).toString());
        textoCidade.setText(tabelaCliente.getModel().getValueAt(setar, 5).toString());
        textoTelefone.setText(tabelaCliente.getModel().getValueAt(setar, 6).toString());
    }
    
    private void alterar(){
        String sql = "UPDATE tabela_clientes set nomeCliente=?,enderecoCliente=?,bairroCliente=?,numeroCliente=?,cidadeCliente=?,telefoneCliente=? where idCliente=?";
        try{
            pst = conexao.prepareStatement(sql);
            this.habilitarDesabilitarCampos(true);
            pst.setString(1, textoNome.getText());
            pst.setString(2, textoEndereco.getText());
            pst.setString(3, textoBairro.getText());
            pst.setString(4, textoNumero.getText());
            pst.setString(5, textoCidade.getText());
            pst.setString(6, textoTelefone.getText());
            pst.setString(7, textoCodigo.getText());
            if ((textoNome.getText().isEmpty()) || (textoEndereco.getText().isEmpty()) || (textoBairro.getText().isEmpty()) || (textoNumero.getText().isEmpty()) || (textoCidade.getText().isEmpty()) || (textoTelefone.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
            } else {

                int adicionado = pst.executeUpdate();

                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Cliente alterado com sucessso!");
                    this.habilitarDesabilitarCampos(false);
                    limparCampos();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
    
    private void remover(){
        int confirmar = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover este usuário","Atenção",JOptionPane.WARNING_MESSAGE);
        if(confirmar == JOptionPane.YES_OPTION){
            String sql = "DELETE FROM tabela_clientes WHERE nomeCliente=?";
            try{
                pst=conexao.prepareStatement(sql);
                pst.setString(1, textoNome.getText());
                int apagado = pst.executeUpdate();
                
                if(apagado>0){
                    JOptionPane.showMessageDialog(null, "Cliente removido com sucesso!");
                    limparCampos();
                }else{
                    JOptionPane.showMessageDialog(null, "Nenhum cliente removido!");
                }
                
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }
    
    private void habilitarDesabilitarCampos(boolean condicao) {
        textoNome.setEnabled(condicao);
        textoEndereco.setEnabled(condicao);
        textoBairro.setEnabled(condicao);
        textoNumero.setEnabled(condicao);
        textoCidade.setEnabled(condicao);
        textoTelefone.setEnabled(condicao);
        botaoSalvar.setEnabled(condicao);
        botaoAlterar.setEnabled(condicao);
        botaoExcluir.setEnabled(condicao);
    }
    
    private void limparCampos(){
        textoNome.setText("");
        textoEndereco.setText("");
        textoBairro.setText("");
        textoNumero.setText("");
        textoCidade.setText("");
        textoTelefone.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        textoNome = new javax.swing.JTextField();
        labelEndereco = new javax.swing.JLabel();
        labelBairro = new javax.swing.JLabel();
        textoEndereco = new javax.swing.JTextField();
        textoBairro = new javax.swing.JTextField();
        labelNumero = new javax.swing.JLabel();
        labelCidade = new javax.swing.JLabel();
        textoCidade = new javax.swing.JTextField();
        labelTelefone = new javax.swing.JLabel();
        botaoCancelar = new javax.swing.JButton();
        botaoSalvar = new javax.swing.JButton();
        botaoAlterar = new javax.swing.JButton();
        botaoNovo = new javax.swing.JButton();
        botaoExcluir = new javax.swing.JButton();
        textoNumero = new javax.swing.JFormattedTextField();
        textoTelefone = new javax.swing.JTextField();
        pesquisarCliente = new javax.swing.JLabel();
        textoPesquisar = new javax.swing.JTextField();
        botaoPesquisa = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaCliente = new javax.swing.JTable();
        textoCodigo = new javax.swing.JFormattedTextField();
        labelCodigo = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Cadastro de Clientes");

        jPanel1.setBackground(new java.awt.Color(176, 196, 222));

        jLabel3.setText("Nome:");

        textoNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textoNomeActionPerformed(evt);
            }
        });

        labelEndereco.setText("Endereço: ");

        labelBairro.setText("Bairro: ");

        labelNumero.setText("Número: ");

        labelCidade.setText("Cidade:");

        labelTelefone.setText("Telefone:");

        botaoCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/cancelar.png"))); // NOI18N
        botaoCancelar.setText("Cancelar");
        botaoCancelar.setToolTipText("Cancelar cadastro");
        botaoCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCancelarActionPerformed(evt);
            }
        });

        botaoSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/salvar.png"))); // NOI18N
        botaoSalvar.setText("Salvar");
        botaoSalvar.setToolTipText("Salvar cliente");
        botaoSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoSalvarActionPerformed(evt);
            }
        });

        botaoAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/alterar.png"))); // NOI18N
        botaoAlterar.setText("Alterar");
        botaoAlterar.setToolTipText("Alterar cliente");
        botaoAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAlterarActionPerformed(evt);
            }
        });

        botaoNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/novo.png"))); // NOI18N
        botaoNovo.setText("Novo");
        botaoNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoNovoActionPerformed(evt);
            }
        });

        botaoExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/excluir.png"))); // NOI18N
        botaoExcluir.setText("Excluir");
        botaoExcluir.setToolTipText("Excluir cliente");
        botaoExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoExcluirActionPerformed(evt);
            }
        });

        pesquisarCliente.setText("Pesquisar:");

        textoPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textoPesquisarActionPerformed(evt);
            }
        });
        textoPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textoPesquisarKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textoPesquisarKeyReleased(evt);
            }
        });

        botaoPesquisa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/procura-de-emprego.png"))); // NOI18N
        botaoPesquisa.setText("Pesquisar");
        botaoPesquisa.setToolTipText("Pesquisar cliente");
        botaoPesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoPesquisaActionPerformed(evt);
            }
        });
        botaoPesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                botaoPesquisaKeyReleased(evt);
            }
        });

        tabelaCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tabelaCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaClienteMouseClicked(evt);
            }
        });
        tabelaCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tabelaClienteKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaCliente);

        textoCodigo.setEnabled(false);

        labelCodigo.setText("Código:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textoEndereco)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(botaoCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(botaoExcluir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(botaoNovo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(botaoAlterar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botaoSalvar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(textoBairro, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                                    .addComponent(labelBairro)
                                    .addComponent(labelCidade)
                                    .addComponent(textoCidade))
                                .addGap(50, 50, 50)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelTelefone)
                                    .addComponent(labelNumero)
                                    .addComponent(textoNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textoTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(labelEndereco)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(pesquisarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textoPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 529, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(botaoPesquisa)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, 0)
                                .addComponent(labelCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textoNome, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCodigo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelEndereco)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textoEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelBairro)
                    .addComponent(labelNumero))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelTelefone)
                    .addComponent(labelCidade))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pesquisarCliente)
                    .addComponent(textoPesquisar)
                    .addComponent(botaoPesquisa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoNovo)
                    .addComponent(botaoExcluir)
                    .addComponent(botaoCancelar)
                    .addComponent(botaoAlterar)
                    .addComponent(botaoSalvar))
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textoNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textoNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textoNomeActionPerformed

    private void botaoCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelarActionPerformed
        // TODO add your handling code here:
        limparCampos();
        this.habilitarDesabilitarCampos(false);
    }//GEN-LAST:event_botaoCancelarActionPerformed

    private void botaoSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSalvarActionPerformed
        adicionar();
        limparCampos();
        this.habilitarDesabilitarCampos(false);
    }//GEN-LAST:event_botaoSalvarActionPerformed

    private void botaoAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAlterarActionPerformed
        alterar();
    }//GEN-LAST:event_botaoAlterarActionPerformed

    private void botaoNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoNovoActionPerformed
        this.habilitarDesabilitarCampos(true);
    }//GEN-LAST:event_botaoNovoActionPerformed

    private void botaoExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoExcluirActionPerformed
        remover();
    }//GEN-LAST:event_botaoExcluirActionPerformed

    private void textoPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textoPesquisarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textoPesquisarActionPerformed

    private void botaoPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoPesquisaActionPerformed
        // TODO add your handling code here:
        pesquisarCliente();

    }//GEN-LAST:event_botaoPesquisaActionPerformed

    private void tabelaClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabelaClienteKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tabelaClienteKeyReleased

    private void textoPesquisarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoPesquisarKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_textoPesquisarKeyPressed

    private void botaoPesquisaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_botaoPesquisaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_botaoPesquisaKeyReleased

    private void textoPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoPesquisarKeyReleased
        // TODO add your handling code here:
        pesquisarCliente();
    }//GEN-LAST:event_textoPesquisarKeyReleased

    private void tabelaClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaClienteMouseClicked
        // TODO add your handling code here:
        setarCampos();
    }//GEN-LAST:event_tabelaClienteMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoAlterar;
    private javax.swing.JButton botaoCancelar;
    private javax.swing.JButton botaoExcluir;
    private javax.swing.JButton botaoNovo;
    private javax.swing.JButton botaoPesquisa;
    private javax.swing.JButton botaoSalvar;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelBairro;
    private javax.swing.JLabel labelCidade;
    private javax.swing.JLabel labelCodigo;
    private javax.swing.JLabel labelEndereco;
    private javax.swing.JLabel labelNumero;
    private javax.swing.JLabel labelTelefone;
    private javax.swing.JLabel pesquisarCliente;
    private javax.swing.JTable tabelaCliente;
    private javax.swing.JTextField textoBairro;
    private javax.swing.JTextField textoCidade;
    private javax.swing.JFormattedTextField textoCodigo;
    private javax.swing.JTextField textoEndereco;
    private javax.swing.JTextField textoNome;
    private javax.swing.JFormattedTextField textoNumero;
    private javax.swing.JTextField textoPesquisar;
    private javax.swing.JTextField textoTelefone;
    // End of variables declaration//GEN-END:variables
}
