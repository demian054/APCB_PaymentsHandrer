/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.apcb.paymentsHandler.utils;

import com.apcb.paymentsHandler.InstaPagoPrincipalEntities.InstaPagoMainRequest;
import com.apcb.paymentsHandler.InstaPagoPrincipalEntities.InstaPagoMainResponse;
import com.apcb.paymentsHandler.InstaPagoPrincipalEntities.StatusIdEnum;
import com.apcb.utils.conection.ConectionHttpsURL;
import com.apcb.utils.utils.PropertiesReader;
import com.apcb.utils.ticketsHandler.Enums.CabinTypeEnum;
import com.apcb.utils.ticketsHandler.entities.APCB_Travel;
import com.apcb.utils.xml.XmlParser;
import com.google.gson.Gson;
import org.apache.log4j.Logger;


public class InstaPago_Conection {
    private final static Logger log = Logger.getLogger(InstaPago_Conection.class);
    
    public InstaPagoMainResponse post(InstaPagoMainRequest instaPagoMainRequest) throws Exception{
        PropertiesReader prop = new PropertiesReader("InstaPagoConnection");
        //InstaPagoMainRequest request = new InstaPagoMainRequest();        
        instaPagoMainRequest.setKeyId(prop.getProperty("KeyId"));
        instaPagoMainRequest.setPublicKeyId(prop.getProperty("PublicKeyId"));

        log.info(instaPagoMainRequest.toString());
        ConectionHttpsURL con = new ConectionHttpsURL(prop);
        String InstaPagoResp = con.sendPost(instaPagoMainRequest.toString());
                
        log.info("InstaPago literal response: "+InstaPagoResp); 
        return (InstaPagoMainResponse) new Gson().fromJson(InstaPagoResp, InstaPagoMainResponse.class);    
        //return (InstaPagoMainResponse) XmlParser.fromXML(InstaPagoResp, InstaPagoMainResponse.class);    
    }
    
    public static void main(String[] args) throws Exception {
        
        InstaPagoMainRequest instaPagoMainRequest = new InstaPagoMainRequest();

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
        InstaPagoMainResponse instaPagoMainResponse = instaPago_Conection.post(instaPagoMainRequest);
 
    }
}
