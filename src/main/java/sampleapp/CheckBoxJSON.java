/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sampleapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author BKJVZ
 */
public class CheckBoxJSON {

    private List<String> value;

    CheckBoxJSON(String sValue, boolean bValue) {
        if(bValue){
        setValue(sValue);
        }
    }

    public void setValue(String sValue) {
        this.value = new ArrayList<>();
        this.value.add(sValue);
    }

    public boolean getValue() {
        return this.value!= null && this.value.size() > 0;
    }

    public String getName() {
        return this.value.get(0);
    }
}
