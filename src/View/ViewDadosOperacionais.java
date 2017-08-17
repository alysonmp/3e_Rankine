/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Control.ControlPrincipal;
import Control.Ciclo2.ControlPdeVapor;
import Control.Ciclo2.ControlT_Ref;
import Control.Conversao.ControlConverte;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.hibernate.Session;

/**
 *
 * @author leonardo
 */
public class ViewDadosOperacionais extends JPanel{
    
    private JLabel lblP1 = new JLabel("<html>P<sub>1</sub></html>");
    private JLabel lblT1 = new JLabel("<html>T<sub>1</sub></html>");
    private JLabel lblSUP = new JLabel("SUP");
    private JLabel lblPINCH = new JLabel("PINCH");
    private JLabel lblTeff = new JLabel("<html>&eta;<sub>T</sub></html>");
    private JLabel lblBeff = new JLabel("<html>&eta;<sub>B</sub></html>");
    private JLabel lblTcon = new JLabel("<html>T<sub>con</sub></html>");
    
    private JTextField txtP1 = new JTextField("1229", 10);
    private JTextField txtT1 = new JTextField("413.81");
    private JTextField txtSUP = new JTextField("5");
    private JTextField txtPINCH = new JTextField("5");
    private JTextField txtBeff = new JTextField("0.8");
    private JTextField txtTeff = new JTextField("0.8");
    private JTextField txtTcon = new JTextField("0.8");
    
    //ENTRADA
    private JLabel labelFluidos = new JLabel("Fluído");
    private JComboBox<String> comboFluidos;
    
    private String[] pressoes = {"kPa", "bar", "atm"};
    private String[] temps = {"K", "°C", "°F"};
    private JComboBox comboP1 = new JComboBox(pressoes);
    private JComboBox comboT1 = new JComboBox(temps);
    
    URL url1 = this.getClass().getResource("/Images/Load/att1.png");
    URL url2 = this.getClass().getResource("/Images/Load/att2.png");
    Icon[] refreshIcon = {
    		new ImageIcon(new ImageIcon(url1).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)),
    		new ImageIcon(new ImageIcon(url2).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)),
    };

    private JLabel btnAtualizaTemp = new JLabel(refreshIcon[0]);
    private JLabel btnAtualizaPressao = new JLabel(refreshIcon[0]);
    
    private int flu = 1;
    
    private ControlConverte controlConverte;

    public ViewDadosOperacionais(ControlPrincipal ctrlPrincipal) {
        controlConverte = new ControlConverte();
        
        this.setLayout(new GridBagLayout());
        this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(1, Color.lightGray, Color.lightGray), "Entrada", 1, 2, new Font("Times New Roman", 1, 12), Color.darkGray));
        
        List results = ctrlPrincipal.getFluidos();
        Iterator iterator = results.iterator();
        
        Vector<String> fluidos = new Vector<>();
        while(iterator.hasNext()){
            fluidos.add((String) iterator.next());
        }
        comboFluidos = new JComboBox<String>(fluidos);
        comboFluidos.setFont(new Font("Arial", 0, 12));
        
        GridBagConstraints g = new GridBagConstraints();
        
        g.gridx = 0;
        g.gridy = 0;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        this.add(labelFluidos,g);
        
        g.gridx = 1;
        g.gridy = 0;
        g.gridwidth = 4;
        g.fill = GridBagConstraints.HORIZONTAL;
        this.add(comboFluidos,g);
        
        g.gridx = 0;
        g.gridy = 1;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        this.add(lblT1,g);
        
        g.gridx = 1;
        g.gridy = 1;
        g.gridwidth = 2;
        g.fill = GridBagConstraints.HORIZONTAL;
        this.add(txtT1,g);
        
        g.gridx = 3;
        g.gridy = 1;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        this.add(comboT1,g);
        
        g.gridx = 4;
        g.gridy = 1;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        this.add(btnAtualizaTemp,g);
        
        g.gridx = 0;
        g.gridy = 2;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        this.add(lblP1,g);
        
        g.gridx = 1;
        g.gridy = 2;
        g.gridwidth = 2;
        g.fill = GridBagConstraints.HORIZONTAL;
        this.add(txtP1,g);
        
        g.gridx = 3;
        g.gridy = 2;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        this.add(comboP1,g);
        
        g.gridx = 4;
        g.gridy = 2;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        this.add(btnAtualizaPressao,g);
        
        g.gridx = 0;
        g.gridy = 3;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        this.add(lblSUP,g);

        g.gridx = 1;
        g.gridy = 3;
        g.gridwidth = 2;
        g.fill = GridBagConstraints.HORIZONTAL;
        this.add(txtSUP,g);
        
        g.gridx = 0;
        g.gridy = 4;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        this.add(lblPINCH,g);

        g.gridx = 1;
        g.gridy = 4;
        g.gridwidth = 2;
        g.fill = GridBagConstraints.HORIZONTAL;
        this.add(txtPINCH,g);
        
        g.gridx = 0;
        g.gridy = 5;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        this.add(lblTeff,g);
        
        g.gridx = 1;
        g.gridy = 5;
        g.gridwidth = 2;
        g.fill = GridBagConstraints.HORIZONTAL;
        this.add(txtTeff,g);
        
        g.gridx = 0;
        g.gridy = 6;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        this.add(lblBeff,g);
        
        g.gridx = 1;
        g.gridy = 6;
        g.gridwidth = 2;
        g.fill = GridBagConstraints.HORIZONTAL;
        this.add(txtBeff,g);
        
        g.gridx = 0;
        g.gridy = 7;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        this.add(lblTcon,g);
        
        g.gridx = 1;
        g.gridy = 7;
        g.gridwidth = 2;
        g.fill = GridBagConstraints.HORIZONTAL;
        this.add(txtTcon,g);
        
        comboFluidos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                flu = comboFluidos.getSelectedIndex()+1;
            }
        });
        
        comboT1.addItemListener(new ItemListener() {
            
            String[] tipo = new String[2];
            int tip = 0;
            
            @Override
            public void itemStateChanged(ItemEvent ie) {
                
                tipo[tip] = ie.getItem().toString();
                tip++;
                if(tip == 2){
                    String valor = txtT1.getText();
                    if(!valor.isEmpty()){
                        txtT1.setText(String.valueOf(controlConverte.converte(tipo[0],tipo[1],Double.parseDouble(valor))));
                    } 
                    tip = 0;
                }
            }
        });
        
        comboP1.addItemListener(new ItemListener() {
            
            String[] tipo = new String[2];
            int tip = 0;
            
            @Override
            public void itemStateChanged(ItemEvent ie) {
                
                tipo[tip] = ie.getItem().toString();
                tip++;
                if(tip == 2){
                    String valor = txtP1.getText();
                    if(!valor.isEmpty()){
                        txtP1.setText(String.valueOf(controlConverte.converte(tipo[0],tipo[1],Double.parseDouble(valor))));
                    } 
                    tip = 0;
                }
            }
        });
        
        btnAtualizaTemp.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
            }

            @Override
            public void mousePressed(MouseEvent me) {
                btnAtualizaTemp.setIcon(refreshIcon[1]);
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                btnAtualizaTemp.setIcon(refreshIcon[0]);
                String fluNome = comboFluidos.getSelectedItem().toString();
                int flu = ctrlPrincipal.getFluidoCod(fluNome);
                
                txtT1.setText(controlConverte.round(Double.parseDouble(txtT1.getText().toString()), 3)+"");
                
                double Tee = Double.parseDouble(txtT1.getText().toString()) - Double.parseDouble(txtSUP.getText().toString());
                ControlPdeVapor pdevapor = new ControlPdeVapor(Tee, flu, ctrlPrincipal.getSession());
                
                txtP1.setText(controlConverte.round(pdevapor.getPs2(), 2)+"");
            }

            @Override
            public void mouseEntered(MouseEvent me) {
            }

            @Override
            public void mouseExited(MouseEvent me) {
            }
        });
        
        btnAtualizaPressao.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
            }

            @Override
            public void mousePressed(MouseEvent me) {
                btnAtualizaPressao.setIcon(refreshIcon[1]);
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                btnAtualizaPressao.setIcon(refreshIcon[0]);
                String fluNome = comboFluidos.getSelectedItem().toString();
                int flu = ctrlPrincipal.getFluidoCod(fluNome);
                
                txtP1.setText(controlConverte.round(Double.parseDouble(txtP1.getText().toString()), 3)+"");
                
                double P1 = Double.parseDouble(txtP1.getText().toString());
                
                ControlT_Ref tRef = new ControlT_Ref(P1, flu, ctrlPrincipal.getSession());
                double Tee = tRef.getTref();
                
                double T1 = Tee + Double.parseDouble(txtSUP.getText().toString());
                
                txtT1.setText(controlConverte.round(T1, 2)+"");
            }

            @Override
            public void mouseEntered(MouseEvent me) {
            }

            @Override
            public void mouseExited(MouseEvent me) {
            }
        });
    }

    public JTextField getTxtP1() {
        return txtP1;
    }

    public JTextField getTxtT1() {
        return txtT1;
    }

    public JTextField getTxtSUP() {
        return txtSUP;
    }

    public JTextField getTxtBeff() {
        return txtBeff;
    }

    public JTextField getTxtTeff() {
        return txtTeff;
    }

    public JTextField getTxtTcon() {
        return txtTcon;
    }

    public String[] getPressoes() {
        return pressoes;
    }

    public String[] getTemps() {
        return temps;
    }

    public JComboBox getComboP1() {
        return comboP1;
    }

    public JComboBox getComboT1() {
        return comboT1;
    }

	public JTextField getTxtPINCH() {
		return txtPINCH;
	}

	public void setTxtPINCH(JTextField txtPINCH) {
		this.txtPINCH = txtPINCH;
	}

	public void setTxtP1(JTextField txtP1) {
		this.txtP1 = txtP1;
	}

	public void setTxtT1(JTextField txtT1) {
		this.txtT1 = txtT1;
	}

	public void setTxtSUP(JTextField txtSUP) {
		this.txtSUP = txtSUP;
	}

	public void setTxtBeff(JTextField txtBeff) {
		this.txtBeff = txtBeff;
	}

	public void setTxtTeff(JTextField txtTeff) {
		this.txtTeff = txtTeff;
	}

	public void setTxtTcon(JTextField txtTcon) {
		this.txtTcon = txtTcon;
	}

	public int getFlu() {
		return flu;
	}

	public void setFlu(int flu) {
		this.flu = flu;
	}
}
