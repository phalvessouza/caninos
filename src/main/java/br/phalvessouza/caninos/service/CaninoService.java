package br.phalvessouza.caninos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import br.phalvessouza.caninos.model.Canino;
import br.phalvessouza.caninos.model.Raca;
import br.phalvessouza.caninos.repository.CaninoRepository;
import br.phalvessouza.caninos.repository.RacaRepository;

@Service
public class CaninoService {

    @Autowired
    private CaninoRepository caninoRepository;

    @Autowired
    private RacaRepository racaRepository;

    public ResponseEntity<Canino> cadastrarCanino(Integer racaId, Canino canino) {
        Optional<Raca> raca = racaRepository.findById(racaId);
        
        if(raca.isPresent()) {
        	canino.setRaca(raca.get());
        	caninoRepository.save(canino);
        	return ResponseEntity.status(HttpStatus.CREATED).body(canino);
        }
        
        return ResponseEntity.notFound().build();
    }
    
    
    public ResponseEntity<List<Canino>> listarCaninosPorRaca(Integer racaId) {
        Optional<Raca> raca = racaRepository.findById(racaId);

        if (raca.isPresent()) {
            List<Canino> caninos = caninoRepository.findByRaca(raca.get());
            return ResponseEntity.ok(caninos);
        }

        return ResponseEntity.notFound().build();
    }
    
    public List<Canino> listarTodosCaninos() {
        return caninoRepository.findAll();
    }
    
    public ResponseEntity<Canino> excluirCanino(Integer id) {
        if (caninoRepository.existsById(id)) {
        	caninoRepository.deleteById(id);
        	return ResponseEntity.noContent().build();
        }
        
        return ResponseEntity.notFound().build();
    }
    
}
