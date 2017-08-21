/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control.Ciclo2;

import Control.Ciclo2.ControlCubica;
import Model.ModelComplexo;

/**
 *
 * @author leonardo
 */
public class ControlZeta {
    
    private ControlCubica cubica;
    private double Zl, Zv, Z;
    private double ter1, ter2, ter3, ter4, Z1m, Z2m;

    public ControlZeta(double A, double B, double C) {

        ter1 = 1;
        ter2 = A;
        ter3 = B;
        ter4 = C;

        cubica = new ControlCubica();
        cubica.solve(ter1, ter2, ter3, ter4);

        ModelComplexo complexo1 = cubica.getComplexo1();
        ModelComplexo complexo2 = cubica.getComplexo2();
        ModelComplexo complexo3 = cubica.getComplexo3();
            
        double x1 = complexo1.getReal();
        double x2 = complexo2.getReal();
        double x3 = complexo3.getReal();
        
        if (complexo1.getImaginario() == 0 && complexo2.getImaginario() == 0 && complexo3.getImaginario() == 0) {
            if(x1 > x2 && x1 > x3){
                Z1m = x1;
            }else if(x2 > x3){
                Z1m = x2;
            }else{
                Z1m = x3;            
            }
            
            if (x1 < x2 && x1 < x3) {
                Z2m = x1;
            }else if (x2 < x3) {
                Z2m = x2;
            }else {
                Z2m = x3;
            }
        }else{
            if(x1 > x2 && x1 > x3 && complexo1.getImaginario() == 0){
                Z1m = x1;
            }else if(x2 > x3 && complexo2.getImaginario() == 0){
                Z1m = x2;
            }else if(complexo3.getImaginario() == 0){
                Z1m = x3;
            }else{
                if(x1 > x2 && x1 > x3){
                    Z1m = complexo1.abs();
                }else if(x2 > x3){
                    Z1m = complexo2.abs();
                }else{
                    Z1m = complexo3.abs();
                }
                
            }
            
            if(x1 < x2 && x1 < x3 && complexo1.getImaginario() == 0){
                Z2m = x1;
            }else if(x2 < x3 && complexo2.getImaginario() == 0){
                Z2m = x2;
            }else if(complexo3.getImaginario() == 0){
                Z2m = x3;
            }else{
                if(x1 < x2 && x1 < x3){
                    Z2m = complexo1.abs();
                }else if(x2 < x3){
                    Z2m = complexo2.abs();
                }else{
                    Z2m = complexo3.abs();
                }
            }
            
        }
        Zl = Z2m;
        Zv = Z1m;
    }

    public double getZl() {
        return Zl;
    }

    public void setZl(double Zl) {
        this.Zl = Zl;
    }

    public double getZv() {
        return Zv;
    }

    public void setZv(double Zv) {
        this.Zv = Zv;
    }

    public double getZ() {
        return Z;
    }

    public void setZ(double Z) {
        this.Z = Z;
    }
    
    
    
}
