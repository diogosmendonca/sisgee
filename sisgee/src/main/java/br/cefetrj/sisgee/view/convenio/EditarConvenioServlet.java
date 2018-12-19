package br.cefetrj.sisgee.view.convenio;

import br.cefetrj.sisgee.control.ConvenioServices;
import br.cefetrj.sisgee.model.entity.Convenio;
import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Matheus Silva
 */
@WebServlet(name = "EditarConvenioServlet", urlPatterns = {"/EditarConvenioServlet"})
public class EditarConvenioServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String numeroConvenio = request.getParameter("convenio");

        Convenio convenio = ConvenioServices.buscarConvenioByNumeroConvenio(numeroConvenio);
        if (convenio != null && convenio.getTermoEstagios().isEmpty()) {
            if (convenio.getEmpresa() != null) {
                request.setAttribute("isEmpresa", "sim");
                if (convenio.getEmpresa().isAgenteIntegracao()) {
                    request.setAttribute("simAgenteIntegracao", "sim");
                } else {
                    request.setAttribute("naoAgenteIntegracao", "sim");
                }
                request.setAttribute("dataRegistroConvenioEmpresa", formataData(convenio.getDataRegistro()));
                request.setAttribute("cnpjEmpresa", convenio.getEmpresa().getCnpjEmpresa());
                request.setAttribute("nomeEmpresa", convenio.getEmpresa().getRazaoSocial());
                request.setAttribute("emailEmpresa", convenio.getEmpresa().getEmailEmpresa());
                request.setAttribute("telefoneEmpresa", convenio.getEmpresa().getTelefoneEmpresa());
                request.setAttribute("contatoEmpresa", convenio.getEmpresa().getContatoEmpresa());
                request.setAttribute("numeroEmpresa", convenio.getNumero());
                request.setAttribute("anoEmpresa", convenio.getAno());
            } else {
                request.setAttribute("isPessoa", "sim");
                request.setAttribute("cpfPessoa", convenio.getPessoa().getCpf());
                request.setAttribute("nomePessoa", convenio.getPessoa().getNome());
                request.setAttribute("dataRegistroConvenioPessoa", formataData(convenio.getDataRegistro()));
                request.setAttribute("emailPessoa", convenio.getPessoa().getEmail());
                request.setAttribute("telefonePessoa", convenio.getPessoa().getTelefone());
                request.setAttribute("numeroPessoa", convenio.getNumero());
                request.setAttribute("anoPessoa", convenio.getAno());
            }
            request.setAttribute("numeroSugerido", convenio.getNumero());
            request.setAttribute("convenioRenovar", convenio);
            request.getRequestDispatcher("form_edita_empresa.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("Alterar_ExcluirConvenioNaoPermitido").forward(request, response);
        }
    }
    
    private String formataData(Date data){
        String dataFormatada = data.toString();
        String ano = dataFormatada.substring(0,4);
        String mes = dataFormatada.substring(5,7);
        String dia = dataFormatada.substring(8);
        dataFormatada = dia.concat("/" + mes).concat("/" + ano);
        System.out.println(dataFormatada);
        return dataFormatada;
    }
}
