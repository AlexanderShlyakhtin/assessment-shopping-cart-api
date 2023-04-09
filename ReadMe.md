

# Project Title

Project shopping-cart-api is API developed for the calculation of Subtotal before discounts, Discount total, Subtotal after discounts, Taxable subtotal after discounts, Tax total, and Grand total of given products in request.

* Subtotal before discounts is the sum of prices of all products
* Discount total is the sum of applied discount coupons
* Subtotal after discounts is the sum of prices minus applied discounts
* Taxable subtotal after discounts is the sum of prices for taxable products minus discounts
* Tax total is the tax rate (8.25%) of taxable products' net price
* Grand total is the sum of Taxable subtotal after discounts and Subtotal after discounts for non-taxable items in the cart

## Requirements

For building and running the application you need the following:
* JDK: 17
* Maven 3
* or Docker

## Getting Started

There are several ways how to run the application

* Using docker

```
docker pull alexandershlyakhtin/shopping-cart-api
docker run --name shopping-cart-api -p 8080:8080 -d alexandershlyakhtin/shopping-cart-api:latest
```

* Using Maven
```
https://github.com/AlexanderShlyakhtin/assessment-shopping-cart-api.git
cd .\application\
./mvnw spring-boot:run
```


## Usage

By default, the application runs on port 8080. Be sure, that port is available.  
For usage API you can use Postman or similar software. URL for testing is:
```
POST http://localhost:8080/shopping-cart/calculate
```
You have to add the request body. You can find an example request body below.
```
{
  "items": [
    {
      "itemName": "Two Bite Brownies",
      "sku": 85294241,
      "isTaxable": false,
      "ownBrand": true,
      "price": 3.61
    },
    {
      "itemName": "Halo Top Vanilla Bean Ice Cream",
      "sku": 95422042,
      "isTaxable": false,
      "ownBrand": false,
      "price": 3.31
    }
  ]
}
```
