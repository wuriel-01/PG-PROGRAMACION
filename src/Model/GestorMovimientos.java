package Model;



import java.util.ArrayList;

public class GestorMovimientos {

    private static ArrayList<Movimientos> historial = new ArrayList<>();

    public static void agregarMovimiento(Movimientos movimiento) {

        if (movimiento != null && movimiento.tieneCambios()) {
            historial.add(movimiento);
        }
    }

    public static ArrayList<Movimientos> buscarPorUuid(String uuid) {

        ArrayList<Movimientos> resultado = new ArrayList<>();

        for (Movimientos movimiento : historial) {

            if (movimiento.getUuid().equals(uuid)) {
                resultado.add(movimiento);
            }
        }

        return resultado;
    }

    public static ArrayList<Movimientos> getHistorial() {
        return historial;
    }
}