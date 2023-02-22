package domain;

/**
 * @author MikeCoder
 * @create 2023-01-2014:55
 * @description:domain
 * @verson:
 */
public class Architect extends Designer{
    private int stock;
    Architect(){};
    Architect(int id,String name,int age,double salary,Equipment equipment,double bonus,int stock){
        super(id,name,age,salary,equipment,bonus);
        this.stock = stock;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
