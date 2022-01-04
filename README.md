#### 1. Tomcat 서버를 시작할 때 웹 애플리케이션이 초기화하는 과정을 설명하라.
1.  tomcat이 뜨면서 ContextLoaderListener의 contextInitialized 메소드를 실행한다.
    * 데이터베이스 관련 쿼리가 실행된다.
2.  tomcat 내부에 서블릿으로서 DispatcherServlet의 인스턴스가 생성되고 "/"와 메핑된다.
    * init() 메소드가 실행된다.
        * RequestMapping 객체가 생성되고 initMapping를 통해 메핑 정보가 등록된다.

#### 2. Tomcat 서버를 시작한 후 http://localhost:8080으로 접근시 호출 순서 및 흐름을 설명하라.
1.  요청을 보내면 우선 server에서는 해당 요청을 처리하기 위한 socket 하나가 생성된다.
2.  socket으로 부터 connection을 얻은 후 요청 정보를 얻기 위한 input stream과 응답 데이터를 쓰기 위한 output stream을 생성한다.
3.  input stream을 통해 요청 데이터를 읽어 파싱한다.
    * HttpServletRequest, HttpServletResponse등으로 유용하게 사용할 인스턴스를 만든다.
4.  파싱 정보를 분석해 특정 url에 해당하는 servlet을 찾아 service 메소드를 호출한다.
5.  service 메소드 안에서 다시한번 url과 method를 분석해 해당하는 controller를 찾아 execute 메소드를 호출한다.
6.  컨트롤러는 비즈니스 로직을 수행하기 위해 service를 호출한다.
7.  비즈니스 로직을 수행한 결과로 model data가 나오게 되고 이를 포함하는 view를 리턴한다.
8.  DispatcherServlet은 리턴 받은 view를 최종 응답 스트림에 write하여 응답을 내려준다.

#### 7. next.web.qna package의 ShowController는 멀티 쓰레드 상황에서 문제가 발생하는 이유에 대해 설명하라.
* 
