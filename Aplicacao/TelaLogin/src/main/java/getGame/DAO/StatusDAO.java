/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package getGame.DAO;

import java.time.LocalDateTime;
import org.springframework.jdbc.core.JdbcTemplate;


/**
 *
 * @author eduab
 */
public class StatusDAO {
    private JdbcTemplate jdbcTemplate;
    Conexao conect = new Conexao();
    
    public String inserirStatusServidor
        (Float cpu, Float ram, Float disco, String status, Integer codServidor, LocalDateTime data){
        
        jdbcTemplate = new JdbcTemplate(conect.getDataSource());
        
        try {
            jdbcTemplate.update("insert into tbStatusServidor(cpuStatusServidor, ramStatusServidor, discoStatusServidor, situacaoStatusServidor, codServidor, dataHoraStatusServidor)"
                                + "values(?, ?, ?, ?, ?, ?)", 
                    cpu.toString().replace(",", ".").substring(0, 5), ram.toString().replace(",", ".").substring(0, 5), 
                    disco.toString().replace(",", ".").substring(0, 5), status, codServidor, data);
        } catch (Exception e) {
            String excessao = e.toString();
            return excessao;
        }
        
        return "Dados atuais de hardware registrados com sucesso";
    }
    
}
