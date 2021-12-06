package e1;

/**
 * Intefaz que define los métodos que tendrá que implementar cada estado en concreto
 *
 */
public interface Estado {

    Estado getEstado();
    void nuevaTemperatura(Termostato termostato);
    String mostrarInfo(Termostato termostato);
    void cambioEstado(Termostato termostato, Estado estado, String keywords);
    void logActivado(Termostato termostato);

}
