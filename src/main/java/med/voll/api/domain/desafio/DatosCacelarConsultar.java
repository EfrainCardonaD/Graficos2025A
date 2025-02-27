package med.voll.api.domain.desafio;

import med.voll.api.domain.consulta.MotivoCancelamiento;

public record DatosCacelarConsultar(

        Long idConsulta,
        MotivoCancelamiento motivoCancelamiento

) {
}
