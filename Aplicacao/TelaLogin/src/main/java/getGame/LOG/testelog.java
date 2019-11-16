/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package getGame.LOG;

import getGame.Model.Disco;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Guilherme
 */
public class testelog {
    public static void main(String[] args) throws IOException {
           
        GeracaoLog geracao = new GeracaoLog();
        
        DataHora datahora = new DataHora();
        
   geracao.gerarPasta();
 
   geracao.escritaArquivo();
     
   
        System.out.println(geracao.gerarPasta());
        System.out.println(datahora.gerarDataHora());
        

    }
}
