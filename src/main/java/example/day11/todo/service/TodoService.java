package example.day11.todo.service;

import example.day11.todo.dto.TodoDto;
import example.day11.todo.entity.TodoEntity;
import example.day11.todo.repository.TodoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor    // final 멤버변수 생성자 제공
@Transactional
public class TodoService {
    @Autowired private final TodoRepository todoRepository;
    public List<TodoDto> findAll(){
        // 1] 모든 엔티티 조회
        List<TodoEntity> todoEntityList=todoRepository.findAll();
        // 2] 모든 엔티티 --> 모든 dto 변환
//            // 방법1
//        List<TodoDto> list1=new ArrayList<>();
//        for(int i=0;i<todoEntityList.size();i++){
//            TodoEntity entity=todoEntityList.get(i);
//            list1.add(entity.toDto());
//        }
//
//            // 방법 2
//        List<TodoDto> list2=new ArrayList<>();
//        todoEntityList.stream().forEach(entity->{ list2.add(entity.toDto()); });
//
//            // 방법 3
//        List<TodoDto> list3=todoEntityList.stream()// 스트림(데이터들 흐름) 시작
//                .map(entity->entity.toDto())  // 중간 연산
//                .collect(Collectors.toList());  // 최종 출력

            // 방법 4
        List<TodoDto> list4=todoEntityList.stream()
                .map(TodoEntity::toDto)  // 중간 연산, 람다식 대신에 메소드 레퍼런스 API( 미리 정의된 메소드 )
                // 클래스명 :: 메소드명
                .collect(Collectors.toList());
        return list4;
    }

    public TodoDto findById(int id){
//        // 방법 1
//        Optional<TodoEntity> optional=todoRepository.findById(id);
//        if(optional.isPresent()){
//            TodoDto todoDto=optional.get().toDto();
//        }
        // 방법 2
        TodoDto todoDto=todoRepository.findById(id)
                // 스트림(데이터들) 사용하지 않고 Optional 에서 map 메소드 지원
                .map(TodoEntity::toDto) // 중간 연산
                .orElse(null);   // 만약에 조회 결과가 없으면 null
        return todoDto;
    }
    public TodoDto query1(String title){
        // * findById 밖에 없으므로 리포지토리에서 findByTitle 만들자
            // 2-1  쿼리 메소드 호출
        TodoEntity entity=todoRepository.findByTitle(title);
            // 3-1 네이티브 쿼리 호출
        TodoEntity entity1=todoRepository.query1(title);
        return entity.toDto();
    }

    // 4. title과 content 개별 조회
    public Map<String,Object> query2(String title,String content){
        // 2-2 쿼리 메소드 호출
        Map<String,Object> result=todoRepository.findByTitleAndContent(title, content);
        System.out.println("result.toString() = " + result.toString());
        // 3-2 네이티브 메소드 호출
        return todoRepository.query2(title,content);
    }

    // 5. title이 포함된 개별 조회
    public List<TodoDto> query3(String title){
        List<TodoEntity> result=todoRepository.findByTitleContaining(title); // 2-3
        List<TodoEntity> entity=todoRepository.query3(title);       // 3-3
        return entity.stream()
                .map(TodoEntity::toDto) // 중간연산 메소드 레퍼런스 API, entity->dto변환
                .collect(Collectors.toList());  // 리스트 타입으로 최종 출력
    }

    // 6. Page인터페이스란? 페이징 처리 정보 담는 인터페이스
    public Page<TodoDto> page(int page,int size){
        // 1] 페이징 옵션 설정한다. PageRequest 구현체, .of(조회할 페이지 번호, 페이지당 개수, 정렬)
            // page-1 : JPA는 페이징번호가 0부터 시작함으로써 1페이지는 0, 2페이지는 1, 3페이지 2
            // size : 현재 (한)페이지에 조회할 자료/엔티티 개수
            // Sort.by(Sort.Direction.DESC, "정렬기준필드명") : 'id' 속성명으로 내림차순
        PageRequest pageRequest=PageRequest.of(page-1,size, Sort.by(Sort.Direction.DESC,"id"));
        // 2] findXXX(pageRequest) 구현체를 포함한다, 반환값은 Page<엔티티>
        Page<TodoEntity> entityPage=todoRepository.findAll(pageRequest);    // 전체조회에 대한 페이징처리
            // page.content : 조회된 엔티티들(list)
            // page.empty : 조회 실패 또는 없으면 true
            // page.first : 요청 페이징이 첫페이지이면 true,
            // page.totalElements : 전체 자료 개수
            // page.totalPages : 전체 페이징 개수
        // 3] Page<엔티티> --> Page<Dto>변환
        return entityPage.map( TodoEntity::toDto ); // map과 레퍼런스 api를 이용한 변환
    }

    // 7. 페이징 처리 2
    public Page<TodoDto> page2(String keyword,int page,int size){
        PageRequest pageRequest=PageRequest.of(page-1,size,Sort.by(Sort.Direction.DESC,"id"));
        // 2] 전체 조회 인지?? 키워드 조회 인지??
        Page<TodoEntity> result;    // 조회결과를 담는 Page 타입
        if(keyword==null || keyword.isBlank()) {// 만약 키워드가 비어있으면 전체조회
            result=todoRepository.findAll(pageRequest);
        }else{  // 아니면 키워드 조회
            result=todoRepository.query4(keyword,pageRequest);
        }
        return result.map(TodoEntity::toDto);
    }
}
