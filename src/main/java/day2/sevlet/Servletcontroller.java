package day2.sevlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

// 서블릿 이란? 자바 회사에서 HTTP웹 동적처리 하기위한 라이브러리/클래스
// 사용법
// 1. 현재 클래스의 "HttpServlet" 클래스로 부터 상속 받는다.
// 2. 현재 클래스 위에 @WebServlet("/URL"), 주소 설정하고 중복없이 영문으로 정의한다(한글은 깨질 가능성이 있음)
// 3. (스프링환경에만 해당) AppStart위에 순수 클래스를 사용할거
@WebServlet("/day2/sevlet") // --> localhost:8080/day2/servlet 요청시 통신 가능한 클래스 정의
public class Servletcontroller extends HttpServlet {

    // 순수 자바의 메소드/함수
    boolean method( int param ){ return true; }

    // 1. 서블릿 클래스로 부터 init함수가 최초 1번 실행된다.
    @Override
    public void init() throws ServletException {
        System.out.println("init 함수 실행");
        super.init();
    }

    // 2. 서블릿 클래스로 부터 HTTP 요청마다 service함수 실행된다
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        System.out.println("service 함수 실행");
        super.service(req, res);
    }

    // 3. HTTP 메소드 이란? GET,POST,PUT,DELETE 으로 통신 방법
    // 서블릿 객체는 요청이 끝나도 사라지지 않는다. 다음 요청에 재사용
    // HttpServletRequest,HttpServletResponse 객체는 요청이 끝나면 사라진다
    // 3.1 GET방식
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ServletController.doGet");
        // HTTP 요청시 포함된 매개변수 확인, http://localhost:8080/day2/sevlet?data=아무값
        String data=req.getParameter("data");
        System.out.println("data = " + data);
        // HTTP 요청시 클라이언트에게 응답
        resp.getWriter().println("client Rssponse: HI!");
    }

    // 3.2 POST, 브라우저(크롬)의 주소입력창에 요청은 무조건 GET방식 이므로 POST,PUT,DELETE는 포스트맨VS탈렌트API사용
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Servletcontroller.doPost");
        String data=req.getParameter("data");
        System.out.println("data = " + data);
        resp.getWriter().println("client Rssponse: HI!");
    }

    // 3.3 PUT : 사용자가 요청을 하고 서블릿이 없으면 서비스가 실행(GET,POST,PUT,DELETE)됨
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Servletcontroller.doPost");
        String data=req.getParameter("data");
        System.out.println("data = " + data);
        resp.getWriter().println("client Rssponse: HI!");
    }

    // 3.4 DELETE :
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Servletcontroller.doPost");
        String data=req.getParameter("data");
        System.out.println("data = " + data);
        resp.getWriter().println("client Rssponse: HI!");
    }
    // HTTP란? 클라이언트와 서버간의 통신하는 규칙
    // 클라이언트가 1번 요청의 1번 응답, 즉 요청이 없으면 응답할 수 없다.
    // 서블릿 클래스내 동일한 시그니처(선언부)를 갖는 메소드는 존재할 수 없다 --> 스프링 환경에서 보완 제공
}
