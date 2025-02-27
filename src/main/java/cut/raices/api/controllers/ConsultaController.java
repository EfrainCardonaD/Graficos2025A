package cut.raices.api.controllers;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import cut.raices.api.domain.consulta.AgendaDeConsultaService;
import cut.raices.api.domain.consulta.DatosAgendarConsulta;
import cut.raices.api.domain.desafio.DatosCacelarConsultar;
import cut.raices.api.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequestMapping("/consultas")
@SecurityRequirement(name = "bearer-key")
@SuppressWarnings("all")
public class ConsultaController {

    @Autowired
    private AgendaDeConsultaService service;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DatosAgendarConsulta datos) throws ValidacionDeIntegridad{
        var response = service.agendar(datos);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{idConsulta}")
    public ResponseEntity retornoDatosMedico(@RequestBody @PathVariable  DatosCacelarConsultar datosCacelarConsultar) {
        service.calcelarConsulta(datosCacelarConsultar);
        return ResponseEntity.ok(datosCacelarConsultar);
    }


}