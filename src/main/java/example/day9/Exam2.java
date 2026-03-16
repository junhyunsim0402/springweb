package example.day9;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

interface Calculator{ int plus(int x, int y); }  // 추상메소드 (구현{ }이 없는 메소드)

public class Exam2 {
    public static int plus(int x,int y){ return x+y; }
    public static void main(String[] args) {
        // [1] 메소드 호출
        int result=plus(3,2);
        System.out.println("result = " + result);
        // [2] 익명 구현체, 구현체란?추상메소드를 구현한 객체, 익명구현체란?클래스 없이 구현한 인스턴스
        // 인터페이스명 변수명=new 인터페이스명(){오버라이딩}
        Calculator calculator=new Calculator() {
            @Override
            public int plus(int x, int y) {
                return x-y;     // 재정의
            }
        };
        int result2=calculator.plus(5,2);   // 5-2
        System.out.println("result2 = " + result2);
        // [3] 람다 표현식, (매개변수) -> {구현부}
        Calculator calc2=(x,y)->x-y;
        int result3=calc2.plus(5,2);
        System.out.println("result3 = " + result3);
        // [4] 람다 표현식을 사용하는 *함수형*인터페이스 들
        // < > : 제네릭이란? 인스턴스(객체)를 생성할때 인스턴스(객체)내 멤버들의 타입 정의
        // List<Integer> : List 객체 생성시 내부에 Integer 타입으로 항목 구성 하겠다는 뜻
        // Map<String,Object> : Map 객체 1개 생성시 내부에 String타입으로 key,Object타입으로 Value구성 하겠다는 뜻
        // 즉] 객체내 멤버들의 타입을 매개로 정할 수 있는 타입
            // [4-1] Function< T, R > T : 입력받은 값 타입 , R : 결과반환타입, .apply(T)메소드 -> 3*2
        Function<Integer,Integer> function=(x) -> {return x*2;};    // return 생략시 {} 같이 생략
        System.out.println("function.apply(3) = " + function.apply(3)); // 결과 6
            // [4-2] Supplier< T > T : 입력없이 결과반환값타입, .get() 메소드
        Supplier<Double> supplier=()->{return 3.14;};   // return 생략시 {} 같이 생략
        System.out.println("supplier.get() = " + supplier.get());

        // [4-3] Consumer< T > T : 입력받은 값 타입이고 결과값 없다, .accept(T) 메소드
        Consumer<String> consumer=str-> System.out.println(str);
        consumer.accept("유재석");

        // [4-4] Predicate<T> T: 입력받아서 TRUE/FALSE 반환, .test(T)메소드
        Predicate<Integer> predicate=(x)->{return x%2==0;};
        System.out.println("predicate.test(3) = " + predicate.test(3)); // false
    }
}
