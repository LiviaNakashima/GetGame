/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package getGame.DAO;

import getGame.Model.Disco;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Aluno
 */
public class DiscoDAO {
    
    Disco disco = new Disco();
    
    
    
    private JdbcTemplate jdbcTemplate;
    
    public void inserirDisco(Float calculoPorcentagemEspacoUsado) {
    BasicDataSource dataSource = new BasicDataSource();
        
    String insertBanco = String.format("insert into tbStatusServidor (discoStatusServidor) VALUES (%s)", disco.getEspacoUsado());

       jdbcTemplate = new JdbcTemplate(dataSource);
        
      jdbcTemplate.update(insertBanco);
        
    }
    
}
