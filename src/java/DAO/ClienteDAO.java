/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import VO.ClienteVO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Programador
 */
public class ClienteDAO {
    
    public static void insere(ClienteVO c) throws SQLException {

        
        Connection cx = ConexaoBD.getConexao();
        try {
            c.setId(getNexId());
            String sql = "insert into Cliente (cliente_id, cliente_nome, cliente_dtnasc, cliente_cpf,"
                    + "cliente_telefone, cliente_endereco, cliente_email, cliente_senha) values (?,?,?,?,?,?,?,?)";

            PreparedStatement cmd = cx.prepareStatement(sql);
            cmd.setInt(1, c.getId());
            cmd.setString(2, c.getNome());
            cmd.setDate(3, Date.valueOf(c.getDataNascimento()));
            cmd.setString(4, c.getCpf());
            cmd.setString(5, c.getTelefone());
            cmd.setString(6, c.getEndereco());
            cmd.setString(7, c.getEmail());
            cmd.setString(8, c.getSenha());
            cmd.execute();
            cmd.close();
        } finally {
            cx.close();
        }
    }
    
     private static int getNexId() throws SQLException {
        Connection cx = ConexaoBD.getConexao();
        try {
            String sql = "select isnull(max(cliente_id),0)+1 as 'proximo' from Cliente";
            PreparedStatement p = cx.prepareStatement(sql);
            ResultSet rs = p.executeQuery();
            rs.next();
            int id = rs.getInt("proximo");
            p.close();
            return id;
        } finally {
            cx.close();
        }
    }

    public static void altera(ClienteVO c) throws SQLException {
        Connection cx = ConexaoBD.getConexao();
        try {
            String sql = "update Cliente set cliente_nome = ?, cliente_dtnasc = ?, cliente_cpf = ?, "
                    + "cliente_telefone = ?, cliente_endereco = ?, cliente_email = ?, cliente_senha = ? where cliente_id = ?";

            PreparedStatement cmd = cx.prepareStatement(sql);
            
            cmd.setString(1, c.getNome());
            cmd.setDate(2, Date.valueOf(c.getDataNascimento()));
            cmd.setString(3, c.getCpf());
            cmd.setString(4, c.getTelefone());
            cmd.setString(5, c.getEndereco());
            cmd.setString(6, c.getEmail());
            cmd.setString(7, c.getSenha());
            cmd.setInt(8, c.getId());
            cmd.execute();
            cmd.close();
        } finally {
            cx.close();
        }
    }

    public static void delete(int id) throws SQLException {
        Connection cx = ConexaoBD.getConexao();
        try {
            String sql = "delete Cliente where cliente_id = ?";

            PreparedStatement cmd = cx.prepareStatement(sql);
            cmd.setInt(1, id);
            cmd.execute();
            cmd.close();
        } finally {
            cx.close();
        }
    }

    public static List<ClienteVO> listagem() throws SQLException {
        Connection cx = ConexaoBD.getConexao();
        List<ClienteVO> lista = new ArrayList();
        try {
            String sql = "select * from Cliente order by cliente_id";

            PreparedStatement cmd = cx.prepareStatement(sql);
            ResultSet rs = cmd.executeQuery();

            while (rs.next() == true) {
                lista.add(montaCliente(rs));
            }
            cmd.close();
            return lista;
        } finally {
            cx.close();
        }
    }

    private static ClienteVO montaCliente(ResultSet rs) throws SQLException {
        ClienteVO c = new ClienteVO();
        c.setId(rs.getInt("cliente_id"));
        c.setNome(rs.getString("cliente_nome"));
        c.setDataNascimento(rs.getDate("cliente_dtnasc").toLocalDate());
        c.setCpf(rs.getString("cliente_cpf"));
        c.setTelefone(rs.getString("cliente_telefone"));
        c.setEndereco(rs.getString("cliente_endereco"));
        c.setEmail(rs.getString("cliente_email"));
        c.setSenha(rs.getString("cliente_senha"));
        return c;
    }
    
    public static ClienteVO consulta(int id) throws SQLException {
        Connection cx = ConexaoBD.getConexao();
        List<ClienteVO> lista = new ArrayList();
        try {
            String sql = "select * from Cliente where cliente_id = ?";

            PreparedStatement cmd = cx.prepareStatement(sql);
            cmd.setInt(1, id);
            ResultSet rs = cmd.executeQuery();
            ClienteVO c = null;
            if (rs.next() == true) {
                c = montaCliente(rs);
                cmd.close();
            }
            return c;
        } finally {
            cx.close();
        }
    }
    
    public static ClienteVO validaLogin(String email,String senha) throws SQLException{
        Connection cx = ConexaoBD.getConexao();
        
        try{
            String sql = "select * from Cliente where cliente_email = "+ "'" + email + "' and cliente_senha='"+senha+"'";
            PreparedStatement cmd = cx.prepareStatement(sql);
            ResultSet rs = cmd.executeQuery();
            ClienteVO c= null;
            if(rs.next()){
                c = montaCliente(rs);
                cmd.close();
            }
            return c;
        } finally {
            cx.close();
        }
    }
    
}
