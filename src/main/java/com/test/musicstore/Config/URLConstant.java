package com.test.musicstore.Config;

public class URLConstant {
    //request mapping
    public static final String ARTIST_URL = "/api/artist";
    public static final String ALBUM_URL = "/api/album";
    public static final String SALES_URL = "/api/sales";

    //api general
    public static final String SAVE_URL = "/save";
    public static final String GET_ALL_URL = "/get-all";

    //artist's api with parameters
    public static final String UPDATE_ARTIST_URL = "/update/{artistId}";
    public static final String GET_ARTIST_URL = "/get/{artistId}";
    public static final String DELETE_ARTIST_URL = "/delete/{artistId}";

    //artist's api with parameters
    public static final String UPDATE_ALBUM_URL = "/update/{albumId}";
    public static final String GET_ALBUM_URL = "/get/{albumId}";
    public static final String DELETE_ALBUM_URL = "/delete/{albumId}";

    // sales api with parameters
    public static final String UPDATE_SALES_URL = "/update/{salesId}";
    public static final String GET_SALES_URL = "/get";
    public static final String DELETE_SALES_URL = "/delete/{salesId}";
    public static final String GET_SALES_BY_DATE = "/get/{salesId}";
}
