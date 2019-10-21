package br.cefetrj.sisgee.view.AJAX;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.cefetrj.sisgee.control.AlunoServices;
import br.cefetrj.sisgee.model.entity.Aluno;
import br.cefetrj.sisgee.model.entity.Campus;
import br.cefetrj.sisgee.model.entity.Curso;
import br.cefetrj.sisgee.model.entity.Pessoa;
import br.cefetrj.sisgee.model.entity.TermoEstagio;
import br.cefetrj.sisgee.model.utils.Constantes;
import br.cefetrj.sisgee.view.utils.ServletUtils;
import br.cefetrj.sisgee.view.utils.ValidaUtils;

/**
 * Servlet para trazer os dados do Aluno por meio de requisição AJAX.
 * 
 * @author Augusto Jose
 * @since 1.0
 * 
 *        Internacionalizar e usar outros parâmetros na Busca do Aluno
 * 
 * @author Claudio Freitas
 * @since 2.0
 */
@WebServlet("/BuscaAlunoServlet")
public class BuscaAlunoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String matricula = request.getParameter("matricula").substring(10);
		String termoAditivo = request.getParameter("termoAditivo");

		String idAluno = "";
		String nome = "";
		String nomeCurso = "";
		String nomeCampus = "";
		String idTermoEstagioAtivo = "";
		String tipoAluno = "";
		String alunoMsg1 = "";
		String alunoMsg2 = "";
		String alunoMsg3 = "";
		String alunoMsg4 = "";
		String alunoMsg5 = "";
		int tamanhoMin = 10;
		int tamanhoMax = 13;

		Locale locale = ServletUtils.getLocale(request);
		ResourceBundle messages = ResourceBundle.getBundle("Messages", locale);

		alunoMsg1 = ValidaUtils.validaTamanhoMatricula(tamanhoMin, tamanhoMax, matricula);

		if (alunoMsg1.isEmpty()) {

			Aluno aluno = AlunoServices.buscarAlunoByMatricula(matricula.trim());
			if (aluno != null) {
				Pessoa pessoa = aluno.getPessoa();
				Curso curso = aluno.getCurso();
				Campus campus = curso.getCampus();
				tipoAluno = aluno.getTipoAluno();

				idAluno = Integer.toString(aluno.getIdAluno());
				nome = pessoa.getNome();
				nomeCurso = curso.getNomeCurso();
				nomeCampus = campus.getNomeCampus();
				List<TermoEstagio> termos = aluno.getTermoEstagios();

				if (termos != null && !termos.isEmpty()) {
					//Define se a requisição veio da tela de termoEstágio ou aditivo 
					//(Caso venha vazio o parametro termo Aditivo, significa que veio da tela de Estágio)
					//TODO definir melhor esse parâmetro para caso essa busca seja utilizada em mais de duas telas
					boolean formEstagio = termoAditivo == null || termoAditivo.isEmpty();					
					
					//Veio da tela de estágio e possui um termo de estágio ativo
					if(formEstagio && existeTermoAtivo(termos) ) {
										
						alunoMsg4 = "br.cefetrj.sisgee.form_termo_estagio_servlet.msg_aluno_has_termo_aberto";
						alunoMsg5 = "br.cefetrj.sisgee.resources.form.msg_aluno_has_termo_aberto2";

						alunoMsg4 = messages.getString(alunoMsg4);
						alunoMsg5 = messages.getString(alunoMsg5);
						request.setAttribute("alunoMsg4", alunoMsg4);
						request.setAttribute("alunoMsg5", alunoMsg5);
					}

					for (TermoEstagio termo : termos) {
						if (termo.getDataFimTermoEstagio() == null) {
							idTermoEstagioAtivo = (termo.getIdTermoEstagio() != null
									? termo.getIdTermoEstagio().toString()
									: "");
							termo.getDataInicioTermoEstagio();
							termo.getConvenio().getEmpresa().getCnpjEmpresa();
							termo.getConvenio().getEmpresa().getRazaoSocial();
						}
					}
					
				}
			} else {
				alunoMsg2 = "br.cefetrj.sisgee.resources.form.aluno_nao_encontrado1";
				alunoMsg3 = "br.cefetrj.sisgee.resources.form.aluno_nao_encontrado2";

				alunoMsg2 = messages.getString(alunoMsg2);
				alunoMsg3 = messages.getString(alunoMsg3);
				request.setAttribute("alunoMsg2", alunoMsg2);
				request.setAttribute("alunoMsg3", alunoMsg3);
			}
		} else {
			alunoMsg2 = "br.cefetrj.sisgee.resources.form.aluno_nao_encontrado1";

			alunoMsg2 = messages.getString(alunoMsg2);
			alunoMsg1 = messages.getString(alunoMsg1);

			request.setAttribute("alunoMsg1", alunoMsg1);
			request.setAttribute("alunoMsg2", alunoMsg2);

		}
		// JSON
		JsonObject model = Json.createObjectBuilder()
				.add("idAluno", idAluno)
				.add("nome", nome)
				.add("nomeCurso", nomeCurso)
				.add("nomeCampus", nomeCampus)
				.add("idTermoEstagioAtivo", idTermoEstagioAtivo)
				.add("tipoAluno", tipoAluno)
				.add("alunoMsg1", alunoMsg1)
				.add("alunoMsg2", alunoMsg2)
				.add("alunoMsg3", alunoMsg3)
				.add("alunoMsg4", alunoMsg4)
				.add("alunoMsg5", alunoMsg5)
				.build();

		StringWriter stWriter = new StringWriter();
		JsonWriter jsonWriter = Json.createWriter(stWriter);
		jsonWriter.writeObject(model);
		jsonWriter.close();
		String jsonData = stWriter.toString();

		response.setContentType("application/json");
		response.getWriter().print(jsonData);
	}

	/**
	 * Método que verifica, dada uma lista de termos, se algum deles está ativo.
	 * 
	 * @param termos Lista de termo estágio
	 * @return true caso algum dos termos seja ativo ou false, caso contrário
	 */
	private boolean existeTermoAtivo(List<TermoEstagio> termos) {
		
		for (TermoEstagio termo : termos) {
			if(Constantes.ATIVO.equalsIgnoreCase(termo.getEstado())){
				return true;
			}
		}
		return false;
	}
}