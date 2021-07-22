package br.com.Banco.exception;


public class BancoException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5102335325853656943L;
	
	private String mensagemDeErro;
	public BancoException(String mensagemDeErro) {
		this.mensagemDeErro = mensagemDeErro;
	}
	
	public String getMensagemDeErro() {
		return mensagemDeErro;
	}

}
