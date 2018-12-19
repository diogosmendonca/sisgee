/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetrj.sisgee.view.convenio;

import br.cefetrj.sisgee.control.AlunoServices;
import br.cefetrj.sisgee.control.ConvenioServices;
import br.cefetrj.sisgee.control.EmpresaServices;
import br.cefetrj.sisgee.control.PessoaServices;
import br.cefetrj.sisgee.control.TermoAditivoServices;
import br.cefetrj.sisgee.control.TermoEstagioServices;
import br.cefetrj.sisgee.model.entity.Aluno;
import br.cefetrj.sisgee.model.entity.Convenio;
import br.cefetrj.sisgee.model.entity.Empresa;
import br.cefetrj.sisgee.model.entity.Pessoa;
import br.cefetrj.sisgee.model.entity.TermoAditivo;
import br.cefetrj.sisgee.model.entity.TermoEstagio;
import br.cefetrj.sisgee.view.utils.ServletUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
 * @author FernandoGodoy
 */
@WebServlet("/ExcluirConvenioServlet")
public class ExcluirConvenioServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Locale locale = ServletUtils.getLocale(req);
        ResourceBundle messages = ResourceBundle.getBundle("Messages", locale);
        String numConvenio = req.getParameter("ncon");
        String[] convenionumero = numConvenio.split("/");
        numConvenio = convenionumero[0];
        String codigo = req.getParameter("codigo");
        Empresa empresa = null;
        Convenio convenio = null;
        Pessoa pessoa = null;
        System.out.println(codigo);
        if (codigo.length() == 11) {
            pessoa = PessoaServices.buscarPessoaByCpf(codigo);
        } else if (codigo.length() == 14) {
            empresa = EmpresaServices.buscarEmpresaByCnpj(codigo);
        }
        if (numConvenio != null) {
            convenio = ConvenioServices.buscarConvenioByNumeroConvenio(numConvenio);
        }
        String exclusaoConvenioConcluido = "";
        String msgOcorreuErro = "";
        Logger lg = Logger.getLogger(ExcluirConvenioServlet.class);
        try {
            ConvenioServices.excluirConvenio(convenio, empresa, pessoa);
            exclusaoConvenioConcluido = messages.getString("br.cefetrj.sisgee.excluir_convenio_servlet.msg_exclusaoConvenioConcluida");
            req.setAttribute("msg", exclusaoConvenioConcluido);
            lg.info(exclusaoConvenioConcluido);
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        } catch (Exception e) {
            msgOcorreuErro = messages.getString("br.cefetrj.sisgee.excluir_convenio_servlet.msg_ocorreuErro");
            req.setAttribute("msg", msgOcorreuErro);
            lg.error("Exception ao tentar Excluir o Convenio", e);
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }

        System.out.println(exclusaoConvenioConcluido);

    }

}
