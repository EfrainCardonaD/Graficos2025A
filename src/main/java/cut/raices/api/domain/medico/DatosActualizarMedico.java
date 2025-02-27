package cut.raices.api.domain.medico;

import jakarta.validation.constraints.NotNull;
import cut.raices.api.domain.direccion.DatosDireccion;

public record DatosActualizarMedico(
        @NotNull
        Long id,
        String nombre,
        String documento,
        DatosDireccion direccion,
        Boolean activo

) {



}
