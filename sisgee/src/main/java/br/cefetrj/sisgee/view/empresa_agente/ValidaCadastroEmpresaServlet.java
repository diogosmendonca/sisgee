package br.cefetrj.sisgee.view.empresa_agente;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.cefetrj.sisgee.control.AgenteIntegracaoServices;
import br.cefetrj.sisgee.control.ConvenioServices;
import br.cefetrj.sisgee.control.EmpresaServices;
import br.cefetrj.sisgee.control.PessoaServices;
import br.cefetrj.sisgee.model.entity.AgenteIntegracao;
import br.cefetrj.sisgee.model.entity.Convenio;
import br.cefetrj.sisgee.model.entity.Empresa;
import br.cefetrj.sisgee.model.entity.Pessoa;
import br.cefetrj.sisgee.view.utils.ServletUtils;
import br.cefetrj.sisgee.view.utils.ValidaUtils;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Servlet para validar os dados da tela de cadastro de Convenio.
 *
 * @author Lucas Lima
 * @since 1.0
 *
 */
@WebServlet("/ValidaCadastroEmpresaServlet")
public class ValidaCadastroEmpresaServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     *
     * @param request um objeto HttpServletRequest que contém a solicitação
     * feita pelo cliente do servlet.
     * @param response um objeto HttpServletResponse que contém a resposta que o
     * servlet envia para o cliente
     * @throws ServletException se o pedido do service não puder ser tratado
     * @throws IOException se um erro de entrada ou saída for detectado quando o
     * servlet manipula o pedido
     */
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Locale locale = ServletUtils.getLocale(request);
        ResourceBundle messages = ResourceBundle.getBundle("Messages", locale);
        String numeroSugerido = request.getParameter("numeroSugerido");
        System.out.print(numeroSugerido);
        String tipoPessoa = request.getParameter("tipoPessoa");
        boolean pessoaJuridica = true;

        if (tipoPessoa.equals("nao")) {
            pessoaJuridica = false;
        }

        //Dados Pessoa Jurídica
        String cnpjEmpresa = request.getParameter("cnpjEmpresa");
        System.out.println(cnpjEmpresa);
        String nomeEmpresa = request.getParameter("nomeEmpresa");
        System.out.println(nomeEmpresa);
        String agenteIntegracao = request.getParameter("agenteIntegracao");
        System.out.println(agenteIntegracao);
        String numeroEmpresa = request.getParameter("numeroEmpresa");
        System.out.println(numeroEmpresa);
        String anoEmpresa = request.getParameter("anoEmpresa");
        System.out.println(anoEmpresa);
        String dataAssinaturaConvenioEmpresa = request.getParameter("dataRegistroConvenioEmpresa");
        System.out.println(dataAssinaturaConvenioEmpresa);
        String emailEmpresa = request.getParameter("emailEmpresa");
        System.out.println(emailEmpresa);
        String telefoneEmpresa = request.getParameter("telefoneEmpresa");
        System.out.println(telefoneEmpresa);
        String contatoEmpresa = request.getParameter("contatoEmpresa");
        System.out.println(contatoEmpresa);

        //Dados Pessoa física
        String cpfPessoa = request.getParameter("cpfPessoa");
        String nomePessoa = request.getParameter("nomePessoa");
        String emailPessoa = request.getParameter("emailPessoa");
        String telefonePessoa = request.getParameter("telefonePessoa");
        String numeroPessoa = request.getParameter("numeroPessoa");
        String anoPessoa = request.getParameter("anoPessoa");
        String dataAssinaturaConvenioPessoa = request.getParameter("dataRegistroConvenioPessoa");

        boolean isValid = true;
        Integer tamanho = 0;

        if (pessoaJuridica) {
            /**
             * Validação do CNPJ da empresa usando os métodos da Classe
             * ValidaUtils Campo obrigatório; Tamanho de 14 caracteres; CNPJ
             * repetido.
             */
            String cnpjEmpresaMsg = "";
            tamanho = 14;
            cnpjEmpresaMsg = ValidaUtils.validaObrigatorio("CNPJ", cnpjEmpresa);
            if (cnpjEmpresaMsg.trim().isEmpty()) {
                //remove caracteres especiais antes de vazer a validação numérica do CNPJ
                cnpjEmpresa = cnpjEmpresa.replaceAll("[.|/|-]", "");
                cnpjEmpresaMsg = ValidaUtils.validaInteger("CNPJ", cnpjEmpresa);
                if (cnpjEmpresaMsg.trim().isEmpty()) {
                    cnpjEmpresaMsg = ValidaUtils.validaTamanhoExato("CNPJ", tamanho, cnpjEmpresa);
                    if (cnpjEmpresaMsg.trim().isEmpty()) {
                        Empresa e = EmpresaServices.buscarEmpresaByCnpj(cnpjEmpresa);
                        if (e == null) {
                        	AgenteIntegracao a = AgenteIntegracaoServices.buscarAgenteIntegracaoByCnpj(cnpjEmpresa);
                            if (a == null) {
                            request.setAttribute("cnpjEmpresa", cnpjEmpresa);
                            } else {
                            	cnpjEmpresaMsg = messages.getString("br.cefetrj.sisgee.valida_cadastro_empresa_servlet.msg_empresa_duplicada");
                                request.setAttribute("cnpjEmpresaMsg", cnpjEmpresaMsg);
                                isValid = false;
                                System.out.println("isValid falso cnpj agente integração já existe");
                            }
                        } else {
	                        cnpjEmpresaMsg = messages.getString("br.cefetrj.sisgee.valida_cadastro_empresa_servlet.msg_empresa_duplicada");
	                        cnpjEmpresaMsg = ServletUtils.mensagemFormatada(cnpjEmpresaMsg, locale, tamanho);
	                        request.setAttribute("cnpjEmpresaMsg", cnpjEmpresaMsg);
	                        isValid = false;
	                        System.out.println("isValid falso cnpj já existe no bd");
                        }
                    } else {
	                    cnpjEmpresaMsg = messages.getString("br.cefetrj.sisgee.valida_utils.msg_valida_tamanho_exato");
	                    request.setAttribute("cnpjEmpresaMsg", cnpjEmpresaMsg);
	                    isValid = false;
	                    System.out.println("isValid falso tamanho cnpj errado");
	                }
                } else {
	                cnpjEmpresaMsg = messages.getString("br.cefetrj.sisgee.valida_utils.msg_valida_numerico");
	                request.setAttribute("cnpjEmpresaMsg", cnpjEmpresaMsg);
	                isValid = false;
	                System.out.println("isValid falso não é apenas números cnpj");
	            }
            } else {
                cnpjEmpresaMsg = messages.getString("br.cefetrj.sisgee.valida_utils.msg_valida_obrigatorio");
                request.setAttribute("cnpjEmpresaMsg", cnpjEmpresaMsg);
                isValid = false;
                System.out.println("isValid falso cpnj nulo");
            }
               
            /**
             * Validação do campo Agente Integração, usando métodos da Classe
             * ValidaUtils. Deve ser campo booleano
             */
            String agenteIntegracaoMsg = "";
            agenteIntegracaoMsg = ValidaUtils.validaObrigatorio("Agente Integração", agenteIntegracao);
            if (agenteIntegracaoMsg.trim().isEmpty()) {
                agenteIntegracaoMsg = ValidaUtils.validaBoolean("Agente Integração", agenteIntegracao);
                if (agenteIntegracaoMsg.trim().isEmpty()) {
                    Boolean obrigatorio = Boolean.parseBoolean(agenteIntegracao);
                    request.setAttribute("obrigatorio", obrigatorio);
                } else {
                    agenteIntegracaoMsg = messages.getString("br.cefetrj.sisgee.valida_utils.msg_valida_booleano");
                    request.setAttribute("agenteIntegracaoMsg", agenteIntegracaoMsg);
                    isValid = false;
                    System.out.println("isValid falso não é um boolean");
                }
            } else {
                agenteIntegracaoMsg = messages.getString("br.cefetrj.sisgee.valida_utils.msg_valida_obrigatorio");
                request.setAttribute("agenteIntegracaoMsg", agenteIntegracaoMsg);
                isValid = false;
                System.out.println("isValid falso Agente de integração vazio");
            }

            /**
             * Validação da Razão Social do Cadastro Empresa usando métodos da
             * Classe ValidaUtils. Campo obrigatório; Tamanho máximo de 100
             * caracteres; Razão Social já existente.
             */
            String nomeEmpresaMsg = "";
            nomeEmpresaMsg = ValidaUtils.validaObrigatorio("Razão Social", nomeEmpresa);
            if (nomeEmpresaMsg.trim().isEmpty()) {
                nomeEmpresaMsg = ValidaUtils.validaTamanho("Razão Social", 100, nomeEmpresa);
                if (nomeEmpresaMsg.trim().isEmpty()) {
                    Empresa e = EmpresaServices.buscarEmpresaByNome(nomeEmpresa);
                    if (e == null) {
                        request.setAttribute("nomeEmpresa", nomeEmpresa);
                    } else {
                        nomeEmpresaMsg = messages.getString("br.cefetrj.sisgee.valida_cadastro_empresa_servlet.msg_empresa_duplicada");
                        request.setAttribute("nomeEmpresaMsg", nomeEmpresaMsg);
                        isValid = false;
                        System.out.println("isValid falso nome já existe");
                    }
                } else {
                    nomeEmpresaMsg = messages.getString("br.cefetrj.sisgee.valida_utils.msg_valida_tamanho_txt");
                    nomeEmpresaMsg = ServletUtils.mensagemFormatada(nomeEmpresaMsg, locale, tamanho);
                    request.setAttribute("nomeEmpresaMsg", nomeEmpresaMsg);
                    isValid = false;
                    System.out.println("isValid falso tamanho errado nome empresa");
                }
            } else {
                nomeEmpresaMsg = messages.getString("br.cefetrj.sisgee.valida_utils.msg_valida_obrigatorio");
                request.setAttribute("nomeEmpresaMsg", nomeEmpresaMsg);
                isValid = false;
                System.out.println("isValid falso razão social vazio");
            }

            /**
             * Validação do Email do Cadastro Empresa usando métodos da Classe
             * ValidaUtils. Campo obrigatório; Tamanho máximo de 50 caracteres;
             */
            String emailEmpresaMsg = "";
            emailEmpresaMsg = ValidaUtils.validaObrigatorio("emailEmpresa", emailEmpresa);
            if (emailEmpresaMsg.trim().isEmpty()) {
                emailEmpresaMsg = ValidaUtils.validaTamanho("emailEmpresa", 50, emailEmpresa);
                if (emailEmpresaMsg.trim().isEmpty()) {
                    emailEmpresaMsg = ValidaUtils.validaEmail("emailEmpresa", emailEmpresa);
                    if (emailEmpresaMsg.trim().isEmpty()) {
                        request.setAttribute("emailEmpresa", emailEmpresa);
                    } else {
                        emailEmpresaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                        request.setAttribute("emailEmpresaMsg", emailEmpresaMsg);
                        isValid = false;
                        System.out.println("isValid falso não é email");
                    }

                } else {
                    emailEmpresaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                    request.setAttribute("emailEmpresaMsg", emailEmpresaMsg);
                    isValid = false;
                    System.out.println("isValid falso tamanho errado do email");
                }

            } else {
            	emailEmpresaMsg = messages.getString("br.cefetrj.sisgee.valida_utils.msg_valida_obrigatorio");
            	request.setAttribute("emailEmpresaMsg", emailEmpresaMsg);
            	isValid = false;
            	System.out.println("isValid falso email obrigatório");
            }

            /**
             * Validação do Telefone do Cadastro Empresa usando métodos da
             * Classe ValidaUtils. Campo obrigatório; Tamanho máximo de 11
             * caracteres;
             */
            String telefoneEmpresaMsg = "";
            telefoneEmpresaMsg = ValidaUtils.validaObrigatorio("telefoneEmpresa", telefoneEmpresa);
            if (telefoneEmpresaMsg.trim().isEmpty()) {
                telefoneEmpresaMsg = ValidaUtils.validaTamanho("telefoneEmpresa", 11, telefoneEmpresa);
                System.out.println("passou tamanho");
                System.out.println(telefoneEmpresa);
                if (telefoneEmpresaMsg.trim().isEmpty()) {
                    telefoneEmpresa = telefoneEmpresa.replaceAll("[.|/|-]", "");
                    System.out.println(telefoneEmpresa);
                    telefoneEmpresaMsg = ValidaUtils.validaInteger("telefoneEmpresa", telefoneEmpresa);
                    System.out.println("passou apenas números");
                    System.out.println("telefoneEmpresaMsg: " + telefoneEmpresaMsg);
                    if (telefoneEmpresaMsg.trim().isEmpty()) {
                        telefoneEmpresaMsg = ValidaUtils.validaTelefone("telefoneEmpresa", telefoneEmpresa);
                        System.out.println(telefoneEmpresa);
                        System.out.println("formato telefone válido");
                        System.out.println("telefoneEmpresaMsg: " +telefoneEmpresaMsg);
                        if (telefoneEmpresaMsg.trim().isEmpty()) {
                        	//Alterações, nova condição
                            telefoneEmpresaMsg = ValidaUtils.validaNumeroDDD("telefoneEmpresa", telefoneEmpresa);
                            System.out.println("telefoneEmpresaMsg: " +telefoneEmpresaMsg);
                            System.out.println("formato ddd válido");
                            if (telefoneEmpresaMsg.trim().isEmpty()) {
                                request.setAttribute("telefoneEmpresa", telefoneEmpresa);
                            } else {
                                telefoneEmpresaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                                request.setAttribute("telefoneEmpresaMsg", telefoneEmpresaMsg);
                                isValid = false;
                                System.out.println("isValid falso formato ddd errado");
                            }
                        } else {
                            telefoneEmpresaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                            request.setAttribute("telefoneEmpresaMsg", telefoneEmpresaMsg);
                            isValid = false;
                            System.out.println("isValid falso não é telefone");
                        }

                    } else {
                        telefoneEmpresaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                        request.setAttribute("telefoneEmpresaMsg", telefoneEmpresaMsg);
                        isValid = false;
                        System.out.println("isValid falso telefone não é apenas números");

                    }

                } else {
                    telefoneEmpresaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                    request.setAttribute("telefoneEmpresaMsg", telefoneEmpresaMsg);
                    isValid = false;
                    System.out.println("isValid falso não tá no tamanho certo telefone");
                }

            } else {
            	telefoneEmpresaMsg = messages.getString("br.cefetrj.sisgee.valida_utils.msg_valida_obrigatorio");
            	request.setAttribute("telefoneEmpresaMsg", telefoneEmpresaMsg);
            	isValid = false;
            	System.out.println("isValid falso telefone é obrigatório");
            }

            /**
             * Validação do contato com a Empresa do Cadastro Empresa usando
             * métodos da Classe ValidaUtils. Campo opcional; Tamanho máximo de
             * 50 caracteres;
             */
            String contatoEmpresaMsg = "";
            contatoEmpresaMsg = ValidaUtils.validaObrigatorio("contatoEmpresa", contatoEmpresa);
            if (contatoEmpresaMsg.trim().isEmpty()) {
                contatoEmpresaMsg = ValidaUtils.validaTamanho("contatoEmpresa", 50, contatoEmpresa);
                if (contatoEmpresaMsg.trim().isEmpty()) {
                    contatoEmpresaMsg = ValidaUtils.validaSomenteLetras("contatoEmpresa", contatoEmpresa);
                    if (contatoEmpresaMsg.trim().isEmpty()) {
                        request.setAttribute("contatoEmpresa", contatoEmpresa);

                    } else {
                        contatoEmpresaMsg = messages.getString("br.cefetrj.sisgee.valida_utils.msg_valida_txt");
                        request.setAttribute("contatoEmpresaMsg", contatoEmpresaMsg);
                        isValid = false;
                        System.out.println("isValid falso contato não é apenas letras");
                    }

                } else {
                    contatoEmpresaMsg = messages.getString("br.cefetrj.sisgee.valida_utils.msg_valida_tamanho_txt");
                    request.setAttribute("contatoEmpresaMsg", contatoEmpresaMsg);
                    isValid = false;
                    System.out.println("isValid falso tamanho errado contato");
                }

            } else {
            	contatoEmpresaMsg = messages.getString("br.cefetrj.sisgee.valida_utils.msg_valida_obrigatorio");
            	request.setAttribute("contatoEmpresaMsg", contatoEmpresaMsg);
            	isValid = false;
            	System.out.println("isValid falso contato é obrigatório");
            }

            /**
             * Validação da Data de Assinatura do Convenio da Pessoa usando os
             * métodos da Classe ValidaUtils Campo obrigatório
             */
            Date dataAssinaturaEmpresa = null;
            String dataAssinaturaMsg = "";
            String campo = "Data de Assinatura";

            dataAssinaturaMsg = ValidaUtils.validaObrigatorio(campo, dataAssinaturaConvenioEmpresa);
            if (dataAssinaturaMsg.trim().isEmpty()) {
                dataAssinaturaMsg = ValidaUtils.validaDate(campo, dataAssinaturaConvenioEmpresa);
                if (dataAssinaturaMsg.trim().isEmpty()) {
                    dataAssinaturaMsg = ValidaUtils.validaTamanhoExato(campo, 10, dataAssinaturaConvenioEmpresa);
                    if (dataAssinaturaMsg.trim().isEmpty()) {
                        try {
                            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                            dataAssinaturaEmpresa = format.parse(dataAssinaturaConvenioEmpresa);

                            request.setAttribute("dataAssinaturaConvenioEmpresa", dataAssinaturaEmpresa);
                        } catch (Exception e) {
                            isValid = false;
                            System.out.println("isValid falso não conseguiu fazer o parse da data");
                        }
                    } else {
                        dataAssinaturaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                        request.setAttribute("dataAssinaturaEmpresaMsg", dataAssinaturaMsg);
                        isValid = false;
                        System.out.println("isValid falso não é tamanho certo de data");
                    }
                } else {
                    dataAssinaturaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                    request.setAttribute("dataAssinaturaEmpresaMsg", dataAssinaturaMsg);
                    isValid = false;
                    System.out.println("isValid falso não é data");
                    System.out.println(dataAssinaturaMsg);
                }
            } else {
                dataAssinaturaMsg = messages.getString("br.cefetrj.sisgee.valida_utils.msg_valida_obrigatorio");
                request.setAttribute("dataAssinaturaEmpresaMsg", dataAssinaturaMsg);
                isValid = false;
                System.out.println("isValid falso data é obrigatório");
                System.out.println(dataAssinaturaMsg);
            }

            String numeroEmpresaMsg = "";
            tamanho = 6;
            numeroEmpresaMsg = ValidaUtils.validaObrigatorio("Número Convênio Empresa", numeroEmpresa);
            if (numeroEmpresaMsg.trim().isEmpty()) {
                numeroEmpresaMsg = ValidaUtils.validaInteger("Número Convênio Empresa", numeroEmpresa);
                if (numeroEmpresaMsg.trim().isEmpty()) {
                    numeroEmpresaMsg = ValidaUtils.validaTamanho("Número Convênio Empresa", tamanho, numeroEmpresa);
                    if (numeroEmpresaMsg.trim().isEmpty()) {
                        numeroEmpresaMsg = ValidaUtils.validaPositivo("Número Convênio Empresa", numeroEmpresa);
                        if (numeroEmpresaMsg.trim().isEmpty()) {
                            Convenio conv = ConvenioServices.buscarConvenioByNumeroConvenio(numeroEmpresa);
                            if (conv == null) {
                                request.setAttribute("numeroEmpresa", numeroEmpresa);
                            } else {
                                numeroEmpresaMsg = messages.getString("br.cefetrj.sisgee.valida_cadastro_empresa_servlet.msg_numeroConvenio_invalido");
                                request.setAttribute("numeroEmpresaMsg", numeroEmpresaMsg);
                                isValid = false;
                                System.out.println("isValid falso número convênio já existe");
                            }
                        } else {
                            numeroEmpresaMsg = messages.getString("br.cefetrj.sisgee.valida_utils.msg_valida_positivo");
                            request.setAttribute("numeroEmpresaMsg", numeroEmpresaMsg);
                            isValid = false;
                            System.out.println("isValid falso número convênio não é positivo");
                        }
                    } else {
                        numeroEmpresaMsg = messages.getString("br.cefetrj.sisgee.valida_utils.msg_valida_tamanho_num");
                        numeroEmpresaMsg = ServletUtils.mensagemFormatada(numeroEmpresaMsg, locale, tamanho);
                        request.setAttribute("numeroEmpresaMsg", numeroEmpresaMsg);
                        isValid = false;
                        System.out.println("isValid falso número convênio fora do tamanho exato");
                    }
                } else {
                    numeroEmpresaMsg = messages.getString("br.cefetrj.sisgee.valida_utils.msg_valida_numerico");
                    numeroEmpresaMsg = ServletUtils.mensagemFormatada(numeroEmpresaMsg, locale, tamanho);
                    request.setAttribute("numeroEmpresaMsg", numeroEmpresaMsg);
                    isValid = false;
                    System.out.println("isValid falso não é apenas números ");
                }
            } else {
                numeroEmpresaMsg = messages.getString("br.cefetrj.sisgee.valida_utils.msg_valida_obrigatorio");
                request.setAttribute("numeroEmpresaMsg", numeroEmpresaMsg);
                isValid = false;
                System.out.println("isValid falso numero da empresa é obrigatório");
            }

            String anoEmpresaMsg = "";
            tamanho = 4;
            anoEmpresaMsg = ValidaUtils.validaObrigatorio("Ano Convênio Empresa", anoEmpresa);
            if (anoEmpresaMsg.trim().isEmpty()) {
                anoEmpresaMsg = ValidaUtils.validaInteger("Ano Convênio Empresa", anoEmpresa);
                if (anoEmpresaMsg.trim().isEmpty()) {
                    anoEmpresaMsg = ValidaUtils.validaPositivo("Ano Convênio Empresa", anoEmpresa);
                    if (anoEmpresaMsg.trim().isEmpty()) {
                        anoEmpresaMsg = ValidaUtils.validaTamanhoExato("Ano Convênio Empresa", tamanho, anoEmpresa);
                        if (anoEmpresaMsg.trim().isEmpty()) {
                            request.setAttribute("anoEmpresa", anoEmpresa);
                        } else {
                            anoEmpresaMsg = messages.getString("br.cefetrj.sisgee.valida_utils.msg_valida_tamanho_num");
                            anoEmpresaMsg = ServletUtils.mensagemFormatada(anoEmpresaMsg, locale, tamanho);
                            request.setAttribute("anoEmpresaMsg", anoEmpresaMsg);
                            isValid = false;
                            System.out.println("isValid falso não é tamanho exato");
                        }
                    } else {
                        anoEmpresaMsg = messages.getString("br.cefetrj.sisgee.valida_utils.msg_valida_positivo");
                        request.setAttribute("anoEmpresaMsg", anoEmpresaMsg);
                        isValid = false;
                        System.out.println("isValid falso ano não é positivo");
                    }
                } else {
                    anoEmpresaMsg = messages.getString("br.cefetrj.sisgee.valida_utils.msg_valida_numerico");
                    anoEmpresaMsg = ServletUtils.mensagemFormatada(anoEmpresaMsg, locale, tamanho);
                    request.setAttribute("anoEmpresaMsg", anoEmpresaMsg);
                    isValid = false;
                    System.out.println("isValid falso ano não é apenas números");
                }
            } else {
                anoEmpresaMsg = messages.getString("br.cefetrj.sisgee.valida_utils.msg_valida_obrigatorio");
                request.setAttribute("anoEmpresaMsg", anoEmpresaMsg);
                isValid = false;
                System.out.println("isValid falso ano é obrigatório");
            }

        } else {
            /**
             * Validação do CPF da pessoa usando os métodos da Classe
             * ValidaUtils Campo obrigatório; Tamanho de 11 caracteres; CNPJ
             * repetido.
             */
            String cpfPessoaMsg = "";
            tamanho = 11;
            cpfPessoaMsg = ValidaUtils.validaObrigatorio("CPF", cpfPessoa);
            if (cpfPessoaMsg.trim().isEmpty()) {

                //remove caracteres especiais antes de vazer a validação numérica do CNPJ
                cpfPessoa = cpfPessoa.replaceAll("[.|/|-]", "");
                cpfPessoaMsg = ValidaUtils.validaInteger("CPF", cpfPessoa);
                if (cpfPessoaMsg.trim().isEmpty()) {
                    cpfPessoaMsg = ValidaUtils.validaTamanhoExato("CPF", tamanho, cpfPessoa);
                    if (cpfPessoaMsg.trim().isEmpty()) {
                        Pessoa e = PessoaServices.buscarPessoaByCpf(cpfPessoa);
                        System.out.println(cpfPessoa);
                        if (e == null) {
                            System.out.println(cpfPessoa);
                            request.setAttribute("cpfPessoa", cpfPessoa.replaceAll("[.|/|-]", ""));
                        } else {
                            cpfPessoaMsg = messages.getString("br.cefetrj.sisgee.valida_cadastro_empresa_servlet.msg_pessoafisica_duplicada");
                            request.setAttribute("cpfPessoaMsg", cpfPessoaMsg);
                            isValid = false;
                            System.out.println("isValid falso cpf já existe");
                        }
                    } else {
                        cpfPessoaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                        request.setAttribute("cpfPessoaMsg", cpfPessoaMsg);
                        isValid = false;
                        System.out.println("isValid falso cpf não está no tamanho exato");
                    }
                } else {
                    cpfPessoaMsg = messages.getString("br.cefetrj.sisgee.valida_utils.msg_valida_numerico");
                    request.setAttribute("cpfPessoaMsg", cpfPessoaMsg);
                    isValid = false;
                    System.out.println("isValid falso não é apenas números");
                }
            } else {
                cpfPessoaMsg = messages.getString("br.cefetrj.sisgee.valida_utils.msg_valida_obrigatorio");
                cpfPessoaMsg = ServletUtils.mensagemFormatada(cpfPessoaMsg, locale, tamanho);
                request.setAttribute("cpfPessoaMsg", cpfPessoaMsg);
                isValid = false;
                System.out.println("isValid falso cpf é obrigatório");
            }

            /**
             * Validação do nome da Pessoa Cadastro Pessoa Fisica usando métodos
             * da Classe ValidaUtils. Campo obrigatorio; Tamanho máximo de 100
             * caracteres;
             */
            String nomePessoaMsg = "";
            nomePessoaMsg = ValidaUtils.validaObrigatorio("nomePessoa", nomePessoa);
            if (nomePessoaMsg.trim().isEmpty()) {
                nomePessoaMsg = ValidaUtils.validaTamanho("nomePessoa", 100, nomePessoa);
                if (nomePessoaMsg.trim().isEmpty()) {
                    nomePessoaMsg = ValidaUtils.validaSomenteLetras("nomePessoa", nomePessoa);
                    if (nomePessoaMsg.trim().isEmpty()) {
                        request.setAttribute("nomePessoa", nomePessoa);
                    } else {
                        nomePessoaMsg = messages.getString("br.cefetrj.sisgee.valida_utils.msg_valida_txt");
                        request.setAttribute("nomePessoaMsg", nomePessoaMsg);
                        isValid = false;
                        System.out.println("isValid falso nome pessoa não é só letras");
                    }

                } else {
                    nomePessoaMsg = messages.getString("br.cefetrj.sisgee.valida_utils.msg_valida_tamanho_txt");
                    request.setAttribute("nomePessoaMsg", nomePessoaMsg);
                    isValid = false;
                    System.out.println("isValid falso nome pessoa fora do tamanho");
                }

            } else {
                nomePessoaMsg = messages.getString("br.cefetrj.sisgee.valida_utils.msg_valida_obrigatorio");
                request.setAttribute("nomePessoaMsg", nomePessoaMsg);
                isValid = false;
                System.out.println("isValid falso nome pessoa é obrigatório");
            }

            /**
             * Validação do email da Pessoa Cadastro Pessoa Fisica usando
             * métodos da Classe ValidaUtils. Campo opcional; Tamanho máximo de
             * 50 caracteres;
             */
            String emailPessoaMsg = "";
            emailPessoaMsg = ValidaUtils.validaObrigatorio("emailPessoa", emailPessoa);
            if (emailPessoaMsg.trim().isEmpty()) {
                emailPessoaMsg = ValidaUtils.validaTamanho("emailPessoa", 50, emailPessoa);
                if (emailPessoaMsg.trim().isEmpty()) {
                    emailPessoaMsg = ValidaUtils.validaEmail("emailPessoa", emailPessoa);
                    if (emailPessoaMsg.trim().isEmpty()) {
                        request.setAttribute("emailPessoa", emailPessoa);
                    } else {
                        emailPessoaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                        request.setAttribute("emailPessoaMsg", emailPessoaMsg);
                        isValid = false;
                        System.out.println("isValid falso não é um email pessoa");
                    }
                } else {
                    emailPessoaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                    request.setAttribute("emailPessoaMsg", emailPessoaMsg);
                    isValid = false;
                    System.out.println("isValid falso tamanho errado email pessoa");
                }

            } else {
            	emailPessoaMsg = messages.getString("br.cefetrj.sisgee.valida_utils.msg_valida_obrigatorio");
            	request.setAttribute("emailPessoaMsg", emailPessoaMsg);
            	isValid = false;
            	System.out.println("isValid falso email pessoa obrigatório");
            }


            /**
             * Validação do telefone da Pessoa Cadastro Pessoa Fisica usando
             * métodos da Classe ValidaUtils. Campo opcional; Tamanho máximo de
             * 11 caracteres;
             */
            String telefonePessoaMsg = "";
            telefonePessoaMsg = ValidaUtils.validaObrigatorio("telefonePessoa", telefonePessoa);
            if (telefonePessoaMsg.trim().isEmpty()) {
                telefonePessoaMsg = ValidaUtils.validaTamanho("telefonePessoa", 11, telefonePessoa);
                if (telefonePessoaMsg.trim().isEmpty()) {
                    telefonePessoa = telefonePessoa.replaceAll("[.|/|-]", "");
                    telefonePessoaMsg = ValidaUtils.validaInteger("telefonePessoa", telefonePessoa);
                    if (telefonePessoaMsg.trim().isEmpty()) {
                        telefonePessoaMsg = ValidaUtils.validaTelefone("telefonePessoa", telefonePessoa);
                        if (telefonePessoaMsg.trim().isEmpty()) {
                            request.setAttribute("telefonePessoa", telefonePessoa);
                        } else {
                            telefonePessoaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                            request.setAttribute("telefonePessoaMsg", telefonePessoaMsg);
                            isValid = false;
                            System.out.println("isValid falso não é um telefone pessoa válido");
                        }
                    } else {
                        telefonePessoaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                        request.setAttribute("telefonePessoaMsg", telefonePessoaMsg);
                        isValid = false;
                        System.out.println("isValid falso não é apenas número telefone pessoa");
                    }
                } else {
                    telefonePessoaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                    request.setAttribute("telefonePessoaMsg", telefonePessoaMsg);
                    isValid = false;
                    System.out.println("isValid falso tamanho errado telefone pessoa");
                }

            } else {
            	telefonePessoaMsg = messages.getString("br.cefetrj.sisgee.valida_utils.msg_valida_obrigatorio");
            	request.setAttribute("telefonePessoaMsg", telefonePessoaMsg);
            	isValid = false;
            	System.out.println("isValid falso telefone pessoa é obrigatório");
            }

            /**
             * Validação da Data de Assinatura do Convenio da Pessoa usando os
             * métodos da Classe ValidaUtils Campo obrigatório
             */
            Date dataAssinaturaPessoa = null;
            String dataAssinaturaMsg = "";
            String campo = "Data de Assinatura";

            dataAssinaturaMsg = ValidaUtils.validaObrigatorio(campo, dataAssinaturaConvenioPessoa);
            if (dataAssinaturaMsg.trim().isEmpty()) {
                dataAssinaturaMsg = ValidaUtils.validaDate(campo, dataAssinaturaConvenioPessoa);
                if (dataAssinaturaMsg.trim().isEmpty()) {
                    dataAssinaturaMsg = ValidaUtils.validaTamanhoExato(campo, 10, dataAssinaturaConvenioPessoa);
                    if (dataAssinaturaMsg.trim().isEmpty()) {
                        try {
                            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                            dataAssinaturaPessoa = format.parse(dataAssinaturaConvenioPessoa);

                            request.setAttribute("dataAssinaturaConvenioPessoa", dataAssinaturaPessoa);
                        } catch (Exception e) {
                            //TODO trocar saída de console por Log
                            System.out.println("Data em formato incorreto, mesmo após validação na classe ValidaUtils");
                            isValid = false;
                            System.out.println("isValid falso não conseguiu fazer parse data pessoa");
                        }
                    } else {
                        dataAssinaturaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                        request.setAttribute("dataAssinaturaPessoaMsg", dataAssinaturaMsg);
                        isValid = false;
                        System.out.println("isValid falso não é o tamanho exato data assinatura pessoa");
                    }

                } else {
                    dataAssinaturaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                    request.setAttribute("dataAssinaturaPessoaMsg", dataAssinaturaMsg);
                    isValid = false;
                    System.out.println("isValid falso não é uma data pessoa válida");
                    System.out.println(dataAssinaturaMsg);
                }
            } else {
                dataAssinaturaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                request.setAttribute("dataAssinaturaPessoaMsg", dataAssinaturaMsg);
                isValid = false;
                System.out.println("isValid falso data assinatura pessoa obrigatória");
                System.out.println(dataAssinaturaMsg);
            }

            String numeroPessoaMsg = "";
            tamanho = 6;
            numeroPessoaMsg = ValidaUtils.validaObrigatorio("Número Convênio Pessoa", numeroPessoa);
            if (numeroPessoaMsg.trim().isEmpty()) {
                numeroPessoaMsg = ValidaUtils.validaInteger("Número Convênio Pessoa", numeroPessoa);
                if (numeroPessoaMsg.trim().isEmpty()) {
                    numeroPessoaMsg = ValidaUtils.validaTamanho("Número Convênio Pessoa", tamanho, numeroPessoa);
                    if (numeroPessoaMsg.trim().isEmpty()) {
                        numeroPessoaMsg = ValidaUtils.validaPositivo("Número Convênio Pessoa", numeroPessoa);
                        if (numeroPessoaMsg.trim().isEmpty()) {
                            Convenio conv = ConvenioServices.buscarConvenioByNumeroConvenio(numeroPessoa);
                            if (conv == null) {
                                request.setAttribute("numeroPessoa", numeroPessoa);
                            } else {
                                numeroPessoaMsg = messages.getString("br.cefetrj.sisgee.valida_cadastro_empresa_servlet.msg_numeroConvenio_invalido");
                                request.setAttribute("numeroPessoaMsg", numeroPessoaMsg);
                                isValid = false;
                                System.out.println("isValid falso número pessoa já existe");
                            }
                        } else {
                            numeroPessoaMsg = messages.getString("br.cefetrj.sisgee.valida_utils.msg_valida_positivo");
                            request.setAttribute("numeroPessoaMsg", numeroPessoaMsg);
                            isValid = false;
                            System.out.println("isValid falso número pessoa não é positivo");
                        }
                    } else {
                        numeroPessoaMsg = messages.getString("br.cefetrj.sisgee.valida_utils.msg_valida_tamanho_num");
                        numeroPessoaMsg = ServletUtils.mensagemFormatada(numeroPessoaMsg, locale, tamanho);
                        request.setAttribute("numeroPessoaMsg", numeroPessoaMsg);
                        isValid = false;
                        System.out.println("isValid falso número pessoa tamanho errado");
                    }
                } else {
                    numeroPessoaMsg = messages.getString("br.cefetrj.sisgee.valida_utils.msg_valida_numerico");
                    numeroPessoaMsg = ServletUtils.mensagemFormatada(numeroPessoaMsg, locale, tamanho);
                    request.setAttribute("numeroPessoaMsg", numeroPessoaMsg);
                    isValid = false;
                    System.out.println("isValid falso número pessoa não é apenas número");
                }
            } else {
                numeroPessoaMsg = messages.getString("br.cefetrj.sisgee.valida_utils.msg_valida_obrigatorio");
                request.setAttribute("numeroPessoaMsg", numeroPessoaMsg);
                isValid = false;
                System.out.println("isValid falso número pessoa é obrigatório");
            }

            String anoPessoaMsg = "";
            tamanho = 4;
            anoPessoaMsg = ValidaUtils.validaObrigatorio("Ano Convênio Pessoa", anoPessoa);
            if (anoPessoaMsg.trim().isEmpty()) {
                anoPessoaMsg = ValidaUtils.validaInteger("Ano Convênio Pessoa", anoPessoa);
                if (anoPessoaMsg.trim().isEmpty()) {
                    anoPessoaMsg = ValidaUtils.validaPositivo("Ano Convênio Pessoa", anoPessoa);
                    if (anoPessoaMsg.trim().isEmpty()) {
                        anoPessoaMsg = ValidaUtils.validaTamanhoExato("Ano Convênio Pessoa", tamanho, anoPessoa);
                        if (anoPessoaMsg.trim().isEmpty()) {
                            request.setAttribute("anoPessoa", anoPessoa);
                        } else {
                            anoPessoaMsg = messages.getString(anoPessoaMsg);
                            anoPessoaMsg = ServletUtils.mensagemFormatada(anoPessoaMsg, locale, tamanho);
                            request.setAttribute("anoPessoaMsg", anoPessoaMsg);
                            isValid = false;
                            System.out.println("isValid falso ano pessoa fora do tamanho exato");
                        }
                    } else {
                        anoPessoaMsg = messages.getString(anoPessoaMsg);
                        request.setAttribute("anoPessoaMsg", anoPessoaMsg);
                        isValid = false;
                        System.out.println("isValid falso ano pessoa não é positivo ");
                    }
                } else {
                    anoPessoaMsg = messages.getString(anoPessoaMsg);
                    anoPessoaMsg = ServletUtils.mensagemFormatada(anoPessoaMsg, locale, tamanho);
                    request.setAttribute("anoPessoaMsg", anoPessoaMsg);
                    isValid = false;
                    System.out.println("isValid falso ano pessoa não é apenas número ");
                }
            } else {
                anoPessoaMsg = messages.getString("br.cefetrj.sisgee.valida_utils.msg_valida_obrigatorio");
                request.setAttribute("anoPessoaMsg", anoPessoaMsg);
                isValid = false;
                System.out.println("isValid falso ano pessoa é obrigatório");
            }

        }

        /**
         * Teste das variáveis booleanas após validação. Redirecionamento para a
         * inclusão ou devolver para o formulário com as mensagens.
         */
        if (isValid) {
            request.getRequestDispatcher("/IncluirCadastroEmpresaServlet").forward(request, response);
        } else {
            request.setAttribute("anoPessoa", anoPessoa);
            request.setAttribute("anoEmpresa", anoEmpresa);
            request.setAttribute("numeroPessoa", numeroPessoa);
            request.setAttribute("numeroEmpresa", numeroEmpresa);
            request.setAttribute("numeroSugerido", numeroSugerido);
            String msg = messages.getString("br.cefetrj.sisgee.valida_cadastro_empresa_servlet.msg_atencao");
            request.setAttribute("msg", msg);
            request.getRequestDispatcher("/form_empresa.jsp").forward(request, response);
        }
    }

}
