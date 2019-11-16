/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package getGame.LOG;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Guilherme
 */
public class DataHora {
    
    public String gerarDataHora() {
    
            Date data = GregorianCalendar.getInstance().getTime();
          //  SimpleDateFormat format = new SimpleDateFormat();
            
            String dataHora = String.format("%s ", data );
            
            return dataHora;
}
     
}
