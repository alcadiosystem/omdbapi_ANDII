/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author SUPER
 */
public class Conexion {

    private static Connection conectar = null;

    /**
     * Metodo que establece una conexion con una base de datos que le pase por
     * parametros
     *
     * @param servidor Direccion ip donde se encuentra la base de datos
     * @param DB Nombre de la base de datos
     * @param usuario Usuario del motor de la base de datos
     * @param password Contraseña del motor de base de datos
     */
    public static void getInstancia(String servidor, String DB, String usuario, String password) {
        if (conectar == null) {
            try {

                Class.forName("com.mysql.jdbc.Driver").getInterfaces();

                String URL = "jdbc:mysql://" + servidor + "/" + DB;

                conectar = DriverManager.getConnection(URL, usuario, password);

                conectar.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

                System.out.println("+--------------------------------------------------+");
                System.out.println("|           Base de datos conectada.               |");
                System.out.println("+--------------------------------------------------+");
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println("+--------------------------------------------------+");
                System.out.println("|  Error al tratar de conectar la base de datos.   |");
                System.out.println(e.getMessage());
                System.out.println("+--------------------------------------------------+");

            }
        }
    }

    /**
     * Obtiene una conexion
     *
     * @return conectar
     */
    public static Connection getConexion() {
        return conectar;
    }

}
