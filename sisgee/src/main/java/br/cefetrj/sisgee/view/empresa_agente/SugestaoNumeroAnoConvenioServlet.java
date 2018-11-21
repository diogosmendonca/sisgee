package br.cefetrj.sisgee.view.empresa_agente;

import br.cefetrj.sisgee.control.ConvenioServices;
import br.cefetrj.sisgee.model.entity.Convenio;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Year;
import java.util.List;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Matheus Silva
 */
@WebServlet("/SugestaoNumeroAnoConvenioServlet")
public class SugestaoNumeroAnoConvenioServlet extends HttpServlet {

    /*
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String numeroSugerido = gerarNumeroConvenio();
        request.setAttribute("numeroEmpresa", numeroSugerido);
        request.setAttribute("numeroPessoa", numeroSugerido);
        request.setAttribute("anoEmpresa", Year.now().getValue());
        request.setAttribute("anoPessoa", Year.now().getValue());
        request.setAttribute("numeroSugerido", numeroSugerido);
        request.getRequestDispatcher("form_empresa.jsp").forward(request, response);
    }

    private String gerarNumeroConvenio() {
        List<Convenio> x = ConvenioServices.listarConvenios();
        int sugestao = x.size() + 1;
        boolean numValido = true;
        do {
            numValido = true;
            Convenio conv = ConvenioServices.buscarConvenioByNumeroConvenio(Integer.toString(sugestao));
            if (conv != null) {
                numValido = false;
                sugestao++;
            }
        } while (!numValido);
        String numero = String.valueOf(sugestao);
        return numero;
    }
}
