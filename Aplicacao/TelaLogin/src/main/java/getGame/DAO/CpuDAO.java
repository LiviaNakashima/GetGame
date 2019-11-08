/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package getGame.DAO;

/**
 *
 * @author Aluno
 */
public class CpuDAO {
    
     public String inserirCPU() {
        //criar string para inserir 
        String sql = "insert into tbStatusServidor (cpuStatusServidor) VALUES(%s)";
        return sql;
     }
}
