package Classes_BO;

import java.util.List;
import java.util.Scanner;

import Classes_DAO.Usuario_DAO;
import Classes_DTO.Usuario_DTO;

public class Usuario_BO {
    private Usuario_DAO usuarioDAO;

    public Usuario_BO() {
        this.usuarioDAO = new Usuario_DAO();
    }

    public void cadastrarUsuario( List<Usuario_DTO> usuarios, Scanner scanner) {
        System.out.println("Digite o email do usuário:");
        String email = scanner.nextLine();

        if (usuarioDAO.buscarPorEmail(email) != null) {
            System.out.println("Email já cadastrado! Tente outro.");
            return;
        }

        System.out.println("Digite o nome do usuário:");
        String nome = scanner.nextLine();

        System.out.println("Digite a senha do usuário:");
        String senha = scanner.nextLine();

        Usuario_DTO novoUsuario = new Usuario_DTO(nome, email, senha);
        usuarioDAO.salvar(novoUsuario);
        System.out.println("Usuário cadastrado com sucesso!");
    }

    public boolean autenticarUsuario( List<Usuario_DTO> usuarios, Scanner scanner) {
        System.out.println("Digite o email:");
        String email = scanner.nextLine();

        System.out.println("Digite a senha:");
        String senha = scanner.nextLine();

        Usuario_DTO usuario = usuarioDAO.buscarPorEmail(email);
        if (usuario != null && usuario.getSenha().equals(senha)) {
            System.out.println("Autenticação bem-sucedida! Bem-vindo, " + usuario.getNome() + "!");
            return true;
        }

        System.out.println("Email ou senha incorretos. Tente novamente.");
        return false;
    }

    public boolean removerUsuario( List<Usuario_DTO> usuarios,Scanner scanner) {
        System.out.println("Digite o email do usuário a ser removido:");
        String email = scanner.nextLine();

        Usuario_DTO usuario = usuarioDAO.buscarPorEmail(email);
        if (usuario != null) {
            usuarioDAO.excluir(usuario);
            System.out.println("Usuário " + usuario.getNome() + " removido com sucesso.");
            return true;
        }

        System.out.println("Usuário com o email " + email + " não encontrado.");
        return false;
    }

    public boolean atualizarUsuario( List<Usuario_DTO> usuarios, Scanner scanner) {
        System.out.println("Digite o email do usuário a ser atualizado:");
        String email = scanner.nextLine();

        Usuario_DTO usuarioAntigo = usuarioDAO.buscarPorEmail(email);
        if (usuarioAntigo != null) {
            System.out.println("Usuário encontrado: " + usuarioAntigo);

            System.out.println("Digite o novo nome (ou pressione Enter para manter):");
            String novoNome = scanner.nextLine();
            if (novoNome.isEmpty()) {
                novoNome = usuarioAntigo.getNome();
            }

            System.out.println("Digite a nova senha (ou pressione Enter para manter):");
            String novaSenha = scanner.nextLine();
            if (novaSenha.isEmpty()) {
                novaSenha = usuarioAntigo.getSenha();
            }

            Usuario_DTO usuarioAtualizado = new Usuario_DTO(novoNome, email, novaSenha);
            usuarioDAO.atualizar(usuarioAntigo, usuarioAtualizado);
            System.out.println("Usuário atualizado com sucesso: " + usuarioAtualizado);
            return true;
        }

        System.out.println("Usuário com o email " + email + " não encontrado.");
        return false;
    }

    public void listarUsuarios( List<Usuario_DTO> usuarios) {
        usuarios = usuarioDAO.listarTodos();

        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
            return;
        }

        for (Usuario_DTO usuario : usuarios) {
            System.out.println("-------------------------");
            System.out.println("Nome: " + usuario.getNome());
            System.out.println("Email: " + usuario.getEmail());
            System.out.println("-------------------------");
        }
    }
}
