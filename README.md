# CRUD de Productos, Categorias y Pedidos en Consola con Java

Proyecto realizado en Java como practica de Programacion Orientada a Objetos.

La aplicacion es un CRUD por consola que permite administrar productos, categorias y pedidos desde un menu interactivo. Fue desarrollado aplicando conceptos de Programacion Orientada a Objetos y usando estructuras basicas de Java.

## Funcionalidades

- Crear, listar, actualizar y eliminar productos.
- Crear, listar, actualizar y eliminar categorias.
- Crear, listar, actualizar y eliminar pedidos.
- Manejo de articulos y servicios como tipos de producto.
- Calculo de precio final mediante una interfaz.
- Calculo del total de cada pedido a partir de sus items.

## Conceptos aplicados

- **Encapsulamiento:** los atributos principales son privados y se accede a ellos mediante getters y setters.
- **Herencia:** `Articulo` y `Servicio` heredan de la clase abstracta `Producto`.
- **Polimorfismo:** los productos implementan su propia forma de calcular el precio final.
- **Abstraccion:** se usa la clase abstracta `Producto` y la clase abstracta generica `CrudConsola<T>`.
- **Interfaces:** la interfaz `Vendible` define el metodo `calcularPrecioFinal()`.
- **Generics:** `CrudConsola<T>` permite reutilizar la estructura del CRUD para distintas entidades.

## Como ejecutar

Desde un IDE, abrir el archivo `Main.java` y ejecutar el metodo `main`.

Tambien se puede ejecutar por consola. Para eso, hay que abrir una terminal en la carpeta raiz del proyecto y ejecutar estos comandos:

```bash
javac -d out src/crudpreentrega/*.java
java -cp out crudpreentrega.Main
```
