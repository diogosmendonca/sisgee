package br.cefetrj.sisgee.view.relatorio;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.cefetrj.sisgee.view.utils.ValidaUtils;

/**
 *
 * Servlet de validacao do form em relatorio_consolidado.jsp. E retorna
 * mensagens de correcao.
 *
 * @author Marcos E Carvalho
 * @since 1.0
 *
 */
@WebServlet("/ValidaRelatorioConsolidadoServlet")
public class ValidaRelatorioConsolidadoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidaRelatorioConsolidadoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     *
     * @param request um objeto HttpServletRequest que contém a solicitação feita pelo cliente do servlet.
     * @param response um objeto HttpServletResponse que contém a resposta que o servlet envia para o cliente
     * @throws ServletException se o pedido do service não puder ser tratado
     * @throws IOException se um erro de entrada ou saída for detectado quando o servlet manipula o pedido 
     */
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String dataDeInicio = request.getParameter("dataDeInicio");
        String dataDeTermino = request.getParameter("dataDeTermino");
        String estagioObrigatorio = request.getParameter("estagioObrigatorio");
        String estagioNaoObrigatorio = request.getParameter("estagioNaoObrigatorio");

        Locale locale = getLocale(request);

        request.setAttribute("Locale", locale);

        ResourceBundle messages = ResourceBundle.getBundle("Messages", locale);

        String msg = null;
        String msgDataObrig = null;
        String msgCheckEstagio = null;
        String msgComparaDatas = null;
        String msgDataInicio = null;
        String msgDataTermino = null;

        Date dataInicio = null;
        Date dataTermino = null;

        Boolean estagioObrig;
        Boolean estagioNaoObrig;

        Boolean validDate = false;

        msgDataInicio = ValidaUtils.validaDate("", dataDeInicio);
        msgDataTermino = ValidaUtils.validaDate("", dataDeTermino);

        if (!(msgDataInicio.isEmpty())) {
            msgDataInicio = messages.getString("br.cefetrj.sisgee.relatorio.relatorio_consolidado_servlet.alert_data_inicio");
            request.setAttribute("msgDataInicio", msgDataInicio);
            validDate = false;
        } else {
            validDate = true;
        }

        if (!(msgDataTermino.isEmpty())) {
            msgDataTermino = messages.getString("br.cefetrj.sisgee.relatorio.relatorio_consolidado_servlet.alert_data_termino");
            request.setAttribute("msgDataTermino", msgDataTermino);
            validDate = false;
        } else {

            validDate = true;
        }

        if (validDate == false) {
            msgDataObrig = messages.getString("br.cefetrj.sisgee.relatorio.relatorio_consolidado_servlet.msg_data_obrigatoria");
            request.setAttribute("msgDataObrig", msgDataObrig);
            msg += msgDataObrig;
        }

        if (!(dataDeInicio.isEmpty() || dataDeTermino.isEmpty())) {
            try {
                DateFormat format = DateFormat.getDateInstance(DateFormat.SHORT, locale);

                dataInicio = format.parse(dataDeInicio);

                dataTermino = format.parse(dataDeTermino);

            } catch (Exception e) {
                System.out.println("Data em formato incorreto, mesmo após validação na classe ValidaUtils");
            }

            msgComparaDatas = ValidaUtils.validaDatas(dataInicio, dataTermino);
            if (msgComparaDatas != "") {
                msgComparaDatas = messages.getString("br.cefetrj.sisgee.relatorio.relatorio_consolidado_servlet.msg_valida_datas");
                request.setAttribute("msgComparaDatas", msgComparaDatas);
                msg += msgComparaDatas;
            } else {
                request.setAttribute("dataInicio", dataInicio);
                request.setAttribute("dataTermino", dataTermino);

            }
        } else {
            msgDataObrig = messages.getString("br.cefetrj.sisgee.relatorio.relatorio_consolidado_servlet.msg_data_obrigatoria");
            request.setAttribute("msgDataObrig", msgDataObrig);
            msg += msgDataObrig;

        }

        if (estagioObrigatorio == null && estagioNaoObrigatorio == null) {
            msgCheckEstagio = messages.getString("br.cefetrj.sisgee.relatorio.relatorio_consolidado_servlet.msg_check_estagio_not_null");

            request.setAttribute("msgCheckEstagio", msgCheckEstagio);
            msg += msgCheckEstagio;

        } else {
            if (estagioObrigatorio != null) {
                estagioObrig = true;
            } else {
                estagioObrig = false;
            }
            if (estagioNaoObrigatorio != null) {
                estagioNaoObrig = true;
            } else {
                estagioNaoObrig = false;
            }

            request.setAttribute("estagioObrig", estagioObrig);
            request.setAttribute("estagioNaoObrig", estagioNaoObrig);
        }

        if (msg != null) {
            request.getRequestDispatcher("/relatorio_consolidado.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/BuscaRelatorioConsolidadoServlet").forward(request, response);
        }

    }

    /**
     *
     * Metodo para receber o local de formatacao a ser usado na aplicacao.
     *
     * @param request HttpServlet
     * @return locale
     */
    private Locale getLocale(HttpServletRequest request) throws ServletException {

        Locale locale = null;

        Object localeFound = request.getParameter("lang");
        if (localeFound == null) {
            localeFound = request.getSession().getAttribute("lang");
        }

        if (localeFound == null) {
            localeFound = request.getLocale();
        }

        if (localeFound instanceof Locale) {
            locale = (Locale) localeFound;
        } else if (localeFound instanceof String) {
            String[] parts = ((String) localeFound).split("_");
            locale = new Locale(parts[0], parts[1].toUpperCase());
        } else {
            throw new ServletException("Locale not found!!!");
        }

        return locale;
    }

}
