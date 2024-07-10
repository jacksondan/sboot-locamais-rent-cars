package br.com.locamais.sbootlocamaisrentcars.service.impl;

import br.com.locamais.rent.cars.representation.RequestInsertVeiculo;
import br.com.locamais.rent.cars.representation.RequestUpdateVeiculo;
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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static br.com.locamais.sbootlocamaisrentcars.util.Validator.uuidValid;

@Slf4j
@RequiredArgsConstructor
@Service
public class RentCarsServiceImpl implements RentCarsService {

    private final VeiculoRepository repository;
    private final ModelMapper mapper;
    private int i = 0;

    @Override
    public ResponseSuccess salvarVeiculo(RequestInsertVeiculo requestInsertVeiculo) {
        var veiculo = mapper.map(requestInsertVeiculo, VeiculoModel.class);
        veiculo.setAlugado(i % 2 == 0);
        i++;
        var response = repository.save(veiculo);
        return new ResponseSuccess()
                .idVeiculo(response.getIdVeiculo().toString())
                .message("Veículo cadastrado")
                .statusCode(201);
    }

    @Override
    public ResponseSuccess alterarCompletaVeiculo(String idVeiculo, RequestUpdateVeiculo requestUpdateVeiculo) {
        var request = repository.findById(uuidValid(idVeiculo));
        if (request.isPresent()) {
            var veiculo = request.get();
            completeRequestUpdateVeiculo(veiculo, requestUpdateVeiculo);
            var response = repository.save(veiculo);
            return new ResponseSuccess()
                    .idVeiculo(response.getIdVeiculo().toString())
                    .message("Veículo alterado")
                    .statusCode(200);
        }else {
            var message = "Registro não encontrado para o id: "+idVeiculo;
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND,message);
        }
    }

    @Override
    public ResponseSuccess alterarParcialVeiculo(String idVeiculo, RequestUpdateVeiculo requestUpdateVeiculo) {
        var request = repository.findById(uuidValid(idVeiculo));
        if (request.isPresent()) {
            var veiculo = request.get();
            parcialRequestUpdateVeiculo(veiculo, requestUpdateVeiculo);
            var response = repository.save(veiculo);
            return new ResponseSuccess()
                    .idVeiculo(response.getIdVeiculo().toString())
                    .message("Veículo alterado")
                    .statusCode(200);
        } else {
            var message = "Registro não encontrado para o id: "+idVeiculo;
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND,message);
        }

    }

    @Override
    public void apagarVeiculo(String idVeiculo) {
        var request = repository.findById(uuidValid(idVeiculo));
        if (request.isPresent()) {
            repository.delete(request.get());
        } else {
            var message = "Registro não encontrado para o id: "+idVeiculo;
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND,message);
        }
    }

    @Override
    public ResponseGetVeiculo buscarVeiculo(String idVeiculo) {
        var response = repository.findById(UUID.fromString(idVeiculo));
        return mapper.map(response.get(), ResponseGetVeiculo.class);
    }

    @SneakyThrows
    @Override
    public List<ResponseGetVeiculo> listarVeiculos() {
        var response = repository.findAll();
        return mapper.map(response,new TypeToken<List<ResponseGetVeiculo>>() {}.getType());
    }

    @Override
    public List<ResponseGetVeiculo> listarVeiculosAlugados() {
        var response = repository.findAllByAlugadoTrue();
        if (response.isEmpty())
            throw new HttpClientErrorException(HttpStatus.NO_CONTENT);
        return mapper.map(response,new TypeToken<List<ResponseGetVeiculo>>() {}.getType());
    }

    @Override
    public List<ResponseGetVeiculo> listarVeiculosNaoAlugados() {
        var response = repository.findAllByAlugadoFalse();
        if (response.isEmpty())
            throw new HttpClientErrorException(HttpStatus.NO_CONTENT);
        return mapper.map(response,new TypeToken<List<ResponseGetVeiculo>>() {}.getType());
    }

    private void completeRequestUpdateVeiculo(VeiculoModel veiculo, RequestUpdateVeiculo requestUpdateVeiculo){
        veiculo.setNomeVeiculo(requestUpdateVeiculo.getNomeVeiculo());
        veiculo.setTipoVeiculo(requestUpdateVeiculo.getTipoVeiculo());
        veiculo.setMarca(requestUpdateVeiculo.getMarca());
        veiculo.setPlaca(requestUpdateVeiculo.getPlaca());
        veiculo.setChassi(requestUpdateVeiculo.getChassi());
        if(Objects.nonNull(requestUpdateVeiculo.getValorTabela())) {
            veiculo.setValorTabela(requestUpdateVeiculo.getValorTabela().doubleValue());
        }else {
            veiculo.setValorTabela(null);
        }
    }

    private void parcialRequestUpdateVeiculo(VeiculoModel veiculo, RequestUpdateVeiculo requestUpdateVeiculo){
        if(Objects.nonNull(requestUpdateVeiculo.getNomeVeiculo()))
            veiculo.setNomeVeiculo(requestUpdateVeiculo.getNomeVeiculo());
        if(Objects.nonNull(requestUpdateVeiculo.getTipoVeiculo()))
            veiculo.setTipoVeiculo(requestUpdateVeiculo.getTipoVeiculo());
        if(Objects.nonNull(requestUpdateVeiculo.getMarca()))
            veiculo.setMarca(requestUpdateVeiculo.getMarca());
        if(Objects.nonNull(requestUpdateVeiculo.getPlaca()))
            veiculo.setPlaca(requestUpdateVeiculo.getPlaca());
        if(Objects.nonNull(requestUpdateVeiculo.getChassi()))
            veiculo.setChassi(requestUpdateVeiculo.getChassi());
        if(Objects.nonNull(requestUpdateVeiculo.getValorTabela()))
            veiculo.setValorTabela(requestUpdateVeiculo.getValorTabela().doubleValue());
    }
}
