/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.apcb.paymentsHandler.process;

import com.apcb.paymentsHandler.enums.ActionMethodEnum;
import com.apcb.paymentsHandler.utils.InstaPago_Conection;
import com.apcb.utils.entities.Message;
import com.apcb.utils.entities.Request;
import com.apcb.utils.entities.Response;
import com.apcb.utils.enums.RequestMethodEnum;
import com.apcb.utils.paymentHandler.entities.PayMainRequest;
import com.apcb.utils.paymentHandler.entities.PayMainResponse;
import com.apcb.utils.ticketsHandler.enums.MessagesTypeEnum;
import com.google.gson.Gson;

/**
 *
 * @author Demian
 */
public class APCBPaymentsHandrerProcess {
    private org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(APCBPaymentsHandrerProcess.class);
    private Gson gson = new Gson();

    public Response createPay(Request request) {
        Response response = new Response();
        PayMainRequest payMainRequest = request.getPayMainRequest();
        if (payMainRequest==null){            
            response.setMessage(new Message(MessagesTypeEnum.ErrorValidate_ObjectPayMain));
            return response;
        }
        InstaPago_Conection instaPago_Conection;
        try {
            instaPago_Conection = new InstaPago_Conection();
            PayMainResponse instaPagoMainResponse = instaPago_Conection.post(payMainRequest,ActionMethodEnum.Payment,RequestMethodEnum.Post);
            log.debug(new Gson().toJson(instaPagoMainResponse)); 
        } catch (Exception e) {
            response.setMessage(new Message(MessagesTypeEnum.ErrorAccessExt_IntaPago));
            return response;
        }
        return response;
    }

    public Response completePay(Request request) {
        Response response = new Response();
        PayMainRequest payMainRequest = request.getPayMainRequest();
        if (payMainRequest==null){            
            response.setMessage(new Message(MessagesTypeEnum.ErrorValidate_ObjectPayMain));
            return response;
        }
        InstaPago_Conection instaPago_Conection;
        try {
            instaPago_Conection = new InstaPago_Conection();
            PayMainResponse instaPagoMainResponse = instaPago_Conection.post(payMainRequest,ActionMethodEnum.Complete,RequestMethodEnum.Post);
            log.debug(new Gson().toJson(instaPagoMainResponse)); 
        } catch (Exception e) {
            response.setMessage(new Message(MessagesTypeEnum.ErrorAccessExt_IntaPago));
            return response;
        }
        return response; }

    public Response consultPay(Request request) {
                Response response = new Response();
        PayMainRequest payMainRequest = request.getPayMainRequest();
        if (payMainRequest==null){            
            response.setMessage(new Message(MessagesTypeEnum.ErrorValidate_ObjectPayMain));
            return response;
        }
        InstaPago_Conection instaPago_Conection;
        try {
            instaPago_Conection = new InstaPago_Conection();
            PayMainResponse instaPagoMainResponse = instaPago_Conection.post(payMainRequest,ActionMethodEnum.Payment,RequestMethodEnum.Get);
            log.debug(new Gson().toJson(instaPagoMainResponse)); 
        } catch (Exception e) {
            response.setMessage(new Message(MessagesTypeEnum.ErrorAccessExt_IntaPago));
            return response;
        }
        return response;
    }

    public Response annularPay(Request request) {
        Response response = new Response();
        PayMainRequest payMainRequest = request.getPayMainRequest();
        if (payMainRequest==null){            
            response.setMessage(new Message(MessagesTypeEnum.ErrorValidate_ObjectPayMain));
            return response;
        }
        InstaPago_Conection instaPago_Conection;
        try {
            instaPago_Conection = new InstaPago_Conection();
            PayMainResponse instaPagoMainResponse = instaPago_Conection.post(payMainRequest,ActionMethodEnum.Payment,RequestMethodEnum.Delete);
            log.debug(new Gson().toJson(instaPagoMainResponse)); 
        } catch (Exception e) {
            response.setMessage(new Message(MessagesTypeEnum.ErrorAccessExt_IntaPago));
            return response;
        }
        return response;
    }
    
}
