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
import javax.swing.JTextField;


public class ViewPrincipal extends JFrame{

    //Labels
    //ENTRADA
    private JLabel lblP1 = new JLabel("P1: ");
    private JLabel lblT1 = new JLabel("T1: ");
    private JLabel lblSUP = new JLabel("SUP: ");
    private JLabel lblkm = new JLabel("km: ");
    private JLabel lblTf = new JLabel("Tf: ");
    private JLabel lblPf = new JLabel("Pf: ");
    private JLabel lblMf = new JLabel("Mf: ");
    private JLabel lblcompressor = new JLabel("Compressor: ");
    private JLabel lbleff = new JLabel("eff: ");
    private JLabel lblBeff = new JLabel("Beff: ");
    private JLabel lblTeff = new JLabel("Teff: ");
    private JLabel lblPINCH = new JLabel("PINCH: ");
    private JLabel lblFluido = new JLabel("Fluido: ");
    
    //SAIDA
    private JLabel lblWt = new JLabel("Wt: ");
    private JLabel lblWn = new JLabel("Wn: ");
    private JLabel lblWb = new JLabel("Wb: ");
    private JLabel lblQevp = new JLabel("Qevp: ");
    private JLabel lblQcon = new JLabel("Qcon: ");
    private JLabel lblAt = new JLabel("At: ");
    private JLabel lblQreg = new JLabel("Qreg: ");
    private JLabel lblATevp = new JLabel("ATevp: ");
    private JLabel lblATcon = new JLabel("ATcon: ");
    private JLabel lblATreg = new JLabel("ATreg: ");

    //TextFields
    //ENTRADA
    private JTextField txtP1 = new JTextField("1229", 10);
    private JTextField txtT1 = new JTextField("413.81");
    private JTextField txtSUP = new JTextField("5");
    private JTextField txtkm = new JTextField("16");
    private JTextField txtTf = new JTextField("431.15");
    private JTextField txtPf = new JTextField("408.35");
    private JTextField txtMf = new JTextField("408.35");
    private JTextField txteff = new JTextField("0.3");
    private JTextField txtBeff = new JTextField("0.8");
    private JTextField txtTeff = new JTextField("0.8");
    private JTextField txtPINCH = new JTextField("4");
    
    //SAIDA
    private JLabel txtWt = new JLabel("0");
    private JLabel txtWn = new JLabel("0");
    private JLabel txtWb = new JLabel("0");
    private JLabel txtQevp = new JLabel("0");
    private JLabel txtQcon = new JLabel("0");
    private JLabel txtAt = new JLabel("0");
    private JLabel txtQreg = new JLabel("0");
    private JLabel txtATevp = new JLabel("0");
    private JLabel txtATcon = new JLabel("0");
    private JLabel txtATreg = new JLabel("0");
    
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
    private JPanel painelSaida = new JPanel(new GridBagLayout());
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
        painelSaida.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(1, Color.lightGray, Color.lightGray), "Saída", 1, 2, new Font("Times New Roman", 1, 12), Color.darkGray));

        List results = ctrPrincipal.getFluidos();
        Iterator iterator = results.iterator();
        
        Vector<String> fluidos = new Vector<>();
        while(iterator.hasNext()){
            fluidos.add((String) iterator.next());
        }
        comboFluidos = new JComboBox<String>(fluidos);
        comboFluidos.setFont(new Font("Arial", 0, 11));
        
        GridBagConstraints g = new GridBagConstraints();
       
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
        g.gridy = 1;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelEntrada.add(lblFluido,g);
        
        g.gridx = 1;
        g.gridy = 1;
        g.gridwidth = 2;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelEntrada.add(comboFluidos,g);
        
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
        g.gridy = 8;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelEntrada.add(lblkm,g);

        g.gridx = 1;
        g.gridy = 8;
        g.gridwidth = 2;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelEntrada.add(txtkm,g);
        
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
        
        //PAINEL SAIDA
        g.anchor = GridBagConstraints.LINE_START;
        g.gridx = 0;
        g.gridy = 0;
        g.gridwidth = 1;
        g.weightx = 2;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelSaida.add(lblWt,g);
        
        g.gridx = 1;
        g.gridy = 0;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelSaida.add(txtWt,g);
        
        g.gridx = 0;
        g.gridy = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelSaida.add(lblWn,g);
        
        g.gridx = 1;
        g.gridy = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelSaida.add(txtWn,g);
        
        g.gridx = 0;
        g.gridy = 2;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelSaida.add(lblWb,g);
        
        g.gridx = 1;
        g.gridy = 2;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelSaida.add(txtWb,g);
        
        g.gridx = 0;
        g.gridy = 3;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelSaida.add(lblQevp,g);
        
        g.gridx = 1;
        g.gridy = 3;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelSaida.add(txtQevp,g);
        
        g.gridx = 0;
        g.gridy = 4;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelSaida.add(lblQcon,g);
        
        g.gridx = 1;
        g.gridy = 4;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelSaida.add(txtQcon,g);
        
        g.gridx = 0;
        g.gridy = 5;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelSaida.add(lblAt,g);
        
        g.gridx = 1;
        g.gridy = 5;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelSaida.add(txtAt,g);
        
        g.gridx = 0;
        g.gridy = 6;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelSaida.add(lblQreg,g);
        
        g.gridx = 1;
        g.gridy = 6;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelSaida.add(txtQreg,g);
        
        g.gridx = 0;
        g.gridy = 7;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelSaida.add(lblATevp,g);
        
        g.gridx = 1;
        g.gridy = 7;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelSaida.add(txtATevp,g);
        
        g.gridx = 0;
        g.gridy = 8;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelSaida.add(lblATcon,g);
        
        g.gridx = 1;
        g.gridy = 8;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelSaida.add(txtATcon,g);
        
        g.gridx = 0;
        g.gridy = 9;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelSaida.add(lblATreg,g);
        
        g.gridx = 1;
        g.gridy = 9;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelSaida.add(txtATreg,g);
        
        //PAINEL MAIOR
        g.gridx = 0;
        g.gridy = 0;
        g.gridwidth = 3;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelDados.add(painelEntrada,g);
        
        g.gridx = 0;
        g.gridy = 1;
        g.gridwidth = 3;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelDados.add(painelSaida,g);
        
        g.gridx = 2;
        g.gridy = 2;
        g.gridwidth = 1;
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

    public JPanel getPainelSaida() {
        return painelSaida;
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

    public JLabel getTxtWt() {
        return txtWt;
    }

    public void setTxtWt(JLabel txtWt) {
        this.txtWt = txtWt;
    }

    public JLabel getTxtWn() {
        return txtWn;
    }

    public void setTxtWn(JLabel txtWn) {
        this.txtWn = txtWn;
    }

    public JLabel getTxtWb() {
        return txtWb;
    }

    public void setTxtWb(JLabel txtWb) {
        this.txtWb = txtWb;
    }

    public JLabel getTxtQevp() {
        return txtQevp;
    }

    public void setTxtQevp(JLabel txtQevp) {
        this.txtQevp = txtQevp;
    }

    public JLabel getTxtQcon() {
        return txtQcon;
    }

    public void setTxtQcon(JLabel txtQcon) {
        this.txtQcon = txtQcon;
    }

    public JLabel getTxtAt() {
        return txtAt;
    }

    public void setTxtAt(JLabel txtAt) {
        this.txtAt = txtAt;
    }

    public JLabel getTxtQreg() {
        return txtQreg;
    }

    public void setTxtQreg(JLabel txtQreg) {
        this.txtQreg = txtQreg;
    }

    public int getFON() {
        return FON;
    }

    public void setFON(int FON) {
        this.FON = FON;
    }

    public JTextField getTxtkm() {
        return txtkm;
    }

    public void setTxtkm(JTextField txtkm) {
        this.txtkm = txtkm;
    }

    public int getFlu() {
        return flu;
    }

    public void setFlu(int flu) {
        this.flu = flu;
    }

    public JLabel getTxtATevp() {
        return txtATevp;
    }

    public void setTxtATevp(JLabel txtATevp) {
        this.txtATevp = txtATevp;
    }

    public JLabel getTxtATcon() {
        return txtATcon;
    }

    public void setTxtATcon(JLabel txtATcon) {
        this.txtATcon = txtATcon;
    }

    public JLabel getTxtATreg() {
        return txtATreg;
    }

    public void setTxtATreg(JLabel txtATreg) {
        this.txtATreg = txtATreg;
    }
    
    
}
