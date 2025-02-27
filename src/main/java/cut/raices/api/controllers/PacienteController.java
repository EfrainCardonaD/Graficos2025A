package cut.raices.api.controllers;

import cut.raices.api.domain.paciente.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @PostMapping
    public ResponseEntity<DatosDetallesPaciente> registrarMedico(@RequestBody @Valid DatosRegistroPaciente datosRegistroPaciente,
                                                                 UriComponentsBuilder uriComponentsBuilder) {
        Paciente paciente = pacienteRepository.save(new Paciente(datosRegistroPaciente));

        URI uri = uriComponentsBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(uri).body(new DatosDetallesPaciente(paciente));
    }

    @GetMapping
    public ResponseEntity<Page<DatosDetallesPaciente>> listadoMedicos(@PageableDefault(size = 2, sort = "nombre") Pageable paginacion) {
        //return medicoRepositorio.findAll(paginacion).map(DatosListadoMedico::new);
        return ResponseEntity.ok(pacienteRepository.findAllByActivoTrue(paginacion).map(DatosDetallesPaciente::new));

    }

    @PutMapping
    @Transactional
    public ResponseEntity<DatosDetallesPaciente> actualizarPaciente(@RequestBody @Valid DatosActualizacionPaciente datosActualizacionPaciente) {
        Paciente paciente = pacienteRepository.getReferenceById(datosActualizacionPaciente.id());
        paciente.actualizarDatosPaciente(datosActualizacionPaciente);
        return ResponseEntity.ok(new DatosDetallesPaciente(paciente));
    }


 /*
//Delete total
    //@DeleteMapping("/{id}")
    //@Transactional
   public void eliminarMedico(@RequestBody @PathVariable Long id) {

        Medico medico = medicoRepositorio.getReferenceById(id);
        medicoRepositorio.delete(medico);

    }*/

//Delete logico
   /* @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarMedico(@RequestBody @PathVariable Long id) {
        Medico medico = medicoRepositorio.getReferenceById(id);
        medico.desactivarMedico();
        return ResponseEntity.noContent().build();
    }*/


    @GetMapping("/{id}")
    public ResponseEntity retornoDatosMedico(@RequestBody @PathVariable Long id) {
        Paciente paciente = pacienteRepository.getReferenceById(id);
        paciente.eliminar();
        return ResponseEntity.ok(new DatosDetallesPaciente(paciente));
    }







}
