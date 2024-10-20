package DAO;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class conectaDAO {
    private String url = "jdbc:mysql://localhost:3306/atv2uc11";
    private String user = "root";
    private String senha = "urso9090";
    private Connection con;
    
    public Connection connectDB(){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url,user, senha);
            return con;
        } catch (ClassNotFoundException |SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro ConectaDAO" + erro.getMessage());
            return null;
        }
    }
    public Connection getConexao(){
        if(con == null){
            con = connectDB();
            return con;
        }
        return con;
    }
    public void desconectar() throws SQLException{
        if(con != null && !con.isClosed()){
            con.close();
        }else{
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao desconectar");
        }
    }
    
}
