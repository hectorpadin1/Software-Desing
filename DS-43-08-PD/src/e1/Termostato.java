package e1;

import e1.estados.Off;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Clase Termostato encargada de gestionar la temperatura de la calefacción, con diferentes modos de funcionamiento
 * que proporciona una interfaz para cambiar entre dichos modos y mostrar información acerca de la temperatura
 * de ambiente, temperatura de la calefacción y tiempo, según qué tipo de programa se haya introducido.
 *
 */
public class Termostato {
    private Estado estado = Off.getInstancia();
    private Power power = Power.OFF;
    private float temperatura;
    private final List<String> systemLogs = new ArrayList<>();
    private String[] keywords;

    /**
     * Constructor para la clase Termostato que acepta una temperatura inicial recogida por los sensores.
     *
     * @param temperatura la nueva temperatura que han detectado los sensores.
     */
    public Termostato (float temperatura) {
        this.temperatura = temperatura;
    }

    /**
     * Getter para el atributo que permite conocer si la calefacción está encendida o apagada.
     *
     * @return devuelve en formato {ON,OFF} si la máquina está encendida o apagada.
     */
    public Power getPower() {
        return power;
    }

    /**
     * Setter para el atributo que permite conocer si la calefacción está encendida o apagada.
     *
     * @param power pone en ON u OFF el termostato.
     */
    public void setPower(Power power) {
        this.power = power;
    }

    /**
     * Getter para el parámetro temperatura que recibe de los sensores.
     *
     * @return la temperatura del exterior.
     */
    public float getTemperatura() {
        return temperatura;
    }

    /**
     * Getts all the logs from the system.
     *
     * @return los logs del sistema.
     */
    public List<String> getSystemLogs() {
        return systemLogs;
    }

    /**
     * Adds a new log into the system logs.
     *
     * @param log el nuevo log a añadir.
     */
    public void addLog(String log) {
        systemLogs.add(log);
    }

    /**
     * Getter para el atributo keywords que representa los datos extra introducidos por el usuario para cada
     * modo de funcionamiento.
     *
     * @return los parámetros que introdujo el usuario.
     */
    public String[] getKeywords() {
        return keywords;
    }

    /**
     * Setter para el atributo keywords que representa los datos extra introducidos por el usuario para cada
     * modo de funcionamiento.
     *
     * @param keywords los argumentos introducidos por teclado.
     */
    public void setKeywords(String[] keywords) {
        this.keywords = keywords;
    }

    /**
     * Método que permite ver el estado del termostato. Usado principalmente en pruebas.
     *
     * @return devuelve el modo en el que se encuentra el termostato.
     */
    public Estado getEstado() {
        return estado.getEstado();
    }

    /**
     * Método que permite el cambio de estado, y enciende o apaga el termostato si es necesario.
     *
     * @param estado el modo al que se quiere cambiar.
     */
    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    /**
     * Permite registrar una nueva temperatura en el sistema. Simularemos que cada vez que se llama al método han
     * pasado 5 minutos.
     *
     * @param currentTemperature la nueva temperatura que han detectado los sensores
     */
    public void newTemperature(float currentTemperature) {
        this.temperatura = currentTemperature;
        this.estado.nuevaTemperatura(this);
    }

    /**
     * Muestra por pantalla la información actual de la temperatura que reciben los sensores, del modo del termostado
     * y si se encuentra en TIMER o PROGRAM dará información del tiempo restante o de la temperatura de consigna
     * respectivamente. Puede ser activado en cualquier momento y no implica la actualización de los valores que
     * reciben los sensores.
     *
     * @return información del estado y temperatura actuales.
     */
    public String screenInfo() {
        return estado.mostrarInfo(this);
    }

    /**
     * Este método lo usará el usuario o una aplicación externa para cuando desee cambiar de modo de funcionamiento
     * que admite un argumento extra para ajustar el tiempo o la temperatura, dependiendo del modo que hayamos
     * seleccionado.
     *
     * @param nuevoEstado el estado al que se quiere cambiar.
     * @param keywords los argumentos que se introdujeron.
     */
    public void cambioModo(Estado nuevoEstado, String keywords) {
        estado.cambioEstado(this,nuevoEstado,keywords);
    }

    /**
     * Método de hascode para la clase Termostato usando el algoritmo de hash 31.
     *
     * @return hascode del objeto con el algoritmo de hash 31.
     */
    @Override
    public int hashCode() {
        int result;
        long tmp;
        result = estado.hashCode();
        result = result*31 + power.hashCode();
        tmp = Float.floatToIntBits(temperatura);
        result = result*31 + (int) (tmp ^ (tmp >>> 32));
        result = result*31 + systemLogs.hashCode();
        if (keywords!=null)
            for (String s : keywords)
                result = result*31 + s.hashCode();
        return result;
    }

    /**
     * Mejora el método equals para el objeto por defecto para comprobar si dos objetos Termostato son iguales.
     *
     * @param o el objeto a comprobar.
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
        if (getClass() != o.getClass())
            return false;
        Termostato t = (Termostato) o;
        if (!estado.equals(t.getEstado()))
            return false;
        if (!power.equals(t.getPower()))
            return false;
        if (!(temperatura==t.getTemperatura()))
            return false;
        return systemLogs.equals(t.getSystemLogs()) && Arrays.equals(keywords, t.getKeywords());
    }
}
