/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VO;

/**
 *
 * @author Programador
 */
public class ProdutoVO {
    
    private int id;
    private String nome;
    private double preco;
    private String descricao;
    private String imagem1;
    private String imagem2;
    private String imagem3;
    private int categoriaId;
    
     private String nomeCategoria;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the preco
     */
    public double getPreco() {
        return preco;
    }

    /**
     * @param preco the preco to set
     */
    public void setPreco(double preco) {
        this.preco = preco;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the imagem1
     */
    public String getImagem1() {
        return imagem1;
    }

    /**
     * @param imagem1 the imagem1 to set
     */
    public void setImagem1(String imagem1) {
        this.imagem1 = imagem1;
    }

    /**
     * @return the imagem2
     */
    public String getImagem2() {
        return imagem2;
    }

    /**
     * @param imagem2 the imagem2 to set
     */
    public void setImagem2(String imagem2) {
        this.imagem2 = imagem2;
    }

    /**
     * @return the imagem3
     */
    public String getImagem3() {
        return imagem3;
    }

    /**
     * @param imagem3 the imagem3 to set
     */
    public void setImagem3(String imagem3) {
        this.imagem3 = imagem3;
    }
    
     /**
     * @return the categoriaId
     */
    public int getCategoriaId() {
        return categoriaId;
    }

    /**
     * @param categoriaId the categoriaId to set
     */
    public void setCategoriaId(int categoriaId) {
        this.categoriaId = categoriaId;
    }

    /**
     * @return the descricaoCategoria
     */
    public String getNomeCategoria() {
        return nomeCategoria;
    }

    /**
     * @param descricaoCategoria the descricaoCategoria to set
     */
    public void setNomeCategoria(String descricaoCategoria) {
        this.nomeCategoria = descricaoCategoria;
    }
    
}
