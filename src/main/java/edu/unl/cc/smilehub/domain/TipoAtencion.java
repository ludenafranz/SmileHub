package edu.unl.cc.smilehub.domain;

public enum TipoAtencion {
    PREVENCION_LIMPIEZA(20.0),
    DIAGNOSTICO_EVALUACION(30.0),
    RESTAURADORA(50.0),
    QUIRURGICA(100.0),
    ENDODONCIA(40.0),
    CIRUGIA_ORAL(120.0),
    PROTESIS_DENTAL(80.0),
    PERIODONCIA(60.0),
    CONTROL_SEGUIMIENTO(15.0),
    ESTETICA_DENTAL(70.0),
    ODONTOPEDIATRIA(25.0);

    private final double costo;

    TipoAtencion(double costo) {
        this.costo = costo;
    }

    public double getCosto() {
        return costo;
    }
}
