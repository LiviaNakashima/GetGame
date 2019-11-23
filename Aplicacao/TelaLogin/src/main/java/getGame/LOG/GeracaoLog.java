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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Guilherme
 */
public class GeracaoLog {

    public boolean gerarPasta() throws IOException {

        File pasta = new File("C:\\getGameLogs\\");

        boolean pastaCriada = pasta.mkdir();

        File arquivo = new File("C:\\getGameLogs\\logGetGame.txt");

        boolean arquivoCriado = arquivo.createNewFile();

        return arquivoCriado;

    }

    public FileOutputStream escritaArquivo(String log) throws IOException, InterruptedException {

        if (log.charAt(0) == 'h' || log.charAt(0) == 'j') {
            return new FileOutputStream("C:\\getGameLogs\\logGetGame.txt");
        } else {
            int i;
            DataHora dataHora = new DataHora();
            FileOutputStream fout = new FileOutputStream("C:\\getGameLogs\\logGetGame.txt");
            PrintStream print = new PrintStream(new File("C:\\getGameLogs\\logGetGame.txt"));

            // PrintWriter editarArquivo = new PrintWriter("C:\\getGameLogs2\\log.txt");
            // String formatacaoTexto = String.format("%s | Disco: %s | Memória RAM: %s | Uso de CPU: %.2f " , dataHora.gerarDataHora(), disco.getDisco(), ram.getRAMFormatado(), cpu.getCPUUsada());
            // editarArquivo.printf(formatacaoTexto);
            // editarArquivo.close();
            for (i = 1; i <= 40; i++, TimeUnit.SECONDS.sleep(10)) {
                String formatacaoTexto = String.format("%s ", dataHora.gerarDataHora());

                System.out.println("");
                System.out.println(formatacaoTexto + log);

                System.setOut(print);
                //     editarArquivo.println("");
                //   editarArquivo.println(formatacaoTexto);
            }

            // editarArquivo.close();
            print.close();
            fout.close();

            return fout;
        }
    }
}
