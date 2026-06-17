package com.provinciasverdes.interfaz;

import com.provinciasverdes.modelo.Usuario;
import com.provinciasverdes.persistencia.UsuarioDAO;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuPrincipal {
    private final Scanner scanner = new Scanner(System.in);
    private Usuario usuarioActual;

    public void iniciar() {
        if (login()) {
            mostrarMenuOpciones();
        } else {
            System.out.println("❌ Acceso denegado. Revise sus credenciales.");
        }
    }

    private boolean login() {
        System.out.println("=====================================");
        System.out.println("   SISTEMA PROVINCIAS VERDES");
        System.out.println("=====================================");
        System.out.print("Ingrese su correo: ");
        String correo = scanner.nextLine().trim();
        System.out.print("Ingrese su contraseña: ");
        String clave = scanner.nextLine().trim();

        UsuarioDAO dao = new UsuarioDAO();
        usuarioActual = dao.buscarPorCorreoYClave(correo, clave);
        return usuarioActual != null;
    }

    private void mostrarMenuOpciones() {
        int opcion;
        do {
            System.out.println("\n=====================================");
            System.out.println("    🌱 PROVINCIAS VERDES - MENÚ");
            System.out.println("   Usuario: " + usuarioActual.getNombre() + " | Perfil: " + usuarioActual.getPerfil());
            System.out.println("=====================================");
            System.out.println("👉 1. Gestión de Usuarios");
            System.out.println("👉 2. Gestión de Ubicaciones");
            System.out.println("👉 3. Gestión de Equipos Solares");
            System.out.println("👉 4. Registro de Mediciones");
            System.out.println("👉 5. Consultas y Balance Energético");
            System.out.println("👉 6. Generación de Reportes");
            System.out.println("👉 7. Herramientas (Ordenar / Buscar)");
            System.out.println("👉 8. Salir");
            System.out.println("=====================================");
            System.out.print("Ingrese su opción: ");

            try {
                opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar buffer

                switch (opcion) {
                    case 1 -> System.out.println("🔧 Módulo de Usuarios en desarrollo...");
                    case 2 -> System.out.println("🔧 Módulo de Ubicaciones en desarrollo...");
                    case 3 -> System.out.println("🔧 Módulo de Equipos en desarrollo...");
                    case 4 -> System.out.println("🔧 Cargar Mediciones en desarrollo...");
                    case 5 -> System.out.println("🔧 Calcular Balance en desarrollo...");
                    case 6 -> System.out.println("🔧 Generar Reportes en desarrollo...");
                    case 7 -> System.out.println("🔧 Ordenar y Buscar en desarrollo...");
                    case 8 -> System.out.println("👋 Saliendo del sistema...");
                    default -> System.out.println("❌ Opción inválida. Intente nuevamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("❌ ERROR: Debe ingresar un número entero.");
                scanner.next(); // Limpiar entrada errónea
                opcion = -1;
            }

        } while (opcion != 8);
    }
}
