package br.cefetrj.sisgee.control.json;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import br.cefetrj.sisgee.control.AlunoServices;
import br.cefetrj.sisgee.control.CursoServices;
import br.cefetrj.sisgee.control.PessoaServices;
import br.cefetrj.sisgee.model.dao.CampusDAO;
import br.cefetrj.sisgee.model.dao.CursoDAO;
import br.cefetrj.sisgee.model.dao.PessoaDAO;
import br.cefetrj.sisgee.model.entity.Aluno;
import br.cefetrj.sisgee.model.entity.Campus;
import br.cefetrj.sisgee.model.entity.Curso;
import br.cefetrj.sisgee.model.entity.Pessoa;
import br.cefetrj.sisgee.model.entity.ProfessorOrientador;
import br.cefetrj.sisgee.model.utils.AlunoSIE;

public class SIEServices {

	// TODO mudar para a URL do SIE
	private static final String URLBASE = "http://my-json-server.typicode.com/paducantuaria/sisgeeJson/";

	/**
	 * Método de busca de aluno a partir da base do SIE, usando webservice
	 * 
	 * @param matricula
	 * @return aluno buscado ou null, caso não exista no SIE
	 */
	public static Aluno buscarAlunoFromSIE(String matricula) {

		// TODO Ajustar essa URL para a do aluno com matrícula
		String urlAluno = URLBASE + "aluno/?matricula=" + matricula;

		JSONObject jsonObject = getJsonAluno(urlAluno);
		AlunoSIE alunoSie = jsonObject != null ? converterJsonEmAlunoSIE(jsonObject) : null;
		return alunoSie != null ? converterAlunoSIE(alunoSie) : null;
	}

	/**
	 * Método para remover boiler plate do método principal da busca de aluno no SIE
	 * 
	 * @param urlAluno url de busca de aluno, usando GET
	 * @return JSONObject do aluno buscado ou null caso não exista
	 */
	private static JSONObject getJsonAluno(String urlAluno) {
		try {
			String urlParse = IOUtils.toString(new URL(urlAluno), StandardCharsets.UTF_8);
			JSONArray jsonArray = (JSONArray) JSONValue.parseWithException(urlParse);
			return jsonArray.isEmpty() ? null : (JSONObject) jsonArray.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Busca na API do SIE e pega todos os professores
	 * @return
	 */
	public static List<ProfessorOrientador> getListaProfessorFromSIE() {

		String urlProfessor = URLBASE + "professor";
		JSONArray jsonArray;
		try {

			String urlParse = IOUtils.toString(new URL(urlProfessor), StandardCharsets.UTF_8);
			jsonArray = (JSONArray) JSONValue.parseWithException(urlParse);
			return converterJsonProfSIEParaListaProfessores(jsonArray);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}		
	}

	/**
	 * Método que converte a lista de professores recebida do SIE para uma lista de
	 * professores do domínio Sisgee
	 * 
	 * @param jsonArray
	 * @return A lista de professores convertida ou nulo caso a lista esteja vazia
	 */
	private static List<ProfessorOrientador> converterJsonProfSIEParaListaProfessores(JSONArray jsonArray) {

		List<ProfessorOrientador> listaOrientadores = new ArrayList<>();

		for (int i = 0; i < jsonArray.size(); i++) {

			JSONObject jsonObject = (JSONObject) jsonArray.get(i);

			String siape = jsonObject.get("siape").toString();
			String nome = jsonObject.get("nome").toString();

			listaOrientadores.add(new ProfessorOrientador(siape.trim(), nome.trim()));
		}

		return listaOrientadores.isEmpty() ? null : listaOrientadores;
	}

	/**
	 * Método que converte um aluno vindo do JSON para o aluno do domínio (Classe
	 * AlunoSIE para Classe Aluno)
	 * 
	 * @param alunoSie
	 * @return Objeto Aluno convertido
	 */
	private static Aluno converterAlunoSIE(AlunoSIE alunoSie) {

		Aluno aluno = new Aluno();
		Curso curso = new CursoDAO().buscarByCodigo(alunoSie.getSiglaCurso());
		if (curso == null) {
			// TODO Ajustar a busca com a String "Campus" após o ajuste das bases (Sisgee
			// com SIE)
			Campus campus = new CampusDAO().buscarByNome("Campus " + alunoSie.getCampus());
			if (campus != null) {
				curso = new Curso();
				curso.setCampus(campus);
				curso.setCodigoCurso(alunoSie.getSiglaCurso());
				curso.setNomeCurso(alunoSie.getCurso());
				CursoServices.incluirCurso(curso);
				curso = new CursoDAO().buscar(curso.getIdCurso());

			} else {
				// TODO Alterar comportamento quando as bases estiverem ajustadas (Sisgee com
				// SIE)
				// nesse caso, os nomes dos campi estarão em acordo entre os bancos e um nome
				// diferente significa um novo campus
				// por enquanto manteremos o retorno null
				Logger.getLogger(SIEServices.class).error("Nome do Campus inválido");
				return null;
			}
		}
		aluno.setCurso(curso);

		Pessoa pessoa = new PessoaDAO().buscarByCpf(alunoSie.getCpf());
		if (pessoa == null) {
			pessoa = new Pessoa();
			pessoa.setNome(alunoSie.getNome());
			pessoa.setCpf(alunoSie.getCpf());
			PessoaServices.incluirPessoa(pessoa);
		}
		aluno.setPessoa(pessoa);
		aluno.setMatricula(alunoSie.getMatricula());
		aluno.setTipoAluno(alunoSie.getTipo());
		AlunoServices.incluirAluno(aluno);
		return aluno;
	}

	/**
	 * Método que converte um JSONObject em um AlunoSIE
	 * 
	 * @param jsonObject
	 * @return AlunoSIE convertido ou null caso a conversão não seja possível
	 */
	private static AlunoSIE converterJsonEmAlunoSIE(JSONObject jsonObject) {
		// Recebendo os dados de aluno provenientes do json
		AlunoSIE alunoSie = new AlunoSIE();

		try {

			String matriculaSie = (String) jsonObject.get("matricula");
			String nome = (String) jsonObject.get("nome");
			String cpf = (String) jsonObject.get("cpf");
			String curso = (String) jsonObject.get("curso");
			String siglaCurso = (String) jsonObject.get("siglaCurso");
			String tipo = (String) jsonObject.get("tipo");
			String campus = (String) jsonObject.get("campus");

			// instanciando o alunoSie com os valores provenientes do json

			alunoSie.setMatricula(matriculaSie.trim());
			alunoSie.setNome(nome.trim());
			alunoSie.setCpf(cpf.trim());
			alunoSie.setCurso(curso.trim());
			alunoSie.setSiglaCurso(siglaCurso.trim());
			alunoSie.setTipo(tipo);
			alunoSie.setCampus(campus.trim());
			return alunoSie;

		} catch (Exception e) {
			return null;
		}
	}
}
