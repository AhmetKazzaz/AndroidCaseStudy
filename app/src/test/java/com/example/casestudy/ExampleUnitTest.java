package com.example.casestudy;

import com.example.casestudy.model.Banner;
import com.example.casestudy.datasource.CampaignsDataSource;
import com.example.casestudy.model.HotDeal;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }


    @Test
    public void testLife() {

        ArrayList<HotDeal> hotDeals = new ArrayList<>();
        hotDeals.add(new HotDeal());
        hotDeals.add(new HotDeal());

        ArrayList<Banner> banners = new ArrayList<>();
        banners.add(new Banner());
        banners.add(new Banner());
        banners.add(new Banner());
        banners.add(new Banner());


        CampaignsDataSource campaignsDataSource = new CampaignsDataSource();
        List<Object> arr = campaignsDataSource.combineLists(hotDeals, banners);

        assertThat(arr.get(0), instanceOf(HotDeal.class));
        assertThat(arr.get(1), instanceOf(Banner.class));
        assertThat(arr.get(2), instanceOf(HotDeal.class));
        assertThat(arr.get(3), instanceOf(Banner.class));
        assertThat(arr.get(4), instanceOf(Banner.class));
        assertThat(arr.get(5), instanceOf(Banner.class));

        System.out.println(arr);
    }
}