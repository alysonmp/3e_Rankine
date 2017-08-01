/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import java.net.InetAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.derby.drda.NetworkServerControl;

/**
 *
 * @author alysonmp
 */
public class Main {
    
    private static String dbURL = "jdbc:derby://localhost:1527/3e;create=true;user=root;password=root";
    
    public static void main(String[] args){
        try {
            NetworkServerControl server = new NetworkServerControl(InetAddress.getByName("localhost"),1527);
            server.start(null);
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection conn = DriverManager.getConnection(dbURL);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ControlPrincipal principal = new ControlPrincipal();
    }
}
