/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package getGame.LOG;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Guilherme
 */
public class GeracaoLog {
    
    public boolean gerarPasta() {
       
        File pasta = new File("C:\\getGameLogs\\");
        
       boolean pastaCriada = pasta.mkdir();
       
        System.out.println(pastaCriada);
       
       return pastaCriada;
        
    }

public boolean gerarArquivoTXT() throws IOException {
    
    
    
    File arquivo = new File("C:\\getGameLogs\\teste.txt");
    
    boolean arquivoTXT = arquivo.createNewFile();
    
    return arquivoTXT;
    
}    
    
}
