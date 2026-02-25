package day2.practice1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.servlet.context.ServletComponentScan;

import java.io.IOException;

@SpringBootApplication @ServletComponentScan
public class practice1 {
    public static void main(String[] args) {
        SpringApplication.run(practice1.class);
    }
}

@WebServlet("/practice1")
class servlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 보내온 값 가져오기 = 요청한 정보가 들어있는 매개변수 : HttpServlertRequest req
        String  valueStr=req.getParameter("value");
        int value=Integer.parseInt(valueStr);
        // 계산된 값을 반환한다=요청한 클라이언트에게 계산 결과를 응답한다.HttpResponse resp
        resp.getWriter().println(value*2);
        System.out.println("(value*2) = " + (value*2));
    }// C

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String valueStr=req.getParameter("value");
        int value=Integer.parseInt(valueStr);
        resp.getWriter().println(value+2);
        System.out.println("value = " + (value+2));
    }// R

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String  valueStr=req.getParameter("value");
        int value=Integer.parseInt(valueStr);
        resp.getWriter().println(value/2);
        System.out.println("(value/2) = " + (value/2));
    }// U

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String valueStr = req.getParameter("value");
        int value = Integer.parseInt(valueStr);
        resp.getWriter().println(value % 2);
        System.out.println("(value%2) = " + (value%2));
    }// D
}