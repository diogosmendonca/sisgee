/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetrj.sisgee.view.convenio;

import br.cefetrj.sisgee.control.ConvenioServices;
import br.cefetrj.sisgee.model.entity.Convenio;
import br.cefetrj.sisgee.view.utils.ServletUtils;
import br.cefetrj.sisgee.view.utils.ValidaUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet para validar dados do renovar convenio
 * 
 * @author Lucas Lima
 */
public class ValidaRenovarConvenio extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * Post para validar dados de renovar convenio
     * @param request um objeto HttpServletRequest que contém a solicitação feita pelo cliente do servlet.
     * @param response um objeto HttpServletResponse que contém a resposta que o servlet envia para o cliente
     * @throws ServletException se o pedido do service não puder ser tratado
     * @throws IOException se um erro de entrada ou saída for detectado quando o servlet manipula o pedido 
     */
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Locale locale = ServletUtils.getLocale(request);
        ResourceBundle messages = ResourceBundle.getBundle("Messages", locale);

        String emailPessoa = request.getParameter("emailPessoa");
        String telefonePessoa = request.getParameter("telefonePessoa");

        String dataAssinaturaConvenioEmpresa = request.getParameter("dataAssinaturaConvenioEmpresa");
        String dataAssinaturaConvenioPessoa = request.getParameter("dataAssinaturaConvenioPessoa");

        String emailEmpresa = request.getParameter("emailEmpresa");
        String telefoneEmpresa = request.getParameter("telefoneEmpresa");
        String contatoEmpresa = request.getParameter("contatoEmpresa");

        String numero = (String) request.getSession().getAttribute("numero");

        Convenio convenio = ConvenioServices.buscarConvenioByNumeroConvenio(numero);

        boolean isValid = true;

        if (convenio.getEmpresa() != null) {
            if (convenio.getEmpresa().isAgenteIntegracao()) {
                request.setAttribute("simAgenteIntegracao", "sim");
            } else {
                request.setAttribute("naoAgenteIntegracao", "sim");
            }
            request.setAttribute("cnpj", convenio.getEmpresa().getCnpjEmpresa());
            request.setAttribute("razao", convenio.getEmpresa().getRazaoSocial());
            /**
             * Validação do Email do Cadastro Empresa usando métodos da Classe
             * ValidaUtils. Campo obrigatório; Tamanho máximo de 50 caracteres;
             */
            String emailEmpresaMsg = "";
            emailEmpresaMsg = ValidaUtils.validaObrigatorio("emailEmpresa", emailEmpresa);
            if (emailEmpresaMsg.trim().isEmpty()) {
                emailEmpresaMsg = ValidaUtils.validaTamanho("emailEmpresa", 50, emailEmpresa);
                if (emailEmpresaMsg.trim().isEmpty()) {
                    emailEmpresaMsg = ValidaUtils.validaEmail("emailEmpresa", emailEmpresa);
                    if (emailEmpresaMsg.trim().isEmpty()) {
                        request.setAttribute("emailEmpresa", emailEmpresa);
                    } else {
                        emailEmpresaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                        request.setAttribute("emailEmpresaMsg", emailEmpresaMsg);
                        request.setAttribute("isEmpresa", "sim");
                        isValid = false;
                    }
                } else {
                    emailEmpresaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                    request.setAttribute("emailEmpresaMsg", emailEmpresaMsg);
                    request.setAttribute("isEmpresa", "sim");
                    isValid = false;
                }

            }

            /**
             * Validação do Telefone do Cadastro Empresa usando métodos da
             * Classe ValidaUtils. Campo obrigatório; Tamanho máximo de 11
             * caracteres;
             */
            String telefoneEmpresaMsg = "";
            telefoneEmpresaMsg = ValidaUtils.validaObrigatorio("telefoneEmpresa", telefoneEmpresa);
            if (telefoneEmpresaMsg.trim().isEmpty()) {
                telefoneEmpresaMsg = ValidaUtils.validaTamanho("telefoneEmpresa", 11, telefoneEmpresa);

                if (telefoneEmpresaMsg.trim().isEmpty()) {
                    telefoneEmpresa = telefoneEmpresa.replaceAll("[.|/|-]", "");
                    telefoneEmpresaMsg = ValidaUtils.validaInteger("telefoneEmpresa", telefoneEmpresa);
                    if (telefoneEmpresaMsg.trim().isEmpty()) {
                        telefoneEmpresaMsg = ValidaUtils.validaTelefone("telefoneEmpresa", telefoneEmpresa);
                        if (telefoneEmpresaMsg.trim().isEmpty()) {
                            request.setAttribute("telefoneEmpresa", telefoneEmpresa);
                        } else {
                            telefoneEmpresaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                            request.setAttribute("telefoneEmpresaMsg", telefoneEmpresaMsg);
                            request.setAttribute("isEmpresa", "sim");
                            isValid = false;
                        }
                    } else {
                        telefoneEmpresaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                        request.setAttribute("telefoneEmpresaMsg", telefoneEmpresaMsg);
                        request.setAttribute("isEmpresa", "sim");
                        isValid = false;

                    }

                } else {
                    telefoneEmpresaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                    request.setAttribute("telefoneEmpresaMsg", telefoneEmpresaMsg);
                    request.setAttribute("isEmpresa", "sim");
                    isValid = false;
                }

            }

            /**
             * Validação do contato com a Empresa do Cadastro Empresa usando
             * métodos da Classe ValidaUtils. Campo opcional; Tamanho máximo de
             * 50 caracteres;
             */
            String contatoEmpresaMsg = "";
            contatoEmpresaMsg = ValidaUtils.validaObrigatorio("contatoEmpresa", contatoEmpresa);
            if (contatoEmpresaMsg.trim().isEmpty()) {
                contatoEmpresaMsg = ValidaUtils.validaTamanho("contatoEmpresa", 50, contatoEmpresa);
                if (contatoEmpresaMsg.trim().isEmpty()) {
                    contatoEmpresaMsg = ValidaUtils.validaSomenteLetras("contatoEmpresa", contatoEmpresa);
                    if (contatoEmpresaMsg.trim().isEmpty()) {
                        request.setAttribute("contatoEmpresa", contatoEmpresa);

                    } else {
                        contatoEmpresaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                        request.setAttribute("contatoEmpresaMsg", contatoEmpresaMsg);
                        request.setAttribute("isEmpresa", "sim");
                        isValid = false;

                    }
                } else {
                    contatoEmpresaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                    request.setAttribute("contatoEmpresaMsg", contatoEmpresaMsg);
                    request.setAttribute("isEmpresa", "sim");
                    isValid = false;
                }

            }

            /**
             * Validação da Data de Assinatura do Convenio da Pessoa usando os
             * métodos da Classe ValidaUtils Campo obrigatório
             */
            Date dataAssinaturaEmpresa = null;
            String dataAssinaturaMsg = "";
            String campo = "Data de Assinatura";

            dataAssinaturaMsg = ValidaUtils.validaObrigatorio(campo, dataAssinaturaConvenioEmpresa);
            if (dataAssinaturaMsg.trim().isEmpty()) {
                dataAssinaturaMsg = ValidaUtils.validaDate(campo, dataAssinaturaConvenioEmpresa);
                if (dataAssinaturaMsg.trim().isEmpty()) {
                    dataAssinaturaMsg = ValidaUtils.validaTamanhoExato(campo, 10, dataAssinaturaConvenioEmpresa);
                    if (dataAssinaturaMsg.trim().isEmpty()) {
                        try {
                            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                            dataAssinaturaEmpresa = format.parse(dataAssinaturaConvenioEmpresa);
                            request.setAttribute("dataAssinaturaConvenioEmpresa", dataAssinaturaEmpresa);
                        } catch (Exception e) {
                            //TODO trocar saída de console por Log
                            System.out.println("Data em formato incorreto, mesmo após validação na classe ValidaUtils");
                            request.setAttribute("isEmpresa", "sim");
                            isValid = false;
                        }
                    } else {
                        dataAssinaturaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                        request.setAttribute("dataAssinaturaEmpresaMsg", dataAssinaturaMsg);
                        request.setAttribute("isEmpresa", "sim");
                        isValid = false;
                        //TODO Fazer log
                        System.out.println(dataAssinaturaMsg);

                    }

                } else {
                    dataAssinaturaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                    request.setAttribute("dataAssinaturaEmpresaMsg", dataAssinaturaMsg);
                    request.setAttribute("isEmpresa", "sim");
                    isValid = false;
                    //TODO Fazer log
                    System.out.println(dataAssinaturaMsg);
                }
            } else {
                dataAssinaturaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                request.setAttribute("dataAssinaturaEmpresaMsg", dataAssinaturaMsg);
                request.setAttribute("isEmpresa", "sim");
                isValid = false;
                //TODO Fazer log
                System.out.println(dataAssinaturaMsg);
            }

        } else {

            request.setAttribute("cpf", convenio.getPessoa().getCpf());
            request.setAttribute("nome", convenio.getPessoa().getNome());

            /**
             * Validação do email da Pessoa Cadastro Pessoa Fisica usando
             * métodos da Classe ValidaUtils. Campo opcional; Tamanho máximo de
             * 50 caracteres;
             */
            String emailPessoaMsg = "";
            emailPessoaMsg = ValidaUtils.validaObrigatorio("emailPessoa", emailPessoa);
            if (emailPessoaMsg.trim().isEmpty()) {
                emailPessoaMsg = ValidaUtils.validaTamanho("emailPessoa", 50, emailPessoa);
                if (emailPessoaMsg.trim().isEmpty()) {
                    emailPessoaMsg = ValidaUtils.validaEmail("emailPessoa", emailPessoa);
                    if (emailPessoaMsg.trim().isEmpty()) {
                        request.setAttribute("emailPessoa", emailPessoa);
                    } else {
                        emailPessoaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                        request.setAttribute("emailPessoaMsg", emailPessoaMsg);
                        request.setAttribute("isPessoa", "sim");
                        isValid = false;
                    }
                } else {
                    emailPessoaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                    request.setAttribute("emailPessoaMsg", emailPessoaMsg);
                    request.setAttribute("isPessoa", "sim");
                    isValid = false;
                }

            }

            /**
             * Validação do telefone da Pessoa Cadastro Pessoa Fisica usando
             * métodos da Classe ValidaUtils. Campo opcional; Tamanho máximo de
             * 11 caracteres;
             */
            String telefonePessoaMsg = "";
            telefonePessoaMsg = ValidaUtils.validaObrigatorio("telefonePessoa", telefonePessoa);
            if (telefonePessoaMsg.trim().isEmpty()) {
                telefonePessoaMsg = ValidaUtils.validaTamanho("telefonePessoa", 11, telefonePessoa);
                if (telefonePessoaMsg.trim().isEmpty()) {

                    telefonePessoa = telefonePessoa.replaceAll("[.|/|-]", "");
                    telefonePessoaMsg = ValidaUtils.validaInteger("telefonePessoa", telefonePessoa);
                    if (telefonePessoaMsg.trim().isEmpty()) {
                        telefonePessoaMsg = ValidaUtils.validaTelefone("telefonePessoa", telefonePessoa);
                        if (telefonePessoaMsg.trim().isEmpty()) {
                            request.setAttribute("telefonePessoa", telefonePessoa);
                        } else {
                            telefonePessoaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                            request.setAttribute("telefonePessoaMsg", telefonePessoaMsg);
                            request.setAttribute("isPessoa", "sim");
                            isValid = false;
                        }
                    } else {
                        telefonePessoaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                        request.setAttribute("telefonePessoaMsg", telefonePessoaMsg);
                        request.setAttribute("isPessoa", "sim");
                        isValid = false;

                    }
                } else {
                    telefonePessoaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                    request.setAttribute("telefonePessoaMsg", telefonePessoaMsg);
                    request.setAttribute("isPessoa", "sim");
                    isValid = false;
                }

            }

            /**
             * Validação da Data de Assinatura do Convenio da Pessoa usando os
             * métodos da Classe ValidaUtils Campo obrigatório
             */
            Date dataAssinaturaPessoa = null;
            String dataAssinaturaMsg = "";
            String campo = "Data de Assinatura";

            dataAssinaturaMsg = ValidaUtils.validaObrigatorio(campo, dataAssinaturaConvenioPessoa);
            if (dataAssinaturaMsg.trim().isEmpty()) {
                dataAssinaturaMsg = ValidaUtils.validaDate(campo, dataAssinaturaConvenioPessoa);
                if (dataAssinaturaMsg.trim().isEmpty()) {
                    dataAssinaturaMsg = ValidaUtils.validaTamanhoExato(campo, 10, dataAssinaturaConvenioPessoa);
                    if (dataAssinaturaMsg.trim().isEmpty()) {
                        try {
                            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                            dataAssinaturaPessoa = format.parse(dataAssinaturaConvenioPessoa);
                            request.setAttribute("dataAssinaturaConvenioPessoa", dataAssinaturaPessoa);
                        } catch (Exception e) {
                            //TODO trocar saída de console por Log
                            System.out.println("Data em formato incorreto, mesmo após validação na classe ValidaUtils");
                            request.setAttribute("isPessoa", "sim");
                            isValid = false;
                        }
                    } else {
                        dataAssinaturaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                        request.setAttribute("dataAssinaturaPessoaMsg", dataAssinaturaMsg);
                        request.setAttribute("isPessoa", "sim");
                        isValid = false;
                        //TODO Fazer log
                        System.out.println(dataAssinaturaMsg);
                    }

                } else {
                    dataAssinaturaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                    request.setAttribute("dataAssinaturaPessoaMsg", dataAssinaturaMsg);
                    request.setAttribute("isPessoa", "sim");
                    isValid = false;
                    //TODO Fazer log
                    System.out.println(dataAssinaturaMsg);
                }
            } else {
                dataAssinaturaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                request.setAttribute("dataAssinaturaPessoaMsg", dataAssinaturaMsg);
                request.setAttribute("isPessoa", "sim");
                isValid = false;
                //TODO Fazer log
                System.out.println(dataAssinaturaMsg);
            }
        }

        /**
         * Teste das variáveis booleanas após validação. Redirecionamento para a
         * inclusão ou devolver para o formulário com as mensagens.
         */
        if (isValid) {
            request.getRequestDispatcher("/alterarConvenioServlet").forward(request, response);

        } else {
            String msg = messages.getString("br.cefetrj.sisgee.valida_cadastro_empresa_servlet.msg_atencao");
            request.setAttribute("msg", msg);
            request.getRequestDispatcher("/form_renovar_convenio2.jsp").forward(request, response);

        }

    }

}
