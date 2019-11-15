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
public class testelog {
    public static void main(String[] args) throws IOException {
           
        GeracaoLog geracao = new GeracaoLog();
        
        geracao.gerarPasta();
        geracao.gerarArquivoTXT();

    }
}
