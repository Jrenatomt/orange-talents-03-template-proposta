package com.renato.proposta.cadastraProposta;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.util.Assert;

import com.renato.proposta.analisaProposta.AnalisaCliente;
import com.renato.proposta.analisaProposta.AnalisaClienteRequest;
import com.renato.proposta.analisaProposta.AnalisaClienteResponse;

import feign.FeignException;

@Entity
public class Proposta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private @NotBlank String nome;
	private @NotBlank @Email String email;
	private @NotBlank String documento;
	private @NotBlank String endereco;
	private @NotNull @Positive BigDecimal salario;
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private LocalDateTime criadoEm;
	@Enumerated(EnumType.STRING)
	private StatusProposta status = StatusProposta.NAO_ELEGIVEL;

	@Deprecated
	public Proposta() {
	}

	public Proposta(@NotBlank String nome, @NotBlank @Email String email, @NotBlank String documento,
			@NotBlank String endereco, @NotNull @Positive BigDecimal salario) {
		this.nome = nome;
		this.email = email;
		this.documento = documento.replaceAll("[.-]", "");
		this.endereco = endereco;
		this.salario = salario;
		Assert.hasLength(documento, "Documento é obrigatório");
		Assert.hasLength(email, "Email é obrigatório");
		Assert.hasLength(nome, "Nome é obrigatório");
		Assert.notNull(endereco, "Endereço é obrigatório");
		Assert.state(salario.compareTo(BigDecimal.ZERO) > 0, "Salário não pode ser negativo");
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getDocumento() {
		return documento;
	}

	public String getEndereco() {
		return endereco;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public LocalDateTime getCriadoEm() {
		return criadoEm;
	}

	@PrePersist
	public void prePersist() {
		criadoEm = LocalDateTime.now();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((documento == null) ? 0 : documento.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Proposta other = (Proposta) obj;
		if (documento == null) {
			if (other.documento != null)
				return false;
		} else if (!documento.equals(other.documento))
			return false;
		return true;
	}

	public void analisaProposta(AnalisaCliente analiseCliente) {
		AnalisaClienteRequest analiseRequest = new AnalisaClienteRequest(this);
		try {
			AnalisaClienteResponse response = analiseCliente.consultaAnalise(analiseRequest);
			this.status = response.analisaStatus();
		} catch (FeignException.UnprocessableEntity e) {
			this.status = StatusProposta.NAO_ELEGIVEL;
		}
	}
}
