package br.phalvessouza.caninos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.phalvessouza.caninos.model.Raca;
import br.phalvessouza.caninos.repository.RacaRepository;

@Service
public class RacaService {

    @Autowired
    private RacaRepository racaRepository;

    public ResponseEntity<Raca> cadastrarRaca(Raca raca) {
        return ResponseEntity.status(HttpStatus.CREATED).body(racaRepository.save(raca));
    }

    public ResponseEntity<Raca> listarRacaPorId(Integer id) {
        Optional<Raca> raca = racaRepository.findById(id);       
        if (raca.isPresent()) {
        	return ResponseEntity.ok(raca.get());
        	
        }    
        return ResponseEntity.notFound().build();
    }

    public List<Raca> listarTodasRacas() {
        return racaRepository.findAll();
    }

    public ResponseEntity<Raca> excluirRaca(Integer id) {
        if (racaRepository.existsById(id)) {
        	racaRepository.deleteById(id);
        	return ResponseEntity.noContent().build();
        }
        
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Raca> atualizarRaca(Integer id, Raca novaRaca) {
       if (racaRepository.existsById(id)) {
    	   novaRaca.setId(id);
    	   return ResponseEntity.ok(racaRepository.save(novaRaca));
       }
       
       return ResponseEntity.notFound().build();
    }
}

