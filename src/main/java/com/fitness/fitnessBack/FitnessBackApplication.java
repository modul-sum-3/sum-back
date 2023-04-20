package com.fitness.fitnessBack;

import com.fitness.fitnessBack.club.model.Club;
import com.fitness.fitnessBack.club.repository.ClubRepository;
import com.fitness.fitnessBack.room.model.Room;
import com.fitness.fitnessBack.room.repository.RoomRepository;
import com.fitness.fitnessBack.category.model.Category;
import com.fitness.fitnessBack.category.repository.CategoryRepository;
import com.fitness.fitnessBack.empolyee.model.Employee;
import com.fitness.fitnessBack.empolyee.repository.EmployeeRepository;
import com.fitness.fitnessBack.trainer.model.Trainer;
import com.fitness.fitnessBack.trainer.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class FitnessBackApplication {
	@Autowired
	private TrainerRepository trainerRepository;

	@Autowired
	private ClubRepository clubRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private RoomRepository roomRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	public static void main(String[] args) {
		SpringApplication.run(FitnessBackApplication.class, args);

	}

	private List<Trainer> trainerList = new ArrayList<>();
	private List<Club> clubs = new ArrayList<>();
	private List<Room> rooms = new ArrayList<>();
	private List<Category> categories = new ArrayList<>();
	private List<String> icons = new ArrayList<>();

	private List<Employee> employees = new ArrayList<>();

	private void saveList() {
		for (int i = 1; i <= 10; i++) {
			trainerList.add(new Trainer("Jan", "Kowalski" + i, "email" + i + "@google.com", "0000000",
					LocalDate.of(1999, i, 1)));
		}

		for (int i = 1; i <= 10; i++) {
			rooms.add(new Room("name" + i, icons));
		}

		for (int i = 1; i <= 10; i++) {
			categories.add(new Category("name" + i, rooms));
		}
		for (int i = 1; i <= 3; i++) {
			clubs.add(new Club("name" + i, rooms.get(i - 1)));
		}
		for (int i = 1; i <= 3; i++) {
			employees.add(new Employee("Karol", "Kowalski" + i, "emailKlienta" + i + "@google.com", "0000000",
					LocalDate.of(1999, i, 1), clubs.get(i - 1)));
		}
	}

	@EventListener
	public void onReady(ApplicationReadyEvent e) {
		saveList();
		trainerRepository.saveAll(trainerList);
		clubRepository.saveAll(clubs);
		employeeRepository.saveAll(employees);
		roomRepository.saveAll(rooms);
		categoryRepository.saveAll(categories);
	}
}
