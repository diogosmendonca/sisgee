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

import br.cefetrj.sisgee.control.EmpresaServices;
import br.cefetrj.sisgee.control.ConvenioServices;
import br.cefetrj.sisgee.control.PessoaServices;
import br.cefetrj.sisgee.model.entity.Convenio;
import br.cefetrj.sisgee.model.entity.Empresa;
import br.cefetrj.sisgee.model.entity.Pessoa;
import br.cefetrj.sisgee.view.utils.ServletUtils;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.text.MaskFormatter;

/**
 * Servlet para trazer o convenio por meio de requisição AJAX.
 * 
 * @author Claudio
 */
@WebServlet("/BuscaConvenioBotaoServlet")
public class BuscaConvenioBotaoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * 
     * @param request um objeto HttpServletRequest que contém a solicitação feita pelo cliente do servlet.
     * @param response um objeto HttpServletResponse que contém a resposta que o servlet envia para o cliente
     * @throws ServletException se o pedido do service não puder ser tratado
     * @throws IOException se um erro de entrada ou saída for detectado quando o servlet manipula o pedido 
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String numeroConvenio = request.getParameter("numeroConvenio");
        String nome = request.getParameter("nomeConvenio");
        String idConvenio = "";
        Empresa empresa = null;
        String convenioMsg1 = "", convenioMsg2 = "";

        Empresa empresaNome = null;
        Pessoa pessoaNome = null;

        Locale locale = ServletUtils.getLocale(request);
        ResourceBundle messages = ResourceBundle.getBundle("Messages", locale);

        boolean isValid = false;
        Integer tamanho = 0;

        String agenteIntegracao = "NÃO";
        String CPF = "", CNPJ = "";

        Convenio convenio = null;
        List<Convenio> convenios = new ArrayList();
        List<Empresa> empresas = new ArrayList();
        List<Pessoa> pessoas = new ArrayList();

        /**
         * Buscar pelo numero do Convenio
         */
        if (numeroConvenio != null) {
            convenio = ConvenioServices.buscarConvenioByNumeroConvenio(numeroConvenio.trim());
            if (convenio != null) {
                empresaNome = convenio.getEmpresa();
                if (empresaNome != null) {
                    CNPJ = formatString(empresaNome.getCnpjEmpresa(), "##.###.###/####-##");
                    if (empresaNome.isAgenteIntegracao()) {
                        agenteIntegracao = "SIM";
                    }
                }

                pessoaNome = convenio.getPessoa();
                if (pessoaNome != null) {
                    pessoaNome.getNome();
                    CPF = formatString(pessoaNome.getCpf(), "###.###.###-##");
                }
            }
        }
        /**
         * Buscar pelo nome da Empresa/Pessoa
         */
        if (nome != null) {
            if (!nome.equals("")) {

                pessoas = PessoaServices.buscarPessoaByNomeList(nome.trim());

                empresas = EmpresaServices.buscarEmpresaByNomeList(nome.trim());

                if (pessoas != null) {
                    for (Pessoa x : pessoas) {
                        convenio = ConvenioServices.buscarConvenioByPessoa(x);
                        convenios.add(convenio);
                        break;
                    }
                }

                if (empresas != null) {
                    for (Empresa x : empresas) {
                        convenio = ConvenioServices.buscarConvenioByEmpresa(x);
                        convenios.add(convenio);
                        break;
                    }
                }
                System.out.println("br.cefetrj.sisgee.view.AJAX.BuscaConvenioBotaoServlet.doGet() " + convenios.toString());
                if (convenio != null) {
                    System.out.println("ENTROU NO IF DO BuscaConvenioBotaoServlet.doGet() " + convenios.toString());
                    empresaNome = convenio.getEmpresa();
                    if (empresaNome != null) {
                        CNPJ = formatString(empresaNome.getCnpjEmpresa(), "##.###.###/####-##");
                        if (empresaNome.isAgenteIntegracao()) {
                            agenteIntegracao = "SIM";
                        }
                    }

                    pessoaNome = convenio.getPessoa();
                    if (pessoaNome != null) {
                        pessoaNome.getNome();
                        CPF = formatString(pessoaNome.getCpf(), "###.###.###-##");
                    }
                }
            }
        }
        
        convenioMsg1 = "br.cefetrj.sisgee.resources.form.convenio_nao_encontrado1";
        convenioMsg2 = "br.cefetrj.sisgee.resources.form.convenio_nao_encontrado2";

        convenioMsg1 = messages.getString(convenioMsg1);
        convenioMsg2 = messages.getString(convenioMsg2);
        request.setAttribute("convenioMsg1", convenioMsg1);
        request.setAttribute("convenioMsg2", convenioMsg2);        

        //JSON
        if (empresaNome != null) {
            JsonObject model = Json.createObjectBuilder()
                    .add("idConvenio", convenio.getNumero())
                    .add("razaoSocial", empresaNome.getRazaoSocial())
                    .add("tipoConvenio", "Pessoa Jurídica")
                    .add("isAgenteIntegracao", agenteIntegracao)
                    .add("cnpjEcpf", CNPJ)
                    .add("nomeEmpresaPessoa", empresaNome.getRazaoSocial())
                    .add("nomeConvenio", empresaNome.getRazaoSocial())
                    .add("numeroConvenio", convenio.getNumero())
                    .build();

            StringWriter stWriter = new StringWriter();
            JsonWriter jsonWriter = Json.createWriter(stWriter);
            jsonWriter.writeObject(model);
            jsonWriter.close();
            String jsonData = stWriter.toString();

            response.setContentType("application/json");
            response.getWriter().print(jsonData);
        } else if (pessoaNome != null) {
            JsonObject model = Json.createObjectBuilder()
                    .add("idConvenio", convenio.getNumero())
                    .add("razaoSocial", pessoaNome.getNome())
                    .add("tipoConvenio", "Pessoa Física")
                    .add("isAgenteIntegracao", "NÃO")
                    .add("cnpjEcpf", CPF)
                    .add("nomeEmpresaPessoa", pessoaNome.getNome())
                    .add("nomeConvenio", pessoaNome.getNome())
                    .add("numeroConvenio", convenio.getNumero())
                    .build();

            StringWriter stWriter = new StringWriter();
            JsonWriter jsonWriter = Json.createWriter(stWriter);
            jsonWriter.writeObject(model);
            jsonWriter.close();
            String jsonData = stWriter.toString();

            response.setContentType("application/json");
            response.getWriter().print(jsonData);
        } else {
            JsonObject model = Json.createObjectBuilder()
                    .add("razaoSocial", "")
                    .add("tipoConvenio", "")
                    .add("isAgenteIntegracao", "")
                    .add("nomeAgenciada", "")
                    .add("convenioMsg1", convenioMsg1)
                    .add("convenioMsg2", convenioMsg2)                  
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
    /**
     * Método para formatar String de Valor ao Retornar para a Tela onde é exibido
     * 
     * @author Claudio
     * @since 1.0
     */  
    public static String formatString(String value, String pattern) {
        MaskFormatter mf;
        try {
            mf = new MaskFormatter(pattern);
            mf.setValueContainsLiteralCharacters(false);
            return mf.valueToString(value);
        } catch (ParseException ex) {
            return null;
        }
    }

}
