package example.day9;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Exam3 {
    public static void main(String[] args) {
        // 람다표현식, (매개변수)->{구현부}
        // 스르림API : 데이터(매개변수) --> 중간연산 --> 최종출력
        List<Integer> number=List.of(1,2,3,4,5,6,7,8,9,10); // 임의의 데이터를 담고있는 리스트
        // [1] 리스트변수명.stream().forEach();   , 중간 연산 없이 바로 최종 출력
        // 매개변수에 반복변수를 하나씩 대입하여 return없는 반복문
        number.stream().forEach(( x ) -> { System.out.println(x*2+"\t"); });    // 2 4 6 8 ... 20
        // [2] 리스트변수명.stream().map( 중간 연산 ).collect(최종출력); : 리스트형태로 x*2를 출력
        // 매개변수에 반복변수를 하나씩 대입하여 return 있는 반복문, 반복 return값들을 .collect(Collectors.toXXX()) 반환 받는 타입
        List<Integer> result=
            number.stream().map( ( x ) -> x*2 ).collect(Collectors.toList());
        System.out.println("result = " + result);
        // [3] 리스트변수명.stream().map(중간연산).forEach( ( ) -> { } )
        number.stream().map(x->x*2).forEach(( 중간연산결과 )->{
            System.out.println("result = " + 중간연산결과);
        });
        // [4] 리스트변수명.stream().filter(중간연산).forEach( ( ) -> { } )
        number.stream()
                .filter(x->x%2==0)   // 중간연산, 짝수 찾기 반환
                .forEach(y->{ System.out.println("y = " + y); });   // 최종출력
        // [5] 리스트변수명..stream().sorted().forEach( ( ) -> { } ) 오름 차순
        number.stream() // 리스트 내 데이터들의 흐름 시작
                .sorted(Comparator.reverseOrder())   // 중간연산, 오름차순, 내림차순( Comparator.reverseOrder() )
                .forEach(y-> System.out.println("y = " + y));
        // [6]
        number.stream()
                .distinct() // 중간 연산, 중복 제거
                .collect(Collectors.toList());
        // [7] 
        number.stream()
                .limit(5)   //중간 연산, 앞에서부터 N개까지
                .forEach(y-> System.out.println("y = " + y));
        /*
        * 스트림이란? 데이터 다니는 연속적인 흐름
        *   - 데이터들 --> 중간연산 --> 최종연산
        *   - 중간연산은 여러개 가능
        *   - 최종연산은 반드시 1개 가능
        *
        * 주요 연산
        *   .map() : 키값에 값을 넣기
        *   .filter() : 찾기
        *   .sorted() : 오름/내림 차순
        *   .distinct() : 중복 제거
        *   .limit() : 앞에서부터 N개 까지
        *
        * 최종 연산
        *   - .forEach() : 반복
        *   - .collect : 어떤 타입으로 반환할지
        * */
    }
}
