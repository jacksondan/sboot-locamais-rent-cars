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
    public ResponseEntity<ResponseSuccess> postVeiculo(RequestPostVeiculo requestPostVeiculo){
        return new ResponseEntity<>(service.salvarVeiculo(requestPostVeiculo),HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ResponseSuccess> patchVeiculo(String idVeiculo, RequestPatchVeiculo requestPatchVeiculo){
        return new ResponseEntity<>(service.alterarVeiculo(idVeiculo,requestPatchVeiculo),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteVeiculo( String idVeiculo){
        service.apagarVeiculo(idVeiculo);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ResponseGetVeiculo>> getListAll(){
        log.info("Executando listagem de veículos");
        var failMessage = new ResponseError()
                .error("Erro planejado para teste")
                .statusCode(400)
                .success(false);
        log.error("Erro apresentado ao consultar lista de veículos");
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
