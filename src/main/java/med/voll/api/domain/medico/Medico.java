package med.voll.api.domain.medico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.direccion.Direccion;

@Table(name = "medicos")
@Entity(name = "Medico")
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode(of = "id")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String documento;
    private String telefono;
    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;
    @Embedded
    private Direccion direccion;

    private boolean activo;



    public Medico(DatosRegistroMedico datosRegistroMedico) {
        this.nombre = datosRegistroMedico.nombre();
        this.email = datosRegistroMedico.email();
        this.direccion = new Direccion(datosRegistroMedico.direccion());
        this.documento = datosRegistroMedico.documento();
        this.especialidad= datosRegistroMedico.especialidad();
        this.telefono = datosRegistroMedico.telefono();
        this.activo = true;
    }


    public void actualizarDatos(DatosActualizarMedico datosActualizarMedico) {
        if (datosActualizarMedico.nombre() != null){
            this.nombre = datosActualizarMedico.nombre();
        }
        if (datosActualizarMedico.documento() != null){
            this.documento = datosActualizarMedico.documento();
        }
        if (datosActualizarMedico.direccion() != null){
            this.direccion = direccion.actualizarDatos(datosActualizarMedico.direccion());
        }
        if (datosActualizarMedico.activo() != null){
            this.activo = datosActualizarMedico.activo();
        }

    }

    public void desactivarMedico() {
        this.activo = false;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id + "," +
                "\"nombre\":\"" + nombre + "\"," +
                "\"email\":\"" + email + "\"," +
                "\"documento\":\"" + documento + "\"," +
                "\"telefono\":\"" + telefono + "\"," +
                "\"especialidad\":\"" + especialidad + "\"," +
                "\"direccion\":{" +
                "\"calle\":\"" + direccion.getCalle() + "\"," +
                "\"numero\":\"" + direccion.getNumero() + "\"," +
                "\"cruces\":\"" + direccion.getCruces() + "\"," +
                "\"ciudad\":\"" + direccion.getCiudad() + "\"," +
                "\"colonia\":\"" + direccion.getColonia() + "\"" +
                "}," +
                "\"activo\":" + activo +
                "}";
    }

}
