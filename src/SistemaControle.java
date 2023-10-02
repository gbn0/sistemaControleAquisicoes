import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.Scanner;
import java.io.FileReader;

public class SistemaControle {
    
    private Scanner in = null;
    private GerenciaPedidos gerenciaPedidos;
    private GerenciaDepartamentos gerenciaDepartamentos;
    private GerenciaUsuarios gerenciaUsuarios;
    private Usuario usuario = null;

    public SistemaControle() {
        try {
            BufferedReader streamEntrada = new BufferedReader(new FileReader("dadosentrada.txt"));
            in = new Scanner(streamEntrada);
        }catch(Exception e) {
            System.out.println(e);
        }

        this.gerenciaDepartamentos = new GerenciaDepartamentos();
        this.gerenciaPedidos = new GerenciaPedidos();
        this.gerenciaUsuarios = new GerenciaUsuarios();
    }

    public void executa() {
        carregaDados();
        restauraEntrada();
        Usuario u = null;
        while(u == null) {
            System.out.println("Digite o nome do usuário que está utilizando o sistema");
            String nome = in.nextLine();
            u = gerenciaUsuarios.pesquisaUsuario(nome);
            if(u == null) {
                System.out.println("Usuário não encontrado digite novamente");
            }
        }
        this.usuario = u;
        loop();
    }

    private void loop() {
        
    }

    private void menu() {
        
    }

    private void carregaDados() {

    }

    private void restauraEntrada() {
        in = new Scanner(System.in);
    }


    private void AprovaPedido() {
        if(!(usuario instanceof Administrador)) {
            System.out.println("Você não tem permissão para acessar essa função");
            loop();
        }
        System.out.println("Digite o codigo do pedido a ser buscado");
        int c = in.nextInt();
        in.nextLine();
        Pedido p = gerenciaPedidos.pesquisaPedido(c);

        if(p == null) {
            System.out.println("Pedido não encontrado");
        }else {
            switch(p.getStatus()) {
                case "Aberto":
                    System.out.println("O pedido está aberto, detalhes do pedido:\n" + p);
                    System.out.println("--------------------------------------------------");
                    System.out.println("1- Aprovar\n2-Reprovar\n0-Sair");
                    int op = in.nextInt();
                    if(op == 1) {
                        p.setStatus("Aprovado");
                        System.out.println("Pedido aprovado");
                    }else if(op == 2) {
                        p.setStatus("Reprovado");
                        System.out.println("Pedido reprovado");
                    }else if(op == 0) {
                        loop();
                    }else {
                        System.out.println("Opção invalida, voltando");
                        loop();
                    }
                    break;
                case "Aprovado":
                    System.out.println("O pedido já foi aprovado");
                    break;
                case "Reprovado":
                    System.out.println("O pedido já foi reprovado");
                    break;
                case "Concluido":
                    System.out.println("O pedido já está concluído");
                    break;
            }
        }
    }
}
