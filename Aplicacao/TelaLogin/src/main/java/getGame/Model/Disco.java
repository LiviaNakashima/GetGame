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
          //   Double espacoUtilizavel = Double.parseDouble(usableSpace);
             String totalSpace = (FormatUtil.formatBytes(root.getTotalSpace()).replace("GiB", "")).replace(",", ".");
           //  Double espacoTotal = Double.parseDouble(totalSpace);
             String usedSpace = (FormatUtil.formatBytes(root.getTotalSpace() - root.getUsableSpace()).replace("GiB","")).replace(",",".");
            // Double espacoUsado = Double.parseDouble(usedSpace);
          //   Double porcentagemEspacoRestante = (espacoUtilizavel / espacoTotal) * 100;
             
             valorFinal =  String.format("%.2f",usedSpace);
             
             //System.out.println(espacoUtilizavel);
             //System.out.println(espacoTotal);
            // System.out.println(espacoUsado);
           // System.out.println(porcentagemEspacoRestante);
             
        }
     return valorFinal;
    }
    
  
    
         private static String EspacoUtilizavel(HWDiskStore[] diskStores) {
             
        File[] roots = File.listRoots();
        String valorPorcentagemEspacoRestante = null;
        for (File root : roots) {
            
            String usableSpace = (FormatUtil.formatBytes(root.getUsableSpace())); //.replace("GiB","")).replace(",",".");
            Double espacoUtilizavel = Double.parseDouble(usableSpace);
            String totalSpace = (FormatUtil.formatBytes(root.getTotalSpace())); //.replace("GiB", "")).replace(",", ".");
            Double espacoTotal = Double.parseDouble(totalSpace);
            Double porcentagemEspacoRestante = (espacoUtilizavel / espacoTotal) * 100;
            valorPorcentagemEspacoRestante =  String.format("%.2f",porcentagemEspacoRestante);
          
        }  
        return valorPorcentagemEspacoRestante;
         }
         
         private static String espacoTotal(HWDiskStore[] diskStores) {
             File[] roots = File.listRoots();
             String valorEspacoTotal = "0.00";
             for (File root : roots) {
            String totalSpace = (FormatUtil.formatBytes(root.getTotalSpace()));
          
            
               valorEspacoTotal = String.format("%s", totalSpace );           
             }
             return valorEspacoTotal;
         }
         
         private static String espacoUsado(HWDiskStore[] diskStores) {
             String valorUsado = "0.00";
                 File[] roots = File.listRoots();
                   for (File root : roots) {
          String usedSpace = FormatUtil.formatBytes(root.getTotalSpace() - root.getUsableSpace());
           
          
            valorUsado = String.format("%s", usedSpace);
                        
             }
            return valorUsado;
         }
         
         
       public float getEspacoUsado() {
           SystemInfo si = new SystemInfo();
            HardwareAbstractionLayer hal = si.getHardware();

            espacoUsado(hal.getDiskStores());
            espacoTotal(hal.getDiskStores());

            Float usedSpace = Float.parseFloat(String.format("%s", espacoUsado(hal.getDiskStores())).replace("GiB", "").replace(",","."));
            Float totalSpace = Float.parseFloat(String.format("%s",espacoTotal(hal.getDiskStores())).replace("GiB", "").replace(",","."));
            Float calculoPorcentagemEspacoUsado =  usedSpace / totalSpace * 100;
            
            return calculoPorcentagemEspacoUsado;
       } 
          
        public String getEspacoUtilizavel() {
            SystemInfo si = new SystemInfo();
            HardwareAbstractionLayer hal = si.getHardware();

            EspacoUtilizavel(hal.getDiskStores());
            return String.format("%s",EspacoUtilizavel(hal.getDiskStores()));
                
        }
         
        public Float getEspacoTotal() {
            SystemInfo si = new SystemInfo();
            HardwareAbstractionLayer hal = si.getHardware();

            espacoTotal(hal.getDiskStores());
            Float spaceTotal = Float.parseFloat(String.format("%s",espacoTotal(hal.getDiskStores())).replace("GiB", "").replace(",","."));
            
            return spaceTotal;    
         } 
      
        public String getDisco() {
            SystemInfo si = new SystemInfo();
            HardwareAbstractionLayer hal = si.getHardware();

            printDisks(hal.getDiskStores());
            return String.format("%s",printDisks(hal.getDiskStores()));
        }
}
