# Bill-Management-System-Demo

Bill managment system app developed as an exercise in creating a working GUI with the Swing library.
The application works by parsing some special files that contain the necessary data. The files are contained in the project
and the applications knows to look after them there.

On starting the applications, users will pe prompted with a log in screen. After clicking 'Login' button, the
applications requires it's files. The fields are loaded with the default locations for these files so what the user needs to do
is to click 'Gestiune'. Now the other tabs are unlocked so you can browse the different products in the 'Produse' page
where one can add, sort, delete and search for a certain product or see different statistics for stores.

On the 'Produse' page you can click on the category you want to sort the list on, right click for a context menu which has a
search option or you can double click a product to modify it's price.

The factory pattern is used to generate the products from the files and a singleton object is used for the 
object that contains the data obtained after the parsing is done.
