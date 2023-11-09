/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Base64;

public class CookieUtils {

    public static String encode(String value) {
        byte[] bytes = value.getBytes();
        return Base64.getEncoder().encodeToString(bytes);
    }

    public static String decode(String encodedValue) {
        byte[] bytes = Base64.getDecoder().decode(encodedValue);
        return new String(bytes);
    }
}
