package utility;

/**
 * @author MikeCoder
 * @create 2023-01-1919:17
 * @description:utility
 * @verson:
 */
public class Status {
    private final String NAME;
    Status(String name){
        this.NAME = name;
    }
    public static final Status FREE = new Status("FREE");
    public static final Status BUSY = new Status("BUSY");
    public static final Status VOCATION = new Status("VOCATION");

}
