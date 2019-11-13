package br.cefetrj.sisgee.view.relatorio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.cefetrj.sisgee.control.ConvenioServices;
import br.cefetrj.sisgee.model.entity.Convenio;
import br.cefetrj.sisgee.view.utils.ItemRelatorio;
/**
 * Servlet para buscar e processar os dados de convenios entre o per�odo selecionado obtidos do banco.
 * Foi convencionado que convenios novos seriam os conv�nios registrados a partir de 2019 e maiores ou igual ao n�mero convenio 5445
 * Porque este foi o primeiro novo registrado no ano. Convenios registrados em datas anteriores e que foram renovados a partir de 2019 tamb�m apareceriam na lista,
 * Ent�o foi determinado esse valor como refer�ncia entre o que era conv�nio novo e o que era renovado. Este crit�rio pode ser alterado em anos futuros. 
 * 
 * @author Ricardo
 * 
 *
 */

@WebServlet("/BuscaRelatorioConveniosConsolidadoServlet")
public class BuscaRelatorioConveniosConsolidadoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

        
    public BuscaRelatorioConveniosConsolidadoServlet() {
        super();
    }
    
    /**
     * 
     * @param request um objeto HttpServletRequest que contém a solicitação feita pelo cliente do servlet.
     * @param response um objeto HttpServletResponse que contém a resposta que o servlet envia para o cliente
     * @throws ServletException se o pedido do service não puder ser tratado
     * @throws IOException se um erro de entrada ou saída for detectado quando o servlet manipula o pedido 
     */
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   
		Date registroInicio = (Date) request.getAttribute("registroInicio");
		Date registroFim = (Date) request.getAttribute("registroFim");    	
    	
    	
    	Locale locale = (Locale) request.getAttribute("Locale");

    	
		ResourceBundle messages = ResourceBundle.getBundle("Messages", locale);
		
    	System.out.println(registroInicio);
		System.out.println(registroFim);
		
    	List<Object[]> conveniosLista = null;
		conveniosLista = ConvenioServices.listarConveniosFiltrado(registroInicio, registroFim);
    	List<Convenio> conveniosNovos = null;
    	List<Convenio> conveniosRenovados = null;
    	if(!(conveniosLista.isEmpty())) {		   			
   			for(Object o : conveniosLista ) {
   				Convenio conv = (Convenio) o;
   				int numero = Integer.parseInt(conv.getNumero());
   				if(numero < 5445) {
   					conveniosRenovados.add(conv);
   				}else if(numero >= 5445) {
   					conveniosNovos.add(conv);
   				}
   			}
   			request.setAttribute("relatorioRenovados", conveniosRenovados);
    		request.setAttribute("relatorioNovos", conveniosNovos);
    			
    	}else {
    		System.out.println("Nenhum registro encontrado nesse período de tempo");
    		String msgRelatorio = messages.getString("br.cefetrj.sisgee.relatorio.busca_relatorio_consolidado_servlet.nenhum_resultado");
    		request.setAttribute("msgRelatorio", msgRelatorio);
    	}
    	
    	request.getSession().setAttribute("imprimirRenovados", conveniosRenovados);
        request.getSession().setAttribute("imprimirNovos", conveniosNovos);
    	
    	
    	request.getRequestDispatcher("/relatorio_convenios_consolidado.jsp").forward(request, response);
    	
    }
    
  
}