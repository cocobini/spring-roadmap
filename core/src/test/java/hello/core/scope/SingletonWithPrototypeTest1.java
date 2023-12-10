package hello.core.scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import hello.core.scope.PrototypeTest.PrototypeBean;

public class SingletonWithPrototypeTest1 {
    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        PrototypeBean bean1 = ac.getBean(PrototypeBean.class);
        bean1.addCount();
        Assertions.assertThat(bean1.getCount()).isEqualTo(1);

        PrototypeBean bean2 = ac.getBean(PrototypeBean.class);
        bean2.addCount();
        Assertions.assertThat(bean2.getCount()).isEqualTo(1);

        ac.close();
    }

    @Test
    void singletonClientUsePrototype() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        ClientBean bean1 = ac.getBean(ClientBean.class);
        Assertions.assertThat(bean1.logic()).isEqualTo(1);

        ClientBean bean2 = ac.getBean(ClientBean.class);
        Assertions.assertThat(bean2.logic()).isEqualTo(1);

        ac.close();
    }

    @Scope // singleton이 디폴트라서 적지 않아도 됨
    static class ClientBean {
        // 생성 시 프로토타입 빈이 주입됨
        // private final PrototypeBean prototypeBean;

        // @Autowired
        // private ObjectProvider<PrototypeBean> prototypeBeanProvider;

        @Autowired
        private Provider<PrototypeBean> prototypeBeanProvider;

        // @Autowired
        // public ClientBean(PrototypeBean prototypeBean) {
        //     this.prototypeBean = prototypeBean; // 최초 주입받은 (프로토타입)빈을 반복하여 사용
        // }

        public int logic() {
            //PrototypeBean prototypeBean = prototypeBeanProvider.getObject(); // 요청 시마다 빈을 받아옴
            PrototypeBean prototypeBean = prototypeBeanProvider.get();
            prototypeBean.addCount(); 
            return prototypeBean.getCount();
        }
    }

    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init: " + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("SingletonBean.destroy");
        }
    }
}
