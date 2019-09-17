/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetrj.sisgee.migracao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.ParseException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * Efetua a migração da planilha de alunos e matrículas.
 * 
 * @author c18634659798
 */
public class PrincipalMatricula {
    public static void main(String[] args) throws ParseException {
        
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("sisgeePU");
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        String [] arrayLinha;
        
        int cont = 0;

        try{
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\09513592740\\OneDrive\\Documentos\\CEFET\\Sistemas\\Diemp_dados_migracao\\matriculas_alunos.csv"));
            while(br.ready()){   
                Matricula m= new Matricula();
                String linha = br.readLine();
                System.out.println("leu linha:" + linha);
                if(cont==0||cont==1){
                    cont++;
                }
                else{
                    cont++;
                    arrayLinha=linha.split(";");
                    m.setNome(arrayLinha[0]);
                    m.setCurso(arrayLinha[3]);
                    m.setMatricula(arrayLinha[1]);
                    if(arrayLinha.length>3){
                        m.setCpf(arrayLinha[4]);
                        m.setUnidade(arrayLinha[5]);
                        m.setSituacao(arrayLinha[6]);
                        m.setLogradouro(arrayLinha[17]);
                        m.setNumero(arrayLinha[18]);
                        m.setComplemento(arrayLinha[19]);
                        m.setBairro(arrayLinha[20]);
                        m.setCep(arrayLinha[21]);
                        m.setUf(arrayLinha[24]);
                    }
                    
                    manager.persist(m);
                }
            }
            br.close();
            manager.getTransaction().commit();
        }catch(Exception ioe){
            System.out.println("na linha " + cont);
            ioe.printStackTrace();
            manager.getTransaction().rollback();
            System.exit(-1);
        }finally{
            System.out.println("numero de linhas " + cont);
            manager.close();
            factory.close();
        }  
        
    }
}

