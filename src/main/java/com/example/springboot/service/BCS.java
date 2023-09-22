package com.example.springboot.service;


import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.HasKey;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.ExtractByUrl;
import us.codecraft.webmagic.pipeline.JsonFilePageModelPipeline;
import us.codecraft.webmagic.scheduler.FileCacheQueueScheduler;

@TargetUrl("https:\\/\\/www.bcs.org\\/events-calendar\\/\\d{4}\\/[a-zA-Z]*\\/[a-zA-Z-]*")
@HelpUrl("https://www.bcs.org/sitemap.xml")

public class BCS {

    private boolean hasImg;
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

    @ExtractBy("//[@class='pageheader-title']")
    private String title;

    @ExtractByUrl
    private String url;

    public static void main(String[] args) {
        OOSpider.create(Site.me().setSleepTime(0).setRetryTimes(1), new JsonFilePageModelPipeline(), BCS.class)
                .addUrl("https://www.bcs.org/sitemap.xml").thread(1).run();
                //.setScheduler(new FileCacheQueueScheduler("/data/webmagic/cache/")).thread(15).run();
    }
}
