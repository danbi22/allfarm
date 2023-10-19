package LoginController;

import logModel.LoginInfo;

public interface LoginDao {
	
	
	/**
	 * 회원가입 정보를 DB에 저장
	 * @param logininfo 회원 정보가 담긴 객체
	 * @return 성공하면 1을 리턴, 실패하면 0을 리턴
	 */
	int joinin(LoginInfo logininfo);
	
	/**
	 * 회원가입 여부 확인 회원가입 정보를 DB에서 가져와 받은 LoginInfo객체와 비교
	 * @param logininfo 회원 정보가 담긴 객체
	 * @return 로그인 정보가 있으면 1을 리턴, 없으면 0을 리턴
	 */
	int loginCheck(LoginInfo logininfo);
	
	/**
	 * id 텍스트필드에서 정보를 받아 DB에 같은 아이디가 있는지 비교
	 * @param id 텍스트드에서 가져온 id 정보
	 * @return 같은 아이디가 있으면 0을 리턴, 없으면 1을 리턴
	 */
	int duplicateIDCheck(String id);
	
	//TODO 프라이머리키로 연결된 crop 테이블이 있는지 확인 있으면 GrowingCropFrame으로 이동
	// 없으면 CreateFarmFrame으로 이동
	
	/**
	 * 프라이머리키로 연결된 crop 테이블이 있는지 확인  
	 * 
	 * @param id
	 * @return 이전 기록이 있으면 1을 리턴, 없으면 0을 리턴
	 */
	int checkRecorde(LoginInfo loginInfo);
}