//Importando bibliotecas.
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

//Criando uma interface gráfica para Gerenciar lista de frutas.
public class FrutaManagerGUI {
	private ArrayList<String> frutas;
	private DefaultListModel<String> listModel; 
	private JList<String> list;
	
//Constructor para inicialização de lista e modelo de lista.
	public FrutaManagerGUI() {
		frutas = new ArrayList<>();
		listModel = new DefaultListModel<>();
		
/*Criando a janela principal da interface, contendo a configuração de tamanho, operação 
 para quando fechada a janela o programa encerrar e organizador de layout para organizar 
 componentes em 'norte, sul, lest, oeste e centro,
 */
		JFrame frame = new JFrame("Gerenciador de Frutas");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 300);
		frame.setLayout(new BorderLayout());
		
//Criação do painel superior e definindo tudo horizontalmente.	
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
	
//Criação do campo de texto para usuário digitar.
		JTextField frutaField = new JTextField(15);
		panel.add(frutaField);

//Criação dos botões já com seus nomes.
		JButton addButton = new JButton("Adicionar");
		panel.add(addButton);
		
		JButton modifyButton = new JButton("Modificar");
		modifyButton.setEnabled(false);
		panel.add(modifyButton);
		
		JButton removeButton = new JButton("Remover");
		removeButton.setEnabled(false);
		panel.add(removeButton);
		
//Ajusta o painel criado ao norte.	
		frame.add(panel, BorderLayout.NORTH);
		
//Criação da lista de frutas.
		list = new JList<>(listModel);
		JScrollPane scrollPane = new JScrollPane(list);
		frame.add(scrollPane, BorderLayout.CENTER);
		
//Criação do botão 'Listar Frutas' localizado ao sul da janela, para exibir todas frutas adicionada à lista.
		JButton listButton = new JButton("Listar Frutas");
		frame.add(listButton, BorderLayout.SOUTH);
		
//Criação da execução do botão adicionar, obtendo a informação de que o usuário digita no JTexteFiled, adiciona na lista e exibe uma confirmação.
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String novaFruta = frutaField.getText();
				if (!novaFruta.isEmpty()) {
					frutas.add(novaFruta);
					listModel.addElement(novaFruta);
					frutaField.setText("");
					JOptionPane.showMessageDialog(frame, novaFruta + " foi adicionada.");
				}
			}
		});
		
/*Criação da execução do botão modificar, onde caso o usuário clique sobre uma fruta já adicionada, pode clicar no botão 'modificar' 
e pode alterar seu nome, atualizando a informação na lista e finalizando com uma exibição de confirmação da ação. */
		modifyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = list.getSelectedIndex();
				if (selectedIndex != -1) {
					String frutaSelecionada = listModel.getElementAt(selectedIndex);
					String novaFruta = JOptionPane.showInputDialog(frame, "Modificar " + frutaSelecionada + " para:", frutaSelecionada);
					if (novaFruta != null && !novaFruta.isEmpty()) {
						frutas.set(selectedIndex, novaFruta);
						listModel.set(selectedIndex, novaFruta);
						JOptionPane.showMessageDialog(frame, "Fruta " + frutaSelecionada + " foi modificada para " + novaFruta);
					}
				} else {
					JOptionPane.showMessageDialog(frame, "Selecione uma fruta para modificar.");
				}
			}
		});
		
/*Criação da execução do botão Remover, onde quando o usuário clique sobre uma fruta adicionada, pode clicar no botão de 'remover' e excluir 
a fruta da lista, finalizando com a exibição de confirmação da ação. */
		removeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = list.getSelectedIndex();
				if (selectedIndex !=-1) {
					String frutaRemovida = frutas.remove(selectedIndex);
					listModel.remove(selectedIndex);
					JOptionPane.showMessageDialog(frame, frutaRemovida + " foi removida.");
				} else {
					JOptionPane.showMessageDialog(frame, "Selecione uma fruta para remover.");
				}
			}
		});
		
/*Criação da execução do botão de listar frutas, onde quando clicado exibe a lista de frutas adicionadas, ou caso esteja vazia, exibe uma mensagem 
dizendo que não tem nenhuma fruta na lista "Nenhuma fruta na lista".*/
		listButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (frutas.isEmpty()) {
					JOptionPane.showMessageDialog(frame, "Nehuma fruta na lista.");
				} else {
					JOptionPane.showMessageDialog(frame, "Frutas: " + frutas);
				}
			}
		});
		

//Criação do ouvinte, onde verifica se exite interação com o sistema, e caso houver interação, o sistema habilita outras funcionalidades.
		list.addListSelectionListener(e -> {
			boolean selectionExists = !list.isSelectionEmpty();
			removeButton.setEnabled(selectionExists);
			modifyButton.setEnabled(selectionExists);
		});
		
//Permissão para que o usuário veja a janela criada.
		frame.setVisible(true);
	}
	
//Método main para invocar a interface criada e poder executá-lo em seguida. 
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new FrutaManagerGUI();
			}
		});
	}
}

