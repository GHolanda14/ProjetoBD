
package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Conexao {
    private Connection conexao;
    public Statement statement;
    public ResultSet resultset;
    public boolean conecta() {
        boolean result = true;
        try {
            Class.forName("org.postgresql.Driver");
            conexao = (Connection) DriverManager.getConnection("jdbc:postgresql://localhost:5432/Trabalho_Final" , "postgres","fbd");
        } catch (ClassNotFoundException | SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar "+erro);
            result = false;
        }        
        return result;
    }
    public void desconecta() {
        boolean result = true;
        try {
            conexao.close();
        } catch (SQLException erroSQL) {
           result = false;
        }
    }
      public void ConsultaSQL(String sql){
            try
            {                 
                statement = conexao.createStatement(
                ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
                resultset = statement.executeQuery(sql); 
            }
            catch (SQLException sqlex)
            {
                 JOptionPane.showMessageDialog(null, "Não foi possível"+
                        "executar o comando"+sqlex+", o sql passado foi"+sql);
                
            }

        }
}
