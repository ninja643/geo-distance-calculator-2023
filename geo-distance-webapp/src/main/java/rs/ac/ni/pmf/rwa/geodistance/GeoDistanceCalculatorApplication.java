package rs.ac.ni.pmf.rwa.geodistance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {
//		DataSourceAutoConfiguration.class
})
public class GeoDistanceCalculatorApplication
{

	public static void main(String[] args)
	{
		SpringApplication.run(GeoDistanceCalculatorApplication.class, args);
	}
}
