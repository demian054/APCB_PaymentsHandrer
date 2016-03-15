/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.apcb.paymentsHandler.utils;

import com.apcb.paymentsHandler.enums.ActionMethodEnum;
import com.apcb.utils.paymentHandler.entities.APCB_PayMain;
import com.apcb.utils.paymentHandler.enums.StatusIdEnum;
import com.apcb.utils.conection.ConectionHttpsURL;
import com.apcb.utils.enums.RequestMethodEnum;
import com.apcb.utils.utils.DateParser;
import com.apcb.utils.utils.PdfCreator;
import com.apcb.utils.utils.PropertiesReader;
import com.google.gson.Gson;
import java.util.Calendar;
import org.apache.log4j.Logger;


public class InstaPago_Conection {
    private final static Logger log = Logger.getLogger(InstaPago_Conection.class);
    
    public APCB_PayMain post(APCB_PayMain instaPagoMainRequest, ActionMethodEnum actionMethod, RequestMethodEnum requestMethod) throws Exception{
        PropertiesReader prop = new PropertiesReader("InstaPagoConnection");
        //InstaPagoMainRequest request = new InstaPagoMainRequest();        
        instaPagoMainRequest.setKeyId(prop.getProperty("KeyId"));
        instaPagoMainRequest.setPublicKeyId(prop.getProperty("PublicKeyId"));
        
        prop.setProperty("Method", actionMethod.getDescription());

        Calendar ExpirationDate = DateParser.fromDateSring(instaPagoMainRequest.getExpirationDate(), prop, "ExpirationDateCardFormatIN" );
        instaPagoMainRequest.setExpirationDate(DateParser.toDateSring(ExpirationDate, prop, "ExpirationDateCardFormatOUT"));
        
        log.info(instaPagoMainRequest.toString());
        ConectionHttpsURL con = new ConectionHttpsURL(prop);
        String InstaPagoResp = con.send(instaPagoMainRequest.toString(), requestMethod);
                
        log.info("InstaPago literal response: "+InstaPagoResp); 
        
        APCB_PayMain instaPagoMainResponse = (APCB_PayMain) new Gson().fromJson(InstaPagoResp, APCB_PayMain.class);
        instaPagoMainResponse = merge(instaPagoMainRequest, instaPagoMainResponse);
        ExpirationDate = DateParser.fromDateSring(instaPagoMainResponse.getExpirationDate(), prop, "ExpirationDateCardFormatOUT" );
        instaPagoMainResponse.setExpirationDate(DateParser.toDateSring(ExpirationDate, prop, "ExpirationDateCardFormatIN"));
        try {
            if (instaPagoMainResponse.getVoucher()!=null && !instaPagoMainResponse.getVoucher().isEmpty()){
                String nameFile = PdfCreator.createPdfVoucher(instaPagoMainResponse,prop);
                log.info("nameFile"+nameFile);
                instaPagoMainResponse.setVoucher(nameFile);
                log.info("instaPagoMainResponse"+instaPagoMainResponse.getVoucher());
            }   
        } catch(Exception e) {
            //log.error("Cant write Voucher File",e);
            e.printStackTrace();
            //throw new Exception("Cant write Voucher File");
        }
        return instaPagoMainResponse;    
        //return (InstaPagoMainResponse) XmlParser.fromXML(InstaPagoResp, InstaPagoMainResponse.class);    
    }
   
    private APCB_PayMain merge(APCB_PayMain payMainRQ, APCB_PayMain payMainRS){
        payMainRQ.setSuccess(payMainRS.isSuccess());
        payMainRQ.setMessage(payMainRS.getMessage());
        payMainRQ.setId(payMainRS.getId());
        payMainRQ.setReference(payMainRS.getReference());
        payMainRQ.setVoucher(payMainRS.getVoucher());
        payMainRQ.setOrderNumber(payMainRS.getOrderNumber());
        payMainRQ.setSequence(payMainRS.getSequence());
        payMainRQ.setApproval(payMainRS.getApproval());
        
        return payMainRQ;
    }
    /*public static void main(String[] args) throws Exception {
        
        APCB_PayMain instaPagoMainRequest = new APCB_PayMain();

        instaPagoMainRequest.setAmount(new Double("10.00"));
        instaPagoMainRequest.setDescription("Prueba");
        instaPagoMainRequest.setCardHolder("11");
        instaPagoMainRequest.setCardHolderID("17498122");
        instaPagoMainRequest.setCardNumber(new Long("4111111111111111"));
        instaPagoMainRequest.setCVC(new Integer("123"));
        instaPagoMainRequest.setExpirationDate("10-20");
        
        instaPagoMainRequest.setStatusId(StatusIdEnum.Pagar);
        instaPagoMainRequest.setIP("192.168.1.108");
        instaPagoMainRequest.setOrderNumber(1);
        InstaPago_Conection instaPago_Conection = new InstaPago_Conection();
        APCB_PayMain instaPagoMainResponse = instaPago_Conection.post(instaPagoMainRequest,ActionMethodEnum.Payment,RequestMethodEnum.Post);
        
        log.debug(new Gson().toJson(instaPagoMainResponse));
    }*/
    

}
