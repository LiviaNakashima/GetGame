/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package getGame.LOG;

import getGame.Model.CPU;
import getGame.Model.Disco;
import getGame.Model.Ram;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Guilherme
 */
public class GeracaoLog {
    
    public boolean gerarPasta() throws IOException {
       
        File pasta = new File("C:\\getGameLogs2\\");
        
       boolean pastaCriada = pasta.mkdir();
       
       File arquivo = new File("C:\\getGameLogs2\\log.txt");
       
       boolean arquivoCriado = arquivo.createNewFile();
       
       return arquivoCriado;
         
    }
    
    public PrintWriter escritaArquivo() throws IOException, InterruptedException{
       
        
        int i;
        Disco disco = new Disco();
        Ram ram = new Ram();
        CPU cpu = new CPU();
        DataHora dataHora = new DataHora();
        
          
      PrintWriter editarArquivo = new PrintWriter("C:\\getGameLogs2\\log.txt");
      
     // String formatacaoTexto = String.format("%s | Disco: %s | Memória RAM: %s | Uso de CPU: %.2f " , dataHora.gerarDataHora(), disco.getDisco(), ram.getRAMFormatado(), cpu.getCPUUsada());
      
     // editarArquivo.printf(formatacaoTexto);
      
     // editarArquivo.close();
        
     
      for (i=1; i<=5; i++, TimeUnit.SECONDS.sleep(6)) {
       
          
          
           String formatacaoTexto = String.format("%s | Disco: %s | Memória RAM: %s | Uso de CPU: %.2f " , dataHora.gerarDataHora(), disco.getDisco(), ram.getRAMFormatado(), cpu.getCPUUsada());
            
            editarArquivo.println("");
            editarArquivo.println(formatacaoTexto);
          
            
            
      }
      
      editarArquivo.close();
      
      return editarArquivo;
        
    }

}
