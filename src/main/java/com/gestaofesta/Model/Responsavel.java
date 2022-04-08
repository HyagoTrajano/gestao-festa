package com.gestaofesta.Model;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "tb_responsavel_festa")
public class Responsavel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_responsavel;
    
    @NonNull
    private String nome_responsavel;
    
    @NonNull
    private String cpf_cnpj_responsavel;
    
    @NonNull
    private String telefone_reponsavel;
    
    @NonNull
    private String email_responsavel;

    @ManyToOne
    @JoinColumn(name = "id_festa_responsavel")
    private Festa festa;
    
    @OneToMany
    @JoinColumn(name = "id_responsavel_convidado")
    private List<Convidado> convidado;
    /* Foi relaizado o getter e o setter dos atributos com a dependencia lombok com o (@Getter) e o (@Setter) */
}
