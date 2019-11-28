/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package getGame.DAO;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author eduab
 */
public class ProcessosDAO {
    private JdbcTemplate jdbcTemplate;
    
    public void inserirProcessos(List<String> processos){
       Conexao conect = new Conexao();

       jdbcTemplate = new JdbcTemplate(conect.getDataSource());

       try {
           processos.forEach(processo ->{
               jdbcTemplate.update("insert into tbProcessoServidor( nomeProcessoServidor, codServidor)"
                               + "values(?, ?)", processo, 1);
           });
           
       } catch (Exception e) {
           System.out.println(e);
       }
    }
    
}
