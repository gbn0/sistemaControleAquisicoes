import java.io.BufferedReader;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
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
            u = gerenciaUsuarios.pesquisaUsuarioNome(nome);
            if(u == null) {
                System.out.println("Usuário não encontrado digite novamente");
            }
        }
        this.usuario = u;
        loop();
    }

    private void loop() {
        int opcao;
        do {
            menuPrincipal();
            System.out.print("Digite a opção desejada: ");
            opcao = in.nextInt();
            switch(opcao) {
                case 1:
                    menuUsuário();
                    System.out.print("Digite a opção desejada: ");
                    int opcao2 = in.nextInt();
                    switch(opcao2) {
                        case 1:
                            listaUsuarios();
                            break;
                        case 2:
                            System.out.println("Digite o id do usuário");
                            int id = in.nextInt();
                            if(trocaUsuario(id)) {
                                System.out.println("Usuário trocado com sucesso");
                            }else {
                                System.out.println("Usuário não encontrado");
                            }
                            break;
                        case 0:
                            break;
                        default:
                            System.out.println("Opção inválida");
                            break;
                    }
                case 2:
                    menuPedidos();
                    System.out.print("Digite a opção desejada: ");
                    int opcao3 = in.nextInt();
                    switch(opcao3) {
                        case 1:
                            adicionaPedido();
                            break;
                        case 2:
                            listaPedidos();
                            break;
                        case 3:
                            aprovaPedido();
                            break;
                    }
            }
        }while(opcao !=0);
    }

    private void menuPrincipal() {
        System.out.println("[1] Usuários\n"
                            + "[2] Pedidos\n"
                            + "[3] Itens\n"
                            + "[4] Departamentos\n"
                            + "[5] Funcionários\n"
                            + "[6] Estatísticas gerais\n"
                            + "[0] Sair");
    }

    private void menuPedidos() {
        System.out.println("[1] Registrar pedido\n"
                            + "[2] Listar todos pedidos\n"
                            + "[3] Aprovar pedido\n"
                            + "[4] Procurar pedidos\n"
                            + "[0] Voltar");
    }

    private void menuProcuraPedidos() {

    }

    private void menuUsuário() {
        System.out.println("[1] Listar usuários\n"
                            + "[2] Trocar usuário\n"
                            + "[0] Voltar");
    }

    private void menuItens() {
        System.out.println("[1] Listar itens\n"
                            + "[0] Voltar");
    }

    private void carregaDados() {

    }

    private void restauraEntrada() {
        in = new Scanner(System.in);
    }

    public void adicionaPedido() {
        System.out.println("Digite o nome do departamento");
        Departamento d = gerenciaDepartamentos.pesquisaDepartamento(in.nextLine());
        if(d == null) {
            System.out.println("Departamento não encontrado");
            return;
        }
        Pedido p = new Pedido(gerenciaPedidos.getUltimoCodigo(),this.usuario,d,LocalDate.now());
        gerenciaPedidos.adicionaPedido(p);
    }


    private void aprovaPedido() {
        if(!(usuario instanceof Administrador)) {
            System.out.println("Você não tem permissão para acessar essa função");
            return;
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
    public boolean trocaUsuario(int id){
        Usuario u = gerenciaUsuarios.pesquisaUsuarioId(id);
        if(u != null){
            usuario = u;
            return true;
        }
        return false;
    }
        public void listaUsuarios(){
        ArrayList<Usuario> usuarios = gerenciaUsuarios.getUsuarios();
        for(Usuario u : usuarios){
            System.out.println(u);
            
        }
    }

    public void listaPedidos() {
        ArrayList<Pedido> pedidos = gerenciaPedidos.getPedidos();
        for(Pedido p : pedidos) {
            System.out.println("==================================\n"
             + p + "\n"
             + "==================================");
        }
    }
}
