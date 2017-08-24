package Control.Ciclo2;

import org.hibernate.Session;

public class ControlMassa1 {

	private String mensagem = "";
	
	private double m, Q, Tfout, PINCH, Hlat, Hsen, Hsup, T1s, titulo, Hsat, tit, Qfon1, HL, HV, Hout, Hin, Burbuja, HLsat, HVsat, HLb, HVb;
	private double[] X;
	
	public ControlMassa1(double H4, double H1, double H6, double P1, int ii, double Pref, double Tref, double T1, double T6, double SUP, int FON, double PINCH, double mf, double Tf, double Pf, Session session) {
		
		if(FON == 1) {
			ControlPdeVapor pdevapor = new ControlPdeVapor(Tf, 1, session);
		    double Psf = pdevapor.getPs2();
		    if(Psf > Pf) {
		    		mensagem = "Aumentar a pressão da fonte de calor";
		        return;
		    }
		    
		    double[] X = {0.0, 0, 0, 20.1, 75.9, 0, 0, 0};
		    titulo = 0;
		    mf = mf/18; //%Dividi do peso molecular para pasar a kmol
		}else {
		    X = new double[] {0.0, 0, 0, 21, 79, 0, 0, 0};
		    titulo = 1;
		    mf = mf/28.97; //%Dividi do peso molecular para pasar a kmol
		}
		
		T1s = T1-SUP;
		double Tf2 = T1s+PINCH;

		ControlH_Sistema hSistema = new ControlH_Sistema(T1s, P1, Pref, Tref, ii, session);
		
		HLsat = hSistema.getHL();
		double EntEVP = H1-HLsat;
		
		if(Tf2 > Tf) {
		     mensagem = "Número errado de argumentos de entrada";
		     return;
		}
		
		if(FON == 1) {
			hSistema = new ControlH_Sistema(Tf, Pf, Pref, Tref, 1, session);
			double HL = hSistema.getHL();
			double HV = hSistema.getHV();
			double Hin = HL + (titulo*(HV-HL)) ;
			   
			hSistema = new ControlH_Sistema(Tf2, Pf, Pref, Tref, 1, session);
			double HLb = hSistema.getHL();
			double HVb = hSistema.getHV();
			    
			ControlT_Ref tRef = new ControlT_Ref(Pf, 1, session);
			double Tag = tRef.getTref();
		    
			if(Tag < Tf2) {
			      Hsat = HVb;
			      tit = 1;
			}else {  
			      Hsat = HLb;
			      tit = 0;
			}
					       
			Qfon1=(Hin-Hsat)*mf;
		}else {
			ControlExergia_Gases exergia = new ControlExergia_Gases(mf, Tf, X, session);
			double Ein = exergia.getEin();
			double Hin = exergia.getHin();
			double Sin = exergia.getSin();
			double bgasin = exergia.getBgas();
			double ETin = exergia.getET();
			
			exergia = new ControlExergia_Gases(mf, Tf2, X, session);
			double Esat = exergia.getEin();
			double Hsat = exergia.getHin();
			double Ssat = exergia.getSin();
			double bgassat = exergia.getBgas();
			double ETsat = exergia.getET();
			
		    Qfon1 = (Hin-Hsat)*mf;
		}
		
		m = (Qfon1/EntEVP)*1.05;

		double QTf = m*(H1-H6);

		//%%%%%% teste de T de saida da fonte de calor %%%%
		double Test = T1s;
		double erro = 1;
		double DT = (T1-T6)/2;
		int it = 0;
		
		while(erro > 0.0005) {
		    it=it+1;
            if(it > 10000) {
                break;
            }
		    
		    if(FON == 1) {
		    		hSistema = new ControlH_Sistema(Test, Pf, Pref, Tref, 1, session);
		    		HL = hSistema.getHL();
		    		HV = hSistema.getHV();
		    		
		    		Hout = HL + (tit*(HV-HL));
		    		Q = (Hin-Hout)*mf;
		    		erro = Math.abs((QTf-Q)/QTf);
		    		Burbuja = Q-QTf;
		   
		        if(erro > 0.0005 && Burbuja < 0) {
		        		Test = Test-DT;
		        		DT = DT/2;
		        		if(DT < 0.0005) {
		        			DT=0.0005456321;
		        		}
		        }else {
		        		if(erro > 0.0005 && Burbuja > 0) {
		        			Test = Test+DT;
		        			DT = DT/2;
		        			if(DT < 0.0005) {
		        				DT = 0.0003975313;
		        			}
		        		}
		        }
		    }else {
		    		ControlExergia_Gases exergia = new ControlExergia_Gases(mf, Test, X, session);
		    
		    		Q = (Hin-Hout)*mf;
		    		erro = Math.abs((QTf-Q)/QTf);
		    		Burbuja=Q-QTf;
		    		if(erro > 0.0005 && Burbuja < 0) {
		    			Test = Test-DT;
		    			DT = DT/2;
		    			if(DT < 0.0005) {
		    				DT = 0.0005456321;
		    			}
		    		}else {
		    			if(erro > 0.0005 && Burbuja > 0) {
		    				Test = Test+DT;
		    				DT = DT/2;
		    				if(DT < 0.0005) {
		    					DT = 0.0003975313;
		    				}
		    			}
		    		}
		    }
		    if(Test <= T6) {
		        erro = 0;
		    }
		}
		
		Tfout=Test;
		
		//%%%%%%%%%%%%%%%%%%%%%%%%%% CorreÁ„o da massa %%%%%%%%%%%%%%%%%%%
		it=0;
		while(T6+5 > Tfout) {
		     PINCH=PINCH+0.1;   
		     it=it+1;
	            if(it > 10000) {
	                break;
				}
		     
		     T1s = T1-SUP;
		     Tf2=T1s+PINCH;
		     
		     hSistema = new ControlH_Sistema(T1s, P1, Pref, Tref, ii, session);
		     HLsat = hSistema.getHL();
		     HVsat = hSistema.getHV();
		     EntEVP = H1-HLsat;

		     if(FON == 1) {
		    	 	hSistema = new ControlH_Sistema(Tf, Pf, Pref, Tref, ii, session);
		    	 	HLsat = hSistema.getHL();
			    HVsat = hSistema.getHV();
		      
		        Hin=HL + (titulo*(HV-HL)) ;
		        
		        hSistema = new ControlH_Sistema(Tf2, Pf, Pref, Tref, 1, session);
		    	 	HLb = hSistema.getHL();
			    HVb = hSistema.getHV();
			    
			    ControlT_Ref tRef = new ControlT_Ref(Pf, 1, session);
			    double Tag = tRef.getTref();
		              
	            if(Tag < Tf2) {
	                Hsat = HVb;
	                tit = 1;
	            }else {  
                    Hsat = HLb;
                    tit = 0;
	            }
	            
	            Qfon1=(Hin-Hsat)*mf;
	             
		     }else {
		    	 	ControlExergia_Gases exergia = new ControlExergia_Gases(mf, Tf, X, session);
				double Ein = exergia.getEin();
				double Hin = exergia.getHin();
				double Sin = exergia.getSin();
				double bgasin = exergia.getBgas();
				double ETin = exergia.getET();
				
				exergia = new ControlExergia_Gases(mf, Tf2, X, session);
				double Esat = exergia.getEin();
				double Hsat = exergia.getHin();
				double Ssat = exergia.getSin();
				double bgassat = exergia.getBgas();
				double ETsat = exergia.getET();
		     
	            Qfon1=(Hin-Hsat)*mf;
		    }
		     
            m=(Qfon1/EntEVP);

            QTf=m*(H1-H6);

            //%%%%%% teste de T de saida da fonte de calor %%%%
            if(it > 10000) {
            		break;
            }
            
            Test = T6;
            erro = 1;
            DT = 5;
            it = 0;
            while(erro > 0.0005) {
                it=it+1;
                if(it > 10000) {
                    break;
                }
                
                if(FON == 1) {
                		hSistema = new ControlH_Sistema(Test, Pf, Pref, Tref, 1, session);
                		HL = hSistema.getHL();
                		HV = hSistema.getHV();
                		
                		Hout = HL + (tit*(HV-HL));
                		Q = (Hin-Hout)*mf;
                		erro = Math.abs((QTf-Q)/QTf);
                    Burbuja = Q-QTf;
               
                    if(erro > 0.0005 && Burbuja < 0) {
                    		Test = Test-DT;
                    		DT = DT/2;
                    		if(DT < 0.0005) {
                    			DT = 0.0005456321;
                    		}
                    }else{
                    		if(erro > 0.0005 && Burbuja > 0) {
                    
                    			Test = Test+DT;
                    			DT = DT/2;
                    			if(DT < 0.0005) {
                    				DT = 0.0003975313;
                    			}
                    		}
                    }
                }else {
                		ControlExergia_Gases exergia = new ControlExergia_Gases(mf, Test, X, session);
                		double Eout = exergia.getEin();
                		double Hout = exergia.getHin();
                		double Sout = exergia.getSin();
                		double bgasout = exergia.getBgas();
                		double ETout = exergia.getET();
                		
                		Q = (Hin-Hout)*mf;
                    erro = Math.abs((QTf-Q)/QTf);
                    Burbuja = Q-QTf;
                    if(erro > 0.0005 && Burbuja < 0) {
                        Test = Test-DT;
                        DT = DT/2;
                        if(DT < 0.0005) {
                            DT = 0.0005456321;
                        }else { 
                        		if(erro > 0.0005 && Burbuja > 0) {
                                Test = Test+DT;
                                DT = DT/2;
                                if(DT < 0.0005) {
                                    DT = 0.0003975313;
                                }
                        		}
                        }
                    }
                    
                    if(Test <= T6) {
                    		erro=0;
            			}
                        
                }
            }
            
            Tfout=Test;          

		     T1s = T1-SUP;
		     Tf2 = T1s+PINCH;
		     
		     hSistema = new ControlH_Sistema(T1s, P1, Pref, Tref, ii, session);
		     HLsat = hSistema.getHL();
		     HVsat = hSistema.getHV();
		     
		     Hlat = HVsat-HLsat; //%kJ/kmol
		     Hsen = HLsat-H6; //%kJ/kmol
		     Hsup = H1-HVsat; //%kJ/kmol              
		}
	            
	}

	public double getM() {
		return m;
	}

	public void setM(double m) {
		this.m = m;
	}

	public double getQ() {
		return Q;
	}

	public void setQ(double q) {
		Q = q;
	}

	public double getTfout() {
		return Tfout;
	}

	public void setTfout(double tfout) {
		Tfout = tfout;
	}

	public double getPINCH() {
		return PINCH;
	}

	public void setPINCH(double pINCH) {
		PINCH = pINCH;
	}

	public double getHlat() {
		return Hlat;
	}

	public void setHlat(double hlat) {
		Hlat = hlat;
	}

	public double getHsen() {
		return Hsen;
	}

	public void setHsen(double hsen) {
		Hsen = hsen;
	}

	public double getHsup() {
		return Hsup;
	}

	public void setHsup(double hsup) {
		Hsup = hsup;
	}

	public double getT1s() {
		return T1s;
	}

	public void setT1s(double t1s) {
		T1s = t1s;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}