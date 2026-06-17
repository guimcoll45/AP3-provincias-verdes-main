# 🌱 Provincias Verdes
Sistema de gestión y monitoreo de energía solar desarrollado en Java con MySQL.
Trabajo Práctico de Programación Orientada a Objetos.

## 🛠️ Tecnologías
- Java 11+
- MySQL
- JDBC

## 📋 Requisitos
1. Tener JDK instalado.
2. Tener MySQL instalado y corriendo.
3. Archivo `mysql-connector-java-8.0.xx.jar` en la carpeta `lib/`.

## ⚙️ Configuración
1. Ejecutar el script `sql/provincias_verdes.sql` en tu gestor de base de datos.
2. Modificar usuario y contraseña en `src/com/provinciasverdes/persistencia/ConexionDB.java`.

## 🚀 Ejecución
1. Compilar:
```bash
javac -cp "lib/*" -d bin src/com/provinciasverdes/*/*.java src/com/provinciasverdes/Main.java
