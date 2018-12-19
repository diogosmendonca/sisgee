package br.cefetrj.sisgee.view.convenio;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import br.cefetrj.sisgee.view.utils.ServletUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * @author Matheus
 */
@WebServlet("/Alterar_ExcluirConvenioNaoPermitido")
public class AlterarExcluirConvenioNaoPermitido extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Locale locale = ServletUtils.getLocale(request);
        ResourceBundle messages = ResourceBundle.getBundle("Messages", locale);
        String invalidoMsg = messages.getString("br.cefetrj.sisgee.excluir_convenio_servlet.msg_exclusao_alteracao_invalida");
        request.setAttribute("erroBuscar", invalidoMsg);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
