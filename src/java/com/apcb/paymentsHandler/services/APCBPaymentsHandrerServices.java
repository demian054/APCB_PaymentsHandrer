/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.apcb.paymentsHandler.services;

import com.apcb.paymentsHandler.process.APCBPaymentsHandrerProcess;
import com.apcb.utils.entities.Message;
import com.apcb.utils.entities.Request;
import com.apcb.utils.entities.Response;
import com.apcb.utils.ticketsHandler.enums.MessagesTypeEnum;
import com.google.gson.Gson;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author Demian
 */
@WebService(serviceName = "APCBPaymentsHandrerServices")
public class APCBPaymentsHandrerServices {
 
    private org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(APCBPaymentsHandrerServices.class);
    private Gson gson = new Gson();

    @WebMethod(operationName = "createPay")
    public String createPay(@WebParam(name = "strRequest") String strRequest) {
        log.info("APCBPaymentsHandrerServices -> createPay ini");
        Response response = new Response();
        try {
            Request payMainRequest = new Request(strRequest); 
            APCBPaymentsHandrerProcess process = new APCBPaymentsHandrerProcess();
            response = process.createPay(payMainRequest);
        } catch (Exception e) {
            response.setMessage(new Message(MessagesTypeEnum.Error_AplicationErrorNotHandler));
            log.error(response.getMessage().getMsgDesc(), e);
        }
        log.info("APCBPaymentsHandrerServices -> createPay end");
        return gson.toJson(response);
    }
    
    @WebMethod(operationName = "completePay")
    public String completePay(@WebParam(name = "strRequest") String strRequest) {
        log.info("APCBPaymentsHandrerServices -> completePay ini");
        Response response = new Response();
        try {
            Request payMainRequest = new Request(strRequest); 
            APCBPaymentsHandrerProcess process = new APCBPaymentsHandrerProcess();
            response = process.completePay(payMainRequest);
        } catch (Exception e) {
            response.setMessage(new Message(MessagesTypeEnum.Error_AplicationErrorNotHandler));
            log.error(response.getMessage().getMsgDesc(), e);
        }
        log.info("APCBPaymentsHandrerServices -> completePay end");
        return gson.toJson(response);
    }
    
    @WebMethod(operationName = "consultPay")
    public String consultPay(@WebParam(name = "strRequest") String strRequest) {
        log.info("APCBPaymentsHandrerServices -> consultPay ini");
        Response response = new Response();
        try {
            Request payMainRequest = new Request(strRequest); 
            APCBPaymentsHandrerProcess process = new APCBPaymentsHandrerProcess();
            response = process.consultPay(payMainRequest);
        } catch (Exception e) {
            response.setMessage(new Message(MessagesTypeEnum.Error_AplicationErrorNotHandler));
            log.error(response.getMessage().getMsgDesc(), e);
        }
        log.info("APCBPaymentsHandrerServices -> consultPay end");
        return gson.toJson(response);
    }
    
    @WebMethod(operationName = "annularPay")
    public String annularPay(@WebParam(name = "strRequest") String strRequest) {
        log.info("APCBPaymentsHandrerServices -> annularPay ini");
        Response response = new Response();
        try {
            Request payMainRequest = new Request(strRequest); 
            APCBPaymentsHandrerProcess process = new APCBPaymentsHandrerProcess();
            response = process.annularPay(payMainRequest);
        } catch (Exception e) {
            response.setMessage(new Message(MessagesTypeEnum.Error_AplicationErrorNotHandler));
            log.error(response.getMessage().getMsgDesc(), e);
        }
        log.info("APCBPaymentsHandrerServices -> annularPay end");
        return gson.toJson(response);
    }
}
