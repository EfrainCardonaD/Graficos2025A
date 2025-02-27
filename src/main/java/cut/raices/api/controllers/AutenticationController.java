package cut.raices.api.controllers;


import cut.raices.api.domain.usuarios.DatosAutenticacionUsuario;
import cut.raices.api.domain.usuarios.Usuario;
import cut.raices.api.infra.security.DatosJWTToken;
import cut.raices.api.infra.security.PasswordService;
import cut.raices.api.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("login")
public class AutenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    //test
    @Autowired
    PasswordService passwordService ;

    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody DatosAutenticacionUsuario datosAutenticacionUsuario) {
        Authentication authenticationToken  = new UsernamePasswordAuthenticationToken(datosAutenticacionUsuario.login(), datosAutenticacionUsuario.clave());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        var JWTtoken = tokenService.generarToken((Usuario) authenticate.getPrincipal());
        return ResponseEntity.ok(new DatosJWTToken(JWTtoken));
    }

    @GetMapping
    public void test() {

        System.out.println(passwordService.comprobarContrasenia("efra105", "123456"));

    }

}
