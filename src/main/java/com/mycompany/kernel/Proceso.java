
package com.mycompany.kernel;
import java.io.*;
import java.util.ArrayList;

public class Proceso {
       String nombre;
   int paginas;
   int ejecTotal;
   String status;
   int tiempoLlegada;
   int cpuAsignado;
   int envejecimiento;
   int cpuRestante;
   int quantumRestante;
   double prioridad;
   
   public Proceso(String nombre, int paginas, int ejecTotal, int reloj) {
      this.tiempoLlegada = reloj;
      this.nombre = nombre;
      this.paginas = paginas;
      this.ejecTotal = ejecTotal;
      this.status = "Ready";
   }
   
   public void actualizarProceso(int reloj) {
      this.cpuAsignado++;
      this.envejecimiento = reloj - this.tiempoLlegada - this.ejecTotal;
      this.cpuRestante = this.ejecTotal - this.cpuAsignado;
   }
   
   public void cambiarEstado(String status) {
      this.status = status;
   }
   
   public void cambiarQuantum(int quantum) {
      this.quantumRestante = quantum;
   }
   
}