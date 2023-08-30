package global.govstack.payment.bb.adapter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class AdapterApplication {

  public static void main(String[] args) {
    SpringApplication.run(AdapterApplication.class, args);
  }
}
