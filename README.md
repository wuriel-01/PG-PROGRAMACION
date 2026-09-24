# Gestor de productos

Aplicación de escritorio Java Swing para cargar, buscar, editar y eliminar productos. El valor del stock se calcula como precio por cantidad.

## Requisitos

- JDK instalado (incluye `javac` y `java`).
- En VS Code, la extensión **Extension Pack for Java** para usar el botón **Run**.

## Ejecutar desde VS Code

Abrí `src/view/GestorProductos.java` y elegí **Run** sobre el método `main`.

## Compilar y ejecutar desde PowerShell

Desde la carpeta del proyecto:

```powershell
javac -encoding UTF-8 -d bin (Get-ChildItem -Recurse src -Filter *.java | ForEach-Object { $_.FullName })
java -cp bin view.GestorProductos
```

Los productos se mantienen en memoria mientras la aplicación está abierta; al cerrarla, se borran.
