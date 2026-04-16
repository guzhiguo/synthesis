package coffee;

/**
 * @author: bill
 * @create: 2025/08/03 19:10
 */
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CoffeeShopCrawler {

    public static void main(String[] args) {
        String url = "https://www.dianping.com/search/keyword/119/0_coffee";  // 你想爬取的页面地址
        List<CoffeeShop> coffeeShops = new ArrayList<>();

        try {
            // 模拟浏览器请求，设置 User-Agent 和其他请求头
            Document doc = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36")
                    .timeout(10000)  // 设置请求超时
                    .get();

            // 选择包含店铺信息的 HTML 元素，这些 CSS Selector 需要根据页面结构调整
            //此id下包含每页的门店数据信息
            Element content = doc.getElementById("shop-all-list");  // 假设每个店铺的信息在 .shop-item 类下

            //获取门店列表
            Elements ul = content.getElementsByTag("ul");
            //获取门店信息的li列表
            Elements allShops = ul.get(0).getAllElements();

            for (Element shop : allShops) {

                String link = shop.getElementsByClass("pic").get(0).select("a").attr("href");
//
//
//
//
//                String name = shop.select(".shop-name").text();
//                String link = shop.select("a").attr("href");
//                String location = shop.select(".shop-location").text();
//                String rating = shop.select(".shop-rating").text();
//                String reviewCount = shop.select(".shop-reviews").text();
//                String avgPrice = shop.select(".shop-price").text();
//                String recommendedMenu = shop.select(".shop-recommended-menu").text();
//                String positiveReviews = shop.select(".positive-reviews").text();
//                String negativeReviews = shop.select(".negative-reviews").text();

                System.out.printf("链接是："+link);

//                // 创建 CoffeeShop 对象
//                CoffeeShop coffeeShop = new CoffeeShop(name, link, location, rating, reviewCount, avgPrice, recommendedMenu, positiveReviews, negativeReviews);
//                coffeeShops.add(coffeeShop);
            }

//            // 输出爬取的结果
//            for (CoffeeShop shop : coffeeShops) {
//                System.out.println(shop);
//            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class CoffeeShop {
        String name, link, location, rating, reviewCount, avgPrice, recommendedMenu, positiveReviews, negativeReviews;

        public CoffeeShop(String name, String link, String location, String rating, String reviewCount, String avgPrice, String recommendedMenu, String positiveReviews, String negativeReviews) {
            this.name = name;
            this.link = link;
            this.location = location;
            this.rating = rating;
            this.reviewCount = reviewCount;
            this.avgPrice = avgPrice;
            this.recommendedMenu = recommendedMenu;
            this.positiveReviews = positiveReviews;
            this.negativeReviews = negativeReviews;
        }

        @Override
        public String toString() {
            return "CoffeeShop{" +
                    "name='" + name + '\'' +
                    ", link='" + link + '\'' +
                    ", location='" + location + '\'' +
                    ", rating='" + rating + '\'' +
                    ", reviewCount='" + reviewCount + '\'' +
                    ", avgPrice='" + avgPrice + '\'' +
                    ", recommendedMenu='" + recommendedMenu + '\'' +
                    ", positiveReviews='" + positiveReviews + '\'' +
                    ", negativeReviews='" + negativeReviews + '\'' +
                    '}';
        }
    }
}

