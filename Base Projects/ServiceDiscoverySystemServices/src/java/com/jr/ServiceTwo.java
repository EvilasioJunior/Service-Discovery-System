/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jr;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 *
 * @author junior
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class ServiceTwo {
   @WebMethod
    public String funcService(String valor){       
        return "Serviço 2 executado com sucesso";
    }
}
