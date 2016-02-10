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
public enum StatusIdEnum {
    
    Retener  ("1","Retener"), //(pre-autorización)
    Pagar  ("2","Pagar"), //(autorización).
    Know ("0","Know");
    
    private final String code; 
    private final String description; 
 
    StatusIdEnum (String code, String description) { 
        this.code = code;
        this.description = description;
    }
    
    public String getCode() { return code; }
    public String getDescription() { return description; }
    
    public static String getDescriptionByCode(String code){
        for (StatusIdEnum mealCodeEnum: StatusIdEnum.values()){
            if (mealCodeEnum.getCode().equalsIgnoreCase(code)){
                return mealCodeEnum.getDescription();
            } 
        }
        return StatusIdEnum.Know.getDescription();
    }
    
}
