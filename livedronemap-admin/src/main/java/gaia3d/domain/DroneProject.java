package gaia3d.domain;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Drone Project
 * @author Cheon JeongDae
 *
 */
@Getter
@Setter
@ToString
public class DroneProject {
	
	// 페이지 처리를 위한 시작
	private Long offset;
	// 페이지별 표시할 건수
	private Long limit;
		
	/********** 검색 조건 ************/
	private String search_word;
	// 검색 옵션. 0 : 일치, 1 : 포함
	private String search_option;
	private String search_value;
	private String start_date;
	private String end_date;
	private String order_word;
	private String order_value;
	private Long list_counter = 10l;

	/****** validator ********/
	private String method_mode;
	
	// 아이디 중복 확인 hidden 값
	private String duplication_value;

	// 고유번호
	private Integer drone_project_id;
	// drone 고유번호
	private Integer drone_id;
	// drone project명
	private String drone_project_name;
	// 촬영 지역
	private String shooting_area;
	// 촬영 시작 지점의 위도1
	private BigDecimal shooting_latitude1;
	// 촬영 시작 지점의 경도1
	private BigDecimal shooting_longitude1;
	// 촬영 시작 지점의 위도2
	private BigDecimal shooting_latitude2;
	// 촬영 시작 지점의 경도2
	private BigDecimal shooting_longitude2;
	// 촬영 시작 지점의 위도3
	private BigDecimal shooting_latitude3;
	// 촬영 시작 지점의 경도3
	private BigDecimal shooting_longitude3;
	// 촬영 시작 지점의 위도4
	private BigDecimal shooting_latitude4;
	// 촬영 시작 지점의 경도4
	private BigDecimal shooting_longitude4;
	// Multi Polygon
	private String location;
	// 촬영 일시
	private String shooting_date;
	// 상태. 0:준비중, 1:점검/테스트, 2:개별 정사영상, 3:후처리 영상 , 4:프로젝트 종료, 5:에러
	private String status;
	
	// 설명
	private String description;
	// 수정일
	private String update_date;
	// 등록일
	private String insert_date;

	public String getViewInsertDate() {
		if(this.insert_date == null || "".equals( insert_date)) {
			return "";
		}
		return insert_date.substring(0, 19);
	}
}