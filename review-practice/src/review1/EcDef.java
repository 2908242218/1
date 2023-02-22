package review1;

/**
 * @author MikeCoder
 * @create 2023-01-1712:27
 */
public class EcDef extends Exception{
    static final long serialVersionUID = -3387516993124948L;
    EcDef(){};
    EcDef(String msg){
        super(msg);
    }
}
