package pythonML.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import pythonML.dto.CarDto;
import pythonML.entity.CarEntity;
import pythonML.repository.CarRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;

    // [1] 학습요청
    public boolean 학습요청( ){
        List<CarEntity> entityList = carRepository.findAll();
        List<CarDto> dtoList = entityList.stream().map( CarEntity :: ToDto ).toList();
        // 전제조회 확인
        System.out.println("dtoList.size = " + dtoList.size());
        // **FastAPI(파이썬) 에게 자료 보내고 학습하기.**
        WebClient webClient = WebClient.create();
        Object object = webClient.post() // post method
                .uri("http://localhost:8000/api/model/learn") // 파이썬 주소
                .bodyValue( dtoList ) // JPA에서 꺼내온 DTO 보낸다. < 파이썬에게 보낼 자료 >
                .retrieve() // 응답(반환) 받기
                .bodyToMono( Object.class ) // 응답자료 타입 // 타입명.class
                .block(); // 동기화
        return true;
    }
    // [2] 예측요청
    public Object 예측요청( CarDto carDto ){
        System.out.println("carDto = " + carDto);
        // ***FastAPI(파이썬)에게 자료 보내고 예측 받아오기 **
        WebClient webClient = WebClient.create();
        Object object = webClient.post()
                .uri("http://localhost:8000/api/model/predict")
                .bodyValue( carDto )
                .retrieve()
                .bodyToMono( Object.class )
                .block();
        return object;
    }
}

