import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        GestorTechMarket gestor = new GestorTechMarket();

        while (true) {
            System.out.println("\n---MENÚ---");
            System.out.println("1. Agregar producto");
            System.out.println("2. Mostrar todos los productos");
            System.out.println("3. Filtrar productos por rango de precio");
            System.out.println("4. Aplicar descuento por marca");
            System.out.println("5. Buscar producto más barato");
            System.out.println("6. Reponer stock de producto");
            System.out.println("7. Promedio de precio por marca");
            System.out.println("8. Ejecutar casos de prueba");
            System.out.println("9. Salir");
            System.out.print("Opción: ");

            int opcion = gestor.validarOpcion(in, 1, 9);

            if (opcion == 1) {
                gestor.pedirAgregar(in);

            } else if (opcion == 2) {
                gestor.mostrarProductos();

            } else if (opcion == 3) {
                double min = gestor.validarNumero(in, "precio mínimo", 0);
                double max = gestor.validarNumero(in, "precio máximo", 0);
                gestor.filtrarPorRangoPrecio(min, max);

            } else if (opcion == 4) {
                String marca = gestor.validarNombre(in, "marca");
                double porcentaje = gestor.validarNumero(in, "porcentaje de descuento", 0.01);

                if (porcentaje >= 100) {
                    System.out.println("ERROR: El porcentaje debe ser menor a 100");
                } else {
                    gestor.aplicarDescuentoMarca(marca, porcentaje);
                }

            } else if (opcion == 5) {
                gestor.buscarProductoBarato();

            } else if (opcion == 6) {
                String id;
                while (true) {
                    System.out.print("Ingrese el ID del producto: ");
                    id = in.nextLine();
                    if (id == null || id.trim().isEmpty()) {
                        System.out.println("ERROR: El ID no puede estar vacío");
                    } else {
                        break;
                    }
                }
                double nuevoPrecio = gestor.validarPrecio(in);
                gestor.reponerStockProducto(id, nuevoPrecio);

            } else if (opcion == 7) {
                String marca = gestor.validarNombre(in, "marca");
                gestor.promedioPrecioPorMarca(marca);

            } else if (opcion == 8) {
                System.out.println("\n---CASOS POSITIVOS---");
                GestorTechMarket g1 = new GestorTechMarket();
                g1.agregar(new Producto("P01", "Laptop Gaming", "ASUS", 1200.0));
                g1.agregar(new Producto("P02", "Mouse Inalámbrico", "Logitech", 25.0));
                g1.agregar(new Producto("P03", "Teclado Mecánico", "ASUS", 80.0));
                g1.agregar(new Producto("P04", "Monitor 24\"", "Samsung", 180.0));

                System.out.println("\n1. Mostrar todos los productos:");
                g1.mostrarProductos();

                System.out.println("\n2. Productos entre 50 y 200:");
                g1.filtrarPorRangoPrecio(50, 200);

                System.out.println("\n3. Aplicar 10% de descuento a ASUS:");
                g1.aplicarDescuentoMarca("ASUS", 10);
                g1.mostrarProductos();

                System.out.println("\n4. Producto más barato:");
                g1.buscarProductoBarato();

                System.out.println("\n5. Reponer stock del producto P02 con nuevo precio 30.0:");
                g1.reponerStockProducto("P02", 30.0);
                g1.mostrarProductos();

                System.out.println("\n6. Promedio de precio de ASUS:");
                g1.promedioPrecioPorMarca("ASUS");

                System.out.println("\n---CASOS NEGATIVOS---");
                GestorTechMarket g2 = new GestorTechMarket();

                System.out.println("\n1. Sin productos:");
                g2.mostrarProductos();
                g2.filtrarPorRangoPrecio(10, 100);
                g2.aplicarDescuentoMarca("ASUS", 10);
                g2.buscarProductoBarato();
                g2.reponerStockProducto("P01", 500.0);
                g2.promedioPrecioPorMarca("ASUS");

                System.out.println("\n2. Marca sin productos:");
                g2.agregar(new Producto("P01", "Laptop", "ASUS", 1200.0));
                g2.aplicarDescuentoMarca("Logitech", 10);
                g2.promedioPrecioPorMarca("Logitech");

                System.out.println("\n3. ID no encontrado:");
                g2.reponerStockProducto("P99", 500.0);

                System.out.println("\n4. Rango de precios inválido (min > max):");
                g2.filtrarPorRangoPrecio(500, 100);

                System.out.println("\n5. Porcentaje de descuento inválido (mayor a 100):");
                g2.aplicarDescuentoMarca("ASUS", 150);

                System.out.println("\n6. Porcentaje de descuento inválido (negativo):");
                g2.aplicarDescuentoMarca("ASUS", -10);

                System.out.println("\n7. Nuevo precio inválido (negativo):");
                g2.reponerStockProducto("P01", -500.0);

                System.out.println("\n8. Rango sin resultados:");
                g2.filtrarPorRangoPrecio(5000, 10000);

                System.out.println("\n--- FIN DE LOS CASOS DE PRUEBA ---");

            } else if (opcion == 9) {
                System.out.println("Ha salido del menú...");
                return;
            }
        }
    }
}
