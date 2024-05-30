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
    @Column(name = "id_veiculo")
    private UUID idVeiculo;
    @Column(name = "nome_veiculo")
    private String nomeVeiculo;
    @Column(name = "tipo_veiculo")
    private String tipoVeiculo;
    private Boolean alugado;
    private String status;
    private String marca;
    private String placa;
    private String chassi;
    @Column(name = "valor_tabela")
    private Double valorTabela;
}
