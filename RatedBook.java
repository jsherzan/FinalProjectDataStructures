
public class RatedBook {
	private String isbn;
	private String title;
	private boolean recommend;
	
	public String getIsbn() {
		return this.isbn;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public boolean getRecommend() {
		return this.recommend;
	}
	
	public RatedBook(String isbn, String title, boolean recommend) {
		this.isbn = isbn;
		this.title = title;
		this.recommend = recommend;
	}
}
