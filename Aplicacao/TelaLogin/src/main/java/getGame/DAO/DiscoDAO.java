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
    
    private JdbcTemplate jdbcTemplate;
    
    public void inserirDisco(String porcentagemDisco) {
        BasicDataSource dataSource = new BasicDataSource();

        jdbcTemplate = new JdbcTemplate(dataSource);
        
        jdbcTemplate.update("insert into tbStatusServidor (discoStatusServidor) VALUES (?)", porcentagemDisco);
        
    }
    
}
