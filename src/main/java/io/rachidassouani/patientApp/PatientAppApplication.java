package io.rachidassouani.patientApp;

import io.rachidassouani.patientApp.model.Patient;
import io.rachidassouani.patientApp.service.PatientService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class PatientAppApplication implements CommandLineRunner {


	private PatientService patientService;

	public PatientAppApplication(PatientService patientService) {
		this.patientService = patientService;
	}

	public static void main(String[] args) {
		SpringApplication.run(PatientAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Patient patient = new Patient("Rachid", "Assouani", LocalDate.now(), true, 20);
		patientService.save(patient);

		Patient patient2 = new Patient("Test1", "Test2", LocalDate.now(), false, 30);
		patientService.save(patient2);
	}
}
