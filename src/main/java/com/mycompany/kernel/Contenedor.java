/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.kernel;
import java.io.*;
import java.util.ArrayList;

public class Contenedor {
String contenedor;
ArrayList<Proceso> listaReady = new ArrayList<Proceso>();
ArrayList<Proceso> listaRunning = new ArrayList<Proceso>();
ArrayList<Proceso> listaBlocked = new ArrayList<Proceso>();
ArrayList<Proceso> listaFinished = new ArrayList<Proceso>();
int reloj = 0;

public Contenedor(String nombre) {
   this.contenedor = "";
}
 
public void actualizarListas() {
   for (int i = 0; i < listaReady.size(); i++) {
      if (listaReady.get(i).status == "Running") { Proceso proc = listaReady.get(i); listaReady.remove(i); listaRunning.add(proc); }
      if (listaReady.get(i).status == "Blocked") { Proceso proc = listaReady.get(i); listaReady.remove(i); listaBlocked.add(proc); }
      if (listaReady.get(i).status == "Finished") { Proceso proc = listaReady.get(i); listaReady.remove(i); listaFinished.add(proc); }
   }
   for (int i = 0; i < listaRunning.size(); i++) {
      if (listaReady.get(i).status == "Ready") { Proceso proc = listaRunning.get(i); listaRunning.remove(i); listaReady.add(proc); }
      if (listaReady.get(i).status == "Blocked") { Proceso proc = listaRunning.get(i); listaRunning.remove(i); listaBlocked.add(proc); }
      if (listaReady.get(i).status == "Finished") { Proceso proc = listaRunning.get(i); listaRunning.remove(i); listaFinished.add(proc); }
   }
   for (int i = 0; i < listaBlocked.size(); i++) {
      if (listaBlocked.get(i).status == "Running") { Proceso proc = listaBlocked.get(i); listaBlocked.remove(i); listaRunning.add(proc); }
      if (listaBlocked.get(i).status == "Ready") { Proceso proc = listaBlocked.get(i); listaBlocked.remove(i); listaReady.add(proc); }
      if (listaBlocked.get(i).status == "Finished") { Proceso proc = listaBlocked.get(i); listaBlocked.remove(i); listaFinished.add(proc); }
   }
}

}

