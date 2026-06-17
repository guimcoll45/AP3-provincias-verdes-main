package com.provinciasverdes.persistencia;

import com.provinciasverdes.modelo.EquipoSolar;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EquipoSolarDAO {

    public boolean crear(EquipoSolar equipo) {
        String sql = "INSERT INTO equipos_solares (id_ubicacion, tipo, potencia, modelo) VALUES (?, ?, ?, ?)";
        Connection conn = ConexionDB.obtenerConexion();
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, equipo.getIdUbicacion());
            pst.setString(2, equipo.getTipo());
            pst.setDouble(3, equipo.getPotencia());
            pst.setString(4, equipo.getModelo());
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error crear equipo: " + e.getMessage());
            return false;
        } finally {
            ConexionDB.cerrarConexion(conn);
        }
    }

    public List<EquipoSolar> listarPorUbicacion(int idUbicacion) {
        List<EquipoSolar> lista = new ArrayList<>();
        String sql = "SELECT * FROM equipos_solares WHERE id_ubicacion = ?";
        Connection conn = ConexionDB.obtenerConexion();
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, idUbicacion);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                lista.add(new EquipoSolar(
                    rs.getInt("id"),
                    rs.getInt("id_ubicacion"),
                    rs.getString("tipo"),
                    rs.getDouble("potencia"),
                    rs.getString("modelo")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error listar equipos: " + e.getMessage());
        } finally {
            ConexionDB.cerrarConexion(conn);
        }
        return lista;
    }
}
