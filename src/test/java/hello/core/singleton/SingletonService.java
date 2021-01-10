package hello.core.singleton;

public class SingletonService {

    //static 클래스 레벨로 올라가기 때문에 1개만 존재
    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance() {
        return instance;
    }

    private  SingletonService() {
    }
    
    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }

}
