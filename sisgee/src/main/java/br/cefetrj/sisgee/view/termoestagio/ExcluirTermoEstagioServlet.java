/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetrj.sisgee.view.termoestagio;

import br.cefetrj.sisgee.control.AlunoServices;
import br.cefetrj.sisgee.control.TermoAditivoServices;
import br.cefetrj.sisgee.control.TermoEstagioServices;
import br.cefetrj.sisgee.model.entity.Aluno;
import br.cefetrj.sisgee.model.entity.TermoAditivo;
import br.cefetrj.sisgee.model.entity.TermoEstagio;
import br.cefetrj.sisgee.view.utils.ServletUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *Servlet criado para excluir um termo estágio
 * @author FernandoGodoy
 */
@WebServlet("/ExcluirTermoEstagioServlet")
public class ExcluirTermoEstagioServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Locale locale = ServletUtils.getLocale(req);
        ResourceBundle messages = ResourceBundle.getBundle("Messages", locale);

        String ide = req.getParameter("ide");
        TermoEstagio termoEstagio = null;

        if (ide != null) {
            termoEstagio = TermoEstagioServices.buscarTermoEstagio(Integer.parseInt(ide));

        }

        String exclusaoEstagioConcluido = "";
        String msgOcorreuErro = "";
        Logger lg = Logger.getLogger(ExcluirTermoEstagioServlet.class);
        try {
            TermoEstagioServices.excluirTermoEstagio(termoEstagio);
            exclusaoEstagioConcluido = messages.getString("br.cefetrj.sisgee.excluir_termo_estagio_servlet.msg_exclusaoEstagioConcluida");
            req.setAttribute("msg", exclusaoEstagioConcluido);
            lg.info(exclusaoEstagioConcluido);
            req.getRequestDispatcher("/index.jsp").forward(req, resp);

        } catch (Exception e) {
            msgOcorreuErro = messages.getString("br.cefetrj.sisgee.excluir_termo_estagio_servlet.msg_ocorreuErro");
            req.setAttribute("msg", msgOcorreuErro);

            lg.error("Exception ao tentar Excluir o Termo Estagio", e);
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }

        System.out.println(exclusaoEstagioConcluido);

    }

}
