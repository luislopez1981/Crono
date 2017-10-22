package com.cice.logica;

import com.cice.vistas.Principal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author NYL
 */
public class Crono extends Thread {

    private int centesimas, minutos, segundos = 0;
    private String min, seg, cent = "";
    private volatile boolean estado = true;
    private volatile boolean pause = false;
    private Principal vista;

    public Crono(Principal vista) {
        this.vista = vista;
    }

    public Crono() {

    }


    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }

    public boolean isPause() {
        return pause;
    }
    
    

    @Override
    public void run() {

        while (estado) {

            if (!pause) {
                try {
                    Thread.sleep(10);
                    centesimas++;

                    if (centesimas == 100) {
                        centesimas = 0;
                        segundos++;
                    }
                    if (segundos == 60) {
                        segundos = 0;
                        minutos++;
                    }
                    if (minutos == 60) {
                        minutos = 0;
                    }

                    if (minutos < 10) {
                        min = "0" + min.valueOf(minutos);
                    } else {
                        min = min.valueOf(minutos);
                    }
                    if (segundos < 10) {
                        seg = "0" + seg.valueOf(segundos);
                    } else {
                        seg = seg.valueOf(segundos);
                    }

                    if (centesimas < 10) {
                        cent = "0" + cent.valueOf(centesimas);
                    } else {
                        cent = cent.valueOf(centesimas);
                    }

                    vista.txtPantalla.setText(min + ":" + seg + ":" + cent);
                    
                } catch (InterruptedException ex) {
                    Logger.getLogger(Crono.class.getName()).log(Level.SEVERE, null, ex);
                }catch (IllegalThreadStateException Itse ){
                    
                }
            }

        }
    }

    public void seleccionarFuncion(JPanel botonPulsado) {

        String aux = botonPulsado.getName();
        switch (aux) {
            case "start":
                vista.encender();
                break;
            case "pause":
                vista.pausar();
                break;
            case "split":
                vista.split();
                break;
            case "stop":
                vista.detener();

        }

    }

}
