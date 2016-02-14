# Bill-Management-System-Demo

Bill managment system app developed as an exercise in creating a working GUI with the Swing library.
The application works by parsing some special files that contain the necessary data. The files are contained in the project
and the app knows to look after them there.

On starting the application, users will pe prompted with a log in screen. After clicking 'Login' button, the
applications requires it's files. The fields are loaded from the default locations for these files so the only thing the user needs to do is press 'Manage'.

![](/pics/Login.png)


After loading the files the other tabs are unlocked so you can browse the different products in the 'Produse' page
where one can add, sort, delete and search for a certain product or see different statistics for stores.
On the 'Products' page you can click on the category you want to sort the list on, right click for a context menu which has a
search option or you can double click a product to modify it's price.

![](/pics/Products.png)


There is also a Statistics tab to view different statistics.

![](/pics/Statistics.png)

The factory pattern is used to generate the products from the files and a singleton object is used for the 
object that contains the data obtained after the parsing is done.
