/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.apcb.paymentsHandler.InstaPagoPrincipalEntities;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Demian
 */
public class InstaPagoMainRequest {
    private String KeyId;// (Requerido): Llave generada desde InstaPago.
    private String PublicKeyId; // (Requerido): Llave compartida (Enviada por correo electrónico al crear la cuenta en el portal de InstaPago)
    private Double Amount;// (Requerido): Monto a Debitar, utilizando punto “.” como separador decimal. Por ejemplo: 200.00.
    private String Description;// (Requerido): Cadena de caracteres con la descripción de la operación.
    private String CardHolder;// (Requerido): Nombre del Tarjeta habiente.
    private Integer CardHolderID;// (Requerido): Cédula del Tarjeta habiente.
    private long CardNumber;// (Requerido): Numero de la tarjeta de crédito, sin espacios ni separadores.
    private Integer CVC;// (Requerido): Código secreto de la Tarjeta de crédito.
    private String ExpirationDate;// (Requerido): Fecha de expiración de la tarjeta en el formato mostrado en la misma MM/YYYY. Por Ejemplo: 10/2014.
    private StatusIdEnum StatusId;// (Requerido): Estatus en el que se creará la transacción.
    private String IP; // (Requerido): Dirección IP del cliente que genera la solicitud del pago.
    
    private Integer OrderNumber; // (Opcional): Numero de orden del pago según el comercio.
    private String Address; // (Opcional): Dirección asociada a la tarjeta, Utilizada por algunos bancos para mayor seguridad.
    private String City; // (Opcional): Ciudad asociada a la tarjeta, Utilizada por algunos bancos para mayor seguridad.
    private String ZipCode; // (Opcional): Código Postal asociada a la tarjeta, Utilizada por algunos bancos para mayor seguridad.
    private String State; // (Opcional): Estado o provincia asociada a la tarjeta, Utilizada por algunos bancos para mayor seguridad.

    public String getKeyId() {
        return KeyId;
    }

    public void setKeyId(String KeyId) {
        this.KeyId = KeyId;
    }

    public String getPublicKeyId() {
        return PublicKeyId;
    }

    public void setPublicKeyId(String PublicKeyId) {
        this.PublicKeyId = PublicKeyId;
    }

    public Double getAmount() {
        return Amount;
    }

    public void setAmount(Double Amount) {
        this.Amount = Amount;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getCardHolder() {
        return CardHolder;
    }

    public void setCardHolder(String CardHolder) {
        this.CardHolder = CardHolder;
    }

    public Integer getCardHolderID() {
        return CardHolderID;
    }

    public void setCardHolderID(Integer CardHolderID) {
        this.CardHolderID = CardHolderID;
    }

    public long getCardNumber() {
        return CardNumber;
    }

    public void setCardNumber(long CardNumber) {
        this.CardNumber = CardNumber;
    }

    public Integer getCVC() {
        return CVC;
    }

    public void setCVC(Integer CVC) {
        this.CVC = CVC;
    }

    public String getExpirationDate() {
        return ExpirationDate;
    }

    public void setExpirationDate(String ExpirationDate) {
        this.ExpirationDate = ExpirationDate;
    }

    public StatusIdEnum getStatusId() {
        return StatusId;
    }

    public void setStatusId(StatusIdEnum StatusId) {
        this.StatusId = StatusId;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public Integer getOrderNumber() {
        return OrderNumber;
    }

    public void setOrderNumber(Integer OrderNumber) {
        this.OrderNumber = OrderNumber;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public String getZipCode() {
        return ZipCode;
    }

    public void setZipCode(String ZipCode) {
        this.ZipCode = ZipCode;
    }

    public String getState() {
        return State;
    }

    public void setState(String State) {
        this.State = State;
    }
    
    public String toString(){
        StringBuilder retBuilder = new StringBuilder("");
        //InstaPagoMainRequest.class.
                
        Field[] fields = InstaPagoMainRequest.class.getDeclaredFields();
        //System.out.printf("%d fields:%n", fields.length);
        for (Field field : fields) {
             try {
                 if (field.get(this)!=null){
                    field.setAccessible(true);
                    retBuilder.append(field.getName());
                    retBuilder.append("=");
                    
                    if (field.getType()==String.class){
                        retBuilder.append(((String)field.get(this)).replace(" ", "+")); 
                    } else if (field.getType()==StatusIdEnum.class){
                         retBuilder.append(((StatusIdEnum)field.get(this)).getCode()); 
                    } else {
                         retBuilder.append(field.get(this)); 
                    }
                    retBuilder.append("&");
                 }
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                Logger.getLogger(InstaPagoMainRequest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return retBuilder.toString();
    }
    
}
