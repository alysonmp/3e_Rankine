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
	
    //Labels
    //ENTRADA
    private JLabel lblP1 = new JLabel("P1: ");
    private JLabel lblT1 = new JLabel("T1: ");
    private JLabel lblSUP = new JLabel("SUP: ");
    private JLabel lblTf = new JLabel("Tf: ");
    private JLabel lblPf = new JLabel("Pf: ");
    private JLabel lblMf = new JLabel("Mf: ");
    private JLabel lblcompressor = new JLabel("Compressor: ");
    private JLabel lbleff = new JLabel("eff: ");
    private JLabel lblBeff = new JLabel("Beff: ");
    private JLabel lblTeff = new JLabel("Teff: ");
    private JLabel lblPINCH = new JLabel("PINCH: ");
    private JLabel lblFluido = new JLabel("Fluido: ");

    //TextFields
    //ENTRADA
    private JTextField txtP1 = new JTextField("1229", 10);
    private JTextField txtT1 = new JTextField("413.81");
    private JTextField txtSUP = new JTextField("5");
    private JTextField txtTf = new JTextField("431.15");
    private JTextField txtPf = new JTextField("408.35");
    private JTextField txtMf = new JTextField("408.35");
    private JTextField txteff = new JTextField("0.3");
    private JTextField txtBeff = new JTextField("0.8");
    private JTextField txtTeff = new JTextField("0.8");
    private JTextField txtPINCH = new JTextField("4");
    
    //COMBOBOX
    private String[] pressoes = {"kPa", "bar", "atm"};
    private String[] temps = {"K", "°C", "°F"};
    
    private JComboBox comboPf = new JComboBox(pressoes);
    private JComboBox comboP1 = new JComboBox(pressoes);
    private JComboBox comboTf = new JComboBox(temps);
    private JComboBox comboT1 = new JComboBox(temps);
    
    private String[] fluidos = {"Água", "Ar", "Compressor 1", "Compressor 2", "Compressor 3", "Compressor 4", "Compressor 5", "Gases"};
    private JComboBox cmbCompressor = new JComboBox(fluidos);
    
    private JLabel labelFluidos = new JLabel("Fluído");
    private JComboBox<String> comboFluidos;
    
    //BUTTON
    private JButton btnCalcular = new JButton("Calcular");
            
    //Panel
    private JPanel painelEntrada = new JPanel(new GridBagLayout());
    private JPanel painelDados = new JPanel(new GridBagLayout());
    
    private JFrame frameEspera;
    
    private int comp = 0;
    private int FON = 1;
    private int flu = 1;
    
    private ControlConverte controlConverte;
    
    public ViewPrincipal(ControlPrincipal ctrPrincipal) {
        controlConverte = new ControlConverte();
        
        this.setLayout(new FlowLayout());
        
        painelEntrada.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(1, Color.lightGray, Color.lightGray), "Entrada", 1, 2, new Font("Times New Roman", 1, 12), Color.darkGray));

        List results = ctrPrincipal.getFluidos();
        Iterator iterator = results.iterator();
        
        Vector<String> fluidos = new Vector<>();
        while(iterator.hasNext()){
            fluidos.add((String) iterator.next());
        }
        comboFluidos = new JComboBox<String>(fluidos);
        comboFluidos.setFont(new Font("Arial", 0, 11));
        
        GridBagConstraints g = new GridBagConstraints();
        
        tabbedPane.addTab("Fonte de Calor", component);
        tabbedPane.addTab("Dados Operacionais", component);
        
        ViewTrocadores trocadores = new ViewTrocadores();
        tabbedPane.addTab("Trocadores de Calor", trocadores);
        
        //PANEL ENTRADA
        g.gridx = 0;
        g.gridy = 0;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelEntrada.add(lblcompressor,g);
        
        g.gridx = 1;
        g.gridy = 0;
        g.gridwidth = 2;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelEntrada.add(cmbCompressor,g);
        
        g.gridx = 0;
        g.gridy = 2;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelEntrada.add(lblT1,g);
        
        g.gridx = 1;
        g.gridy = 2;
        g.gridwidth = 2;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelEntrada.add(txtT1,g);
        
        g.gridx = 3;
        g.gridy = 2;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelEntrada.add(comboT1,g);
        
        g.gridx = 0;
        g.gridy = 3;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelEntrada.add(lblP1,g);
        
        g.gridx = 1;
        g.gridy = 3;
        g.gridwidth = 2;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelEntrada.add(txtP1,g);
        
        g.gridx = 3;
        g.gridy = 3;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelEntrada.add(comboP1,g);
        
        g.gridx = 0;
        g.gridy = 4;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelEntrada.add(lblTf,g);
        
        g.gridx = 1;
        g.gridy = 4;
        g.gridwidth = 2;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelEntrada.add(txtTf,g);
        
        g.gridx = 3;
        g.gridy = 4;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelEntrada.add(comboTf,g);
        
        g.gridx = 0;
        g.gridy = 5;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelEntrada.add(lblPf,g);
        
        g.gridx = 1;
        g.gridy = 5;
        g.gridwidth = 2;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelEntrada.add(txtPf,g);
        
        g.gridx = 3;
        g.gridy = 5;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelEntrada.add(comboPf,g);
        
        g.gridx = 0;
        g.gridy = 6;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelEntrada.add(lblMf,g);
        
        g.gridx = 1;
        g.gridy = 6;
        g.gridwidth = 2;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelEntrada.add(txtMf,g);
        
        g.gridx = 0;
        g.gridy = 9;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelEntrada.add(lblSUP,g);

        g.gridx = 1;
        g.gridy = 9;
        g.gridwidth = 2;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelEntrada.add(txtSUP,g);
        
        g.gridx = 0;
        g.gridy = 10;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelEntrada.add(lbleff,g);
        
        g.gridx = 1;
        g.gridy = 10;
        g.gridwidth = 2;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelEntrada.add(txteff,g);
        
        g.gridx = 0;
        g.gridy = 11;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelEntrada.add(lblBeff,g);
        
        g.gridx = 1;
        g.gridy = 11;
        g.gridwidth = 2;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelEntrada.add(txtBeff,g);
        
        g.gridx = 0;
        g.gridy = 12;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelEntrada.add(lblTeff,g);
        
        g.gridx = 1;
        g.gridy = 12;
        g.gridwidth = 2;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelEntrada.add(txtTeff,g);
        
        g.gridx = 0;
        g.gridy = 13;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelEntrada.add(lblPINCH,g);
        
        g.gridx = 1;
        g.gridy = 13;
        g.gridwidth = 2;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelEntrada.add(txtPINCH,g);
        
        
        
        //PAINEL MAIOR
        g.gridx = 0;
        g.gridy = 0;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelDados.add(lblFluido,g);
        
        g.gridx = 1;
        g.gridy = 0;
        g.gridwidth = 2;
        g.fill = GridBagConstraints.HORIZONTAL;
        g.insets = new Insets(0, 0, 10, 0);
        painelDados.add(comboFluidos,g);
        
        g.gridx = 0;
        g.gridy = 1;
        g.gridwidth = 3;
        g.insets = new Insets(0, 0, 0, 0);
        g.fill = GridBagConstraints.HORIZONTAL;
        painelDados.add(painelEntrada,g);
        
        g.gridx = 0;
        g.gridy = 3;
        g.gridwidth = 3;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelDados.add(btnCalcular,g);
     
        this.add(painelDados);
        
        cmbCompressor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                FON = cmbCompressor.getSelectedIndex()+1;
                switch(FON){
                    case 3:
                        comp = 1;
                        txtMf.setText(55.54+"");
                        txtTf.setText(415.15+"");
                        txtPf.setText(1144.4+"");
                        break;
                    case 4:
                        comp = 2;
                        txtMf.setText(55.54+"");
                        txtTf.setText(417.15+"");
                        txtPf.setText(3133.8+"");
                        break;
                    case 5: 
                        comp = 3;
                        txtMf.setText(55.54+"");
                        txtTf.setText(418.15+"");
                        txtPf.setText(8825.6+"");
                        break;
                    case 6:
                        comp = 4;
                        txtMf.setText(55.54+"");
                        txtTf.setText(408.15+"");
                        txtPf.setText(25109+"");
                        break;
                    case 7: 
                        comp = 5;
                        txtMf.setText(61.78+"");
                        txtTf.setText(431.15+"");
                        txtPf.setText(8196.1+"");
                        break;
                    default:
                        comp = 0;
                        break;
                }
            }
        });
        
        comboFluidos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                flu = comboFluidos.getSelectedIndex()+1;
            }
        });
        
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
        
        comboTf.addItemListener(new ItemListener() {
            
            String[] tipo = new String[2];
            int tip = 0;
            
            @Override
            public void itemStateChanged(ItemEvent ie) {
                
                tipo[tip] = ie.getItem().toString();
                tip++;
                if(tip == 2){
                    String valor = txtTf.getText();
                    if(!valor.isEmpty()){
                        txtTf.setText(String.valueOf(controlConverte.converte(tipo[0],tipo[1],Double.parseDouble(valor))));
                    } 
                    tip = 0;
                }
            }
        });
        
        comboPf.addItemListener(new ItemListener() {
            
            String[] tipo = new String[2];
            int tip = 0;
            
            @Override
            public void itemStateChanged(ItemEvent ie) {
                
                tipo[tip] = ie.getItem().toString();
                tip++;
                if(tip == 2){
                    String valor = txtPf.getText();
                    if(!valor.isEmpty()){
                        txtPf.setText(String.valueOf(controlConverte.converte(tipo[0],tipo[1],Double.parseDouble(valor))));
                    } 
                    tip = 0;
                }
            }
        });
        
        
    }

    public JLabel getLblP1() {
        return lblP1;
    }

    public JLabel getLblSUP() {
        return lblSUP;
    }

    public JLabel getLblTf() {
        return lblTf;
    }

    public JLabel getLblcompressor() {
        return lblcompressor;
    }

    public JLabel getLbleff() {
        return lbleff;
    }

    public JTextField getTxtP1() {
        return txtP1;
    }

    public JTextField getTxtSUP() {
        return txtSUP;
    }

    public JTextField getTxtTf() {
        return txtTf;
    }
    
    public JTextField getTxteff() {
        return txteff;
    }

    public JPanel getPainelEntrada() {
        return painelEntrada;
    }

    public JPanel getPainelDados() {
        return painelDados;
    }

    public JLabel getLblBeff() {
        return lblBeff;
    }

    public void setLblBeff(JLabel lblBeff) {
        this.lblBeff = lblBeff;
    }

    public JLabel getLblTeff() {
        return lblTeff;
    }

    public void setLblTeff(JLabel lblTeff) {
        this.lblTeff = lblTeff;
    }
    
    public JTextField getTxtBeff() {
        return txtBeff;
    }

    public void setTxtBeff(JTextField txtBeff) {
        this.txtBeff = txtBeff;
    }

    public JTextField getTxtTeff() {
        return txtTeff;
    }

    public void setTxtTeff(JTextField txtTeff) {
        this.txtTeff = txtTeff;
    }

    public int getComp() {
        return comp;
    }

    public void setComp(int comp) {
        this.comp = comp;
    }

    public JTextField getTxtPINCH() {
        return txtPINCH;
    }

    public void setTxtPINCH(JTextField txtPINCH) {
        this.txtPINCH = txtPINCH;
    }

    public JTextField getTxtT1() {
        return txtT1;
    }

    public void setTxtT1(JTextField txtT1) {
        this.txtT1 = txtT1;
    }

    public JTextField getTxtPf() {
        return txtPf;
    }

    public void setTxtPf(JTextField txtPf) {
        this.txtPf = txtPf;
    }

    public JTextField getTxtMf() {
        return txtMf;
    }

    public void setTxtMf(JTextField txtMf) {
        this.txtMf = txtMf;
    }

    

    public int getFON() {
        return FON;
    }

    public void setFON(int FON) {
        this.FON = FON;
    }

    public int getFlu() {
        return flu;
    }

    public void setFlu(int flu) {
        this.flu = flu;
    }

    public JComboBox getComboPf() {
        return comboPf;
    }

    public void setComboPf(JComboBox comboPf) {
        this.comboPf = comboPf;
    }

    public JComboBox getComboP1() {
        return comboP1;
    }

    public void setComboP1(JComboBox comboP1) {
        this.comboP1 = comboP1;
    }

    public JComboBox getComboTf() {
        return comboTf;
    }

    public void setComboTf(JComboBox comboTf) {
        this.comboTf = comboTf;
    }

    public JComboBox getComboT1() {
        return comboT1;
    }

    public void setComboT1(JComboBox comboT1) {
        this.comboT1 = comboT1;
    }
}
