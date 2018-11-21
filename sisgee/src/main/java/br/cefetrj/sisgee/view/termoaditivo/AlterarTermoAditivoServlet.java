/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetrj.sisgee.view.termoaditivo;

import br.cefetrj.sisgee.control.AlunoServices;
import br.cefetrj.sisgee.control.ProfessorOrientadorServices;
import br.cefetrj.sisgee.control.TermoAditivoServices;
import br.cefetrj.sisgee.control.TermoEstagioServices;
import br.cefetrj.sisgee.model.entity.Aluno;
import br.cefetrj.sisgee.model.entity.ProfessorOrientador;
import br.cefetrj.sisgee.model.entity.TermoAditivo;
import br.cefetrj.sisgee.model.entity.TermoEstagio;
import br.cefetrj.sisgee.view.utils.ServletUtils;
import br.cefetrj.sisgee.view.utils.UF;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author mathe
 */
@WebServlet("/AlterarTermoAditivo")
public class AlterarTermoAditivoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        String ide = req.getParameter("ide");
        String ida = req.getParameter("ida");
        String matricula = req.getParameter("matricula");
        UF[] uf = UF.asList();
        TermoEstagio termoEstagio = null;
        TermoAditivo termoAditivo = null;

        Aluno aluno = AlunoServices.buscarAlunoByMatricula(matricula);

        try {
            if (ide != null) {
                termoEstagio = TermoEstagioServices.buscarTermoEstagio(Integer.parseInt(ide));
            }
            if (ida != null) {
                termoAditivo = TermoAditivoServices.buscarTermoAditivo(Integer.parseInt(ida));
            }

            if (termoAditivo != null && termoEstagio.getTermosAditivos().get(termoEstagio.getTermosAditivos().size() - 1).getIdTermoAditivo().equals(termoAditivo.getIdTermoAditivo()) && termoEstagio.getAluno().getMatricula().equals(matricula)) {

                String vigencia = "";
                String carga = "";
                String valorBolsa = "";
                String endereco = "";
                String supervisor = "";
                String professor = "";

                switch (termoAditivo.getTipoAditivo()) {
                    case "Vigência":
                        vigencia = "sim";
                        break;
                    case "Carga Horária":
                        carga = "sim";
                        break;
                    case "Professor Orientador":
                        professor = "sim";
                        break;
                    case "Valor da Bolsa":
                        valorBolsa = "sim";
                        break;
                    case "Local Estágio":
                        endereco = "sim";
                        break;
                    case "Supervisor":
                        supervisor = "sim";
                        break;
                }

                req.setAttribute("showVigencia", vigencia);
                req.setAttribute("showCargaHoraria", carga);
                req.setAttribute("showValorBolsa", valorBolsa);
                req.setAttribute("showLocal", endereco);
                req.setAttribute("showSupervisor", supervisor);
                req.setAttribute("showProfessor", professor);

                /**
                 * Dados de aluno
                 */
                req.setAttribute("alMatricula", aluno.getMatricula());
                req.setAttribute("alNome", aluno.getPessoa().getNome());
                req.setAttribute("alCampus", aluno.getCurso().getCampus().getNomeCampus());
                req.setAttribute("alCurso", aluno.getCurso());
                req.setAttribute("alId", aluno.getIdAluno());

                /**
                 * Dados de convenio
                 */
                req.setAttribute("cvNumero", termoEstagio.getConvenio().getNumeroConvenio());
                if (termoEstagio.getConvenio().getEmpresa() == null) {
                    req.setAttribute("cvNome", termoEstagio.getConvenio().getPessoa().getNome());
                    req.setAttribute("tConvenio", "pf");
                    req.setAttribute("cvCpfCnpj", termoEstagio.getConvenio().getPessoa().getCpf());
                    req.setAttribute("nomeAgenciada", termoEstagio.getNomeAgenciada());

                } else {
                    req.setAttribute("cvNome", termoEstagio.getConvenio().getEmpresa().getRazaoSocial());
                    req.setAttribute("tConvenio", "pj");
                    req.setAttribute("agIntegracao", termoEstagio.getConvenio().getEmpresa().isAgenteIntegracao());
                    req.setAttribute("cvCpfCnpj", termoEstagio.getConvenio().getEmpresa().getCnpjEmpresa());
                    req.setAttribute("nomeAgenciada", termoEstagio.getNomeAgenciada());
                }

                /**
                 * Dados de Vigência
                 */
                req.setAttribute("vidataInicioTermoEstagio", termoEstagio.getDataInicioTermoEstagio2());
                req.setAttribute("vidataFimTermoEstagio", termoEstagio.getDataFimTermoEstagioVisu(termoAditivo));

                /**
                 * Dados de Carga Horária
                 */
                req.setAttribute("cacargaHorariaTermoEstagio", termoEstagio.getCargaHorariaTermoEstagioVisu(termoAditivo));

                /**
                 * Dados de Valor Bolsa
                 */
                req.setAttribute("vavalorBolsa", termoEstagio.getValorBolsaVisu(termoAditivo));
                System.out.println("aqui" + termoEstagio.getValorBolsaVisu(termoAditivo));
                /**
                 * Dados de Local
                 */
                req.getServletContext().setAttribute("enenderecoTermoEstagio", termoEstagio.getEnderecoTermoEstagioVisu(termoAditivo));
                req.setAttribute("ennumeroEnderecoTermoEstagio", termoEstagio.getNumeroEnderecoTermoEstagioVisu(termoAditivo));
                req.setAttribute("encomplementoEnderecoTermoEstagio", termoEstagio.getComplementoEnderecoTermoEstagioVisu(termoAditivo));
                req.setAttribute("enbairroEnderecoTermoEstagio", termoEstagio.getBairroEnderecoTermoEstagioVisu(termoAditivo));
                req.setAttribute("encidadeEnderecoTermoEstagio", termoEstagio.getCidadeEnderecoTermoEstagioVisu(termoAditivo));
                req.setAttribute("enuf", termoEstagio.getEstadoEnderecoTermoEstagioVisu(termoAditivo));
                req.setAttribute("encepEnderecoTermoEstagio", termoEstagio.getCepEnderecoTermoEstagioVisu(termoAditivo));

                /**
                 * Dados de Supervisor
                 */
                req.setAttribute("eobrigatorio", termoEstagio.getEEstagioObrigatorio());
                req.setAttribute("nomeSupervisor", termoEstagio.getNomeSupervisorVisu(termoAditivo));
                req.setAttribute("cargoSupervisor", termoEstagio.getCargoSupervisorVisu(termoAditivo));
                /**
                 * Dados de Professor
                 */
                req.setAttribute("pfnomeprofessor", termoEstagio.getProfessorOrientadorVisu(termoAditivo));

                List<ProfessorOrientador> professores = ProfessorOrientadorServices.listarProfessorOrientador();
                req.setAttribute("professores", professores);
                req.setAttribute("idTermoEstagio", termoEstagio.getIdTermoEstagio());
                req.setAttribute("idTermoAditivo", termoAditivo.getIdTermoAditivo());
                req.setAttribute("uf", uf);
                req.getRequestDispatcher("/form_termo_edita_aditivo.jsp").forward(req, response);
            } else {
                Locale locale = ServletUtils.getLocale(req);
                ResourceBundle messages = ResourceBundle.getBundle("Messages", locale);
                String msg = messages.getString("br.cefetrj.sisgee.form_alterar_termo_aditivo.msg_erro");
                req.setAttribute("msg", msg);
                req.getRequestDispatcher("/form_termo_aditivo.jsp").forward(req, response);
            }

        } catch (NullPointerException e) {
            Locale locale = ServletUtils.getLocale(req);
            ResourceBundle messages = ResourceBundle.getBundle("Messages", locale);
            String msg = messages.getString("br.cefetrj.sisgee.form_alterar_termo_aditivo.msg_erro_irregular");
            req.setAttribute("msg", msg);
            req.getRequestDispatcher("/form_termo_aditivo.jsp").forward(req, response);
        }
    }
}
