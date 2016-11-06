/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sampleapp;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author janva_000
 */
public class FileRestHelperTest {
    
    public FileRestHelperTest() {
    }
    
    /**
     * Test of toId method, of class FileRestHelper.
     */
    @Test
    public void testToId() {
        System.out.println("toId");
        String path = "c:/Dojo Workshop\\test.txt";
        String expResult = "c_58__47_Dojo_32_Workshop_47_test_46_txt";
        String result = FileRestHelper.toId(path);
        assertEquals(expResult, result);
    }

    /**
     * Test of toPath method, of class FileRestHelper.
     */
    @Test
    public void testToPath() {
        System.out.println("toPath");
        String id = "c_58__47_Dojo_32_Workshop_47_test_46_txt";
        String expResult = "c:/Dojo Workshop/test.txt";
        String result = FileRestHelper.toPath(id);
        assertEquals(expResult, result);
    }
    
}
