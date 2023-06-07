package rs.ac.ni.pmf.rwa.geodistance;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@SpringBootApplication(exclude = {
			SecurityAutoConfiguration.class
	//		DataSourceAutoConfiguration.class
})
@EnableTransactionManagement
@SecurityScheme(name = "GeoDistanceAPI", scheme = "basic", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
public class GeoDistanceCalculatorApplication
{

	public static void main(String[] args)
	{
		SpringApplication.run(GeoDistanceCalculatorApplication.class, args);
	}

	@Bean
	public CommandLineRunner afterInit(final SomeComponent service)
	{
		return args -> {
			//			service.initializeData();
			//			service.initializeData2();
			service.initializeData3();
			//			service.showUsers();
			//
			service.viewData();
		};
	}
	//
}
