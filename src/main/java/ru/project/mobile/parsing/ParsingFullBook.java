package ru.project.mobile.parsing;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import ru.project.mobile.dto.Book;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParsingFullBook {
    private final String baseUrl = "https://book24.ru/product/";
    public Book getBookData(String productName){
        try {
            Document document = Jsoup.connect(baseUrl+productName).get();
            Book bookDto = new Book();
            bookDto.setUrl(baseUrl+productName);

            Element body = document.body();

            String bookFullName = body.selectFirst("h1[itemprop = name]").text();
            String bookDescription = body.selectFirst("div.product-about__text").text();
            String bookAuthor = body.selectFirst("dd.product-characteristic__value").text();

            Elements imageContainers = body.select("picture.product-poster__main-picture");
            ArrayList<String> imageList = new ArrayList<>();
            imageContainers.forEach(container -> {

                        String url = container.select("source").attr("data-srcset");
                        if (url.equals("")){
                            url = container.select("source").attr("srcset");
                        }
                        {
                            url = url.split(" ")[0];
                            if (url.startsWith("//"))
                                url = url.replace("//", "https://");
                            if (url.endsWith(","))
                                url = url.replace(",", "");
                        } //url formating
                        imageList.add(url);
                    });

            String price = body.select("span.product-sidebar-price__price")
                    .text().replace("₽","")
                    .replace(" ", "");

            String genre = body.select("dd.product-characteristic__value").get(2).text();
            String article = body.select("p.product-detail-page__article").text().split(" ")[1];

            bookDto.setBookName(bookFullName);
            bookDto.setDescription(bookDescription);
            bookDto.setAuthor(bookAuthor);
            bookDto.setImageUrl(imageList);
            bookDto.setPrice(Double.parseDouble(price));
            bookDto.setGenre(genre);
            bookDto.setArticul(article);

            return bookDto;
        }
        catch (Exception e){
            e.printStackTrace();
            //log
            System.out.println("Coonection to web page failed");
        }
        return null;
    }

}
