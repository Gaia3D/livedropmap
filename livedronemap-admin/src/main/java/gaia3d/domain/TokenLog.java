package gaia3d.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * token log
 * @author Cheon JeongDae
 *
 */
@Getter
@Setter
@ToString
public class TokenLog extends SearchDomain {
	
	// 날짜 포맷
	// TODO 환경설정에서 읽기
	private String datePattern = "yyyy-MM-dd HH:mm:ss";

	// 환경 설정 token 유효 시간
	private Integer rest_api_token_max_age;
	private String client_name;
	
	// 고유키
	private Long token_log_id;
	// client 고유키
	private Integer client_id;
	// 사용자 아이디
	private String user_id;
	// 토큰
	private String token;
	// 토큰 상태. 토큰 상태. 0 : 사용중, 1 : 시간만료
	private String token_status;
	// 토큰 지속 시간. 240분(기본값)
	private Date expires;
	// 년
	private String year;
	// 월
	private String month;
	// 일
	private String day;
	// 일년중 몇주
	private String year_week;
	// 이번달 몇주
	private String week;
	// 시간
	private String hour;
	// 분
	private String minute;
	// 수정일
	private String update_date;
	// 등록일
	private String insert_date;
	
	public String getViewExpires() {
		if(this.expires == null) {
			return "";
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
		return dateFormat.format(expires);
	}
	public String getViewInsert_date() {
		if(this.insert_date == null || "".equals(insert_date)) {
			return "";
		}
		return insert_date.substring(0, 19);
	}
	public String getViewUpdate_date() {
		if(this.update_date == null || "".equals(update_date)) {
			return "";
		}
		return update_date.substring(0, 19);
	}
	
}
