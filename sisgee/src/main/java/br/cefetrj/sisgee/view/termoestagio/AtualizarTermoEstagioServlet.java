package br.cefetrj.sisgee.view.termoestagio;

import br.cefetrj.sisgee.control.AlunoServices;
import br.cefetrj.sisgee.control.ConvenioServices;
import br.cefetrj.sisgee.control.TermoEstagioServices;
import br.cefetrj.sisgee.model.entity.AgenteIntegracao;
import br.cefetrj.sisgee.model.entity.Aluno;
import br.cefetrj.sisgee.model.entity.Convenio;
import br.cefetrj.sisgee.model.entity.ProfessorOrientador;
import br.cefetrj.sisgee.model.entity.TermoEstagio;
import br.cefetrj.sisgee.view.utils.ServletUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 * Servlet que persiste as alterações feitas no termo estágio
 * @author Matheus Silva e Yasmin Cardoso
 */
@WebServlet("/AtualizarTermoEstagioServlet")
public class AtualizarTermoEstagioServlet extends HttpServlet {

    /**
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Locale locale = ServletUtils.getLocale(request);
        ResourceBundle messages = ResourceBundle.getBundle("Messages", locale);

        //OBRIGATÓRIO
        Date dataInicioTermoEstagio = (Date) request.getAttribute("dataInicio");
        Integer cargaHorariaTermoEstagio = (Integer) request.getAttribute("cargaHoraria");
        Float valorBolsa = (Float) request.getAttribute("valor");
        String enderecoTermoEstagio = (String) request.getAttribute("enderecoTermoEstagio");
        String numeroEnderecoTermoEstagio = (String) request.getAttribute("numeroEnderecoTermoEstagio");
        String complementoEnderecoTermoEstagio = (String) request.getAttribute("complementoEnderecoTermoEstagio");
        String bairroEnderecoTermoEstagio = (String) request.getAttribute("bairroEnderecoTermoEstagio");
        String cepEnderecoTermoEstagio = (String) request.getAttribute("cepEnderecoTermoEstagio");
        String cidadeEnderecoTermoEstagio = (String) request.getAttribute("cidadeEnderecoTermoEstagio");
        String estadoEnderecoTermoEstagio = (String) request.getAttribute("estadoEnderecoTermoEstagio");
        Boolean eEstagioObrigatorio = (Boolean) request.getAttribute("obrigatorio");

        String nomeSupervisor = request.getParameter("nomeSupervisor");
        String cargoSupervisor = request.getParameter("cargoSupervisor");
        String nomeAgenciada = request.getParameter("nomeAgenciada");
        
        String matricula = (String)request.getAttribute("almatricula");
        Aluno aluno = AlunoServices.buscarAlunoByMatricula(matricula);
        System.out.println(matricula + "\n" +aluno);
        

        //OBRIGATÓRIO
        String convenionum = (String)request.getAttribute("numConvenio");
        System.out.print("numConvenio>" + convenionum);
        Convenio convenio = ConvenioServices.buscarConvenioByNumeroConvenio(convenionum);
        //NÃO OBRIGATÓRIO
        Boolean hasDataFim = (Boolean) request.getAttribute("hasDataFim");
        Boolean hasProfessor = (Boolean) request.getAttribute("hasProfessor");

        Date dataFimTermoEstagio = null;
        ProfessorOrientador professorOrientador = null;
        AgenteIntegracao agenteIntegracao = null;
        dataFimTermoEstagio = (Date) request.getAttribute("dataFim");
        professorOrientador = new ProfessorOrientador((Integer) request.getAttribute("idProfessor"));

        TermoEstagio termoEstagio = new TermoEstagio(dataInicioTermoEstagio, dataFimTermoEstagio, cargaHorariaTermoEstagio,
                valorBolsa, enderecoTermoEstagio, numeroEnderecoTermoEstagio,
                complementoEnderecoTermoEstagio, bairroEnderecoTermoEstagio, cepEnderecoTermoEstagio,
                cidadeEnderecoTermoEstagio, estadoEnderecoTermoEstagio, eEstagioObrigatorio,
                aluno, convenio, professorOrientador, nomeSupervisor, cargoSupervisor, nomeAgenciada);

        String msg = "";
        Logger lg = Logger.getLogger(AtualizarTermoEstagioServlet.class);
        termoEstagio.setIdTermoEstagio(Integer.parseInt(request.getParameter("idTermoEstagio")));
        try {
            TermoEstagioServices.alterarTermoEstagio(termoEstagio);
            msg = messages.getString("br.cefetrj.sisgee.alterar_termo_estagio_servlet.sucess");
            request.setAttribute("msg", msg);
            lg.info(msg);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (Exception e) {
            msg = messages.getString("br.cefetrj.sisgee.alterar_termo_estagio_servlet.msg_ocorreuErro");
            request.setAttribute("msg", msg);
            System.out.println("Erro no Try Catch do atualizarTermoEstagioServlet " + e);
            lg.error("Exception ao tentar alterar o Termo de Estágio", e);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
        System.out.println(msg);
    }
}
