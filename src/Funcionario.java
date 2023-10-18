public class Funcionario extends Usuario{
    
    public Funcionario(int id, String nome, String iniciais, Departamento departamento) {
        super(id, nome, iniciais, departamento);
        
    }

    @Override
    public String toString() {
        return ("==================================\n" + "Tipo: Funcionário" + "\nID: " + this.getId() + "\nNome: " + this.getNome() + "\nDepartamento: " + this.getDepartamento().getNome() + "\n==================================");
    }
    
}
