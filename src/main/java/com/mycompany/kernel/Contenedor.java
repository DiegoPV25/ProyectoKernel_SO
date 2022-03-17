package com.mycompany.kernel;
import java.io.*;
import java.util.ArrayList;

public class Contenedor {
String ubicacion;
String metodoScheduling = "FIFO";
int tamanioQuantum = 0;
ArrayList<Proceso> listaReady = new ArrayList<Proceso>();
ArrayList<Proceso> listaRunning = new ArrayList<Proceso>();
ArrayList<Proceso> listaBlocked = new ArrayList<Proceso>();
ArrayList<Proceso> listaFinished = new ArrayList<Proceso>();
int reloj = 0;

public Contenedor(String path) {
   this.ubicacion = path;
}
 
public void actualizarListas(int reloj) {
   for (int i = 0; i < listaReady.size(); i++) {
        if (listaReady.size() > 0) {
        listaReady.get(i).actualizarProceso(reloj);
        if (listaReady.get(i).status == "Running") { Proceso proc = listaReady.get(i); listaReady.remove(i); listaRunning.add(proc); }
        else if (listaReady.get(i).status == "Blocked") { Proceso proc = listaReady.get(i); listaReady.remove(i); listaBlocked.add(proc); }
        else if (listaReady.get(i).status == "Finished") { Proceso proc = listaReady.get(i); listaReady.remove(i); listaFinished.add(proc); }
   }
}
   for (int i = 0; i < listaRunning.size(); i++) {
    if (listaRunning.size() > 0) {
        listaRunning.get(i).actualizarProcesoRunning(reloj);
        if (listaRunning.get(i).status == "Ready") { Proceso proc = listaRunning.get(i); listaRunning.remove(i); listaReady.add(proc); }
        else if (listaRunning.get(i).status == "Blocked") { Proceso proc = listaRunning.get(i); listaRunning.remove(i); listaBlocked.add(proc); }
        else if (listaRunning.get(i).status == "Finished") { Proceso proc = listaRunning.get(i); listaRunning.remove(i); listaFinished.add(proc); }   
    }
}
   for (int i = 0; i < listaBlocked.size(); i++) {
        if (listaBlocked.size() > 0) {
        listaBlocked.get(i).actualizarProceso(reloj);
        if (listaBlocked.get(i).status == "Running") { Proceso proc = listaBlocked.get(i); listaBlocked.remove(i); listaRunning.add(proc); }
        else if (listaBlocked.get(i).status == "Ready") { Proceso proc = listaBlocked.get(i); listaBlocked.remove(i); listaReady.add(proc); }
        else if (listaBlocked.get(i).status == "Finished") { Proceso proc = listaBlocked.get(i); listaBlocked.remove(i); listaFinished.add(proc); }
   }
}
}

    void dispatchContenedor(int reloj) {
    if (metodoScheduling == "FIFO") { 
        int tiempoMenorllegada = reloj;
        if(listaReady.isEmpty() == false && listaRunning.isEmpty() == true) {
            for (int i = 0; i < listaReady.size(); i++) {
                if(listaReady.get(i).tiempoLlegada < tiempoMenorllegada) tiempoMenorllegada = listaReady.get(i).tiempoLlegada;
            }
            for (int i = 0; i < listaReady.size(); i++) {
                if(listaReady.get(i).tiempoLlegada == tiempoMenorllegada) listaReady.get(i).status = "Running";
            }
        }

    }
    if (metodoScheduling == "Round Robin") { }
    if (metodoScheduling == "SRT") {
        int srt = reloj;
        boolean completado = false;
        if(listaReady.isEmpty() == false && listaRunning.isEmpty() == true) {
            for (int i = 0; i < listaReady.size(); i++) {
                if(listaReady.get(i).cpuRestante < srt)  srt = listaReady.get(i).cpuRestante;
            }
            for (int i = 0; i < listaReady.size(); i++) {
                if(listaReady.get(i).cpuRestante == srt && completado == false) {listaReady.get(i).status = "Running"; completado = true;}
            }
        }
    }
    if (metodoScheduling == "HRRN") { 
        double hrrn = 0;
        boolean completado = false;
        if(listaReady.isEmpty() == false && listaRunning.isEmpty() == true) {
            for (int i = 0; i < listaReady.size(); i++) {
                if(listaReady.get(i).prioridad < hrrn)  hrrn = listaReady.get(i).prioridad;
            }
            for (int i = 0; i < listaReady.size(); i++) {
                if(listaReady.get(i).prioridad == hrrn && completado == false) {listaReady.get(i).status = "Running"; completado = true;}
            }
        }
    }
    

    actualizarListas(reloj);
    }

    void interrupcionSVC_IO() {
    dispatchContenedor(reloj);
    }

    void interrupcionSVC_TNormal() {
    if(listaRunning.isEmpty() == false) {
        listaRunning.get(0).status = "Finished";
    }
    dispatchContenedor(reloj);
    }

    void interrupcionSVC_Fecha() {
    if(listaRunning.isEmpty() == false) {
        listaRunning.get(0).status = "Blocked";
    } 
    dispatchContenedor(reloj);
    }

    void interrupcionErrorPrograma() {
    if(listaRunning.isEmpty() == false) {
        listaRunning.get(0).status = "Finished";
    }
    dispatchContenedor(reloj);
    }

    void interrupcionDispositivoIO() {
    if(listaBlocked.isEmpty() == false) {
        listaBlocked.get(0).status = "Ready";
    } 
    dispatchContenedor(reloj);
    }

    void interrupcionExterna_Quantum() {
    if(listaRunning.isEmpty() == false) {
        listaRunning.get(0).status = "Ready";
    } 
    dispatchContenedor(reloj);
    }

}

