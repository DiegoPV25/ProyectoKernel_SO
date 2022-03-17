
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
   
   public void actualizarProcesoRunning(int reloj) {
        this.cpuAsignado += 1;
        this.envejecimiento = reloj - this.tiempoLlegada - this.cpuAsignado;
        this.cpuRestante = this.ejecTotal - this.cpuAsignado;
        this.prioridad = (this.ejecTotal + this.envejecimiento) / this.ejecTotal;
   }

    public void actualizarProceso(int reloj) {
        this.cpuRestante = this.ejecTotal - this.cpuAsignado;
        this.envejecimiento = reloj - this.tiempoLlegada - this.ejecTotal;
   }
   
   
}
