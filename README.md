# Sistema de Gestión de Tienda en Línea "TechMarket"

## ¿De qué trata este proyecto?

Este proyecto consiste en un programa de consola desarrollado en Java que implementa una **lista dinámica desde cero** para gestionar el catálogo de productos de una tienda en línea llamada **TechMarket**.

La idea surgió de la necesidad de **comprender a fondo** cómo funcionan las listas dinámicas basadas en arreglos, construyendo una desde cero sin usar las estructuras que ya trae Java. En lugar de usar `ArrayList` de la biblioteca estándar, aquí se construyó todo manualmente: el arreglo que se expande automáticamente, los métodos de agregar, eliminar y obtener, y cada una de las operaciones del ejercicio. El objetivo es entender cómo funciona una lista dinámica por dentro y cómo se gestiona la memoria cuando el arreglo se llena.

---

## ¿Para qué sirve?

Este programa permite gestionar un catálogo de productos informáticos de forma interactiva y con él se puede:

- **Agregar productos** con sus datos: ID, nombre, marca y precio.
- **Mostrar todos los productos** del catálogo.
- **Filtrar productos por rango de precio**, mostrando los nombres de los que estén dentro del rango.
- **Aplicar descuentos por marca**, reduciendo el precio de todos los productos de una marca específica.
- **Buscar el producto más barato** del catálogo.
- **Reponer stock de un producto**, actualizando su precio.
- **Calcular el promedio de precio** de todos los productos de una marca.
- **Salir del programa** cuando se termine.

---

## ¿Cómo está organizado el proyecto?

El proyecto está dividido en varias clases, cada una con una responsabilidad específica. A continuación explico qué hace cada una y cómo se relacionan entre sí.

### Interfaz `List<E>`

Define el contrato que debe cumplir cualquier implementación de lista. Especifica los métodos que una lista debe tener: agregar, eliminar, obtener, consultar tamaño, vaciar y verificar si está vacía. Gracias a esta interfaz, la clase `ArrayList` puede ser reemplazada por otra implementación sin que el resto del código se vea afectado.

### Clase `ArrayList<E>`

Esta es una implementación propia de una lista dinámica basada en un arreglo que se expande automáticamente. En lugar de usar `ArrayList` de Java, aquí se construyó desde cero para comprender mejor cómo funciona este tipo de estructura.

Internamente tiene un arreglo de objetos (`items`), un contador de tamaño (`size`) y una capacidad (`capacity`). Los métodos que ofrece son:

- **`add(E e)`**: agrega un elemento al final de la lista, expandiendo la capacidad si es necesario.
- **`add(E e, int index)`**: agrega un elemento en una posición específica, desplazando los elementos siguientes.
- **`remove(int index)`**: elimina el elemento en la posición indicada y lo devuelve.
- **`get(int index)`**: devuelve el elemento en la posición indicada.
- **`size()`**: devuelve el número de elementos.
- **`clear()`**: vacía la lista, reiniciando la capacidad a 10.
- **`isEmpty()`**: indica si la lista está vacía.
- **`Expand()`**: método protegido que aumenta la capacidad del arreglo en 10 cuando se llena.
- **`isFull()`**: método protegido que verifica si el arreglo está lleno.

Esta clase es la que da soporte a todas las operaciones del ejercicio. Sin ella, no se podrían guardar ni manipular los productos.

### Clase `Producto`

Esta clase representa a un producto dentro del sistema. Cada producto tiene cuatro atributos: **ID**, **nombre**, **marca** y **precio**.

La clase tiene métodos para obtener cada uno de estos datos (`getId`, `getNombre`, `getMarca`, `getPrecio`) y un método para actualizar el precio (`setPrecio`). También tiene un `toString()` que muestra la información del producto de forma legible.

### Clase `GestorTechMarket`

Esta es la clase principal del sistema, la que contiene toda la lógica de negocio. Dentro de ella se guarda un catálogo de productos, que no es más que una lista dinámica (implementada en otra clase) que almacena objetos de tipo `Producto`.

Aquí se encuentran los métodos que el usuario puede usar:

- **`agregar(Producto p)`**: agrega un producto a la lista.
- **`pedirAgregar(Scanner in)`**: pide los datos de un producto al usuario y los valida antes de agregarlo.
- **`filtrarPorRangoPrecio(double precioMin, double precioMax)`**: muestra los nombres de los productos cuyo precio esté dentro del rango.
- **`aplicarDescuentoMarca(String marca, double porcentaje)`**: reduce el precio de todos los productos de una marca y devuelve la cantidad modificada.
- **`buscarProductoBarato()`**: muestra el producto con el precio más bajo.
- **`reponerStockProducto(String id, double nuevoPrecio)`**: actualiza el precio de un producto dado su ID.
- **`promedioPrecioPorMarca(String marca)`**: calcula el promedio de precios de todos los productos de una marca.
- **`mostrarProductos()`**: muestra todos los productos del catálogo.
- **`buscarPorId(String id)`**: método auxiliar que busca un producto por su ID.
- **`validarOpcion`, `validarNumero`, `validarNombre`, `validarId`, `validarPrecio`**: métodos auxiliares para validar los datos ingresados por el usuario.

### Clase `Main`

Es el punto de entrada del programa. Muestra un menú con las operaciones disponibles y utiliza un `Scanner` para leer las opciones del usuario. Cada opción llama a un método de `GestorTechMarket` y muestra el resultado correspondiente.

También incluye una opción para ejecutar **casos de prueba** (positivos y negativos) que verifican el funcionamiento de los métodos.

---

## ¿Cómo se relacionan todas las clases?

La relación entre las clases es bastante clara:

- `Producto` es el objeto que se va a gestionar.
- `ArrayList` es la estructura de datos que almacena los productos.
- `List` define el contrato que sigue `ArrayList`.
- `GestorTechMarket` utiliza un `ArrayList` para guardar los productos y ofrece métodos para interactuar con ellos.
- `Main` utiliza `GestorTechMarket` para probar todas las funcionalidades.

En resumen, `Main` llama a `GestorTechMarket`, que a su vez usa `ArrayList`, que implementa `List`. Y todo esto gira en torno a los objetos `Producto`.

---

## Estructura del proyecto

```
TechMarket/
├── src
│   ├── Main.java
│   ├── GestorTechMarket.java
│   ├── Producto.java
│   ├── ArrayList.java
│   └── List.java
├── README.md
└── TechMarket_Presentación
```

---

## ¿Cómo ejecutar el programa?

1. Descarga o clona el repositorio.
2. Abre el proyecto en tu IDE favorito (Visual Studio, Eclipse, IntelliJ, NetBeans, etc.).
3. Compila todas las clases.
4. Ejecuta la clase `Main`.
5. Sigue las instrucciones del menú para probar las operaciones.

---

## Casos de prueba incluidos

El programa permite probar todas las operaciones desde el menú. A continuación se describen los casos que se pueden probar:

### Casos positivos
- **Agregar productos** con datos válidos.
- **Mostrar todos los productos** del catálogo.
- **Filtrar productos por rango de precio**.
- **Aplicar descuento por marca**.
- **Buscar el producto más barato**.
- **Reponer stock de un producto** actualizando su precio.
- **Calcular el promedio de precio por marca**.

### Casos negativos
- **Sin productos registrados**: todos los métodos avisan que la lista está vacía.
- **Marca sin productos**: `aplicarDescuentoMarca` y `promedioPrecioPorMarca` avisan que no hay productos de esa marca.
- **ID no encontrado**: `reponerStockProducto` avisa que no existe un producto con ese ID.
- **Rango de precios inválido (min > max)**: `filtrarPorRangoPrecio` avisa del error.
- **Porcentaje de descuento inválido (mayor a 100)**: `aplicarDescuentoMarca` avisa del error.
- **Porcentaje de descuento inválido (negativo)**: `aplicarDescuentoMarca` avisa del error.
- **Nuevo precio inválido (negativo)**: `reponerStockProducto` avisa del error.
- **Rango sin resultados**: `filtrarPorRangoPrecio` avisa que no hay productos en ese rango.

---

## Declaración de uso de Inteligencia Artificial

En este proyecto se utilizó Inteligencia Artificial (Claude) como herramienta de apoyo en las siguientes partes:

- **Generación de casos de prueba**: se utilizó la IA para proponer casos de prueba positivos, negativos y de límite que permitieran verificar el correcto funcionamiento de los métodos implementados.

- **Revisión del código**: se utilizó la IA para revisar el código en busca de posibles errores, malas prácticas o mejoras en la legibilidad y estructura del mismo.

- **Diseño de la presentación**: se utilizó la IA para apoyar en la organización del contenido de la presentación oral y en la estructura de las diapositivas.
