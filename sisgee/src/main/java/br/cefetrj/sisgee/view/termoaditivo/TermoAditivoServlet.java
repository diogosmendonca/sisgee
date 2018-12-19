package br.cefetrj.sisgee.view.termoaditivo;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.cefetrj.sisgee.control.AlunoServices;
import br.cefetrj.sisgee.control.ProfessorOrientadorServices;
import br.cefetrj.sisgee.control.TermoAditivoServices;
import br.cefetrj.sisgee.model.entity.Aluno;
import br.cefetrj.sisgee.model.entity.ProfessorOrientador;
import br.cefetrj.sisgee.model.entity.TermoAditivo;
import br.cefetrj.sisgee.model.entity.TermoEstagio;
import br.cefetrj.sisgee.view.utils.ServletUtils;
import br.cefetrj.sisgee.view.utils.UF;
import br.cefetrj.sisgee.view.utils.ValidaUtils;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/*
 * @author Vinicius Paradellas
 * @since 1.1
 *
 */
@WebServlet("/TermoAditivoServlet")
public class TermoAditivoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Locale locale = ServletUtils.getLocale(request);
        ResourceBundle messages = ResourceBundle.getBundle("Messages", locale);
        final DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        final Calendar cal = Calendar.getInstance();

        String vigencia = request.getParameter("alvigencia");
        String carga = request.getParameter("alcargaHoraria");
        String valorBolsa = request.getParameter("alvalor");
        String endereco = request.getParameter("alendereco");
        String supervisor = request.getParameter("alsupervisor");
        String professor = request.getParameter("alprofessor");

        String idAluno = request.getParameter("idAlunoAdt");
        System.out.println("Idaluno :" + idAluno);

        Aluno aluno = null;
        TermoEstagio termoEstagio = null;
        List<TermoAditivo> termosAditivos = null;
        TermoAditivo termoAditivo = null;

        boolean isValid = true;
        String campo = "";
        String msg = "";

        /**
         * Validação do Id do Aluno, usando métodos da Classe ValidaUtils.
         * Instanciando o objeto e pegando o TermoEstagio válido (sem data de
         * rescisão)
         */
        String idAlunoMsg = "";
        campo = "Aluno";
        idAlunoMsg = ValidaUtils.validaObrigatorio(campo, idAluno);
        if (idAlunoMsg.trim().isEmpty()) {
            if (idAlunoMsg.trim().isEmpty()) {
                aluno = AlunoServices.buscarAlunoByMatricula(idAluno);
                System.out.println(aluno);
                if (aluno != null) {
                    termoEstagio = aluno.getTermoEstagios().get(aluno.getTermoEstagios().size() - 1);
                    System.out.println(termoEstagio);
                }

            } else {
                idAlunoMsg = messages.getString(idAlunoMsg);
                request.setAttribute("idAlunoMsg", idAlunoMsg);
                isValid = false;
            }
            System.out.println("que isso :" + TermoAditivoServices.listarTermoAditivo());
            try {
                if (termoEstagio != null && (aluno.getTermoEstagios().get(aluno.getTermoEstagios().size() - 1).getDataFimTermoEstagio().compareTo(cal.getTime()) > 0) && termoEstagio.getEstado().equals("Ativo")) {
                    //TODO implementar lógica de encaminhamento para a tela de registro
                    termosAditivos = termoEstagio.getTermosAditivos();
                    if (termosAditivos != null && !termosAditivos.isEmpty()) {
                        termoAditivo = termosAditivos.get(termosAditivos.size() - 1);
                    }

                    // se existe algum termo aditivo para o termo estagio
                    if (termoAditivo != null) {
                        termoEstagio = TermoAditivoServices.termoEstagioAtualizadoByTermoAditivo(termoAditivo);
                    }

                    List<ProfessorOrientador> professores = ProfessorOrientadorServices.listarProfessorOrientador();
                    UF[] uf = UF.asList();

                    request.setAttribute("uf", uf);

                    /**
                     * Dados de aluno
                     */
                    request.setAttribute("alMatricula", aluno.getMatricula());
                    request.setAttribute("alNome", aluno.getPessoa().getNome());
                    request.setAttribute("alCampus", aluno.getCurso().getCampus().getNomeCampus());
                    request.setAttribute("alCurso", aluno.getCurso());

                    /**
                     * Dados de convenio
                     */
                    request.setAttribute("cvNumero", termoEstagio.getConvenio().getNumeroConvenio());
                    if (termoEstagio.getConvenio().getEmpresa() == null) {
                        request.setAttribute("cvNome", termoEstagio.getConvenio().getPessoa().getNome());
                        request.setAttribute("tConvenio", "pf");
                        request.setAttribute("cvCpfCnpj", termoEstagio.getConvenio().getPessoa().getCpf());
                        request.setAttribute("nomeAgenciada", termoEstagio.getNomeAgenciada());

                    } else {
                        request.setAttribute("cvNome", termoEstagio.getConvenio().getEmpresa().getRazaoSocial());
                        request.setAttribute("tConvenio", "pj");
                        request.setAttribute("agIntegracao", termoEstagio.getConvenio().getEmpresa().isAgenteIntegracao());
                        request.setAttribute("cvCpfCnpj", termoEstagio.getConvenio().getEmpresa().getCnpjEmpresa());
                        request.setAttribute("nomeAgenciada", termoEstagio.getNomeAgenciada());
                    }

                    /**
                     * Dados de Vigência
                     */
                    request.setAttribute("vidataInicioTermoEstagio", termoEstagio.getDataInicioTermoEstagio2());
                    request.setAttribute("vidataFimTermoEstagio", termoEstagio.getDataFimTermoEstagio2());

                    /**
                     * Dados de Carga Horária
                     */
                    request.setAttribute("cacargaHorariaTermoEstagio", termoEstagio.getCargaHorariaTermoEstagio());

                    /**
                     * Dados de Valor Bolsa
                     */
                    request.setAttribute("vavalorBolsa", termoEstagio.getValorBolsa());

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
                    request.setAttribute("eobrigatorio", termoEstagio.getEEstagioObrigatorio());
                    request.setAttribute("nomeSupervisor", termoEstagio.getNomeSupervisor());
                    request.setAttribute("cargoSupervisor", termoEstagio.getCargoSupervisor());

                    /**
                     * Dados de Professor
                     */
                    request.setAttribute("pfnomeprofessor", termoEstagio.getProfessorOrientador());

                    request.setAttribute("termoEstagio", termoEstagio);

                    request.setAttribute("showVigencia", vigencia);
                    request.setAttribute("showCargaHoraria", carga);
                    request.setAttribute("showValorBolsa", valorBolsa);
                    request.setAttribute("showLocal", endereco);
                    request.setAttribute("showSupervisor", supervisor);
                    request.setAttribute("showProfessor", professor);
                    request.setAttribute("professores", professores);
                } else {
                    msg = messages.getString("br.cefetrj.sisgee.form_termo_aditivo_servlet.msg_termo_estagio_invalido");
                    request.setAttribute("msg", msg);
                    isValid = false;
                }

                if (isValid) {
                    request.getRequestDispatcher("/form_termo_adciona_aditivo.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("/form_termo_aditivo.jsp").forward(request, response);
                }

            } catch (Exception e) {

            }
        }

    }
}
