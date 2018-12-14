package kim.taeng.service.aoptest;

import org.springframework.stereotype.Service;

@Service
public class AopTestService {

	public void aopTest() {
		System.out.println("aop job");
	}

}
