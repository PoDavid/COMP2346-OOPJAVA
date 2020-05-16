public class Task3 {
    public static void main(String args[]) {
        Animal a = new Dog();
        System.out.println(a.weight);
        //System print 5 which is the value assigned to weight in class Animal
        //In static binding, the Type(class) of the object determines what field to be accessed.
        //In the above case, a is a Dog object but with type Animal, thus the field (weight=5) in Animal is used,
        //instead of the actual class of the object(Dog).
    }
}
class Animal{
    int weight = 5;
}
class Dog extends Animal{
    int weight = 10;
}