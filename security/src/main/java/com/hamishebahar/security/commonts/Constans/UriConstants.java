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
    public static final String NEWS_FIND_VIEW_WITH_FILTER = "/api/v1/view/news";

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
    public static final String STUDENT_FIND_VIEW_WITH_FILTER = "/api/v1/view/student";


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
    public static final String TEACHER_FIND_VIEW_WITH_FILTER = "/api/v1/view/teacher";

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

    //@GET method - download file
    public static final String MEDIA_DOWNLOAD = "/api/v1/view/media/download/{path}";

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

    public static final String FIND_ROLE="/api/v1/roles";
    public static final String FIND_PERMISSION="/api/v1/permissions";

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
    public static final String COURSE_FIND_VIEW_WITH_FILTER = "/api/v1/view/course";
    public static final String COURSE_FIND = "/api/v1/course/{id}";
    public static final String COURSE_VIEW_FIND = "/api/v1/view/course/{id}";
    public static final String COURSE_FIND_CATEGORIES_USAGE_VIEW_FIND = "/api/v1/view/course/categories";


    //**Category**
    //@POST method - insert a category
    public static final String CATEGORY_INSERT = "/api/v1/insert/category";
    //@PUT method - update a category
    public static final String CATEGORY_UPDATE = "/api/v1/category/{id}";
    //@DELETE method - delete a category
    public static final String CATEGORY_DELETE_WITH_ID = "/api/v1/category/{id}";
    public static final String CATEGORY_FIND = "/api/v1/category/{id}";
    public static final String CATEGORY_FIND_VIEW = "/api/v1/view/category/{id}";
    public static final String CATEGORY_FIND_ALL = "/api/v1/category";
    public static final String CATEGORY_FIND_VIEW_ALL = "/api/v1/view/category";

    //**AboutUs**
    //@POST method - insert a AboutUs
    public static final String ABOUT_US_INSERT = "/api/v1/insert/aboutus";
    //@PUT method - update a AboutUs
    public static final String ABOUT_US_UPDATE = "/api/v1/aboutus/{id}";
    //@DELETE method - delete a AboutUs
    public static final String ABOUT_US_DELETE_WITH_ID = "/api/v1/aboutus/{id}";
    //@GET method - get AboutUs
    public static final String ABOUT_US_FIND = "/api/v1/aboutus";
    public static final String ABOUT_US_VIEW_FIND = "/api/v1/view/aboutus";

    //**AboutUsPlans**
    //@POST method - insert a AboutUsPlan
    public static final String ABOUT_US_PLANS_INSERT = "/api/v1/insert/plans";
    //@PUT method - update a AboutUsPlan
    public static final String ABOUT_US_PLANS_UPDATE = "/api/v1/plans/{id}";
    //@DELETE method - delete a AboutUsPlan
    public static final String ABOUT_US_PLANS_DELETE_WITH_ID = "/api/v1/plans/{id}";
    //@GET method - get AboutUsPlan
    public static final String ABOUT_US_ALL_PLANS_VIEW_FIND = "/api/v1/view/plans";
    public static final String ABOUT_US_PLANS_VIEW_FIND = "/api/v1/view/plans/{id}";

    //**ContactUs**
    //@POST method - insert a ContactUs
    public static final String CONTACT_US_INSERT = "/api/v1/insert/contactus";
    //@PUT method - update a ContactUs
    public static final String CONTACT_US_UPDATE = "/api/v1/contactus/{id}";
    //@DELETE method - delete a ContactUs
    public static final String CONTACT_US_DELETE_WITH_ID = "/api/v1/contactus/{id}";
    //@GET method - get ContactUs
    public static final String CONTACT_US_VIEW_FIND = "/api/v1/view/contactus";

    //**Icons**
    //@POST method - insert an Icons
    public static final String ICON_INSERT = "/api/v1/insert/icon";
    //@PUT method - update an Icons
    public static final String ICON_UPDATE = "/api/v1/icon/{id}";
    //@DELETE method - delete an Icons
    public static final String ICON_DELETE_WITH_ID = "/api/v1/icon/{id}";
    //@GET method - get Icons
    public static final String ICON_FIND = "/api/v1/icon";
    public static final String ICON_FIND_ONE_VIEW = "/api/v1/view/icon/{id}";

    //**Links**
    //@POST method - insert an LINKS
    public static final String LINKS_INSERT = "/api/v1/insert/links";
    //@PUT method - update an LINKS
    public static final String LINKS_UPDATE = "/api/v1/links/{id}";
    //@DELETE method - delete an LINKS
    public static final String LINKS_DELETE_WITH_ID = "/api/v1/links/{id}";
    //@GET method - get LINKS
    public static final String LINKS_FIND_VIEW = "/api/v1/view/links";
    public static final String LINKS_FIND_ONE_VIEW = "/api/v1/view/links/{id}";

    //**Question**
    //@POST method - insert an QUESTION
    public static final String QUESTION_INSERT = "/api/v1/insert/question";
    //@PUT method - update an QUESTION
    public static final String QUESTION_UPDATE = "/api/v1/question/{id}";
    //@DELETE method - delete an QUESTION
    public static final String QUESTION_DELETE_WITH_ID = "/api/v1/question/{id}";
    //@GET method - get QUESTION
    public static final String QUESTION_FIND = "/api/v1/view/question";

    //**Activity**
    //@POST method - insert an ACTIVITY
    public static final String ACTIVITY_INSERT = "/api/v1/insert/activity";
    //@PUT method - update an ACTIVITY
    public static final String ACTIVITY_UPDATE = "/api/v1/activity/{id}";
    //@DELETE method - delete an ACTIVITY
    public static final String ACTIVITY_DELETE_WITH_ID = "/api/v1/activity/{id}";
    //@GET method - get ACTIVITY
    public static final String ACTIVITY_FIND = "/api/v1/view/activity";

    //todo RepostedCards

    //todo HomeApi

    //todo Periods

    //todo newsLetter

}
