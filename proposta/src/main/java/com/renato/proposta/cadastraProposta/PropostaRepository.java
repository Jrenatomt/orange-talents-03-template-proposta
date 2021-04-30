package com.renato.proposta.cadastraProposta;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PropostaRepository extends JpaRepository<Proposta, Long>{
	
	Optional<Proposta> findByHashDocumento(String documento);

	List<Proposta> findAllByStatusAndCartaoIsNull(StatusProposta elegivel);

	Optional<Proposta> findByDocumento(String documento);
}
