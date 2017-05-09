package platformer4d;



public enum Dimension{
    x("x"),
    y("y"),
    z("z"),
    w("w");
    String asString;
    Dimension(String s){
        asString = s;
    }
}
