package com.example.springboot.service;


import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.ExtractByUrl;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.JsonFilePageModelPipeline;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.pipeline.MultiPagePipeline;

@TargetUrl("https:\\/\\/www.varzesh3.com\\/news\\/\\d{7}\\/.*")
//@HelpUrl("https://www.varzesh3.com")

public class Varzesh3 {

    private boolean hasImg;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private boolean hasCategory;
    private boolean hasSummary;

//    @ExtractBy(value = "//h1[@class='entry-title public']/strong/a/text()", notNull = true)
//    private String name;

//    @ExtractByUrl("https://github\\.com/(\\w+)/.*")
//    private String author;

//    @ExtractBy("//div[@id='readme']")
//    private String source;

//    @ExtractBy(value = "//div[@class='repository-lang-stats']//li//span[@class='lang']",multi = true)
//    private List<String> language;

//    @ExtractBy("//a[@class='social-count js-social-count']/text()")
//    private String content;

    @ExtractBy("//[@class='headline']")
    private String title;

    @ExtractByUrl
    private String url;

    public static void main(String[] args) {
        OOSpider.create(Site.me().setSleepTime(0).setRetryTimes(1), new JsonFilePageModelPipeline(), Varzesh3.class)
                .addPipeline(new JsonFilePipeline("./extra/a.txt")).addPipeline(new MultiPagePipeline()).addPipeline(new ConsolePipeline())
                .addUrl("https://www.varzesh3.com")
                .thread(1).
                run();
                //.setScheduler(new FileCacheQueueScheduler("/data/webmagic/cache/")).thread(15).run();
    }
}
