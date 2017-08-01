package Control;

import View.ViewInicio;

public class Main {
        
    public static void main(String[] args){    
        ViewInicio vi = new ViewInicio();
        ControlPrincipal principal = new ControlPrincipal();
        vi.getViewInicio().dispose();
    }
}
