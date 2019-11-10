/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package getGame.Model;

import java.io.File;
import oshi.SystemInfo;
import oshi.hardware.HWDiskStore;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.util.FormatUtil;

/**
 *
 * @author eduab
 */
public class Disco {
    
    private static String printDisks(HWDiskStore[] diskStores) {

        String dadosDisco = null;
        File[] roots = File.listRoots();
        for (File root : roots) {
            String usedSpace = FormatUtil.formatBytes(root.getTotalSpace() - root.getUsableSpace());
            String discos = String.format("%s used of %s",
             usedSpace, FormatUtil.formatBytes(root.getTotalSpace()));
            dadosDisco = discos;
        }
        
        return dadosDisco;
        
    }
    
    private static String printDisco(HWDiskStore[] diskStores) {

       
        File[] roots = File.listRoots();
        String valorFinal = "0.00";
        for (File root : roots) {
            
             String usableSpace = (FormatUtil.formatBytes(root.getUsableSpace()).replace("GiB","")).replace(",",".");
             Double espacoUtilizavel = Double.parseDouble(usableSpace);
             String totalSpace = (FormatUtil.formatBytes(root.getTotalSpace()).replace("GiB", "")).replace(",", ".");
             Double espacoTotal = Double.parseDouble(totalSpace);
             String usedSpace = (FormatUtil.formatBytes(root.getTotalSpace() - root.getUsableSpace()).replace("GiB","")).replace(",",".");
             Double espacoUsado = Double.parseDouble(usedSpace);
             Double porcentagemEspacoRestante = (espacoUtilizavel / espacoTotal) * 100;
             
             valorFinal =  String.format("%.2f",porcentagemEspacoRestante);
             
             //System.out.println(espacoUtilizavel);
             //System.out.println(espacoTotal);
            // System.out.println(espacoUsado);
           // System.out.println(porcentagemEspacoRestante);
             
        }
     return valorFinal;
    }
    
  
    
         private static String EspacoUtilizavel(HWDiskStore[] diskStores) {
             
        File[] roots = File.listRoots();
        String valorPorcentagemEspacoRestante = "0.00";
        for (File root : roots) {
            
            String usableSpace = (FormatUtil.formatBytes(root.getUsableSpace()).replace("GiB","")).replace(",",".");
            Double espacoUtilizavel = Double.parseDouble(usableSpace);
            String totalSpace = (FormatUtil.formatBytes(root.getTotalSpace()).replace("GiB", "")).replace(",", ".");
            Double espacoTotal = Double.parseDouble(totalSpace);
            Double porcentagemEspacoRestante = (espacoUtilizavel / espacoTotal) * 100;
            valorPorcentagemEspacoRestante =  String.format("%.2f",porcentagemEspacoRestante);
          
        }  
        return valorPorcentagemEspacoRestante;
         }
         
         private static String EspacoTotal(HWDiskStore[] diskStores) {
             File[] roots = File.listRoots();
             String valorEspacoTotal = "0.00";
             for (File root : roots) {
            String totalSpace = (FormatUtil.formatBytes(root.getTotalSpace()).replace("GiB", "")).replace(",", ".");
            Double espacoTotal = Double.parseDouble(totalSpace);
            
               valorEspacoTotal = String.format("%s", espacoTotal );           
             }
             return valorEspacoTotal;
         }
          
         public String getEspacoUtilizavel() {
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
        
        EspacoUtilizavel(hal.getDiskStores());
        return String.format("%s",EspacoUtilizavel(hal.getDiskStores()));
                
         }
         
         public String getEspacoTotal() {
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
        
        EspacoTotal(hal.getDiskStores());
        return String.format("%s",EspacoTotal(hal.getDiskStores()));
                
         } 
      
        public String getDisco() {
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();

        printDisks(hal.getDiskStores());
        return String.format("%s",printDisks(hal.getDiskStores()));
    }
}
