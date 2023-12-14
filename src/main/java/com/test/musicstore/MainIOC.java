package com.test.musicstore;
import com.test.musicstore.POJO.Request.SaveArtistRequest;
import com.test.musicstore.Service.Impl.AlbumServiceImpl;
import com.test.musicstore.Service.Impl.ArtistServiceImpl;
import com.test.musicstore.Service.Impl.SalesServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class MainIOC {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(new String[] {"Services.xml"});
        ArtistServiceImpl artistService = applicationContext.getBean("artistService", ArtistServiceImpl.class);
        AlbumServiceImpl albumService = applicationContext.getBean("albumService", AlbumServiceImpl.class);
        SalesServiceImpl salesService = applicationContext.getBean("salesService", SalesServiceImpl.class);
        // retrieve configured instance
        Object userList = artistService.getAllArtists();
        Object albumList = albumService.getAllAlbum();
        Object salesList = salesService.getAllSales();
    }
}
