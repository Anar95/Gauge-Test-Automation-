# API Test Ssenarileri - Real Heyat Nümüneleri

## 1. POST - Uğurlu İstifadeçi Yaratma

* Set base URL to "https://jsonplaceholder.typicode.com"
* Initialize request specification
* Add endpoint "/users"
* Add as a header "Content-Type" = "application/json"
* Add body as file from resource "created-user.json"
* Post request and display respons
* Status kodu "201" olmalidir
* Json cavabinda "id" deyeri bos olmamalidir
* Json Cavabinda "email" deyeri "anar@test.com"

## 2. POST - Boş Body ile Xeta

* Set base URL to "https://jsonplaceholder.typicode.com"
* Initialize request specification
* Add endpoint "/posts"
* Add as a header "Content-Type" = "application/json"
* Add body as text "{}"
* Post request and display respons
* Status kodu "201" olmalidir


## 3. POST - Dublikate Yaratma Yoxlanışı

* Set base URL to "https://jsonplaceholder.typicode.com"
* Initialize request specification
* Add endpoint "/posts"
* Add as a header "Content-Type" = "application/json"
* Add body as file from resource "valid-post-user.json"
* Post request and display respons
* Save value of "id" as "firstPostId"
* Initialize request specification
* Add endpoint "/posts"
* Add as a header "Content-Type" = "application/json"
* Add body as file from resource "valid-post-user.json"
* Post request and display respons
* Json cavabinda "id" deyeri "firstPostId" ile ferqlidir

## 4. POST - Authorization ile (Token)

* Set base URL to "https://jsonplaceholder.typicode.com"
* Initialize request specification
* Add endpoint "/posts"
* Add as a header "Content-Type" = "application/json"
* Add as a header "Authorization" = "Bearer fake-token-12345"
* Add body as file from resource "valid-post-user.json"
* Post request and display respons
* Status kodu "201" olmalidir


## 5. PUT - Tam Update (Bütün fielder)

* Set base URL to "https://jsonplaceholder.typicode.com"
* Initialize request specification
* Add endpoint "/posts/1"
* Add as a header "Content-Type" = "application/json"
* Add body as file from resource "full-update-post.json"
* Put request and display respons
* Status kodu "200" olmalidir
* Json Cavabinda "id" deyeri integer "1" beraberdir
* Json cavabinda "title" deyeri bos olmamalidir
* Json cavabinda "body" deyeri bos olmamalidir
* Json cavabinda "userId" deyeri bos olmamalidir


## 6. PUT - Mövcud Olmayan Resurs

* Set base URL to "https://jsonplaceholder.typicode.com"
* Initialize request specification
* Add endpoint "/posts/999999999"
* Add as a header "Content-Type" = "application/json"
* Add body as file from resource "update-post-user1.json"
* Put request and display respons
* Status kodu "500" olmalidir


## 7. PATCH - Yalnız Title Update

* Set base URL to "https://jsonplaceholder.typicode.com"
* Initialize request specification
* Add endpoint "/posts/1"
* Add as a header "Content-Type" = "application/json"
* Add body as text "{\"title\": \"Yalnız title deyişdi\"}"
* Patch request and display respons
* Status kodu "200" olmalidir
* Json Cavabinda "title" deyeri string "Yalnız title deyişdi" beraberdir


## 8. DELETE - Uğurlu Silme


* Set base URL to "https://jsonplaceholder.typicode.com"
* Initialize request specification
* Add endpoint "/posts/1"
* Delete request and display respons
* Status kodu "200" olmalidir