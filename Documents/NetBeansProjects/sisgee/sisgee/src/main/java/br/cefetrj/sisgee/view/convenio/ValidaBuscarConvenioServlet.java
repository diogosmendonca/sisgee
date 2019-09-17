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
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Valida dados do ao buscar um convenio, numero ou nome
 *
 * @author Lucas Lima
 */
public class ValidaBuscarConvenioServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     *
     * @param request um objeto HttpServletRequest que contém a solicitação
     * feita pelo cliente do servlet.
     * @param response um objeto HttpServletResponse que contém a resposta que o
     * servlet envia para o cliente
     * @throws ServletException se o pedido do service não puder ser tratado
     * @throws IOException se um erro de entrada ou saída for detectado quando o
     * servlet manipula o pedido
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Locale locale = ServletUtils.getLocale(request);
        ResourceBundle messages = ResourceBundle.getBundle("Messages", locale);

        String numero = request.getParameter("numeroConvenio");
        String nome = request.getParameter("razaoSocial");

        boolean isValid = true;
        Integer tamanho = 0;

        if (numero != null) {

            if (numero.isEmpty()) {
                String numeroConvenioMsg = "";
                numeroConvenioMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                request.setAttribute("numeroConvenioMsg", numeroConvenioMsg);
                isValid = false;
            }
            /**
             * Validação do numero do Convenio usando métodos da Classe
             * ValidaUtils. Campo opcional; Tamanho máximo de 5 caracteres;
             */
            String numeroConvenioMsg = "";
            numeroConvenioMsg = ValidaUtils.validaObrigatorio("numerConvenio", numero);
            if (numeroConvenioMsg.trim().isEmpty()) {
                numeroConvenioMsg = ValidaUtils.validaTamanho("numerConvenio", 6, numero);
                if (numeroConvenioMsg.trim().isEmpty()) {

                    numeroConvenioMsg = ValidaUtils.validaInteger("numeroConvenio", numero);
                    if (numeroConvenioMsg.trim().isEmpty()) {
                        request.setAttribute("numeroConvenio", numero);
                    } else {
                        numeroConvenioMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                        request.setAttribute("numeroConvenioMsg", numeroConvenioMsg);
                        isValid = false;

                    }
                } else {
                    numeroConvenioMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                    request.setAttribute("numeroConvenioMsg", numeroConvenioMsg);
                    isValid = false;
                }

            }
        } else {
            /**
             * Validação do nome da pessoa ou empresa usando métodos da Classe
             * ValidaUtils. Campo opcional; Tamanho máximo de 100 caracteres;
             */
            String nomeMsg = "";
            nomeMsg = ValidaUtils.validaObrigatorio("Razão Social", nome);
            if (nomeMsg.trim().isEmpty()) {
                nomeMsg = ValidaUtils.validaTamanho("Razão Social", 100, nome);

                if (nomeMsg.trim().isEmpty()) {
                    nomeMsg = ValidaUtils.validaInteger("Razão Social", nome);
                    if (nomeMsg.trim().isEmpty()) {
                        nomeMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                        request.setAttribute("nomeMsg", nomeMsg);
                        isValid = false;

                    } else {
                        request.setAttribute("razaoSocial", nome);

                    }

                } else {
                    nomeMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                    nomeMsg = ServletUtils.mensagemFormatada(nomeMsg, locale, tamanho);
                    request.setAttribute("nomeMsg", nomeMsg);
                    isValid = false;
                }
            } else {
                nomeMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                request.setAttribute("nomeMsg", nomeMsg);
                isValid = false;
            }

        }

        /**
         * Teste das variáveis booleanas após validação. Redirecionamento para a
         * inclusão ou devolver para o formulário com as mensagens.
         */
        if (isValid) {
            request.getRequestDispatcher("/BuscarConvenioServlet").forward(request, response);

        } else {
            String msg = messages.getString("br.cefetrj.sisgee.valida_cadastro_empresa_servlet.msg_atencao");
            request.setAttribute("msg", msg);

            request.getRequestDispatcher("/form_renovar_convenio.jsp").forward(request, response);

        }
    }

}
