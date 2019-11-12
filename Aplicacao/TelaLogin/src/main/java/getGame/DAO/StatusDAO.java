/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package getGame.DAO;

import java.time.LocalDate;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;


/**
 *
 * @author eduab
 */
public class StatusDAO {
    private JdbcTemplate jdbcTemplate;
    
    public boolean inserirStatusServidor
        (Float cpu, Float ram, Float disco, String status, String tempoOff, Integer codServidor, LocalDate data){
        
        BasicDataSource dataSource = new BasicDataSource();

        jdbcTemplate = new JdbcTemplate(dataSource);
        
        jdbcTemplate.update("insert into tbStatusServidor (cpuStatusServidor, ramStatusServidor, discoStatusServidor"
                + " discoStatusServidor, situacaoStatusServidor, tempoOffStatusServidor, codServidor,"
                + " dataHoraStatusServidor) VALUES (?, ?, ?, ?, ?, ?, ?)", 
                cpu, ram, disco, status, tempoOff, codServidor, data);
        
        return false;
    }

    
}
