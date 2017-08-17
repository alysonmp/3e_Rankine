package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ViewTrocadores extends JPanel{

	//Evaporador Label
	JLabel labelEvapCore = new JLabel("Core");
	JLabel labelEvapK = new JLabel("K");
	
	//Evaporador Field
	JTextField fieldEvapCore = new JTextField(10);
	JTextField fieldEvapK = new JTextField(10);
	
	//Condensador Label
	JLabel labelCondCore = new JLabel("Core");
	JLabel labelCondK = new JLabel("K");
	
	//Condensador Field
	JTextField fieldCondCore = new JTextField(10);
	JTextField fieldCondK = new JTextField(10);
	
	//Regenerador Label
	JLabel labelRegCore = new JLabel("Core");
	JLabel labelRegK = new JLabel("K");
	
	//Regenerador Field
	JTextField fieldRegCore = new JTextField(10);
	JTextField fieldRegK = new JTextField(10);
	
	JPanel painelEvaporador;
	JPanel painelRegenerador;
	JPanel painelCondensador;
	
	public ViewTrocadores() {
		
		painelEvaporador = new JPanel(new GridBagLayout());
		painelRegenerador = new JPanel(new GridBagLayout());
		painelCondensador = new JPanel(new GridBagLayout());
		
		painelEvaporador.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(1, Color.lightGray, Color.lightGray), "Evaporador", 1, 2, new Font("Times New Roman", 1, 12), Color.darkGray));
		painelRegenerador.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(1, Color.lightGray, Color.lightGray), "Regenerador", 1, 2, new Font("Times New Roman", 1, 12), Color.darkGray));
		painelCondensador.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(1, Color.lightGray, Color.lightGray), "Condensador", 1, 2, new Font("Times New Roman", 1, 12), Color.darkGray));
		
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints g = new GridBagConstraints();
		
		//Evaporador
		g.gridx = 0;
		g.gridy = 0;
		g.fill = GridBagConstraints.HORIZONTAL;
		painelEvaporador.add(labelEvapCore);
		
		g.gridx = 1;
		g.gridy = 0;
		painelEvaporador.add(fieldEvapCore);
		
		g.gridx = 0;
		g.gridy = 1;
		painelEvaporador.add(labelEvapK);
		
		g.gridx = 1;
		g.gridy = 1;
		painelEvaporador.add(fieldEvapK);
		
		//Regenerador
		g.gridx = 0;
		g.gridy = 0;
		g.fill = GridBagConstraints.HORIZONTAL;
		painelRegenerador.add(labelRegCore);
		
		g.gridx = 1;
		g.gridy = 0;
		painelRegenerador.add(fieldRegCore);
		
		g.gridx = 0;
		g.gridy = 1;
		painelRegenerador.add(labelRegK);
		
		g.gridx = 1;
		g.gridy = 1;
		painelRegenerador.add(fieldRegK);
		
		//Condensador
		g.gridx = 0;
		g.gridy = 0;
		g.fill = GridBagConstraints.HORIZONTAL;
		painelCondensador.add(labelCondCore);
		
		g.gridx = 1;
		g.gridy = 0;
		painelCondensador.add(fieldCondCore);
		
		g.gridx = 0;
		g.gridy = 1;
		painelCondensador.add(labelCondK);
		
		g.gridx = 1;
		g.gridy = 1;
		painelCondensador.add(fieldCondK);
		
		g.gridx = 0;
		g.gridy = 0;
		this.add(painelEvaporador, g);
		
		g.gridx = 0;
		g.gridy = 1;
		this.add(painelRegenerador, g);
		
		g.gridx = 0;
		g.gridy = 2;
		this.add(painelCondensador, g);
	}
	
}
