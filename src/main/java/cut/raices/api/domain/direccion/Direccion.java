package cut.raices.api.domain.direccion;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Direccion {

    private String calle;
    private String numero;
    private String cruces;
    private String ciudad;
    private String colonia;


    public Direccion(DatosDireccion direccion) {
        this.calle = direccion.calle();
        this.numero = direccion.numero();
        this.cruces = direccion.cruces();
        this.ciudad = direccion.ciudad();
        this.colonia = direccion.colonia();
    }

    public Direccion actualizarDatos(DatosDireccion datosActualizarMedico) {
        this.calle = datosActualizarMedico.calle();
        this.numero = datosActualizarMedico.numero();
        this.cruces = datosActualizarMedico.cruces();
        this.ciudad = datosActualizarMedico.ciudad();
        this.colonia = datosActualizarMedico.colonia();
        return this;
    }
}
