package domain;

/**
 * @author MikeCoder
 * @create 2023-01-1919:35
 * @description:domain
 * @verson:
 */
public class Designer extends Programmer{
    private double bonus;
    Designer(){};

    Designer(int id,String name,int age,double salary,Equipment equipment,double bonus){
        super(id,name,age,salary,equipment);
        this.bonus = bonus;
    };

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }
}
