package org.dmiit3iy.ordermicroservice;

import net.devh.boot.grpc.server.autoconfigure.GrpcServerSecurityAutoConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@EnableAutoConfiguration(exclude = {GrpcServerSecurityAutoConfiguration.class})
class OrderMicroserviceApplicationTests {

	@Test
	void contextLoads() {
	}

}
