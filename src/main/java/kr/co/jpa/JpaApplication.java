package kr.co.jpa;

import kr.co.entity.Member;
import kr.co.entity.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;

@Slf4j
@SpringBootApplication

@Configuration
@EnableJpaRepositories(basePackages = "kr.co.entity")
@EntityScan(basePackages = "kr.co.entity")
@EnableTransactionManagement
public class JpaApplication {


	public static void main(String[] args) {
		SpringApplication.run(JpaApplication.class, args);
	}


	@Bean
	public CommandLineRunner Member(MemberRepository repository) {
		return (args) -> {
			//엔티티 생성
			Member member1 = new Member("홍길동");
			Member member2 = new Member("이순신");
			Member member3 = new Member("김기동");
			Member member4 = new Member("김길수");
			Member member5 = new Member("김방진");
			Member member6 = new Member("김우동");

			//엔티티 저장
			repository.save(member1);
			repository.save(member2);
			repository.save(member3);
			repository.save(member4);
			repository.save(member5);
			repository.save(member6);

			//엔티티 조회
			for(Member m : repository.findAll()) {
				log.info("회원 명 : {}", m.getName());
			}

			//엔티티 삭제
			repository.delete(member2);

			for(Member m : repository.findAll()) {
				log.info("회원 명 : {}", m.getName());
			}
		};
	}

	@Bean

	public CommandLineRunner findMember(MemberRepository repository) {
		return (args) -> {
			PageRequest pagerequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "name"));
			Page<Member> m1 = repository.findAll(pagerequest);

			log.info("페이지 건수 : {}", m1.getTotalPages());
			log.info("다음 페이지 존재 여부 : {}", m1.hasNext());

			List<Member> members = m1.getContent();
			for (Member m : members) {
				log.info("회원 명 : {}", m.getName());
			}
		};
	}
}
