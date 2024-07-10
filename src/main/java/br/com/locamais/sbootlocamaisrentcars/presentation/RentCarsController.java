package br.com.locamais.sbootlocamaisrentcars.presentation;

import br.com.locamais.rent.cars.presentation.V1Api;
import br.com.locamais.rent.cars.representation.*;
import br.com.locamais.sbootlocamaisrentcars.service.RentCarsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class RentCarsController implements V1Api {

    private final RentCarsService service;

    @Override
    public ResponseEntity<ResponseGetVeiculo> getVeiculo(String idVeiculo){
        return new ResponseEntity<>(service.buscarVeiculo(idVeiculo), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseSuccess> postVeiculo(RequestInsertVeiculo requestInsertVeiculo){
        return new ResponseEntity<>(service.salvarVeiculo(requestInsertVeiculo),HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ResponseSuccess> putVeiculo(String idVeiculo, RequestUpdateVeiculo requestUpdateVeiculo){
        return new ResponseEntity<>(service.alterarCompletaVeiculo(idVeiculo,requestUpdateVeiculo),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseSuccess> patchVeiculo(String idVeiculo, RequestUpdateVeiculo requestUpdateVeiculo){
        return new ResponseEntity<>(service.alterarParcialVeiculo(idVeiculo,requestUpdateVeiculo),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteVeiculo(String idVeiculo){
        service.apagarVeiculo(idVeiculo);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<ResponseGetVeiculo>> getListAll(){
        return new ResponseEntity<>(service.listarVeiculos(),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ResponseGetVeiculo>> getListRented(){
        return new ResponseEntity<>(service.listarVeiculosAlugados(),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ResponseGetVeiculo>> getListUnrented(){
        return new ResponseEntity<>(service.listarVeiculosNaoAlugados(),HttpStatus.OK);
    }
}
