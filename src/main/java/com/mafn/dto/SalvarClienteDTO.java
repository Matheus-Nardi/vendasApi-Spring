package com.mafn.dto;

public class SalvarClienteDTO {
    private String nome;

    public SalvarClienteDTO() {
        
    }
    public SalvarClienteDTO(String nome) {
        this.nome = nome;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    
}
