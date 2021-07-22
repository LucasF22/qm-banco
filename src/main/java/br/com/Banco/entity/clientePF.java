package br.com.Banco.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cliente_PF")
public class clientePF extends Cliente{
	
	@Column(length = 11, unique = true)
	private String CPF;
	private String nome;
	private int idade;
	/**
	 * @param nroConta
	 * @param agencia
	 * @param telefone
	 * @param saldo
	 * @param chequeEspecial
	 * @param cPF
	 * @param nome
	 * @param idade
	 */
	public clientePF(int nroConta, int agencia, String telefone, float saldo, float chequeEspecial, String CPF,
			String nome, int idade) {
		super(nroConta, agencia, telefone, saldo, chequeEspecial);
		this.CPF = CPF;
		this.nome = nome;
		this.idade = idade;
	}
	
	@Override
	public String imprimeCliente() {
		return String.format("%-5d %-3d %-20s %-14s %-20s", super.getNroConta(), super.getAgencia(), super.getTelefone(), this.CPF, this.nome);
	}
	
	public String getCPF() {
		return CPF;
	}
	public void setCPF(String cPF) {
		CPF = cPF;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	
	
	
}
