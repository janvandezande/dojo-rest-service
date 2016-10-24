/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sampleapp;

/**
 *
 * @author BKJVZ
 */
public class FileRestHelper {

    static String toId(String path) {
        return path.replaceAll(":", "_58_").replaceAll("\\\\", "_92_");
    }

    static String toPath(String id) {
        return id.replaceAll("_58_", ":").replaceAll("_92_", "/");
    }
    
}
