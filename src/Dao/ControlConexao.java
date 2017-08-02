/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Control.Main;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.derby.drda.NetworkServerControl;

/**
 *
 * @author leonardo
 */
public class ControlConexao {
    
    private String dbURL = "jdbc:derby://localhost:1527/3e;create=true;user=root;password=root";
    private Connection conn = null;
    
    public ControlConexao() {
        try {
            NetworkServerControl server = new NetworkServerControl(InetAddress.getByName("localhost"),1527);
            server.start(null);
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            conn = DriverManager.getConnection(dbURL);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Falha na conexão com o banco de dados, abra o programa novamente");
            System.exit(0);
            //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getConn() {
        return conn;
    }
}
