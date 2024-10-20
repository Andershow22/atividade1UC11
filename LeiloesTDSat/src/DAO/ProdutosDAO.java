package DAO;



import MODEL.ProdutosDTO;
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO p){
        try {
            conectaDAO c = new conectaDAO();
        String sql = "INSERT INTO `produtos`(`nome`, `valor`, `status`) VALUES (?,?,?)";
            PreparedStatement ps = c.getConexao().prepareStatement(sql);
            ps.setString(1, p.getNome());
            ps.setInt(2, p.getValor());
            ps.setString(3, p.getStatus());
            ps.execute();
            ps.close();
            JOptionPane.showMessageDialog(null, "cadastratdo com sucesso ");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto " + ex.getMessage());
        }
        
        
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        return listagem;
    }
        
}

