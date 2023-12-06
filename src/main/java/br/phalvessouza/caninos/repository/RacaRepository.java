package br.phalvessouza.caninos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.phalvessouza.caninos.model.Raca;

@Repository
public interface RacaRepository extends JpaRepository<Raca, Integer> {
}
