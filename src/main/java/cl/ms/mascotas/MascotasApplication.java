package cl.ms.mascotas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

@SpringBootApplication
public class MascotasApplication {

	public static void main(String[] args) {
		SpringApplication.run(MascotasApplication.class, args);
//		Locale loc = new Locale("en", "US");
//		DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT, loc);
//		System.out.println(dateFormat.format(new Date()));
	}

}
