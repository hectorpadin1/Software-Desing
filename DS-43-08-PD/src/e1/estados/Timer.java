package e1.estados;

import e1.Estado;
import e1.Power;
import e1.Termostato;

/**
 * Estado concreto del termostato que representa el modo Timer.
 *
 */
public class Timer implements Estado {
    private static Timer instancia = null;

    private Timer() {
    }

    /**
     * Devuelve una instacia del estado Timer.
     *
     * @return instancia de Timer.
     */
    public static Timer getInstancia() {
        if (instancia==null)
            instancia = new Timer();
        return instancia;
    }

    /**
     * Devuelve el estado de Timer.
     *
     * @return devuelve el modo.
     */
    @Override
    public Estado getEstado() {
        return getInstancia();
    }

    /**
     * Método por el cual la clase Termostato delega el comportamiento ante un nuevo registro de temperatura
     * en la clase Timer.
     *
     * @param termostato el termostato que quiere actualizar su temperatura.
     */
    @Override
    public void nuevaTemperatura(Termostato termostato) {
        String[] aux = termostato.getKeywords();
        int tiempo = Integer.parseInt(aux[0]);

        if ((tiempo -= 5) > 0) {
            termostato.addLog(termostato.getTemperatura() + " Modo " + getClass().getSimpleName()
                    + " (faltan " + tiempo + " minutos) - Calefacción encendida.");
        } else {
            termostato.addLog("Se desactiva el modo " + getClass().getSimpleName() + ".");
            tiempo=0;
        }
        aux[0] = String.valueOf(tiempo);
        termostato.setKeywords(aux);

        if (tiempo == 0) {
            termostato.setPower(Power.OFF);
            termostato.setEstado(Off.getInstancia());
        }
    }

    /**
     * Método por el cual la clase Termostato delega el comportamiento de mostrar la información actual al estado
     * Timer.
     *
     * @param termostato el termostato que solicita información.
     * @return la información solicitada.
     */
    @Override
    public String mostrarInfo(Termostato termostato) {
        return termostato.getTemperatura() + " " + termostato.getPower().toString() + " T " + termostato.getKeywords()[0];
    }

    /**
     * Método que define las reglas del estado Timer para cambiar de modo de funcionamiento. La única
     * restricción es que no puede cambiar directamente al modo Program.
     *
     * @param termostato el termostato que solicita el cambio de estado.
     * @param estado el estado al que se quiere cambiar.
     * @param keywords los argumentos que introdujo el usuario.
     */
    @Override
    public void cambioEstado(Termostato termostato, Estado estado, String keywords) {
        if (estado instanceof Program)
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
        termostato.addLog("Se activa el modo " + Timer.getInstancia().getClass().getSimpleName() + " " +
                Integer.parseInt(termostato.getKeywords()[0]) + " minutos.");
    }

    /**
     * Mejora el método equals para el objeto por defecto para comprobar si dos objetos Timer son iguales.
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
