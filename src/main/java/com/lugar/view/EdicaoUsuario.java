/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.lugar.view;

import com.lugar.confeitaria.Util;
import com.lugar.controller.OperacoesCliente;
import com.lugar.controller.OperacoesUsuario;
import com.lugar.model.Cliente;
import com.lugar.model.Endereco;
import com.lugar.model.Funcionario;
import com.lugar.model.PessoaFisica;
import com.lugar.model.PessoaJuridica;
import com.lugar.model.SetStringString;
import com.lugar.model.Usuario;
import com.lugar.model.exceptions.ExcecaoCampoInvalido;
import java.time.LocalDate;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author lugar
 */
public class EdicaoUsuario extends javax.swing.JDialog {

    private int id;
    private java.awt.Frame pai;
    private Usuario usuario;
    private boolean isPessoaF;
    private boolean funcionario;
    private OperacoesUsuario operacoesUsuario;

    public EdicaoUsuario(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public EdicaoUsuario(java.awt.Frame parent, boolean modal, int id) {
        super(parent, modal);
        this.id = id;
        this.pai = parent;
        this.operacoesUsuario = new OperacoesUsuario();
        this.usuario = operacoesUsuario.busca(id);
        this.isPessoaF = usuario instanceof PessoaFisica;
        this.funcionario = usuario.isAdmin();

        initComponents();

        //Preenchendo infos
        campoNome.setText(usuario.getNome());
        campoNomeUsuario.setText(usuario.getNomeUsuario());
        campoEmail.setText(usuario.getEmail());
        campoTelefone.setText(usuario.getTelefone());
        Endereco endereco = usuario.getEndereco();
        campoLogradouro.setText(endereco.getLogradouro());
        campoNumero.setText(endereco.getNumero());
        campoComplemento.setText(endereco.getComplemento());
        campoBairro.setText(endereco.getBairro());
        campoCidade.setText(endereco.getCidade());
        campoCep.setText(endereco.getCep());
        campoIdentificador.setText(usuario.getIdentificador());
        campoUf.setSelectedItem(endereco.getUf());
        campoSenha.setText(usuario.getSenhaHash());

    }

    private void editaUsuario() {
        String nome = campoNome.getText().trim();
        String NomeUsuario = campoNomeUsuario.getText().trim();
        String email = campoEmail.getText().trim();
        String telefone = campoTelefone.getText().trim();
        Endereco endereco = usuario.getEndereco();
        String identificador = campoIdentificador.getText().trim();
        String senha = String.valueOf(campoSenha.getPassword()).trim();
        String cartao = campoCartao.getText().trim();
        String dataNascimento = campoDataDeNascimento.getText().trim();
        String matricula = campoMatricula.getText().trim();
        String funcao = campoFuncaoFuncionario.getText().trim();
        String razao = campoRazaoSocial.getText().trim();

        if (validaDados(nome, NomeUsuario, senha, email, telefone, cartao, identificador, endereco, dataNascimento, matricula, funcao, razao)) {
            boolean confirmacao = JOptionPane.showConfirmDialog(null, "Deseja confirmar a edição do perfil?", "Edição de Perfil", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == 0;
            if (confirmacao) {
                int resultado = Util.RETORNO_SUCESSO;
//                if (usuario.isAdmin()) {
//                    Funcionario pFuncionario = new Funcionario();
//                    resultado = this.operacoesFuncionario.atualiza(Pfuncionario);
//                } else {
//                    if (isPessoaF) {
//                        PessoaFisica pessoa = new PessoaFisica();
//                        resultado = this.operacoesPessoaFisica.atualiza(pessoa);
//                    } else {
//                        PessoaJuridica pessoa = new PessoaJuridica();
//                        resultado = this.operacoesPessoaJuridica.atualiza(pessoa);
//                    }
//                }
                if (resultado == Util.RETORNO_SUCESSO) {
                    JOptionPane.showMessageDialog(this.pai, "Edição realizada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this.pai, "Infelizmente não foi possivel editar, tente novamente mais tarde!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private boolean validaDados(String nomeForm,
            String nomeUsuarioForm,
            String senhaForm,
            String emailForm,
            String telefoneForm,
            String cartaoForm,
            String identificadorForm,
            Endereco endereco,
            String dataNascimentoForm,
            String matriculaForm,
            String funcaoForm,
            String razaoForm
    ) {
        String logradouro = endereco.getLogradouro();
        String numero = endereco.getNumero();
        String complemento = endereco.getComplemento();
        String bairro = endereco.getBairro();
        String cidade = endereco.getCidade();
        String cep = endereco.getCep();

        //PARA OS CASOS ESPECIFICOS
        if (!usuario.isAdmin()) {
            matriculaForm = "00";
            funcaoForm = "00";
            if (isPessoaF) {
                razaoForm = "00";
            } else {
                dataNascimentoForm = "00";
            }
        } else {
            cartaoForm = "0000000000000000";
            razaoForm = "00";
            dataNascimentoForm = "00";
        }

        if (nomeForm.isBlank() || nomeUsuarioForm.isBlank()
                || senhaForm.isBlank() || emailForm.isBlank()
                || telefoneForm.isBlank() || cartaoForm.isBlank()
                || identificadorForm.isBlank() || logradouro.isBlank()
                || numero.isBlank() || complemento.isBlank() || bairro.isBlank()
                || cidade.isBlank() || cep.isBlank() || matriculaForm.isBlank()
                || funcaoForm.isBlank() || razaoForm.isBlank() || dataNascimentoForm.isBlank()) {
            JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios");
            return false;
        } else if (nomeUsuarioForm.contains(" ")
                || senhaForm.contains(" ")
                || emailForm.contains(" ")
                || cartaoForm.contains(" ")) {
            JOptionPane.showMessageDialog(null, "Os campos nome de usuário, senha, email e cartão não devem conter espaço");
            return false;
        } else if (cartaoForm.length() != 16) {
            JOptionPane.showMessageDialog(null, "Numero de cartao invalido");
            return false;
        }
        /*
        TODO tratar CNPJ e CPF
        else if (identificadorForm.length() != 11) {
            JOptionPane.showMessageDialog(null, "Numero de cartao invalido");
            return 3;}*/
//        else {
//            return 0;
////        }
//        }
        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        botoesTipoPessoa = new javax.swing.ButtonGroup();
        painelFormulario = new javax.swing.JPanel();
        titulo = new javax.swing.JLabel();
        painelCampos = new javax.swing.JPanel();
        textoNome = new javax.swing.JLabel();
        campoNome = new javax.swing.JFormattedTextField();
        textoSenha = new javax.swing.JLabel();
        campoSenha = new javax.swing.JPasswordField();
        textoNomeUsuario = new javax.swing.JLabel();
        campoNomeUsuario = new javax.swing.JFormattedTextField();
        textoEmail = new javax.swing.JLabel();
        campoEmail = new javax.swing.JFormattedTextField();
        textoTelefone = new javax.swing.JLabel();
        campoTelefone = new javax.swing.JFormattedTextField();
        textoIdentificador = new javax.swing.JLabel();
        textoCartaoCredito = new javax.swing.JLabel();
        campoCartao = new javax.swing.JFormattedTextField();
        campoIdentificador = new javax.swing.JFormattedTextField();
        painelCamposEndereco = new javax.swing.JPanel();
        textoLogradouro = new javax.swing.JLabel();
        campoLogradouro = new javax.swing.JFormattedTextField();
        textoNumero = new javax.swing.JLabel();
        campoNumero = new javax.swing.JFormattedTextField();
        textoComplemento = new javax.swing.JLabel();
        campoComplemento = new javax.swing.JFormattedTextField();
        textoBairro = new javax.swing.JLabel();
        campoBairro = new javax.swing.JFormattedTextField();
        textoCidade = new javax.swing.JLabel();
        campoCidade = new javax.swing.JFormattedTextField();
        textoUf = new javax.swing.JLabel();
        campoUf = new javax.swing.JComboBox<>();
        textoCep = new javax.swing.JLabel();
        campoCep = new javax.swing.JFormattedTextField();
        painelCamposEspecificos = new javax.swing.JPanel();
        painelPessoaJuridica = new javax.swing.JPanel();
        textoRazaoSocial = new javax.swing.JLabel();
        campoRazaoSocial = new javax.swing.JFormattedTextField();
        painelFuncionario = new javax.swing.JPanel();
        textoMatricula = new javax.swing.JLabel();
        campoMatricula = new javax.swing.JFormattedTextField();
        campoFuncaoFuncionario = new javax.swing.JFormattedTextField();
        textoFuncaoFuncionario = new javax.swing.JLabel();
        painelPessoaFisica = new javax.swing.JPanel();
        textoDataDeNascimento = new javax.swing.JLabel();
        campoDataDeNascimento = new javax.swing.JFormattedTextField();
        painelBotoes = new javax.swing.JPanel();
        botaoVoltar = new javax.swing.JButton();
        botaoEditar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("PERFIL");
        setMinimumSize(new java.awt.Dimension(500, 660));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        painelFormulario.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        painelFormulario.setMinimumSize(new java.awt.Dimension(550, 600));
        painelFormulario.setPreferredSize(new java.awt.Dimension(550, 600));
        painelFormulario.setLayout(new java.awt.GridBagLayout());

        titulo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo.setText("PERFIL");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
        painelFormulario.add(titulo, gridBagConstraints);

        painelCampos.setMinimumSize(new java.awt.Dimension(500, 510));
        painelCampos.setOpaque(false);
        painelCampos.setPreferredSize(new java.awt.Dimension(500, 700));
        painelCampos.setRequestFocusEnabled(false);
        painelCampos.setLayout(new java.awt.GridBagLayout());

        textoNome.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        textoNome.setText("Nome:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        painelCampos.add(textoNome, gridBagConstraints);

        campoNome.setPreferredSize(new java.awt.Dimension(200, 22));
        campoNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoNomeActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        painelCampos.add(campoNome, gridBagConstraints);

        textoSenha.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        textoSenha.setText("Senha:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        painelCampos.add(textoSenha, gridBagConstraints);

        campoSenha.setMinimumSize(new java.awt.Dimension(150, 24));
        campoSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoSenhaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        painelCampos.add(campoSenha, gridBagConstraints);

        textoNomeUsuario.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        textoNomeUsuario.setText("Nome de Usuário:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        painelCampos.add(textoNomeUsuario, gridBagConstraints);

        campoNomeUsuario.setPreferredSize(new java.awt.Dimension(200, 22));
        campoNomeUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoNomeUsuarioActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        painelCampos.add(campoNomeUsuario, gridBagConstraints);

        textoEmail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        textoEmail.setText("E-mail:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        painelCampos.add(textoEmail, gridBagConstraints);

        campoEmail.setPreferredSize(new java.awt.Dimension(200, 22));
        campoEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoEmailActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        painelCampos.add(campoEmail, gridBagConstraints);

        textoTelefone.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        textoTelefone.setText("Telefone");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        painelCampos.add(textoTelefone, gridBagConstraints);

        campoTelefone.setPreferredSize(new java.awt.Dimension(200, 22));
        campoTelefone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoTelefoneActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        painelCampos.add(campoTelefone, gridBagConstraints);

        textoIdentificador.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        textoIdentificador.setText("CPF / CNPJ:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        painelCampos.add(textoIdentificador, gridBagConstraints);

        textoCartaoCredito.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        textoCartaoCredito.setText("Cartão de Crédito:");
        if(!usuario.isAdmin()){
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 6;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.ipadx = 30;
            gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
            painelCampos.add(textoCartaoCredito, gridBagConstraints);
        }

        campoCartao.setPreferredSize(new java.awt.Dimension(200, 22));
        campoCartao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoCartaoActionPerformed(evt);
            }
        });
        if(!usuario.isAdmin()){
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 1;
            gridBagConstraints.gridy = 6;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
            painelCampos.add(campoCartao, gridBagConstraints);
        }

        campoIdentificador.setPreferredSize(new java.awt.Dimension(200, 22));
        campoIdentificador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoIdentificadorActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        painelCampos.add(campoIdentificador, gridBagConstraints);

        painelCamposEndereco.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Endereço"));
        painelCamposEndereco.setLayout(new java.awt.GridBagLayout());

        textoLogradouro.setText("Logradouro");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        painelCamposEndereco.add(textoLogradouro, gridBagConstraints);

        campoLogradouro.setPreferredSize(new java.awt.Dimension(170, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        painelCamposEndereco.add(campoLogradouro, gridBagConstraints);

        textoNumero.setText("Número");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        painelCamposEndereco.add(textoNumero, gridBagConstraints);

        campoNumero.setText("S/N");
        campoNumero.setPreferredSize(new java.awt.Dimension(170, 22));
        campoNumero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoNumeroActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        painelCamposEndereco.add(campoNumero, gridBagConstraints);

        textoComplemento.setText("Complemento");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        painelCamposEndereco.add(textoComplemento, gridBagConstraints);

        campoComplemento.setMinimumSize(new java.awt.Dimension(100, 24));
        campoComplemento.setPreferredSize(new java.awt.Dimension(170, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        painelCamposEndereco.add(campoComplemento, gridBagConstraints);

        textoBairro.setText("Bairro");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        painelCamposEndereco.add(textoBairro, gridBagConstraints);

        campoBairro.setPreferredSize(new java.awt.Dimension(170, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        painelCamposEndereco.add(campoBairro, gridBagConstraints);

        textoCidade.setText("Cidade");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        painelCamposEndereco.add(textoCidade, gridBagConstraints);

        campoCidade.setPreferredSize(new java.awt.Dimension(170, 22));
        campoCidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoCidadeActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        painelCamposEndereco.add(campoCidade, gridBagConstraints);

        textoUf.setText("UF");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        painelCamposEndereco.add(textoUf, gridBagConstraints);

        campoUf.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
        campoUf.setSelectedIndex(12);
        campoUf.setToolTipText("");
        campoUf.setMinimumSize(new java.awt.Dimension(100, 24));
        campoUf.setPreferredSize(new java.awt.Dimension(170, 22));
        campoUf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoUfActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        painelCamposEndereco.add(campoUf, gridBagConstraints);

        textoCep.setText("CEP");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        painelCamposEndereco.add(textoCep, gridBagConstraints);

        campoCep.setPreferredSize(new java.awt.Dimension(170, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        painelCamposEndereco.add(campoCep, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 5, 10);
        painelCampos.add(painelCamposEndereco, gridBagConstraints);
        painelCamposEndereco.getAccessibleContext().setAccessibleDescription("");

        if(!usuario.isAdmin()){
            painelCamposEspecificos.setBorder(javax.swing.BorderFactory.createTitledBorder("Cliente"));
        }else{
            painelCamposEspecificos.setBorder(javax.swing.BorderFactory.createTitledBorder("Funcionário"));
        }
        painelCamposEspecificos.setMinimumSize(new java.awt.Dimension(350, 58));
        painelCamposEspecificos.setPreferredSize(new java.awt.Dimension(350, 100));
        painelCamposEspecificos.setLayout(new java.awt.GridBagLayout());

        painelPessoaJuridica.setMinimumSize(new java.awt.Dimension(300, 29));
        painelPessoaJuridica.setLayout(new java.awt.GridBagLayout());

        textoRazaoSocial.setText("Razão Social");
        textoRazaoSocial.setMinimumSize(new java.awt.Dimension(100, 18));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        painelPessoaJuridica.add(textoRazaoSocial, gridBagConstraints);

        campoRazaoSocial.setMinimumSize(new java.awt.Dimension(90, 24));
        campoRazaoSocial.setPreferredSize(new java.awt.Dimension(200, 24));
        campoRazaoSocial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoRazaoSocialActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        painelPessoaJuridica.add(campoRazaoSocial, gridBagConstraints);

        if(!isPessoaF && !usuario.isAdmin()){
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            painelCamposEspecificos.add(painelPessoaJuridica, gridBagConstraints);
        }

        painelFuncionario.setMinimumSize(new java.awt.Dimension(300, 58));
        painelFuncionario.setPreferredSize(new java.awt.Dimension(450, 100));
        painelFuncionario.setLayout(new java.awt.GridBagLayout());

        textoMatricula.setText("Matricula");
        textoMatricula.setMinimumSize(new java.awt.Dimension(100, 18));
        textoMatricula.setPreferredSize(new java.awt.Dimension(100, 18));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        painelFuncionario.add(textoMatricula, gridBagConstraints);

        campoMatricula.setMinimumSize(new java.awt.Dimension(100, 24));
        campoMatricula.setPreferredSize(new java.awt.Dimension(200, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        painelFuncionario.add(campoMatricula, gridBagConstraints);

        campoFuncaoFuncionario.setMinimumSize(new java.awt.Dimension(200, 24));
        campoFuncaoFuncionario.setPreferredSize(new java.awt.Dimension(200, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        painelFuncionario.add(campoFuncaoFuncionario, gridBagConstraints);

        textoFuncaoFuncionario.setText("Função");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        painelFuncionario.add(textoFuncaoFuncionario, gridBagConstraints);

        if(funcionario){

            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 2;
            painelCamposEspecificos.add(painelFuncionario, gridBagConstraints);
        }

        painelPessoaFisica.setMinimumSize(new java.awt.Dimension(300, 29));
        painelPessoaFisica.setLayout(new java.awt.GridBagLayout());

        textoDataDeNascimento.setText("Data de Nascimento");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        painelPessoaFisica.add(textoDataDeNascimento, gridBagConstraints);

        campoDataDeNascimento.setMinimumSize(new java.awt.Dimension(140, 24));
        campoDataDeNascimento.setPreferredSize(new java.awt.Dimension(170, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        painelPessoaFisica.add(campoDataDeNascimento, gridBagConstraints);

        if(isPessoaF && !usuario.isAdmin()){
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 1;
            painelCamposEspecificos.add(painelPessoaFisica, gridBagConstraints);
        }

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        painelCampos.add(painelCamposEspecificos, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        painelFormulario.add(painelCampos, gridBagConstraints);

        painelBotoes.setLayout(new java.awt.GridBagLayout());

        botaoVoltar.setText("Voltar");
        botaoVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoVoltarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 5);
        painelBotoes.add(botaoVoltar, gridBagConstraints);

        botaoEditar.setText("Editar");
        botaoEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoEditarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 0, 0);
        painelBotoes.add(botaoEditar, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 0);
        painelFormulario.add(painelBotoes, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        getContentPane().add(painelFormulario, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void campoNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoNomeActionPerformed

    private void campoSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoSenhaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoSenhaActionPerformed

    private void campoNomeUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoNomeUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoNomeUsuarioActionPerformed

    private void campoEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoEmailActionPerformed

    private void campoIdentificadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoIdentificadorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoIdentificadorActionPerformed

    private void campoTelefoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoTelefoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoTelefoneActionPerformed

    private void campoCartaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoCartaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoCartaoActionPerformed

    private void campoCidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoCidadeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoCidadeActionPerformed

    private void campoUfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoUfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoUfActionPerformed

    private void campoNumeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoNumeroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoNumeroActionPerformed

    private void botaoVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoVoltarActionPerformed
        this.dispose();
    }//GEN-LAST:event_botaoVoltarActionPerformed

    private void campoRazaoSocialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoRazaoSocialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoRazaoSocialActionPerformed

    private void botaoEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoEditarActionPerformed
        this.editaUsuario();
    }//GEN-LAST:event_botaoEditarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EdicaoUsuario.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EdicaoUsuario.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EdicaoUsuario.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EdicaoUsuario.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                EdicaoUsuario dialog = new EdicaoUsuario(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoEditar;
    private javax.swing.JButton botaoVoltar;
    private javax.swing.ButtonGroup botoesTipoPessoa;
    private javax.swing.JFormattedTextField campoBairro;
    private javax.swing.JFormattedTextField campoCartao;
    private javax.swing.JFormattedTextField campoCep;
    private javax.swing.JFormattedTextField campoCidade;
    private javax.swing.JFormattedTextField campoComplemento;
    private javax.swing.JFormattedTextField campoDataDeNascimento;
    private javax.swing.JFormattedTextField campoEmail;
    private javax.swing.JFormattedTextField campoFuncaoFuncionario;
    private javax.swing.JFormattedTextField campoIdentificador;
    private javax.swing.JFormattedTextField campoLogradouro;
    private javax.swing.JFormattedTextField campoMatricula;
    private javax.swing.JFormattedTextField campoNome;
    private javax.swing.JFormattedTextField campoNomeUsuario;
    private javax.swing.JFormattedTextField campoNumero;
    private javax.swing.JFormattedTextField campoRazaoSocial;
    private javax.swing.JPasswordField campoSenha;
    private javax.swing.JFormattedTextField campoTelefone;
    private javax.swing.JComboBox<String> campoUf;
    private javax.swing.JPanel painelBotoes;
    private javax.swing.JPanel painelCampos;
    private javax.swing.JPanel painelCamposEndereco;
    private javax.swing.JPanel painelCamposEspecificos;
    private javax.swing.JPanel painelFormulario;
    private javax.swing.JPanel painelFuncionario;
    private javax.swing.JPanel painelPessoaFisica;
    private javax.swing.JPanel painelPessoaJuridica;
    private javax.swing.JLabel textoBairro;
    private javax.swing.JLabel textoCartaoCredito;
    private javax.swing.JLabel textoCep;
    private javax.swing.JLabel textoCidade;
    private javax.swing.JLabel textoComplemento;
    private javax.swing.JLabel textoDataDeNascimento;
    private javax.swing.JLabel textoEmail;
    private javax.swing.JLabel textoFuncaoFuncionario;
    private javax.swing.JLabel textoIdentificador;
    private javax.swing.JLabel textoLogradouro;
    private javax.swing.JLabel textoMatricula;
    private javax.swing.JLabel textoNome;
    private javax.swing.JLabel textoNomeUsuario;
    private javax.swing.JLabel textoNumero;
    private javax.swing.JLabel textoRazaoSocial;
    private javax.swing.JLabel textoSenha;
    private javax.swing.JLabel textoTelefone;
    private javax.swing.JLabel textoUf;
    private javax.swing.JLabel titulo;
    // End of variables declaration//GEN-END:variables
}