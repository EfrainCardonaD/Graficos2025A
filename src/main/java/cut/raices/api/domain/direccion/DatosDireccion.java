package cut.raices.api.domain.direccion;

import jakarta.validation.constraints.NotBlank;

public record DatosDireccion(
        @NotBlank
        String calle,
        @NotBlank
        String colonia,
        @NotBlank(message = "Falta ciudad")
        String ciudad,
        @NotBlank
        String numero,
        @NotBlank
        String cruces ) {
}
