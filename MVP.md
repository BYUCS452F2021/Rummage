MVP for Rummage

1. Login
2. Register
3. Create sale
4. View single sale
5. View map of sales
6. Edit sale
7. Filtered search for sales

Additional Ideas to implement (stretch goals):

1. Follow sales
2. Follow users
3. Logout
4. Edit user account
5. Add individual items
6. Add item images

Views

1. Login
2. Register
3. Create Sale (Connected to from Sale List possibly FAB)
4. Sale List (linear list of all sales, links to Filter options, Create Sale, Map View, Sale Details) &quot;Home Page&quot;
5. Filter Options
6. Map view (google map with pins for sales, Link to filter options and Sale List)
7. Sale Details (includes edit button if user is owner)
8. Edit Sale Page

API

/user, GET, POST

/contact-info, GET, POST

/yard-sales, GET, POST

(Returned sales also include location information)

GET: with no params returns batched list of all sales, optionally include filters

Filters: date, city, state, type

/{saleId} GET, PUT
