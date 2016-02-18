/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.apcb.paymentsHandler.enums;

/**
 *
 * @author Demian
 */
public enum ActionMethodEnum {
    
    Payment         ("payment"),
    Complete        ("complete"), 
    Know            ("Know");
    
    private final String description; 
 
    ActionMethodEnum (String description) { 
        this.description = description;
    }

    public String getDescription() { return description; }
    
    /*public static String getDescriptionByCode(String code){
        for (RequestMethodEnum mealCodeEnum: RequestMethodEnum.values()){
            if (mealCodeEnum.getCode().equalsIgnoreCase(code)){
                return mealCodeEnum.getDescription();
            } 
        }
        return RequestMethodEnum.Know.getDescription();
    }*/
    
}
