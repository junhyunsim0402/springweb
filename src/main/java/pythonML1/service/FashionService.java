package pythonML1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import pythonML1.dto.FashionDto;
import pythonML1.dto.FashionRequestDto;
import pythonML1.entity.FashionEntity;
import pythonML1.repository.FashionRepository;

import java.util.List;

@Service @RequiredArgsConstructor
public class FashionService {
    @Autowired private final FashionRepository fashionRepository;
    // 2. 관리자가 Spring Boot REST API를 호출하여, 데이터베이스에 저장된 전체 데이터를 기반으로 예측 모델을 (재)학습하고 최신화할 수 있어야 한다.
    // 3. 일반 사용자가 Spring Boot REST API를 호출하여 4개 핵심 변수(나이, 성별, 유입 채널, 선호 스타일)를 입력하여 요청하면, 모델이 예측한 구매 옷 카테고리(0~7)를 실시간으로 반환한다.
    public boolean admin(){
        List<FashionEntity> fashionEntityList=fashionRepository.findAll();
        List<FashionDto> dtoList=fashionEntityList.stream().map(FashionEntity::ToDto).toList();
        WebClient webClient=WebClient.create();
        Object object=webClient.post()
                .uri("http://172.16.104.196:8000/api/model/learn")
                .bodyValue(dtoList)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
        return true;
    }
    public Object user(FashionRequestDto fashionRequestDto){
        WebClient webClient=WebClient.create();
        Object object=webClient.post()
                .uri("http://172.16.104.196:8000/api/model/predict")
                .bodyValue(fashionRequestDto)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
        return object;
    }
}
