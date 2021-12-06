package e1;

import e1.estados.Manual;
import e1.estados.Off;
import e1.estados.Program;
import e1.estados.Timer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EstadosTermostatoTest {

    Off off = Off.getInstancia();
    Manual manual = Manual.getInstancia();
    Timer timer = Timer.getInstancia();
    Program program = Program.getInstancia();

    @Test
    public void cambioEstadoManualTest() {
        Termostato t = new Termostato(20.0f);

        assertEquals(Power.OFF, t.getPower());
        assertEquals(off, t.getEstado());
        assertEquals("20.0 OFF O", t.screenInfo());

        t.cambioModo(timer,"15");
        assertEquals(Power.ON, t.getPower());
        assertEquals(timer, t.getEstado());
        assertEquals("20.0 ON T 15", t.screenInfo());

        t.cambioModo(manual, null);
        assertEquals(Power.ON, t.getPower());
        assertEquals(manual, t.getEstado());
        assertEquals("20.0 ON M", t.screenInfo());

        t.cambioModo(program,"20.3");
        assertEquals(Power.ON, t.getPower());
        assertEquals(program, t.getEstado());
        assertEquals("20.0 ON P 20.3", t.screenInfo());
    }

    @Test
    public void estadoOffManualTest() {
        Termostato t = new Termostato(20.0f);

        assertEquals(off, t.getEstado());
        assertEquals("20.0 OFF O", t.screenInfo());

        t.cambioModo(manual, null);
        assertEquals("20.0 ON M", t.screenInfo());

        t.cambioModo(off, null);
        assertEquals("20.0 OFF O", t.screenInfo());

        t.newTemperature(21.0f);
        assertEquals("21.0 OFF O", t.screenInfo());

        t.newTemperature(20.5f);
        assertEquals("20.5 OFF O", t.screenInfo());

        t.newTemperature(20.0f);
        t.cambioModo(manual, null);
        assertEquals("20.0 ON M", t.screenInfo());

        t.newTemperature(21.0f);
        assertEquals("21.0 ON M", t.screenInfo());

        t.newTemperature(21.5f);
        assertEquals("21.5 ON M", t.screenInfo());

        t.newTemperature(21.0f);
        t.cambioModo(off, null);
        assertEquals("21.0 OFF O", t.screenInfo());

    }

    @Test
    public void estadoTimerTest() {
        Termostato t1 = new Termostato(20.0f);
        Termostato t2 = new Termostato(18.0f);
        t1.cambioModo(timer, "15");

        assertEquals("20.0 ON T 15", t1.screenInfo());
        assertEquals("18.0 OFF O", t2.screenInfo());
        assertEquals(Power.ON, t1.getPower());
        assertEquals(Power.OFF, t2.getPower());

        t1.newTemperature(21.0f);
        t2.newTemperature(18.0f);
        assertEquals("21.0 ON T 10", t1.screenInfo());
        assertEquals("18.0 OFF O", t2.screenInfo());
        assertEquals(Power.ON, t1.getPower());
        assertEquals(Power.OFF, t2.getPower());

        t1.newTemperature(21.5f);
        t2.newTemperature(18.0f);
        t2.cambioModo(timer, "19");
        assertEquals("21.5 ON T 5", t1.screenInfo());
        assertEquals("18.0 ON T 19", t2.screenInfo());
        assertEquals(Power.ON, t1.getPower());
        assertEquals(Power.ON, t2.getPower());

        t1.newTemperature(22.0f);
        t2.newTemperature(19.0f);
        assertEquals("22.0 OFF O", t1.screenInfo());
        assertEquals("19.0 ON T 14", t2.screenInfo());
        assertEquals(Power.OFF, t1.getPower());
        assertEquals(Power.ON, t2.getPower());

        t1.newTemperature(21.5f);
        t2.newTemperature(20.0f);
        assertEquals("21.5 OFF O", t1.screenInfo());
        assertEquals("20.0 ON T 9", t2.screenInfo());
        assertEquals(Power.OFF, t1.getPower());
        assertEquals(Power.ON, t2.getPower());

        t1.newTemperature(21.0f);
        t2.newTemperature(21.0f);
        assertEquals("21.0 OFF O", t1.screenInfo());
        assertEquals("21.0 ON T 4", t2.screenInfo());
        assertEquals(Power.OFF, t1.getPower());
        assertEquals(Power.ON, t2.getPower());

        t1.newTemperature(21.0f);
        t2.newTemperature(21.0f);
        assertEquals("21.0 OFF O", t1.screenInfo());
        assertEquals("21.0 OFF O", t2.screenInfo());
        assertEquals(Power.OFF, t1.getPower());
        assertEquals(Power.OFF, t2.getPower());

    }

    @Test
    public void estadoProgramTest() {
        Termostato t1 = new Termostato(21.0f);
        Termostato t2 = new Termostato(20.0f);
        t1.cambioModo(program, "22.5");

        assertEquals("21.0 ON P 22.5", t1.screenInfo());
        assertEquals("20.0 OFF O", t2.screenInfo());
        assertEquals(Power.ON, t1.getPower());
        assertEquals(Power.OFF, t2.getPower());

        t1.newTemperature(22.0f);
        t2.newTemperature(20.0f);
        assertEquals("22.0 ON P 22.5", t1.screenInfo());
        assertEquals("20.0 OFF O", t2.screenInfo());
        assertEquals(Power.ON, t1.getPower());
        assertEquals(Power.OFF, t2.getPower());

        t1.newTemperature(23.0f);
        t2.newTemperature(20.0f);
        assertEquals("23.0 OFF P 22.5", t1.screenInfo());
        assertEquals("20.0 OFF O", t2.screenInfo());
        assertEquals(Power.OFF, t1.getPower());
        assertEquals(Power.OFF, t2.getPower());

        t1.newTemperature(22.5f);
        t2.newTemperature(20.0f);
        t2.cambioModo(program, "21.0");
        assertEquals("22.5 OFF P 22.5", t1.screenInfo());
        assertEquals("20.0 ON P 21.0", t2.screenInfo());
        assertEquals(Power.OFF, t1.getPower());
        assertEquals(Power.ON, t2.getPower());

        t1.newTemperature(22.0f);
        t2.newTemperature(20.5f);
        assertEquals("22.0 ON P 22.5", t1.screenInfo());
        assertEquals("20.5 ON P 21.0", t2.screenInfo());
        assertEquals(Power.ON, t1.getPower());
        assertEquals(Power.ON, t2.getPower());

        t1.newTemperature(22.3f);
        t2.newTemperature(21.0f);
        assertEquals("22.3 ON P 22.5", t1.screenInfo());
        assertEquals("21.0 OFF P 21.0", t2.screenInfo());
        assertEquals(Power.ON, t1.getPower());
        assertEquals(Power.OFF, t2.getPower());

        t1.newTemperature(22.6f);
        t2.newTemperature(20.7f);
        assertEquals("22.6 OFF P 22.5", t1.screenInfo());
        assertEquals("20.7 ON P 21.0", t2.screenInfo());
        assertEquals(Power.OFF, t1.getPower());
        assertEquals(Power.ON, t2.getPower());

    }


    @Test
    public void cambioProgramTimerTest() {
        Termostato t = new Termostato(20.0f);

        t.cambioModo(program, "22.5");
        assertEquals("20.0 ON P 22.5", t.screenInfo());
        t.cambioModo(timer, "15");
        t.newTemperature(22.6f);
        assertEquals("22.6 OFF P 22.5", t.screenInfo());
        t.cambioModo(timer, "15");

        t.cambioModo(manual, null);
        t.newTemperature(21.0f);
        assertEquals("21.0 ON M", t.screenInfo());

        t.cambioModo(timer, "7");
        assertEquals("21.0 ON T 7", t.screenInfo());
        t.cambioModo(program, "22.0");
        t.newTemperature(21.0f);
        t.newTemperature(21.0f);
        assertEquals("21.0 OFF O", t.screenInfo());

        t.cambioModo(program, "22.5");
        assertEquals("21.0 ON P 22.5", t.screenInfo());
        t.cambioModo(timer, "20");

        t.cambioModo(off, null);
        t.newTemperature(21.0f);
        assertEquals("21.0 OFF O", t.screenInfo());

        t.cambioModo(timer, "10");
        assertEquals("21.0 ON T 10", t.screenInfo());
        t.newTemperature(22.0f);
        t.cambioModo(program, "22.0");

        t.cambioModo(manual, null);
        assertEquals("22.0 ON M", t.screenInfo());

        t.cambioModo(program, "22.5");
        assertEquals("22.0 ON P 22.5", t.screenInfo());
    }
}