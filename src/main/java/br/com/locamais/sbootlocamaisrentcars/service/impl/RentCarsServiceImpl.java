package br.com.locamais.sbootlocamaisrentcars.service.impl;

import br.com.locamais.rent.cars.representation.*;
import br.com.locamais.sbootlocamaisrentcars.service.RentCarsService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Slf4j
@Service
public class RentCarsServiceImpl implements RentCarsService {
    @Override
    public ResponseSuccess salvarVeiculo(RequestPostVeiculo requestPostVeiculo) {
        return new ResponseSuccess();
    }

    @Override
    public ResponseSuccess alterarVeiculo(String idVeiculo, RequestPatchVeiculo requestPatchVeiculo) {

        return null;
    }

    @Override
    public void apagarVeiculo(String idVeiculo) {

    }

    @Override
    public ResponseGetVeiculo buscarVeiculo(String idVeiculo) {
        return null;
    }

    @SneakyThrows
    @Override
    public List<ResponseGetVeiculo> listarVeiculos() {
        //return List.of();
//        throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,t);
        throw new RuntimeException("Falha geral no componente X");

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
