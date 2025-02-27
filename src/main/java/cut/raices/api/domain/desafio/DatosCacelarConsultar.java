package cut.raices.api.domain.desafio;

import cut.raices.api.domain.consulta.MotivoCancelamiento;

public record DatosCacelarConsultar(

        Long idConsulta,
        MotivoCancelamiento motivoCancelamiento

) {
}
