package Model;

public class Cambio {

    private String campo;
    private String valorAnterior;
    private String valorNuevo;
    private String uuid;

    public Cambio(String campo ,String valorNuevo ,String valorAnterior ,String uuid) {
        this.campo = campo;
        this.valorAnterior = valorAnterior;
        this.valorNuevo = valorNuevo;
        this.uuid = uuid;
    }

    public String getCampo() {
        return campo;
    }

    public String getValorAnterior() {
        return valorAnterior;
    }

    public String getValorNuevo() {
        return valorNuevo;
    }

    public String getUuid() {
        return uuid;
    }
}
