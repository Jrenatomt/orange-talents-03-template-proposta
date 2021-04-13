package com.renato.proposta.cadastraProposta;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.util.Assert;

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

	@Deprecated
	public Proposta() {
	}

	public Proposta(@NotBlank String nome, @NotBlank @Email String email, @NotBlank String documento,
			@NotBlank String endereco, @NotNull @Positive BigDecimal salario) {
		this.nome = nome;
		this.email = email;
		this.documento = documento;
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
}
