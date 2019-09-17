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

import br.cefetrj.sisgee.control.AlunoServices;
import br.cefetrj.sisgee.model.entity.Aluno;
import br.cefetrj.sisgee.model.entity.Campus;
import br.cefetrj.sisgee.model.entity.Curso;
import br.cefetrj.sisgee.model.entity.Pessoa;
import br.cefetrj.sisgee.model.entity.TermoEstagio;
import br.cefetrj.sisgee.view.utils.ServletUtils;
import br.cefetrj.sisgee.view.utils.ValidaUtils;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Servlet para trazer os dados do Aluno por meio de requisição AJAX.
 * 
 * @author Augusto Jose
 * @since 1.0
 * 
 * Internacionalizar e usar outros parâmetros na Busca do Aluno
 * 
 * @author Claudio Freitas
 * @since 2.0
 */
@WebServlet("/BuscaAlunoServlet")
public class BuscaAlunoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String matricula = request.getParameter("matricula").substring(10);
                String termoAditivo = "nao";
                
		String idAluno = "";
		String nome = "";
		String nomeCurso = "";
		String nomeCampus = "";
                String idTermoEstagioAtivo = "";
                String tipoAluno = "";
                String alunoMsg1 = "", alunoMsg2 = "", alunoMsg3 = "", alunoMsg4 = "", alunoMsg5 = "";
                int tamanhoMin = 10;
                int tamanhoMax = 13;
                termoAditivo = request.getParameter("termoAditivo");
                                
                Locale locale = ServletUtils.getLocale(request);
                ResourceBundle messages = ResourceBundle.getBundle("Messages", locale);
                final Calendar cal = Calendar.getInstance();
                
                alunoMsg1 = ValidaUtils.validaTamanhoMatricula(tamanhoMin, tamanhoMax, matricula);
                
                if(alunoMsg1.isEmpty() || alunoMsg1 == null ){

                    Aluno aluno = AlunoServices.buscarAlunoByMatricula(matricula.trim());
                    if(aluno != null) {
                            Pessoa pessoa = aluno.getPessoa();
                            Curso curso   = aluno.getCurso();
                            Campus campus = curso.getCampus();
                            tipoAluno     = aluno.getTipoAluno();

                            idAluno = Integer.toString(aluno.getIdAluno());
                            nome = pessoa.getNome();
                            nomeCurso = curso.getNomeCurso();
                            nomeCampus = campus.getNomeCampus();
                            List<TermoEstagio> termos = aluno.getTermoEstagios();

                            if(termos != null && !termos.isEmpty()){
                                if(((termoAditivo == null || termoAditivo.isEmpty()) && (aluno.getTermoEstagios().get(aluno.getTermoEstagios().size()-1).getDataRescisaoTermoEstagio() == null &&   aluno.getTermoEstagios().get(aluno.getTermoEstagios().size()-1).getDataFimTermoEstagio().compareTo(cal.getTime())>0)) || ((termoAditivo == null || termoAditivo.isEmpty()) && 
                                   ( aluno.getTermoEstagios().get(aluno.getTermoEstagios().size()-1).getDataRescisaoTermoEstagio().compareTo(cal.getTime()) > 0) 
                                        ) ){
                                    alunoMsg4 = "br.cefetrj.sisgee.form_termo_estagio_servlet.msg_aluno_has_termo_aberto";
                                    alunoMsg5 = "br.cefetrj.sisgee.resources.form.msg_aluno_has_termo_aberto2";

                                    alunoMsg4 = messages.getString(alunoMsg4);
                                    alunoMsg5 = messages.getString(alunoMsg5);  
                                    request.setAttribute("alunoMsg4", alunoMsg4);
                                    request.setAttribute("alunoMsg5", alunoMsg5);                                    
                                }
                                
                                for (TermoEstagio termo : termos) {
                                    if(termo.getDataFimTermoEstagio()== null){
                                        idTermoEstagioAtivo = 
                                               (termo.getIdTermoEstagio() != null ? 
                                                termo.getIdTermoEstagio().toString() : 
                                                "" );
                                        termo.getDataInicioTermoEstagio();
                                        termo.getConvenio().getEmpresa().getCnpjEmpresa();
                                        termo.getConvenio().getEmpresa().getRazaoSocial();

                                    }
                                }
                            }
                    }else{
                        alunoMsg2 = "br.cefetrj.sisgee.resources.form.aluno_nao_encontrado1";
                        alunoMsg3 = "br.cefetrj.sisgee.resources.form.aluno_nao_encontrado2";
                        
                        alunoMsg2 = messages.getString(alunoMsg2);
                        alunoMsg3 = messages.getString(alunoMsg3);
                        request.setAttribute("alunoMsg2", alunoMsg2);
                        request.setAttribute("alunoMsg3", alunoMsg3);                        
                    }
                }else{
                    alunoMsg2 = "br.cefetrj.sisgee.resources.form.aluno_nao_encontrado1";
                    
                    alunoMsg2 = messages.getString(alunoMsg2);                    
                    alunoMsg1 = messages.getString(alunoMsg1);
                    
                    request.setAttribute("alunoMsg1", alunoMsg1);
                    request.setAttribute("alunoMsg2", alunoMsg2);
                    //TODO Fazer log
                    System.out.println(alunoMsg1);                    
                }
		//JSON
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

}
