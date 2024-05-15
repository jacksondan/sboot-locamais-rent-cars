package br.com.locamais.sbootlocamaisrentcars.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@Table(name = "TB_VEHICLES")
public class VeiculoModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idVeiculo;
    private String nomeVeiculo;
    private String tipoVeiculo;
    private Boolean alugado;
    private String status;
    private String marca;
    private String placa;
    private String chassi;
    private Double valorTabela;
}
