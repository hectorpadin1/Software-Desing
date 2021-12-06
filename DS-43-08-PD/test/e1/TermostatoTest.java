package e1;

import e1.estados.Manual;
import e1.estados.Off;
import e1.estados.Program;
import e1.estados.Timer;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TermostatoTest {

    Off off = Off.getInstancia();
    Manual manual = Manual.getInstancia();
    Timer timer = Timer.getInstancia();
    Program program = Program.getInstancia();

    @Test
    public void logTest() {
        Termostato t = new Termostato(20.0f);
        List<String> l = new ArrayList<>();

        t.newTemperature(20.1f);
        l.add("20.1 Modo Off - Calefacción apagada.");

        t.cambioModo(manual, null);
        l.add("Se activa el modo Manual.");
        t.newTemperature(20.1f);
        l.add("20.1 Modo Manual - Calefacción encendida.");
        t.newTemperature(21.5f);
        l.add("21.5 Modo Manual - Calefacción encendida.");
        t.newTemperature(21.1f);
        l.add("21.1 Modo Manual - Calefacción encendida.");

        t.cambioModo(timer, "19");
        l.add("Se activa el modo Timer 19 minutos.");
        t.newTemperature(21.0f);
        l.add("21.0 Modo Timer (faltan 14 minutos) - Calefacción encendida.");
        t.newTemperature(21.9f);
        l.add("21.9 Modo Timer (faltan 9 minutos) - Calefacción encendida.");
        t.newTemperature(22.8f);
        l.add("22.8 Modo Timer (faltan 4 minutos) - Calefacción encendida.");
        t.newTemperature(22.8f);
        l.add("Se desactiva el modo Timer.");

        t.newTemperature(22.5f);
        l.add("22.5 Modo Off - Calefacción apagada.");
        t.newTemperature(21.4f);
        l.add("21.4 Modo Off - Calefacción apagada.");

        t.cambioModo(program, "20.0");
        l.add("Se activa el modo Program a 20.0 grados.");
        t.newTemperature(21.2f);
        l.add("21.2 Modo Program (a 20.0 grados) - Calefacción apagada.");
        t.newTemperature(20.8f);
        l.add("20.8 Modo Program (a 20.0 grados) - Calefacción apagada.");
        t.newTemperature(20.1f);
        l.add("20.1 Modo Program (a 20.0 grados) - Calefacción apagada.");
        t.newTemperature(19.2f);
        l.add("19.2 Modo Program (a 20.0 grados) - Calefacción encendida.");
        t.newTemperature(19.9f);
        l.add("19.9 Modo Program (a 20.0 grados) - Calefacción encendida.");
        t.newTemperature(20.7f);
        l.add("20.7 Modo Program (a 20.0 grados) - Calefacción apagada.");
        t.newTemperature(22.8f);
        l.add("22.8 Modo Program (a 20.0 grados) - Calefacción apagada.");

        t.cambioModo(off, null);
        l.add("Se activa el modo Off.");
        t.newTemperature(20.2f);
        l.add("20.2 Modo Off - Calefacción apagada.");

        assertEquals(l,t.getSystemLogs());
    }

    @Test
    public void equalsTest() {
        Object t1, t2;
        t1 = new Termostato(20.0f);
        t2 = null;

        assertEquals(true,t1.equals(t1));
        assertEquals(false,t1.equals(t2));
        t2 = new Object();
        assertEquals(false,t1.equals(t2));
        t2 = new Termostato(21.0f);
        ((Termostato) t2).setEstado(manual);
        assertEquals(false,t1.equals(t2));
        ((Termostato) t2).setEstado(off);
        ((Termostato) t2).setPower(Power.ON);
        assertEquals(false,t1.equals(t2));
        ((Termostato) t2).setPower(Power.OFF);
        assertEquals(false,t1.equals(t2));
        Termostato t3 = new Termostato(20.0f);
        String[] keyw = new String[1];
        keyw[0] = "55";
        ((Termostato) t2).setKeywords(keyw);
        t3.setKeywords(keyw);
        assertEquals(false,t1.equals(t3));
    }

    @Test
    public void hashCodeTest() {
        Termostato t1, t2;
        t1 = new Termostato(20.0f);
        t2 = new Termostato(20.0f);

        assertEquals(t2.hashCode(),t1.hashCode());
        assertEquals(t1.hashCode(),t1.hashCode());
        assertEquals(t2.hashCode(),t2.hashCode());

        t1.setEstado(manual);
        t2.setEstado(manual);
        String[] keyw = new String[1];
        keyw[0] = "55";
        t1.setKeywords(keyw);
        t2.setKeywords(keyw);
        t1.addLog("log");
        t2.addLog("log");

        assertEquals(t2.hashCode(),t1.hashCode());
        assertEquals(t1.hashCode(),t1.hashCode());
        assertEquals(t2.hashCode(),t2.hashCode());


        Termostato t3, t4;
        t3 = new Termostato(21.0f);
        t4 = new Termostato(21.0f);

        t1.setEstado(manual);
        t2.setEstado(manual);
        String[] keyw1 = new String[1];
        keyw[0] = "55";
        String[] keyw2 = new String[1];
        keyw[0] = "55";
        t1.setKeywords(keyw1);
        t2.setKeywords(keyw2);
        t1.addLog("log");
        t2.addLog("log");

        assertEquals(t3.hashCode(),t4.hashCode());
        assertEquals(t3.hashCode(),t3.hashCode());
        assertEquals(t4.hashCode(),t4.hashCode());
    }

}