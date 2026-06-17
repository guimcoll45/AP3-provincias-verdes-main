package com.provinciasverdes.persistencia;

import com.provinciasverdes.modelo.RegistroEnergia;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RegistroEnergiaDAO {

    public boolean crear(RegistroEnergia registro) {
        String sql = "INSERT INTO registros_energia (id_equipo, fecha_hora, voltaje, corriente, energia_generada, energia_consumida) VALUES (?, ?, ?, ?, ?, ?)";
        Connection conn = ConexionDB.obtenerConexion();
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, registro.getIdEquipo());
            pst.setTimestamp(2, new Timestamp(registro.getFechaHora().getTime()));
            pst.setDouble(3, registro.getVoltaje());
            pst.setDouble(4, registro.getCorriente());
            pst.setDouble(5, registro.getEnergiaGenerada());
            pst.setDouble(6, registro.getEnergiaConsumida());
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error crear registro: " + e.getMessage());
            return false;
        } finally {
            ConexionDB.cerrarConexion(conn);
        }
    }

    public List<RegistroEnergia> listarPorEquipo(int idEquipo) {
        List<RegistroEnergia> lista = new ArrayList<>();
        String sql = "SELECT * FROM registros_energia WHERE id_equipo = ? ORDER BY fecha_hora DESC";
        Connection conn = ConexionDB.obtenerConexion();
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, idEquipo);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                lista.add(new RegistroEnergia(
                    rs.getInt("id"),
                    rs.getInt("id_equipo"),
                    new Date(rs.getTimestamp("fecha_hora").getTime()),
                    rs.getDouble("voltaje"),
                    rs.getDouble("corriente"),
                    rs.getDouble("energia_generada"),
                    rs.getDouble("energia_consumida")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error listar registros: " + e.getMessage());
        } finally {
            ConexionDB.cerrarConexion(conn);
        }
        return lista;
    }
}
