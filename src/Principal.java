import java.util.Scanner;

import conexion.ConexionApi;
import modelo.MonedaConstructor;
import record.Moneda;

public class Principal {

    public static void main(String[] args) throws Exception {
        System.out.println("************************************************");
        System.out.println("-----Bienvenidos a Nuestro Sitema de Cambio-----");
        System.out.println("************************************************");
        System.out.println("");

        int opcion;
        double cantidad;
        String menu = """
                ******************************************
                1) Dólar ----------------> Peso Argentino
                2) Peso Argentino -------> Dólar
                3) Dólar ----------------> Real Brasileño
                4) Real Brasileño -------> Dólar
                5) Dólar ----------------> Peso Colombiano
                6) Peso Colombiano ------> Dólar
                0) Salir del Sistema
                ******************************************
                """;
        Scanner teclado = new Scanner(System.in);

        do {

            // -----Inicio Sistema y Opciones
            System.out.println(menu);
            System.out.println("Elija la Convencion: ");
            opcion = teclado.nextInt();

            if (opcion == 0) {
                System.out.println("Saliendo del sistema...!!!");
            }

            System.out.println("Ingrese el valor a convertir: ");
            cantidad = teclado.nextInt();

            realizarConversion(opcion, cantidad);

        } while (opcion != 0);
        teclado.close();

    }

    private static void realizarConversion(int opcion, double cantidad) {
        ConexionApi consulta = new ConexionApi();
        String base = "", cambio = "";

        switch (opcion) {
            case 1 -> {
                base = "USD";
                cambio = "ARS";
            }
            case 2 -> {
                base = "ARS";
                cambio = "USD";
            }
            case 3 -> {
                base = "USD";
                cambio = "BRL";
            }
            case 4 -> {
                base = "BRL";
                cambio = "USD";
            }
            case 5 -> {
                base = "USD";
                cambio = "COP";
            }
            case 6 -> {
                base = "COP";
                cambio = "USD";
            }
            default -> {
                System.out.println("Opción no válida.");
                return;
            }

        }

        try {

            Moneda monedaRecord = consulta.parMoneda(base, cambio, cantidad);
            MonedaConstructor moneda = new MonedaConstructor(monedaRecord);
            System.out.println("");
            System.out.println(String.format("El valor de " + "%.2f" + " " + base + " su conversión es de: " + "%.2f"
                    + " " + cambio, cantidad, moneda.getConversion()));
            System.out.println("");
            GenerarArchivo generador = new GenerarArchivo();
            generador.generadorJson(moneda);

        } catch (Exception e) {
            System.out.println("Error al realizar la conversión: " + e.getMessage());
        }
    }
}
