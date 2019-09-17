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

import br.cefetrj.sisgee.control.TermoEstagioServices;
import br.cefetrj.sisgee.control.TermoAditivoServices;
import br.cefetrj.sisgee.view.utils.ItemRelatorio;
/**
 * Servlet para buscar e processar os dados obtidos do banco.
 * 
 * @author Marcos E Carvalho
 * @since 1.0
 *
 */

@WebServlet("/BuscaRelatorioConsolidadoServlet")
public class BuscaRelatorioConsolidadoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

        
    public BuscaRelatorioConsolidadoServlet() {
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
   
		Date dataInicio = (Date) request.getAttribute("dataInicio");
		Date dataTermino = (Date) request.getAttribute("dataTermino");    	
    	Boolean estagioObrig = (Boolean) request.getAttribute("estagioObrig");
    	Boolean estagioNaoObrig = (Boolean) request.getAttribute("estagioNaoObrig");
    	
    	
    	Locale locale = (Locale) request.getAttribute("Locale");

    	
		ResourceBundle messages = ResourceBundle.getBundle("Messages", locale);
		
    	System.out.println(dataInicio);
		System.out.println(dataTermino);
    	System.out.println(estagioObrig);
    	System.out.println(estagioNaoObrig);
		
    	
    	List<Object []> termosEstagioLista = null;
    	List<Object []> termosAditivoLista = null;
    	
    	if(estagioObrig == true && estagioNaoObrig == true) {
    		termosEstagioLista  = TermoEstagioServices.listarTermoEstagioFiltrado(null, dataInicio , dataTermino);
    		termosAditivoLista = TermoAditivoServices.listarTermoAditivoFiltrado(null, dataInicio, dataTermino);
    	}else {
    		
    		if(estagioObrig == true && estagioNaoObrig == false) {
    			termosEstagioLista  = TermoEstagioServices.listarTermoEstagioFiltrado(true, dataInicio , dataTermino);
        		termosAditivoLista = TermoAditivoServices.listarTermoAditivoFiltrado(true, dataInicio, dataTermino);

    		}
    		
    		if(estagioObrig == false && estagioNaoObrig == true) {
    			termosEstagioLista = TermoEstagioServices.listarTermoEstagioFiltrado(false, dataInicio , dataTermino);
        		termosAditivoLista = TermoAditivoServices.listarTermoAditivoFiltrado(false, dataInicio, dataTermino);

    		}
    	}
    	
    	
    	List <ItemRelatorio> listaItemRelatorio = new ArrayList <ItemRelatorio> ();
    	
    	if(!(termosEstagioLista.isEmpty())) {
    		if(!(termosAditivoLista.isEmpty())){
    			
    			listaItemRelatorio = qntdPorCurso(termosEstagioLista);
    			Object [] aux = null;
    			String cursoNome = null;
    			for (Iterator<ItemRelatorio> iterator = listaItemRelatorio.iterator(); iterator.hasNext();) {
					ItemRelatorio itemRelatorio = (ItemRelatorio) iterator.next();
					for(int i = 0; i < termosAditivoLista.size(); i++) {
					aux = termosAditivoLista.get(i);
					cursoNome = (String) aux[0];
					if(itemRelatorio.getNomeCurso().equals(cursoNome) ) {
						itemRelatorio.setQntTermoAditivo(itemRelatorio.getQntTermoAditivo()+1);
					}
					}
				}
    			
    			    			
    		}else {
    			
    			listaItemRelatorio = qntdPorCurso(termosEstagioLista);
    			
    		}
    			
    		
    		
    	}else {
    		System.out.println("Nenhum registro encontrado nesse período de tempo");
    		String msgRelatorio = messages.getString("br.cefetrj.sisgee.relatorio.busca_relatorio_consolidado_servlet.nenhum_resultado");
    		request.setAttribute("msgRelatorio", msgRelatorio);
    	}
    	
    	
    	request.setAttribute("relatorio", listaItemRelatorio);
        
        request.getSession().setAttribute("imprimir", listaItemRelatorio);
    	
    	
    	request.getRequestDispatcher("/relatorio_consolidado.jsp").forward(request, response);
    	
    }
    
   
    /**
     * Metodo para relacionar nomecurso com termo estagio e termo rescisao.
     * @param termosEstagioLista matriz com conteúdo obtido do banco
     * @return listaItemRelatorio
     */
    public static List<ItemRelatorio> qntdPorCurso(List<Object []> termosEstagioLista ){
    	List <ItemRelatorio> listaItemRelatorio = new ArrayList <ItemRelatorio> ();
    	
    	Object[] aux = null;
		ItemRelatorio item = null;
		String stg = null;
		
		 for (int i = 0; i < termosEstagioLista.size(); i++) {
			 
			 aux = termosEstagioLista.get(i);
			 stg = (String) aux[1];
			 int idx = 0;
			 
			 int verifResc = 0;
			 
			 if(aux[2] == null) verifResc = 0;
			 else verifResc = 1;
			 	
			 
			 if(listaItemRelatorio.contains(new ItemRelatorio(stg))) {
				idx = listaItemRelatorio.indexOf(new ItemRelatorio(stg));
				item = listaItemRelatorio.get(idx);
				item.setQntTermoAditivo(item.getQntTermoEstagio() + 1);
				item.setQntRescReg(item.getQntRescReg() + verifResc);
			 }else {
				 listaItemRelatorio.add(new ItemRelatorio(stg, 1 , verifResc));				 
			 }
		 }
    	
    	return listaItemRelatorio;
    }

}
