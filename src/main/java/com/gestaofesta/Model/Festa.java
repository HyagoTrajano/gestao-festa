package com.gestaofesta.Model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Table(name = "tb_festa")
@Getter
@Setter
public class Festa implements Serializable{

	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_festa;

    @NonNull
    private String tipo_festa;
    
    @NonNull
    private String nome_festa;
    
    @NonNull
    private String sala_festa;
    
    @NonNull
    private String data_festa;
    
    @NonNull
    private String hora_festa;

    @OneToMany
    @JoinColumn(name = "id_festa_responsavel")
    private List<Responsavel> responsavel;

    /* Foi relaizado o getter e o setter dos atributos com a dependencia lombok com o (@Getter) e o (@Setter) */

}
