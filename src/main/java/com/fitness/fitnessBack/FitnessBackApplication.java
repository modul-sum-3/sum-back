package com.fitness.fitnessBack;

import com.fitness.fitnessBack.auth.model.RegisterRequest;
import com.fitness.fitnessBack.auth.service.AuthenticationService;
import com.fitness.fitnessBack.VisitRanking.model.Rating;
import com.fitness.fitnessBack.VisitRanking.model.VisitRanking;
import com.fitness.fitnessBack.VisitRanking.repository.VisitRankingRepository;
import com.fitness.fitnessBack.carnet_transaction.model.CarnetTransaction;
import com.fitness.fitnessBack.carnet_transaction.repository.TransactionRepository;
import com.fitness.fitnessBack.carnets.model.Carnet;
import com.fitness.fitnessBack.carnets.repository.CarnetRepository;
import com.fitness.fitnessBack.client.model.Client;
import com.fitness.fitnessBack.client.repository.ClientRepository;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableMongoRepositories
@EnableScheduling
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
	private VisitRankingRepository visitRankingRepository;

	@Autowired
	private TrainingService trainingService;

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private CarnetRepository carnetRepository;
	Logger logger = LoggerFactory.getLogger(FitnessBackApplication.class);
	public static void main(String[] args) {
		System.setProperty("spring.devtools.restart.enabled", "false");
		SpringApplication.run(FitnessBackApplication.class, args);

	}

	private List<Trainer> trainerList = new ArrayList<>();
	private List<Club> clubs = new ArrayList<>();
	private List<Room> rooms = new ArrayList<>();

	private List<VisitRanking> visitRankings = new ArrayList<>();

	private List<Client> clients = new ArrayList<>();

	private List<Category> categories = new ArrayList<>();

	private List<Training> trainings = new ArrayList<>();

	private byte[] icons;

	{
		try {
			icons = Files.readAllBytes(Paths.get("src\\main\\resources\\Icons\\Gym-Transparent.png"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private List<Employee> employees = new ArrayList<>();

	private String password = "1234";

	private void saveList() {
		for (int i = 1; i <= 10; i++) {
			trainerList.add(new Trainer("Jan", "Kowalski" + i, "emailTrener" + i + "@google.com", "0000000",
					LocalDate.of(1999, i, 1)));
		}
		categories.add(new Category("Zumba", null));
		categories.add(new Category("Box", null));
		categories.add(new Category("Yoga", null));
		categories.add(new Category("Stretching", null));

		rooms.add(new Room("Large room", List.of(categories.get(1), categories.get(4))));
		rooms.add(new Room("Boxing room", List.of(categories.get(2))));
		rooms.add(new Room("Yoga room", List.of(categories.get(3))));
		rooms.add(new Room("Large room", List.of(categories.get(1), categories.get(4))));
		rooms.add(new Room("Small room", List.of(categories.get(4))));
		rooms.add(new Room("Dance room", List.of(categories.get(1))));
		rooms.add(new Room("Small room", List.of(categories.get(4))));
		rooms.add(new Room("Yoga room", List.of(categories.get(3))));

		clubs.add(new Club("FitNest Łódź Centrum", "Centrum", "Łódź", "Piotrkowska 55","95-001", List.of(rooms.get(0),rooms.get(1),rooms.get(2))));
		clubs.add(new Club("FitNest Łódź Baluty", "Baluty", "Łódź", "Łagiewnicka 118","95-002" , List.of(rooms.get(3),rooms.get(4),rooms.get(5))));
		clubs.add(new Club("FitNest Warszawa Centrum", "Centrum", "Warszawa", "Nowogrodzka 40","95-003" , List.of(rooms.get(6),rooms.get(7),rooms.get(8))));

		for (int i = 1; i <= 3; i++) {
			employees.add(new Employee("Karol", "Kowalski" + i, "emailEmployee" + i + "@google.com", "0000000",
					LocalDate.of(1999, i, 1), clubs.get(i - 1)));
		}
		for (int i = 1; i <= 3; i++) {
			trainings.add(new Training(clubs.get(i-1), rooms.get(i-1).getCategoryList().get(i-1) ,rooms.get(i - 1), trainerList.get(i - 1),
					10, 60L, ZonedDateTime.of(2024, 1, i, 10 + i, 10, 0, 0, ZoneId.of("Z"))));
		}

		for (int i = 1; i <= 10; i++) {
			visitRankings.add(new VisitRanking(ZonedDateTime.of(2023, 1, i, 10 + i, 10, 0, 0, ZoneId.of("Z")),
					clients.get(i % 3), trainings.get(i % 3), trainings.get(i % 3).getClub(), Rating.Good));
		}
	}

	@EventListener
	public void onReady(ApplicationReadyEvent e) {
		for (int i = 1; i <= 3; i++) {
			clients.add(new Client("Karol", "Kowalski" + i, "+48000100100", "emailKlienta" + i + "@google.com",
					LocalDate.of(1999, i, 1)));
		}
		for (int i = 0; i < 3; i++) {
			authenticationServiceService.register(new RegisterRequest(clients.get(i), password));
		}
		clients = clientRepository.findAll() ;
		saveList();
		for (int i = 0; i < 10; i++) {
			trainerService.saveTrainer(new TrainerPass(trainerList.get(i), password));
		}
		categoryRepository.saveAll(categories);
		roomRepository.saveAll(rooms);
		clubRepository.saveAll(clubs);
		for (int i = 0; i < 3; i++) {
			employeeService.saveEmployee(new EmployeePass(employees.get(i), password));
		}
		trainingRepository.saveAll(trainings);

		for (int i = 0; i < 3; i++) {
			trainingService.addClient(1L, clients.get(i));
		}
		trainings = trainingRepository.findAll();

		visitRankingRepository.saveAll(visitRankings);
		Carnet carnet = carnetRepository.save(new Carnet(30.00,"FIT CLASSIC",30L,categories,"Classic one-month pass. Allows you to use the gym as well as any type of training available. Suggested option for those who want to see if our gyms and workouts suit you."));
		carnetRepository.save(new Carnet(20.00,"STUDENT CLASSIC",30L,categories,"One-month pass for students. Allows you to use the gym as well as any type of training available. Suggested option for those who want to see if our gyms and workouts suit you. At the entrance to the gym, you must show your student ID card"));
		carnetRepository.save(new Carnet(75.00,"FIT 90-DAYS",90L,categories,"Classic 90-day pass. Allows you to use the gym as well as any type of training available. Suggested option for those familiar with our clubs - save as much as $15 with this pass!"));
		carnetRepository.save(new Carnet(45.00,"STUDENT 90-DAYS",90L,categories,"A 90-day pass for students. Allows you to use the gym as well as any type of training available. Suggested option for those familiar with our clubs - save as much as $15 with this pass! At the entrance to the gym you must your student ID card"));
		transactionRepository.save(new CarnetTransaction(ZonedDateTime.now(),clients.get(0),carnet.getId()));
	}
}
