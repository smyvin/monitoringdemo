package com.offsidegaming.monitoringdemo;

import com.offsidegaming.monitoringdemo.domain.Measurement;
import com.offsidegaming.monitoringdemo.domain.User;
import com.offsidegaming.monitoringdemo.repository.MeasurementRepository;
import com.offsidegaming.monitoringdemo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.offsidegaming.monitoringdemo.domain.Measurement.Type.COLD_WATER;
import static com.offsidegaming.monitoringdemo.domain.Measurement.Type.GAS;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class MonitoringDemoApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MeasurementRepository measurementRepository;

	@Test
	public void givenGasMeasurement_whenSearch_thenMeasurementDTO() throws Exception {
		User user = createUser();

		Measurement gasMeasurement = new Measurement();
		gasMeasurement.setUser(user);
		gasMeasurement.setType(GAS);
		gasMeasurement.setCreateDate(LocalDateTime.of(2022,5,10,19,10, 15));
		gasMeasurement.setQty(BigDecimal.valueOf(123.45));
		measurementRepository.saveAndFlush(gasMeasurement);

		mockMvc.perform(get("/measurement/search")
				.contentType(MediaType.APPLICATION_JSON)
				.param("userId", String.valueOf(user.getId()))
				.param("type", "GAS"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.content", hasSize(1)))
				.andExpect(jsonPath("$.content[0].type").value("GAS"))
				.andExpect(jsonPath("$.content[0].createDate").value("2022-05-10T19:10:15"))
				.andExpect(jsonPath("$.content[0].qty").value(123.45));
	}

	@Test
	public void givenWaterMeasurement_whenSearch_thenMeasurementDTO() throws Exception {
		User user = createUser();

		Measurement waterMeasurement = new Measurement();
		waterMeasurement.setUser(user);
		waterMeasurement.setType(COLD_WATER);
		waterMeasurement.setCreateDate(LocalDateTime.of(2022,5,10,19,15, 25));
		waterMeasurement.setQty(BigDecimal.valueOf(123.55));
		measurementRepository.saveAndFlush(waterMeasurement);

		mockMvc.perform(get("/measurement/search")
				.contentType(MediaType.APPLICATION_JSON)
				.param("userId", String.valueOf(user.getId()))
				.param("type", "COLD_WATER"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.content", hasSize(1)))
				.andExpect(jsonPath("$.content[0].type").value("COLD_WATER"))
				.andExpect(jsonPath("$.content[0].createDate").value("2022-05-10T19:15:25"))
				.andExpect(jsonPath("$.content[0].qty").value(123.55));
	}


	//todo: more tests (save, search, validate and etc.)



	private User createUser(){
		User user = new User();
		user.setName("test");
		return userRepository.saveAndFlush(user);
	}

}
