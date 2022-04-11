package com.gestaofesta.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;


@Entity
@Table(name = "tb_convidado")
@Getter
@Setter
public class Convidado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_convidado;

    @NonNull
    private String nome_convidado;
    @NonNull
    private String rg_convidado_n_convite;

    @ManyToOne
    @JoinColumn(name = "id_responsavel_convidado")
    private Responsavel responsavel;
    /* Foi relaizado o getter e o setter dos atributos com a dependencia lombok com o (@Getter) e o (@Setter) */
}
