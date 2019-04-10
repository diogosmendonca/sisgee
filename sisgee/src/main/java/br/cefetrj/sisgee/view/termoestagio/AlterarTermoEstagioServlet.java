package br.cefetrj.sisgee.view.termoestagio;

import br.cefetrj.sisgee.control.AlunoServices;
import br.cefetrj.sisgee.control.ProfessorOrientadorServices;
import br.cefetrj.sisgee.control.TermoAditivoServices;
import br.cefetrj.sisgee.control.TermoEstagioServices;
import br.cefetrj.sisgee.model.entity.Aluno;
import br.cefetrj.sisgee.model.entity.ProfessorOrientador;
import br.cefetrj.sisgee.model.entity.TermoEstagio;
import br.cefetrj.sisgee.view.utils.ServletUtils;
import br.cefetrj.sisgee.view.utils.UF;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * @author Matheus Silva e Yasmin Cardoso
 */
@WebServlet("/AlterarTermoEstagioServlet")
public class AlterarTermoEstagioServlet extends HttpServlet {

    /*
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String ide = request.getParameter("ide");
        String matricula = request.getParameter("matricula");
        UF[] uf = UF.asList();
        TermoEstagio termoEstagio = null;

        Aluno aluno = AlunoServices.buscarAlunoByMatricula(matricula);

        if (ide != null) {
            termoEstagio = TermoEstagioServices.buscarTermoEstagio(Integer.parseInt(ide));
            request.setAttribute("idTermoEstagio", ide);
        }

        if (termoEstagio.getAluno().equals(aluno) && termoEstagio.getTermosAditivos().isEmpty()) {
            /**
             * Dados de aluno
             */
            request.setAttribute("alMatricula", aluno.getMatricula());
            request.setAttribute("alNome", aluno.getPessoa().getNome());
            request.setAttribute("alCampus", aluno.getCurso().getCampus().getNomeCampus());
            request.setAttribute("alCurso", aluno.getCurso());
            request.setAttribute("alId", aluno.getIdAluno());
            /**
             * Dados de convenio
             */
            request.setAttribute("cvId", termoEstagio.getConvenio().getIdConvenio());
            request.setAttribute("cvNumero", termoEstagio.getConvenio().getNumero());
            if (termoEstagio.getConvenio().getEmpresa() == null) {
                request.setAttribute("cvNome", termoEstagio.getConvenio().getPessoa().getNome());
                request.setAttribute("tipoConvenio", "Pessoa Fisica");
                request.setAttribute("cvCpfCnpj", termoEstagio.getConvenio().getPessoa().getCpf());
                request.setAttribute("nomeAgenciada", termoEstagio.getNomeAgenciada());
            } else {
                request.setAttribute("cvNome", termoEstagio.getConvenio().getEmpresa().getRazaoSocial());
                request.setAttribute("tipoConvenio", "Pessoa Juridica");
                if (termoEstagio.getConvenio().getEmpresa().isAgenteIntegracao()) {
                    request.setAttribute("agIntegracao", "SIM");
                } else {
                    request.setAttribute("agIntegracao", "NÃO");
                }
                request.setAttribute("cvCpfCnpj", termoEstagio.getConvenio().getEmpresa().getCnpjEmpresa());
                request.setAttribute("nomeAgenciada", termoEstagio.getNomeAgenciada());
            }

            /**
             * Dados de Vigência
             */
            request.setAttribute("vidataInicioTermoEstagio", termoEstagio.getDataInicioTermoEstagio2());
            try {
                request.setAttribute("vidataFimTermoEstagio", termoEstagio.getDataFimTermoEstagio3());
                System.out.println("Data Fim>" + termoEstagio.getDataFimTermoEstagio3());
            } catch (ParseException e) {
                System.out.println("Parse Error");
            }

            /**
             * Dados de Carga Horária
             */
            request.setAttribute("cacargaHorariaTermoEstagio", termoEstagio.getCargaHorariaTermoEstagio());

            /**
             * Dados de Valor Bolsa
             */
            String valorFormatado = String.format("%.2f", termoEstagio.getValorBolsa());
            valorFormatado = valorFormatado.replace(".", ",");
            request.setAttribute("vavalorBolsa", valorFormatado);
            System.out.print("aqui" + termoEstagio.getValorBolsa());
            System.out.println("   + Valor Formatado:" + valorFormatado);
            /**
             * Dados de Local
             */
            request.getServletContext().setAttribute("enenderecoTermoEstagio", termoEstagio.getEnderecoTermoEstagio());
            request.setAttribute("ennumeroEnderecoTermoEstagio", termoEstagio.getNumeroEnderecoTermoEstagio());
            request.setAttribute("encomplementoEnderecoTermoEstagio", termoEstagio.getComplementoEnderecoTermoEstagio());
            request.setAttribute("enbairroEnderecoTermoEstagio", termoEstagio.getBairroEnderecoTermoEstagio());
            request.setAttribute("encidadeEnderecoTermoEstagio", termoEstagio.getCidadeEnderecoTermoEstagio());
            request.setAttribute("enuf", termoEstagio.getEstadoEnderecoTermoEstagio());
            request.setAttribute("encepEnderecoTermoEstagio", termoEstagio.getCepEnderecoTermoEstagio());

            /**
             * Dados de Supervisor
             */
            if (termoEstagio.getEEstagioObrigatorio()) {
                request.setAttribute("eobrigatorio", "sim");
            } else {
                request.setAttribute("eobrigatorio", "nao");
            }
            request.setAttribute("nomeSupervisor", termoEstagio.getNomeSupervisor());
            request.setAttribute("cargoSupervisor", termoEstagio.getCargoSupervisor());
            /**
             * Dados de Professor
             */
            request.setAttribute("pfnomeprofessor", termoEstagio.getProfessorOrientador());

            List<ProfessorOrientador> professores = ProfessorOrientadorServices.listarProfessorOrientador();
            request.setAttribute("professores", professores);
            if(termoEstagio.getProfessorOrientador() != null){
                request.setAttribute("professorSelecionado", termoEstagio.getProfessorOrientador().getIdProfessorOrientador());
                System.out.println("ID PROF>" + termoEstagio.getProfessorOrientador().getIdProfessorOrientador());
            }
            request.setAttribute("idTermoEstagio", termoEstagio.getIdTermoEstagio());
            request.setAttribute("uf", uf);
            request.getRequestDispatcher("/form_termo_edita_estagio.jsp").forward(request, response);
        } else {
            Locale locale = ServletUtils.getLocale(request);
            ResourceBundle messages = ResourceBundle.getBundle("Messages", locale);
            String msg = messages.getString("br.cefetrj.sisgee.alterar_termo_estagio_servlet.msg_ocorreuErro");
            request.setAttribute("msg", msg);
            request.getRequestDispatcher("/form_termo_aditivo.jsp").forward(request, response);
        }
    }
}
