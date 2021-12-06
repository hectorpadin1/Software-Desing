package e1.estados;

import e1.Estado;
import e1.Power;
import e1.Termostato;

/**
 * Estado concreto del termostato que representa el modo OFF.
 *
 */

public class Off implements Estado {
    private static Off instancia = null;

    private Off () {
    }

    /**
     * Devuelve una instacia del estado OFF.
     *
     * @return instancia de Off.
     */
    public static Off getInstancia() {
        if (instancia==null)
            instancia = new Off();
        return instancia;
    }

    /**
     * Devuelve el estado de Off.
     *
     * @return devuelve el modo.
     */
    @Override
    public Estado getEstado() {
        return getInstancia();
    }

    /**
     * Método por el cual la clase Termostato delega el comportamiento ante un nuevo registro de temperatura
     * en la clase OFF.
     *
     * @param termostato el termostato que quiere actualizar su temperatura.
     */
    @Override
    public void nuevaTemperatura(Termostato termostato) {
        termostato.addLog(termostato.getTemperatura() + " Modo " + getClass().getSimpleName()
                + " - Calefacción apagada.");
    }

    /**
     * Método por el cual la clase Termostato delega el comportamiento de mostrar la información actual al estado
     * OFF.
     *
     * @param termostato el termostato que solicita información.
     * @return la información solicitada.
     */
    @Override
    public String mostrarInfo(Termostato termostato) {
        return termostato.getTemperatura() + " " + termostato.getPower().toString() + " O";
    }

    /**
     * Método que define las reglas del estado Off para cambiar de modo de funcionamiento.
     *
     * @param termostato el termostato que solicita el cambio de estado.
     * @param estado el estado al que se quiere cambiar.
     * @param keywords los argumentos que introdujo el usuario.
     */
    @Override
    public void cambioEstado(Termostato termostato, Estado estado, String keywords) {
        if (keywords!=null)
            termostato.setKeywords(keywords.split(" "));
        estado.logActivado(termostato);
        termostato.setEstado(estado);
    }

    /**
     * Método que se encarga de generar un log en el termostato indicando que el modo Timer ha sido activado.
     *
     * @param termostato el termostato que activa el estado.
     */
    @Override
    public void logActivado(Termostato termostato) {
        termostato.setPower(Power.OFF);
        termostato.addLog("Se activa el modo " + getClass().getSimpleName() + ".");
    }

    /**
     * Mejora el método equals para el objeto por defecto para comprobar si dos objetos Off son iguales.
     *
     * @param o el objeto que se quiere comparar.
     * @return true si son iguales, false en otro caso.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        return getClass() == o.getClass();
    }
}
