package pythonML.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service @RequiredArgsConstructor
public class ConnectService {
    RestTemplate restTemplate=new RestTemplate();
    String url="http://localhost:8080/python";

}
