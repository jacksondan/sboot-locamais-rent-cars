package br.com.locamais.sbootlocamaisrentcars.infrastructure;

import br.com.locamais.sbootlocamaisrentcars.model.VeiculoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VeiculoRepository extends JpaRepository<VeiculoModel, UUID> {
}
