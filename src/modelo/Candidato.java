package modelo;

import java.time.LocalDate;

public class Candidato {
    private int cdCargo;
    private int cdSituacaoCandidatoTot;
    private int nrCandidato;
    private String nmUrnaCandidato;
    private int nrPartido;
    private String sgPartido;
    private int nrFederacao;
    private String dtNascimento;
    private int cdSitTotTurno;
    private int cdGenero;
    private String nmTipoDestinacaoVotos;

    public Candidato(int cdCargo, int cdSituacaoCandidatoTot, int nrCandidato, String nmUrnaCandidato, int nrPartido,
            String sgPartido, int nrFederacao, String dtNascimento, int cdSitTotTurno, int cdGenero,
            String nmTipoDestinacaoVotos) {
        this.cdCargo = cdCargo;
        this.cdSituacaoCandidatoTot = cdSituacaoCandidatoTot;
        this.nrCandidato = nrCandidato;
        this.nmUrnaCandidato = nmUrnaCandidato;
        this.nrPartido = nrPartido;
        this.sgPartido = sgPartido;
        this.nrFederacao = nrFederacao;
        this.dtNascimento = dtNascimento;
        this.cdSitTotTurno = cdSitTotTurno;
        this.cdGenero = cdGenero;
        this.nmTipoDestinacaoVotos = nmTipoDestinacaoVotos;
    }

}
