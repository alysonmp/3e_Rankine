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
	JTextField fieldEvapCore = new JTextField("13",15);
	JTextField fieldEvapK = new JTextField("16",15);
	
	//Condensador Label
	JLabel labelCondCore = new JLabel("Core");
	JLabel labelCondK = new JLabel("K");
	
	//Condensador Field
	JTextField fieldCondCore = new JTextField("13",15);
	JTextField fieldCondK = new JTextField("16",15);
	
	//Regenerador Label
	JLabel labelRegCore = new JLabel("Core");
	JLabel labelRegK = new JLabel("K");
	JLabel labelRegEff = new JLabel("<html>&isin;</html>");
	
	//Regenerador Field
	JTextField fieldRegCore = new JTextField("13",15);
	JTextField fieldRegK = new JTextField("16",15);
	JTextField fieldRegEff = new JTextField("0",15);
	
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
		g.gridwidth = 1;
		g.fill = GridBagConstraints.HORIZONTAL;
		painelEvaporador.add(labelEvapCore, g);
		
		g.gridx = 1;
		g.gridy = 0;
		painelEvaporador.add(fieldEvapCore, g);
		
		g.gridx = 0;
		g.gridy = 1;
		painelEvaporador.add(labelEvapK, g);
		
		g.gridx = 1;
		g.gridy = 1;
		painelEvaporador.add(fieldEvapK, g);
		
		//Regenerador
		g.gridx = 0;
		g.gridy = 0;
		g.fill = GridBagConstraints.HORIZONTAL;
		painelRegenerador.add(labelRegCore, g);
		
		g.gridx = 1;
		g.gridy = 0;
		painelRegenerador.add(fieldRegCore, g);
		
		g.gridx = 0;
		g.gridy = 1;
		painelRegenerador.add(labelRegK, g);
		
		g.gridx = 1;
		g.gridy = 1;
		painelRegenerador.add(fieldRegK, g);
		
		g.gridx = 0;
		g.gridy = 2;
		painelRegenerador.add(labelRegEff, g);
		Font font = new Font("Arial", Font.BOLD, 18);
		labelRegEff.setFont(font);
		
		g.gridx = 1;
		g.gridy = 2;
		painelRegenerador.add(fieldRegEff, g);
		
		//Condensador
		g.gridx = 0;
		g.gridy = 0;
		g.fill = GridBagConstraints.HORIZONTAL;
		painelCondensador.add(labelCondCore, g);
		
		g.gridx = 1;
		g.gridy = 0;
		painelCondensador.add(fieldCondCore, g);
		
		g.gridx = 0;
		g.gridy = 1;
		painelCondensador.add(labelCondK, g);
		
		g.gridx = 1;
		g.gridy = 1;
		painelCondensador.add(fieldCondK, g);
		
		
		//PAINEIS
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

	public JTextField getFieldEvapCore() {
		return fieldEvapCore;
	}

	public void setFieldEvapCore(JTextField fieldEvapCore) {
		this.fieldEvapCore = fieldEvapCore;
	}

	public JTextField getFieldEvapK() {
		return fieldEvapK;
	}

	public void setFieldEvapK(JTextField fieldEvapK) {
		this.fieldEvapK = fieldEvapK;
	}

	public JTextField getFieldCondCore() {
		return fieldCondCore;
	}

	public void setFieldCondCore(JTextField fieldCondCore) {
		this.fieldCondCore = fieldCondCore;
	}

	public JTextField getFieldCondK() {
		return fieldCondK;
	}

	public void setFieldCondK(JTextField fieldCondK) {
		this.fieldCondK = fieldCondK;
	}

	public JTextField getFieldRegCore() {
		return fieldRegCore;
	}

	public void setFieldRegCore(JTextField fieldRegCore) {
		this.fieldRegCore = fieldRegCore;
	}

	public JTextField getFieldRegK() {
		return fieldRegK;
	}

	public void setFieldRegK(JTextField fieldRegK) {
		this.fieldRegK = fieldRegK;
	}

	public JTextField getFieldRegEff() {
		return fieldRegEff;
	}

	public void setFieldRegEff(JTextField fieldRegEff) {
		this.fieldRegEff = fieldRegEff;
	}
}
