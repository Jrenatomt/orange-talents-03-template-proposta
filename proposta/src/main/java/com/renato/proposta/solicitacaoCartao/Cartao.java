package com.renato.proposta.solicitacaoCartao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.renato.proposta.avisoViagem.AvisoViagem;
import com.renato.proposta.bloqueioCartao.BloqueioCartao;
import com.renato.proposta.bloqueioCartao.BloqueioRequest;
import com.renato.proposta.bloqueioCartao.BloqueioResponse;
import com.renato.proposta.cadastraProposta.Proposta;
import com.renato.proposta.cadastroBiometria.Biometria;

@Entity
public class Cartao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String numeroCartao;
	@NotNull
	private LocalDateTime emitidoEm;
	@NotBlank
	private String titular;
	@NotNull @Positive 
	private BigDecimal limite;
	@Enumerated(EnumType.STRING)
	private StatusCartao status = StatusCartao.ATIVO;
	@OneToOne
	private @Valid Proposta proposta;
	@OneToMany(mappedBy = "cartao")
	private Set<Biometria> biometrias = new HashSet<>();
	@OneToOne(mappedBy = "cartao")
	private BloqueioCartao bloqueioCartao;
	@OneToMany(mappedBy = "cartao")
	private Set<AvisoViagem> avisosViagens = new HashSet<>();

	@Deprecated
	public Cartao() {
	}

	public Cartao(String numeroCartao, LocalDateTime emitidoEm, String titular, BigDecimal limite, Proposta proposta) {
		this.numeroCartao = numeroCartao;
		this.emitidoEm = emitidoEm;
		this.titular = titular;
		this.limite = limite;
		this.proposta = proposta;
	}

	public Long getId() {
		return id;
	}

	public String getNumeroCartao() {
		return numeroCartao;
	}

	public LocalDateTime getEmitidoEm() {
		return emitidoEm;
	}

	public String getTitular() {
		return titular;
	}

	public BigDecimal getLimite() {
		return limite;
	}

	public Proposta getProposta() {
		return proposta;
	}

	public Set<Biometria> getBiometrias() {
		return biometrias;
	}

	public boolean bloqueado() {
		return status == StatusCartao.BLOQUEADO;
	}

	public void atualizaStatus(IntegracaoCartaoCliente solicitaCartaoCliente, @Valid BloqueioRequest request) {
		BloqueioResponse response = solicitaCartaoCliente.bloqueiaCartao(numeroCartao, request);
		 this.status = response.atualizaStatusCartao();
	}
}
