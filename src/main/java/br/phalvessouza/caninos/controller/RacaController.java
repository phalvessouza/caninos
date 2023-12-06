package br.phalvessouza.caninos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.phalvessouza.caninos.model.Canino;
import br.phalvessouza.caninos.model.Raca;
import br.phalvessouza.caninos.service.CaninoService;
import br.phalvessouza.caninos.service.RacaService;

@RestController
@RequestMapping("/racas")
public class RacaController {

    @Autowired
    private RacaService racaService;

    @Autowired
    private CaninoService caninoService;

    @PostMapping("/")
    public ResponseEntity<Raca> cadastrarRaca(@RequestBody Raca raca) {
        return racaService.cadastrarRaca(raca);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Raca> listarRacaPorId(@PathVariable Integer id) {
        return racaService.listarRacaPorId(id);
    }

    @GetMapping("/")
    public List<Raca> listarTodasRacas() {
        return racaService.listarTodasRacas();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Raca> excluirRaca(@PathVariable Integer id) {      
        return racaService.excluirRaca(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Raca> atualizarRaca(@PathVariable Integer id, @RequestBody Raca novaRaca) {
        return racaService.atualizarRaca(id, novaRaca);          
    }
    
    @PostMapping("/{racaId}/caninos")
    public ResponseEntity<Canino> cadastrarCanino(@PathVariable Integer racaId, @RequestBody Canino canino){
    	return caninoService.cadastrarCanino(racaId, canino);
    }
    
    @GetMapping("/{racaId}/caninos")
    public ResponseEntity<List<Canino>> listarCaninosPorRaca(@PathVariable Integer racaId) {
        return caninoService.listarCaninosPorRaca(racaId);
    }
        
}
