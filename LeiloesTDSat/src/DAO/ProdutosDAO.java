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
    PreparedStatement ps;
    ResultSet rs;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    public void cadastrarProduto(ProdutosDTO p) {
        try {
            conectaDAO c = new conectaDAO();
            String sql = "INSERT INTO `produtos`(`nome`, `valor`, `status`) VALUES (?,?,?)";
            ps = c.getConexao().prepareStatement(sql);
            ps.setString(1, p.getNome());
            ps.setInt(2, p.getValor());
            ps.setString(3, p.getStatus());
            ps.execute();
            ps.close();
            JOptionPane.showMessageDialog(null, p.getNome() + " cadastratdo com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto " + ex.getMessage());
        }

    }

    public ArrayList<ProdutosDTO> listarProdutos() {
        try {
            conectaDAO c = new conectaDAO();
            String sql = "SELECT * FROM `produtos`";
            ps = c.getConexao().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ProdutosDTO p = new ProdutosDTO();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setValor(rs.getInt("valor"));
                p.setStatus(rs.getString("status"));
                listagem.add(p);
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao Listar dados" + e.getMessage());
        }
        return listagem;
    }

    public void venderProduto(int id) {
        try {
            conectaDAO c = new conectaDAO();
            String sql = "UPDATE `produtos` AS p SET p.status = 'vendido' WHERE p.id = " + id;
            ps = c.getConexao().prepareStatement(sql);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
           JOptionPane.showMessageDialog(null, "Erro ao vender produto " + e.getMessage());
        }

    }

}
