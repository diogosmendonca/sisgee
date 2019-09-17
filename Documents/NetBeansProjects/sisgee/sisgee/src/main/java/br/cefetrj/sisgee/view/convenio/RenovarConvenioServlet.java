/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetrj.sisgee.view.convenio;

import br.cefetrj.sisgee.control.ConvenioServices;
import br.cefetrj.sisgee.model.entity.Convenio;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet para renovar convenio 
 * @author Lucas Lima
 */
@WebServlet(name = "RenovarConvenioServlet", urlPatterns = {"/RenovarConvenioServlet"})
public class RenovarConvenioServlet extends HttpServlet {
    
    /**
     * 
     * @param request um objeto HttpServletRequest que contém a solicitação feita pelo cliente do servlet.
     * @param response um objeto HttpServletResponse que contém a resposta que o servlet envia para o cliente
     * @throws ServletException se o pedido do service não puder ser tratado
     * @throws IOException se um erro de entrada ou saída for detectado quando o servlet manipula o pedido 
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        
        int pos = req.getParameter("convenio").indexOf("/");
        //Substring iniciando em 0 até posição do caracter especial
        
        String numeroConvenio = req.getParameter("convenio").substring(0,pos);
        
        
        Convenio convenio = ConvenioServices.buscarConvenioByNumeroConvenio(numeroConvenio);
       
        
        if(convenio.getEmpresa()!=null){
            req.setAttribute("isEmpresa", "sim");
            if(convenio.getEmpresa().isAgenteIntegracao()){
                req.setAttribute("simAgenteIntegracao", "sim");
            }else{
                req.setAttribute("naoAgenteIntegracao", "sim");
            }
            req.setAttribute("cnpj", convenio.getEmpresa().getCnpjEmpresa());
            req.setAttribute("razao", convenio.getEmpresa().getRazaoSocial());
            req.setAttribute("numeroConvenio", numeroConvenio);
            req.setAttribute("emailEmpresa", convenio.getEmpresa().getEmailEmpresa());
            req.setAttribute("telefoneEmpresa", convenio.getEmpresa().getTelefoneEmpresa());
            req.setAttribute("contatoEmpresa", convenio.getEmpresa().getContatoEmpresa());
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            final String dataFormatada = df.format(convenio.getDataFinal());
            req.setAttribute("dataFinal", dataFormatada);
               
        }else{
            req.setAttribute("isPessoa", "sim");
            req.setAttribute("cpf", convenio.getPessoa().getCpf());
            req.setAttribute("nome", convenio.getPessoa().getNome());
            req.setAttribute("numeroConvenio", numeroConvenio);
            req.setAttribute("emailPessoa", convenio.getPessoa().getEmail());
            req.setAttribute("telefonePessoa", convenio.getPessoa().getTelefone());
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            final String dataFormatada = df.format(convenio.getDataFinal());
            req.setAttribute("dataFinal", dataFormatada);
            
        }
        req.getSession().setAttribute("numero", numeroConvenio);
        
        req.setAttribute("convenioRenovar", convenio);
  
        req.getRequestDispatcher("form_renovar_convenio2.jsp").forward(req, resp);
        
    }

}
