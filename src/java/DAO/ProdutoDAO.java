/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import VO.ProdutoVO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Programador
 */
public class ProdutoDAO {

    public static void inserir(ProdutoVO produto) throws SQLException {
        Connection cx = ConexaoBD.getConexao();
        try {
            produto.setId(getNexId());

            String sql = "insert into Produto (produto_id, produto_nome, produto_preco, produto_descricao,"
                    + " produto_imagem1, produto_imagem2, produto_imagem3, categoria_id) "
                    + " values (?,?,?,?,?,?,?,?)";

            PreparedStatement p = cx.prepareStatement(sql);
            p.setInt(1, produto.getId());
            p.setString(2, produto.getNome());
            p.setDouble(3, produto.getPreco());
            p.setString(4, produto.getDescricao());
            p.setString(5, produto.getImagem1());
            p.setString(6, produto.getImagem2());
            p.setString(7, produto.getImagem3());
            p.setInt(8, produto.getCategoriaId());
            p.execute();
            p.close();
        } finally {
            cx.close();
        }
    }

    private static int getNexId() throws SQLException {
        Connection cx = ConexaoBD.getConexao();
        try {
            String sql = "select isnull(max(produto_id),0)+1 as 'proximo' from Produto";
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

    public static void alterar(ProdutoVO produto) throws SQLException {
        Connection cx = ConexaoBD.getConexao();
        try {
            String sql = "update Produto set produto_nome=?, produto_preco=?, produto_descricao=?, produto_imagem1=?,"
                    + " produto_imagem2=?, produto_imagem3=?, categoria_id=? where produto_id=?";

            PreparedStatement p = cx.prepareStatement(sql);
            p.setString(1, produto.getNome());
            p.setDouble(2, produto.getPreco());
            p.setString(3, produto.getDescricao());
            p.setString(4, produto.getImagem1());
            p.setString(5, produto.getImagem2());
            p.setString(6, produto.getImagem3());
            p.setInt(7, produto.getCategoriaId());
            p.setInt(8, produto.getId());
            p.execute();
            p.close();
        } finally {
            cx.close();
        }
    }

    public static void deletar(int id) throws SQLException {
        Connection cx = ConexaoBD.getConexao();
        try {
            String sql = "delete Produto where produto_id=?";
            PreparedStatement p = cx.prepareStatement(sql);
            p.setInt(1, id);
            p.execute();
            p.close();
        } finally {
            cx.close();
        }
    }

    public static List<ProdutoVO> listagem() throws SQLException {
        return listagem(0, "", false, 0);
    }

    public static List<ProdutoVO> listagem(int idCategoria, String filtroProduto,  
            boolean randomico, int quantidade) throws SQLException {
         Connection cx = ConexaoBD.getConexao();
        try {
            String sql; 
            String where = " where produto_nome like '%" + filtroProduto + "%' ";
            if (idCategoria != 0)
                where += " and Produto.categoria_id = " + idCategoria;
            
            if (randomico) {
                sql = "select top " + quantidade + " Produto.*, " + 
                      "Categoria.categoria_nome from Produto " + 
                      "inner join Categoria on Produto.categoria_id = Categoria.categoria_id " +
                      where + " " +
                      "order by NEWID()";
            }
            else                
            {
                sql = "select Produto.*, Categoria.categoria_nome  from Produto " + 
                    "inner join Categoria on Produto.categoria_id = Categoria.categoria_id " +
                     where + " " +
                    "order by Produto.produto_id";            
            }

            PreparedStatement p = cx.prepareStatement(sql);
            ResultSet rs = p.executeQuery();

            List<ProdutoVO> lista = new ArrayList<ProdutoVO>();

            while (rs.next()) {
                lista.add(MontaProduto(rs));
            }
            p.close();
            return lista;
        } finally {
            cx.close();
        }
    }


    private static ProdutoVO MontaProduto(ResultSet rs) throws SQLException {
        ProdutoVO p = new ProdutoVO();
        p.setId(rs.getInt("produto_id"));
        p.setNome(rs.getString("produto_nome"));
        p.setPreco(rs.getDouble("produto_preco"));
        p.setDescricao(rs.getString("produto_descricao"));
        p.setImagem1(rs.getString("produto_imagem1"));
        p.setImagem2(rs.getString("produto_imagem2"));
        p.setImagem3(rs.getString("produto_imagem3"));
        p.setCategoriaId(rs.getInt("categoria_id"));        
        p.setNomeCategoria(rs.getString("categoria_nome") ); 

        return p;
    }

    public static ProdutoVO consulta(int id) throws SQLException {
        Connection cx = ConexaoBD.getConexao();
        try {
            String sql = "select Produto.*, Categoria.categoria_nome  from Produto " + 
                    "inner join Categoria on Produto.categoria_id = Categoria.categoria_id " +
                    "where Produto.produto_id = ?";            
            PreparedStatement p = cx.prepareStatement(sql);
            p.setInt(1, id);
            ResultSet rs = p.executeQuery();
            ProdutoVO po = null;
            if (rs.next()) {
                po = MontaProduto(rs);
            }
            p.close();
            return po;
        } finally {
            cx.close();
        }
    }

}
