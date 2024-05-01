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


    //**Teachers**
    //@POST method - insert a STUDENT
    public static final String TEACHER_INSERT = "/api/v1/teacher";
    //@PUT method - update a STUDENT
    public static final String TEACHER_UPDATE = "/api/v1/teacher/{id}";
    //@DELETE method - delete a STUDENT
    public static final String TEACHER_DELETE_WITH_ID = "/api/v1/teacher/{id}";
    public static final String TEACHER_DELETE_WITH_RANGE = "/api/v1/teacher/range";
    //@GET method - get a STUDENT
    public static final String TEACHER_FIND_WITH_FILTER = "/api/v1/teacher";

    //**Media**
    //@POST method - insert a Media
    public static final String MEDIA_INSERT = "/api/v1/media";
    //@PUT method - update a Media
    public static final String MEDIA_UPDATE = "/api/v1/media/{id}";
    //@DELETE method - delete a Media
    public static final String MEDIA_DELETE_WITH_ID = "/api/v1/media/{id}";
    public static final String MEDIA_DELETE_WITH_RANGE = "/api/v1/media/range";
    //@GET method - get a Media
    public static final String MEDIA_FIND_WITH_FILTER = "/api/v1/media";

    //**Users**
    //@POST method - insert a USER
    public static final String USER_REGISTER = "/api/v1/register/users";
    //@PUT method - update a USER
    public static final String USER_UPDATE = "/api/v1/users/{id}";
    public static final String USER_ADMIN_UPDATE = "/api/v1/admin/users/{id}";
    //@DELETE method - delete a USER
    public static final String USER_ADMIN_DELETE_WITH_ID = "/api/v1/admin/users/{id}";
    //@GET method - get a USER
    public static final String USER_ADMIN_FIND_WITH_FILTER = "/api/v1/admin/users";
    public static final String USER_FIND = "/api/v1/users/{id}";
}
