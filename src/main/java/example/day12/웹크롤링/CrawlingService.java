package example.day12.웹크롤링;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CrawlingService {

    // [1] Jsoup 이용한 특정 url html 정보 수집
    public List<String> test1(){
        List<String> list = new ArrayList<>(); // 여러개 문자열 저장 리스트
        // 1) 크롤링 URL 웹페이지 주소
        String url = "https://www.karnews.or.kr/news/articleList.html?sc_section_code=S1N1&view_type=sm";
        // 2) 크롤링 할 URL 요청하여 HTML 전체를 가져온다. Jsoup.connect( 주소 ).get();
        // Document , import org.jsoup.nodes.Document;
        try {
            Document document = Jsoup.connect( url ).get(); // 외부 통신은 일반예외가 주로 발생
            // 3) 특정한 마크업/요소 식별자 , document.select( "식별자" );
            Elements elements = document.select( ".titles > a" ); // 클래스가 titles 인 마크업 아래에 <a> 가져온다.
            // 4) 여러개 가져 왔다면 반복문 이용한 요소/마크업(Element) 1개씩 순회
            for( Element element : elements ){
                // vs innerHTML  비슷하게 마크업 사이 텍스트를 반환 <a> 여기! </a>
                String title = element.text();
                // 만약에 텍스트가 존재하면 리스트에 담기
                if( title.isBlank() ){ continue; } // 반복문 으로 (위로)이동  vs break;
                else{ list.add( title ); }
            }
        }catch ( Exception e ){System.out.println(e);}
        return list;
    } // f end

    // [2] Jsoup 이용한 HTML 정보 수집 , 페이지이동
    public List<Map<String, Object> > test2(){
        List<Map<String,Object>> list = new ArrayList<>(); // 책정보(dto/map)들을 담는 리스트 선언
        try {
            for( int page = 1 ; page <= 3 ; page++ ) { // page는 1부터 3까지
                // 1) 크롤링 URL 주소 ( 예스2 베스트셀러 일별 ) ++ 반복문 이용하여 페이지번호 여러개 요청
                String url = "https://www.yes24.com/product/category/daybestseller";
                url += "?categoryNumber=001"; // 베스트셀러 카테고리 번호
                url += "&pageNumber=" + page; // 크롤링할 페이지 번호 < page 변수로 활용 >
                url += "&pageSize=24"; // 페이지당 제품 수
                // 2) URL 연결
                Document document = Jsoup.connect( url ).get();
                // 3) 식별자 , 가져올 텍스트가 위치한 식별자와 상위 식별자 1~2 같이 선택한다. <중복 배제>
                Elements nameList = document.select(".info_name .gd_name");// 책이름 , .info_name  .gd_name
                Elements priceList = document.select(".info_price .txt_num .yes_b");// 책가격 , .info_price .txt_num .yes_b
                Elements imageList = document.select( ".img_bdr .lazy");// 책이미지 , .img_bdr .lazy
                // 4) 반복문 이용하여 여러개 요소/마크업들을 도서별 MAP 구성하여 LIST 저장
                for( int index = 0 ; index < nameList.size() ; index++ ){ // 첫번재 도서부터 마지막 도서까지
                    String name = nameList.get( index ).text(); // index번째 요소의 책이름(텍스트) 반환
                    String price = priceList.get( index ).text();
                    // text() : 마크업 사이 텍스트 반환 , attr( 속성명 ) : 해당 속성명의 속성값 반환
                    String image = imageList.get( index ).attr( "data-original" );
                    // 5) DTO/MAP 구성
                    Map<String, Object > map = new HashMap<>();
                    map.put( "name" , name ); map.put( "price" , price ); map.put("image" , image);
                    // 6)
                    list.add( map );
                }
            }
        } catch (Exception e) { System.out.println(e); }
        return list; // 리스트 반환
    } // f end

    // [3]
    public Map<String,Object> test3(){
        // 1] 크롬 드라이버 설치
        WebDriverManager.chromedriver().setup();
        // 2] 크롤링 할 웹 주소
        String url="https://weather.daum.net/";
        // 3] 크롬 드라이버 객체 생성
            // * 드라이버 옵션
        ChromeOptions options=new ChromeOptions();
        // options.addArguments("--headless=new","--disable-gpu"); // 크롬 백그아운드 실행
        WebDriver webDriver=new ChromeDriver(options);
        // 4] 크롬 드라이버 객체에 크롤링할 주소 넣기
        webDriver.get(url);
        // 5] 해당 페이지는 동적( 데이터를 표현하는데 부분적 시간필요 ) 페이지
            // new WebDriverWait ( 현재크롬객체, Duration.ofXXX( 대기 단위 ) )
        WebDriverWait wait=new WebDriverWait(webDriver, Duration.ofSeconds(1));
        // 6] 크롤링할 선택자, Element/요소/마크업/<마크업>
            // WebElement 변수면=wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("선택자")));
        WebElement webElement=wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".info_weather .num_deg")));    // 1) 온도 : info_weather -> num_deg
        System.out.println(webElement.getText());   // 크롤링된 요소/마크업 텍스트 확인
        WebElement webElement1=wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".tooltip_icon .ico_airstat1")));
        System.out.println(webElement1.getText());
        // 7] 가져온 정보들을 dto/map 구성
        Map<String,Object> map=new HashMap<>();
        map.put("온도",webElement1.getText());
        map.put("초미세먼지",webElement.getText());
        // 8] 안전하게 드라이버 객체 직접 종료
        webDriver.quit();
        return map;
    }

    // [4] CGV 특정 영화 관람평 크롤링( + 무한 스크롤 )
    public List<String> test4(){
        WebDriverManager.chromedriver().setup();    // 1] 크롬 설치
        String url="https://cgv.co.kr/cnm/cgvChart/movieChart/30000927";    // 2] 웹 크롤링할 주소(왕과 사는 남자)
        WebDriver webDriver=new ChromeDriver();     // 3] 크롬 객체
        webDriver.get(url); // 4] 크롬 객체내 url대입 하고 실행

        // * 자바에서 JS 제어하여 스크롤을 내리는 작업 *
        JavascriptExecutor js=(JavascriptExecutor) webDriver;    // (현재)크롬 객체에서 JS객체 꺼내기
        js.executeScript("window.scrollTo(0,document.body.scrollHeight)");       // .executeScript("JS문법");
            // window.scrollTo(100,document.body.scrollHeight);
                // 1. document.body.scrollHeight : 현재 화면에서 스크롤 전체 길이 = 높이 = 300px, 상단 꼭지점=0, 하단 꼭지점=300
                // 2. .scrollTo( 이동할 위치, 전체길이 )
        try{Thread.sleep(1000);}catch (Exception e){}   // 1초간 대기
        // 크롤링할 선택자로 요소 크롤링, reveiwCard_txt__RrTgu
        List<String> list=new ArrayList<>();
        for(int page=1;page<=10;page++){
            int startCount=list.size(); // 특정 반복문 시작, 현재 리뷰 개수
            // WebElement : 1개 요소 VS List<WebElements> : 여러개 요소
            // wait.until() VS webDriver.findElements
            List<WebElement> elements=webDriver.findElements(By.cssSelector(".reveiwCard_txt__RrTgu"));
            System.out.println("elements = " + elements);
            for(WebElement element:elements){
                System.out.println("확인용 = " + element);
                // 만약에 리스트에 없는 리뷰이면 리스트 추가
                String review=element.getText();
                if(list.contains(review)){continue;}    // .contains(찾을값) 만약에 찾을값이 존재하면 true
                else{ list.add(review); }
            }
            int endCount=list.size();   // 특정 반복분이 한번 종료 되었을때
            if(endCount==startCount){ break; }  // 리뷰개수가 시작과 끝 개수가 같다면 크롤링 중지
            else {
                // ============= 자바에서 JS 사용 : 스크롤을 내리는 작업 =================== //
                js.executeScript("window.scrollTo( 0 , document.body.scrollHeight ); ");
                try { Thread.sleep( 1000 ); } catch (Exception e) {}  // 1초 대기
            }
        }
        return list;
    }
} // c end

    /*
        - 웹크롤링 : 웹(페이지의) HTML 정보/자료 수집 과정
        - 웹페이지 마다 크롤링 허용 여부 : URL?robots.txt
            - https://www.jobkorea.co.kr/robots.txt
        - 정적페이지 : HTML  , 동적페이지 : JS( AXIOS/REACT )
            - 정적페이지 : Jsoup 라이브러리
            - 동적페이지 : Selenium 라이브러리 ( * 파이썬 동일 * )
        - Jsoup 라이브러리 : implementation 'org.jsoup:jsoup:1.22.1'
        - Selenium 라이브러리 :
            implementation 'org.seleniumhq.selenium:selenium-java:4.35.0'
            implementation 'io.github.bonigarcia:webdrivermanager:6.3.1'
                - 스프링 라이브러리 : https://start.spring.io/
                - 그 외 라이브러리 : https://mvnrepository.com/
    */