package e1.estados;

import e1.Estado;
import e1.Power;
import e1.Termostato;

/**
 * Estado concreto del termostato que representa el modo Program.
 *
 */

public class Program implements Estado {
    private static Program instancia = null;

    private Program() {
    }

    /**
     * Devuelve una instancia de la clase Program.
     *
     * @return instancia de Program.
     */
    public static Program getInstancia() {
        if (instancia==null)
            instancia = new Program();
        return instancia;
    }

    /**
     * Devuelve el estado de Program.
     *
     * @return devuelve el modo.
     */
    @Override
    public Estado getEstado() {
        return getInstancia();
    }

    /**
     * Método por el cual la clase Termostato delega el comportamiento ante un nuevo registro de temperatura
     * en la clase Program.
     *
     * @param termostato el termostato que quiere actualizar su temperatura.
     */
    @Override
    public void nuevaTemperatura(Termostato termostato) {
        String[] aux = termostato.getKeywords();
        float tempConsignia = Float.parseFloat(aux[0]);

        if (termostato.getTemperatura() >= tempConsignia && termostato.getPower().equals(Power.ON))
            termostato.setPower(Power.OFF);
        else if (termostato.getTemperatura() < tempConsignia && termostato.getPower().equals(Power.OFF))
            termostato.setPower(Power.ON);

        termostato.addLog(termostato.getTemperatura() + " Modo " + getClass().getSimpleName()
                + " (a " + tempConsignia + " grados) - Calefacción " + ((termostato.getPower().equals(Power.OFF)) ?
                "apagada" : "encendida") + ".");
    }

    /**
     * Método por el cual la clase Termostato delega el comportamiento de mostrar la información actual al estado
     * Program.
     *
     * @param termostato el termostato que solicita información.
     * @return la información solicitada.
     */
    @Override
    public String mostrarInfo(Termostato termostato) {
        return termostato.getTemperatura() + " " + termostato.getPower().toString() + " P " + termostato.getKeywords()[0];
    }

    /**
     * Método que define las reglas del estado Program para cambiar de modo de funcionamiento. La única
     * restricción es que no puede cambiar directamente al modo Timer.
     *
     * @param termostato el termostato que solicita el cambio de estado.
     * @param estado el estado al que se quiere cambiar.
     * @param keywords los argumentos que introdujo el usuario.
     */
    @Override
    public void cambioEstado(Termostato termostato, Estado estado, String keywords) {
        if (estado instanceof Timer)
            return;
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
        termostato.setPower(Power.ON);
        termostato.addLog("Se activa el modo " + Program.getInstancia().getClass().getSimpleName() + " a " +
                Float.parseFloat(termostato.getKeywords()[0]) + " grados.");
    }

    /**
     * Mejora el método equals para el objeto por defecto para comprobar si dos objetos Program son iguales.
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
