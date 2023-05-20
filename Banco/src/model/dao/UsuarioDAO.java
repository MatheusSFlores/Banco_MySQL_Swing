/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import com.mysql.cj.protocol.Resultset;
import connection.connectionUser;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Usuario;

/**
 *
 * @author mathe
 */
public class UsuarioDAO {

    public void create(Usuario usuario) {

        Connection con = connectionUser.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO usuario (nome, cpf, celular, saldo)VALUES (?, ?, ?, ?)");
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getCpf());
            stmt.setString(3, usuario.getCelular());
            stmt.setDouble(4, usuario.getSaldo());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Dados Salvos!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Salvar!" + ex);
        } finally {
            connectionUser.closeConnection(con, stmt);
        }

    }

    public List<Usuario> read() {

        Connection con = connectionUser.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Usuario> listUsuario = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM usuario");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setCpf(rs.getString("cpf"));
                usuario.setCelular(rs.getString("celular"));
                usuario.setSaldo(rs.getDouble("saldo"));
                listUsuario.add(usuario);

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao Listar Usuarios!" + ex);
        } finally {
            connectionUser.closeConnection(con, stmt, rs);
        }
        return listUsuario;
    }

    public List<Usuario> getUsuario(String cpf) {
        Connection con = connectionUser.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Usuario> dadosUsuario = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM usuario WHERE cpf = ? ");
            stmt.setString(1, cpf);
            rs = stmt.executeQuery();
            Usuario usuario = new Usuario();
            if (rs.next()) {
                usuario.setNome(rs.getString("nome"));
                usuario.setCpf(cpf);
                usuario.setCelular(rs.getString("celular"));
                usuario.setSaldo(rs.getDouble("saldo"));
                dadosUsuario.add(usuario);
                return dadosUsuario;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Buscar Usuario!" + ex);
        } finally {
            connectionUser.closeConnection(con, stmt, rs);
        }
        return dadosUsuario;
    }

    /*Não Implementado por falta de necessidade S2
    public void update(Usuario usuario) {

        Connection con = connectionUser.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE usuario SET nome = ?, cpf = ?, celular = ?, saldo = ? WHERE id = ?");
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getCpf());
            stmt.setString(3, usuario.getCelular());
            stmt.setDouble(4, usuario.getSaldo());
            stmt.setInt(5, usuario.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Dados Atualizados!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Atualizar!" + ex);
        } finally {
            connectionUser.closeConnection(con, stmt);
        }

    }*/
    public void delete(String cpf) {

        Connection con = connectionUser.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM usuario WHERE cpf = ?");
            stmt.setString(1, cpf);

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Dados Deletados!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Deletar!" + ex);
        } finally {
            connectionUser.closeConnection(con, stmt);
        }

    }

    public void saldoAddUpdate(String cpf, double dinheiro) {

        Connection con = connectionUser.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE usuario SET saldo = saldo + ? WHERE cpf = ?");
            stmt.setDouble(1,dinheiro);
            stmt.setString(2, cpf);
            
            

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Dados Atualizados!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Atualizar!" + ex);
        } finally {
            connectionUser.closeConnection(con, stmt);
        }

    }
    public void saldoSubUpdate(String cpf, double dinheiro) {

        Connection con = connectionUser.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE usuario SET saldo = saldo - ? WHERE cpf = ?");
            stmt.setDouble(1,dinheiro);
            stmt.setString(2, cpf);
            
            

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Dados Atualizados!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Atualizar!" + ex);
        } finally {
            connectionUser.closeConnection(con, stmt);
        }

    }
    public void celUpdate(String cpf, String celular) {

        Connection con = connectionUser.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE usuario SET celular = ? WHERE cpf = ?");
            stmt.setString(1,celular);
            stmt.setString(2, cpf);
            
            

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Dados Atualizados!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Atualizar!" + ex);
        } finally {
            connectionUser.closeConnection(con, stmt);
        }

    }

    public boolean checkLogin(String login, String pass) {

        Connection con = connectionUser.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean check = false;

        try {
            stmt = con.prepareStatement("SELECT * FROM usuario WHERE nome = ? and cpf = ?");
            stmt.setString(1, login);
            stmt.setString(2, pass);

            rs = stmt.executeQuery();

            if (rs.next()) {

                check = true;

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Atualizar!" + ex);
        }
        return (check);
    }
}
