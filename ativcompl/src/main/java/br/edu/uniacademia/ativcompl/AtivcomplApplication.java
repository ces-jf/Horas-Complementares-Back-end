package br.edu.uniacademia.ativcompl;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.edu.uniacademia.ativcompl.domain.Activity;
import br.edu.uniacademia.ativcompl.domain.Address;
import br.edu.uniacademia.ativcompl.domain.Campus;
import br.edu.uniacademia.ativcompl.domain.Category;
import br.edu.uniacademia.ativcompl.domain.Course;
import br.edu.uniacademia.ativcompl.domain.Student;
import br.edu.uniacademia.ativcompl.domain.User;
import br.edu.uniacademia.ativcompl.domain.UserType;
import br.edu.uniacademia.ativcompl.domain.enums.Valuation;
import br.edu.uniacademia.ativcompl.repositories.ActivityRepository;
import br.edu.uniacademia.ativcompl.repositories.AddressRepository;
import br.edu.uniacademia.ativcompl.repositories.CampusRepository;
import br.edu.uniacademia.ativcompl.repositories.CategoryRepository;
import br.edu.uniacademia.ativcompl.repositories.CourseRepository;
import br.edu.uniacademia.ativcompl.repositories.StudentRepository;
import br.edu.uniacademia.ativcompl.repositories.UserRepository;
import br.edu.uniacademia.ativcompl.repositories.UserTypeRepository;

@SpringBootApplication
public class AtivcomplApplication implements CommandLineRunner{
	
	@Autowired 	private AddressRepository addressRepository;
	@Autowired 	private UserRepository userRepository;
	@Autowired  private UserTypeRepository userTypeRepository;
	@Autowired  private CampusRepository campusRepository;
	@Autowired  private CourseRepository courseRepository;
	@Autowired  private CategoryRepository categoryRepository;
	@Autowired  private StudentRepository studentRepository;
	@Autowired  private ActivityRepository activityRepository;

	public static void main(String[] args) {
		SpringApplication.run(AtivcomplApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
//		Address addr1 = new Address(null, "Rua I", "621", "Jardim Itália II", "Uberaba"); 
//		Address addr2 = new Address(null, "Rua Primavera", "560", "Baixa da Colina", "Rio Branco");
//		Address addr3 = new Address(null, "Rua Major Izidoro Jerônimo da Rocha", "450","Jacarecica", "Maceió");
//		Address addr4 = new Address(null, "Quadra SHIN QI 2", "581", "Setor de Habitações Individuais Norte", "Brasília");
//		Address addr5 = new Address(null, "Rua Monte Sião", "967", "Tarumã", "Manaus");
//		Address addr6 = new Address(null, "Quadra QRSW 5 Bloco A-3", "604", "Setor Sudoeste", "Brasília");
//		Address addr7 = new Address(null, "Rua Noé Fortes", "519", "Uruguai", "Teresina");
		Address addr8 = new Address(null, "Rua Dom Luiz", "970", "Centro", "Belo Jardim");
		Address addr9 = new Address(null, "Alameda do Lago", "608", "Posse", "Teresópolis");
		Address addr10 = new Address(null, "Rua J", "914", "Nova Cidade", "Manaus");
		Address addr11 = new Address(null, "Rua H", "854", "Alto Maron", "Vitória da Conquista");
		Address addr12 = new Address(null, "Vila Militar", "817", "Tambauzinho", "João Pessoa");
		Address addr13 = new Address(null, "Rua Alemanha", "546", "Loteamento Menino Jesus I", "Sinop");
		Address addr14 = new Address(null, "Rua Pinheiro Machado", "650", "Cidade Alta", "Alegrete");
		Address addr15 = new Address(null, "Rua Juvêncio Hilário de Santana", "767", "Bugio", "Aracaju");
		Address addr16 = new Address(null, "Rua Zabaram", "647", "Jorge Teixeira", "Manaus");
		Address addr17 = new Address(null, "Rodovia BR-364", "672", "Eletronorte", "Porto Velho");
		Address addr18 = new Address(null, "Quadra CLS 110 Bloco B", "986", "Asa Sul", "Brasília");
		Address addr19 = new Address(null, "Rua Severina Batista Andrade Goulart", "657", "Novo Horizonte", "Crato");
		Address addr20 = new Address(null, "Rua Gravataí", "864", "Potengi", "Natal");
				
		
		// Coordenador: "Sistemas de Informação" e "Engenharia de Software"
		User user1 = new User(null, "375.577.622-74", "Vitória Carolina Barbosa", "vitoriacarolinabarbosa-96@lphbrasil.com.br", "UQk4wSoai7");
		// Supervisor: "Sistemas de Informação" e "Engenharia de Software"
		User user2 = new User(null, "124.906.495-31", "Guilherme Samuel Pires", "guilhermesamuelpires@cedda.com.br", "1XbTTe9udg");
		
		// Coordenador: "Administração"
		User user3 = new User(null, "441.377.809-00", "Agatha Brenda Moreira", "agathabrendamoreira@pubdesign.com.br", "p4x2RimGji");
		// Supervisor: "Administração"
		User user4 = new User(null, "420.790.053-59", "Joana Carolina Souza", "joanacarolinasouza@jbtc.com", "4ld5o8RYYb"); 

		// Coordenador: "Nutrição" e "Fisioterapia"
		User user5 = new User(null, "169.588.437-03", "Bernardo Anthony Davi Drumond", "bernardoanthonydavidrumond-77@raya3.com.br", "W2BRb4G4Qz");
		// Supervisor: "Nutrição" e "Fisioterapia"
		User user6 = new User(null, "272.659.622-30", "Isabel Renata Agatha Pinto", "isabelrenataagathapinto@charquesorocaba.com.br", "ILyZyTXflo");

		// Coordenador: "Teologia" e "Filosofia"
		// Supervisor: "Teologia" e "Filosofia"
		User user7 = new User(null, "267.364.255-22", "Luiz Kaique Isaac Jesus", "luizkaiqueisaacjesus@budsoncorporation.com", "owX8j6ltA0");
		

		// Administador e Aluno "Sistemas de Informação"
		User user8 = new User(null, "017.728.964-38", "Nathan Noah Thiago da Costa", "nathannoahthiagodacosta@esplanadaviagens.com.br", "MN98AOw70K");

		// Alunos:
		// "Sistemas de Informação"
		User user9 = new User(null, "824.973.055-01", "Diogo Raul Silva", "diogoraulsilva-78@digen.com.br", "D7Zi2H17rW");

		// "Engenharia de Software"
		User user10 = new User(null, "770.164.986-70", "Rafaela Clara Bárbara da Rocha", "rrafaelaclarabarbaradarocha@candello.abv.br", "O4bOFIrC6U");
		User user11 = new User(null, "634.546.494-55", "Bernardo Anderson Lima", "bernardoandersonlima-77@inductothermgroup.com.br", "4RuW0MAPLb");
		
		// "Administração"
		User user12 = new User(null, "193.628.382-49", "Antonella Heloise Aragão", "antonellaheloisearagao_@grupoitamaraty.com.br", "SDAmsNgDEy");
		User user13 = new User(null, "759.012.025-11", "Milena Nicole Ester Melo", "milenanicoleestermelo-70@ouserTipolock.com", "KiDJmNXmd4");
		User user14 = new User(null, "558.108.283-96", "Ruan Pedro Viana", "ruanpedroviana..ruanpedroviana@anbima.com.br", "QP8Eg0Y7Pw");
		
		// "Nutrição"
		User user15 = new User(null, "020.984.203-23", "Lucas Oliver das Neves", "lucasoliverdasneves__lucasoliverdasneves@zoomfoccus.com.br", "FoeKiXm0ZB");
		User user16 = new User(null, "955.586.448-93", "Tomás Paulo Jesus", "ttomaspaulojesus@nelsoncosta.com.br", "N05yZtdyMU");
		
		// "Fisioterapia"
		User user17 = new User(null, "348.646.029-30", "Pedro Henrique Diego Roberto Martins", "pedrohenriquediegorobertomartins..pedrohenriquediegorobertomartins@tec3.com.br", "LzmEdCpfRp");
		User user18 = new User(null, "845.932.684-51", "Osvaldo Edson Vieira", "osvaldoedsonvieira__osvaldoedsonvieira@focustg.com.br", "eqNBljRNau");
		
		// "Teologia"
		User user19 = new User(null, "459.518.706-82", "Joaquim Lucca da Cruz", "joaquimluccadacruz__joaquimluccadacruz@callan.com.br", "r9EngPfORH");
		
		// "Filosofia"
		User user20 = new User(null, "685.776.221-00", "João Miguel da Conceição", "joaomigueldaconceicao__joaomigueldaconceicao@carlosalbertoleite.com.br", "qMEWbTgXku");

		UserType userTipo1 = new UserType(null, "Coordenador");
		UserType userTipo2 = new UserType(null, "Supervisor");
		UserType userTipo3 = new UserType(null, "Administrador");
		UserType userTipo4 = new UserType(null, "Aluno");
		
		Campus cmp1 = new Campus(null, "Academia");
		Campus cmp2 = new Campus(null, "Arnaldo Janssen");
		Campus cmp3 = new Campus(null, "Seminário Santo Antônio");
		
		Course crs1 = new Course(null, "Sistemas de Informação", 216.0, cmp1);
		Course crs2 = new Course(null, "Engenharia de Software", 144.0, cmp1);
		Course crs3 = new Course(null, "Administração", 216.0, cmp1);
		Course crs4 = new Course(null, "Nutrição", 320.0, cmp2);
		Course crs5 = new Course(null, "Fisioterapia", 180.0, cmp2);
		Course crs6 = new Course(null, "Teologia", 320.0, cmp3);
		Course crs7 = new Course(null, "Filosofia", 180.0, cmp3);
		
		Category cat1 = new Category(null, "Exercício de Monitoria");
		Category cat2 = new Category(null, "Iniciação Científica");
		Category cat3 = new Category(null, "Curso de Extensão");
		Category cat4 = new Category(null, "Palestra");
		Category cat5 = new Category(null, "Curso de Línguas");
		Category cat6 = new Category(null, "Curso de Programação");

		Student stu1 = new Student(null, user8, addr8);
		Student stu2 = new Student(null, user9, addr9);
		Student stu3 = new Student(null, user10, addr10);
		Student stu4 = new Student(null, user11, addr11);
		Student stu5 = new Student(null, user12, addr12);
		Student stu6 = new Student(null, user13, addr13);
		Student stu7 = new Student(null, user14, addr14);
		Student stu8 = new Student(null, user15, addr15);
		Student stu9 = new Student(null, user16, addr16);
		Student stu10 = new Student(null, user17, addr17);
		Student stu11 = new Student(null, user18, addr18);
		Student stu12 = new Student(null, user19, addr19);
		Student stu13 = new Student(null, user20, addr20);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Activity at1 = new Activity(null, "Curso de Phyton", sdf.parse("15/05/2021"), 12.0, 5.0, false, "", null, "", stu1, cat6);
		Activity at2 = new Activity(null, "Curso de Inglês", sdf.parse("20/02/2020"), 1200.0, 640.0, false, "", null, "", stu2, cat5);
		Activity at3 = new Activity(null, "Curso de PHP", sdf.parse("20/04/2021"), 6.0, 6.0, true, "abc12es381a", Valuation.ACEITAR, "", stu3,  cat6);
		Activity at4 = new Activity(null, "Palestra Senhor dos Aneis", sdf.parse("28/08/2021"), 3.0, 3.0, true, "25e23rty31hj", Valuation.NEGAR_TOTAL, "Essa palestra não condiz com o curso", stu4, cat4);
			
		user1.getUsersTypes().addAll(Arrays.asList(userTipo1));
		user2.getUsersTypes().addAll(Arrays.asList(userTipo2));
		user3.getUsersTypes().addAll(Arrays.asList(userTipo1));
		user4.getUsersTypes().addAll(Arrays.asList(userTipo2));
		user5.getUsersTypes().addAll(Arrays.asList(userTipo1));
		user6.getUsersTypes().addAll(Arrays.asList(userTipo2));
		user7.getUsersTypes().addAll(Arrays.asList(userTipo1, userTipo2));
		user8.getUsersTypes().addAll(Arrays.asList(userTipo3, userTipo4));
		user9.getUsersTypes().addAll(Arrays.asList(userTipo4));
		user10.getUsersTypes().addAll(Arrays.asList(userTipo4));
		user11.getUsersTypes().addAll(Arrays.asList(userTipo4));
		user12.getUsersTypes().addAll(Arrays.asList(userTipo4));
		user13.getUsersTypes().addAll(Arrays.asList(userTipo4));
		user14.getUsersTypes().addAll(Arrays.asList(userTipo4));
		user15.getUsersTypes().addAll(Arrays.asList(userTipo4));
		user16.getUsersTypes().addAll(Arrays.asList(userTipo4));
		user17.getUsersTypes().addAll(Arrays.asList(userTipo4));
		user18.getUsersTypes().addAll(Arrays.asList(userTipo4));
		user19.getUsersTypes().addAll(Arrays.asList(userTipo4));
		user20.getUsersTypes().addAll(Arrays.asList(userTipo4));
		
		userTipo1.getUsers().addAll(Arrays.asList(user1, user3, user5, user7));
		userTipo2.getUsers().addAll(Arrays.asList(user2, user4, user6, user7));
		userTipo3.getUsers().addAll(Arrays.asList(user8));
		userTipo4.getUsers().addAll(Arrays.asList(user8, user9, user10, user11, user12, user13, user14, user15, user16, user17, user18, user19, user20));
		
		user1.getCourses().addAll(Arrays.asList(crs1, crs2));
		user2.getCourses().addAll(Arrays.asList(crs1, crs2));
		user3.getCourses().addAll(Arrays.asList(crs3));
		user4.getCourses().addAll(Arrays.asList(crs3));
		user5.getCourses().addAll(Arrays.asList(crs4, crs5));
		user6.getCourses().addAll(Arrays.asList(crs4, crs5));
		user7.getCourses().addAll(Arrays.asList(crs6, crs7));
		// Alunos
		user8.getCourses().addAll(Arrays.asList(crs1));
		user9.getCourses().addAll(Arrays.asList(crs1));
		user10.getCourses().addAll(Arrays.asList(crs2));
		user11.getCourses().addAll(Arrays.asList(crs2));
		user12.getCourses().addAll(Arrays.asList(crs3));
		user13.getCourses().addAll(Arrays.asList(crs3));
		user14.getCourses().addAll(Arrays.asList(crs3));
		user15.getCourses().addAll(Arrays.asList(crs4));
		user16.getCourses().addAll(Arrays.asList(crs4));
		user17.getCourses().addAll(Arrays.asList(crs5));
		user18.getCourses().addAll(Arrays.asList(crs5));
		user19.getCourses().addAll(Arrays.asList(crs6));
		user20.getCourses().addAll(Arrays.asList(crs7));
		
		crs1.getUsers().addAll(Arrays.asList(user1, user2, user8, user9));
		crs2.getUsers().addAll(Arrays.asList(user1, user2, user10, user11));
		crs3.getUsers().addAll(Arrays.asList(user3, user4, user12, user13, user14));
		crs4.getUsers().addAll(Arrays.asList(user5, user6, user15, user16));
		crs5.getUsers().addAll(Arrays.asList(user5, user6, user17, user18));
		crs6.getUsers().addAll(Arrays.asList(user7, user19));
		crs7.getUsers().addAll(Arrays.asList(user7, user20));
		
		cmp1.getCourses().addAll(Arrays.asList(crs1, crs2, crs3));
		cmp2.getCourses().addAll(Arrays.asList(crs4, crs5));
		cmp3.getCourses().addAll(Arrays.asList(crs6, crs7));
		
		
		addressRepository.saveAll(Arrays.asList(addr8, addr9, addr10, addr11, addr12, addr13, addr14, addr15, addr16, addr17, addr18, addr19, addr20));
		userRepository.saveAll(Arrays.asList(user1, user2, user3, user4, user5, user6, user7, user8, user9, user10, user11, user12, user13, user14, user15, user16, user17, user18, user19, user20));
		userTypeRepository.saveAll(Arrays.asList(userTipo1, userTipo2, userTipo3, userTipo4));
		campusRepository.saveAll(Arrays.asList(cmp1, cmp2, cmp3));
		courseRepository.saveAll(Arrays.asList(crs1, crs2, crs3, crs4, crs5, crs6, crs7));
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6));
		studentRepository.saveAll(Arrays.asList(stu1, stu2, stu3, stu4, stu5, stu6, stu7, stu8, stu9, stu10, stu11, stu12, stu13));
		activityRepository.saveAll(Arrays.asList(at1, at2, at3, at4));
		
	}

}
