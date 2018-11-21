/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetrj.sisgee.view.termoestagio;

import br.cefetrj.sisgee.control.AlunoServices;
import br.cefetrj.sisgee.control.ConvenioServices;
import br.cefetrj.sisgee.control.ProfessorOrientadorServices;
import br.cefetrj.sisgee.control.TermoAditivoServices;
import br.cefetrj.sisgee.control.TermoEstagioServices;
import br.cefetrj.sisgee.model.entity.AgenteIntegracao;
import br.cefetrj.sisgee.model.entity.Aluno;
import br.cefetrj.sisgee.model.entity.Convenio;
import br.cefetrj.sisgee.model.entity.Empresa;
import br.cefetrj.sisgee.model.entity.ProfessorOrientador;
import br.cefetrj.sisgee.model.entity.TermoAditivo;
import br.cefetrj.sisgee.model.entity.TermoEstagio;
import br.cefetrj.sisgee.view.utils.ServletUtils;
import br.cefetrj.sisgee.view.utils.UF;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author diogo
 */
@WebServlet(name = "FormEditarTermoEstagioServlet", urlPatterns = {"/EditarTermoEAditivo"})
public class FormEditarTermoEstagioServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String idEstagio = req.getParameter("ide");
        String idAluno = req.getParameter("ida");
        String matricula = req.getParameter("matricula");
        UF[] uf = UF.asList();
        TermoEstagio termoEstagio=null;
        TermoAditivo termoAditivo=null;
        
        Aluno aluno=AlunoServices.buscarAlunoByMatricula(matricula);
        List<ProfessorOrientador> listaProfessores = ProfessorOrientadorServices.listarProfessorOrientador();
        req.setAttribute("aluno", aluno);
        req.setAttribute("listaProfessores", listaProfessores);
        
        if(idEstagio!=null){
            termoEstagio=TermoEstagioServices.buscarTermoEstagio(Integer.parseInt(idEstagio));
            
            req.setAttribute("professorOrientador", termoEstagio.getProfessorOrientador());
            
            /** Dados do termo de estágio */
            req.setAttribute("idTermoEstagio", idEstagio);
            req.setAttribute("idEstagio", idEstagio);
            req.setAttribute("ufTermoEstagio", termoEstagio.getEstadoEnderecoTermoEstagio());
        }
        
        if(idAluno!=null){
            termoAditivo=TermoAditivoServices.buscarTermoAditivo(Integer.parseInt(idAluno));
            req.setAttribute("termoAditivo", termoAditivo);
            /** Dados de aluno */
        }
        req.setAttribute("uf", uf);
        
                        
        /** Dados de aluno*/
        req.setAttribute("alMatricula", aluno.getMatricula());
        req.setAttribute("alNome", aluno.getPessoa().getNome());
        req.setAttribute("alCampus", aluno.getCurso().getCampus().getNomeCampus());
        req.setAttribute("alCurso", aluno.getCurso());

        /** Dados de convenio*/
        req.setAttribute("convenio", termoEstagio.getConvenio());
        req.setAttribute("convenioEmpresa", termoEstagio.getConvenio().getEmpresa());
        
        req.setAttribute("cvNumero", termoEstagio.getConvenio().getNumeroConvenio());
        req.setAttribute("cvNumero2", termoEstagio.getConvenio().getNumero());
        if(termoEstagio.getConvenio().getEmpresa()==null){
            req.setAttribute("cvNome", termoEstagio.getConvenio().getPessoa().getNome());
            req.setAttribute("cvId", termoEstagio.getConvenio().getIdConvenio());
            req.setAttribute("tConvenio","pf");
            req.setAttribute("cvCpfCnpj",termoEstagio.getConvenio().getPessoa().getCpf());
            req.setAttribute("nomeAgenciada",termoEstagio.getNomeAgenciada());

        }else{
            req.setAttribute("cvNome", termoEstagio.getConvenio().getEmpresa().getRazaoSocial());
            req.setAttribute("tConvenio","pj");
            req.setAttribute("agIntegracao",termoEstagio.getConvenio().getEmpresa().isAgenteIntegracao());
            req.setAttribute("cvCpfCnpj", termoEstagio.getConvenio().getEmpresa().getCnpjEmpresa());
            req.setAttribute("nomeAgenciada",termoEstagio.getNomeAgenciada());
        }

        /** Dados de Vigência */
        req.setAttribute("vidataInicioTermoEstagio",termoEstagio.getDataInicioTermoEstagio2());
        req.setAttribute("vidataFimTermoEstagio",termoEstagio.getDataFimTermoEstagioVisu(termoAditivo));

        /** Dados de Carga Horária */
        req.setAttribute("cacargaHorariaTermoEstagio",termoEstagio.getCargaHorariaTermoEstagioVisu(termoAditivo));

        /** Dados de Valor Bolsa */
        req.setAttribute("vavalorBolsa",termoEstagio.getValorBolsaVisu(termoAditivo));
        /** Dados de Local */
        req.setAttribute("enenderecoTermoEstagio",termoEstagio.getEnderecoTermoEstagioVisu(termoAditivo));
        req.setAttribute("ennumeroEnderecoTermoEstagio",termoEstagio.getNumeroEnderecoTermoEstagioVisu(termoAditivo));
        req.setAttribute("encomplementoEnderecoTermoEstagio",termoEstagio.getComplementoEnderecoTermoEstagioVisu(termoAditivo));
        req.setAttribute("enbairroEnderecoTermoEstagio",termoEstagio.getBairroEnderecoTermoEstagioVisu(termoAditivo));
        req.setAttribute("encidadeEnderecoTermoEstagio",termoEstagio.getCidadeEnderecoTermoEstagioVisu(termoAditivo));
        req.setAttribute("enuf",termoEstagio.getEstadoEnderecoTermoEstagioVisu(termoAditivo));
        req.setAttribute("encepEnderecoTermoEstagio",termoEstagio.getCepEnderecoTermoEstagioVisu(termoAditivo));

        /** Dados de Supervisor */

        req.setAttribute("eobrigatorio",termoEstagio.getEEstagioObrigatorio());
        req.setAttribute("nomeSupervisor",termoEstagio.getNomeSupervisorVisu(termoAditivo));
        req.setAttribute("cargoSupervisor",termoEstagio.getCargoSupervisorVisu(termoAditivo));

        /** Dados de Professor */
        req.setAttribute("pfnomeprofessor",termoEstagio.getProfessorOrientadorVisu(termoAditivo));
                        
        req.getRequestDispatcher("/form_termo_estagio_edita.jsp").forward(req, resp);
    }

   
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Locale locale = ServletUtils.getLocale(request);
        ResourceBundle messages = ResourceBundle.getBundle("Messages", locale);
        
        //OBRIGATÓRIO
        String idTermoEstagio               = (String) request.getParameter("idTermoEstagio");
        Date dataInicioTermoEstagio         = new Date(request.getParameter("dataInicioTermoEstagio"));        
        Date dataFimTermoEstagio            = new Date(request.getParameter("dataFimTermoEstagio"));
        Integer cargaHorariaTermoEstagio    = Integer.parseInt(request.getParameter("cargaHorariaTermoEstagio"));
        Float valorBolsa                    = Float.parseFloat(request.getParameter("valorBolsa"));
        String enderecoTermoEstagio         = (String)request.getParameter("enderecoTermoEstagio");
        String numeroEnderecoTermoEstagio   = (String)request.getParameter("numeroEnderecoTermoEstagio");
        String complementoEnderecoTermoEstagio = (String)request.getParameter("complementoEnderecoTermoEstagio");
        String bairroEnderecoTermoEstagio   = (String)request.getParameter("bairroEnderecoTermoEstagio");
        String cepEnderecoTermoEstagio      = (String)request.getParameter("cepEnderecoTermoEstagio");
        String cidadeEnderecoTermoEstagio   = (String)request.getParameter("cidadeEnderecoTermoEstagio");
        String estadoEnderecoTermoEstagio   = (String)request.getParameter("estadoEnderecoTermoEstagio");
        Boolean eEstagioObrigatorio         = ((String)(request.getParameter("eobrigatorio"))).equals("sim") ? true:false;

        String nomeSupervisor               = request.getParameter("nomeSupervisor");
        String cargoSupervisor              = request.getParameter("cargoSupervisor");    
        String nomeAgenciada                = request.getParameter("nomeAgenciada");                

               
        
        Aluno alunoAux = AlunoServices.buscarAluno(new Aluno(Integer.parseInt((String)request.getParameter("idAluno"))));
        Convenio convenioAux = ConvenioServices.buscarConvenioByNumeroConvenio((String)request.getParameter("idConvenio"));
        ProfessorOrientador profAux = ProfessorOrientadorServices.buscarProfessorOrientador(new ProfessorOrientador(Integer.parseInt((String)request.getParameter("idProfessorOrientador"))));
        
        TermoEstagio termoEstagio=TermoEstagioServices.buscarTermoEstagio(Integer.parseInt((String)request.getParameter("idEstagio")));
        
        // SETANDO NOVOS VALORES NO OBJETO
        termoEstagio.setDataInicioTermoEstagio(dataInicioTermoEstagio);
        termoEstagio.setDataFimTermoEstagio(dataFimTermoEstagio);
        termoEstagio.setCargaHorariaTermoEstagio(cargaHorariaTermoEstagio);
        termoEstagio.setValorBolsa(valorBolsa);
        termoEstagio.setEnderecoTermoEstagio(enderecoTermoEstagio);
        termoEstagio.setNumeroEnderecoTermoEstagio(numeroEnderecoTermoEstagio);
        termoEstagio.setComplementoEnderecoTermoEstagio(complementoEnderecoTermoEstagio);
        termoEstagio.setBairroEnderecoTermoEstagio(bairroEnderecoTermoEstagio);
        termoEstagio.setCepEnderecoTermoEstagio(cepEnderecoTermoEstagio);
        termoEstagio.setCidadeEnderecoTermoEstagio(cidadeEnderecoTermoEstagio);
        termoEstagio.setEstadoEnderecoTermoEstagio(estadoEnderecoTermoEstagio);
        termoEstagio.setEEstagioObrigatorio(eEstagioObrigatorio);
        termoEstagio.setAluno(alunoAux);
        termoEstagio.setConvenio(convenioAux);
        termoEstagio.setNomeAgenciada(nomeAgenciada);
        termoEstagio.setProfessorOrientador(profAux);

        String msg = "";
        Logger lg = Logger.getLogger(FormEditarTermoEstagioServlet.class);
        try{

                TermoEstagioServices.alterarTermoEstagio(termoEstagio);
                msg = messages.getString("br.cefetrj.sisgee.alterar_termo_estagio_servlet.sucess");
                
                request.setAttribute("msg", msg);

                lg.info(msg);
                request.getRequestDispatcher("/index.jsp").forward(request, response);			


        }catch(Exception e) {
                msg = messages.getString("br.cefetrj.sisgee.alterar_termo_estagio_servlet.msg_ocorreuErro");
                request.setAttribute("msg", msg);
                System.out.println("Erro no Try Catch do IncluirTermoEstagioServlet " + e);
                lg.error("Exception ao tentar inserir o Termo de Estágio", e);
                request.getRequestDispatcher("FormTermoEstagioServlet").forward(request, response);			

        }

        System.out.println(msg);

    }
}
