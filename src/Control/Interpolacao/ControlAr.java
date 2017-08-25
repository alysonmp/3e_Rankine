/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control.Interpolacao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import Model.TabelasFluidos.ModelAir;

/**
 *
 * @author alysonmp
 */
public class ControlAr {
    
    Session session;
    private double Cpv, Prv, kv, Muv, Vcv, Df;
    private double cpv1, cpv2, Prv1, Prv2, kv1, kv2, Muv1, Muv2, Vcv1, Vcv2, Df1, Df2;
    
    private ModelAir air1;
    private ModelAir air2;
    private ModelAir air3;
    private ModelAir air4;
    
    public ControlAr(Session session){
        this.session = session;
    }
    
    public void criaTabelaAr(){
        String csvFile = "/Ciclo3/Csv/AIR.csv";
        InputStream is = getClass().getResourceAsStream(csvFile);
        
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ";";
        this.session = session;
        
        try {

            Criteria cr = this.session.createCriteria(ModelAir.class);
            List results = cr.list();
            
            if(results.isEmpty()){
                br = new BufferedReader(new InputStreamReader(is));
                line = br.readLine();
                while ((line = br.readLine()) != null) {

                    // use comma as separator
                    String[] air_g = line.split(cvsSplitBy);
                    
                    session.save(new ModelAir(Double.parseDouble(air_g[0]), Double.parseDouble(air_g[1]), Double.parseDouble(air_g[2]), Double.parseDouble(air_g[3]), Double.parseDouble(air_g[4]), Double.parseDouble(air_g[5]), Double.parseDouble(air_g[6]), Double.parseDouble(air_g[7])));
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public void interpolacao(double pressao, double temperatura){
        Criteria cr = this.session.createCriteria(ModelAir.class);
        //cr = this.session.createCriteria(ModelwaterGas.class);
        temperatura -=1;
        do{
            temperatura += 1;
            SQLQuery consulta = this.session.createSQLQuery("select * from AIR where pressao <= " +pressao+ "and temperatura <= " +temperatura+ "ORDER BY ID DESC FETCH FIRST 1 ROWS ONLY");

            consulta.setResultTransformer(Transformers.aliasToBean(ModelAir.class));//Sem isso aqui impossível de retornar
            List<ModelAir> airess = consulta.list(); 
            if(!airess.isEmpty())
                air1 = airess.get(0);

            consulta = this.session.createSQLQuery("select * from AIR where pressao <= "+pressao+" and temperatura >= "+temperatura+" ORDER BY PRESSAO DESC, TEMPERATURA ASC FETCH FIRST 1 ROWS ONLY");

            consulta.setResultTransformer(Transformers.aliasToBean(ModelAir.class));//Sem isso aqui impossível de retornar
            airess = consulta.list(); 
            if(!airess.isEmpty())
                air2 = airess.get(0);

            consulta = this.session.createSQLQuery("select * from AIR where pressao >= "+pressao+" and temperatura <= "+temperatura+" ORDER BY PRESSAO ASC, TEMPERATURA DESC");

            consulta.setResultTransformer(Transformers.aliasToBean(ModelAir.class));//Sem isso aqui impossível de retornar
            airess = consulta.list(); 
            if(!airess.isEmpty())
                air3 = airess.get(0);

            consulta = this.session.createSQLQuery("select * from AIR where pressao >= " +pressao+ "and temperatura >= " +temperatura+ " FETCH FIRST 1 ROWS ONLY");

            consulta.setResultTransformer(Transformers.aliasToBean(ModelAir.class));//Sem isso aqui impossível de retornar
            airess = consulta.list(); 
            if(!airess.isEmpty())
                air4 = airess.get(0);

            
        }while(air1 == null || air2 == null || air3 == null || air4 == null);
        
        cpv1 = air1.getCPV() + (air2.getCPV() - air1.getCPV()) * ((temperatura-air1.getTEMPERATURA())/(air2.getTEMPERATURA()-air1.getTEMPERATURA()));
        cpv2 = air3.getCPV() + (air4.getCPV() - air3.getCPV()) * ((temperatura-air3.getTEMPERATURA())/(air4.getTEMPERATURA()-air3.getTEMPERATURA()));
        Cpv = cpv1 + (cpv2 - cpv1) * ((pressao-air1.getPRESSAO())/(air3.getPRESSAO()-air1.getPRESSAO()));
        
        Prv1 = air1.getPRV() + (air2.getPRV() - air1.getPRV()) * ((temperatura-air1.getTEMPERATURA())/(air2.getTEMPERATURA()-air1.getTEMPERATURA()));
        Prv2 = air3.getPRV() + (air4.getPRV() - air3.getPRV()) * ((temperatura-air3.getTEMPERATURA())/(air4.getTEMPERATURA()-air3.getTEMPERATURA()));
        Prv = Prv1 + (Prv2 - Prv1) * ((pressao-air1.getPRESSAO())/(air3.getPRESSAO()-air1.getPRESSAO()));
        
        kv1 = air1.getKV() + (air2.getKV() - air1.getKV()) * ((temperatura-air1.getTEMPERATURA())/(air2.getTEMPERATURA()-air1.getTEMPERATURA()));
        kv2 = air3.getKV() + (air4.getKV() - air3.getKV()) * ((temperatura-air3.getTEMPERATURA())/(air4.getTEMPERATURA()-air3.getTEMPERATURA()));
        kv = kv1 + (kv2 - kv1) * ((pressao-air1.getPRESSAO())/(air3.getPRESSAO()-air1.getPRESSAO()));
        
        Muv1 = air1.getMUV() + (air2.getMUV() - air1.getMUV()) * ((temperatura-air1.getTEMPERATURA())/(air2.getTEMPERATURA()-air1.getTEMPERATURA()));
        Muv2 = air3.getMUV() + (air4.getMUV() - air3.getMUV()) * ((temperatura-air3.getTEMPERATURA())/(air4.getTEMPERATURA()-air3.getTEMPERATURA()));
        Muv = Muv1 + (Muv2 - Muv1) * ((pressao-air1.getPRESSAO())/(air3.getPRESSAO()-air1.getPRESSAO()));
        
        Vcv1 = air1.getVCV() + (air2.getVCV() - air1.getVCV()) * ((temperatura-air1.getTEMPERATURA())/(air2.getTEMPERATURA()-air1.getTEMPERATURA()));
        Vcv2 = air3.getVCV() + (air4.getVCV() - air3.getVCV()) * ((temperatura-air3.getTEMPERATURA())/(air4.getTEMPERATURA()-air3.getTEMPERATURA()));
        Vcv = Vcv1 + (Vcv2 - Vcv1) * ((pressao-air1.getPRESSAO())/(air3.getPRESSAO()-air1.getPRESSAO()));
        
        Df1 = air1.getDFV() + (air2.getDFV() - air1.getDFV()) * ((temperatura-air1.getTEMPERATURA())/(air2.getTEMPERATURA()-air1.getTEMPERATURA()));
        Df2 = air3.getDFV() + (air4.getDFV() - air3.getDFV()) * ((temperatura-air3.getTEMPERATURA())/(air4.getTEMPERATURA()-air3.getTEMPERATURA()));
        Df = Df1 + (Df2 - Df1) * ((pressao-air1.getPRESSAO())/(air3.getPRESSAO()-air1.getPRESSAO()));
    }

    public double getCpv() {
        return Cpv;
    }

    public void setCpv(double Cpv) {
        this.Cpv = Cpv;
    }

    public double getPrv() {
        return Prv;
    }

    public void setPrv(double Prv) {
        this.Prv = Prv;
    }

    public double getKv() {
        return kv;
    }

    public void setKv(double kv) {
        this.kv = kv;
    }

    public double getMuv() {
        return Muv;
    }

    public void setMuv(double Muv) {
        this.Muv = Muv;
    }

    public double getVcv() {
        return Vcv;
    }

    public void setVcv(double Vcv) {
        this.Vcv = Vcv;
    }

    public double getDf() {
        return Df;
    }

    public void setDf(double Df) {
        this.Df = Df;
    }

}
