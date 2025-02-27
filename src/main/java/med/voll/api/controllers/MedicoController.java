package med.voll.api.controllers;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("medicos")
@CrossOrigin
public class MedicoController {

    @Autowired
    private MedicoRepositorio medicoRepositorio;

    @PostMapping
    public ResponseEntity<DatosRespuestaMedico> registrarMedico(@RequestBody @Valid DatosRegistroMedico datosRegistroMedico,
                                                                UriComponentsBuilder uriComponentsBuilder) {
            Medico medico = medicoRepositorio.save(new Medico(datosRegistroMedico));
            URI uri = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
            return ResponseEntity.created(uri).body(new DatosRespuestaMedico(medico));
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoMedico>> listadoMedicos(@PageableDefault(size = 2, sort = "nombre") Pageable paginacion) {
        //return medicoRepositorio.findAll(paginacion).map(DatosListadoMedico::new);
        return ResponseEntity.ok(medicoRepositorio.findByActivoTrue(paginacion).map(DatosListadoMedico::new));

    }

    @PutMapping
    @Transactional
    public ResponseEntity<DatosRespuestaMedico> actualizarMedico(@RequestBody @Valid DatosActualizarMedico datosActualizarMedico) {
        Medico medico = medicoRepositorio.getReferenceById(datosActualizarMedico.id());
        medico.actualizarDatos(datosActualizarMedico);
        return ResponseEntity.ok(getDatosRespuestaMedico(medico));
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
        Medico medico = medicoRepositorio.getReferenceById(id);
        medico.desactivarMedico();
        return ResponseEntity.ok(getDatosRespuestaMedico(medico));
    }

    private static DatosRespuestaMedico getDatosRespuestaMedico(Medico medico) {
        return new DatosRespuestaMedico(medico.getId(), medico.getNombre(), medico.getEspecialidad().toString(), medico.getDocumento(), medico.getEmail());
    }





}
