package br.cefetrj.sisgee.view.AJAX;

import java.io.IOException;
import java.io.StringWriter;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.cefetrj.sisgee.control.EmpresaServices;
import br.cefetrj.sisgee.model.entity.Empresa;

/**
 * Servlet para trazer os dados da Empresa para a tela de cadastro de Termo
 * de Estágio, por meio de requisição AJAX.
 * 
 * @author Augusto Jose
 * @since 1.0
 *
 */
@WebServlet("/BuscaEmpresaServlet")
public class BuscaEmpresaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String cnpjEmpresa = request.getParameter("cnpjEmpresa");
		String idEmpresa = "";
		String nomeEmpresa = "";
                if(cnpjEmpresa != null){
                    cnpjEmpresa = cnpjEmpresa.replaceAll("[.|/|-]", "");
                }
		
		Empresa empresa = EmpresaServices.buscarEmpresaByCnpj(cnpjEmpresa.trim());
		if(empresa != null) {
			idEmpresa = Integer.toString(empresa.getIdEmpresa());
			nomeEmpresa = empresa.getRazaoSocial();
		}
		
		//JSON
		JsonObject model = Json.createObjectBuilder()
				.add("idEmpresa", idEmpresa)
				.add("nomeEmpresa", nomeEmpresa)
				.build();
		
		StringWriter stWriter = new StringWriter();
		JsonWriter jsonWriter = Json.createWriter(stWriter);
		jsonWriter.writeObject(model);
		jsonWriter.close();
		String jsonData = stWriter.toString();
		
		response.setContentType("application/json");
		response.getWriter().print(jsonData);
	}

}
