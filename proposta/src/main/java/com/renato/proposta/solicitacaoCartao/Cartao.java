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
	private String numeroCartao;
	private LocalDateTime emitidoEm;
	private String titular;
	private BigDecimal limite;
	@Enumerated(EnumType.STRING)
	private StatusCartao status = StatusCartao.ATIVO;
	@OneToOne
	private Proposta proposta;
	@OneToMany(mappedBy = "cartao")
	private Set<Biometria> biometrias = new HashSet<>();
	@OneToOne(mappedBy = "cartao")
	private BloqueioCartao bloqueioCartao;

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

	@Override
	public String toString() {
		return "Cartao [id=" + id + ", numeroCartao=" + numeroCartao + ", emitidoEm=" + emitidoEm + ", titular="
				+ titular + ", limite=" + limite + ", proposta=" + proposta + "]";
	}

	public boolean bloqueado() {
		return status == StatusCartao.BLOQUEADO;
	}

	public void atualizaStatus(SolicitaCartaoCliente solicitaCartaoCliente, @Valid BloqueioRequest request) {
		BloqueioResponse response = solicitaCartaoCliente.bloqueiaCartao(numeroCartao, request);
		 this.status = response.atualizaStatusCartao();
	}
}
