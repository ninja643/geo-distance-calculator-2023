package rs.ac.ni.pmf.rwa.geodistance;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication(exclude = {
//		DataSourceAutoConfiguration.class
})
@EnableTransactionManagement
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
