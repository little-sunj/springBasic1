package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;
import java.lang.annotation.Annotation;

import static org.assertj.core.api.Assertions.*;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertThat(prototypeBean2.getCount()).isEqualTo(1);
    }

    //싱글톤 빈이 프로토타입 빈을 사용하게 된다.
    //그러나 싱글톤 빈은 생성 시점에만 의존관계 주입을 받기 때문에
    //프로토타입 빈이 새로 생성되기는 하지만, 싱글톤 빈과 함께 계속 유지되는것이 문제다.
    //프로토타입 빈은 사용할 때마다 새로 생성해서 사용하길 원할 것이기 때문.
    @Test
    void singletonClientUsePrototype() {
        AnnotationConfigApplicationContext ac =
                new AnnotationConfigApplicationContext(ClientBean.class,PrototypeBean.class);
        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(1);
    }

    @Scope("singleton")
    static class ClientBean {
        //private final PrototypeBean prototypeBean; //생성시점에 주입

        @Autowired
        private Provider<PrototypeBean> prototypeBeanProvider;
        //Provider :별도의 라이브러리(javax.inject)를 추가해야하는 단점이 있다. get을 사용
        //자바 표준이기 때문에 다른 컨테이너에서도 사용할 수 있다.

//        private ObjectProvider<PrototypeBean> prototypeBeanProvider;
        //private ObjectFactory<PrototypeBean> prototypeBeanProvider;
        //ObjectFactory 는 getObject 기능만 제공
        //ObjectProvider 는 다른 기능도 좀더 추가되어서 제공



//        @Autowired
//        public ClientBean(PrototypeBean prototypeBean) {
//            this.prototypeBean = prototypeBean;
//        }
        public int logic() {
            PrototypeBean prototypeBean = prototypeBeanProvider.get();
//            PrototypeBean prototypeBean = prototypeBeanProvider.getObject();
            //getObject를 호출하면 그때 컨테이너에서 빈을 찾아서 반환
            //항상 새로운 프로토타입 빈이 생성되는 것을 확인할 수 있다.
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }
    }

    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;
        public  void  addCount() {
            count++;
        }
        public int getCount() {
            return count;
        }
        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init" + this);
        }
        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}

/*
* 싱글톤 빈으로 대부분의 문제를 해결할 수 있기 때문에
* 프로토타입 빈을 직접적으로 사용하는 일은 매우 드물다.
*
* JSR-330 Provider / ObjectProvider 등은 프로토타입 뿐 아니라 DL이 필요한 경우 언제든지 사용가능하다.
* */