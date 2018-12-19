/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetrj.sisgee.view.convenio;

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
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Matheus Silva
 */
@WebServlet(name = "ValidaAlterarConvenio", urlPatterns = {"/ValidaAlterarConvenio"})
public class ValidaAlterarConvenio extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Locale locale = ServletUtils.getLocale(request);
        ResourceBundle messages = ResourceBundle.getBundle("Messages", locale);
        String numeroSugerido = request.getParameter("numeroSugerido");
        Convenio convenio = ConvenioServices.buscarConvenioByNumeroConvenio(numeroSugerido);
        System.out.print(numeroSugerido);
        String tipoPessoa = request.getParameter("Empresa");
        boolean pessoaJuridica = true;

        if (!tipoPessoa.equals("sim")) {
            pessoaJuridica = false;
        }

        //Dados Pessoa Jurídica
        String cnpjEmpresa = request.getParameter("cnpjEmpresa");
        String nomeEmpresa = request.getParameter("nomeEmpresa");
        String agenteIntegracao = request.getParameter("agenteIntegracao");
        String numeroEmpresa = request.getParameter("numeroEmpresa");
        String anoEmpresa = request.getParameter("anoEmpresa");
        String dataAssinaturaConvenioEmpresa = request.getParameter("dataRegistroConvenioEmpresa");
        String emailEmpresa = request.getParameter("emailEmpresa");
        String telefoneEmpresa = request.getParameter("telefoneEmpresa");
        String contatoEmpresa = request.getParameter("contatoEmpresa");

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
                                if (e.equals(convenio.getEmpresa())) {
                                    request.setAttribute("cnpjEmpresa", cnpjEmpresa);
                                } else {
                                    cnpjEmpresaMsg = messages.getString("br.cefetrj.sisgee.valida_cadastro_empresa_servlet.msg_empresa_duplicada");
                                    request.setAttribute("cnpjEmpresaMsg", cnpjEmpresaMsg);
                                    isValid = false;
                                }
                            }
                        } else {
                            if (e.equals(convenio.getEmpresa())) {
                                request.setAttribute("cnpjEmpresa", cnpjEmpresa);
                            } else {
                                cnpjEmpresaMsg = messages.getString("br.cefetrj.sisgee.valida_cadastro_empresa_servlet.msg_empresa_duplicada");
                                request.setAttribute("cnpjEmpresaMsg", cnpjEmpresaMsg);
                                isValid = false;
                            }
                        }
                    } else {
                        cnpjEmpresaMsg = messages.getString(cnpjEmpresaMsg);
                        cnpjEmpresaMsg = ServletUtils.mensagemFormatada(cnpjEmpresaMsg, locale, tamanho);
                        request.setAttribute("cnpjEmpresaMsg", cnpjEmpresaMsg);
                        isValid = false;
                    }
                } else {
                    cnpjEmpresaMsg = messages.getString(cnpjEmpresaMsg);
                    request.setAttribute("cnpjEmpresaMsg", cnpjEmpresaMsg);
                    isValid = false;
                }
            } else {
                cnpjEmpresaMsg = messages.getString(cnpjEmpresaMsg);
                request.setAttribute("cnpjEmpresaMsg", cnpjEmpresaMsg);
                isValid = false;
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
                    agenteIntegracaoMsg = messages.getString(agenteIntegracaoMsg);
                    request.setAttribute("agenteIntegracaoMsg", agenteIntegracaoMsg);
                    isValid = false;
                }
            } else {
                agenteIntegracaoMsg = messages.getString(agenteIntegracaoMsg);
                request.setAttribute("agenteIntegracaoMsg", agenteIntegracaoMsg);
                isValid = false;
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
                        if (e.equals(convenio.getEmpresa())) {
                            request.setAttribute("nomeEmpresa", nomeEmpresa);
                            System.out.print("Aceitou nome Empresa");
                        } else {
                            nomeEmpresaMsg = messages.getString("br.cefetrj.sisgee.valida_cadastro_empresa_servlet.msg_empresa_duplicada");
                            request.setAttribute("nomeEmpresaMsg", nomeEmpresaMsg);
                            isValid = false;
                        }
                    }
                } else {
                    nomeEmpresaMsg = messages.getString(nomeEmpresaMsg);
                    nomeEmpresaMsg = ServletUtils.mensagemFormatada(nomeEmpresaMsg, locale, tamanho);
                    request.setAttribute("nomeEmpresaMsg", nomeEmpresaMsg);
                    isValid = false;
                }
            } else {
                nomeEmpresaMsg = messages.getString(nomeEmpresaMsg);
                request.setAttribute("nomeEmpresaMsg", nomeEmpresaMsg);
                isValid = false;
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
                    }

                } else {
                    emailEmpresaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                    request.setAttribute("emailEmpresaMsg", emailEmpresaMsg);
                    isValid = false;
                }
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
                if (telefoneEmpresaMsg.trim().isEmpty()) {
                    telefoneEmpresa = telefoneEmpresa.replaceAll("[.|/|-]", "");
                    telefoneEmpresaMsg = ValidaUtils.validaInteger("telefoneEmpresa", telefoneEmpresa);
                    if (telefoneEmpresaMsg.trim().isEmpty()) {
                        telefoneEmpresaMsg = ValidaUtils.validaTelefone("telefoneEmpresa", telefoneEmpresa);
                        if (telefoneEmpresaMsg.trim().isEmpty()) {
                            //Alterações, nova condição
                            telefoneEmpresaMsg = ValidaUtils.validaNumeroDDD("telefoneEmpresa", telefoneEmpresa);
                            if (telefoneEmpresaMsg.trim().isEmpty()) {
                                request.setAttribute("telefoneEmpresa", telefoneEmpresa);
                            } else {
                                telefoneEmpresaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                                request.setAttribute("telefoneEmpresaMsg", telefoneEmpresaMsg);
                                isValid = false;
                            }
                        } else {
                            telefoneEmpresaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                            request.setAttribute("telefoneEmpresaMsg", telefoneEmpresaMsg);
                            isValid = false;
                        }

                    } else {
                        telefoneEmpresaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                        request.setAttribute("telefoneEmpresaMsg", telefoneEmpresaMsg);
                        isValid = false;

                    }

                } else {
                    telefoneEmpresaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                    request.setAttribute("telefoneEmpresaMsg", telefoneEmpresaMsg);
                    isValid = false;
                }

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
                        contatoEmpresaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                        request.setAttribute("contatoEmpresaMsg", contatoEmpresaMsg);
                        isValid = false;
                    }
                } else {
                    contatoEmpresaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                    request.setAttribute("contatoEmpresaMsg", contatoEmpresaMsg);
                    isValid = false;
                }
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
                            //TODO trocar saída de console por Log
                            System.out.println("Data em formato incorreto, mesmo após validação na classe ValidaUtils");
                            isValid = false;
                        }
                    } else {
                        dataAssinaturaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                        request.setAttribute("dataAssinaturaEmpresaMsg", dataAssinaturaMsg);
                        isValid = false;
                        //TODO Fazer log
                    }
                } else {
                    dataAssinaturaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                    request.setAttribute("dataAssinaturaEmpresaMsg", dataAssinaturaMsg);
                    isValid = false;
                    //TODO Fazer log
                    System.out.println(dataAssinaturaMsg);
                }
            } else {
                dataAssinaturaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                request.setAttribute("dataAssinaturaEmpresaMsg", dataAssinaturaMsg);
                isValid = false;
                //TODO Fazer log
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
                                if (conv.equals(convenio)) {
                                    request.setAttribute("numeroEmpresa", numeroEmpresa);
                                    System.out.println("aceitou o numero");
                                } else {
                                    numeroEmpresaMsg = messages.getString("br.cefetrj.sisgee.valida_cadastro_empresa_servlet.msg_numeroConvenio_invalido");
                                    request.setAttribute("numeroEmpresaMsg", numeroEmpresaMsg);
                                    isValid = false;
                                }
                            }
                        } else {
                            numeroEmpresaMsg = messages.getString(numeroEmpresaMsg);
                            request.setAttribute("numeroEmpresaMsg", numeroEmpresaMsg);
                            isValid = false;
                        }
                    } else {
                        numeroEmpresaMsg = messages.getString(numeroEmpresaMsg);
                        numeroEmpresaMsg = ServletUtils.mensagemFormatada(numeroEmpresaMsg, locale, tamanho);
                        request.setAttribute("numeroEmpresaMsg", numeroEmpresaMsg);
                        isValid = false;
                    }
                } else {
                    numeroEmpresaMsg = messages.getString(numeroEmpresaMsg);
                    numeroEmpresaMsg = ServletUtils.mensagemFormatada(numeroEmpresaMsg, locale, tamanho);
                    request.setAttribute("numeroEmpresaMsg", numeroEmpresaMsg);
                    isValid = false;
                }
            } else {
                numeroEmpresaMsg = messages.getString(numeroEmpresaMsg);
                request.setAttribute("numeroEmpresaMsg", numeroEmpresaMsg);
                isValid = false;
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
                            anoEmpresaMsg = messages.getString(anoEmpresaMsg);
                            anoEmpresaMsg = ServletUtils.mensagemFormatada(anoEmpresaMsg, locale, tamanho);
                            request.setAttribute("anoEmpresaMsg", anoEmpresaMsg);
                            isValid = false;
                        }
                    } else {
                        anoEmpresaMsg = messages.getString(anoEmpresaMsg);
                        request.setAttribute("anoEmpresaMsg", anoEmpresaMsg);
                        isValid = false;
                    }
                } else {
                    anoEmpresaMsg = messages.getString(anoEmpresaMsg);
                    anoEmpresaMsg = ServletUtils.mensagemFormatada(anoEmpresaMsg, locale, tamanho);
                    request.setAttribute("anoEmpresaMsg", anoEmpresaMsg);
                    isValid = false;
                }
            } else {
                anoEmpresaMsg = messages.getString(anoEmpresaMsg);
                request.setAttribute("anoEmpresaMsg", anoEmpresaMsg);
                isValid = false;
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
                            if (e.equals(convenio.getPessoa())) {
                                request.setAttribute("cpfPessoa", cpfPessoa.replaceAll("[.|/|-]", ""));
                            } else {
                                cpfPessoaMsg = messages.getString("br.cefetrj.sisgee.valida_cadastro_empresa_servlet.msg_pessoafisica_duplicada");
                                request.setAttribute("cpfPessoaMsg", cpfPessoaMsg);
                                isValid = false;
                            }
                        }
                    } else {
                        cpfPessoaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                        request.setAttribute("cpfPessoaMsg", cpfPessoaMsg);
                        isValid = false;
                    }
                } else {
                    cpfPessoaMsg = messages.getString("br.cefetrj.sisgee.valida_utils.msg_valida_numerico");
                    request.setAttribute("cpfPessoaMsg", cpfPessoaMsg);
                    isValid = false;
                }
            } else {
                cpfPessoaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                cpfPessoaMsg = ServletUtils.mensagemFormatada(cpfPessoaMsg, locale, tamanho);
                request.setAttribute("cpfPessoaMsg", cpfPessoaMsg);
                isValid = false;
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
                        nomePessoaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                        request.setAttribute("nomePessoaMsg", nomePessoaMsg);
                        isValid = false;
                    }
                } else {
                    nomePessoaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                    request.setAttribute("nomePessoaMsg", nomePessoaMsg);
                    isValid = false;
                }
            } else {
                nomePessoaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                request.setAttribute("nomePessoaMsg", nomePessoaMsg);
                isValid = false;

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
                    }
                } else {
                    emailPessoaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                    request.setAttribute("emailPessoaMsg", emailPessoaMsg);
                    isValid = false;
                }

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
                        }
                    } else {
                        telefonePessoaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                        request.setAttribute("telefonePessoaMsg", telefonePessoaMsg);
                        isValid = false;
                    }
                } else {
                    telefonePessoaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                    request.setAttribute("telefonePessoaMsg", telefonePessoaMsg);
                    isValid = false;
                }

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
                        }
                    } else {
                        dataAssinaturaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                        request.setAttribute("dataAssinaturaPessoaMsg", dataAssinaturaMsg);
                        isValid = false;
                        //TODO Fazer log
                    }

                } else {
                    dataAssinaturaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                    request.setAttribute("dataAssinaturaPessoaMsg", dataAssinaturaMsg);
                    isValid = false;
                    //TODO Fazer log
                    System.out.println(dataAssinaturaMsg);
                }
            } else {
                dataAssinaturaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                request.setAttribute("dataAssinaturaPessoaMsg", dataAssinaturaMsg);
                isValid = false;
                //TODO Fazer log
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
                                if (conv.equals(convenio)) {
                                    request.setAttribute("numeroPessoa", numeroPessoa);
                                    System.out.println("aceitou o numero pessoa");
                                } else {
                                    numeroPessoaMsg = messages.getString("br.cefetrj.sisgee.valida_cadastro_empresa_servlet.msg_numeroConvenio_invalido");
                                    request.setAttribute("numeroPessoaMsg", numeroPessoaMsg);
                                    isValid = false;
                                }
                            }
                        } else {
                            numeroPessoaMsg = messages.getString(numeroPessoaMsg);
                            request.setAttribute("numeroPessoaMsg", numeroPessoaMsg);
                            isValid = false;
                        }
                    } else {
                        numeroPessoaMsg = messages.getString(numeroPessoaMsg);
                        numeroPessoaMsg = ServletUtils.mensagemFormatada(numeroPessoaMsg, locale, tamanho);
                        request.setAttribute("numeroPessoaMsg", numeroPessoaMsg);
                        isValid = false;
                    }
                } else {
                    numeroPessoaMsg = messages.getString(numeroPessoaMsg);
                    numeroPessoaMsg = ServletUtils.mensagemFormatada(numeroPessoaMsg, locale, tamanho);
                    request.setAttribute("numeroPessoaMsg", numeroPessoaMsg);
                    isValid = false;
                }
            } else {
                numeroPessoaMsg = messages.getString(numeroPessoaMsg);
                request.setAttribute("numeroPessoaMsg", numeroPessoaMsg);
                isValid = false;
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
                        }
                    } else {
                        anoPessoaMsg = messages.getString(anoPessoaMsg);
                        request.setAttribute("anoPessoaMsg", anoPessoaMsg);
                        isValid = false;
                    }
                } else {
                    anoPessoaMsg = messages.getString(anoPessoaMsg);
                    anoPessoaMsg = ServletUtils.mensagemFormatada(anoPessoaMsg, locale, tamanho);
                    request.setAttribute("anoPessoaMsg", anoPessoaMsg);
                    isValid = false;
                }
            } else {
                anoPessoaMsg = messages.getString(anoPessoaMsg);
                request.setAttribute("anoPessoaMsg", anoPessoaMsg);
                isValid = false;
            }

        }

        /**
         * Teste das variáveis booleanas após validação. Redirecionamento para a
         * inclusão ou devolver para o formulário com as mensagens.
         */
        if (isValid) {
            request.setAttribute("numeroAntigo", numeroSugerido);
            request.getRequestDispatcher("/AtualizaConvenioServlet").forward(request, response);
        } else {
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
            String msg = messages.getString("br.cefetrj.sisgee.valida_cadastro_empresa_servlet.msg_atencao");
            request.setAttribute("msg", msg);
            request.getRequestDispatcher("/form_edita_empresa.jsp").forward(request, response);
        }
    }

    private String formataData(Date data) {
        String dataFormatada = data.toString();
        String ano = dataFormatada.substring(0, 4);
        String mes = dataFormatada.substring(5, 7);
        String dia = dataFormatada.substring(8);
        dataFormatada = dia.concat("/" + mes).concat("/" + ano);
        return dataFormatada;
    }

}
