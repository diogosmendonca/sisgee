/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetrj.sisgee.migracao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Executa a migração da planilha de convênios.
 * 
 * @author c18634659798
 */
public class DIEMP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
        int a;
        
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("sisgeePU");
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();    

        ArrayList<Conveniado> listaConvenio = new ArrayList<Conveniado>();
        String arrayLinha[];
        int cont = 0;
        try{
            //todas BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\c18634659798\\Documents\\Final\\RELAÇÃO DE EMPRESAS CONVENIADASf.csv"));
            //só as atuais com cnpj
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\c18634659798\\Documents\\empresas_cnpj_colocado_depois\\RELAÇÃO DE EMPRESAS CONVENIADAS ATUAL_CSV.csv"));
            
            while(br.ready()){
              a=0;
              Conveniado c = new Conveniado();
              String linha = br.readLine();
              System.out.println("leu linha: " + linha);
              if(cont < 3){
                  cont++;
              }
              else{
                cont++;
                arrayLinha=linha.split(";");
                System.out.println("tamanho do array = " + arrayLinha.length);
                for( int i=0; i<arrayLinha[5].length();i++){
                    if(i+5<arrayLinha[5].length()){
                        if(a==0 && Character.isDigit(arrayLinha[5].charAt(i))==true && Character.isDigit(arrayLinha[5].charAt(i+5))==true){                 
                            c.setNome(arrayLinha[5].substring(0, i));
                            arrayLinha[5]=arrayLinha[5].substring(i).replace(" ", "");
                            arrayLinha[5]=arrayLinha[5].replace("/", "");
                            arrayLinha[5]=arrayLinha[5].replace("-", "");
                            c.setCnpj(arrayLinha[5]);
                            a=1;
                            break;
                        }
                    }
                    if ((arrayLinha[5].charAt(i) == '-' ||arrayLinha[5].charAt(i) == '–' )&& a==0 && i+6<arrayLinha[5].length()) {
                        if (Character.isDigit(arrayLinha[5].charAt(i+2))==true && Character.isDigit(arrayLinha[5].charAt(i+6))==true){
                            if(arrayLinha[5].substring(0, i).equalsIgnoreCase("Telca")){
                                break;
                            }
                            else{
                                if(i-5>0){ //Para o caso do -(traço) fazer parte do nome da empresa, como na linha 862
                                    if(arrayLinha[5].substring(i-5, i-1).equalsIgnoreCase("cnpj")){
                                        c.setNome(arrayLinha[5].substring(0, i-7));
                                        arrayLinha[5]=arrayLinha[5].substring(i+1).replace(" ", "");
                                        arrayLinha[5]=arrayLinha[5].replace("/", "");
                                        arrayLinha[5]=arrayLinha[5].replace("-", "");
                                        c.setCnpj(arrayLinha[5]);
                                        }
                                    else{
                                        c.setNome(arrayLinha[5].substring(0, i));
                                        arrayLinha[5]=arrayLinha[5].substring(i+1).replace(" ", "");
                                        arrayLinha[5]=arrayLinha[5].replace("/", "");
                                        arrayLinha[5]=arrayLinha[5].replace("-", "");
                                        c.setCnpj(arrayLinha[5]);
                                    }
                                    a=1;
                                }
                            }
                            
                        }
                    }
                }
                if(a==0){
                    c.setNome(arrayLinha[5]);
                }
                arrayLinha[0]=arrayLinha[0].replace('-', '/').replace(" ", "");
                for(int i=0;i<arrayLinha[0].length()-1;i++){
                    if(arrayLinha[0].charAt(i)=='/'&& arrayLinha[0].charAt(i+1)=='/'){
                        arrayLinha[0]=arrayLinha[0].substring(0, i)+arrayLinha[0].substring(i+1);
                    }
                }
                c.setNumConvenio(arrayLinha[0]);
                //arrayLinha[1]=arrayLinha[1].replaceAll(" ", "");
                if(arrayLinha[1].length()>0){
                    if(Character.isDigit(arrayLinha[1].charAt(0))== true){
                        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                        Date data = formato.parse(arrayLinha[1]);
                        c.setVigencia(data);
                    }
                }
                
                
                a=0;
                for(int i=0;i<arrayLinha[2].length();i++){
                    if(arrayLinha[2].charAt(i) == '/'){ 
                        if(arrayLinha[2].substring(0, i-1).length()<=9){
                            c.setTelefone("21"+arrayLinha[2].substring(0, i).replace(" ", "").replace("-", "").replace("(", "").replace(")", ""));
                            
                        }
                        else{
                            c.setTelefone(arrayLinha[2].substring(0, i).replace(" ", "").replace("-", "").replace("(", "").replace(")", ""));
                        }
                        if(arrayLinha[2].substring(i+1).length()<=6 && arrayLinha[2].substring(i+1).length()>=4){
                            c.setTelefone2(c.getTelefone().substring(0, 6) + arrayLinha[2].substring(i+1).replace(" ", "").replace("-", "").replace("(", "").replace(")", ""));
                        }
                        else{
                            c.setTelefone2(c.getTelefone().substring(0, 2) + arrayLinha[2].substring(i+1).replace(" ", "").replace("-", "").replace("(", "").replace(")", ""));
                            if(c.getTelefone2().length()>10){
                                c.setTelefone2(c.getTelefone2().substring(0, 11));
                            }
                        }
                        a=1;
                        break;
                    }
                    if(arrayLinha[2].charAt(i) == 'R'){
                        c.setTelefone(arrayLinha[2].substring(0, i).replace(" ", "").replace("-", "").replace("(", "").replace(")", ""));
                        System.out.println("Tá assim" + c.getTelefone());
                        if(c.getTelefone().length()<=9){
                            c.setTelefone("21"+c.getTelefone());
                        }
                        c.setRamal(arrayLinha[2].substring(i+2));
                        System.out.println("colocou ramal" + c.getRamal());
                        a=1;
                        break;
                    }
                }
                if(a==0 && !arrayLinha[2].trim().equals("") && Character.isDigit(arrayLinha[2].replace(" ", "").replace("(", "").charAt(0))==true ){
                    c.setTelefone(arrayLinha[2].replace(" ", "").replace("-", "").replace("(", "").replace(")", ""));
                    if(c.getTelefone().length()<=9){
                        c.setTelefone("21"+c.getTelefone());
                    }
                }
                a=0;
                if(!arrayLinha[3].trim().equals("") && !arrayLinha[3].equalsIgnoreCase("0")){
                    if(arrayLinha[3].substring(0,4).equalsIgnoreCase("cnpj")){
                        arrayLinha[3]=arrayLinha[3].substring(5).replace(" ", "");
                        arrayLinha[3]=arrayLinha[3].replace("/", "");
                        arrayLinha[3]=arrayLinha[3].replace("-", "");
                        c.setCnpj(arrayLinha[3]);
                    }else{
                        for( int i=0; i<arrayLinha[3].length();i++){
                            if(arrayLinha[3].charAt(i) == '@'){
                                c.setEmail(arrayLinha[3]);
                                a=1;
                            }
                        }
                        if(a==0){
                            c.setObs(arrayLinha[3]);
                            System.out.println("colocou obs" + c.getObs());
                        }
                    }
                }
                if(arrayLinha[4].indexOf("sim")!= -1 ) {
                    c.setAgenteIntegracao(true);
                  } else {
                    c.setAgenteIntegracao(false);
                  }
                
                if(c.getNumConvenio()!= null){
                    String x= c.getNumConvenio();
                    for(int i=0;i<x.length();i++){
                        if(x.charAt(i)=='/'){
                            c.setNumero(x.substring(0, i));
                            c.setAno(x.substring(i+1));
                        }
                    }
                }
                manager.persist(c);            
                listaConvenio.add(c);
                if(cont == 1903){ // AQUI É O NÚMERO DA ÚLTIMA LINHA USADA DA TABELA
                    break;
                }
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
            manager.close();
            factory.close();
        }  
       
    }  
}
