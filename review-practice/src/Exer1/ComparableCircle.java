package Exer1;

/**
 * @author MikeCoder
 * @create 2023-01-1222:29
 */
public  class ComparableCircle extends Circle implements CompareObject{

        public ComparableCircle() {
        }
        public ComparableCircle(double redius) {
            super(redius);
        }

        @Override
        public int compareTo(Object o) {
            if(this == o){
                return 0;
            }
            if(o instanceof Exer1.ComparableCircle){
                Exer1.ComparableCircle o1 = (Exer1.ComparableCircle) o;
                if(o1.getRedius() < this.getRedius()){
                    return 1;
                }
                else if(o1.getRedius() > this.getRedius() ){
                    return -1;
                }else{
                    return 0;
                }
            }
            else{
                throw new RuntimeException("类的类型不匹配");
            }
        }

}
