public class Producto {
    private String id;
    private String nombre;
    private String marca;
    private double precio;
    
    public Producto(String id, String nombre, String marca, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.marca = marca;
        this.precio = precio;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getMarca() {
        return marca;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return id + " - " + nombre + " - " + marca + " - " + precio + "$";
    }
}
