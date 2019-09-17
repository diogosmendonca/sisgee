package br.cefetrj.sisgee.view.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.apache.log4j.Logger;

/**
 * Filtro para todas as requisições.
 * Substituindo o padrão Front Controller
 * 
 * @author Paulo Cantuária
 * @since 1.0
 */
@WebFilter("/*")
public class TodasRequisicoesFilter implements Filter {

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
				
		try{			
                    request.setCharacterEncoding("UTF-8");
                    chain.doFilter(request, response);	
			
		}catch(Exception e) {
			Logger lg = Logger.getLogger(TodasRequisicoesFilter.class);
			lg.error("Exception não tratada no Filter", e);
			request.getRequestDispatcher("/erro.jsp").forward(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
