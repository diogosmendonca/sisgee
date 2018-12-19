/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetrj.sisgee.view.convenio;

import br.cefetrj.sisgee.control.ConvenioServices;
import br.cefetrj.sisgee.control.EmpresaServices;
import br.cefetrj.sisgee.control.PessoaServices;
import br.cefetrj.sisgee.model.entity.Convenio;
import br.cefetrj.sisgee.model.entity.Empresa;
import br.cefetrj.sisgee.model.entity.Pessoa;
import br.cefetrj.sisgee.view.utils.ServletUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 * Servlet para buscar convenio, pelo nome ou numero
 *
 * @author Lucas Lima
 */
public class BuscarConvenioServlet extends HttpServlet {

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

        String nome = "";
        String numero = "";
        request.setAttribute("filtro", null);

        numero = request.getParameter("numeroConvenio");
        nome = request.getParameter("razaoSocial");

        String idEmpresa = "";
        request.setAttribute("selecao", null);

        Convenio convenio = null;
        Empresa empresa = null;
        List<Convenio> convenios = new ArrayList();
        List<Empresa> empresas = new ArrayList();
        List<Pessoa> pessoas = new ArrayList();

        boolean isValid = true;

        String msg = "";
        Logger lg = Logger.getLogger(BuscarConvenioServlet.class);

        /**
         * Buscar pelo numero do Convenio
         */
        if (numero != null) {
            if (!numero.equals("")) {
                try {
                    convenio = ConvenioServices.buscarConvenioByNumeroConvenio(numero.trim());
                } catch (Exception e) {
                    msg = messages.getString("br.cefetrj.sisgee.incluir_cadastro_empresa_servlet.msg_ocorreu_erro");
                    request.setAttribute("msg", msg);
                    lg.error("Exception ao tentar buscar um convenio pelo numero", e);
                    lg.info(msg);
                    request.getRequestDispatcher("/index.jsp").forward(request, response);
                }
            }
            if (convenio != null) {
                convenios.add(convenio);
            }
        } else {
            isValid = false;
        }

        /**
         * Buscar pelo nome da Empresa/Pessoa
         */
        if (nome != null) {
            if (!nome.equals("")) {
                try {
                    pessoas = PessoaServices.buscarPessoaByNomeList(nome.trim());
                    empresas = EmpresaServices.buscarEmpresaByNomeList(nome.trim());
                } catch (Exception e) {
                    msg = messages.getString("br.cefetrj.sisgee.incluir_cadastro_empresa_servlet.msg_ocorreu_erro");
                    request.setAttribute("msg", msg);
                    lg.error("Exception ao tentar buscar uma pessoa ou empresa pelo nome de Empresa ou Pessoa", e);
                    lg.info(msg);
                    request.getRequestDispatcher("/index.jsp").forward(request, response);
                }
                if (pessoas != null) {
                    for (Pessoa x : pessoas) {
                        try {
                            convenio = ConvenioServices.buscarConvenioByPessoa(x);
                        } catch (Exception e) {
                            msg = messages.getString("br.cefetrj.sisgee.incluir_cadastro_empresa_servlet.msg_ocorreu_erro");
                            request.setAttribute("msg", msg);
                            lg.error("Exception ao tentar buscar um convenio pela pessoa", e);
                            lg.info(msg);
                            request.getRequestDispatcher("/index.jsp").forward(request, response);
                        }
                        convenios.add(convenio);
                    }
                }
                if (empresas != null) {
                    for (Empresa x : empresas) {
                        try {
                            convenio = ConvenioServices.buscarConvenioByEmpresa(x);
                        } catch (Exception e) {
                            msg = messages.getString("br.cefetrj.sisgee.incluir_cadastro_empresa_servlet.msg_ocorreu_erro");
                            request.setAttribute("msg", msg);
                            lg.error("Exception ao tentar buscar um convenio pela empresa", e);
                            lg.info(msg);
                            request.getRequestDispatcher("/index.jsp").forward(request, response);
                        }
                        convenios.add(convenio);
                    }
                }
            } else {
                isValid = false;
            }
        } else {
            isValid = false;
        }

        if (!convenios.isEmpty()) {
            isValid = true;
            request.setAttribute("filtro", convenios);
        }

        /**
         * Se for valido dispacha para o mesmo form com a tabela de convenios
         * encontrado setada
         */
        if (isValid) {
            request.getRequestDispatcher("form_renovar_convenio.jsp").forward(request, response);
        } else {
            request.setAttribute("erroBuscar", "Não foi encontrado nenhum convênio com os parâmetros passados.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }

    }

}
