package br.com.locamais.sbootlocamaisrentcars.service.impl;

import br.com.locamais.rent.cars.representation.RequestPatchVeiculo;
import br.com.locamais.rent.cars.representation.RequestPostVeiculo;
import br.com.locamais.rent.cars.representation.ResponseGetVeiculo;
import br.com.locamais.rent.cars.representation.ResponseSuccess;
import br.com.locamais.sbootlocamaisrentcars.infrastructure.VeiculoRepository;
import br.com.locamais.sbootlocamaisrentcars.model.VeiculoModel;
import br.com.locamais.sbootlocamaisrentcars.service.RentCarsService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class RentCarsServiceImpl implements RentCarsService {

    private final VeiculoRepository repository;
    private final ModelMapper mapper;

    @Override
    public ResponseSuccess salvarVeiculo(RequestPostVeiculo requestPostVeiculo) {
        var entity = mapper.map(requestPostVeiculo, VeiculoModel.class);
        var response = repository.save(entity);
        return new ResponseSuccess()
                .idVeiculo(response.getIdVeiculo().toString())
                .message("Veículo cadastrado")
                .statusCode(201);
    }

    @Override
    public ResponseSuccess alterarVeiculo(String idVeiculo, RequestPatchVeiculo requestPatchVeiculo) {
        var entity = mapper.map(requestPatchVeiculo,VeiculoModel.class);
        return null;
    }

    @Override
    public void apagarVeiculo(String idVeiculo) {

    }

    @Override
    public ResponseGetVeiculo buscarVeiculo(String idVeiculo) {
        var response = repository.getById(UUID.fromString(idVeiculo));
        return mapper.map(response,ResponseGetVeiculo.class);
    }

    @SneakyThrows
    @Override
    public List<ResponseGetVeiculo> listarVeiculos() {
        var response = repository.findAll();
        return mapper.map(response,new TypeToken<List<ResponseGetVeiculo>>() {}.getType());
    }

    @Override
    public List<ResponseGetVeiculo> listarVeiculosAlugados() {
        return List.of();
    }

    @Override
    public List<ResponseGetVeiculo> listarVeiculosNaoAlugados() {
        return List.of();
    }
}
