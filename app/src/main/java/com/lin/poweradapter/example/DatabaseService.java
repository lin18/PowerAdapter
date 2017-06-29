package com.lin.poweradapter.example;

import com.lin.poweradapter.example.headerfooter.Footer;
import com.lin.poweradapter.example.headerfooter.Header;
import com.lin.poweradapter.example.staggered.Staggered;
import com.lin.poweradapter.model.IMulti;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lin18 on 2017/4/27.
 */

public class DatabaseService {

    public static List<Analog> getSampleData(int lenth) {
        List<Analog> list = new ArrayList<>();
        for (int i = 0; i < lenth; i++) {
            Analog analog = new Analog();
            analog.text = "Analog " + i;
            analog.resId = i % 2 == 0 ? R.drawable.ic_cloud_upload : R.drawable.ic_import_export;
            analog.url = "https://avatars1.githubusercontent.com/u/6948411?v=3&u=2d57480717d6294a5c18126b3eb79cf4a0ad2a55&s=40";
            list.add(analog);
        }
        return list;
    }

    public static List<Staggered> getStaggeredData(int lenth) {
        List<Staggered> list = new ArrayList<>();
        for (int i = 0; i < lenth; i++) {
            Staggered staggered = new Staggered();
            staggered.title = "title " + i;
            staggered.subtitle = "subtitle " + i;
            staggered.more = i % 3 == 0 ? "more " + i : null;
            list.add(staggered);
        }
        return list;
    }

    public static List<IMulti> getHeaderFooterData(int lenth) {
        List<IMulti> list = new ArrayList<>();
        list.add(new Header());
        for (int i = 0; i < lenth; i++) {
            Analog analog = new Analog();
            analog.text = "Analog " + i;
            analog.resId = i % 2 == 0 ? R.drawable.ic_cloud_upload : R.drawable.ic_import_export;
            analog.url = "https://avatars1.githubusercontent.com/u/6948411?v=3&u=2d57480717d6294a5c18126b3eb79cf4a0ad2a55&s=40";
            list.add(analog);
        }
        list.add(new Footer());
        return list;
    }

    public static List<Analog> getFlowData() {
        List<Analog> list = new ArrayList<>();
        for (int i = 0; i < texts.length; i++) {
            Analog analog = new Analog();
            analog.text = texts[i];
            analog.resId = i % 2 == 0 ? R.drawable.ic_cloud_upload : R.drawable.ic_import_export;
            analog.url = "https://avatars1.githubusercontent.com/u/6948411?v=3&u=2d57480717d6294a5c18126b3eb79cf4a0ad2a55&s=40";
            list.add(analog);
        }
        return list;
    }

    public final static String[] texts = new String[] {"sdfg", "压下", "有", "45", "践枯", "基扔干无所适从", "播放", "DSFGHSFFD", "标有", "JK", "5644565", "顶戴", "柘城点虫鸣", "穰", "我的电脑", "柘城", "SDG56df", "仍5D655F然SF", "腺体", "紫罗兰", "股份制", "plk", "586856575", "标准", "奇才", "需要有机玻璃"};
}
