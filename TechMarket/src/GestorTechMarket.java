import java.util.Scanner;

public class GestorTechMarket {
    private ArrayList<Producto> productos;

    public GestorTechMarket() {
        this.productos = new ArrayList<>();
    }

    public void agregar(Producto p) {
        productos.add(p);
    }

    public void pedirAgregar(Scanner in) {
        String id = validarId(in);
        String nombre = validarNombre(in, "nombre");
        String marca = validarNombre(in, "marca");
        double precio = validarPrecio(in);

        productos.add(new Producto(id, nombre, marca, precio));
        System.out.println("El producto ha sido agregado correctamente");
    }

    public String validarNombre(Scanner in, String campo) {
        String valor;
        while (true) {
            System.out.print("Ingrese el " + campo + " del producto: ");
            valor = in.nextLine();
            if (valor == null || valor.trim().isEmpty()) {
                System.out.println("ERROR: El " + campo + " no puede estar vacío");
            } else {
                return valor;
            }
        }
    }

    public String validarId(Scanner in) {
        String id;
        while (true) {
            System.out.print("Ingrese el ID del producto: ");
            id = in.nextLine();
            if (id == null || id.trim().isEmpty()) {
                System.out.println("ERROR: El ID no puede estar vacío");
            } else if (buscarPorId(id) != null) {
                System.out.println("ERROR: Ya existe un producto con ese ID");
            } else {
                return id;
            }
        }
    }

    public double validarPrecio(Scanner in) {
        double precio;
        while (true) {
            System.out.print("Ingrese el precio del producto: ");
            if (in.hasNextDouble()) {
                precio = in.nextDouble();
                in.nextLine();
                if (precio > 0) {
                    return precio;
                }
                System.out.println("ERROR: El precio debe ser mayor a 0");
            } else {
                System.out.println("ERROR: Precio inválido");
                in.next();
            }
        }
    } 

    public int validarOpcion(Scanner in, int min, int max) {
        int op;
        while (true) {
            if (in.hasNextInt()) {
                op = in.nextInt();
                in.nextLine();
                if (op >= min && op <= max) {
                    return op;
                }
                System.out.println("ERROR: El número debe estar entre " + min + " y " + max);
            } else {
                System.out.print("ERROR: Ingrese un número válido: ");
                in.next();
            }
        }
    }

    public double validarNumero(Scanner in, String campo, double min) {
        double valor;
        while (true) {
            System.out.print("Ingrese el " + campo + ": ");
            if (in.hasNextDouble()) {
                valor = in.nextDouble();
                in.nextLine();
                if (valor >= min) {
                    return valor;
                }
                System.out.println("ERROR: El " + campo + " debe ser mayor o igual a " + min);
            } else {
                System.out.println("ERROR: Número inválido");
                in.next();
            }
        }    
    }

    public Producto buscarPorId(String id) {
        if (id == null) return null;
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getId().equals(id)) {
                return productos.get(i);
            }
        }
        return null;
    }

    public void filtrarPorRangoPrecio(double precioMin, double precioMax) {
        if (productos.isEmpty()) {
            System.out.println("ERROR: No hay productos registrados");
            return;
        }
        if (precioMin > precioMax) {
            System.out.println("ERROR: El precio mínimo no puede ser mayor que el máximo");
            return;
        }

        ArrayList<String> filtrados = new ArrayList<>();
        for (int i = 0; i < productos.size(); i++) {
            double precio = productos.get(i).getPrecio();
            if (precio >= precioMin && precio <= precioMax) {
                filtrados.add(productos.get(i).getNombre());
            }
        }

        if (filtrados.isEmpty()) {
            System.out.println("No hay productos en ese rango de precios");
            return;
        }

        for (int i = 0; i < filtrados.size(); i++) {
            System.out.println((i + 1) + ". " + filtrados.get(i));
        }
    }

    public void aplicarDescuentoMarca(String marca, double porcentaje) {
        if (productos.isEmpty()) {
            System.out.println("ERROR: No hay productos registrados");
            return;
        }
        if (porcentaje <= 0 || porcentaje >= 100) {
            System.out.println("ERROR: El porcentaje debe estar entre 0 y 100");
            return;
        }

        int pModif = 0;
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getMarca().equalsIgnoreCase(marca)) {
                double nuevoPrecio = productos.get(i).getPrecio() * (1 - porcentaje / 100);
                productos.get(i).setPrecio(nuevoPrecio);
                pModif++;
            }
        }

        if (pModif == 0) {
            System.out.println("No hay productos de la marca " + marca);
        } else {
            System.out.println("Productos modificados: " + pModif);
        }
    }

    public void buscarProductoBarato() {
        if (productos.isEmpty()) {
            System.out.println("ERROR: No hay productos registrados");
            return;
        }

        Producto barato = productos.get(0);
        for (int i = 1; i < productos.size(); i++) {
            if (productos.get(i).getPrecio() < barato.getPrecio()) {
                barato = productos.get(i);
            }
        }

        System.out.println("Producto más barato: " + barato);
    }

    public boolean reponerStockProducto(String id, double nuevoPrecio) {
        if (productos.isEmpty()) {
            System.out.println("ERROR: No hay productos registrados");
            return false;
        }
        if (nuevoPrecio <= 0) {
            System.out.println("ERROR: El precio debe ser mayor a 0");
            return false;
        }

        Producto p = buscarPorId(id);
        if (p == null) {
            System.out.println("ERROR: No existe un producto con ese ID");
            return false;
        }

        p.setPrecio(nuevoPrecio);
        System.out.println("Precio actualizado correctamente");
        return true;
    }

    public void promedioPrecioPorMarca(String marca) {
        if (productos.isEmpty()) {
            System.out.println("ERROR: No hay productos registrados");
            return;
        }

        double suma = 0;
        int cant = 0;
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getMarca().equalsIgnoreCase(marca)) {
                suma += productos.get(i).getPrecio();
                cant++;
            }
        }

        if (cant == 0) {
            System.out.println("Promedio: 0.0");
        } else {
            System.out.println("Promedio: " + (suma / cant));
        }
    }

    public void mostrarProductos() {
        if (productos.isEmpty()) {
            System.out.println("ERROR: No hay productos registrados");
            return;
        }
        for (int i = 0; i < productos.size(); i++) {
            System.out.println((i + 1) + ". " + productos.get(i));
        }
    }
}
