/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package getGame.DAO;
import org.springframework.jdbc.core.JdbcTemplate;
/**
 *
 * @author Aluno
 */
public class RamDAO {
    
        Conexao dadosConexao = new Conexao();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dadosConexao.getDataSource());
    
        
        
}
