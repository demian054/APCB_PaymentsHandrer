/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.apcb.paymentsHandler.utils;

import com.apcb.paymentsHandler.enums.ActionMethodEnum;
import com.apcb.utils.paymentHandler.entities.PayMainRequest;
import com.apcb.utils.paymentHandler.entities.PayMainResponse;
import com.apcb.utils.paymentHandler.enums.StatusIdEnum;
import com.apcb.utils.conection.ConectionHttpsURL;
import com.apcb.utils.enums.RequestMethodEnum;
import com.apcb.utils.utils.PropertiesReader;
import com.google.gson.Gson;
import org.apache.log4j.Logger;


public class InstaPago_Conection {
    private final static Logger log = Logger.getLogger(InstaPago_Conection.class);
    
    public PayMainResponse post(PayMainRequest instaPagoMainRequest, ActionMethodEnum actionMethod, RequestMethodEnum requestMethod) throws Exception{
        PropertiesReader prop = new PropertiesReader("InstaPagoConnection");
        //InstaPagoMainRequest request = new InstaPagoMainRequest();        
        instaPagoMainRequest.setKeyId(prop.getProperty("KeyId"));
        instaPagoMainRequest.setPublicKeyId(prop.getProperty("PublicKeyId"));
        
        prop.setProperty("Method", actionMethod.getDescription());

        log.info(instaPagoMainRequest.toString());
        ConectionHttpsURL con = new ConectionHttpsURL(prop);
        String InstaPagoResp = con.send(instaPagoMainRequest.toString(), requestMethod);
                
        log.info("InstaPago literal response: "+InstaPagoResp); 
        return (PayMainResponse) new Gson().fromJson(InstaPagoResp, PayMainResponse.class);    
        //return (InstaPagoMainResponse) XmlParser.fromXML(InstaPagoResp, InstaPagoMainResponse.class);    
    }
   
    
    public static void main(String[] args) throws Exception {
        
        PayMainRequest instaPagoMainRequest = new PayMainRequest();

        instaPagoMainRequest.setAmount(new Double("10.00"));
        instaPagoMainRequest.setDescription("Prueba");
        instaPagoMainRequest.setCardHolder("11");
        instaPagoMainRequest.setCardHolderID(1);
        instaPagoMainRequest.setCardNumber(new Long("4111111111111111"));
        instaPagoMainRequest.setCVC(new Integer("123"));
        instaPagoMainRequest.setExpirationDate("10/2020");
        instaPagoMainRequest.setStatusId(StatusIdEnum.Pagar);
        instaPagoMainRequest.setIP("192.168.1.108");
        
        instaPagoMainRequest.setOrderNumber(1);

        InstaPago_Conection instaPago_Conection = new InstaPago_Conection();
        PayMainResponse instaPagoMainResponse = instaPago_Conection.post(instaPagoMainRequest,ActionMethodEnum.Payment,RequestMethodEnum.Post);
        log.debug(new Gson().toJson(instaPagoMainResponse));
    }
}
