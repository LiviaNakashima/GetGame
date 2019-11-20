/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package getGame.LOG;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 *
 * @author Guilherme
 */
public class TesteDeNovo {
        
    FileOutputStream fout ;

    public FileOutputStream TesteDeNovo() throws IOException, FileNotFoundException {
    //    FileOutputStream /*fout*/ ;
        
        this.fout = new FileOutputStream("C:\\getGameLogs2\\teste.txt");
        
        PrintStream print = new PrintStream(new File("C:\\getGameLogs2\\teste.txt"));
        
        System.setOut(print);
        
        System.out.println("lalalalala");
        
        return this.fout;
    }
    
}
