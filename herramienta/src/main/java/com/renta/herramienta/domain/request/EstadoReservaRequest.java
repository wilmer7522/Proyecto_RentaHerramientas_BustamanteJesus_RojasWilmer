package com.renta.herramienta.domain.request;

import com.renta.herramienta.domain.entities.EstadoReserva;

public class EstadoReservaRequest {
    private EstadoReserva estado;

    public EstadoReserva getEstado() {
        return estado;
    }

    public void setEstado(EstadoReserva estado) {
        this.estado = estado;
    }
}

