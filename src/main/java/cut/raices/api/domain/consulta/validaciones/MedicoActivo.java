package cut.raices.api.domain.consulta.validaciones;

import jakarta.validation.ValidationException;
import cut.raices.api.domain.consulta.DatosAgendarConsulta;
import cut.raices.api.domain.medico.MedicoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicoActivo implements ValidadorDeConsultas {
    @Autowired
    private MedicoRepositorio repositorio;

    public void validar(DatosAgendarConsulta datos) {
        if (datos.idMedico() == null) {
            return;
        }
        var medicoActivo = repositorio.findActivoById(datos.idMedico());
        if (!medicoActivo) {
            throw new ValidationException("No se puede permitir agendar citas con medicos inactivos en el sistema");
        }
    }
}
