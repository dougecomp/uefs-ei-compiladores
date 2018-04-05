package janelas;

import exceptions.LexerException;
import exceptions.ParserException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import lexico.CodeBuffer;
import lexico.ErrorList;
import lexico.Lexico;
import lexico.TokenList;
import lexico.Token;
import sintatico.Parser;

/**
 *
 * @author Ronaldo e André
 */
public class Tela extends javax.swing.JFrame {

    Parser parser;

    public Tela() {

        initComponents();
        initTable();

        /*
         * Abaixo comandos para estruturar a jTextArea, dand-lhe a capacidade de quebrar linhas dentro daquele espaço
         * limitado no vídeo. Já foi testado e não apresenta ameaças de erros à nossa classe scan();
         * método setLineWrap() --> quebra a linha
         * método setWrapStyleWord() --> quebra a linha somente após um espaço em branco ou um delimitador. Ex: .!, etc...  
         */
        jTextArea1.setLineWrap(true);
        jTextArea1.setWrapStyleWord(true);

        modelTokens.addRow(new String[]{"", "", "", ""});
        modelErros.addRow(new String[]{"", "", "", ""});

        setLabel("Digite o código-fonte na área de texto.");

        // define o tamanho das colunas da tabela;
        tabelaToken.getColumnModel().getColumn(2).setPreferredWidth(5);
        tabelaToken.getColumnModel().getColumn(3).setPreferredWidth(5);

        tabelaErros.getColumnModel().getColumn(2).setPreferredWidth(5);
        tabelaErros.getColumnModel().getColumn(3).setPreferredWidth(5);

        setLocation(100, 50);
        setVisible(true);
    }

    /*
     * inicia as tabelas com as informações dos tokens e dos erros;
     */
    private void initTable() {

        // instância o modelo das tabelas;
        modelTokens = new DefaultTableModel(new String[]{"Token", "Lexema", "Linha", "Posição"}, 0) {

            @Override
            public boolean isCellEditable(int a, int b) {
                return false;
            }
        };
        modelErros = new DefaultTableModel(new String[]{"Erro", "Lexema", "Linha", "Posição"}, 0) {

            @Override
            public boolean isCellEditable(int a, int b) {
                return false;
            }
        };

        // cria as tabelas;
        tabelaToken = new JTable(modelTokens);
        tabelaErros = new JTable(modelErros);

        jScrollPane2.setViewportView(tabelaToken);
        jScrollPane3.setViewportView(tabelaErros);

        jScrollPane2.setAutoscrolls(true);
        jScrollPane3.setAutoscrolls(true);
    } // fim do método initTable();


    /*
     * seta o texto que é apresentado na barra de status;
     */
    private void setLabel(String text) {
        jLabel1.setText(text);
    }


    /*
     * Insere as informações do token na tabela de tokens identificados;
     */
    public void inserirToken(Token token) {

        String infos[] = new String[4];

        infos[0] = token.getTokenClass().toString();
        infos[1] = token.getLexeme();
        infos[2] = String.valueOf(token.getLine());
        infos[3] = String.valueOf(token.getPosition());

        modelTokens.addRow(infos);
    }

    /*
     * Insere os erros de um token não identificado;
     */
    public void inserirErros(LexerException e) {
        String[] infos = new String[4];

        infos[0] = e.getDescricao();
        infos[1] = e.getLexema();
        infos[2] = String.valueOf(e.getLinha());
        infos[3] = String.valueOf(e.getPosicao());

        modelErros.addRow(infos);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        btnDoAnaliseLexica = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Analisador Léxico e Sintático");

        jLabel1.setText(" ");
        jLabel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        jTextArea1.setRows(5);
        jTextArea1.setText("algoritmo teste;\n\nconstantes inteiro MAX = \"teste\";\nconstantes real MIN = 20;\n\nvariaveis inteiro teste = \"teste\";\n\ntipo inteiro int;\ntipo registro {\n    inteiro x;\n    real y;\n    int pos[];\n} pair;\n\nvariaveis real vercompilost = 9.66;\n\nfuncao vazio principal(){\n\tfunnn(a,b);\n\tworking();\n}\n\nfuncao vazio resetar(logico tudo){\n    se(tudo) entao {\n\tclear();\n    }\n    senao { clear(getActive()); }\n}\n\nfuncao logico working(){\n\tretorno(vetor[2555]);\n}\n\nfuncao real gambiarra(inteiro mat[][], pair xy){\n    a = (25.6 + 2 * (43));\n    mat[x][y + 23 / REAL * a] = numeroMagico() / raiz(3.14);\n\n    se(x < (a || b * c)) entao {\n        variaveis logico test = alpha != (1 == 1);\n    }\n    senao {\n        variaveis logico test = beta > 1 / 2;\n    }\n}\n\nvariaveis float exitCode = getRandom(false);");
        jScrollPane1.setViewportView(jTextArea1);

        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder("Tokens Identificados"));

        jScrollPane3.setBorder(javax.swing.BorderFactory.createTitledBorder("Erros"));

        btnDoAnaliseLexica.setText("Análise Léxica/Sintática");
        btnDoAnaliseLexica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoAnaliseLexicaActionPerformed(evt);
            }
        });

        jMenu1.setText("Arquivo");

        jMenuItem1.setText("Sair");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Executar");

        jMenuItem2.setText("Análise Léxica");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Limpar");

        jMenuItem3.setText("Limpar Texto");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);

        jMenuItem4.setText("Limpar Tabelas");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem4);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1002, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 525, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDoAnaliseLexica))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDoAnaliseLexica)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // ação quando clica no itemMenu sair;
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    // limpa a área de texto;
    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        jTextArea1.setText("");
        setLabel("A área de texto foi limpa.");
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    // ação quando clica no itemMenu executar;
    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        setLabel("Análise em andamento.");

        Lexico lex = new Lexico(new CodeBuffer(jTextArea1.getText()));
        lex.scan();

        //removendo da tabela de erros e de tokens
//        modelTokens.getDataVector().removeAllElements();
//        modelErros.getDataVector().removeAllElements();
        jMenuItem4ActionPerformed(evt);


        if (lex.getTokenList().size() == 0) {
            modelTokens.addRow(new String[]{"", "", "", ""});
        } else {
            for (int i = 0; i < lex.getTokenList().size(); i++) {
                inserirToken(lex.getTokenList().get(i));
            }
        }


        if (lex.getErrorList().size() == 0) {
            sintaticAnalysis(lex.getTokenList());

        } else {
            for (int i = 0; i < lex.getErrorList().size(); i++) {
                inserirErros((LexerException) lex.getErrorList().get(i));
            }
        }

        setLabel("Análise finalizada com sucesso.");

    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void sintaticAnalysis(TokenList st) {
        
        parser = new Parser(st, new ErrorList());
//        modelErros.addRow(new String[]{"", "", "", ""});
        
        try {
            parser.scan();

//            System.out.println("Tamanho da tabela de erros: " + parser.getErrorList().size() );
//            System.out.println("Análise sintática finalizada com sucesso.");

            if (parser.getErrorTable().size() == 0) {
                modelTokens.addRow(new String[]{"", "", "", ""});
            } else {
                for (int i=0;i<parser.getErrorTable().size();i++){
                    inserirErrosSintaticos((ParserException) parser.getErrorTable().get(i));
                }
            }

        } catch (ParserException ex) {
            System.out.println(ex.getErrorMessage());
//            System.out.println("Tamanho da tabela de erros: " + parser.getErrorList().size() );
        }
    }

    // limpa as tabelas;
    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        modelTokens.setRowCount(0);
        modelErros.setRowCount(0);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void btnDoAnaliseLexicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoAnaliseLexicaActionPerformed
        // TODO add your handling code here:
        jMenuItem2ActionPerformed(evt);
    }//GEN-LAST:event_btnDoAnaliseLexicaActionPerformed

    public static void main(String[] args) {
        Tela tela = new Tela();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDoAnaliseLexica;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
    private JTable tabelaToken;
    private JTable tabelaErros;
    private DefaultTableModel modelTokens;
    private DefaultTableModel modelErros;

    private void inserirErrosSintaticos(ParserException e) {
        String[] infos = new String[4];

        infos[0] = e.getDescricao();
        infos[1] = "";
        infos[2] = String.valueOf(e.getLinha());
        infos[3] = String.valueOf(e.getPosicao());

        modelErros.addRow(infos);
    }
}// FIM DA CLASSE Tela;

