package config;

import java.sql.Connection;
import java.sql.DriverManager;

public class Koneksi {

    Connection conn;

    public Connection connect(){

        try{

            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/db_warnet_management",
                    "root",
                    "");

            System.out.println("Koneksi Berhasil");

        }catch(Exception e){

            System.out.println("Koneksi Gagal");
            System.out.println(e);

        }

        return conn;

    }

}