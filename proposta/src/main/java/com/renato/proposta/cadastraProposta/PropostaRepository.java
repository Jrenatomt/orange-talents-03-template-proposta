package com.renato.proposta.cadastraProposta;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PropostaRepository extends JpaRepository<Proposta, Long>{
	
	Optional<Proposta> findByDocumento(String documento);

}
