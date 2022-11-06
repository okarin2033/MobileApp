package ru.project.mobile.parsing;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import ru.project.mobile.dto.BookSimple;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ParsingServiceSearch {
    static final private String path = "https://book24.ru/";

    public List<BookSimple> getSearchRequest(String title){

        try {
            Document document = Jsoup.connect(path+"search/?q="+title).get();
            ArrayList<BookSimple> books = new ArrayList<BookSimple>();
            Element body = document.body();
            Elements htmlBooksHolder = body.select("div.catalog__product-list-holder");
            Elements booksInHolder = htmlBooksHolder.select("article.product-card");
            for (Element e:booksInHolder) {
                BookSimple book= new BookSimple();

                String url = e.select("picture.product-card__picture").select("source").attr("data-srcset");
                url = url.split(" ")[0];
                if (url.startsWith("//"))
                    url = url.replace("//", "https://");
                if (url.endsWith(","))
                    url = url.replace(",", "");

                book.setAuthor(e.select("div.author-list").text());
                book.setName(e.select("a.product-card__name").attr("title"));
                book.setUrl("https://book24.ru" + e.select("a.product-card__name").attr("href"));
                book.setImageUrl(url);
                books.add(book);
            }
            return books;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

}
