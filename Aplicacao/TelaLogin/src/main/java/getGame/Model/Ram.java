/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package getGame.Model;

import oshi.SystemInfo;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.util.FormatUtil;

/**
 *
 * @author eduab
 */
public class Ram {
    
       
    
    private static String printMemory(GlobalMemory memory) {

        return String.format("<html>Total: %s <br> Disponível: %s </html>",
                FormatUtil.formatBytes(memory.getTotal()),
                FormatUtil.formatBytes(memory.getAvailable()));
    }
    
      private static String memoriaTotal(GlobalMemory memory) {
          String valorMemoriaTotal = "0.00";
          String totalMemory =   FormatUtil.formatBytes(memory.getTotal()).replace("GiB", "").replace(",",".");
          valorMemoriaTotal = String.format("%s", totalMemory);
          return valorMemoriaTotal;
      }
      
       private static String memoriaUsada(GlobalMemory memory) {
          String valorMemoriaUsada = "0.00";
          String usedMemory = (FormatUtil.formatBytes(memory.getTotal() - memory.getAvailable())).replace("GiB","").replace(",",".");
          valorMemoriaUsada = String.format("%s", usedMemory);
          return valorMemoriaUsada;
       } 
       
        private static String memoriaDisponivel(GlobalMemory memory) {
            String valorPorcentagemMemoriaDisponivel = "0.00";
            String availableMemory = FormatUtil.formatBytes(memory.getAvailable()).replace("GiB","").replace(",",".");
            Double memoriaDisponivel = Double.parseDouble(availableMemory);
            String totalMemory =   FormatUtil.formatBytes(memory.getTotal()).replace("GiB", "").replace(",",".");
            Double memoriaTotal = Double.parseDouble(totalMemory);
            Double porcentagemMemoriaDisponivel = memoriaDisponivel / memoriaTotal;
            
            valorPorcentagemMemoriaDisponivel =  String.format("%s", porcentagemMemoriaDisponivel);
            
            return valorPorcentagemMemoriaDisponivel;
        }
    
         public String getMemoriaTotal() {
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();

        return memoriaTotal(hal.getMemory());
    }
         
          public String getMemoriaUsada() {
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();

        return memoriaUsada(hal.getMemory());
    }
        
           public String getMemoriaDisponivel() {
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();

        return memoriaDisponivel(hal.getMemory());
    }
          
    public String getRAM() {
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();

        return printMemory(hal.getMemory());
    }
}
