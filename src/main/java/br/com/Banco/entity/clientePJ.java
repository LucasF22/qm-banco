package br.com.Banco.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cliente_PJ")
public class clientePJ extends Cliente{
	@Column(length = 14, unique = true)
	private String cnpj;
	@Column (name = "nome_socio")
	private String nomeSocio;
	@Column (name = "razao_social")
	private String razaoSocial;
	@Column (name = "nome_fantasia")
	private String nomeFantasia;
	
	public clientePJ(int nroConta, int agencia, String telefone, float saldo, float chequeEspecial, String cnpj,
			String nomeSocio, String razaoSocial, String nomeFantasia) {
		super(nroConta, agencia, telefone, saldo, chequeEspecial);
		this.cnpj = cnpj;
		this.nomeSocio = nomeSocio;
		this.razaoSocial = razaoSocial;
		this.nomeFantasia = nomeFantasia;
	}

	@Override
	public String imprimeCliente() {
		return String.format("%-5d %-3d %-20s %-14s %-20s", super.getNroConta(), super.getAgencia(), super.getTelefone(), this.cnpj, this.razaoSocial);
	}
	
	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getNomeSocio() {
		return nomeSocio;
	}

	public void setNomeSocio(String nomeSocio) {
		this.nomeSocio = nomeSocio;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}
	
	
}
