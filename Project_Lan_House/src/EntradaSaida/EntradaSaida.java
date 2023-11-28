package EntradaSaida;

import java.text.DecimalFormat;

import javax.swing.JOptionPane;

public class EntradaSaida {
    public static final Integer NUMBER_OF_OPTIONS = 12;

    public static Integer mainScreen() {
        StringBuilder menu = new StringBuilder();
        menu.append("// TELA DE MENU '\\'\n\n");
        menu.append("↓ Selecione uma das opções abaixo ↓\n\n");
        menu.append("1: Cadastrar novo cliente\n");
        menu.append("2: Cadastrar opção de tempo\n");
        menu.append("3: Locar um computador\n");
        menu.append("4: Deslocar um computador\n");
        menu.append("5: Receber pagamento\n");
        menu.append("6: Lista de computadores livres\n");
        menu.append("7: Fazer reserva\n");
        menu.append("8: Lista de reservas\n");
        menu.append("9: Conferência de caixa\n");
        menu.append("10: Lista de inadimplentes\n");
        menu.append("11: Relatório de histórico de uso\n");
        menu.append("12: Sair\n");

        return getNumber(menu.toString());
    }

    public static String getText(String msg) {
		return JOptionPane.showInputDialog(msg);
	}

	public static Integer getNumber(String msg) {
		while(true) {
			try {
				Integer number = Integer.parseInt(JOptionPane.showInputDialog(msg));
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
				Double number = Double.parseDouble(JOptionPane.showInputDialog(msg));
				return Double.parseDouble(new DecimalFormat("#,##0.00").format(number));
			} catch (Exception e) {
				showMessage("Input invalido");
			}
		}
	}

    public static Float getFloat(String msg) {
		while(true) {
			try {
				Float number = Float.parseFloat(JOptionPane.showInputDialog(msg));
				return Float.parseFloat(new DecimalFormat("#,##0.0").format(number));
			} catch (Exception e) {
				showMessage("Input invalido");
			}
		}
	}

	public static void showMessage(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}
}
