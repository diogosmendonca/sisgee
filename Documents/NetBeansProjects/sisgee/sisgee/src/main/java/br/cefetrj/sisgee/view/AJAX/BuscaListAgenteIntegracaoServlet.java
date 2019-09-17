package br.cefetrj.sisgee.view.AJAX;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.cefetrj.sisgee.control.AgenteIntegracaoServices;
import br.cefetrj.sisgee.model.entity.AgenteIntegracao;


/**
 * Servlet para trazer a lista de Agente Integração por meio de requisição AJAX.
 * 
 * @author Augusto Jose
 * @since 1.0
 *
 */
@WebServlet("/BuscaListAgenteIntegracaoServlet")
public class BuscaListAgenteIntegracaoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<AgenteIntegracao> agentesIntegracao = AgenteIntegracaoServices.listarAgenteIntegracao();
		
		//JSON
		JsonArrayBuilder objArrayBuilder = Json.createArrayBuilder();
		
		for(AgenteIntegracao ai: agentesIntegracao) {
			JsonObjectBuilder objBuilderNode = Json.createObjectBuilder();
			objBuilderNode.add("idAgenteIntegracao", ai.getIdAgenteIntegracao());
			objBuilderNode.add("nomeAgenteIntegracao", ai.getNomeAgenteIntegracao());
			objArrayBuilder.add(objBuilderNode);
		}
		
		JsonObjectBuilder objBuilderRoot = Json.createObjectBuilder();
		objBuilderRoot.add("lstAgenteIntegracao", objArrayBuilder);
		JsonObject model = objBuilderRoot.build();
		
		StringWriter stWriter = new StringWriter();
		JsonWriter jsonWriter = Json.createWriter(stWriter);
		jsonWriter.writeObject(model);
		jsonWriter.close();
		String jsonData = stWriter.toString();
		
		response.setContentType("application/json");
		response.getWriter().print(jsonData);
	}

}
