package hello.core.singleton;

public class SingletonService {

    //static 클래스 레벨로 올라가기 때문에 1개만 존재
    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance() {
        return instance;
    }

    private  SingletonService() {}
    
    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }

    /*
    * 싱글톤 패턴 문제점
    *
    * 싱글톤 패턴을 구현하는 코드 자체가 많이 들어간다.
    * 의존관계상 클라이언트가 구체 클래스에 의존한다. (DIP 위반)
    * 클라이언트가 구체 클래스에 의존해서 OCP 원칙도 위반할 가능성이 높다.
    * 테스트하기 어렵다.
    * 내부 속성을 변경하거나 초기화 하기가 어렵다.
    * private 생성자로 자식 클래스를 만들기가 어렵다.
    * 결론적으로 유연성이 떨어진다.
    * 안티패턴으로 불리기도 한다.
    * */

    /*
    * 스프링 컨테이너는 싱글톤 패턴의 문제점을 해결하면서,
    * 객체 인스턴스를 싱글톤(1개만 생성)으로 관리한다.
    * */
}
