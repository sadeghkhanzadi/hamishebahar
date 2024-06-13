package com.hamishebahar.security.commonts.Constans;

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
    //@POST method - insert a Teachers
    public static final String TEACHER_INSERT = "/api/v1/teacher";
    //@PUT method - update a Teachers
    public static final String TEACHER_UPDATE = "/api/v1/teacher/{id}";
    //@DELETE method - delete a Teachers
    public static final String TEACHER_DELETE_WITH_ID = "/api/v1/teacher/{id}";
    public static final String TEACHER_DELETE_WITH_RANGE = "/api/v1/teacher/range";
    //@GET method - get a Teachers
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
    public static final String USER_FIND_WITH_TOKEN="/api/v1/users/token";

    //**LOGIN**
    public static final String LOGIN_JWT = "/jwt/login";

    //**Course**
    //@POST method - insert a Course
    public static final String COURSE_INSERT = "/api/v1/insert/course";
    //@PUT method - update a Course
    public static final String COURSE_UPDATE = "/api/v1/course/{id}";
    //@DELETE method - delete a Course
    public static final String COURSE_DELETE_WITH_ID = "/api/v1/course/{id}";
    //@GET method - get a Course
    public static final String COURSE_FIND_WITH_FILTER = "/api/v1/course";

    public static final String COURSE_FIND = "/api/v1/course/{id}";


    //**Category**
    //@POST method - insert a category
    public static final String CATEGORY_INSERT = "/api/v1/insert/category";
    //@PUT method - update a category
    public static final String CATEGORY_UPDATE = "/api/v1/category/{id}";
    //@DELETE method - delete a category
    public static final String CATEGORY_DELETE_WITH_ID = "/api/v1/category/{id}";
    public static final String CATEGORY_FIND = "/api/v1/category/{id}";
    public static final String CATEGORY_FIND_ALL = "/api/v1/category";

    //**AboutUs**
    //@POST method - insert a AboutUs
    public static final String ABOUT_US_INSERT = "/api/v1/insert/aboutus";
    //@PUT method - update a AboutUs
    public static final String ABOUT_US_UPDATE = "/api/v1/aboutus/{id}";
    //@DELETE method - delete a AboutUs
    public static final String ABOUT_US_DELETE_WITH_ID = "/api/v1/aboutus/{id}";
    //@GET method - get AboutUs
    public static final String ABOUT_US_FIND = "/api/v1/aboutus";

    //**ContactUs**
    //@POST method - insert a ContactUs
    public static final String CONTACT_US_INSERT = "/api/v1/insert/contactus";
    //@PUT method - update a ContactUs
    public static final String CONTACT_US_UPDATE = "/api/v1/contactus/{id}";
    //@DELETE method - delete a ContactUs
    public static final String CONTACT_US_DELETE_WITH_ID = "/api/v1/contactus/{id}";
    //@GET method - get ContactUs
    public static final String CONTACT_US_FIND = "/api/v1/contactus";
}
