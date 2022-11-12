import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
public class test {

    @Test
    public void t() throws IOException {
        Document doc = org.jsoup.Jsoup.connect("https://u.duboku.io/vodtype/2.html").get();
        Elements e = doc.select(".clearfix");
        System.out.println("类型：" + e.select("li").text());
        //System.out.println("标题" + e.select("ul").text() + e.select("li").text() + e.select("btn").text());
        //System.out.println("作者" + e.select(".myui-screen__list nav-slide clearfix").text());
        //System.out.println("来源" + e.select(".col-1-1.fl").text());
    }
}
