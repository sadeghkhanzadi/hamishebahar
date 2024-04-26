package com.commonts.Constans;

public class UriConstants {
    //**NEWS**
    //@POST method - insert a NEWS
    public static final String NEWS_INSERT = "/api/v1/news";
    //@PUT method - update a NEWS
    public static final String NEWS_UPDATE = "/api/v1/news/{id}";
    //@DELETE method - delete a NEWS
    public static final String NEWS_DELETE_WITH_ID = "/api/v1/news/{id}";
    public static final String NEWS_DELETE_WITH_RANGE = "/api/v1/news/range";
    //@GET method - get a NEWS
    public static final String NEWS_FIND_WITH_FILTER = "/api/v1/news";

    //**Students**
    //@POST method - insert a STUDENT
    public static final String STUDENT_INSERT = "/api/v1/student";
    //@PUT method - update a STUDENT
    public static final String STUDENT_UPDATE = "/api/v1/student/{id}";
    //@DELETE method - delete a STUDENT
    public static final String STUDENT_DELETE_WITH_ID = "/api/v1/student/{id}";
    public static final String STUDENT_DELETE_WITH_RANGE = "/api/v1/student/range";
    //@GET method - get a STUDENT
    public static final String STUDENT_FIND_WITH_FILTER = "/api/v1/student";
}
