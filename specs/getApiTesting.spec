# Login Page Test


## GET Users List Test
tags: get, users, list, gETUsersListTest, jra-5678

* Set base URL to "https://jsonplaceholder.typicode.com"

* Initialize request specification


## GET request testi

* API e GET request gonder "https://jsonplaceholder.typicode.com/posts/1"
* Status kodu "200" olmalidir
* Json Cavabinda "title" deyeri "sunt aut facere repellat provident occaecati excepturi optio reprehenderit"
* Json cavabinda "body" deyeri bos olmamalidir


## GET request header server testi

* API e GET request gonder "https://jsonplaceholder.typicode.com/posts/1"
* Status kodu "200" olmalidir
* Header "Server" movcud olmalidir

## GET reuqest bodyde suscipit metni ehtiva edilmelidir testi

* API e GET request gonder "https://jsonplaceholder.typicode.com/posts/1"
* Status kodu "200" olmalidir
* Body icinde "suscipit"  metnini ehtiva etmelidir

## GET request time 500 ms den az olmalidir testi

* API e GET request gonder "https://jsonplaceholder.typicode.com/posts/1"
* Status kodu "200" olmalidir
* Respons cavab muddeti "900" milliSaniyeden az olmalidir


## GET request uerID deyeri int dir testi

* API e GET request gonder "https://jsonplaceholder.typicode.com/posts/1"
* Status kodu "200" olmalidir
* Json cavabinda verilen "userId" deyeri ededdir