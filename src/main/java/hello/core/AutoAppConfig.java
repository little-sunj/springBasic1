package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan( //여러 컴포넌트 (@Component) 스캔해서 자동 빈 등록
        basePackages = "hello.core.member",  //해당 위치에서부터 시작해서 찾아들어간다. (안하면 라이브러리까지 다 뒤짐 - 시간낭비..)


        //기존 예제코드내 수동으로 등록한 configuration 필터로 제외 (@Configuration implements @Component)
        // : 예제코드를 살리기 위함..일반적이지는 않다.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
}
