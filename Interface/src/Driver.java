
public class Driver {

    public static void main(String[] args) {
        Tiger t = new Tiger();
        Chicken c = new Chicken();
        Orange o = new Orange();
        Apple a = new Apple();

        System.out.println(t.speak());
        System.out.println(c.speak());
        System.out.println(c.eat());
        System.out.println(a.eat());
        System.out.println(o.eat());

        Animal[] aArr = {t, c};

        for (int i = 0; i < aArr.length; i++) {
            if (aArr[i] instanceof Edible) {
                System.out.println(((Edible) aArr[i]).eat());
            }
            System.out.println(aArr[i].speak());
        }

        Object[] oArr = {t, c, a, o};

        for (int i = 0; i < oArr.length; i++) {
            if (oArr[i] instanceof Edible) {
                System.out.println(((Edible) oArr[i]).eat());
            }

            if (oArr[i] instanceof Animal) {
                System.out.println(((Animal) oArr[i]).speak());
            }
        }
    }
}
