package EntradaSaida;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;

import javax.swing.JOptionPane;

import Menu.com.model.computer.Computer;
import Menu.com.model.computer.ComputerUse;
import Menu.com.model.computer.ComputerUseHistory;
import Menu.com.model.payment.Cash;
import Menu.com.model.payment.Defaulter;

public class EntradaSaida {
    public static final Integer NUMBER_OF_OPTIONS = 11;

    public static Integer mainScreen() {
        StringBuilder menu = new StringBuilder();
        menu.append(" TELA DE MENU \n\n");
        menu.append("↓ Selecione uma das opções abaixo ↓\n\n");
        menu.append("1: Cadastrar novo cliente\n");
        menu.append("2: Cadastrar opção de tempo\n");
        menu.append("3: Locar um computador\n");
        menu.append("4: Deslocar um computador\n");
        menu.append("5: Receber pagamento\n");
        menu.append("6: Lista de computadores livres\n");
		menu.append("7: Lista de computadores ocupados\n");
        menu.append("8: Conferência de caixa\n");
        menu.append("9: Lista de inadimplentes\n");
        menu.append("10: Relatório de histórico de uso\n");
        menu.append("11: Sair\n");

        return getNumber(menu.toString());
    }

    public static String getText(String msg) {
		return JOptionPane.showInputDialog(msg);
	}

	public static Integer getNumber(String msg) {
		while(true) {
			try {
				Integer number = Integer.parseInt(JOptionPane.showInputDialog(msg));
				if (number <= 0) throw new Exception();
				return number;
			} catch (Exception e) {
				showMessage("Input invalido");
			}
		}
	}

    public static Boolean getBoolean(String msg) {
        return JOptionPane.showConfirmDialog(null, msg, "Question", 0) == 0;
    }

	public static Double getDouble(String msg) {
		while(true) {
			try {
				return new DecimalFormat("#,##0.00").parse(JOptionPane.showInputDialog(msg)).doubleValue();
			} catch (Exception e) {
				showMessage("Input invalido");
			}
		}
	}

    public static Float getFloat(String msg) {
		while(true) {
			try {
				return new DecimalFormat("#,##0.0").parse(JOptionPane.showInputDialog(msg)).floatValue();
			} catch (Exception e) {
				showMessage("Input invalido");
			}
		}
	}

	public static void showMessage(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}

	public static void listFreeComputers(Collection<Computer> computers) {
		StringBuilder str = new StringBuilder("Computadores Livres\n");
		for (Computer pc : computers) {
			if (!pc.isInUse()) {
				str.append("\nComputador: ").append(pc.getId());
			}
		}
		showMessage(str.toString());
	}

	public static void listOccupiedComputers(Collection<ComputerUse> computers) {
		StringBuilder str = new StringBuilder("Computadores Livres\n");
		for (ComputerUse pc : computers) {
			str.append("\nComputador: ").append(pc.getId());
			str.append(" Ocupado pelo usuário ").append(pc.getUserCpf());
		}
		showMessage(str.toString());
	}

	public static String getCpf(String msg) {
		while(true) {
			try {
				String cpf = getText(msg);
				if (cpf.length() != 11) throw new Exception();
				return cpf;
			} catch (Exception e) {
				showMessage("Input invalido");
			}
		}
	}

	public static void printCash(Cash cash) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		StringBuilder str = new StringBuilder("Conferência de Caixa\n");
		str.append("\nSaldo: R$ ").append(cash.getBalance());
		str.append("\nO que tem para receber: R$ ").append(cash.getReceivable());
		str.append("\nUltima movimentação: ").append(dateFormat.format(cash.getLastMovement()));

		showMessage(str.toString());
	}

	public static void listComputersUse(Collection<ComputerUseHistory> computerHistory) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		StringBuilder str = new StringBuilder("Historico de Uso dos Computadores\n\n");
        for (ComputerUseHistory compUse : computerHistory) {
            str.append("\nId do computador: ").append(compUse.getComputerId());
            str.append("\nCliente: ").append(compUse.getUserCpf());
            if(compUse.getFinishedDate()==null) {
				str.append("\nStatus: Cliente em sessão");
            }else {
				str.append("\nStatus: Encerrado em: ").append(dateFormat.format(compUse.getFinishedDate()));
            }
            str.append("\nHora de inicio da sessão: ").append(dateFormat.format(compUse.getInitialTime()));
            str.append("\nHora de término da sessão: ").append(dateFormat.format(compUse.getEndTime()));
            str.append("\nSessão paga?: ").append( compUse.isPayed() ? "Sim\n" : "Não\n" );
        }
        showMessage(str.toString());
	}

	public static void listDefaulters(Collection<Defaulter> defaulters) {
		StringBuilder str = new StringBuilder("Clientes Inadimplentes\n");
        for (Defaulter df : defaulters) {
            str.append("\nCliente: ").append(df.getUserCpf());
            str.append(", Valor devido: R$").append(df.getAmountOwed()).append("\n");
        }
        showMessage(str.toString());
	}
}
