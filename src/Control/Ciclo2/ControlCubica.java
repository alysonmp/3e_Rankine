package Control.Ciclo2;

import Model.ModelComplexo;
import javax.swing.JOptionPane;

public class ControlCubica {

    private double x1;
    private double x2;
    private double x3;
    private double x2i;
    private double x3i;
    private int nRoots;
    private double D;
    private static final double TWO_PI = 2.0 * Math.PI;
    private static final double FOUR_PI = 4.0 * Math.PI;

    private ModelComplexo complexo1, complexo2, complexo3;
    
    public ModelComplexo getComplexo1() {
        return complexo1;
    }

    public void setComplexo1(ModelComplexo complexo1) {
        this.complexo1 = complexo1;
    }

    public ModelComplexo getComplexo2() {
        return complexo2;
    }

    public void setComplexo2(ModelComplexo complexo2) {
        this.complexo2 = complexo2;
    }

    public ModelComplexo getComplexo3() {
        return complexo3;
    }

    public void setComplexo3(ModelComplexo complexo3) {
        this.complexo3 = complexo3;
    }
    
    public double getD(){
        return D;
    }
    
    public void setD(double D){
        this.D = D;
    }
    
    public double getX1() {
        return x1;
    }

    public void setX1(double x1) {
        this.x1 = x1;
    }

    public double getX2() {
        return x2;
    }

    public void setX2(double x2) {
        this.x2 = x2;
    }

    public double getX3() {
        return x3;
    }

    public void setX3(double x3) {
        this.x3 = x3;
    }

    public ControlCubica() {
    }
    
    public void solve(double a,
            double b,
            double c,
            double d) {
        
        double f, g, h, R, k, m2, n, n2, r, rc, theta, x2a, x2b, x2c, x2d, sign, dans;
        
        f = (((3*c)/a) - (((b*b)/(a*a))))/3;

        g = ((2*((b*b*b)/(a*a*a))-(9*b*c/(a*a)) + ((27*(d/a)))))/27;

        h = (((g*g)/4) + ((f*f*f)/27));

        if (h > 0){
            R = (-(g/2)+ (Math.sqrt(h)));

            k=1;
            if (R < 0) 
                    k=-1; 
            else 
                    k=1;

            m2 = (Math.pow((R*k),(0.33333333333333333333333333333333333333333333333333333333)));
            m2 = m2*k;
            k=1;
            n = (-(g/2)- (Math.sqrt(h)));
            if (n < 0) 
                    k=-1; 
            else 
                    k=1;

            n2 = (Math.pow((n*k),(0.33333333333333333333333333333333333333333333333333333333)));
            n2 = n2*k;
            k=1;
            x1= ((m2 + n2) - (b/(3*a)));

            x2=(-1*(m2 + n2)/2 - (b/(3*a)));// + " + i* " + 
            x3=(-1*(m2 + n2)/2 - (b/(3*a)));// + " - i* " + 
            
            x2i = ((m2 - n2)/2)*Math.pow(3,.5);
            x3i = ((m2 - n2)/2)*Math.pow(3,.5);
            
            complexo1 = new ModelComplexo(x1, 0);
            complexo2 = new ModelComplexo(x2, x2i);
            complexo3 = new ModelComplexo(x3, x3i);
        }

        if (h<=0){
            r = ((Math.sqrt((g*g/4)-h)));
            k=1;
            if (r<0) 
                k=-1;

            rc = Math.pow((r*k),(0.33333333333333333333333333333333333333333333333333333333))*k;
            k=1;
            theta =Math.acos((-g/(2*r)));
            x1= (2*(rc*Math.cos(theta/3))-(b/(3*a)));
            x2a=rc*-1;
            x2b= Math.cos(theta/3);
            x2c= Math.sqrt(3)*(Math.sin(theta/3));
            x2d= (b/3*a)*-1;
            x2=(x2a*(x2b + x2c))-(b/(3*a));
            x3=(x2a*(x2b - x2c))-(b/(3*a));

            x1=x1*1E+14;
            //x1=Math.round(x1);
            x1=(x1/1E+14);
            
            x2=x2*1E+14;
            //x2=Math.round(x2);
            x2=(x2/1E+14);
            
            x3=x3*1E+14;
            //x3=Math.round(x3);
            x3=(x3/1E+14);
            
            complexo1 = new ModelComplexo(x1, 0);
            complexo2 = new ModelComplexo(x2, 0);
            complexo3 = new ModelComplexo(x3, 0);
        }

        if ((f+g+h)==0){
            if (d<0){
                sign=-1;
            }else{
                sign=1;
            }

            if (sign>0){
                dans=Math.pow((d/a),(0.33333333333333333333333333333333333333333333333333333333));
                dans=dans*-1;
            }else{
                d=d*-1;
                dans=Math.pow((d/a),(0.33333333333333333333333333333333333333333333333333333333));
            }
            x1=dans; 
            x2=dans;
            x3=dans;
            
            complexo1 = new ModelComplexo(x1, 0);
            complexo2 = new ModelComplexo(x2, 0);
            complexo3 = new ModelComplexo(x3, 0);
        }
    }
    
    public void solve2(double a,
            double b,
            double c,
            double d) {

        
        
        /*PolynomialFunction p = new PolynomialFunction(new double[]{a, b, c, d});
        LaguerreSolver solver = new LaguerreSolver();
        
        try {
            Complex[] com = solver.solveAll(new double[]{a, b, c, d}, 0);
            for(int i = 0; i < com.length; i++){
                System.out.println(com[i].getReal());
                System.out.println(com[i].getImaginary());
            }
        } catch (ConvergenceException ex) {
            Logger.getLogger(ControlCubica.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FunctionEvaluationException ex) {
            Logger.getLogger(ControlCubica.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        
        // Verify preconditions.
        if (a == 0.0) {
            JOptionPane.showMessageDialog(null, "nao e cubica");
        }

        // Normalize coefficients.
        double denom = a;
        a = b / denom;
        b = c / denom;
        c = d / denom;

        // Commence solution.
        double a_over_3 = a / 3.0;
        double Q = (3 * b - a * a) / 9.0;
        double Q_CUBE = Q * Q * Q;
        double R = (9 * a * b - 27 * c - 2 * a * a * a) / 54.0;
        double R_SQR = R * R;
        D = Q_CUBE + R_SQR;
        //System.out.println(D);
        if (D < 0.0) {
            // Three unequal real roots.
            nRoots = 3;
            double theta = Math.acos(R / Math.sqrt(-Q_CUBE));
            double SQRT_Q = Math.sqrt(-Q);
            x1 = 2.0 * SQRT_Q * Math.cos(theta / 3.0) - a_over_3;
            x2 = 2.0 * SQRT_Q * Math.cos((theta + TWO_PI) / 3.0) - a_over_3;
            x3 = 2.0 * SQRT_Q * Math.cos((theta + FOUR_PI) / 3.0) - a_over_3;
        } else if (D > 0.0) {
            // One real root.
            nRoots = 1;
            double SQRT_D = Math.sqrt(D);
            double S = Math.cbrt(R + SQRT_D);
            double T = Math.cbrt(R - SQRT_D);
            x1 = (S + T) - a_over_3;
            x2 = Double.NaN;
            x3 = Double.NaN;
        } else {
            // Three real roots, at least two equal.
            nRoots = 3;
            double CBRT_R = Math.cbrt(R);
            x1 = 2 * CBRT_R - a_over_3;
            x2 = x3 = CBRT_R - a_over_3;
        }
    }
}
