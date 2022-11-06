package ru.project.mobile.parsing;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import ru.project.mobile.dto.BookSimple;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParsingServiceMainPage {
    final private String path = "https://book24.ru/";
    public List<BookSimple> getNewBooks(){
        try {
            Document document = Jsoup.connect(path).get();
            ArrayList<BookSimple> books = new ArrayList<BookSimple>();
            Element body = document.body();
            Element htmlBooksHolder = body.selectFirst("div[itemtype = https://schema.org/OfferCatalog]");
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
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


}
