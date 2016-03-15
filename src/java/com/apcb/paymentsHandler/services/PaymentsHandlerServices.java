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
import java.io.PrintWriter;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Demian
 */
@WebService(serviceName = "PaymentsHandlerServices")
public class PaymentsHandlerServices {
   private org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(PaymentsHandlerServices.class);
    private Gson gson = new Gson();

    @WebMethod(operationName = "createPay")
    public String createPay(@WebParam(name = "strRequest") String strRequest) {
        log.info("APCBPaymentsHandrerServices -> createPay ini");
        Request request = new Request(strRequest); 
        Response response;
        try {
            APCBPaymentsHandrerProcess process = new APCBPaymentsHandrerProcess();
            response = process.createPay(request);
            if (response.getMessage().getMsgCode().equals("200")){
                try {
                    PrintWriter writer = new PrintWriter("voucher.html", "UTF-8");
                    writer.println(response.getPayMainInfo().getVoucher());
                } catch(Exception e) {
                    log.error("Cant write Voucher File");
                    throw new Exception();
                }
            }
        } catch (Exception e) {
            response = new Response(request.getSesionId());
            response.setMessage(new Message(MessagesTypeEnum.Error_AplicationErrorNotHandler));
            log.error(response.getMessage().getMsgDesc(), e);
        }
        log.info("APCBPaymentsHandrerServices -> createPay end");
        return gson.toJson(response);
    }
    
    @WebMethod(operationName = "completePay")
    public String completePay(@WebParam(name = "strRequest") String strRequest) {
        log.info("APCBPaymentsHandrerServices -> completePay ini");
        Request request = new Request(strRequest); 
        Response response;
        try {
            APCBPaymentsHandrerProcess process = new APCBPaymentsHandrerProcess();
            response = process.completePay(request);
            if (response.getMessage().getMsgCode().equals("200")){
                try {
                    PrintWriter writer = new PrintWriter("voucher2.html", "UTF-8");
                    writer.println(response.getPayMainInfo().getVoucher());
                } catch(Exception e) {
                    log.error("Cant write Voucher File");
                    throw new Exception();
                }
            }
        } catch (Exception e) {
            response = new Response(request.getSesionId());
            response.setMessage(new Message(MessagesTypeEnum.Error_AplicationErrorNotHandler));
            log.error(response.getMessage().getMsgDesc(), e);
        }
        log.info("APCBPaymentsHandrerServices -> completePay end");
        return gson.toJson(response);
    }
    
    @WebMethod(operationName = "consultPay")
    public String consultPay(@WebParam(name = "strRequest") String strRequest) {
        log.info("APCBPaymentsHandrerServices -> consultPay ini");
        Request request = new Request(strRequest); 
        Response response;
        try {
            APCBPaymentsHandrerProcess process = new APCBPaymentsHandrerProcess();
            response = process.consultPay(request);
        } catch (Exception e) {
            response = new Response(request.getSesionId());
            response.setMessage(new Message(MessagesTypeEnum.Error_AplicationErrorNotHandler));
            log.error(response.getMessage().getMsgDesc(), e);
        }
        log.info("APCBPaymentsHandrerServices -> consultPay end");
        return gson.toJson(response);
    }
    
    @WebMethod(operationName = "annularPay")
    public String annularPay(@WebParam(name = "strRequest") String strRequest) {
        log.info("APCBPaymentsHandrerServices -> annularPay ini");
        Request request = new Request(strRequest); 
        Response response;
        try { 
            APCBPaymentsHandrerProcess process = new APCBPaymentsHandrerProcess();
            response = process.annularPay(request);
        } catch (Exception e) {
            response = new Response(request.getSesionId());
            response.setMessage(new Message(MessagesTypeEnum.Error_AplicationErrorNotHandler));
            log.error(response.getMessage().getMsgDesc(), e);
        }
        log.info("APCBPaymentsHandrerServices -> annularPay end");
        return gson.toJson(response);
    }
}
