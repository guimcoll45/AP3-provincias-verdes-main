package com.provinciasverdes.persistencia;

import com.provinciasverdes.modelo.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    public boolean crear(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nombre, correo, contrasena, perfil) VALUES (?, ?, ?, ?)";
        Connection conn = ConexionDB.obtenerConexion();
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, usuario.getNombre());
            pst.setString(2, usuario.getCorreo());
            pst.setString(3, usuario.getContrasena());
            pst.setString(4, usuario.getPerfil());
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error crear usuario: " + e.getMessage());
            return false;
        } finally {
            ConexionDB.cerrarConexion(conn);
        }
    }

    public Usuario buscarPorCorreoYClave(String correo, String clave) {
        String sql = "SELECT * FROM usuarios WHERE correo = ? AND contrasena = ?";
        Connection conn = ConexionDB.obtenerConexion();
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, correo);
            pst.setString(2, clave);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return new Usuario(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("correo"),
                    rs.getString("contrasena"),
                    rs.getString("perfil")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error login: " + e.getMessage());
        } finally {
            ConexionDB.cerrarConexion(conn);
        }
        return null;
    }

    public List<Usuario> listarTodos() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
        Connection conn = ConexionDB.obtenerConexion();
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Usuario(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("correo"),
                    rs.getString("contrasena"),
                    rs.getString("perfil")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error listar usuarios: " + e.getMessage());
        } finally {
            ConexionDB.cerrarConexion(conn);
        }
        return lista;
    }
}
