/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetrj.sisgee.view.termoaditivo;

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
 * Servlet responsável por tratar exclusão de um termo aditivo
 *
 * @author FernandoGodoy
 */
@WebServlet("/ExcluirTermoAditivoServlet")
public class ExcluirTermoAditivoServlet extends HttpServlet {

   
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Locale locale = ServletUtils.getLocale(req);
	ResourceBundle messages = ResourceBundle.getBundle("Messages", locale);
        String ida = req.getParameter("ida");
        TermoAditivo termoAditivo = null;
        
        if(ida!=null){
            termoAditivo=TermoAditivoServices.buscarTermoAditivo(Integer.parseInt(ida));
        }
        
        String exclusaoAditivoConcluido = "";
	String msgOcorreuErro = "";
	Logger lg = Logger.getLogger(ExcluirTermoAditivoServlet.class);
		try{
			TermoAditivoServices.excluirTermoAditivo(termoAditivo);
			exclusaoAditivoConcluido = messages.getString("br.cefetrj.sisgee.excluir_termo_aditivo_servlet.msg_exclusaoAditivoConcluida");
			req.setAttribute("msg", exclusaoAditivoConcluido);
			lg.info(exclusaoAditivoConcluido);
			req.getRequestDispatcher("/index.jsp").forward(req, resp);
			
			
			
			
		}catch(Exception e) {
			msgOcorreuErro = messages.getString("br.cefetrj.sisgee.excluir_termo_aditivo_servlet.msg_ocorreuErro");
			req.setAttribute("msg", msgOcorreuErro);
			
			lg.error("Exception ao tentar Excluir o Termo Aditivo", e);
			req.getRequestDispatcher("index.jsp").forward(req, resp);
		}
		
		System.out.println(exclusaoAditivoConcluido);
        
        
        
    }

   
    
   
   

}
