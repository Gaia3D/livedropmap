package gaia3d.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import gaia3d.domain.APIResult;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class DroneProjectAPIControllerTest {

	@Test
	public void test() throws Exception {
		MultiValueMap<String, Object> bodyMap = new LinkedMultiValueMap<>();
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("live_drone_map", getCustomHeader());
		
		createDroneProject(bodyMap);
		
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost/drone-projects";
		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(bodyMap, headers);
		APIResult aPIResult = restTemplate.postForObject(url, requestEntity, APIResult.class);
		
		log.info("statusCode = {}, validationCode = {}, message = {}", aPIResult.getStatusCode(), aPIResult.getValidationCode(), aPIResult.getMessage());
	}

	/**
	 * 암호화 된 header 값을 생성
	 * @return
	 * @throws Exception
	 */
	private String getCustomHeader() throws Exception {
		StringBuffer buffer = new StringBuffer();
		buffer.append("user_id=")
				.append("admin")
				.append("&")
				.append("api_key=")
				.append("test")
				.append("&")
				.append("token=")
				.append("22327341")
				.append("&")
				.append("role=")
				.append("ADMIN")
				.append("&")
				.append("algorithm=")
				.append("sha")
				.append("&")
				.append("type=")
				.append("jwt, mac")
				.append("&")
				.append("timestamp=")
				.append(System.nanoTime());
		
		return buffer.toString();
		//return AES128Cipher.encode(buffer.toString());
	}
	
	private MultiValueMap<String, Object> createDroneProject(MultiValueMap<String, Object> bodyMap) {
		bodyMap.add("drone_id", 1);
		bodyMap.add("drone_project_name", "서해 앞바다 불법 어선 탐지");
		bodyMap.add("shooting_area", "서해 앞바다");
		bodyMap.add("shooting_latitude1", 36.653252);
		bodyMap.add("shooting_longitude1", 125.997061);
		bodyMap.add("shooting_latitude2", 36.644963);
		bodyMap.add("shooting_longitude2", 126.123437);
		bodyMap.add("shooting_latitude3", 36.552572);
		bodyMap.add("shooting_longitude3", 126.007192);
		bodyMap.add("shooting_latitude4", 36.557361);
		bodyMap.add("shooting_longitude4", 126.113856);
		bodyMap.add("location", "POINT (126.042671 36.587415)");
		bodyMap.add("shooting_date", "20181016203800");
		bodyMap.add("description", "테스트 시끕하네... ");
		return bodyMap;
	}
	
	private MultiValueMap<String, Object> createDroneProject1(MultiValueMap<String, Object> bodyMap) {
		bodyMap.add("drone_id", 1);
		bodyMap.add("drone_project_name", "적조 현상 탐지");
		bodyMap.add("shooting_area", "남해 가까운 서해");
		bodyMap.add("shooting_latitude1", 36.409035);
		bodyMap.add("shooting_longitude1", 126.203551);
		bodyMap.add("shooting_latitude2", 36.413363);
		bodyMap.add("shooting_longitude2", 126.313451);
		bodyMap.add("shooting_latitude3", 36.345028);
		bodyMap.add("shooting_longitude3", 126.211229);
		bodyMap.add("shooting_latitude4", 36.347030);
		bodyMap.add("shooting_longitude4", 126.317264);
		bodyMap.add("location", "POINT (126.262715 36.370818)");
		bodyMap.add("shooting_date", "20181015203800");
		bodyMap.add("description", "테스트 입니다.");
		return bodyMap;
	}
}
