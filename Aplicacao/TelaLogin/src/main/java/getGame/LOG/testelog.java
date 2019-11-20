/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package getGame.LOG;


import java.io.IOException;
import java.time.LocalDate;

/**
 *
 * @author Guilherme|
 */
public class testelog {
    public static void main(String[] args) throws IOException, InterruptedException {
         
     //   TesteDeNovo teste = new TesteDeNovo();
        
      //  LocalDate date = LocalDate.now();
        
       // System.out.println(date);
        
        GeracaoLog geracao = new GeracaoLog();
        
       // DataHora datahora = new DataHora();
        
  geracao.gerarPasta();
 
   geracao.escritaArquivo();
   
   
     //   System.out.println(geracao.gerarPasta());
       // System.out.println(datahora.gerarDataHora());
      
      // teste.TesteDeNovo();

    }
}
