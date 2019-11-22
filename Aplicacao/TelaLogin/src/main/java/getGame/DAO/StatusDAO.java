/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package getGame.DAO;

import java.time.LocalDate;
import org.springframework.jdbc.core.JdbcTemplate;


/**
 *
 * @author eduab
 */
public class StatusDAO {
    private JdbcTemplate jdbcTemplate;
    
    public boolean inserirStatusServidor
        (Float cpu, Float ram, Float disco, String status, String tempoOff, Integer codServidor, LocalDate data){
        Conexao conect = new Conexao();
        
        jdbcTemplate = new JdbcTemplate(conect.getDataSource());
        
        try {
            jdbcTemplate.update("insert into tbStatusServidor(cpuStatusServidor, ramStatusServidor, discoStatusServidor, situacaoStatusServidor, tempoOffStatusServidor, codServidor, dataHoraStatusServidor)"
                                + "values(?, ?, ?, ?, ?, ?, ?)", 
                    cpu.toString().replace(",", ".").substring(0, 5), ram.toString().replace(",", ".").substring(0, 5), 
                    disco.toString().replace(",", ".").substring(0, 5), status, tempoOff, codServidor, data);
        } catch (Exception e) {
            System.out.println(e);
        }
        return true;
    }

    
}
