package com.mycompany.exp2_s5_diego_cruz_teatro_moro;

import java.util.Scanner;

public class Exp2_S5_Diego_Cruz_Teatro_Moro {

    static double ingresosTotales = 0;
    static int totalEntradasVendidas = 0;
    static final int MAX_ENTRADAS = 100;
    static String nombreTeatro = "TEATRO MORO";
    static int nextId = 1;

    static int[] numeroEntrada = new int[MAX_ENTRADAS];
    static String[] ubicaciones = new String[MAX_ENTRADAS];
    static double[] preciosFinal = new double[MAX_ENTRADAS];
    static String[] tiposCliente = new String[MAX_ENTRADAS];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;
        String[] opcionesMenu = {
            "Venta de Entradas",
            "Promociones",
            "Busqueda de Entradas",
            "Eliminacion de Entradas",
            "Salir"
        };
        int contadorEntradas = 0;

        while (!salir) {
            System.out.println("\n=== MENU PRINCIPAL - " + nombreTeatro + " ===");
            for (int i = 0; i < opcionesMenu.length; i++) {
                System.out.println((i + 1) + ". " + opcionesMenu[i]);
            }
            System.out.print("Elija una opcion (1-5): ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("\n--- Venta de Entradas ---");
                    System.out.print("Cuantas entradas desea comprar? ");
                    int cantidad = scanner.nextInt();

                    if (contadorEntradas + cantidad > MAX_ENTRADAS) {
                        System.out.println("Error. No hay suficientes entradas disponibles.");
                        break;
                    }

                    double totalVenta = 0;

                    for (int i = 0; i < cantidad; i++) {
                        System.out.println("\nEntrada " + (i + 1) + ":");
                        System.out.println("Seleccione la ubicacion:");
                        System.out.println("1. VIP ($15.000)");
                        System.out.println("2. Platea ($12.000)");
                        System.out.println("3. General ($10.000)");
                        System.out.print("Opcion: ");
                        int ubicacionOpcion = scanner.nextInt();

                        double precioBase = 10000;
                        String ubicacion = "General";

                        if (ubicacionOpcion == 1) {
                            precioBase = 15000;
                            ubicacion = "VIP";
                        } else if (ubicacionOpcion == 2) {
                            precioBase = 12000;
                            ubicacion = "Platea";
                        }

                        System.out.print("Ingrese su edad: ");
                        int edad = scanner.nextInt();
                        double descuento = 0;
                        String tipoCliente = "Publico general";
                        if (edad >= 18 && edad <= 24) {
                            descuento = 0.10;
                            tipoCliente = "Estudiante";
                        } else if (edad > 60) {
                            descuento = 0.15;
                            tipoCliente = "Tercera edad";
                        }

                        double precioTicket = precioBase * (1 - descuento);

                        if (cantidad == 2 && i == 1) {
                            precioTicket *= 0.5;
                            System.out.println("Promocion aplicada: segunda entrada a mitad de precio.");
                        } else if (cantidad >= 3) {
                            precioTicket *= 0.80;
                            if (i == 0) {
                                System.out.println("Promocion aplicada: 20% de descuento por compra multiple.");
                            }
                        }

                        System.out.println("Precio final: $" + precioTicket);

                        numeroEntrada[contadorEntradas] = nextId;
                        nextId++;
                        ubicaciones[contadorEntradas] = ubicacion;
                        preciosFinal[contadorEntradas] = precioTicket;
                        tiposCliente[contadorEntradas] = tipoCliente;
                        contadorEntradas++;

                        totalVenta += precioTicket;
                    }

                    ingresosTotales += totalVenta;
                    totalEntradasVendidas += cantidad;
                    System.out.println("Total a pagar: $" + totalVenta);
                    break;

                case 2:
                    System.out.println("\n--- Promociones Disponibles ---");
                    System.out.println("1. Compra 2 entradas y obten la segunda a mitad de precio.");
                    System.out.println("2. Compra 3 o mas entradas y obten un 20% de descuento en cada una.");
                    break;

                case 3:
                    System.out.println("\n--- Búsqueda de Entradas ---");
                    System.out.println("Buscar por:");
                    System.out.println("1. Numero");
                    System.out.println("2. Ubicacion");
                    System.out.println("3. Tipo de cliente");
                    System.out.print("Opcion: ");
                    int tipoBusqueda = scanner.nextInt();
                    boolean encontrada = false;

                    if (tipoBusqueda == 1) {
                        System.out.print("Ingrese el numero de entrada: ");
                        int buscado = scanner.nextInt();
                        for (int i = 0; i < contadorEntradas; i++) {
                            if (numeroEntrada[i] == buscado) {
                                System.out.println("Entrada #" + numeroEntrada[i]);
                                System.out.println("Ubicacion: " + ubicaciones[i]);
                                System.out.println("Precio: $" + preciosFinal[i]);
                                System.out.println("Tipo: " + tiposCliente[i]);
                                encontrada = true;
                            }
                        }
                    } else if (tipoBusqueda == 2) {
                        System.out.print("Ingrese ubicacion (VIP/Platea/General): ");
                        scanner.nextLine();
                        String ub = scanner.nextLine();
                        for (int i = 0; i < contadorEntradas; i++) {
                            if (ubicaciones[i].equalsIgnoreCase(ub)) {
                                System.out.println("Entrada #" + numeroEntrada[i] + " - " + ubicaciones[i] + " - $" + preciosFinal[i] + " - " + tiposCliente[i]);
                                encontrada = true;
                            }
                        }
                    } else if (tipoBusqueda == 3) {
                        System.out.print("Ingrese tipo (Estudiante/Tercera edad/Publico general): ");
                        scanner.nextLine();
                        String tipo = scanner.nextLine();
                        for (int i = 0; i < contadorEntradas; i++) {
                            if (tiposCliente[i].equalsIgnoreCase(tipo)) {
                                System.out.println("Entrada #" + numeroEntrada[i] + " - " + ubicaciones[i] + " - $" + preciosFinal[i] + " - " + tiposCliente[i]);
                                encontrada = true;
                            }
                        }
                    }

                    if (!encontrada) {
                        System.out.println("No se encontraron entradas.");
                    }
                    break;

                case 4:
                    System.out.println("\n--- Eliminar Entrada ---");
                    System.out.print("Ingrese el numero de entrada a eliminar: ");
                    int eliminar = scanner.nextInt();
                    boolean eliminado = false;
                    for (int i = 0; i < contadorEntradas; i++) {
                        if (numeroEntrada[i] == eliminar) {
                            ingresosTotales -= preciosFinal[i];
                            for (int j = i; j < contadorEntradas - 1; j++) {
                                numeroEntrada[j] = numeroEntrada[j + 1];
                                ubicaciones[j] = ubicaciones[j + 1];
                                preciosFinal[j] = preciosFinal[j + 1];
                                tiposCliente[j] = tiposCliente[j + 1];
                            }
                            contadorEntradas--;
                            totalEntradasVendidas--;
                            eliminado = true;
                            break;
                        }
                    }
                    if (eliminado) {
                        System.out.println("Entrada eliminada.");
                    } else {
                        System.out.println("No se encontro esa entrada.");
                    }
                    break;

                case 5:
                    salir = true;
                    System.out.println("Gracias por su compra. Hasta pronto.");
                    break;

                default:
                    System.out.println("Opcion no valida.");
            }
        }
        scanner.close();
    }
}
