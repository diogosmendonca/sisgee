package br.cefetrj.sisgee.view.utils;

import java.text.MessageFormat;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * Classe criada para incluir método estáticos para melhor reuso de código nos Servlets.
 * @author padu
 * @since 1.0
 *
 */
public class ServletUtils {
	
	/**
	 * Método estático para buscar o locale no request, senão pega da sessão, senão pega o padrão
	 * @param request requisição do usuário
	 * @return Locale localização do usuário ou selecionado pela página
	 * @throws ServletException caso a Locale não seja encontrada
	 * @return locale do user ou selecionado
	 */
	public static Locale getLocale(HttpServletRequest request) throws ServletException {
		
		Locale locale = null;
		
		Object localeFound = request.getParameter("lang");
		if (localeFound == null)
			localeFound = request.getSession().getAttribute("lang");
		
		if (localeFound == null)
			localeFound = request.getLocale();
		
		
		if (localeFound instanceof Locale){
			locale = (Locale)localeFound;
		}else if (localeFound instanceof String){
			String[] parts = ((String) localeFound).split("_"); 
			locale = new Locale(parts[0], parts[1].toUpperCase());
		}else{
			throw new ServletException("Locale not found!!!");
		}		
		return locale;
	}
	
	/**
	 * Método estático para formatar mensagens que precisam de formatação
	 * @param msg chave que deverá ser formatada
	 * @param locale localização do usuário ou selecionado pela página
	 * @param param O valor variável da mensagem
	 * @return Mensagem formatada
	 */	
	public static String mensagemFormatada(String msg, Locale locale, Integer param) {
		MessageFormat formatter = new MessageFormat("");
		formatter.setLocale(locale);
		formatter.applyPattern(msg);
		
		msg = formatter.format(new Object[] {param});		
		return msg;
	}
	

}
