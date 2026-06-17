package com.provinciasverdes.persistencia;

import com.provinciasverdes.modelo.Ubicacion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UbicacionDAO {

    public boolean crear(Ubicacion ubicacion) {
        String sql = "INSERT INTO ubicaciones (id_usuario, provincia, direccion, latitud, longitud) VALUES (?, ?, ?, ?, ?)";
        Connection conn = ConexionDB.obtenerConexion();
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, ubicacion.getIdUsuario());
            pst.setString(2, ubicacion.getProvincia());
            pst.setString(3, ubicacion.getDireccion());
            pst.setDouble(4, ubicacion.getLatitud());
            pst.setDouble(5, ubicacion.getLongitud());
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error crear ubicación: " + e.getMessage());
            return false;
        } finally {
            ConexionDB.cerrarConexion(conn);
        }
    }

    public List<Ubicacion> listarPorUsuario(int idUsuario) {
        List<Ubicacion> lista = new ArrayList<>();
        String sql = "SELECT * FROM ubicaciones WHERE id_usuario = ?";
        Connection conn = ConexionDB.obtenerConexion();
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, idUsuario);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                lista.add(new Ubicacion(
                    rs.getInt("id"),
                    rs.getInt("id_usuario"),
                    rs.getString("provincia"),
                    rs.getString("direccion"),
                    rs.getDouble("latitud"),
                    rs.getDouble("longitud")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error listar ubicaciones: " + e.getMessage());
        } finally {
            ConexionDB.cerrarConexion(conn);
        }
        return lista;
    }
}
