package com.renato.proposta.cadastraProposta;

import java.math.BigDecimal;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import com.renato.proposta.validation.CpfOuCnpj;
import com.renato.proposta.validation.ProibiCpfOuCnpjDuplicadoValid;

@ProibiCpfOuCnpjDuplicadoValid
public class PropostaRequest {
	
	@NotBlank
	private String nome;
	@NotBlank @Email
	private String email;
	@NotBlank @CpfOuCnpj
	@Pattern(regexp = "([0-9]{11})|([0-9]{14})")
	private String documento;
	@NotBlank
	private String endereco;
	@NotNull @Positive
	private BigDecimal salario;
	
	@Deprecated
	public PropostaRequest() {
	}

	public PropostaRequest(@NotBlank String nome, @NotBlank String email, @NotBlank String documento,
			@NotBlank String endereco, @NotNull @Positive BigDecimal salario) {
		this.nome = nome;
		this.email = email;
		this.documento = documento;
		this.endereco = endereco;
		this.salario = salario;
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

	public Proposta toModel() {
		return new Proposta(nome,email,documento,endereco,salario);
	}
}
