package br.com.locamais.sbootlocamaisrentcars.service;

import br.com.locamais.rent.cars.representation.RequestPatchVeiculo;
import br.com.locamais.rent.cars.representation.RequestPostVeiculo;
import br.com.locamais.rent.cars.representation.ResponseGetVeiculo;
import br.com.locamais.rent.cars.representation.ResponseSuccess;

import java.util.List;

public interface RentCarsService {

    ResponseSuccess salvarVeiculo(RequestPostVeiculo requestPostVeiculo);
    ResponseSuccess alterarVeiculo(String idVeiculo, RequestPatchVeiculo requestPatchVeiculo);
    void apagarVeiculo(String idVeiculo);
    ResponseGetVeiculo buscarVeiculo(String idVeiculo);
    List<ResponseGetVeiculo> listarVeiculos();
    List<ResponseGetVeiculo> listarVeiculosAlugados();
    List<ResponseGetVeiculo> listarVeiculosNaoAlugados();
}
