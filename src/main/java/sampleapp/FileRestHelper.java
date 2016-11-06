/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sampleapp;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author BKJVZ
 */
public class FileRestHelper {

    static String toId(String path) {
        return path.replaceAll("\\\\", "/")
                .replaceAll(":", "_" + (int) ':' + "_")
                .replaceAll("/", "_" + (int) '/' + "_")
                .replaceAll(" ", "_" + (int) ' ' + "_")
                .replaceAll("\\.", "_" + (int) '.' + "_");
    }

    static String toPath(String id) {
        return id.replaceAll("_" + (int) ':' + "_", ":")
                .replaceAll("_" + (int) '/' + "_", "/")
                .replaceAll("_" + (int) ' ' + "_", " ")
                .replaceAll("_" + (int) '.' + "_", ".");
    }

}
