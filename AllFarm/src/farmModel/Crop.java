package farmModel;

public class Crop {
	public interface Entity{
		String CROP_TBL_NAME = "CROP";
		String CROP_COL_ID = "id";
		String CROP_COL_NAME = "name";
		String CROP_COL_LEVELPOINT = "levelpoint";
		String CROP_COL_TYPE = "type";
		String CROP_NAME_BANANA = "바나나";
		String CROP_NAME_GRAPE = "포도";
		
	}
	private String id;
	private String name;
	private double levelPoint;
	private String type;
	
	public Crop(String name) {
		this.name = name;
	}

	public Crop(String id, String name, String type) {
		this.id = id;
		this.name = name;
		this.type = type;
	}
	
	public Crop(String id, String name, double levelPoint, String type) {
		super();
		this.id = id;
		this.name = name;
		this.levelPoint = levelPoint;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getLevelPoint() {
		return levelPoint;
	}

	public void setLevelPoint(double levelPoint) {
		this.levelPoint = levelPoint;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}