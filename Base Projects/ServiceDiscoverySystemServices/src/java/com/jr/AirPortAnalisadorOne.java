/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jr;

import java.io.*;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 *
 * @author junior
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class AirPortAnalisadorOne {
   @WebMethod
    public String funcService(String arquivo, String arquivomonitor){
        
        //variáveis de controle
        String aux;
        try{
            BufferedReader in = new BufferedReader(new FileReader(arquivomonitor));
            BufferedWriter out = new BufferedWriter(new FileWriter(arquivo));
            aux = in.readLine();
            while(!(aux==null)){
                out.write(aux);
                aux = in.readLine();
                if(aux==null)
                    continue;
                out.newLine();
            }
            in.close();
            out.close();
        }catch(Exception e){}
      
        String MensagemRetorno = "Análise concluída com sucesso";

        return MensagemRetorno;
    }
}