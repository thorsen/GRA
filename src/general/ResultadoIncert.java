package general;

public class ResultadoIncert {
    private Object nivel1;
    private Object nivel2;
    private Double incertA;
    private Double incertB;
    private Integer tipo;
    
    public static final Integer TIPO_SPL = 0;
    public static final Integer TIPO_VELOCIDAD = 1;
        
    public ResultadoIncert(Object nivel1, Object nivel2, Double incertA, Double incertB, Integer tipo) {
        this.nivel1 = nivel1;
        this.nivel2 = nivel2;
        this.incertA = incertA;
        this.incertB = incertB;
        this.tipo = tipo;
    }

    public Double getIncertA() {
        return incertA;
    }

    public void setIncertA(Double incertA) {
        this.incertA = incertA;
    }

    public Double getIncertB() {
        return incertB;
    }

    public void setIncertB(Double incertB) {
        this.incertB = incertB;
    }

    public Object getNivel1() {
        return nivel1;
    }

    public void setNivel1(Object nivel1) {
        this.nivel1 = nivel1;
    }

    public Object getNivel2() {
        return nivel2;
    }

    public void setNivel2(Object nivel2) {
        this.nivel2 = nivel2;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }
    
    public Double getIncertCombinada() {
        Double res = null;
        
        if (this.incertA != null && this.incertB != null) {
            res = Math.sqrt(Math.pow(this.incertA, 2.0) + Math.pow(this.incertB, 2.0));
        }
        
        return res;
    }
}
