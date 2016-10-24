/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sampleapp;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;

/**
 *
 * @author BKJVZ
 */
class JsonDojoCheckBoxValueSerializer extends JsonSerializer<CheckBoxJSON> {

    @Override
    public void serialize(CheckBoxJSON checkBoxJSON, JsonGenerator jg, SerializerProvider sp) throws IOException, JsonProcessingException {
        if (checkBoxJSON.getValue()) {
            jg.writeStartArray();
            jg.writeString(checkBoxJSON.getName());
            jg.writeEndArray();
        }else{
            jg.writeStartArray();
            jg.writeEndArray();
        }
    }
}
