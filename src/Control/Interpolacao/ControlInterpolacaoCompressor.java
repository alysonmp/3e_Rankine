/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control.Interpolacao;

import Control.Ciclo2.ControlConstantes;
import Control.Ciclo2.ControlZeta;
import Control.TabelaFluidos.ControlCompressor;
import Control.TabelaFluidos.ControlCompressor5;
import Control.TabelaFluidos.ControlWaterLiquido;
import Model.ModelCriticasKCSMat_PM;
import Model.TabelasFluidos.ModelCompressor;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author alysonmp
 */
public class ControlInterpolacaoCompressor {
    
    Session session;
    private double Cpv_g, Prv_g, Vcv_g, Muv_g, kv_g, Df_g;
    private Object gas;
    
    public ControlInterpolacaoCompressor(int FON, double pressao, double temp, Session session){
        
        switch(FON){
        	    case 1:
        	    		gas = new ControlWaterLiquido(session);
        	    		((ControlWaterLiquido)gas).interpolacao(pressao, temp);
        	    		Cpv_g = ((ControlWaterLiquido)gas).getCpl();
                Prv_g = ((ControlWaterLiquido)gas).getPrl();
                kv_g = ((ControlWaterLiquido)gas).getKl();
                Muv_g = ((ControlWaterLiquido)gas).getMul();
                Vcv_g = ((ControlWaterLiquido)gas).getVcl();
                
                ControlConstantes constantes = new ControlConstantes(temp, pressao, 1, session);
                ControlZeta zeta = new ControlZeta(constantes.getBeta(), constantes.getEps(), constantes.getDelta());
                double Zl1 = zeta.getZl();
                
                Criteria cr = session.createCriteria(ModelCriticasKCSMat_PM.class);
                cr.add(Restrictions.eq("cod", 1));
                List results = cr.list();
                ModelCriticasKCSMat_PM pm = (ModelCriticasKCSMat_PM)results.get(0);
                
                Df_g = 1/(((Zl1*constantes.getR()*temp)/pressao)/pm.getValor()); //%%kg/m3 
        	    case 2:
	    	    		gas = new ControlAr(session);
	    	    		((ControlAr)gas).interpolacao(pressao, temp);
	    	    		Cpv_g = ((ControlAr)gas).getCpv();
	            Prv_g = ((ControlAr)gas).getPrv();
	            kv_g = ((ControlAr)gas).getKv();
	            Muv_g = ((ControlAr)gas).getMuv();
	            Vcv_g = ((ControlAr)gas).getVcv();
	            Df_g = ((ControlAr)gas).getDf();
            case 3:
            case 4:
            case 5:
            case 6:
                gas = new ControlCompressor(session);
                ((ControlCompressor)gas).interpolacao(pressao, temp);
                Cpv_g = ((ControlCompressor)gas).getCpv();
                Prv_g = ((ControlCompressor)gas).getPrv();
                kv_g = ((ControlCompressor)gas).getKv();
                Muv_g = ((ControlCompressor)gas).getMuv();
                Vcv_g = ((ControlCompressor)gas).getVcv();
                Df_g = ((ControlCompressor)gas).getDf();
                break;
                
            case 7:
                gas = new ControlCompressor5(session);
                ((ControlCompressor5)gas).interpolacao(pressao, temp);
                Cpv_g = ((ControlCompressor5)gas).getCpv();
                Prv_g = ((ControlCompressor5)gas).getPrv();
                kv_g = ((ControlCompressor5)gas).getKv();
                Muv_g = ((ControlCompressor5)gas).getMuv();
                Vcv_g = ((ControlCompressor5)gas).getVcv();
                Df_g = ((ControlCompressor5)gas).getDf();
                break;
        }
    }

    public double getCpv_g() {
        return Cpv_g;
    }

    public void setCpv_g(double Cpv_g) {
        this.Cpv_g = Cpv_g;
    }

    public double getPrv_g() {
        return Prv_g;
    }

    public void setPrv_g(double Prv_g) {
        this.Prv_g = Prv_g;
    }

    public double getVcv_g() {
        return Vcv_g;
    }

    public void setVcv_g(double Vcv_g) {
        this.Vcv_g = Vcv_g;
    }

    public double getMuv_g() {
        return Muv_g;
    }

    public void setMuv_g(double Muv_g) {
        this.Muv_g = Muv_g;
    }

    public double getKv_g() {
        return kv_g;
    }

    public void setKv_g(double kv_g) {
        this.kv_g = kv_g;
    }

    public double getDf_g() {
        return Df_g;
    }

    public void setDf_g(double Df_g) {
        this.Df_g = Df_g;
    }
    
}
