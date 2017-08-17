/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Control.ControlPrincipal;
import Control.Conversao.ControlConverte;
import Control.Start;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;


public class ViewPrincipal extends JFrame{

	JTabbedPane tabbedPane = new JTabbedPane();
    
    //BUTTON
    private JButton btnCalcular = new JButton("Calcular");
            
    //Panel
    private JPanel painelDados = new JPanel(new GridBagLayout());
    
    private ViewFonteCalor fonteCalor;
    private ViewDadosOperacionais dadosOp;
    private ViewTrocadores trocadores;
    
    private JFrame frameEspera;
    
    private ControlConverte controlConverte;
    
    public ViewPrincipal(ControlPrincipal ctrPrincipal) {
        controlConverte = new ControlConverte();
        
        this.setLayout(new FlowLayout());
        
        GridBagConstraints g = new GridBagConstraints();
        
        fonteCalor = new ViewFonteCalor(ctrPrincipal);
        tabbedPane.addTab("Fonte de Calor", fonteCalor);
        
        dadosOp = new ViewDadosOperacionais(ctrPrincipal);
        tabbedPane.addTab("Dados Operacionais", dadosOp);
        
        trocadores = new ViewTrocadores();
        tabbedPane.addTab("Trocadores de Calor", trocadores);
        
        
        //PAINEL MAIOR
        g.gridx = 0;
        g.gridy = 1;
        g.gridwidth = 3;
        g.insets = new Insets(0, 0, 0, 0);
        g.fill = GridBagConstraints.HORIZONTAL;
        painelDados.add(tabbedPane,g);
        
        g.gridx = 2;
        g.gridy = 3;
        g.weightx = 0.5;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelDados.add(btnCalcular,g);
     
        this.add(painelDados);
        
        btnCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Thread t1 = new Thread(new Runnable() {
                @Override
                    public void run() {
                        Start start = new Start(ctrPrincipal.getSession(), ctrPrincipal);
                    }
                });
                t1.start();
                
                new Thread(new Runnable() {
                    @Override
                    public void run() {    
                        try {
                            ctrPrincipal.getViewPrincipal().setEnabled(false);
                            ViewEspera painelEspera = new ViewEspera(ctrPrincipal);

                            frameEspera = new JFrame();
                            frameEspera.setUndecorated(true);
                            frameEspera.add(painelEspera.getPanelEspera());
                            frameEspera.setAlwaysOnTop(true);
                            frameEspera.setBounds((ctrPrincipal.getViewPrincipal().getWidth()/2)-100, (ctrPrincipal.getViewPrincipal().getHeight()/2)-50, 200, 100);
                            frameEspera.setResizable(false);
                            frameEspera.setVisible(true);
                            frameEspera.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            frameEspera.revalidate();
                            frameEspera.repaint();

                            t1.join();
                            
                            frameEspera.dispose();
                            ctrPrincipal.getViewPrincipal().setEnabled(true);
                        }catch(InterruptedException ex){
                            
                        }
                    }
                }).start();
            }
        });
    }

    public JPanel getPainelDados() {
        return painelDados;
    }

	public ViewFonteCalor getFonteCalor() {
		return fonteCalor;
	}

	public void setFonteCalor(ViewFonteCalor fonteCalor) {
		this.fonteCalor = fonteCalor;
	}

	public ViewDadosOperacionais getDadosOp() {
		return dadosOp;
	}

	public void setDadosOp(ViewDadosOperacionais dadosOp) {
		this.dadosOp = dadosOp;
	}

	public ViewTrocadores getTrocadores() {
		return trocadores;
	}

	public void setTrocadores(ViewTrocadores trocadores) {
		this.trocadores = trocadores;
	}
}
