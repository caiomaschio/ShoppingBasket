As there wasn't any specification of what to do when there is a product different from: 'Orange', 'Apple', 'Banana' or 'Papaya', the program doesn't calculate the total and print an error message.

To compile the program(with jdk >= 8):

cd src
javac maschio/shopping/basket/exception/*.java maschio/shopping/basket/bean/*.java maschio/shopping/basket/dao/*.java maschio/shopping/basket/controller/*.java maschio/shopping/basket/*.java 

To run:
java maschio.shopping.basket.Shopping ListOfFruitNames

Ex:
java maschio.shopping.basket.Shopping Orange Orange Apple Papaya Papaya Banana Banana Apple Papaya Orange
java maschio.shopping.basket.Shopping Banana Papaya Papaya Apple Orange Papaya Orange Apple Apple Papaya Orange Orange Orange Papaya Banana Banana Banana Apple Papaya Apple Apple Apple
