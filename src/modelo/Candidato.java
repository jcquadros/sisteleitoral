package modelo;

import java.time.LocalDate;

public class Candidato {
    int cdCargo; // 6 para deputado federal e 7 para deputado estadual
    int cdSituacaoCandidaturaTot; // processar apenas aqueles com valores 2 ou 16 que representam candidatos com
                                  // candidatura deferida
    int nrCandidato; // número do candidato
    int nrUrnaCandidato; // número do candidato na urna
    int nrPartido; // número do partido
    String sgPartido; // sigla do partido
    int nrFederacao; // número da federação -1 para candidato com partido isolado
    LocalDate dtNascimento; // data de nascimento
    int cdSitTotTurno; // 2 ou 3 representando candidato eleito
    int cdGenero; // 2 para masculino e 4 para feminino
    int nmTipoDestinacaoVotos; // quando for “Válido (legenda)” os votos deste candidato vão para a legenda (e
                               // devem ser computados para a legenda, mesmo em caso de
                               // CD_SITUACAO_CANDIDADO_TOT diferente de 2 ou 16)

    public Candidato(int cdCargo, int cdSituacaoCandidaturaTot, int nrCandidato, int nrUrnaCandidato, int nrPartido,
            String sgPartido, int nrFederacao, LocalDate dtNascimento, int cdSitTotTurno, int cdGenero,
            int nmTipoDestinacaoVotos) {
        this.cdCargo = cdCargo;
        this.cdSituacaoCandidaturaTot = cdSituacaoCandidaturaTot;
        this.nrCandidato = nrCandidato;
        this.nrUrnaCandidato = nrUrnaCandidato;
        this.nrPartido = nrPartido;
        this.sgPartido = sgPartido;
        this.nrFederacao = nrFederacao;
        this.dtNascimento = dtNascimento;
        this.cdSitTotTurno = cdSitTotTurno;
        this.cdGenero = cdGenero;
        this.nmTipoDestinacaoVotos = nmTipoDestinacaoVotos;
    }

    public int getCdCargo() {
        return cdCargo;
    }

    public int getCdSituacaoCandidaturaTot() {
        return cdSituacaoCandidaturaTot;
    }

    public int getNrCandidato() {
        return nrCandidato;
    }

    public int getNrUrnaCandidato() {
        return nrUrnaCandidato;
    }

    public int getNrPartido() {
        return nrPartido;
    }

    public String getSgPartido() {
        return sgPartido;
    }

    public int getNrFederacao() {
        return nrFederacao;
    }

    public LocalDate getDtNascimento() {
        return dtNascimento;
    }

    public int getCdSitTotTurno() {
        return cdSitTotTurno;
    }

    public int getCdGenero() {
        return cdGenero;
    }

    public int getNmTipoDestinacaoVotos() {
        return nmTipoDestinacaoVotos;
    }

    // setters
    public void setCdCargo(int cdCargo) {
        this.cdCargo = cdCargo;
    }

    public void setCdSituacaoCandidaturaTot(int cdSituacaoCandidaturaTot) {
        this.cdSituacaoCandidaturaTot = cdSituacaoCandidaturaTot;
    }

    public void setNrCandidato(int nrCandidato) {
        this.nrCandidato = nrCandidato;
    }

    public void setNrUrnaCandidato(int nrUrnaCandidato) {
        this.nrUrnaCandidato = nrUrnaCandidato;
    }

    public void setNrPartido(int nrPartido) {
        this.nrPartido = nrPartido;
    }

    public void setSgPartido(String sgPartido) {
        this.sgPartido = sgPartido;
    }

    public void setNrFederacao(int nrFederacao) {
        this.nrFederacao = nrFederacao;
    }

    public void setDtNascimento(LocalDate dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public void setCdSitTotTurno(int cdSitTotTurno) {
        this.cdSitTotTurno = cdSitTotTurno;
    }

    public void setCdGenero(int cdGenero) {
        this.cdGenero = cdGenero;
    }

    public void setNmTipoDestinacaoVotos(int nmTipoDestinacaoVotos) {
        this.nmTipoDestinacaoVotos = nmTipoDestinacaoVotos;
    }

}
