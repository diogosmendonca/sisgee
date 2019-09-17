package br.cefetrj.sisgee.view.termoestagio;

import br.cefetrj.sisgee.control.ConvenioServices;
import java.io.IOException;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import br.cefetrj.sisgee.control.TermoEstagioServices;
import br.cefetrj.sisgee.model.entity.AgenteIntegracao;
import br.cefetrj.sisgee.model.entity.Aluno;
import br.cefetrj.sisgee.model.entity.Convenio;
import br.cefetrj.sisgee.model.entity.Empresa;
import br.cefetrj.sisgee.model.entity.Pessoa;
import br.cefetrj.sisgee.model.entity.ProfessorOrientador;
import br.cefetrj.sisgee.model.entity.TermoEstagio;
import br.cefetrj.sisgee.view.utils.ServletUtils;

/**
 * Servlet que inlcui um Termo de Estágio depois de Validado
 * @author Paulo Cantuária
 * @since 1.0
 * 
 * Adaptado para funcionar com formato mais definido
 * @author Claudio Freitas
 * @version 2.0
 * 
 */

@WebServlet("/IncluirTermoEstagioServlet")
public class IncluirTermoEstagioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
        
        /**
        * 
        * @param request um objeto HttpServletRequest que contém a solicitação feita pelo cliente do servlet.
        * @param response um objeto HttpServletResponse que contém a resposta que o servlet envia para o cliente
        * @throws ServletException se o pedido do service não puder ser tratado
        * @throws IOException se um erro de entrada ou saída for detectado quando o servlet manipula o pedido 
        */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Locale locale = ServletUtils.getLocale(request);
		ResourceBundle messages = ResourceBundle.getBundle("Messages", locale);
		
		//OBRIGATÓRIO
		Date dataInicioTermoEstagio         = (Date)request.getAttribute("dataInicio");
		Integer cargaHorariaTermoEstagio    = (Integer)request.getAttribute("cargaHoraria");
		Float valorBolsa                    = (Float)request.getAttribute("valor");
		String enderecoTermoEstagio         = (String)request.getAttribute("enderecoTermoEstagio");
		String numeroEnderecoTermoEstagio   = (String)request.getAttribute("numeroEnderecoTermoEstagio");
		String complementoEnderecoTermoEstagio = (String)request.getAttribute("complementoEnderecoTermoEstagio");
		String bairroEnderecoTermoEstagio   = (String)request.getAttribute("bairroEnderecoTermoEstagio");
		String cepEnderecoTermoEstagio      = (String)request.getAttribute("cepEnderecoTermoEstagio");
		String cidadeEnderecoTermoEstagio   = (String)request.getAttribute("cidadeEnderecoTermoEstagio");
		String estadoEnderecoTermoEstagio   = (String)request.getAttribute("estadoEnderecoTermoEstagio");
		Boolean eEstagioObrigatorio         = (Boolean)request.getAttribute("obrigatorio");
                
                String nomeSupervisor               = request.getParameter("nomeSupervisor");
                String cargoSupervisor              = request.getParameter("cargoSupervisor");    
                String nomeAgenciada                = request.getParameter("nomeAgenciada");                
                
		Aluno aluno = new Aluno((Integer)request.getAttribute("idAluno")); 	
                
                //OBRIGATÓRIO
                String convenionum = (String)request.getAttribute("idConvenio");
                System.out.println("termo estagio ------>>>>>>>>>>" + convenionum);
                Convenio convenio = ConvenioServices.buscarConvenioByNumeroConvenio(convenionum);
		//NÃO OBRIGATÓRIO
		Boolean hasDataFim          = (Boolean)request.getAttribute("hasDataFim");		
		Boolean hasProfessor        = (Boolean)request.getAttribute("hasProfessor");
		String isAgenteIntegracao   = (String)request.getAttribute("isAgenteIntegracao");
		
		Date dataFimTermoEstagio = null;
		ProfessorOrientador professorOrientador = null;
		AgenteIntegracao agenteIntegracao = null;
		
		if(hasDataFim) {
			dataFimTermoEstagio = (Date)request.getAttribute("dataFim");
		}
		
		if(hasProfessor) {
			professorOrientador = new ProfessorOrientador((Integer)request.getAttribute("idProfessor"));
		}		
		
		TermoEstagio termoEstagio = new TermoEstagio(dataInicioTermoEstagio, dataFimTermoEstagio, cargaHorariaTermoEstagio,
				 valorBolsa,  enderecoTermoEstagio,  numeroEnderecoTermoEstagio,
				 complementoEnderecoTermoEstagio,  bairroEnderecoTermoEstagio,  cepEnderecoTermoEstagio,
				 cidadeEnderecoTermoEstagio,  estadoEnderecoTermoEstagio,  eEstagioObrigatorio,
				 aluno,  convenio,  professorOrientador, nomeSupervisor, cargoSupervisor, nomeAgenciada);
		
		String msg = "";
		Logger lg = Logger.getLogger(IncluirTermoEstagioServlet.class);
		try{
			
			TermoEstagioServices.incluirTermoEstagio(termoEstagio, convenio);
			msg = messages.getString("br.cefetrj.sisgee.incluir_termo_estagio_servlet.msg_sucesso");
			request.setAttribute("msg", msg);
			
			lg.info(msg);
			request.getRequestDispatcher("/index.jsp").forward(request, response);			
			
			
		}catch(Exception e) {
			msg = messages.getString("br.cefetrj.sisgee.incluir_termo_estagio_servlet.msg_falha");
			request.setAttribute("msg", msg);
			System.out.println("Erro no Try Catch do IncluirTermoEstagioServlet " + e);
			lg.error("Exception ao tentar inserir o Termo de Estágio", e);
			request.getRequestDispatcher("FormTermoEstagioServlet").forward(request, response);			
			
		}
		
		System.out.println(msg);		
	}
}
