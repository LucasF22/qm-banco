package br.com.Banco.ProgrmaPrincipal;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import br.com.Banco.DAO.ClienteDAO;
import br.com.Banco.entity.Cliente;
import br.com.Banco.entity.clientePF;
import br.com.Banco.entity.clientePJ;
import br.com.Banco.exception.BancoException;

public class ProgramaPrincipal {
	private static void menuGerenciamentoDados(ClienteDAO clienteDao, Scanner teclado) {

		int opcaoMenuGerenciamento = 0;

		do {
			System.out.println("1 - Para cadastrar um cliente PF");
			System.out.println("2 - Para cadastrar um cliente PJ");
			System.out.println("3 - Para consultar um cliente pelo n�mero da conta");
			System.out.println("4 - Para consultar um cliente por cpf");
			System.out.println("5 - Para consultar um cliente por cnpj");
			System.out.println("6 - Para remover um cliente");
			System.out.println("0 - Para voltar ao menu anterior");

			opcaoMenuGerenciamento = teclado.nextInt();

			switch (opcaoMenuGerenciamento) {
			case 1:
				cadastraCliente(clienteDao, teclado, true);
				break;
			case 2:
				cadastraCliente(clienteDao, teclado, false);
				break;
			case 3:
				consultaCliente(clienteDao, teclado);
				break;
			case 4:
				consultaClienteChave(clienteDao, teclado, true);
				break;
			case 5:
				consultaClienteChave(clienteDao, teclado, false);
				break;
			case 6:
				removeCliente(clienteDao, teclado);
				break;
			case 0:
				System.out.println("Voltando ao menu anterior <--");
				break;
			default:
				System.out.println("Op��o inv�lida!");
			}

		} while (opcaoMenuGerenciamento != 0);

	}

	private static void removeCliente(ClienteDAO clienteDao, Scanner teclado) {

		System.out.println("Digite o n�mero da conta: ");
		int nroConta = teclado.nextInt();

		if (clienteDao.removeCliente(nroConta)) {
			System.out.println("Cliente removido com sucesso.");
		} else {
			System.err.println("Erro ao remover cliente.");
		}

	}

	private static void consultaClienteChave(ClienteDAO clienteDao, Scanner teclado, boolean tipoPessoa) {

		if (tipoPessoa) {
			System.out.println("Digite o CPF: ");
		} else {
			System.out.println("Digite o CNPJ: ");
		}
		teclado.nextLine();

		String cpfCnpj = teclado.nextLine();

		Cliente cliente = clienteDao.consultaPorCpfCnpj(cpfCnpj);

		if (cliente == null) {
			System.out.println("Cliente n�o encontrado");
			return;
		}

		System.out.println(cliente);
	}

	private static void consultaCliente(ClienteDAO clienteDao, Scanner teclado) {

		System.out.println("Digite o n�mero da conta: ");
		int nroConta = teclado.nextInt();

		Cliente clienteConsultado = clienteDao.consultaCliente(nroConta);

		if (clienteConsultado == null) {
			System.out.println("Cliente n�o encontrado");
			return;
		}

		System.out.println(clienteConsultado);
	}

	// tipoPessoa -> verdadeiro se PF, falso se PJ
	private static void cadastraCliente(ClienteDAO clienteDao, Scanner teclado, boolean tipoPessoa) {

		System.out.println("Digite o n�mero de conta: ");
		int nroConta = teclado.nextInt();

		System.out.println("Digite a agencia: ");
		int agencia = teclado.nextInt();

		System.out.println("Digite o telefone: ");
		String telefone = teclado.nextLine();

		teclado.nextLine();
		float saldo = 0F;

		System.out.println("Digite o limite do cheque especial: ");
		float limiteChequeEspecial = teclado.nextFloat();

		teclado.nextLine();

		Cliente cliente = null;

		if (tipoPessoa) {
			System.out.println("Digite o CPF: ");
			String cpf = teclado.nextLine();

			System.out.println("Digite o nome: ");
			String nome = teclado.nextLine();

			System.out.println("Digite a idade: ");
			int idade = teclado.nextInt();

			cliente = new clientePF(nroConta, agencia, telefone, saldo, limiteChequeEspecial, cpf, nome, idade);

		} else {

			System.out.println("Digite o cnpj: ");
			String cnpj = teclado.nextLine();

			System.out.println("Digite o nome do s�cio: ");
			String nomeSocio = teclado.nextLine();

			System.out.println("Digite a raz�o social: ");
			String razaoSocial = teclado.nextLine();

			System.out.println("Digite o nome fantasia: ");
			String nomeFantasia = teclado.nextLine();

			cliente = new clientePJ(nroConta, agencia, telefone, saldo, limiteChequeEspecial, cnpj, nomeSocio,
					razaoSocial, nomeFantasia);

		}

		clienteDao.cadastraCliente(cliente);
	}

// 	Financeira	
//	aumentar e diminuir o limite do cheque especial do cliente,  
//	fazer transfer�ncias entre seus clientes - talvez outro menu
//  e adicionar saldo a um cliente, - menu de transa��es
	private static void menuFinanceiro(ClienteDAO clienteDao, Scanner teclado) {
		int opcaoFinanceiro = 0;

		do {
			System.out.println("-- Financeiro --");
			System.out.println("1 - Aumentar ou diminuir limite do cheque especial ");
			System.out.println("2 - Adicionar saldo (dep�sito)");
			System.out.println("3 - Transfer�ncia entre contas");
			System.out.println("0 - Para voltar ao menu anterior");

			opcaoFinanceiro = teclado.nextInt();

			switch (opcaoFinanceiro) {
			case 1:
				modificarLimiteChequeEspecial(clienteDao, teclado);
				break;
			case 2:
				adicionarSaldo(clienteDao, teclado);
				break;
			case 3:
				transferencia(clienteDao, teclado);
				break;
			case 0:
				System.out.println("Voltando ao menu anterior <--");
				break;
			default:
				System.out.println("Op��o inv�lida!");
			}

		} while (opcaoFinanceiro != 0);

	}

	private static void modificarLimiteChequeEspecial(ClienteDAO clienteDao, Scanner teclado) {

		System.out.println("- Aumentar ou diminuir limite do cheque especial -");
		System.out.println("Digite o n�mero da conta do cliente: ");
		int nroConta = teclado.nextInt();
		// Mostrar o cliente antes de alterar
		System.out.println("Digite o valor da altera��o (insira um n�mero negativo para diminuir): ");
		float valorAlteracao = teclado.nextInt();

		boolean clienteAlterado = clienteDao.alteraLimite(valorAlteracao, nroConta);

		if (clienteAlterado) {
			System.out.println("Altera��o realizada com sucesso. ");
		} else {
			System.out.println("Erro ao alterar o limite deste cliente. ");
		}
	}

	private static void adicionarSaldo(ClienteDAO clienteDao, Scanner teclado) {

		System.out.println("- Adicionar saldo (dep�sito) -");
		System.out.println("Digite o n�mero da conta do cliente: ");
		int nroConta = teclado.nextInt();

		System.out.println("Digite o valor a ser depositado na conta do cliente: ");
		float valorAdicionado = teclado.nextFloat();

		try {
			clienteDao.adicionaSaldo(nroConta, valorAdicionado);
		} catch (BancoException e) {
			System.err.println("Erro ao adicionar saldo: " + e.getMensagemDeErro());
		}


	}

	private static void transferencia(ClienteDAO clienteDao, Scanner teclado) {

		System.out.println("- Transfer�ncia entre contas -");

		System.out.println("Digite a conta de origem");
		int nroContaOrigem = teclado.nextInt();

		System.out.println("Digite a conta de destino");
		int nroContaDestino = teclado.nextInt();

		System.out.println("Digite o valor transferido: ");
		float valor = teclado.nextFloat();

		try {
			
			clienteDao.transfereValor(nroContaOrigem, nroContaDestino, valor);
			
		} catch (BancoException e) {
			System.err.println("Erro ao realizar transfer�ncia: " + e.getMensagemDeErro());
		}


	}

	private static void menuRelatorios(ClienteDAO clienteDao, Scanner teclado) {

		int opcaoRelatorios = 0;

		do {
			System.out.println("- Relat�rios -");
			System.out.println("1 - Imprimir relat�rio geral de clientes");
			System.out.println("0 - Voltar ao menu anterior");
			opcaoRelatorios = teclado.nextInt();
			
			switch (opcaoRelatorios) {
				case 1:
					imprimeRelatorioGeral(clienteDao);
					break;
				case 0:
					System.out.println("Voltando ao menu anterior <--");
					break;
				default:
					System.out.println("Op��o inv�lida!");

			}

		} while (opcaoRelatorios != 0);

	}

	private static void imprimeRelatorioGeral(ClienteDAO clienteDao) {
		try {
			FileWriter fw = new FileWriter("relatorioClientes.txt");
			
			fw.write("---- Relat�rio Geral de Clientes ----\n");
			fw.write("Num. Conta\tAg\tTelefone\tCPF/CNPJ\tNome/Razao Social\n");
			
			List<Cliente> clientes = clienteDao.listaClientes();
			
			for (Cliente cliente : clientes) {
				fw.write(cliente.imprimeCliente());
				fw.write("\n");
			}
			
			fw.close();
		} catch (IOException e) {
			System.err.println("Erro ao gerar arquivo");
		}
		
	}

	public static void main(String[] args) {

		ClienteDAO clienteDao = new ClienteDAO();
		Scanner teclado = new Scanner(System.in);

		int opcaoMenuExterno = 0;

		System.out.println("-- SISBAN --");
		System.out.println("-- Bem vindo gerente --");

		do {
			System.out.println("1 - Gerenciamento de dados");
			System.out.println("2 - Financeiro");
			System.out.println("3 - Relat�rios");
			System.out.println("0 - Sair");
			opcaoMenuExterno = teclado.nextInt();

			switch (opcaoMenuExterno) {
			case 1:
				menuGerenciamentoDados(clienteDao, teclado);
				break;
			case 2:
				menuFinanceiro(clienteDao, teclado);
				break;
			case 3:
				menuRelatorios(clienteDao, teclado);
				break;
			case 0:
				System.out.println("Obrigado por usar o nosso sistema, bom descanso.");
				break;
			default:
				System.out.println("Op��o inv�lida");
			}

		} while (opcaoMenuExterno != 0);

	}

}
