package com.fitness.fitnessBack;

import com.fitness.fitnessBack.auth.model.RegisterRequest;
import com.fitness.fitnessBack.auth.service.AuthenticationService;
import com.fitness.fitnessBack.client.model.Client;
import com.fitness.fitnessBack.club.model.Club;
import com.fitness.fitnessBack.club.repository.ClubRepository;
import com.fitness.fitnessBack.employee.model.EmployeePass;
import com.fitness.fitnessBack.employee.service.EmployeeService;
import com.fitness.fitnessBack.room.model.Room;
import com.fitness.fitnessBack.room.repository.RoomRepository;
import com.fitness.fitnessBack.category.model.Category;
import com.fitness.fitnessBack.category.repository.CategoryRepository;
import com.fitness.fitnessBack.employee.model.Employee;
import com.fitness.fitnessBack.trainer.model.Trainer;
import com.fitness.fitnessBack.trainer.model.TrainerPass;
import com.fitness.fitnessBack.trainer.service.TrainerService;
import com.fitness.fitnessBack.training.model.Training;
import com.fitness.fitnessBack.training.repository.TrainingRepository;
import com.fitness.fitnessBack.training.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class FitnessBackApplication {
	@Autowired
	private TrainerService trainerService;

	@Autowired
	private ClubRepository clubRepository;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private RoomRepository roomRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private AuthenticationService authenticationServiceService;

	@Autowired
	private TrainingRepository trainingRepository;

	@Autowired
	private TrainingService trainingService;

	public static void main(String[] args) {
		System.setProperty("spring.devtools.restart.enabled", "false");
		SpringApplication.run(FitnessBackApplication.class, args);

	}

	private List<Trainer> trainerList = new ArrayList<>();
	private List<Club> clubs = new ArrayList<>();
	private List<Room> rooms = new ArrayList<>();

	private List<Room> rooms2 = new ArrayList<>();

	private List<Client> clients = new ArrayList<>();
	private List<Category> categories = new ArrayList<>();

	private List<Training> trainings = new ArrayList<>();
	private byte[] icons;

	private List<Employee> employees = new ArrayList<>();

	private String password = "1234";

	private void saveList() {
		for (int i = 1; i <= 10; i++) {
			trainerList.add(new Trainer("Jan", "Kowalski" + i, "emailTrener" + i + "@google.com", "0000000",
					LocalDate.of(1999, i, 1)));
		}
		for (int i = 1; i <= 3; i++) {
			clubs.add(new Club("name" + i));
		}
		for (int i = 1; i <= 3; i++) {
			rooms.add(new Room("name" + i, icons,clubs.get(1)));
		}
		for (int i = 1; i <= 3; i++) {
			categories.add(new Category("name" + i, rooms));
		}
		for (int i = 1; i <= 3; i++) {
			employees.add(new Employee("Karol", "Kowalski" + i, "emailEmployee" + i + "@google.com", "0000000",
					LocalDate.of(1999, i, 1), clubs.get(i - 1)));
		}
		for (int i = 1; i <= 3; i++) {
			clients.add(new Client("Karol", "Kowalski" + i , "+48000100100","emailKlienta" + i + "@google.com",
					LocalDate.of(1999, i, 1)));
		}
		for (int i = 1; i <= 3; i++) {
			trainings.add(new Training(clubs.get(0), rooms.get(i-1), trainerList.get(i-1) , categories.get(i-1),10,
					ZonedDateTime.of(2024, 1, i,10+i,10,0,0, ZoneId.of("Z"))));
		}

	}

	@EventListener
	public void onReady(ApplicationReadyEvent e) {
		saveList();
		for (int i = 0; i < 3; i++) {
			authenticationServiceService.register(new RegisterRequest(clients.get(i),password));
		}

		for (int i = 0; i < 10; i++) {
			trainerService.saveTrainer(new TrainerPass(trainerList.get(i),password));
		}
		clubRepository.saveAll(clubs);
		for (int i = 0; i < 3; i++) {
			employeeService.saveEmployee(new EmployeePass(employees.get(i), password));
		}
		categoryRepository.saveAll(categories);
		roomRepository.saveAll(rooms);
		trainingRepository.saveAll(trainings);
		for (int i = 0; i < 3; i++) {
			trainingService.addClient(1L, clients.get(i));
		}
}
}
