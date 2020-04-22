package trendyol.filmfinder.apitest.repository;

public class FilmContent {
	
	String Title;
	String Hallows;
	String Year;
	String imdbID;
	String Type;
	String Poster;
	
		public FilmContent() {
			// TODO Auto-generated constructor stub
			this.Title = null;
			this.imdbID = null;
		}

		public void setTitle(String title) {
			this.Title = title;
		}
		
		public String getTitle() {
			return Title;
		}
		
		public void setimdbID(String imdbId) {
			this.imdbID = imdbId;
		}
		
		public String getimdbID() {
			return imdbID;
		}

}
