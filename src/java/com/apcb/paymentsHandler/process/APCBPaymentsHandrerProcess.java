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
import com.apcb.utils.paymentHandler.entities.APCB_PayMain;
import com.apcb.utils.paymentHandler.enums.StatusIdEnum;
import com.apcb.utils.ticketsHandler.enums.MessagesTypeEnum;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 *
 * @author Demian
 */
public class APCBPaymentsHandrerProcess {
    private Logger log = LogManager.getLogger(APCBPaymentsHandrerProcess.class);
    private Gson gson = new Gson();

    public Response createPay(Request request) {
        Response response = new Response(request.getSesionId());
        APCB_PayMain payMainRequest = request.getPayMainInfo();
        payMainRequest.setStatusId(StatusIdEnum.Retener);
        if (payMainRequest==null){            
            response.setMessage(new Message(MessagesTypeEnum.ErrorValidate_ObjectPayMain));
            return response;
        }
        payMainRequest.setOrderNumber(Integer.valueOf(request.getTravelInfo().getTransactionId()));
        payMainRequest.setDescription(payMainRequest.getDescription()+" "+request.getTravelInfo().getTransactionId());
        InstaPago_Conection instaPago_Conection;
        try {
            instaPago_Conection = new InstaPago_Conection();
            APCB_PayMain instaPagoMainResponse = instaPago_Conection.post(payMainRequest,ActionMethodEnum.Payment,RequestMethodEnum.Post);
            log.debug(new Gson().toJson(instaPagoMainResponse)); 
            if (instaPagoMainResponse.isSuccess() && instaPagoMainResponse.getCode().equalsIgnoreCase("201")){
                response.setMessage(new Message(MessagesTypeEnum.Ok));
            } else {
                response.setMessage(new Message(instaPagoMainResponse.getCode(), instaPagoMainResponse.getMessage()));
                log.error("IntaPago ERROR, code:"+instaPagoMainResponse.getCode()+ " message:"+ instaPagoMainResponse.getMessage());
            }
            response.setPayMainInfo(instaPagoMainResponse);
            response.setTravelInfo(request.getTravelInfo());
            log.info(response.getMessage().getMsgDesc());
           
        } catch (Exception e) {
            response.setMessage(new Message(MessagesTypeEnum.ErrorAccessExt_IntaPago));
            return response;
        }
        return response;
    }

    public Response completePay(Request request) {
        Response response = new Response(request.getSesionId());
        APCB_PayMain payMainRequest = request.getPayMainInfo();
        if (payMainRequest==null){            
            response.setMessage(new Message(MessagesTypeEnum.ErrorValidate_ObjectPayMain));
            return response;
        }
        InstaPago_Conection instaPago_Conection;
        payMainRequest.setOrderNumber(Integer.valueOf(request.getTravelInfo().getTransactionId()));
        payMainRequest.setDescription(payMainRequest.getDescription()+" "+request.getTravelInfo().getTransactionId());
        try {
            instaPago_Conection = new InstaPago_Conection();
            APCB_PayMain instaPagoMainResponse = instaPago_Conection.post(payMainRequest,ActionMethodEnum.Complete,RequestMethodEnum.Post);
            log.debug(new Gson().toJson(instaPagoMainResponse)); 
            if (instaPagoMainResponse.isSuccess() && instaPagoMainResponse.getCode().equalsIgnoreCase("201")){
                response.setMessage(new Message(MessagesTypeEnum.Ok));
            } else {
                response.setMessage(new Message(instaPagoMainResponse.getCode(), instaPagoMainResponse.getMessage()));
                log.error("IntaPago ERROR, code:"+instaPagoMainResponse.getCode()+ " message:"+ instaPagoMainResponse.getMessage());
            }
            response.setPayMainInfo(instaPagoMainResponse);
            response.setTravelInfo(request.getTravelInfo());
        } catch (Exception e) {
            response.setMessage(new Message(MessagesTypeEnum.ErrorAccessExt_IntaPago));
            return response;
        }
        return response; }

    public Response consultPay(Request request) {
        Response response = new Response(request.getSesionId());
        APCB_PayMain payMainRequest = request.getPayMainInfo();
        if (payMainRequest==null){            
            response.setMessage(new Message(MessagesTypeEnum.ErrorValidate_ObjectPayMain));
            return response;
        }
        InstaPago_Conection instaPago_Conection;
        payMainRequest.setOrderNumber(Integer.valueOf(request.getTravelInfo().getTransactionId()));
        payMainRequest.setDescription(payMainRequest.getDescription()+" "+request.getTravelInfo().getTransactionId());
        try {
            instaPago_Conection = new InstaPago_Conection();
            APCB_PayMain instaPagoMainResponse = instaPago_Conection.post(payMainRequest,ActionMethodEnum.Payment,RequestMethodEnum.Get);
            log.debug(new Gson().toJson(instaPagoMainResponse));
            if (instaPagoMainResponse.isSuccess() && instaPagoMainResponse.getCode().equalsIgnoreCase("201")){
                response.setMessage(new Message(MessagesTypeEnum.Ok));
            } else {
                response.setMessage(new Message(instaPagoMainResponse.getCode(), instaPagoMainResponse.getMessage()));
                log.error("IntaPago ERROR, code:"+instaPagoMainResponse.getCode()+ " message:"+ instaPagoMainResponse.getMessage());
            }
            response.setPayMainInfo(instaPagoMainResponse);
            response.setTravelInfo(request.getTravelInfo());
        } catch (Exception e) {
            response.setMessage(new Message(MessagesTypeEnum.ErrorAccessExt_IntaPago));
            return response;
        }
        return response;
    }

    public Response annularPay(Request request) {
        Response response = new Response(request.getSesionId());
        APCB_PayMain payMainRequest = request.getPayMainInfo();
        if (payMainRequest==null){            
            response.setMessage(new Message(MessagesTypeEnum.ErrorValidate_ObjectPayMain));
            return response;
        }
        InstaPago_Conection instaPago_Conection;
        payMainRequest.setOrderNumber(Integer.valueOf(request.getTravelInfo().getTransactionId()));
        payMainRequest.setDescription(payMainRequest.getDescription()+" "+request.getTravelInfo().getTransactionId());
        try {
            instaPago_Conection = new InstaPago_Conection();
            APCB_PayMain instaPagoMainResponse = instaPago_Conection.post(payMainRequest,ActionMethodEnum.Payment,RequestMethodEnum.Delete);
            log.debug(new Gson().toJson(instaPagoMainResponse)); 
            if (instaPagoMainResponse.isSuccess() && instaPagoMainResponse.getCode().equalsIgnoreCase("201")){
                response.setMessage(new Message(MessagesTypeEnum.Ok));
            } else {
                response.setMessage(new Message(instaPagoMainResponse.getCode(), instaPagoMainResponse.getMessage()));
                log.error("IntaPago ERROR, code:"+instaPagoMainResponse.getCode()+ " message:"+ instaPagoMainResponse.getMessage());
            }
            response.setPayMainInfo(instaPagoMainResponse);
            response.setTravelInfo(request.getTravelInfo());
        } catch (Exception e) {
            response.setMessage(new Message(MessagesTypeEnum.ErrorAccessExt_IntaPago));
            return response;
        }
        return response;
    }

    
}
