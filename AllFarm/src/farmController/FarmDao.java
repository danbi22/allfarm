package farmController;

import farmModel.Crop;
import logModel.LoginInfo;

public interface FarmDao {
	
	/**
	 * 작물 정보를 가진 객체를 주면 DB에 저장 저장
	 * @param crop
	 * @return 성공하면 1을 리턴, 실패하면 0을 리턴
	 */
	int createNewCrop(Crop crop);
	
	/**
	 * DB에서 ID에 맞는 CropInfo를 리턴
	 * @param loginInfo ID
	 * @return Crop 객체 리턴
	 */
	Crop getCropInfo(LoginInfo loginInfo);
	
	Crop watering(Crop crop);
}