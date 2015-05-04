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
public class ServiceOne {
   @WebMethod
    public String funcService(String valor){       
        return "Service 1 executed with sucess";
    }
}
