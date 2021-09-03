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
public class ViewProdutos extends javax.swing.JInternalFrame {
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    /**
     * Creates new form ViewProduto
     */
    public ViewProdutos() {
        initComponents();
        habilitarDesabilitarCampos(false);
        conexao = ConexaoUtil.conector();
    }
    
    private void adicionar() {
        String sql = "INSERT INTO tabela_produto(nomeProduto,valorProduto,quantidadeProduto) values(?, ?, ?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, textoNome.getText());
            pst.setString(2, textoValor.getText());
            pst.setString(3, textoQuantidade.getText());
            

            if ((textoNome.getText().isEmpty()) || (textoValor.getText().isEmpty()) || (textoQuantidade.getText().isEmpty())) {
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
    
    private void pesquisaProduto(){
        String sql = "SELECT * FROM tabela_produto WHERE nomeProduto like ?";
        try{
            pst = conexao.prepareStatement(sql);
            pst.setString(1, pesquisarProduto.getText() + "%");
            rs = pst.executeQuery();
            
            tabelaProdutos.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void setarCampos(){
        int setar = tabelaProdutos.getSelectedRow();
        codigoProduto.setText(tabelaProdutos.getModel().getValueAt(setar, 0).toString());
        textoNome.setText(tabelaProdutos.getModel().getValueAt(setar, 1).toString());
        textoValor.setText(tabelaProdutos.getModel().getValueAt(setar, 2).toString());
        textoQuantidade.setText(tabelaProdutos.getModel().getValueAt(setar, 3).toString());
    }
    
    private void alterar(){
        String sql = "UPDATE tabela_produto set nomeProduto=?,valorProduto=?,quantidadeProduto=? where idProduto=?";
        try{
            pst = conexao.prepareStatement(sql);
            pst.setString(1, textoNome.getText());
            pst.setString(2, textoValor.getText());
            pst.setString(3, textoQuantidade.getText());
            pst.setString(4, codigoProduto.getText());
            if ((textoNome.getText().isEmpty()) || (textoValor.getText().isEmpty()) || (textoQuantidade.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
            } else {

                int adicionado = pst.executeUpdate();

                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Produto alterado com sucessso!");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
    
    private void remover(){
        int confirmar = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover este usuário","Atenção",JOptionPane.WARNING_MESSAGE);
        if(confirmar == JOptionPane.YES_OPTION){
            String sql = "DELETE FROM tabela_produto WHERE nomeProduto=?";
            try{
                pst=conexao.prepareStatement(sql);
                pst.setString(1, textoNome.getText());
                int apagado = pst.executeUpdate();
                
                if(apagado>0){
                    JOptionPane.showMessageDialog(null, "Produto removido com sucesso!");
                }else{
                    JOptionPane.showMessageDialog(null, "Nenhum produto removido!");
                }
                
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }
    
    private void habilitarDesabilitarCampos(boolean condicao) {
        textoNome.setEnabled(condicao);
        textoValor.setEnabled(condicao);
        textoQuantidade.setEnabled(condicao);
        botaoSalvar.setEnabled(condicao);
        botaoAlterar.setEnabled(condicao);
        botaoExcluir.setEnabled(condicao);
    }
    
    private void limparCampos(){
        textoNome.setText("");
        textoValor.setText("");
        textoQuantidade.setText("");
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        painelProduto = new javax.swing.JPanel();
        labelQuantidade = new javax.swing.JLabel();
        labelCodigo = new javax.swing.JLabel();
        labelValor = new javax.swing.JLabel();
        labelNome = new javax.swing.JLabel();
        textoNome = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaProdutos = new javax.swing.JTable();
        labelPesquisar = new javax.swing.JLabel();
        pesquisarProduto = new javax.swing.JTextField();
        botaoPesquisar = new javax.swing.JButton();
        botaoCancelar = new javax.swing.JButton();
        botaoAlterar = new javax.swing.JButton();
        botaoExcluir = new javax.swing.JButton();
        botaoSalvar = new javax.swing.JButton();
        botaoNovo = new javax.swing.JButton();
        codigoProduto = new javax.swing.JFormattedTextField();
        textoValor = new javax.swing.JTextField();
        textoQuantidade = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Cadastro de Produtos");

        painelProduto.setBackground(new java.awt.Color(176, 196, 222));

        labelQuantidade.setText("Quantidade de Estoque:");

        labelCodigo.setText("ID do Produto:");

        labelValor.setText("Valor do Produto: ");

        labelNome.setText("Nome do Produto:");

        tabelaProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tabelaProdutos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaProdutosMouseClicked(evt);
            }
        });
        tabelaProdutos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tabelaProdutosKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaProdutos);

        labelPesquisar.setText("Pesquisar");

        pesquisarProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pesquisarProdutoActionPerformed(evt);
            }
        });

        botaoPesquisar.setText("Pesquisar");
        botaoPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoPesquisarActionPerformed(evt);
            }
        });

        botaoCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/cancelar.png"))); // NOI18N
        botaoCancelar.setText("Cancelar");
        botaoCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCancelarActionPerformed(evt);
            }
        });

        botaoAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/alterar.png"))); // NOI18N
        botaoAlterar.setText("Alterar");
        botaoAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAlterarActionPerformed(evt);
            }
        });

        botaoExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/excluir.png"))); // NOI18N
        botaoExcluir.setText("Excluir");
        botaoExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoExcluirActionPerformed(evt);
            }
        });

        botaoSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/salvar.png"))); // NOI18N
        botaoSalvar.setText("Salvar");
        botaoSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoSalvarActionPerformed(evt);
            }
        });

        botaoNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/novo.png"))); // NOI18N
        botaoNovo.setText("Novo");
        botaoNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoNovoActionPerformed(evt);
            }
        });

        codigoProduto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        codigoProduto.setEnabled(false);

        javax.swing.GroupLayout painelProdutoLayout = new javax.swing.GroupLayout(painelProduto);
        painelProduto.setLayout(painelProdutoLayout);
        painelProdutoLayout.setHorizontalGroup(
            painelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelProdutoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelValor)
                    .addGroup(painelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, painelProdutoLayout.createSequentialGroup()
                            .addComponent(labelPesquisar)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(pesquisarProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(botaoPesquisar))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, painelProdutoLayout.createSequentialGroup()
                            .addComponent(botaoCancelar)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(botaoNovo)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(botaoAlterar)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(botaoExcluir)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(botaoSalvar))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 616, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(painelProdutoLayout.createSequentialGroup()
                            .addGroup(painelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(labelCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(codigoProduto, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                                .addComponent(textoValor))
                            .addGap(37, 37, 37)
                            .addGroup(painelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(textoQuantidade)
                                .addGroup(painelProdutoLayout.createSequentialGroup()
                                    .addGroup(painelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(labelNome, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(labelQuantidade))
                                    .addGap(0, 0, Short.MAX_VALUE))
                                .addComponent(textoNome)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        painelProdutoLayout.setVerticalGroup(
            painelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelProdutoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelNome, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(painelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(codigoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoNome, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(painelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelValor, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(painelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelPesquisar)
                    .addComponent(pesquisarProduto)
                    .addComponent(botaoPesquisar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addGroup(painelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoCancelar)
                    .addComponent(botaoNovo)
                    .addComponent(botaoAlterar)
                    .addComponent(botaoExcluir)
                    .addComponent(botaoSalvar))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelProduto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelProduto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pesquisarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pesquisarProdutoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pesquisarProdutoActionPerformed

    private void botaoPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoPesquisarActionPerformed
        // TODO add your handling code here:
        this.habilitarDesabilitarCampos(true);
        pesquisaProduto();
        
    }//GEN-LAST:event_botaoPesquisarActionPerformed

    private void botaoCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelarActionPerformed
        // TODO add your handling code here:
        limparCampos();
        this.habilitarDesabilitarCampos(false);
        
    }//GEN-LAST:event_botaoCancelarActionPerformed

    private void botaoAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAlterarActionPerformed
        // TODO add your handling code here:
        this.habilitarDesabilitarCampos(true);
        alterar();
    }//GEN-LAST:event_botaoAlterarActionPerformed

    private void botaoExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoExcluirActionPerformed
        // TODO add your handling code here:
        remover();
        limparCampos();
        this.habilitarDesabilitarCampos(false);
    }//GEN-LAST:event_botaoExcluirActionPerformed

    private void botaoSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSalvarActionPerformed
        // TODO add your handling code here:
        adicionar();
        limparCampos();
        this.habilitarDesabilitarCampos(false);
    }//GEN-LAST:event_botaoSalvarActionPerformed

    private void botaoNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoNovoActionPerformed
        // TODO add your handling code here:
        limparCampos();
        this.habilitarDesabilitarCampos(true);
    }//GEN-LAST:event_botaoNovoActionPerformed

    private void tabelaProdutosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabelaProdutosKeyReleased
        // TODO add your handling code here:
        pesquisaProduto();
    }//GEN-LAST:event_tabelaProdutosKeyReleased

    private void tabelaProdutosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaProdutosMouseClicked
        // TODO add your handling code here:
        setarCampos();
    }//GEN-LAST:event_tabelaProdutosMouseClicked
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoAlterar;
    private javax.swing.JButton botaoCancelar;
    private javax.swing.JButton botaoExcluir;
    private javax.swing.JButton botaoNovo;
    private javax.swing.JButton botaoPesquisar;
    private javax.swing.JButton botaoSalvar;
    private javax.swing.JFormattedTextField codigoProduto;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelCodigo;
    private javax.swing.JLabel labelNome;
    private javax.swing.JLabel labelPesquisar;
    private javax.swing.JLabel labelQuantidade;
    private javax.swing.JLabel labelValor;
    private javax.swing.JPanel painelProduto;
    private javax.swing.JTextField pesquisarProduto;
    private javax.swing.JTable tabelaProdutos;
    private javax.swing.JTextField textoNome;
    private javax.swing.JTextField textoQuantidade;
    private javax.swing.JTextField textoValor;
    // End of variables declaration//GEN-END:variables
}
