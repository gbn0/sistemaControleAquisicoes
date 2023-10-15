import java.util.ArrayList;

public class GerenciaDepartamentos { //athos
    private ArrayList<Departamento> departamentos;
    public GerenciaDepartamentos(){
        departamentos = new ArrayList<Departamento>();
    }

    public boolean cadastraDepartamento(Departamento novoDepartamento){
        return departamentos.add(novoDepartamento);
    }

    public Departamento pesquisaDepartamento(String nome){
        for ( Departamento d : departamentos){
            if(d.getNome().equals(nome)){
                return d;
            }
        }
        return null;
    }
    
    public void listaDepartamentos() {
        for(Departamento d : departamentos) {
            System.out.println(d);
        }
    }
}
