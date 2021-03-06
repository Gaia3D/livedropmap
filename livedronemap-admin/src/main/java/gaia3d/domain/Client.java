package gaia3d.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Cheon JeongDae
 *
 */
@Getter
@Setter
@ToString(callSuper=true)
public class Client extends SearchDomain {
	
	// 상태 : 사용중
	public static final String IN_USE = "Y";
		
	private String view_api_key;
	private String generate_api_key_check;
	
	/****** validator ********/
	private String method_mode;

	/****** DB 사용 ********/
	// 고유번호
	private Integer client_id;
	// 그룹 고유번호
	private Integer client_group_id;
	// 서버명
	private String client_name;
	// 서버 IP
	private String client_ip;
	// 사용유무, Y : 사용, N : 사용안함(기본)
	private String use_yn;
	// API KEY
	private String api_key;
	// 설명
	private String description;
	// 수정일
	private String update_date;
	// 등록일
	private String register_date;
}
