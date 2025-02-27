package cut.raices.api.domain.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import cut.raices.api.domain.direccion.DatosDireccion;


public record DatosRegistroPaciente(
        @NotBlank(message = "{nombre.obligatorio}")
        String nombre,

        @NotBlank(message = "{email.obligatorio}")
        @Email(message = "{email.invalido}")
        String email,

        @NotBlank(message = "{telefono.obligatorio}")
        String telefono,

        @NotBlank(message = "{documento.obligatorio}")
        @Pattern(regexp = "\\d{4,10}", message = "{documento.invalido}")
        String documento,

        @NotNull(message = "{direccion.obligatorio}")
        @Valid
        DatosDireccion direccion) {

}
