package Classes_DAO;

import java.util.ArrayList;
import java.util.List;

import Classes_DTO.Usuario_DTO;

public class Usuario_DAO {
    private List<Usuario_DTO> usuarios;

    public Usuario_DAO() {
        this.usuarios = new ArrayList<>();
    }

    public void salvar(Usuario_DTO usuario) {
        usuarios.add(usuario);
    }

    public Usuario_DTO buscarPorEmail(String email) {
        for (Usuario_DTO usuario : usuarios) {
            if (usuario.getEmail().equals(email)) {
                return usuario;
            }
        }
        return null;
    }

    public void atualizar(Usuario_DTO usuarioAntigo, Usuario_DTO usuarioNovo) {
        usuarios.remove(usuarioAntigo);
        usuarios.add(usuarioNovo);
    }

    public void excluir(Usuario_DTO usuario) {
        usuarios.remove(usuario);
    }

    public List<Usuario_DTO> listarTodos() {
        return usuarios;
    }
}