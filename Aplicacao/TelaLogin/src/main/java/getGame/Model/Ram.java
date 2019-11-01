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
    
    public String getRAM() {
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();

        return printMemory(hal.getMemory());
    }
}
