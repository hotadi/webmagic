package com.example.springboot.service;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import java.util.List;

public class UrlProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);


    private static  String URL_XPATH;
    private static String siteName;

    static final String directory = "./extra";
    @Override
    public void process(Page page) {
        Html html ;
        if(Utils.pageTodayFetched(siteName, directory)){
            html = Utils.readHtmlSavedPage(siteName, directory);
            page.setHtml(html);
        }
        else {
            html = page.getHtml();

            Utils.save(siteName, directory, html.get());
        }

//        List<String> all1 = links.all();
//        System.out.println("Link xml:  "+ html);

//        List<String> all = page.getHtml().xpath(URL_XPATH).links().all();
        List<String> all = html.regex("https:\\/\\/www.bcs.org\\/events-calendar\\/\\d{4}\\/[a-zA-Z]*\\/[a-zA-Z-]+").all();
//        List<String> all = html.regex("https:\\/\\/www.varzesh3.com\\/news\\/\\d{7}\\/").all();
        page.addTargetRequest(all.get(0));
        System.out.println(all.get(0));
        page.putField("url", page.getHtml().css("pageheader-title"));
        //System.out.println("Link list:  "+ all.size() + " ********" + all);

    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        siteName = args[0];
        String baseUrl = args[1];
        URL_XPATH = args[2];

        Spider
            .create(new UrlProcessor())
            .addUrl(baseUrl)
            .addPipeline(new JsonFilePipeline(directory))
            .thread(1)
            .run();
    }
}