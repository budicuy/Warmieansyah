/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Config {

   
    private static final String URL = "jdbc:mysql://localhost:3306/db_tugas_pbo";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static void writeLog(String message) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timestamp = LocalDateTime.now().format(formatter);

        
        try (FileWriter fwlog = new FileWriter("log.txt", true);
                PrintWriter pwlog = new PrintWriter(fwlog)) {
            pwlog.println(timestamp + " - " + message);
        } catch (IOException e) {
            System.out.println("Gagal menulis log " + e.getMessage());
        }
    }

    
    public static Connection configDB() {
        Connection mySQLConfig = null;
        try {
            mySQLConfig = DriverManager.getConnection(URL, USER, PASSWORD);
            if (mySQLConfig.isValid(2)) {
                writeLog("Berhasil membuat koneksi ke database");
            }
        } catch (SQLException e) {
            writeLog("Gagal membuat koneksi ke database " + e.getMessage());
        }
        return mySQLConfig;
    }

}
