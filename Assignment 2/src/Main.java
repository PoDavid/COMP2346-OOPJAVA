public class Main {
    public static void main(String[] args){
        Rectangle r1 = new Rectangle(2,3);
        System.out.println(r1);
        System.out.println("Area is " + r1.getArea());

        Rectangle r2 = new Rectangle(5,1);
        System.out.println(r2);
        System.out.println("Area is " + r2.getArea());
//
//        Square s1 = new Square(3);
//        System.out.println(s1);
//        System.out.println("Area is " + s1.getArea());
//
//        Diamond d1 = new Diamond(3);
//        System.out.println(d1);
//        System.out.println("Area is " + d1.getArea());
//
//        Diamond d2 = new Diamond(6);
//        System.out.println(d2);
//        System.out.println("Area is " + d2.getArea());
//
        Shape u1 = r1.union(r2);
        System.out.println(u1);
        System.out.println("Area is " + u1.getArea());

//        Shape intersect = s1.intersect(d1);
//        System.out.println(intersect);
//        System.out.println("Area is " + intersect.getArea());
        //intersect.print_array();
    }
}
