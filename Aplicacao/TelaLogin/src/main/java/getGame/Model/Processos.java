package getGame.Model;

import oshi.SystemInfo;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OSProcess;
import oshi.software.os.OperatingSystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Processos {
    
    private static List<String> oshi = new ArrayList<String>();
    private String processoFormatado;

    public List<String> getNomeProcesso() {
        SystemInfo si = new SystemInfo();
        OperatingSystem os = si.getOperatingSystem();
        List<String> processos = retornaProcessos(os);
        return processos;
    }
   
    public String getProcessos() {
        processoFormatado = "";
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
        OperatingSystem os = si.getOperatingSystem();
        printProcesses(os, hal.getMemory());

        for (String parteTexto : oshi) {;
            processoFormatado=parteTexto.concat(oshi + "\r\n");
        }
        
        return processoFormatado;
    }

    private static void printProcesses(OperatingSystem os, GlobalMemory memory) {
        oshi.removeAll(oshi);
        // Sort by highest CPU
        List<OSProcess> procs = Arrays.asList(os.getProcesses(10, OperatingSystem.ProcessSort.MEMORY));

        for (int i = 0; i < procs.size() && i < Integer.MAX_VALUE; i++) {
            OSProcess p = procs.get(i);
            oshi.add(String.format(" %5d %5.1f %4.1f %s%n", 
                    p.getProcessID(),
                    100d * (p.getKernelTime() + p.getUserTime()) / p.getUpTime(),
                    100d * p.getResidentSetSize() / memory.getTotal(), 
                    p.getName()));
        }
    }
    
    private static List<String> retornaProcessos(OperatingSystem os){
        oshi.removeAll(oshi);
        // Sort by highest CPU
        List<OSProcess> procs = Arrays.asList(os.getProcesses(10, OperatingSystem.ProcessSort.MEMORY));

        for (int i = 0; i < procs.size() && i < Integer.MAX_VALUE; i++) {
            OSProcess p = procs.get(i);
            oshi.add(String.format("%5d,%s", p.getProcessID(), p.getName()));
        }
        return oshi;
    }   
}