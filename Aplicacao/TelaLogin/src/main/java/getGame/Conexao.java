/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package getGame;

import org.apache.commons.dbcp2.BasicDataSource;

/**
 *
 * @author macos
 */
public class Conexao {

    private BasicDataSource dataSource;

    public Conexao() {
        dataSource​ =  new​ BasicDataSource();
        dataSource​.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dataSource​.setUrl("jdbc:sqlserver://srvgetgame.database.windows.net:1433;user=usergetgame;password=#Gfgrupo2b;databaseName=bdGrupo2");
        //dataSource​.setUrl("jdbc:sqlserver://srvgetgame.database.windows.net/bdGrupo2");

        // quem for acessar do yoshi: localhost -> 10.3.2.180
        // quem for acessar do yoshi: gaga -> gaga-seunome
        
        //dataSource​.setUsername("usergetgame");
        //dataSource​.setPassword("#Gfgrupo2b"); // ou sua senha
    }

    public BasicDataSource getDataSource() {
        return dataSource;
    }

}
