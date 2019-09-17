package br.cefetrj.sisgee.view.empresa_agente;

import java.io.IOException;
import java.util.ResourceBundle;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import br.cefetrj.sisgee.control.AgenteIntegracaoServices;
import br.cefetrj.sisgee.control.ConvenioServices;
import br.cefetrj.sisgee.control.EmpresaServices;
import br.cefetrj.sisgee.control.PessoaServices;
import br.cefetrj.sisgee.model.entity.AgenteIntegracao;
import br.cefetrj.sisgee.model.entity.Convenio;
import br.cefetrj.sisgee.model.entity.Empresa;
import br.cefetrj.sisgee.model.entity.Pessoa;
import br.cefetrj.sisgee.view.utils.ServletUtils;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Servlet responsável por incluir cadastro de empresa
 *
 * @author Matheus
 */
@WebServlet("/IncluirCadastroEmpresaServlet")
public class IncluirCadastroEmpresaServlet extends HttpServlet {

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
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Locale locale = ServletUtils.getLocale(request);
        ResourceBundle messages = ResourceBundle.getBundle("Messages", locale);
        String tipoPessoa = request.getParameter("tipoPessoa");
        boolean pessoaJuridica = true;
        if (tipoPessoa.equals("nao")) {
            pessoaJuridica = false;
        }
        //Dados Pessoa Jurídica
        String cnpjEmpresa = request.getParameter("cnpjEmpresa");
        String nomeEmpresa = request.getParameter("nomeEmpresa");
        String agenteIntegracao = request.getParameter("agenteIntegracao");
        String numeroEmpresa = request.getParameter("numeroEmpresa");
        String anoEmpresa = request.getParameter("anoEmpresa");
        String dataAssinaturaConvenioEmpresa = request.getParameter("dataRegistroConvenioEmpresa");
        String emailEmpresa = request.getParameter("emailEmpresa");
        String telefoneEmpresa = request.getParameter("telefoneEmpresa");
        String contatoEmpresa = request.getParameter("contatoEmpresa");

        //Dados Pessoa física
        String cpfPessoa = request.getParameter("cpfPessoa");
        String nomePessoa = request.getParameter("nomePessoa");
        String emailPessoa = request.getParameter("emailPessoa");
        String telefonePessoa = request.getParameter("telefonePessoa");
        String numeroPessoa = request.getParameter("numeroPessoa");
        String anoPessoa = request.getParameter("anoPessoa");
        String dataAssinaturaConvenioPessoa = request.getParameter("dataRegistroConvenioPessoa");

        Boolean ehAgente = Boolean.parseBoolean(agenteIntegracao);

        Empresa empresa = new Empresa(cnpjEmpresa.replaceAll("[.|/|-]", ""), nomeEmpresa, ehAgente);
        empresa.setContatoEmpresa(contatoEmpresa);
        empresa.setEmailEmpresa(emailEmpresa);
        empresa.setTelefoneEmpresa(telefoneEmpresa);

        Pessoa pessoa = new Pessoa(nomePessoa, cpfPessoa.replaceAll("[.|/|-]", ""));
        pessoa.setEmail(emailPessoa);
        pessoa.setTelefone(telefonePessoa);

        if (pessoaJuridica) {
            String msg = "";
            Logger lg = Logger.getLogger(IncluirCadastroEmpresaServlet.class);
            try {
                EmpresaServices.incluirEmpresa(empresa);
            } catch (Exception e) {
                msg = messages.getString("br.cefetrj.sisgee.incluir_cadastro_empresa_servlet.msg_ocorreu_erro");
                request.setAttribute("msg", msg);
                lg.error("Exception ao tentar inserir uma Empresa", e);
                request.getRequestDispatcher("/form_empresa.jsp").forward(request, response);
                lg.info(msg);
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
            try {
                Convenio convenio = new Convenio(anoEmpresa, numeroEmpresa, new SimpleDateFormat("dd/MM/yyyy").parse(dataAssinaturaConvenioEmpresa), empresa);
                convenio.setNumeroConvenio();
                ConvenioServices.incluirConvenio(convenio);
                msg = messages.getString("br.cefetrj.sisgee.incluir_cadastro_empresa_servlet.msg_convenio_cadastrado");
                request.setAttribute("msg", msg);
                request.setAttribute("numeroConvenioGerado", convenio.getNumeroConvenio());
                request.getRequestDispatcher("/form_empresa_sucesso.jsp").forward(request, response);
                lg.info(msg);
            } catch (Exception e) {
                msg = messages.getString("br.cefetrj.sisgee.incluir_cadastro_empresa_servlet.msg_ocorreu_erro");
                request.setAttribute("msg", msg);
                lg.error("Exception ao tentar inserir um Convenio", e);
                request.getRequestDispatcher("/form_empresa.jsp").forward(request, response);
                lg.info(msg);
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
        } else {
            String msg = "";
            Logger lg = Logger.getLogger(IncluirCadastroEmpresaServlet.class);
            try {
                PessoaServices.incluirPessoa(pessoa);
            } catch (Exception e) {
                msg = messages.getString("br.cefetrj.sisgee.incluir_cadastro_empresa_servlet.msg_ocorreu_erro");
                request.setAttribute("msg", msg);
                lg.error("Exception ao tentar inserir uma Pessoa Física", e);
                request.getRequestDispatcher("/form_empresa.jsp").forward(request, response);
                lg.info(msg);
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
            try {

                Convenio convenio = new Convenio(anoPessoa, numeroPessoa, new SimpleDateFormat("dd/MM/yyyy").parse(dataAssinaturaConvenioPessoa), pessoa);
                convenio.setNumeroConvenio();
                ConvenioServices.incluirConvenio(convenio);
                msg = messages.getString("br.cefetrj.sisgee.incluir_cadastro_empresa_servlet.msg_convenio_cadastrado");
                request.setAttribute("msg", msg);
                request.setAttribute("numeroConvenioGerado", convenio.getNumeroConvenio());
                request.getRequestDispatcher("/form_empresa_sucesso.jsp").forward(request, response);

            } catch (Exception e) {
                msg = messages.getString("br.cefetrj.sisgee.incluir_cadastro_empresa_servlet.msg_ocorreu_erro");
                request.setAttribute("msg", msg);
                lg.error("Exception ao tentar inserir um Convenio", e);
                request.getRequestDispatcher("/form_empresa.jsp").forward(request, response);
                lg.info(msg);
                request.getRequestDispatcher("/index.jsp").forward(request, response);

            }
        }

    }
}
