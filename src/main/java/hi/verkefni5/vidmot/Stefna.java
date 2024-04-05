package hi.verkefni5.vidmot;

public enum Stefna {
    VINSTRI(180),
    HAEGRI(0),
    NIDUR (270), UP(180);

    private final int gradur;
    Stefna(int s) {
        gradur = s;
    }
}
