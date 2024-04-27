package hello.itemservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;

@SpringBootApplication
public class ItemServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItemServiceApplication.class, args);
	}

/*
	@Bean // 디폴트로 등록되므로 작성하지 않아도 됨
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource(); // messages.properties를 읽어들임
		messageSource.setBasenames("messages", "errors"); // 여러 파일을 한 번에 지정할 수 있음
		messageSource.setDefaultEncoding("utf-8");
		return messageSource;
	}
*/
}
