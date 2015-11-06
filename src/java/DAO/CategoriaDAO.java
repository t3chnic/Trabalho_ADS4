/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import VO.CategoriaVO;
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
public class CategoriaDAO {
    
    public static List<CategoriaVO> listagem() throws SQLException {
        Connection cx = ConexaoBD.getConexao();
        try {
            String sql = "select * from Categoria order by categoria_nome";

            PreparedStatement p = cx.prepareStatement(sql);
            ResultSet rs = p.executeQuery();

            List<CategoriaVO> lista = new ArrayList<CategoriaVO>();

            while (rs.next()) {
                lista.add(montaCategoria(rs));
            }
            p.close();
            return lista;
        } finally {
            cx.close();
        }
    }

    private static CategoriaVO montaCategoria(ResultSet rs) throws SQLException {
        CategoriaVO c = new CategoriaVO();
        c.setId(rs.getInt("categoria_id"));
        c.setNome(rs.getString("categoria_nome"));        
        return c;
    }
    
}
