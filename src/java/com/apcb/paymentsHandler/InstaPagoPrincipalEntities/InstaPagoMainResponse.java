/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.apcb.paymentsHandler.InstaPagoPrincipalEntities;

/**
 *
 * @author Demian
 */
public class InstaPagoMainResponse {
    private Boolean success;// Indica si fue procesado el pago (true o false),
    private String message; // Descripción de la respuesta (Máx. 200 caracteres),
    private String id;// Código del pago (Máx. 32 caracteres),
    private Integer code;// Código del respuesta del pago (Máx. 3 caracteres),
    private Integer reference; // Número de referencia del pago (Máx. 6 caracteres),
    private String voucher;// HTML que contiene el recibo del pago que debe ser mostrado en la confirmación del pago al cliente,
    private Integer ordernumber;// Número de orden indicado por el comercio,
    private Integer sequence;// Número de identificación bancario universal (Máx. 12 caracteres),
    private Integer approval;// Número de aprobación bancaria (Máx. 6 caracteres) }

    public Boolean isSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getReference() {
        return reference;
    }

    public void setReference(Integer reference) {
        this.reference = reference;
    }

    public String getVoucher() {
        return voucher;
    }

    public void setVoucher(String voucher) {
        this.voucher = voucher;
    }

    public Integer getOrdernumber() {
        return ordernumber;
    }

    public void setOrdernumber(Integer ordernumber) {
        this.ordernumber = ordernumber;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public Integer getApproval() {
        return approval;
    }

    public void setApproval(Integer approval) {
        this.approval = approval;
    }
}
