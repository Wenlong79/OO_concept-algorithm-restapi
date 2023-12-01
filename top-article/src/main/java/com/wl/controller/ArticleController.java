package com.wl.controller;

import com.wl.model.Article;
import com.wl.model.Page;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

@RestController
@RequestMapping("")
public class ArticleController {
    WebClient webClient = WebClient.create();
    String baseURL = "https://jsonmock.hackerrank.com/api/articles?page=";

    PriorityQueue<Article> maxHeap = new PriorityQueue<>(new Comparator<Article>() {
        @Override
        public int compare(Article a1, Article a2) {
            if (a1.getNum_comments() == null){
                a1.setNum_comments(0);
            }
            if (a2.getNum_comments() == null){
                a2.setNum_comments(0);
            }
            if (a1.getNum_comments() > a2.getNum_comments()){
                return -1;
            } else if (a1.getNum_comments() < a2.getNum_comments()){
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

    private static List<Article> allArticlesSorted = new ArrayList<>();

    public ArticleController() {
        buildArticleList();
    }

    //purely for test
    @GetMapping("/testPage1")
    public void toPage(HttpServletResponse response) throws IOException {
        response.sendRedirect("https://jsonmock.hackerrank.com/api/articles?page=1");
    }

    //purely for test
    @GetMapping("/getPage1Data0")
    public String testPage() {
        String apiUrl = "https://jsonmock.hackerrank.com/api/articles?page=1";
        // Make the API request and get the JSON response as a Flux (reactive)
        Page pageObject = webClient.get()
                .uri(apiUrl)
                .retrieve()
                .bodyToMono(Page.class)
                .block(); // blocking to get the result synchronously
        return pageObject.getData().get(0).toString();
    }

    @GetMapping("/pageAmount")
    public String pageAmount(){
        Integer pageAmount = getPageAmount();
        return "Total page amount :" + pageAmount;
    }

    public Integer getPageAmount(){
        String apiUrl = "https://jsonmock.hackerrank.com/api/articles?page=1";
        // Make the API request and get the JSON response as a Flux (reactive)
        Page pageObject = webClient.get()
                .uri(apiUrl)
                .retrieve()
                .bodyToMono(Page.class)
                .block(); // blocking to get the result synchronously
        //System.out.println(pageObject.getTotal_pages());
        return pageObject.getTotal_pages();
    }

    public Page getPageObject(int pageNum){
        String baseURL = "https://jsonmock.hackerrank.com/api/articles?page="+pageNum;
        Page pageObject = webClient.get()
                .uri(baseURL)
                .retrieve()
                .bodyToMono(Page.class)
                .block(); // blocking to get the result synchronously
        return pageObject;
    }


    public void buildArticleList() {
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
        while (!maxHeap.isEmpty()){
            allArticlesSorted.add(maxHeap.poll());
        }
    }

    @GetMapping("/articles")
    public List<Article> showAllArticles(){
        return allArticlesSorted;
    }

    @GetMapping("/articles/{limit}")
    public List<Article> showLimitedArticles(@PathVariable int limit){
        List<Article> result = new ArrayList<>();
        for (int i = 0; i < limit; i++){
            result.add(allArticlesSorted.get(i));
        }
        return result;
    }


    @GetMapping("/articleNames/{limit}")
    public String[] showLimitedArticleNames(@PathVariable int limit){
        String[] result = new String[limit];
        for (int i = 0; i < limit; i++){
            Article thisArticle = allArticlesSorted.get(i);
            result[i] = thisArticle.getTitle() == null ? thisArticle.getStory_title() : thisArticle.getTitle();
        }
        return result;
    }
}
