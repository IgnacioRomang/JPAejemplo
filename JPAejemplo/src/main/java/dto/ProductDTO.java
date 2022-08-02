package dto;


public class ProductDTO {
	private long id;
	public ProductDTO() {
		super();
	}
	public ProductDTO(long id, long code, String name) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	private long code;
	private String name;
	public long getCode() {
		return code;
	}
	public void setCode(long code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
