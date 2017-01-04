package org.mycompany.util;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.Random;

import org.junit.Test;

public class ShortURLTest {
	
	private static final String longUrl = "ancient-dusk-58211.herokuapp.com/sboard/readPage?page=1&perPageNum=10&searchType&keyword&bno=524298";
	
	@Test
	public void longtoShort() {
		
		URLShortener shortener = new URLShortener();
		String shortUrl = shortener.shortenURL(longUrl);
		System.out.println(shortUrl);
		
		assertThat(shortener.expandURL(shortUrl), is(longUrl));
	}

	@Test
	public void many() {
		URLShortener u = new URLShortener(5, "www.tinyurl.com/");
		String urls[] = { "www.google.com/", "www.google.com",
				"http://www.yahoo.com", "www.yahoo.com/", "www.amazon.com",
				"www.amazon.com/page1.php", "www.amazon.com/page2.php",
				"www.flipkart.in", "www.rediff.com", "www.techmeme.com",
				"www.techcrunch.com", "www.lifehacker.com", "www.icicibank.com" };

		for (int i = 0; i < urls.length; i++) {
			System.out.println("URL:" + urls[i] + "\tTiny: "
					+ u.shortenURL(urls[i]) + "\tExpanded: "
					+ u.expandURL(u.shortenURL(urls[i])));
		}
	}
}

