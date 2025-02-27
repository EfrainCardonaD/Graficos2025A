package med.voll.api.infra.security;

import med.voll.api.domain.usuarios.Usuario;
import med.voll.api.domain.usuarios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;


    public boolean comprobarContrasenia(String login, String rawPassword) {
        UserDetails usuario = usuarioRepositorio.findByLogin(login);

        if (usuario != null) {
            return passwordEncoder.matches(rawPassword, usuario.getPassword());
        }
        return false;
    }
}
