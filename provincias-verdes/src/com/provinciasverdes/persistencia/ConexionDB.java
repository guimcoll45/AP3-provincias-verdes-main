package com.provinciasverdes.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    // ⚠️ MODIFICA ESTOS DATOS CON LOS TUYOS
    private static final String URL = "jdbc:mysql://localhost:3306/provincias_verdes";
    private static final String USUARIO = "root";
    private static final String CLAVE = "tu_contraseña";

    public static Connection obtenerConexion() {
        Connection conexion = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
        } catch (ClassNotFoundException e) {
            System.out.println("❌ Error: Driver no encontrado -> " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("❌ Error de conexión -> " + e.getMessage());
        }
        return conexion;
    }

    public static void cerrarConexion(Connection conexion) {
        if (conexion != null) {
            try {
                conexion.close();
            } catch (SQLException e) {
                System.out.println("⚠️ Error al cerrar -> " + e.getMessage());
            }
        }
    }
}
