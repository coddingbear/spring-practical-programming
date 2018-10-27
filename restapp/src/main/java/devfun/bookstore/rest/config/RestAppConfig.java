package devfun.bookstore.rest.config;

import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages= {"devfun.bookstore.rest.controller"}) //,	useDefaultFilters=false, includeFilters= {@Filter(Controller.class)})
public class RestAppConfig extends WebMvcConfigurerAdapter {
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(marshallingHttpMessageConverter());
	}
	
	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
		MappingJackson2HttpMessageConverter converter =
				new MappingJackson2HttpMessageConverter();
		return converter;
	}
	
	// XML 처리를 위한 JAXB Marshaller/Unmarshaller를 추가
	@Bean
	public Jaxb2Marshaller jaxb2Marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setPackagesToScan(new String[] {
				"devfun.bookstore.common.domain",
				"devfun.bookstore.rest.domain"});
		return marshaller;
	}
	
	// MarshallingHttpMessageConverter를 추가하고, 앞서 생성한 JAXB Marshaller를 등록한다.
	@Bean
	public MarshallingHttpMessageConverter marshallingHttpMessageConverter() {
		MarshallingHttpMessageConverter converter = new MarshallingHttpMessageConverter();
		converter.setMarshaller(jaxb2Marshaller());
		converter.setUnmarshaller(jaxb2Marshaller());
		return converter;
	}
		
}
