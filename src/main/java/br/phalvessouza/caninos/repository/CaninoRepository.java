package br.phalvessouza.caninos.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.phalvessouza.caninos.model.Canino;
import br.phalvessouza.caninos.model.Raca;


@Repository
public interface CaninoRepository extends JpaRepository<Canino, Integer> {
	List<Canino> findByRaca(Raca raca);
	
}
