package br.com.softdesign.votacao.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Objects;

@Getter
@Setter
public class SessaoRequest {

    @NotNull(message = "O campo 'idPauta' não pode ser nulo.")
    private Long idPauta;

    private Integer tempoPautaMinutos;

    public Integer getTempoPautaMinutos() {
        return Objects.isNull(tempoPautaMinutos) ? 1 : tempoPautaMinutos;
    }

}
