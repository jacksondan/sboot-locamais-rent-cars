package br.com.locamais.sbootlocamaisrentcars.presentation;

import br.com.locamais.rent.cars.presentation.V1Api;
import br.com.locamais.rent.cars.representation.RequestPatchVeiculo;
import br.com.locamais.rent.cars.representation.RequestPostVeiculo;
import br.com.locamais.rent.cars.representation.ResponseGetVeiculo;
import br.com.locamais.rent.cars.representation.ResponseSuccess;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RentCarsController implements V1Api {

    @Override
    public ResponseEntity<ResponseGetVeiculo> getVeiculo(String idVeiculo){
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<ResponseSuccess> postVeiculo(RequestPostVeiculo requestPostVeiculo){
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<ResponseSuccess> patchVeiculo(String idVeiculo, RequestPatchVeiculo requestPatchVeiculo){
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<Void> deleteVeiculo( String idVeiculo){
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<List<ResponseGetVeiculo>> getListAll(){
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<List<ResponseGetVeiculo>> getListRented(){
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<List<ResponseGetVeiculo>> getListUnrented(){
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
