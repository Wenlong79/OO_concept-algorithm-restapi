package com.wl;

import com.wl.model.Article;
import com.wl.model.Page;
import com.wl.tools.Print;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.w3c.dom.ls.LSInput;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

@SpringBootTest
@RestController
@RequestMapping("")
class TopArticleApplicationTests {
    WebClient webClient = WebClient.create();
    PriorityQueue<Article> maxHeap = new PriorityQueue<>(new Comparator<Article>() {
        @Override
        public int compare(Article a1, Article a2) {
            if (a1.getNum_comments() == null) {
                a1.setNum_comments(0);
            }
            if (a2.getNum_comments() == null) {
                a2.setNum_comments(0);
            }
            if (a1.getNum_comments() > a2.getNum_comments()) {
                return -1;
            } else if (a1.getNum_comments() < a2.getNum_comments()) {
                return 1;
            } else { //tie in comments count
//                 once reached here, either title or story_title has value, or both have values. If both are null, it would
//                 be filtered out before entering the heap
                String a1Name = a1.getTitle() == null ? a1.getStory_title() : a1.getTitle();
                String a2Name = a2.getTitle() == null ? a2.getStory_title() : a2.getTitle();
                return -a1Name.compareTo(a2Name);
            }
        }
    });

    @Test
    void showArticlesInOnePage() {
        int pageNum = 1;
        String apiUrl = "https://jsonmock.hackerrank.com/api/articles?page=" + pageNum;
        // Make the API request and get the JSON response as a Flux (reactive)
        Page pageObject = webClient.get()
                .uri(apiUrl)
                .retrieve()
                .bodyToMono(Page.class)
                .block(); // blocking to get the result synchronously
        List<Article> articles = pageObject.getData();
        System.out.println("Article amount: " + articles.size());
        System.out.println(articles.get(8));
        System.out.println("************************************");
        System.out.println(articles.get(8).getTitle() == null);
        System.out.println(articles.get(8).getStory_title() == null);

        //Print.printList(articles);

    }

    @Test
    void testAlphabeticalOrder() {
        String str1 = "agple";
        String str2 = "apple";

        // Using compareTo() method
        int result = str1.compareTo(str2);

        if (result < 0) {
            System.out.println(str1 + " comes before " + str2);
        } else if (result > 0) {
            System.out.println(str1 + " comes after " + str2);
        } else {
            System.out.println(str1 + " is equal to " + str2);
        }
    }

    @Test
    public Integer getPageAmount() {
        String apiUrl = "https://jsonmock.hackerrank.com/api/articles?page=1";
        // Make the API request and get the JSON response as a Flux (reactive)
        Page pageObject = webClient.get()
                .uri(apiUrl)
                .retrieve()
                .bodyToMono(Page.class)
                .block(); // blocking to get the result synchronously
        //System.out.println(pageObject.getTotal_pages());
        //System.out.println(pageObject.getTotal_pages());
        return pageObject.getTotal_pages();
    }

    @Test
    public Page getPageObject(int pageNum) {
        String baseURL = "https://jsonmock.hackerrank.com/api/articles?page=" + pageNum;
        Page pageObject = webClient.get()
                .uri(baseURL)
                .retrieve()
                .bodyToMono(Page.class)
                .block(); // blocking to get the result synchronously
        return pageObject;
    }

    @Test
    public void displayPageDataSize() {
        Page page = getPageObject(5);
        System.out.println(page.getData().size());
    }


    @Test
    public void buildHeap() {
        int pageAmount = getPageAmount();
        for (int i = 1; i <= pageAmount; i++) {
            Page page = getPageObject(i);
            //System.out.println(page.getData().size());
            List<Article> articles = page.getData();
            for (Article article : articles) {
                if (!(article.getTitle() == null && article.getStory_title() == null)) {
                    maxHeap.add(article);
                }
            }
        }
    }

    @Test
    public void displayHeap(){
        buildHeap();
        System.out.println(maxHeap.size());
        System.out.println(maxHeap.poll());
        System.out.println(maxHeap.poll());
    }
}
