/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetrj.sisgee.view.convenio;

import br.cefetrj.sisgee.control.ConvenioServices;
import br.cefetrj.sisgee.model.entity.Convenio;
import br.cefetrj.sisgee.view.utils.ServletUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author Matheus Silva
 */
@WebServlet(name = "AtualizaConvenioServlet", urlPatterns = {"/AtualizaConvenioServlet"})
public class AtualizaConvenioServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Locale locale = ServletUtils.getLocale(request);
        ResourceBundle messages = ResourceBundle.getBundle("Messages", locale);

        String numero = (String) request.getAttribute("numeroAntigo");

        //Dados Pessoa Jurídica
        String cnpjEmpresa = request.getParameter("cnpjEmpresa");
        String nomeEmpresa = request.getParameter("nomeEmpresa");
        String agenteIntegracao = request.getParameter("agenteIntegracao");
        String numeroEmpresa = request.getParameter("numeroEmpresa");
        String anoEmpresa = request.getParameter("anoEmpresa");
        String dataRegistroConvenioEmpresa = request.getParameter("dataRegistroConvenioEmpresa");
        String emailEmpresa = request.getParameter("emailEmpresa");
        String telefoneEmpresa = request.getParameter("telefoneEmpresa");
        String contatoEmpresa = request.getParameter("contatoEmpresa");

        Boolean ehAgente = Boolean.parseBoolean(agenteIntegracao);

        //Dados Pessoa física
        String cpfPessoa = request.getParameter("cpfPessoa");
        String nomePessoa = request.getParameter("nomePessoa");
        String emailPessoa = request.getParameter("emailPessoa");
        String telefonePessoa = request.getParameter("telefonePessoa");
        String numeroPessoa = request.getParameter("numeroPessoa");
        String anoPessoa = request.getParameter("anoPessoa");
        String dataRegistroConvenioPessoa = request.getParameter("dataRegistroConvenioPessoa");

        Convenio convenio = ConvenioServices.buscarConvenioByNumeroConvenio(numero);
        String msg = "";
        Logger lg = Logger.getLogger(alterarConvenioServlet.class);
        try {
            if (convenio.getEmpresa() != null) {
                
                convenio.setNumero(numeroEmpresa);
                convenio.setAno(anoEmpresa);
                convenio.setNumeroConvenio();
                cnpjEmpresa = cnpjEmpresa.replaceAll("[.|/|-]", "");
                convenio.getEmpresa().setCnpjEmpresa(cnpjEmpresa);
                convenio.getEmpresa().setRazaoSocial(nomeEmpresa);
                convenio.getEmpresa().setAgenteIntegracao(ehAgente);
                convenio.getEmpresa().setContatoEmpresa(contatoEmpresa);
                convenio.getEmpresa().setTelefoneEmpresa(telefoneEmpresa);
                convenio.getEmpresa().setEmailEmpresa(emailEmpresa);
                convenio.setDataRegistro(new SimpleDateFormat("dd/MM/yyyy").parse(dataRegistroConvenioEmpresa));
            } else {
                convenio.setNumero(numeroPessoa);
                convenio.setAno(anoPessoa);
                convenio.setNumeroConvenio();
                cpfPessoa = cpfPessoa.replaceAll("[.|/|-]", "");
                convenio.getPessoa().setCpf(cpfPessoa);
                convenio.getPessoa().setNome(nomePessoa);
                convenio.getPessoa().setTelefone(telefonePessoa);
                convenio.getPessoa().setEmail(emailPessoa);
                convenio.setNumeroConvenio();
                convenio.setDataRegistro(new SimpleDateFormat("dd/MM/yyyy").parse(dataRegistroConvenioPessoa));
            }
        } catch (Exception e) {
            msg = messages.getString("br.cefetrj.sisgee.incluir_cadastro_empresa_servlet.msg_ocorreu_erro");
            request.setAttribute("msg", msg);
            lg.error("Exception ao tentar alterar um convenio", e);
            request.getRequestDispatcher("/form_renovar_convenio.jsp").forward(request, response);
            lg.info(msg);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
        try {
            ConvenioServices.alterarConvenio(convenio);
            msg = messages.getString("br.cefetrj.sisgee.incluir_cadastro_empresa_servlet.msg_convenio_alterado");
            request.setAttribute("msg", msg);
            request.setAttribute("numeroConvenioGerado", convenio.getNumeroConvenio());
            request.getRequestDispatcher("/form_empresa_sucesso.jsp").forward(request, response);
        } catch (Exception e) {
            msg = messages.getString("br.cefetrj.sisgee.incluir_cadastro_empresa_servlet.msg_ocorreu_erro");
            request.setAttribute("msg", msg);
            lg.error("Exception ao tentar alterar um convenio", e);
            request.getRequestDispatcher("/form_renovar_convenio.jsp").forward(request, response);
            lg.info(msg);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }
}
