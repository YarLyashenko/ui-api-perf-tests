# Test Cases

### Test case 1: [Login](src/test/java/com/toptal/screening/LoginTest.java)

1. Open main page.
2. Click Login button on the header.
3. Fill popup with logging info of existent user: `vqg5gFqAW@vqg5gFqAW.com`/`Kc13gYoIY!1I`.
4. Click login button.
   * 4a. Recaptcha workaround: script should slick login button many times.
5. Login popup disappeared.
6. User logged in. User's name is displayed on the header.

### Test case 2: [Registration](src/test/java/com/toptal/screening/RegisterTest.java)

1. Open main page.
2. Click Login button on the header.
2. Click registration button.
3. User redirected to registration page.
3. Fill all required fields and click Register button.
3. Account Details page is opened with saved user's data provided during registration.

### Test case 3: [Search](src/test/java/com/toptal/screening/SearchTest.java)

1. Open main page.
2. Fill search field with some category(i.e.`okulary`) and perform search.
2. Select three criteria on filtering tab:

   Category | Value
      --- | ---  
   Typ produktu | Okulary przeciwsłoneczne
   Producent | Goggle
   Płeć | Unisex
3. Verify all three criteria are displayed at the top of the result page.

### Test case 4: [Shopping Cart: add items](src/test/java/com/toptal/screening/ShoppingCartTest.java)

1. Open main page.
2. Fill search field with some category(i.e.`okulary`) and perform search.
2. Add few items to the cart:
   1. Open random item.
   2. Click add to cart button.
   3. Verify success message is shown: `Produkt został dodany do koszyka.`
   4. Go back to search result page.
3. Open mini cart by clicking on cart icon on the header section of the page.
4. Verify all added in #3 items are displayed in a cart.

### Test case 5: [Shopping Cart: remove items](src/test/java/com/toptal/screening/ShoppingCartTest.java)

1. Open main page.
2. Fill search field with some category(i.e.`okulary`) and perform search.
2. Add few items to the cart:
   1. Open random item.
   2. Click add to cart button.
   3. Verify success message is shown: `Produkt został dodany do koszyka.`
   4. Go back to search result page.
3. Open mini cart by clicking on cart icon on the header section of the page.
2. Remove few (not all) items from the cart:
   1. Click remove from cart button.
   1. Click accept on confirmation popup.
4. Verify requested items are removed from cart.

### Test case 6: [Checkout](src/test/java/com/toptal/screening/CheckoutTest.java)

1. Open main page.
2. Fill search field with some category(i.e.`okulary`) and perform search.
2. Open random item.
2. Add random item to the cart.
3. Open mini cart by clicking on cart icon on the header section of the page.
4. Click checkout button.
4. Fill shipment details and payment info for the customer.
4. Click proceed with checkout button.
4. Verify shipping address and payment info are correct.
4. ~~Click submit order button~~. (not executed to not interfere with real store)

