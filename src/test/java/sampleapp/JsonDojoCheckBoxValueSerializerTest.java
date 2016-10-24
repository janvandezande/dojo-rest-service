/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sampleapp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import java.util.Date;
import org.hamcrest.Matchers;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author BKJVZ
 */
public class JsonDojoCheckBoxValueSerializerTest {
    
    public JsonDojoCheckBoxValueSerializerTest() {
    }

    /**
     * Test of serialize method, of class JsonDojoCheckBoxValueSerializer.
     *
     * @throws com.fasterxml.jackson.core.JsonProcessingException
     */
    @Test
    public void testSerialize() throws
            JsonProcessingException {
        FilePropertiesJSON filePropertiesJSON = new FilePropertiesJSON("name", "Type", "location", BigDecimal.ONE, new Date(), new Date(), true, false, new Date(), "comment", "label", "user", BigDecimal.ONE);
        String result = new ObjectMapper().writeValueAsString(filePropertiesJSON);
        assertThat(result, Matchers.containsString("\"readOnly\":[\"readOnly\"],\"hidden\":[],"));
    }
    
}
