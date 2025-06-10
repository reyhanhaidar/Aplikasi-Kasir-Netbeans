/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kasir;
import java.awt.GraphicsEnvironment;

import kasir.login;
/**
 *
 * @author WALIDHANIFATAULLAH
 */
public class Kasir {

    /**
     * @param args the command line arguments
     */
     public static void main(String[] args) {
        if (!GraphicsEnvironment.isHeadless()) {
            // GUI mode: show the login window
            new login().setVisible(true);
        } else {
            // Headless mode: no GUI, maybe print a message or run CLI alternative
            System.out.println("Running in headless mode: GUI not available.");
            // You can add CLI logic here if you want
        }
    }
    
}
