package br.com.locamais.sbootlocamaisrentcars.service;

import br.com.locamais.rent.cars.representation.RequestInsertVeiculo;
import br.com.locamais.rent.cars.representation.RequestUpdateVeiculo;
import br.com.locamais.rent.cars.representation.ResponseGetVeiculo;
import br.com.locamais.rent.cars.representation.ResponseSuccess;

import java.util.List;

public interface RentCarsService {

    ResponseSuccess salvarVeiculo(RequestInsertVeiculo requestInsertVeiculo);
    ResponseSuccess alterarCompletaVeiculo(String idVeiculo, RequestUpdateVeiculo requestUpdateVeiculo);
    ResponseSuccess alterarParcialVeiculo(String idVeiculo, RequestUpdateVeiculo requestUpdateVeiculo);
    void apagarVeiculo(String idVeiculo);
    ResponseGetVeiculo buscarVeiculo(String idVeiculo);
    List<ResponseGetVeiculo> listarVeiculos();
    List<ResponseGetVeiculo> listarVeiculosAlugados();
    List<ResponseGetVeiculo> listarVeiculosNaoAlugados();
}
