package br.com.Banco.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Cliente {
	@Id
	@Column(name ="nro_conta")
	private int nroConta;
	private int agencia;
	private String telefone;
	private float saldo;
	@Column(name = "limite_Cheque_Especial")
	private float chequeEspecial;
	/**
	 * @param nroConta
	 * @param agencia
	 * @param telefone
	 * @param saldo
	 * @param chequeEspecial
	 */
	public Cliente(int nroConta, int agencia, String telefone, float saldo, float chequeEspecial) {
		super();
		this.nroConta = nroConta;
		this.agencia = agencia;
		this.telefone = telefone;
		this.saldo = saldo;
		this.chequeEspecial = chequeEspecial;
	}
	public int getNroConta() {
		return nroConta;
	}
	public void setNroConta(int nroConta) {
		this.nroConta = nroConta;
	}
	public int getAgencia() {
		return agencia;
	}
	public void setAgencia(int agencia) {
		this.agencia = agencia;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public float getSaldo() {
		return saldo;
	}
	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}
	public float getChequeEspecial() {
		return chequeEspecial;
	}
	public void setChequeEspecial(float chequeEspecial) {
		this.chequeEspecial = chequeEspecial;
	}
	public abstract String imprimeCliente();
	
	
}
